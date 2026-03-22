package net.minecraft.client.renderer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.optifine.CustomColors;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lagometer;
import net.optifine.RandomEntities;
import net.optifine.SmartAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.render.ChunkVisibility;
import net.optifine.render.CloudRenderer;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadowUtils;
import net.optifine.shaders.gui.GuiShaderOptions;
import net.optifine.util.ChunkUtils;
import net.optifine.util.RenderChunkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderGlobal implements IWorldEventListener, IResourceManagerReloadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
   private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
   private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("textures/environment/clouds.png");
   private static final ResourceLocation END_SKY_TEXTURES = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation FORCEFIELD_TEXTURES = new ResourceLocation("textures/misc/forcefield.png");
   public final Minecraft mc;
   private final TextureManager renderEngine;
   private final RenderManager renderManager;
   private WorldClient world;
   private Set<RenderChunk> chunksToUpdate = new ObjectLinkedOpenHashSet();
   private List<ContainerLocalRenderInformation> renderInfos = Lists.newArrayListWithCapacity(69696);
   private final Set<TileEntity> setTileEntities = Sets.newHashSet();
   private ViewFrustum viewFrustum;
   private int starGLCallList = -1;
   private int glSkyList = -1;
   private int glSkyList2 = -1;
   private final VertexFormat vertexBufferFormat;
   private VertexBuffer starVBO;
   private VertexBuffer skyVBO;
   private VertexBuffer sky2VBO;
   private int cloudTickCounter;
   public final Map<Integer, DestroyBlockProgress> damagedBlocks = Maps.newHashMap();
   private final Map<BlockPos, ISound> mapSoundPositions = Maps.newHashMap();
   private final TextureAtlasSprite[] destroyBlockIcons = new TextureAtlasSprite[10];
   private Framebuffer entityOutlineFramebuffer;
   private ShaderGroup entityOutlineShader;
   private double frustumUpdatePosX = Double.MIN_VALUE;
   private double frustumUpdatePosY = Double.MIN_VALUE;
   private double frustumUpdatePosZ = Double.MIN_VALUE;
   private int frustumUpdatePosChunkX = Integer.MIN_VALUE;
   private int frustumUpdatePosChunkY = Integer.MIN_VALUE;
   private int frustumUpdatePosChunkZ = Integer.MIN_VALUE;
   private double lastViewEntityX = Double.MIN_VALUE;
   private double lastViewEntityY = Double.MIN_VALUE;
   private double lastViewEntityZ = Double.MIN_VALUE;
   private double lastViewEntityPitch = Double.MIN_VALUE;
   private double lastViewEntityYaw = Double.MIN_VALUE;
   private ChunkRenderDispatcher renderDispatcher;
   private ChunkRenderContainer renderContainer;
   private int renderDistanceChunks = -1;
   private int renderEntitiesStartupCounter = 2;
   private int countEntitiesTotal;
   private int countEntitiesRendered;
   private int countEntitiesHidden;
   private boolean debugFixTerrainFrustum;
   private ClippingHelper debugFixedClippingHelper;
   private final Vector4f[] debugTerrainMatrix = new Vector4f[8];
   private final Vector3d debugTerrainFrustumPosition = new Vector3d();
   private boolean vboEnabled;
   IRenderChunkFactory renderChunkFactory;
   private double prevRenderSortX;
   private double prevRenderSortY;
   private double prevRenderSortZ;
   public boolean displayListEntitiesDirty = true;
   private boolean entityOutlinesRendered;
   private final Set<BlockPos> setLightUpdates = Sets.newHashSet();
   private CloudRenderer cloudRenderer;
   public Entity renderedEntity;
   public Set chunksToResortTransparency = new LinkedHashSet();
   public Set chunksToUpdateForced = new LinkedHashSet();
   private Set<RenderChunk> chunksToUpdatePrev = new ObjectLinkedOpenHashSet();
   private Deque visibilityDeque = new ArrayDeque();
   private List<ContainerLocalRenderInformation> renderInfosEntities = new ArrayList(1024);
   private List<ContainerLocalRenderInformation> renderInfosTileEntities = new ArrayList(1024);
   private List renderInfosNormal = new ArrayList(1024);
   private List renderInfosEntitiesNormal = new ArrayList(1024);
   private List renderInfosTileEntitiesNormal = new ArrayList(1024);
   private List renderInfosShadow = new ArrayList(1024);
   private List renderInfosEntitiesShadow = new ArrayList(1024);
   private List renderInfosTileEntitiesShadow = new ArrayList(1024);
   private int renderDistance = 0;
   private int renderDistanceSq = 0;
   private static final Set SET_ALL_FACINGS;
   private int countTileEntitiesRendered;
   private IChunkProvider worldChunkProvider = null;
   private Long2ObjectMap<Chunk> worldChunkProviderMap = null;
   private int countLoadedChunksPrev = 0;
   private RenderEnv renderEnv;
   public boolean renderOverlayDamaged;
   public boolean renderOverlayEyes;
   private boolean firstWorldLoad;
   private static int renderEntitiesCounter;

   public RenderGlobal(Minecraft mcIn) {
      this.renderEnv = new RenderEnv(Blocks.AIR.getDefaultState(), new BlockPos(0, 0, 0));
      this.renderOverlayDamaged = false;
      this.renderOverlayEyes = false;
      this.firstWorldLoad = false;
      this.cloudRenderer = new CloudRenderer(mcIn);
      this.mc = mcIn;
      this.renderManager = mcIn.getRenderManager();
      this.renderEngine = mcIn.getTextureManager();
      this.renderEngine.bindTexture(FORCEFIELD_TEXTURES);
      GlStateManager.glTexParameteri(3553, 10242, 10497);
      GlStateManager.glTexParameteri(3553, 10243, 10497);
      GlStateManager.bindTexture(0);
      this.updateDestroyBlockIcons();
      this.vboEnabled = OpenGlHelper.useVbo();
      if (this.vboEnabled) {
         this.renderContainer = new VboRenderList();
         this.renderChunkFactory = new VboChunkFactory();
      } else {
         this.renderContainer = new RenderList();
         this.renderChunkFactory = new ListChunkFactory();
      }

      this.vertexBufferFormat = new VertexFormat();
      this.vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
      this.generateStars();
      this.generateSky();
      this.generateSky2();
   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.updateDestroyBlockIcons();
   }

   private void updateDestroyBlockIcons() {
      TextureMap texturemap = this.mc.getTextureMapBlocks();

      for(int i = 0; i < this.destroyBlockIcons.length; ++i) {
         this.destroyBlockIcons[i] = texturemap.getAtlasSprite("minecraft:blocks/destroy_stage_" + i);
      }

   }

   public void makeEntityOutlineShader() {
      if (OpenGlHelper.shadersSupported) {
         if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
            ShaderLinkHelper.setNewStaticShaderLinkHelper();
         }

         ResourceLocation resourcelocation = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.entityOutlineShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), resourcelocation);
            this.entityOutlineShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.entityOutlineFramebuffer = this.entityOutlineShader.getFramebufferRaw("final");
         } catch (IOException var3) {
            IOException ioexception = var3;
            LOGGER.warn("Failed to load shader: {}", resourcelocation, ioexception);
            this.entityOutlineShader = null;
            this.entityOutlineFramebuffer = null;
         } catch (JsonSyntaxException var4) {
            JsonSyntaxException jsonsyntaxexception = var4;
            LOGGER.warn("Failed to load shader: {}", resourcelocation, jsonsyntaxexception);
            this.entityOutlineShader = null;
            this.entityOutlineFramebuffer = null;
         }
      } else {
         this.entityOutlineShader = null;
         this.entityOutlineFramebuffer = null;
      }

   }

   public void renderEntityOutlineFramebuffer() {
      if (this.isRenderEntityOutlines()) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
         this.entityOutlineFramebuffer.framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, false);
         GlStateManager.disableBlend();
      }

   }

   protected boolean isRenderEntityOutlines() {
      if (!Config.isFastRender() && !Config.isShaders() && !Config.isAntialiasing()) {
         boolean var1;
         if (this.entityOutlineFramebuffer != null && this.entityOutlineShader != null) {
            Minecraft var10000 = this.mc;
            if (Minecraft.player != null) {
               var1 = true;
               return var1;
            }
         }

         var1 = false;
         return var1;
      } else {
         return false;
      }
   }

   private void generateSky2() {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      if (this.sky2VBO != null) {
         this.sky2VBO.deleteGlBuffers();
      }

      if (this.glSkyList2 >= 0) {
         GLAllocation.deleteDisplayLists(this.glSkyList2);
         this.glSkyList2 = -1;
      }

      if (this.vboEnabled) {
         this.sky2VBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderSky(bufferbuilder, -16.0F, true);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.sky2VBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.glSkyList2 = GLAllocation.generateDisplayLists(1);
         GlStateManager.glNewList(this.glSkyList2, 4864);
         this.renderSky(bufferbuilder, -16.0F, true);
         tessellator.draw();
         GlStateManager.glEndList();
      }

   }

   private void generateSky() {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      if (this.skyVBO != null) {
         this.skyVBO.deleteGlBuffers();
      }

      if (this.glSkyList >= 0) {
         GLAllocation.deleteDisplayLists(this.glSkyList);
         this.glSkyList = -1;
      }

      if (this.vboEnabled) {
         this.skyVBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderSky(bufferbuilder, 16.0F, false);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.skyVBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.glSkyList = GLAllocation.generateDisplayLists(1);
         GlStateManager.glNewList(this.glSkyList, 4864);
         this.renderSky(bufferbuilder, 16.0F, false);
         tessellator.draw();
         GlStateManager.glEndList();
      }

   }

   private void renderSky(BufferBuilder bufferBuilderIn, float posY, boolean reverseX) {
      int i = true;
      int j = true;
      bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);
      int k = (this.renderDistance / 64 + 1) * 64 + 64;

      for(int l = -k; l <= k; l += 64) {
         for(int i1 = -k; i1 <= k; i1 += 64) {
            float f = (float)l;
            float f1 = (float)(l + 64);
            if (reverseX) {
               f1 = (float)l;
               f = (float)(l + 64);
            }

            bufferBuilderIn.pos((double)f, (double)posY, (double)i1).endVertex();
            bufferBuilderIn.pos((double)f1, (double)posY, (double)i1).endVertex();
            bufferBuilderIn.pos((double)f1, (double)posY, (double)(i1 + 64)).endVertex();
            bufferBuilderIn.pos((double)f, (double)posY, (double)(i1 + 64)).endVertex();
         }
      }

   }

   private void generateStars() {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      if (this.starVBO != null) {
         this.starVBO.deleteGlBuffers();
      }

      if (this.starGLCallList >= 0) {
         GLAllocation.deleteDisplayLists(this.starGLCallList);
         this.starGLCallList = -1;
      }

      if (this.vboEnabled) {
         this.starVBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderStars(bufferbuilder);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.starVBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.starGLCallList = GLAllocation.generateDisplayLists(1);
         GlStateManager.pushMatrix();
         GlStateManager.glNewList(this.starGLCallList, 4864);
         this.renderStars(bufferbuilder);
         tessellator.draw();
         GlStateManager.glEndList();
         GlStateManager.popMatrix();
      }

   }

   private void renderStars(BufferBuilder bufferBuilderIn) {
      Random random = new Random(10842L);
      bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);

      for(int i = 0; i < 1500; ++i) {
         double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
         double d4 = d0 * d0 + d1 * d1 + d2 * d2;
         if (d4 < 1.0 && d4 > 0.01) {
            d4 = 1.0 / Math.sqrt(d4);
            d0 *= d4;
            d1 *= d4;
            d2 *= d4;
            double d5 = d0 * 100.0;
            double d6 = d1 * 100.0;
            double d7 = d2 * 100.0;
            double d8 = Math.atan2(d0, d2);
            double d9 = Math.sin(d8);
            double d10 = Math.cos(d8);
            double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
            double d12 = Math.sin(d11);
            double d13 = Math.cos(d11);
            double d14 = random.nextDouble() * Math.PI * 2.0;
            double d15 = Math.sin(d14);
            double d16 = Math.cos(d14);

            for(int j = 0; j < 4; ++j) {
               double d17 = 0.0;
               double d18 = (double)((j & 2) - 1) * d3;
               double d19 = (double)((j + 1 & 2) - 1) * d3;
               double d20 = 0.0;
               double d21 = d18 * d16 - d19 * d15;
               double d22 = d19 * d16 + d18 * d15;
               double d23 = d21 * d12 + 0.0 * d13;
               double d24 = 0.0 * d12 - d21 * d13;
               double d25 = d24 * d9 - d22 * d10;
               double d26 = d22 * d9 + d24 * d10;
               bufferBuilderIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
            }
         }
      }

   }

   public void setWorldAndLoadRenderers(@Nullable WorldClient worldClientIn) {
      if (this.world != null) {
         this.world.removeEventListener(this);
      }

      this.frustumUpdatePosX = Double.MIN_VALUE;
      this.frustumUpdatePosY = Double.MIN_VALUE;
      this.frustumUpdatePosZ = Double.MIN_VALUE;
      this.frustumUpdatePosChunkX = Integer.MIN_VALUE;
      this.frustumUpdatePosChunkY = Integer.MIN_VALUE;
      this.frustumUpdatePosChunkZ = Integer.MIN_VALUE;
      this.renderManager.setWorld(worldClientIn);
      this.world = worldClientIn;
      if (Config.isDynamicLights()) {
         DynamicLights.clear();
      }

      ChunkVisibility.reset();
      this.worldChunkProvider = null;
      this.worldChunkProviderMap = null;
      this.renderEnv.reset((IBlockState)null, (BlockPos)null);
      Shaders.checkWorldChanged(this.world);
      if (worldClientIn != null) {
         worldClientIn.addEventListener(this);
         this.loadRenderers();
      } else {
         this.chunksToUpdate.clear();
         this.chunksToUpdatePrev.clear();
         this.clearRenderInfos();
         if (this.viewFrustum != null) {
            this.viewFrustum.deleteGlResources();
            this.viewFrustum = null;
         }

         if (this.renderDispatcher != null) {
            this.renderDispatcher.stopWorkerThreads();
         }

         this.renderDispatcher = null;
      }

   }

   public void loadRenderers() {
      if (this.world != null) {
         if (this.renderDispatcher == null) {
            this.renderDispatcher = new ChunkRenderDispatcher();
         }

         this.displayListEntitiesDirty = true;
         Blocks.LEAVES.setGraphicsLevel(Config.isTreesFancy());
         Blocks.LEAVES2.setGraphicsLevel(Config.isTreesFancy());
         BlockModelRenderer.updateAoLightValue();
         if (Config.isDynamicLights()) {
            DynamicLights.clear();
         }

         SmartAnimations.update();
         Minecraft var10001 = this.mc;
         this.renderDistanceChunks = Minecraft.gameSettings.renderDistanceChunks;
         this.renderDistance = this.renderDistanceChunks * 16;
         this.renderDistanceSq = this.renderDistance * this.renderDistance;
         boolean flag = this.vboEnabled;
         this.vboEnabled = OpenGlHelper.useVbo();
         if (flag && !this.vboEnabled) {
            this.renderContainer = new RenderList();
            this.renderChunkFactory = new ListChunkFactory();
         } else if (!flag && this.vboEnabled) {
            this.renderContainer = new VboRenderList();
            this.renderChunkFactory = new VboChunkFactory();
         }

         this.generateStars();
         this.generateSky();
         this.generateSky2();
         if (this.viewFrustum != null) {
            this.viewFrustum.deleteGlResources();
         }

         this.stopChunkUpdates();
         synchronized(this.setTileEntities) {
            this.setTileEntities.clear();
         }

         Minecraft var10004 = this.mc;
         this.viewFrustum = new ViewFrustum(this.world, Minecraft.gameSettings.renderDistanceChunks, this, this.renderChunkFactory);
         if (this.world != null) {
            Entity entity = this.mc.getRenderViewEntity();
            if (entity != null) {
               this.viewFrustum.updateChunkPositions(entity.posX, entity.posZ);
            }
         }

         this.renderEntitiesStartupCounter = 2;
      }

      Minecraft var10000 = this.mc;
      if (Minecraft.player == null) {
         this.firstWorldLoad = true;
      }

   }

   protected void stopChunkUpdates() {
      this.chunksToUpdate.clear();
      this.renderDispatcher.stopChunkUpdates();
   }

   public void createBindEntityOutlineFbs(int width, int height) {
      if (OpenGlHelper.shadersSupported && this.entityOutlineShader != null) {
         this.entityOutlineShader.createBindFramebuffers(width, height);
      }

   }

   public void renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks) {
      int i = 0;
      if (Reflector.MinecraftForgeClient_getRenderPass.exists()) {
         i = Reflector.callInt(Reflector.MinecraftForgeClient_getRenderPass);
      }

      if (this.renderEntitiesStartupCounter > 0) {
         if (i <= 0) {
            --this.renderEntitiesStartupCounter;
         }
      } else {
         double d0 = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)partialTicks;
         double d1 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)partialTicks;
         double d2 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)partialTicks;
         this.world.profiler.startSection("prepare");
         TileEntityRendererDispatcher.instance.prepare(this.world, this.mc.getTextureManager(), this.mc.fontRenderer, this.mc.getRenderViewEntity(), this.mc.objectMouseOver, partialTicks);
         RenderManager var10000 = this.renderManager;
         WorldClient var10001 = this.world;
         FontRenderer var10002 = this.mc.fontRenderer;
         Entity var10003 = this.mc.getRenderViewEntity();
         Minecraft var10005 = this.mc;
         var10000.cacheActiveRenderInfo(var10001, var10002, var10003, this.mc.pointedEntity, Minecraft.gameSettings, partialTicks);
         ++renderEntitiesCounter;
         if (i == 0) {
            this.countEntitiesTotal = 0;
            this.countEntitiesRendered = 0;
            this.countEntitiesHidden = 0;
            this.countTileEntitiesRendered = 0;
         }

         Entity entity = this.mc.getRenderViewEntity();
         double d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
         double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
         double d5 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
         TileEntityRendererDispatcher.staticPlayerX = d3;
         TileEntityRendererDispatcher.staticPlayerY = d4;
         TileEntityRendererDispatcher.staticPlayerZ = d5;
         this.renderManager.setRenderPosition(d3, d4, d5);
         this.mc.entityRenderer.enableLightmap();
         this.world.profiler.endStartSection("global");
         List<Entity> list = this.world.getLoadedEntityList();
         if (i == 0) {
            this.countEntitiesTotal = list.size();
         }

         if (Config.isFogOff() && this.mc.entityRenderer.fogStandard) {
            GlStateManager.disableFog();
         }

         boolean flag = Reflector.ForgeEntity_shouldRenderInPass.exists();
         boolean flag1 = Reflector.ForgeTileEntity_shouldRenderInPass.exists();

         for(int j = 0; j < this.world.weatherEffects.size(); ++j) {
            Entity entity1 = (Entity)this.world.weatherEffects.get(j);
            if (!flag || Reflector.callBoolean(entity1, Reflector.ForgeEntity_shouldRenderInPass, i)) {
               ++this.countEntitiesRendered;
               if (entity1.isInRangeToRender3d(d0, d1, d2)) {
                  this.renderManager.renderEntityStatic(entity1, partialTicks, false);
               }
            }
         }

         this.world.profiler.endStartSection("entities");
         boolean flag5 = Config.isShaders();
         if (flag5) {
            Shaders.beginEntities();
         }

         ArrayList list1;
         BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos;
         Minecraft var36;
         boolean var37;
         ArrayList list2;
         label366: {
            RenderItemFrame.updateItemRenderDistance();
            list2 = Lists.newArrayList();
            list1 = Lists.newArrayList();
            blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
            if (Shaders.isShadowPass) {
               var36 = this.mc;
               if (!Minecraft.player.isSpectator()) {
                  var37 = true;
                  break label366;
               }
            }

            var37 = false;
         }

         boolean flag2 = var37;
         Iterator var26 = this.renderInfosEntities.iterator();

         label360:
         while(true) {
            ClassInheritanceMultiMap classinheritancemultimap;
            do {
               ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1;
               if (!var26.hasNext()) {
                  blockpos$pooledmutableblockpos.release();
                  Entity entity5;
                  if (!list1.isEmpty()) {
                     var26 = list1.iterator();

                     label311:
                     while(true) {
                        do {
                           if (!var26.hasNext()) {
                              break label311;
                           }

                           entity5 = (Entity)var26.next();
                        } while(flag && !Reflector.callBoolean(entity5, Reflector.ForgeEntity_shouldRenderInPass, i));

                        if (flag5) {
                           Shaders.nextEntity(entity5);
                        }

                        this.renderManager.renderMultipass(entity5, partialTicks);
                     }
                  }

                  int l;
                  if (i == 0 && this.isRenderEntityOutlines() && (!list2.isEmpty() || this.entityOutlinesRendered)) {
                     this.world.profiler.endStartSection("entityOutlines");
                     this.entityOutlineFramebuffer.framebufferClear();
                     this.entityOutlinesRendered = !list2.isEmpty();
                     if (!list2.isEmpty()) {
                        GlStateManager.depthFunc(519);
                        GlStateManager.disableFog();
                        this.entityOutlineFramebuffer.bindFramebuffer(false);
                        RenderHelper.disableStandardItemLighting();
                        this.renderManager.setRenderOutlines(true);

                        for(l = 0; l < list2.size(); ++l) {
                           entity5 = (Entity)list2.get(l);
                           if (!flag || Reflector.callBoolean(entity5, Reflector.ForgeEntity_shouldRenderInPass, i)) {
                              if (flag5) {
                                 Shaders.nextEntity(entity5);
                              }

                              this.renderManager.renderEntityStatic(entity5, partialTicks, false);
                           }
                        }

                        this.renderManager.setRenderOutlines(false);
                        RenderHelper.enableStandardItemLighting();
                        GlStateManager.depthMask(false);
                        this.entityOutlineShader.render(partialTicks);
                        GlStateManager.enableLighting();
                        GlStateManager.depthMask(true);
                        GlStateManager.enableFog();
                        GlStateManager.enableBlend();
                        GlStateManager.enableColorMaterial();
                        GlStateManager.depthFunc(515);
                        GlStateManager.enableDepth();
                        GlStateManager.enableAlpha();
                     }

                     this.mc.getFramebuffer().bindFramebuffer(false);
                  }

                  if (!this.isRenderEntityOutlines() && (!list2.isEmpty() || this.entityOutlinesRendered)) {
                     this.world.profiler.endStartSection("entityOutlines");
                     this.entityOutlinesRendered = !list2.isEmpty();
                     if (!list2.isEmpty()) {
                        if (flag5) {
                           Shaders.beginEntitiesGlowing();
                        }

                        GlStateManager.disableFog();
                        GlStateManager.disableDepth();
                        this.mc.entityRenderer.disableLightmap();
                        RenderHelper.disableStandardItemLighting();
                        this.renderManager.setRenderOutlines(true);
                        l = 0;

                        while(true) {
                           if (l >= list2.size()) {
                              this.renderManager.setRenderOutlines(false);
                              RenderHelper.enableStandardItemLighting();
                              this.mc.entityRenderer.enableLightmap();
                              GlStateManager.enableDepth();
                              GlStateManager.enableFog();
                              if (flag5) {
                                 Shaders.endEntitiesGlowing();
                              }
                              break;
                           }

                           entity5 = (Entity)list2.get(l);
                           if (!flag || Reflector.callBoolean(entity5, Reflector.ForgeEntity_shouldRenderInPass, i)) {
                              if (flag5) {
                                 Shaders.nextEntity(entity5);
                              }

                              this.renderManager.renderEntityStatic(entity5, partialTicks, false);
                           }

                           ++l;
                        }
                     }
                  }

                  if (flag5) {
                     Shaders.endEntities();
                     Shaders.beginBlockEntities();
                  }

                  this.world.profiler.endStartSection("blockentities");
                  RenderHelper.enableStandardItemLighting();
                  if (Reflector.ForgeTileEntity_hasFastRenderer.exists()) {
                     TileEntityRendererDispatcher.instance.preDrawBatch();
                  }

                  TileEntitySignRenderer.updateTextRenderDistance();
                  var26 = this.renderInfosTileEntities.iterator();

                  label255:
                  while(true) {
                     List list3;
                     do {
                        if (!var26.hasNext()) {
                           synchronized(this.setTileEntities) {
                              Iterator var44 = this.setTileEntities.iterator();

                              label227:
                              while(true) {
                                 TileEntity tileentity;
                                 do {
                                    if (!var44.hasNext()) {
                                       break label227;
                                    }

                                    tileentity = (TileEntity)var44.next();
                                 } while(flag1 && !Reflector.callBoolean(tileentity, Reflector.ForgeTileEntity_shouldRenderInPass, i));

                                 if (flag5) {
                                    Shaders.nextBlockEntity(tileentity);
                                 }

                                 TileEntityRendererDispatcher.instance.render(tileentity, partialTicks, -1);
                              }
                           }

                           if (Reflector.ForgeTileEntity_hasFastRenderer.exists()) {
                              TileEntityRendererDispatcher.instance.drawBatch(i);
                           }

                           this.renderOverlayDamaged = true;
                           this.preRenderDamagedBlocks();
                           var26 = this.damagedBlocks.values().iterator();

                           while(var26.hasNext()) {
                              DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)var26.next();
                              BlockPos blockpos = destroyblockprogress.getPosition();
                              if (this.world.getBlockState(blockpos).getBlock().hasTileEntity()) {
                                 TileEntity tileentity2 = this.world.getTileEntity(blockpos);
                                 if (tileentity2 instanceof TileEntityChest) {
                                    TileEntityChest tileentitychest = (TileEntityChest)tileentity2;
                                    if (tileentitychest.adjacentChestXNeg != null) {
                                       blockpos = blockpos.offset(EnumFacing.WEST);
                                       tileentity2 = this.world.getTileEntity(blockpos);
                                    } else if (tileentitychest.adjacentChestZNeg != null) {
                                       blockpos = blockpos.offset(EnumFacing.NORTH);
                                       tileentity2 = this.world.getTileEntity(blockpos);
                                    }
                                 }

                                 IBlockState iblockstate = this.world.getBlockState(blockpos);
                                 if (tileentity2 != null && iblockstate.hasCustomBreakingProgress()) {
                                    if (flag5) {
                                       Shaders.nextBlockEntity(tileentity2);
                                    }

                                    TileEntityRendererDispatcher.instance.render(tileentity2, partialTicks, destroyblockprogress.getPartialBlockDamage());
                                 }
                              }
                           }

                           this.postRenderDamagedBlocks();
                           this.renderOverlayDamaged = false;
                           if (flag5) {
                              Shaders.endBlockEntities();
                           }

                           --renderEntitiesCounter;
                           this.mc.entityRenderer.disableLightmap();
                           this.mc.profiler.endSection();
                           return;
                        }

                        renderglobal$containerlocalrenderinformation1 = (ContainerLocalRenderInformation)var26.next();
                        list3 = renderglobal$containerlocalrenderinformation1.renderChunk.getCompiledChunk().getTileEntities();
                     } while(list3.isEmpty());

                     Iterator iterator = list3.iterator();

                     while(true) {
                        TileEntity tileentity1;
                        while(true) {
                           if (!iterator.hasNext()) {
                              continue label255;
                           }

                           tileentity1 = (TileEntity)iterator.next();
                           if (!flag1) {
                              break;
                           }

                           if (Reflector.callBoolean(tileentity1, Reflector.ForgeTileEntity_shouldRenderInPass, i)) {
                              AxisAlignedBB axisalignedbb = (AxisAlignedBB)Reflector.call(tileentity1, Reflector.ForgeTileEntity_getRenderBoundingBox);
                              if (axisalignedbb == null || camera.isBoundingBoxInFrustum(axisalignedbb)) {
                                 break;
                              }
                           }
                        }

                        if (flag5) {
                           Shaders.nextBlockEntity(tileentity1);
                        }

                        TileEntityRendererDispatcher.instance.render(tileentity1, partialTicks, -1);
                        ++this.countTileEntitiesRendered;
                     }
                  }
               }

               renderglobal$containerlocalrenderinformation1 = (ContainerLocalRenderInformation)var26.next();
               Chunk chunk = renderglobal$containerlocalrenderinformation1.renderChunk.getChunk();
               classinheritancemultimap = chunk.getEntityLists()[renderglobal$containerlocalrenderinformation1.renderChunk.getPosition().getY() / 16];
            } while(classinheritancemultimap.isEmpty());

            Iterator var30 = classinheritancemultimap.iterator();

            while(true) {
               Entity entity2;
               boolean flag4;
               do {
                  do {
                     boolean flag3;
                     do {
                        do {
                           if (!var30.hasNext()) {
                              continue label360;
                           }

                           entity2 = (Entity)var30.next();
                        } while(flag && !Reflector.callBoolean(entity2, Reflector.ForgeEntity_shouldRenderInPass, i));

                        label332: {
                           if (!this.renderManager.shouldRender(entity2, camera, d0, d1, d2)) {
                              Minecraft var38 = this.mc;
                              if (!entity2.isRidingOrBeingRiddenBy(Minecraft.player)) {
                                 var37 = false;
                                 break label332;
                              }
                           }

                           var37 = true;
                        }

                        flag3 = var37;
                     } while(!flag3);

                     flag4 = this.mc.getRenderViewEntity() instanceof EntityLivingBase ? ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping() : false;
                     if (entity2 != this.mc.getRenderViewEntity() || flag2) {
                        break;
                     }

                     var36 = this.mc;
                  } while(Minecraft.gameSettings.thirdPersonView == 0 && !flag4);
               } while(!(entity2.posY < 0.0) && !(entity2.posY >= 256.0) && !this.world.isBlockLoaded(blockpos$pooledmutableblockpos.setPos(entity2)));

               ++this.countEntitiesRendered;
               this.renderedEntity = entity2;
               if (flag5) {
                  Shaders.nextEntity(entity2);
               }

               this.renderManager.renderEntityStatic(entity2, partialTicks, false);
               this.renderedEntity = null;
               if (this.isOutlineActive(entity2, entity, camera)) {
                  list2.add(entity2);
               }

               if (this.renderManager.isRenderMultipass(entity2)) {
                  list1.add(entity2);
               }
            }
         }
      }
   }

   private boolean isOutlineActive(Entity entityIn, Entity viewer, ICamera camera) {
      boolean flag = viewer instanceof EntityLivingBase && ((EntityLivingBase)viewer).isPlayerSleeping();
      Minecraft var10000;
      if (entityIn == viewer) {
         var10000 = this.mc;
         if (Minecraft.gameSettings.thirdPersonView == 0 && !flag) {
            return false;
         }
      }

      if (entityIn.isGlowing()) {
         return true;
      } else {
         var10000 = this.mc;
         if (Minecraft.player.isSpectator()) {
            var10000 = this.mc;
            if (Minecraft.gameSettings.keyBindSpectatorOutlines.isKeyDown() && entityIn instanceof EntityPlayer) {
               boolean var5;
               if (!entityIn.ignoreFrustumCheck && !camera.isBoundingBoxInFrustum(entityIn.getEntityBoundingBox())) {
                  Minecraft var10001 = this.mc;
                  if (!entityIn.isRidingOrBeingRiddenBy(Minecraft.player)) {
                     var5 = false;
                     return var5;
                  }
               }

               var5 = true;
               return var5;
            }
         }

         return false;
      }
   }

   public String getDebugInfoRenders() {
      int i = this.viewFrustum.renderChunks.length;
      int j = this.getRenderedChunks();
      return String.format("C: %d/%d %sD: %d, L: %d, %s", j, i, this.mc.renderChunksMany ? "(s) " : "", this.renderDistanceChunks, this.setLightUpdates.size(), this.renderDispatcher == null ? "null" : this.renderDispatcher.getDebugInfo());
   }

   protected int getRenderedChunks() {
      int i = 0;
      Iterator var2 = this.renderInfos.iterator();

      while(var2.hasNext()) {
         ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = (ContainerLocalRenderInformation)var2.next();
         CompiledChunk compiledchunk = renderglobal$containerlocalrenderinformation.renderChunk.compiledChunk;
         if (compiledchunk != CompiledChunk.DUMMY && !compiledchunk.isEmpty()) {
            ++i;
         }
      }

      return i;
   }

   public String getDebugInfoEntities() {
      return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ", B: " + this.countEntitiesHidden + ", " + Config.getVersionDebug();
   }

   public void setupTerrain(Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator) {
      Minecraft var10000 = this.mc;
      if (Minecraft.gameSettings.renderDistanceChunks != this.renderDistanceChunks) {
         this.loadRenderers();
      }

      this.world.profiler.startSection("camera");
      double d0 = viewEntity.posX - this.frustumUpdatePosX;
      double d1 = viewEntity.posY - this.frustumUpdatePosY;
      double d2 = viewEntity.posZ - this.frustumUpdatePosZ;
      if (this.frustumUpdatePosChunkX != viewEntity.chunkCoordX || this.frustumUpdatePosChunkY != viewEntity.chunkCoordY || this.frustumUpdatePosChunkZ != viewEntity.chunkCoordZ || d0 * d0 + d1 * d1 + d2 * d2 > 16.0) {
         this.frustumUpdatePosX = viewEntity.posX;
         this.frustumUpdatePosY = viewEntity.posY;
         this.frustumUpdatePosZ = viewEntity.posZ;
         this.frustumUpdatePosChunkX = viewEntity.chunkCoordX;
         this.frustumUpdatePosChunkY = viewEntity.chunkCoordY;
         this.frustumUpdatePosChunkZ = viewEntity.chunkCoordZ;
         this.viewFrustum.updateChunkPositions(viewEntity.posX, viewEntity.posZ);
      }

      if (Config.isDynamicLights()) {
         DynamicLights.update(this);
      }

      this.world.profiler.endStartSection("renderlistcamera");
      double d3 = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
      double d4 = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
      double d5 = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
      this.renderContainer.initialize(d3, d4, d5);
      this.world.profiler.endStartSection("cull");
      if (this.debugFixedClippingHelper != null) {
         Frustum frustum = new Frustum(this.debugFixedClippingHelper);
         frustum.setPosition(this.debugTerrainFrustumPosition.x, this.debugTerrainFrustumPosition.y, this.debugTerrainFrustumPosition.z);
         camera = frustum;
      }

      this.mc.profiler.endStartSection("culling");
      BlockPos blockpos = new BlockPos(d3, d4 + (double)viewEntity.getEyeHeight(), d5);
      RenderChunk renderchunk = this.viewFrustum.getRenderChunk(blockpos);
      new BlockPos(MathHelper.floor(d3 / 16.0) * 16, MathHelper.floor(d4 / 16.0) * 16, MathHelper.floor(d5 / 16.0) * 16);
      this.displayListEntitiesDirty = this.displayListEntitiesDirty || !this.chunksToUpdate.isEmpty() || viewEntity.posX != this.lastViewEntityX || viewEntity.posY != this.lastViewEntityY || viewEntity.posZ != this.lastViewEntityZ || (double)viewEntity.rotationPitch != this.lastViewEntityPitch || (double)viewEntity.rotationYaw != this.lastViewEntityYaw;
      this.lastViewEntityX = viewEntity.posX;
      this.lastViewEntityY = viewEntity.posY;
      this.lastViewEntityZ = viewEntity.posZ;
      this.lastViewEntityPitch = (double)viewEntity.rotationPitch;
      this.lastViewEntityYaw = (double)viewEntity.rotationYaw;
      boolean flag = this.debugFixedClippingHelper != null;
      this.mc.profiler.endStartSection("update");
      Lagometer.timerVisibility.start();
      int i = this.getCountLoadedChunks();
      if (i != this.countLoadedChunksPrev) {
         this.countLoadedChunksPrev = i;
         this.displayListEntitiesDirty = true;
      }

      int j = 256;
      if (!ChunkVisibility.isFinished()) {
         this.displayListEntitiesDirty = true;
      }

      if (!flag && this.displayListEntitiesDirty && Config.isIntegratedServerRunning()) {
         j = ChunkVisibility.getMaxChunkY(this.world, viewEntity, this.renderDistanceChunks);
      }

      RenderChunk renderchunk1 = this.viewFrustum.getRenderChunk(new BlockPos(viewEntity.posX, viewEntity.posY, viewEntity.posZ));
      ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation;
      if (Shaders.isShadowPass) {
         this.renderInfos = this.renderInfosShadow;
         this.renderInfosEntities = this.renderInfosEntitiesShadow;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
         if (!flag && this.displayListEntitiesDirty) {
            this.clearRenderInfos();
            if (renderchunk1 != null && renderchunk1.getPosition().getY() > j) {
               this.renderInfosEntities.add(renderchunk1.getRenderInfo());
            }

            Iterator<RenderChunk> iterator = ShadowUtils.makeShadowChunkIterator(this.world, partialTicks, viewEntity, this.renderDistanceChunks, this.viewFrustum);

            while(iterator.hasNext()) {
               RenderChunk renderchunk2 = (RenderChunk)iterator.next();
               if (renderchunk2 != null && renderchunk2.getPosition().getY() <= j) {
                  renderglobal$containerlocalrenderinformation = renderchunk2.getRenderInfo();
                  if (!renderchunk2.compiledChunk.isEmpty()) {
                     this.renderInfos.add(renderglobal$containerlocalrenderinformation);
                  }

                  if (ChunkUtils.hasEntities(renderchunk2.getChunk())) {
                     this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation);
                  }

                  if (renderchunk2.getCompiledChunk().getTileEntities().size() > 0) {
                     this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation);
                  }
               }
            }
         }
      } else {
         this.renderInfos = this.renderInfosNormal;
         this.renderInfosEntities = this.renderInfosEntitiesNormal;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
      }

      if (!flag && this.displayListEntitiesDirty && !Shaders.isShadowPass) {
         this.displayListEntitiesDirty = false;
         this.clearRenderInfos();
         this.visibilityDeque.clear();
         Deque deque = this.visibilityDeque;
         var10000 = this.mc;
         Entity.setRenderDistanceWeight(MathHelper.clamp((double)Minecraft.gameSettings.renderDistanceChunks / 8.0, 1.0, 2.5));
         boolean flag1 = this.mc.renderChunksMany;
         boolean flag3;
         ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation5;
         if (renderchunk != null && renderchunk.getPosition().getY() <= j) {
            flag3 = false;
            renderglobal$containerlocalrenderinformation5 = new ContainerLocalRenderInformation(renderchunk, (EnumFacing)null, 0);
            Set set1 = SET_ALL_FACINGS;
            if (set1.size() == 1) {
               Vector3f vector3f = this.getViewVector(viewEntity, partialTicks);
               EnumFacing enumfacing2 = EnumFacing.getFacingFromVector(vector3f.x, vector3f.y, vector3f.z).getOpposite();
               set1.remove(enumfacing2);
            }

            if (set1.isEmpty()) {
               flag3 = true;
            }

            if (flag3 && !playerSpectator) {
               this.renderInfos.add(renderglobal$containerlocalrenderinformation5);
            } else {
               if (playerSpectator && this.world.getBlockState(blockpos).isOpaqueCube()) {
                  flag1 = false;
               }

               renderchunk.setFrameIndex(frameCount);
               deque.add(renderglobal$containerlocalrenderinformation5);
            }
         } else {
            int j1 = blockpos.getY() > 0 ? Math.min(j, 248) : 8;
            if (renderchunk1 != null) {
               this.renderInfosEntities.add(renderchunk1.getRenderInfo());
            }

            for(int k = -this.renderDistanceChunks; k <= this.renderDistanceChunks; ++k) {
               for(int l = -this.renderDistanceChunks; l <= this.renderDistanceChunks; ++l) {
                  RenderChunk renderchunk3 = this.viewFrustum.getRenderChunk(new BlockPos((k << 4) + 8, j1, (l << 4) + 8));
                  if (renderchunk3 != null && renderchunk3.isBoundingBoxInFrustum((ICamera)camera, frameCount)) {
                     renderchunk3.setFrameIndex(frameCount);
                     ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1 = renderchunk3.getRenderInfo();
                     renderglobal$containerlocalrenderinformation1.initialize((EnumFacing)null, 0);
                     deque.add(renderglobal$containerlocalrenderinformation1);
                  }
               }
            }
         }

         this.mc.profiler.startSection("iteration");
         flag3 = Config.isFogOn();

         while(!deque.isEmpty()) {
            renderglobal$containerlocalrenderinformation5 = (ContainerLocalRenderInformation)deque.poll();
            RenderChunk renderchunk6 = renderglobal$containerlocalrenderinformation5.renderChunk;
            EnumFacing enumfacing1 = renderglobal$containerlocalrenderinformation5.facing;
            CompiledChunk compiledchunk = renderchunk6.compiledChunk;
            if (!compiledchunk.isEmpty() || renderchunk6.needsUpdate()) {
               this.renderInfos.add(renderglobal$containerlocalrenderinformation5);
            }

            if (ChunkUtils.hasEntities(renderchunk6.getChunk())) {
               this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation5);
            }

            if (compiledchunk.getTileEntities().size() > 0) {
               this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation5);
            }

            EnumFacing[] var32 = flag1 ? ChunkVisibility.getFacingsNotOpposite(renderglobal$containerlocalrenderinformation5.setFacing) : EnumFacing.VALUES;
            int var33 = var32.length;

            for(int var34 = 0; var34 < var33; ++var34) {
               EnumFacing enumfacing = var32[var34];
               if (!flag1 || enumfacing1 == null || compiledchunk.isVisible(enumfacing1.getOpposite(), enumfacing)) {
                  RenderChunk renderchunk4 = this.getRenderChunkOffset(blockpos, renderchunk6, enumfacing, flag3, j);
                  if (renderchunk4 != null && renderchunk4.setFrameIndex(frameCount) && renderchunk4.isBoundingBoxInFrustum((ICamera)camera, frameCount)) {
                     int i1 = renderglobal$containerlocalrenderinformation5.setFacing | 1 << enumfacing.ordinal();
                     ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation2 = renderchunk4.getRenderInfo();
                     renderglobal$containerlocalrenderinformation2.initialize(enumfacing, i1);
                     deque.add(renderglobal$containerlocalrenderinformation2);
                  }
               }
            }
         }

         this.mc.profiler.endSection();
      }

      this.mc.profiler.endStartSection("captureFrustum");
      if (this.debugFixTerrainFrustum) {
         this.fixTerrainFrustum(d3, d4, d5);
         this.debugFixTerrainFrustum = false;
      }

      Lagometer.timerVisibility.end();
      if (Shaders.isShadowPass) {
         Shaders.mcProfilerEndSection();
      } else {
         this.mc.profiler.endStartSection("rebuildNear");
         Set<RenderChunk> set = this.chunksToUpdate;
         this.chunksToUpdate = this.chunksToUpdatePrev;
         this.chunksToUpdatePrev = set;
         this.chunksToUpdate.clear();
         Lagometer.timerChunkUpdate.start();
         Iterator var44 = this.renderInfos.iterator();

         while(true) {
            RenderChunk renderchunk5;
            do {
               if (!var44.hasNext()) {
                  Lagometer.timerChunkUpdate.end();
                  this.chunksToUpdate.addAll(set);
                  this.mc.profiler.endSection();
                  return;
               }

               renderglobal$containerlocalrenderinformation = (ContainerLocalRenderInformation)var44.next();
               renderchunk5 = renderglobal$containerlocalrenderinformation.renderChunk;
            } while(!renderchunk5.needsUpdate() && !set.contains(renderchunk5));

            this.displayListEntitiesDirty = true;
            BlockPos blockpos1 = renderchunk5.getPosition();
            boolean flag4 = blockpos.distanceSq((double)(blockpos1.getX() + 8), (double)(blockpos1.getY() + 8), (double)(blockpos1.getZ() + 8)) < 768.0;
            if (!flag4) {
               this.chunksToUpdate.add(renderchunk5);
            } else if (!renderchunk5.isPlayerUpdate()) {
               this.chunksToUpdateForced.add(renderchunk5);
            } else {
               this.mc.profiler.startSection("build near");
               this.renderDispatcher.updateChunkNow(renderchunk5);
               renderchunk5.clearNeedsUpdate();
               this.mc.profiler.endSection();
            }
         }
      }
   }

   private Set<EnumFacing> getVisibleFacings(BlockPos pos) {
      VisGraph visgraph = new VisGraph();
      BlockPos blockpos = new BlockPos(pos.getX() >> 4 << 4, pos.getY() >> 4 << 4, pos.getZ() >> 4 << 4);
      Chunk chunk = this.world.getChunk(blockpos);
      Iterator var5 = BlockPos.getAllInBoxMutable(blockpos, blockpos.add(15, 15, 15)).iterator();

      while(var5.hasNext()) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var5.next();
         if (chunk.getBlockState(blockpos$mutableblockpos).isOpaqueCube()) {
            visgraph.setOpaqueCube(blockpos$mutableblockpos);
         }
      }

      return visgraph.getVisibleFacings(pos);
   }

   @Nullable
   private RenderChunk getRenderChunkOffset(BlockPos p_getRenderChunkOffset_1_, RenderChunk p_getRenderChunkOffset_2_, EnumFacing p_getRenderChunkOffset_3_, boolean p_getRenderChunkOffset_4_, int p_getRenderChunkOffset_5_) {
      RenderChunk renderchunk = p_getRenderChunkOffset_2_.getRenderChunkNeighbour(p_getRenderChunkOffset_3_);
      if (renderchunk == null) {
         return null;
      } else if (renderchunk.getPosition().getY() > p_getRenderChunkOffset_5_) {
         return null;
      } else {
         if (p_getRenderChunkOffset_4_) {
            BlockPos blockpos = renderchunk.getPosition();
            int i = p_getRenderChunkOffset_1_.getX() - blockpos.getX();
            int j = p_getRenderChunkOffset_1_.getZ() - blockpos.getZ();
            int k = i * i + j * j;
            if (k > this.renderDistanceSq) {
               return null;
            }
         }

         return renderchunk;
      }
   }

   private void fixTerrainFrustum(double x, double y, double z) {
      this.debugFixedClippingHelper = new ClippingHelperImpl();
      ((ClippingHelperImpl)this.debugFixedClippingHelper).init();
      Matrix4f matrix4f = new Matrix4f(this.debugFixedClippingHelper.modelviewMatrix);
      matrix4f.transpose();
      Matrix4f matrix4f1 = new Matrix4f(this.debugFixedClippingHelper.projectionMatrix);
      matrix4f1.transpose();
      Matrix4f matrix4f2 = new Matrix4f();
      Matrix4f.mul(matrix4f1, matrix4f, matrix4f2);
      matrix4f2.invert();
      this.debugTerrainFrustumPosition.x = x;
      this.debugTerrainFrustumPosition.y = y;
      this.debugTerrainFrustumPosition.z = z;
      this.debugTerrainMatrix[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);

      for(int i = 0; i < 8; ++i) {
         Matrix4f.transform(matrix4f2, this.debugTerrainMatrix[i], this.debugTerrainMatrix[i]);
         Vector4f var10000 = this.debugTerrainMatrix[i];
         var10000.x /= this.debugTerrainMatrix[i].w;
         var10000 = this.debugTerrainMatrix[i];
         var10000.y /= this.debugTerrainMatrix[i].w;
         var10000 = this.debugTerrainMatrix[i];
         var10000.z /= this.debugTerrainMatrix[i].w;
         this.debugTerrainMatrix[i].w = 1.0F;
      }

   }

   protected Vector3f getViewVector(Entity entityIn, double partialTicks) {
      float f = (float)((double)entityIn.prevRotationPitch + (double)(entityIn.rotationPitch - entityIn.prevRotationPitch) * partialTicks);
      float f1 = (float)((double)entityIn.prevRotationYaw + (double)(entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks);
      Minecraft.getMinecraft();
      if (Minecraft.gameSettings.thirdPersonView == 2) {
         f += 180.0F;
      }

      float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
      float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
      float f4 = -MathHelper.cos(-f * 0.017453292F);
      float f5 = MathHelper.sin(-f * 0.017453292F);
      return new Vector3f(f3 * f4, f5, f2 * f4);
   }

   public int renderBlockLayer(BlockRenderLayer blockLayerIn, double partialTicks, int pass, Entity entityIn) {
      RenderHelper.disableStandardItemLighting();
      if (blockLayerIn == BlockRenderLayer.TRANSLUCENT && !Shaders.isShadowPass) {
         this.mc.profiler.startSection("translucent_sort");
         double d0 = entityIn.posX - this.prevRenderSortX;
         double d1 = entityIn.posY - this.prevRenderSortY;
         double d2 = entityIn.posZ - this.prevRenderSortZ;
         if (d0 * d0 + d1 * d1 + d2 * d2 > 1.0) {
            this.prevRenderSortX = entityIn.posX;
            this.prevRenderSortY = entityIn.posY;
            this.prevRenderSortZ = entityIn.posZ;
            int k = 0;
            this.chunksToResortTransparency.clear();
            Iterator var13 = this.renderInfos.iterator();

            while(var13.hasNext()) {
               ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = (ContainerLocalRenderInformation)var13.next();
               if (renderglobal$containerlocalrenderinformation.renderChunk.compiledChunk.isLayerStarted(blockLayerIn) && k++ < 15) {
                  this.chunksToResortTransparency.add(renderglobal$containerlocalrenderinformation.renderChunk);
               }
            }
         }

         this.mc.profiler.endSection();
      }

      this.mc.profiler.startSection("filterempty");
      int l = 0;
      boolean flag = blockLayerIn == BlockRenderLayer.TRANSLUCENT;
      int i1 = flag ? this.renderInfos.size() - 1 : 0;
      int i = flag ? -1 : this.renderInfos.size();
      int j1 = flag ? -1 : 1;

      for(int j = i1; j != i; j += j1) {
         RenderChunk renderchunk = ((ContainerLocalRenderInformation)this.renderInfos.get(j)).renderChunk;
         if (!renderchunk.getCompiledChunk().isLayerEmpty(blockLayerIn)) {
            ++l;
            this.renderContainer.addRenderChunk(renderchunk, blockLayerIn);
         }
      }

      if (l == 0) {
         this.mc.profiler.endSection();
         return l;
      } else {
         if (Config.isFogOff() && this.mc.entityRenderer.fogStandard) {
            GlStateManager.disableFog();
         }

         this.mc.profiler.func_194339_b(() -> {
            return "render_" + blockLayerIn;
         });
         this.renderBlockLayer(blockLayerIn);
         this.mc.profiler.endSection();
         return l;
      }
   }

   private void renderBlockLayer(BlockRenderLayer blockLayerIn) {
      this.mc.entityRenderer.enableLightmap();
      if (OpenGlHelper.useVbo()) {
         GlStateManager.glEnableClientState(32884);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         GlStateManager.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         GlStateManager.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         GlStateManager.glEnableClientState(32886);
      }

      if (Config.isShaders()) {
         ShadersRender.preRenderChunkLayer(blockLayerIn);
      }

      this.renderContainer.renderChunkLayer(blockLayerIn);
      if (Config.isShaders()) {
         ShadersRender.postRenderChunkLayer(blockLayerIn);
      }

      if (OpenGlHelper.useVbo()) {
         Iterator var2 = DefaultVertexFormats.BLOCK.getElements().iterator();

         while(var2.hasNext()) {
            VertexFormatElement vertexformatelement = (VertexFormatElement)var2.next();
            VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.getUsage();
            int k1 = vertexformatelement.getIndex();
            switch (vertexformatelement$enumusage) {
               case POSITION:
                  GlStateManager.glDisableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + k1);
                  GlStateManager.glDisableClientState(32888);
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                  break;
               case COLOR:
                  GlStateManager.glDisableClientState(32886);
                  GlStateManager.resetColor();
            }
         }
      }

      this.mc.entityRenderer.disableLightmap();
   }

   private void cleanupDamagedBlocks(Iterator<DestroyBlockProgress> iteratorIn) {
      while(iteratorIn.hasNext()) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iteratorIn.next();
         int k1 = destroyblockprogress.getCreationCloudUpdateTick();
         if (this.cloudTickCounter - k1 > 400) {
            iteratorIn.remove();
         }
      }

   }

   public void updateClouds() {
      if (Config.isShaders()) {
         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(24)) {
            GuiShaderOptions guishaderoptions = new GuiShaderOptions((GuiScreen)null, Config.getGameSettings());
            Config.getMinecraft().displayGuiScreen(guishaderoptions);
         }

         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
            Shaders.uninit();
            Shaders.loadShaderPack();
            Reflector.Minecraft_actionKeyF3.setValue(this.mc, Boolean.TRUE);
         }
      }

      ++this.cloudTickCounter;
      if (this.cloudTickCounter % 20 == 0) {
         this.cleanupDamagedBlocks(this.damagedBlocks.values().iterator());
      }

      if (!this.setLightUpdates.isEmpty() && !this.renderDispatcher.hasNoFreeRenderBuilders() && this.chunksToUpdate.isEmpty()) {
         Iterator<BlockPos> iterator = this.setLightUpdates.iterator();

         while(iterator.hasNext()) {
            BlockPos blockpos = (BlockPos)iterator.next();
            iterator.remove();
            int k1 = blockpos.getX();
            int l1 = blockpos.getY();
            int i2 = blockpos.getZ();
            this.markBlocksForUpdate(k1 - 1, l1 - 1, i2 - 1, k1 + 1, l1 + 1, i2 + 1, false);
         }
      }

   }

   private void renderSkyEnd() {
      if (Config.isSkyEnabled()) {
         GlStateManager.disableFog();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.depthMask(false);
         this.renderEngine.bindTexture(END_SKY_TEXTURES);
         Tessellator tessellator = Tessellator.getInstance();
         BufferBuilder bufferbuilder = tessellator.getBuffer();

         for(int k1 = 0; k1 < 6; ++k1) {
            GlStateManager.pushMatrix();
            if (k1 == 1) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 2) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 3) {
               GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 4) {
               GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (k1 == 5) {
               GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int l1 = 40;
            int i2 = 40;
            int j2 = 40;
            if (Config.isCustomColors()) {
               Vec3d vec3d = new Vec3d((double)l1 / 255.0, (double)i2 / 255.0, (double)j2 / 255.0);
               vec3d = CustomColors.getWorldSkyColor(vec3d, this.world, this.mc.getRenderViewEntity(), 0.0F);
               l1 = (int)(vec3d.x * 255.0);
               i2 = (int)(vec3d.y * 255.0);
               j2 = (int)(vec3d.z * 255.0);
            }

            bufferbuilder.pos(-100.0, -100.0, -100.0).tex(0.0, 0.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(-100.0, -100.0, 100.0).tex(0.0, 16.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(100.0, -100.0, 100.0).tex(16.0, 16.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(100.0, -100.0, -100.0).tex(16.0, 0.0).color(l1, i2, j2, 255).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
      }

   }

   public void renderSky(float partialTicks, int pass) {
      if (Reflector.ForgeWorldProvider_getSkyRenderer.exists()) {
         WorldProvider worldprovider = this.mc.world.provider;
         Object object = Reflector.call(worldprovider, Reflector.ForgeWorldProvider_getSkyRenderer);
         if (object != null) {
            Reflector.callVoid(object, Reflector.IRenderHandler_render, partialTicks, this.world, this.mc);
            return;
         }
      }

      if (this.mc.world.provider.getDimensionType() == DimensionType.THE_END) {
         this.renderSkyEnd();
      } else if (this.mc.world.provider.isSurfaceWorld()) {
         GlStateManager.disableTexture2D();
         boolean flag1 = Config.isShaders();
         if (flag1) {
            Shaders.disableTexture2D();
         }

         Vec3d vec3d = this.world.getSkyColor(this.mc.getRenderViewEntity(), partialTicks);
         vec3d = CustomColors.getSkyColor(vec3d, this.mc.world, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ);
         if (flag1) {
            Shaders.setSkyColor(vec3d);
         }

         float f = (float)vec3d.x;
         float f1 = (float)vec3d.y;
         float f2 = (float)vec3d.z;
         if (pass != 2) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
         }

         GlStateManager.color(f, f1, f2);
         Tessellator tessellator = Tessellator.getInstance();
         BufferBuilder bufferbuilder = tessellator.getBuffer();
         GlStateManager.depthMask(false);
         GlStateManager.enableFog();
         if (flag1) {
            Shaders.enableFog();
         }

         GlStateManager.color(f, f1, f2);
         if (flag1) {
            Shaders.preSkyList();
         }

         if (Config.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.skyVBO.bindBuffer();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.skyVBO.drawArrays(7);
               this.skyVBO.unbindBuffer();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList);
            }
         }

         GlStateManager.disableFog();
         if (flag1) {
            Shaders.disableFog();
         }

         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         RenderHelper.disableStandardItemLighting();
         float[] afloat = this.world.provider.calcSunriseSunsetColors(this.world.getCelestialAngle(partialTicks), partialTicks);
         float f15;
         float f16;
         float f17;
         float f20;
         float f22;
         float f24;
         int k2;
         if (afloat != null && Config.isSunMoonEnabled()) {
            GlStateManager.disableTexture2D();
            if (flag1) {
               Shaders.disableTexture2D();
            }

            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(this.world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            f15 = afloat[0];
            f16 = afloat[1];
            f17 = afloat[2];
            if (pass != 2) {
               float f9 = (f15 * 30.0F + f16 * 59.0F + f17 * 11.0F) / 100.0F;
               float f10 = (f15 * 30.0F + f16 * 70.0F) / 100.0F;
               f20 = (f15 * 30.0F + f17 * 70.0F) / 100.0F;
               f15 = f9;
               f16 = f10;
               f17 = f20;
            }

            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(0.0, 100.0, 0.0).color(f15, f16, f17, afloat[3]).endVertex();
            int l1 = true;

            for(k2 = 0; k2 <= 16; ++k2) {
               f20 = (float)k2 * 6.2831855F / 16.0F;
               f22 = MathHelper.sin(f20);
               f24 = MathHelper.cos(f20);
               bufferbuilder.pos((double)(f22 * 120.0F), (double)(f24 * 120.0F), (double)(-f24 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
         }

         GlStateManager.enableTexture2D();
         if (flag1) {
            Shaders.enableTexture2D();
         }

         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         GlStateManager.pushMatrix();
         f15 = 1.0F - this.world.getRainStrength(partialTicks);
         GlStateManager.color(1.0F, 1.0F, 1.0F, f15);
         GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         CustomSky.renderSky(this.world, this.renderEngine, partialTicks);
         if (flag1) {
            Shaders.preCelestialRotate();
         }

         GlStateManager.rotate(this.world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
         if (flag1) {
            Shaders.postCelestialRotate();
         }

         f16 = 30.0F;
         if (Config.isSunTexture()) {
            this.renderEngine.bindTexture(SUN_TEXTURES);
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos((double)(-f16), 100.0, (double)(-f16)).tex(0.0, 0.0).endVertex();
            bufferbuilder.pos((double)f16, 100.0, (double)(-f16)).tex(1.0, 0.0).endVertex();
            bufferbuilder.pos((double)f16, 100.0, (double)f16).tex(1.0, 1.0).endVertex();
            bufferbuilder.pos((double)(-f16), 100.0, (double)f16).tex(0.0, 1.0).endVertex();
            tessellator.draw();
         }

         f16 = 20.0F;
         if (Config.isMoonTexture()) {
            this.renderEngine.bindTexture(MOON_PHASES_TEXTURES);
            int k1 = this.world.getMoonPhase();
            int i2 = k1 % 4;
            k2 = k1 / 4 % 2;
            f20 = (float)(i2 + 0) / 4.0F;
            f22 = (float)(k2 + 0) / 2.0F;
            f24 = (float)(i2 + 1) / 4.0F;
            float f14 = (float)(k2 + 1) / 2.0F;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos((double)(-f16), -100.0, (double)f16).tex((double)f24, (double)f14).endVertex();
            bufferbuilder.pos((double)f16, -100.0, (double)f16).tex((double)f20, (double)f14).endVertex();
            bufferbuilder.pos((double)f16, -100.0, (double)(-f16)).tex((double)f20, (double)f22).endVertex();
            bufferbuilder.pos((double)(-f16), -100.0, (double)(-f16)).tex((double)f24, (double)f22).endVertex();
            tessellator.draw();
         }

         GlStateManager.disableTexture2D();
         if (flag1) {
            Shaders.disableTexture2D();
         }

         f17 = this.world.getStarBrightness(partialTicks) * f15;
         if (f17 > 0.0F && Config.isStarsEnabled() && !CustomSky.hasSkyLayers(this.world)) {
            GlStateManager.color(f17, f17, f17, f17);
            if (this.vboEnabled) {
               this.starVBO.bindBuffer();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.starVBO.drawArrays(7);
               this.starVBO.unbindBuffer();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.starGLCallList);
            }
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableAlpha();
         GlStateManager.enableFog();
         if (flag1) {
            Shaders.enableFog();
         }

         GlStateManager.popMatrix();
         GlStateManager.disableTexture2D();
         if (flag1) {
            Shaders.disableTexture2D();
         }

         GlStateManager.color(0.0F, 0.0F, 0.0F);
         Minecraft var10000 = this.mc;
         double d3 = Minecraft.player.getPositionEyes(partialTicks).y - this.world.getHorizon();
         if (d3 < 0.0) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList2);
            }

            GlStateManager.popMatrix();
            f20 = 1.0F;
            f22 = -((float)(d3 + 65.0));
            f24 = -1.0F;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(-1.0, (double)f22, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, (double)f22, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, (double)f22, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, (double)f22, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, (double)f22, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, (double)f22, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, (double)f22, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, (double)f22, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
         }

         if (this.world.provider.isSkyColored()) {
            GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
         } else {
            GlStateManager.color(f, f1, f2);
         }

         var10000 = this.mc;
         if (Minecraft.gameSettings.renderDistanceChunks <= 4) {
            GlStateManager.color(this.mc.entityRenderer.fogColorRed, this.mc.entityRenderer.fogColorGreen, this.mc.entityRenderer.fogColorBlue);
         }

         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -((float)(d3 - 16.0)), 0.0F);
         if (Config.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList2);
            }
         }

         GlStateManager.popMatrix();
         GlStateManager.enableTexture2D();
         if (flag1) {
            Shaders.enableTexture2D();
         }

         GlStateManager.depthMask(true);
      }

   }

   public void renderClouds(float partialTicks, int pass, double x, double y, double z) {
      if (!Config.isCloudsOff()) {
         if (Reflector.ForgeWorldProvider_getCloudRenderer.exists()) {
            WorldProvider worldprovider = this.mc.world.provider;
            Object object = Reflector.call(worldprovider, Reflector.ForgeWorldProvider_getCloudRenderer);
            if (object != null) {
               Reflector.callVoid(object, Reflector.IRenderHandler_render, partialTicks, this.world, this.mc);
               return;
            }
         }

         if (this.mc.world.provider.isSurfaceWorld()) {
            if (Config.isShaders()) {
               Shaders.beginClouds();
            }

            if (Config.isCloudsFancy()) {
               this.renderCloudsFancy(partialTicks, pass, x, y, z);
            } else {
               float f9 = partialTicks;
               partialTicks = 0.0F;
               GlStateManager.disableCull();
               int l2 = true;
               int k1 = true;
               Tessellator tessellator = Tessellator.getInstance();
               BufferBuilder bufferbuilder = tessellator.getBuffer();
               this.renderEngine.bindTexture(CLOUDS_TEXTURES);
               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
               Vec3d vec3d = this.world.getCloudColour(partialTicks);
               float f = (float)vec3d.x;
               float f1 = (float)vec3d.y;
               float f2 = (float)vec3d.z;
               this.cloudRenderer.prepareToRender(false, this.cloudTickCounter, f9, vec3d);
               if (this.cloudRenderer.shouldUpdateGlList()) {
                  this.cloudRenderer.startUpdateGlList();
                  float f10;
                  if (pass != 2) {
                     f10 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                     float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                     float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                     f = f10;
                     f1 = f4;
                     f2 = f5;
                  }

                  f10 = 4.8828125E-4F;
                  double d5 = (double)((float)this.cloudTickCounter + partialTicks);
                  double d3 = x + d5 * 0.029999999329447746;
                  int l1 = MathHelper.floor(d3 / 2048.0);
                  int i2 = MathHelper.floor(z / 2048.0);
                  d3 -= (double)(l1 * 2048);
                  double d4 = z - (double)(i2 * 2048);
                  float f6 = this.world.provider.getCloudHeight() - (float)y + 0.33F;
                  Minecraft var10001 = this.mc;
                  f6 += Minecraft.gameSettings.ofCloudsHeight * 128.0F;
                  float f7 = (float)(d3 * 4.8828125E-4);
                  float f8 = (float)(d4 * 4.8828125E-4);
                  bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

                  for(int j2 = -256; j2 < 256; j2 += 32) {
                     for(int k2 = -256; k2 < 256; k2 += 32) {
                        bufferbuilder.pos((double)(j2 + 0), (double)f6, (double)(k2 + 32)).tex((double)((float)(j2 + 0) * 4.8828125E-4F + f7), (double)((float)(k2 + 32) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                        bufferbuilder.pos((double)(j2 + 32), (double)f6, (double)(k2 + 32)).tex((double)((float)(j2 + 32) * 4.8828125E-4F + f7), (double)((float)(k2 + 32) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                        bufferbuilder.pos((double)(j2 + 32), (double)f6, (double)(k2 + 0)).tex((double)((float)(j2 + 32) * 4.8828125E-4F + f7), (double)((float)(k2 + 0) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                        bufferbuilder.pos((double)(j2 + 0), (double)f6, (double)(k2 + 0)).tex((double)((float)(j2 + 0) * 4.8828125E-4F + f7), (double)((float)(k2 + 0) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                     }
                  }

                  tessellator.draw();
                  this.cloudRenderer.endUpdateGlList();
               }

               this.cloudRenderer.renderGlList();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.disableBlend();
               GlStateManager.enableCull();
            }

            if (Config.isShaders()) {
               Shaders.endClouds();
            }
         }
      }

   }

   public boolean hasCloudFog(double x, double y, double z, float partialTicks) {
      return false;
   }

   private void renderCloudsFancy(float partialTicks, int pass, double x, double y, double z) {
      float f251 = 0.0F;
      GlStateManager.disableCull();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      float f = 12.0F;
      float f1 = 4.0F;
      double d3 = (double)((float)this.cloudTickCounter + f251);
      double d4 = (x + d3 * 0.029999999329447746) / 12.0;
      double d5 = z / 12.0 + 0.33000001311302185;
      float f2 = this.world.provider.getCloudHeight() - (float)y + 0.33F;
      Minecraft var10001 = this.mc;
      f2 += Minecraft.gameSettings.ofCloudsHeight * 128.0F;
      int k1 = MathHelper.floor(d4 / 2048.0);
      int l1 = MathHelper.floor(d5 / 2048.0);
      d4 -= (double)(k1 * 2048);
      d5 -= (double)(l1 * 2048);
      this.renderEngine.bindTexture(CLOUDS_TEXTURES);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      Vec3d vec3d = this.world.getCloudColour(f251);
      float f3 = (float)vec3d.x;
      float f4 = (float)vec3d.y;
      float f5 = (float)vec3d.z;
      this.cloudRenderer.prepareToRender(true, this.cloudTickCounter, partialTicks, vec3d);
      float f26;
      float f27;
      float f28;
      if (pass != 2) {
         f26 = (f3 * 30.0F + f4 * 59.0F + f5 * 11.0F) / 100.0F;
         f27 = (f3 * 30.0F + f4 * 70.0F) / 100.0F;
         f28 = (f3 * 30.0F + f5 * 70.0F) / 100.0F;
         f3 = f26;
         f4 = f27;
         f5 = f28;
      }

      f26 = f3 * 0.9F;
      f27 = f4 * 0.9F;
      f28 = f5 * 0.9F;
      float f9 = f3 * 0.7F;
      float f10 = f4 * 0.7F;
      float f11 = f5 * 0.7F;
      float f12 = f3 * 0.8F;
      float f13 = f4 * 0.8F;
      float f14 = f5 * 0.8F;
      float f15 = 0.00390625F;
      float f16 = (float)MathHelper.floor(d4) * 0.00390625F;
      float f17 = (float)MathHelper.floor(d5) * 0.00390625F;
      float f18 = (float)(d4 - (double)MathHelper.floor(d4));
      float f19 = (float)(d5 - (double)MathHelper.floor(d5));
      int i2 = true;
      int j2 = true;
      float f20 = 9.765625E-4F;
      GlStateManager.scale(12.0F, 1.0F, 12.0F);

      int j3;
      for(j3 = 0; j3 < 2; ++j3) {
         if (j3 == 0) {
            GlStateManager.colorMask(false, false, false, false);
         } else {
            switch (pass) {
               case 0:
                  GlStateManager.colorMask(false, true, true, true);
                  break;
               case 1:
                  GlStateManager.colorMask(true, false, false, true);
                  break;
               case 2:
                  GlStateManager.colorMask(true, true, true, true);
            }
         }

         this.cloudRenderer.renderGlList();
      }

      if (this.cloudRenderer.shouldUpdateGlList()) {
         this.cloudRenderer.startUpdateGlList();

         for(j3 = -3; j3 <= 4; ++j3) {
            for(int l2 = -3; l2 <= 4; ++l2) {
               bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
               float f21 = (float)(j3 * 8);
               float f22 = (float)(l2 * 8);
               float f23 = f21 - f18;
               float f24 = f22 - f19;
               if (f2 > -5.0F) {
                  bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + 8.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 0.0F), (double)(f24 + 8.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 0.0F), (double)(f24 + 0.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + 0.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f9, f10, f11, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
               }

               if (f2 <= 5.0F) {
                  bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 4.0F - 9.765625E-4F), (double)(f24 + 8.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 4.0F - 9.765625E-4F), (double)(f24 + 8.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 4.0F - 9.765625E-4F), (double)(f24 + 0.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                  bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 4.0F - 9.765625E-4F), (double)(f24 + 0.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f3, f4, f5, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
               }

               int i4;
               if (j3 > -1) {
                  for(i4 = 0; i4 < 8; ++i4) {
                     bufferbuilder.pos((double)(f23 + (float)i4 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + 8.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 0.0F), (double)(f2 + 4.0F), (double)(f24 + 8.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 0.0F), (double)(f2 + 4.0F), (double)(f24 + 0.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + 0.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                  }
               }

               if (j3 <= 1) {
                  for(i4 = 0; i4 < 8; ++i4) {
                     bufferbuilder.pos((double)(f23 + (float)i4 + 1.0F - 9.765625E-4F), (double)(f2 + 0.0F), (double)(f24 + 8.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 1.0F - 9.765625E-4F), (double)(f2 + 4.0F), (double)(f24 + 8.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 8.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 1.0F - 9.765625E-4F), (double)(f2 + 4.0F), (double)(f24 + 0.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + (float)i4 + 1.0F - 9.765625E-4F), (double)(f2 + 0.0F), (double)(f24 + 0.0F)).tex((double)((f21 + (float)i4 + 0.5F) * 0.00390625F + f16), (double)((f22 + 0.0F) * 0.00390625F + f17)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                  }
               }

               if (l2 > -1) {
                  for(i4 = 0; i4 < 8; ++i4) {
                     bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 4.0F), (double)(f24 + (float)i4 + 0.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 4.0F), (double)(f24 + (float)i4 + 0.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 0.0F), (double)(f24 + (float)i4 + 0.0F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + (float)i4 + 0.0F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                  }
               }

               if (l2 <= 1) {
                  for(i4 = 0; i4 < 8; ++i4) {
                     bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 4.0F), (double)(f24 + (float)i4 + 1.0F - 9.765625E-4F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 4.0F), (double)(f24 + (float)i4 + 1.0F - 9.765625E-4F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 8.0F), (double)(f2 + 0.0F), (double)(f24 + (float)i4 + 1.0F - 9.765625E-4F)).tex((double)((f21 + 8.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                     bufferbuilder.pos((double)(f23 + 0.0F), (double)(f2 + 0.0F), (double)(f24 + (float)i4 + 1.0F - 9.765625E-4F)).tex((double)((f21 + 0.0F) * 0.00390625F + f16), (double)((f22 + (float)i4 + 0.5F) * 0.00390625F + f17)).color(f12, f13, f14, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                  }
               }

               tessellator.draw();
            }
         }

         this.cloudRenderer.endUpdateGlList();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
   }

   public void updateChunks(long finishTimeNano) {
      finishTimeNano = (long)((double)finishTimeNano + 1.0E8);
      this.displayListEntitiesDirty |= this.renderDispatcher.runChunkUploads(finishTimeNano);
      Iterator iterator2;
      RenderChunk renderchunk3;
      if (this.chunksToUpdateForced.size() > 0) {
         iterator2 = this.chunksToUpdateForced.iterator();

         while(iterator2.hasNext()) {
            renderchunk3 = (RenderChunk)iterator2.next();
            if (!this.renderDispatcher.updateChunkLater(renderchunk3)) {
               break;
            }

            renderchunk3.clearNeedsUpdate();
            iterator2.remove();
            this.chunksToUpdate.remove(renderchunk3);
            this.chunksToResortTransparency.remove(renderchunk3);
         }
      }

      if (this.chunksToResortTransparency.size() > 0) {
         iterator2 = this.chunksToResortTransparency.iterator();
         if (iterator2.hasNext()) {
            renderchunk3 = (RenderChunk)iterator2.next();
            if (this.renderDispatcher.updateTransparencyLater(renderchunk3)) {
               iterator2.remove();
            }
         }
      }

      double d4 = 0.0;
      int k1 = Config.getUpdatesPerFrame();
      if (!this.chunksToUpdate.isEmpty()) {
         Iterator<RenderChunk> iterator1 = this.chunksToUpdate.iterator();

         while(iterator1.hasNext()) {
            RenderChunk renderchunk2 = (RenderChunk)iterator1.next();
            boolean flag2 = renderchunk2.isChunkRegionEmpty();
            boolean flag1;
            if (!renderchunk2.needsImmediateUpdate() && !flag2) {
               flag1 = this.renderDispatcher.updateChunkLater(renderchunk2);
            } else {
               flag1 = this.renderDispatcher.updateChunkNow(renderchunk2);
            }

            if (!flag1) {
               break;
            }

            renderchunk2.clearNeedsUpdate();
            iterator1.remove();
            if (!flag2) {
               double d3 = 2.0 * RenderChunkUtils.getRelativeBufferSize(renderchunk2);
               d4 += d3;
               if (d4 > (double)k1) {
                  break;
               }
            }
         }
      }

   }

   public void renderWorldBorder(Entity entityIn, float partialTicks) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      WorldBorder worldborder = this.world.getWorldBorder();
      Minecraft var10000 = this.mc;
      double d3 = (double)(Minecraft.gameSettings.renderDistanceChunks * 16);
      if (entityIn.posX >= worldborder.maxX() - d3 || entityIn.posX <= worldborder.minX() + d3 || entityIn.posZ >= worldborder.maxZ() - d3 || entityIn.posZ <= worldborder.minZ() + d3) {
         if (Config.isShaders()) {
            Shaders.pushProgram();
            Shaders.useProgram(Shaders.ProgramTexturedLit);
         }

         double d4 = 1.0 - worldborder.getClosestDistance(entityIn) / d3;
         d4 = Math.pow(d4, 4.0);
         double d5 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
         double d6 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
         double d7 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         this.renderEngine.bindTexture(FORCEFIELD_TEXTURES);
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         int k1 = worldborder.getStatus().getColor();
         float f = (float)(k1 >> 16 & 255) / 255.0F;
         float f1 = (float)(k1 >> 8 & 255) / 255.0F;
         float f2 = (float)(k1 & 255) / 255.0F;
         GlStateManager.color(f, f1, f2, (float)d4);
         GlStateManager.doPolygonOffset(-3.0F, -3.0F);
         GlStateManager.enablePolygonOffset();
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.enableAlpha();
         GlStateManager.disableCull();
         float f3 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 128.0F;
         bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
         bufferbuilder.setTranslation(-d5, -d6, -d7);
         double d8 = Math.max((double)MathHelper.floor(d7 - d3), worldborder.minZ());
         double d9 = Math.min((double)MathHelper.ceil(d7 + d3), worldborder.maxZ());
         float f11;
         double d14;
         double d17;
         float f14;
         if (d5 > worldborder.maxX() - d3) {
            f11 = 0.0F;

            for(d14 = d8; d14 < d9; f11 += 0.5F) {
               d17 = Math.min(1.0, d9 - d14);
               f14 = (float)d17 * 0.5F;
               bufferbuilder.pos(worldborder.maxX(), 256.0, d14).tex((double)(f3 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(worldborder.maxX(), 256.0, d14 + d17).tex((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(worldborder.maxX(), 0.0, d14 + d17).tex((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).endVertex();
               bufferbuilder.pos(worldborder.maxX(), 0.0, d14).tex((double)(f3 + f11), (double)(f3 + 128.0F)).endVertex();
               ++d14;
            }
         }

         if (d5 < worldborder.minX() + d3) {
            f11 = 0.0F;

            for(d14 = d8; d14 < d9; f11 += 0.5F) {
               d17 = Math.min(1.0, d9 - d14);
               f14 = (float)d17 * 0.5F;
               bufferbuilder.pos(worldborder.minX(), 256.0, d14).tex((double)(f3 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(worldborder.minX(), 256.0, d14 + d17).tex((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(worldborder.minX(), 0.0, d14 + d17).tex((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).endVertex();
               bufferbuilder.pos(worldborder.minX(), 0.0, d14).tex((double)(f3 + f11), (double)(f3 + 128.0F)).endVertex();
               ++d14;
            }
         }

         d8 = Math.max((double)MathHelper.floor(d5 - d3), worldborder.minX());
         d9 = Math.min((double)MathHelper.ceil(d5 + d3), worldborder.maxX());
         if (d7 > worldborder.maxZ() - d3) {
            f11 = 0.0F;

            for(d14 = d8; d14 < d9; f11 += 0.5F) {
               d17 = Math.min(1.0, d9 - d14);
               f14 = (float)d17 * 0.5F;
               bufferbuilder.pos(d14, 256.0, worldborder.maxZ()).tex((double)(f3 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(d14 + d17, 256.0, worldborder.maxZ()).tex((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(d14 + d17, 0.0, worldborder.maxZ()).tex((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).endVertex();
               bufferbuilder.pos(d14, 0.0, worldborder.maxZ()).tex((double)(f3 + f11), (double)(f3 + 128.0F)).endVertex();
               ++d14;
            }
         }

         if (d7 < worldborder.minZ() + d3) {
            f11 = 0.0F;

            for(d14 = d8; d14 < d9; f11 += 0.5F) {
               d17 = Math.min(1.0, d9 - d14);
               f14 = (float)d17 * 0.5F;
               bufferbuilder.pos(d14, 256.0, worldborder.minZ()).tex((double)(f3 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(d14 + d17, 256.0, worldborder.minZ()).tex((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).endVertex();
               bufferbuilder.pos(d14 + d17, 0.0, worldborder.minZ()).tex((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).endVertex();
               bufferbuilder.pos(d14, 0.0, worldborder.minZ()).tex((double)(f3 + f11), (double)(f3 + 128.0F)).endVertex();
               ++d14;
            }
         }

         tessellator.draw();
         bufferbuilder.setTranslation(0.0, 0.0, 0.0);
         GlStateManager.enableCull();
         GlStateManager.disableAlpha();
         GlStateManager.doPolygonOffset(0.0F, 0.0F);
         GlStateManager.disablePolygonOffset();
         GlStateManager.enableAlpha();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         GlStateManager.disableBlend();
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         if (Config.isShaders()) {
            Shaders.popProgram();
         }
      }

   }

   private void preRenderDamagedBlocks() {
      GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.enableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.doPolygonOffset(-1.0F, -10.0F);
      GlStateManager.enablePolygonOffset();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.enableAlpha();
      GlStateManager.pushMatrix();
      if (Config.isShaders()) {
         ShadersRender.beginBlockDamage();
      }

   }

   private void postRenderDamagedBlocks() {
      GlStateManager.disableAlpha();
      GlStateManager.doPolygonOffset(0.0F, 0.0F);
      GlStateManager.disablePolygonOffset();
      GlStateManager.enableAlpha();
      GlStateManager.depthMask(true);
      GlStateManager.popMatrix();
      if (Config.isShaders()) {
         ShadersRender.endBlockDamage();
      }

   }

   public void drawBlockDamageTexture(Tessellator tessellatorIn, BufferBuilder bufferBuilderIn, Entity entityIn, float partialTicks) {
      double d3 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
      double d4 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
      double d5 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
      if (!this.damagedBlocks.isEmpty()) {
         this.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
         this.preRenderDamagedBlocks();
         bufferBuilderIn.begin(7, DefaultVertexFormats.BLOCK);
         bufferBuilderIn.setTranslation(-d3, -d4, -d5);
         bufferBuilderIn.noColor();
         Iterator<DestroyBlockProgress> iterator = this.damagedBlocks.values().iterator();

         while(iterator.hasNext()) {
            DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iterator.next();
            BlockPos blockpos = destroyblockprogress.getPosition();
            double d6 = (double)blockpos.getX() - d3;
            double d7 = (double)blockpos.getY() - d4;
            double d8 = (double)blockpos.getZ() - d5;
            Block block = this.world.getBlockState(blockpos).getBlock();
            boolean flag1;
            if (Reflector.ForgeTileEntity_canRenderBreaking.exists()) {
               boolean flag2 = block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSign || block instanceof BlockSkull;
               if (!flag2) {
                  TileEntity tileentity = this.world.getTileEntity(blockpos);
                  if (tileentity != null) {
                     flag2 = Reflector.callBoolean(tileentity, Reflector.ForgeTileEntity_canRenderBreaking);
                  }
               }

               flag1 = !flag2;
            } else {
               flag1 = !(block instanceof BlockChest) && !(block instanceof BlockEnderChest) && !(block instanceof BlockSign) && !(block instanceof BlockSkull);
            }

            if (flag1) {
               if (d6 * d6 + d7 * d7 + d8 * d8 > 1024.0) {
                  iterator.remove();
               } else {
                  IBlockState iblockstate = this.world.getBlockState(blockpos);
                  if (iblockstate.getMaterial() != Material.AIR) {
                     int k1 = destroyblockprogress.getPartialBlockDamage();
                     TextureAtlasSprite textureatlassprite = this.destroyBlockIcons[k1];
                     BlockRendererDispatcher blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
                     blockrendererdispatcher.renderBlockDamage(iblockstate, blockpos, textureatlassprite, this.world);
                  }
               }
            }
         }

         tessellatorIn.draw();
         bufferBuilderIn.setTranslation(0.0, 0.0, 0.0);
         this.postRenderDamagedBlocks();
      }

   }

   public void drawSelectionBox(EntityPlayer player, RayTraceResult movingObjectPositionIn, int execute, float partialTicks) {
      if (execute == 0 && movingObjectPositionIn.typeOfHit == RayTraceResult.Type.BLOCK) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         GlStateManager.glLineWidth(2.0F);
         GlStateManager.disableTexture2D();
         if (Config.isShaders()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.depthMask(false);
         BlockPos blockpos = movingObjectPositionIn.getBlockPos();
         IBlockState iblockstate = this.world.getBlockState(blockpos);
         if (iblockstate.getMaterial() != Material.AIR && this.world.getWorldBorder().contains(blockpos)) {
            double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
            double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
            double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
            drawSelectionBoundingBox(iblockstate.getSelectedBoundingBox(this.world, blockpos).grow(0.0020000000949949026).offset(-d3, -d4, -d5), 0.0F, 0.0F, 0.0F, 0.4F);
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         if (Config.isShaders()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.disableBlend();
      }

   }

   public static void drawSelectionBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha) {
      drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
   }

   public static void drawBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
      drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
      tessellator.draw();
   }

   public static void drawBoundingBox(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
      buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
      buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, maxY, minZ).color(red, green, blue, 0.0F).endVertex();
      buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, minY, maxZ).color(red, green, blue, 0.0F).endVertex();
      buffer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
      buffer.pos(maxX, maxY, minZ).color(red, green, blue, 0.0F).endVertex();
      buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
   }

   public static void renderFilledBox(AxisAlignedBB aabb, float red, float green, float blue, float alpha) {
      renderFilledBox(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ, red, green, blue, alpha);
   }

   public static void renderFilledBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
      addChainedFilledBoxVertices(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
      tessellator.draw();
   }

   public static void addChainedFilledBoxVertices(BufferBuilder builder, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
      builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
      builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
   }

   private void markBlocksForUpdate(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean updateImmediately) {
      this.viewFrustum.markBlocksForUpdate(minX, minY, minZ, maxX, maxY, maxZ, updateImmediately);
   }

   public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
      int k1 = pos.getX();
      int l1 = pos.getY();
      int i2 = pos.getZ();
      this.markBlocksForUpdate(k1 - 1, l1 - 1, i2 - 1, k1 + 1, l1 + 1, i2 + 1, (flags & 8) != 0);
   }

   public void notifyLightSet(BlockPos pos) {
      this.setLightUpdates.add(pos.toImmutable());
   }

   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
      this.markBlocksForUpdate(x1 - 1, y1 - 1, z1 - 1, x2 + 1, y2 + 1, z2 + 1, false);
   }

   public void playRecord(@Nullable SoundEvent soundIn, BlockPos pos) {
      ISound isound = (ISound)this.mapSoundPositions.get(pos);
      if (isound != null) {
         this.mc.getSoundHandler().stopSound(isound);
         this.mapSoundPositions.remove(pos);
      }

      if (soundIn != null) {
         ItemRecord itemrecord = ItemRecord.getBySound(soundIn);
         if (itemrecord != null) {
            this.mc.ingameGUI.setRecordPlayingMessage(itemrecord.getRecordNameLocal());
         }

         ISound isound1 = PositionedSoundRecord.getRecordSoundRecord(soundIn, (float)pos.getX(), (float)pos.getY(), (float)pos.getZ());
         this.mapSoundPositions.put(pos, isound1);
         this.mc.getSoundHandler().playSound(isound1);
      }

      this.setPartying(this.world, pos, soundIn != null);
   }

   private void setPartying(World worldIn, BlockPos pos, boolean isPartying) {
      Iterator var4 = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, (new AxisAlignedBB(pos)).grow(3.0)).iterator();

      while(var4.hasNext()) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)var4.next();
         entitylivingbase.setPartying(pos, isPartying);
      }

   }

   public void playSoundToAllNearExcept(@Nullable EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
   }

   public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      this.spawnParticle(particleID, ignoreRange, false, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   public void spawnParticle(int id, boolean ignoreRange, boolean minimiseParticleLevel, final double x, final double y, final double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      try {
         this.spawnParticle0(id, ignoreRange, minimiseParticleLevel, x, y, z, xSpeed, ySpeed, zSpeed, parameters);
      } catch (Throwable var20) {
         Throwable throwable = var20;
         CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception while adding particle");
         CrashReportCategory crashreportcategory = crashreport.makeCategory("Particle being added");
         crashreportcategory.addCrashSection("ID", id);
         if (parameters != null) {
            crashreportcategory.addCrashSection("Parameters", parameters);
         }

         crashreportcategory.addDetail("Position", new ICrashReportDetail<String>() {
            public String call() throws Exception {
               return CrashReportCategory.getCoordinateInfo(x, y, z);
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   private void spawnParticle(EnumParticleTypes particleIn, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      this.spawnParticle(particleIn.getParticleID(), particleIn.getShouldIgnoreRange(), xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   @Nullable
   private Particle spawnParticle0(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      return this.spawnParticle0(particleID, ignoreRange, false, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   @Nullable
   private Particle spawnParticle0(int particleID, boolean ignoreRange, boolean minParticles, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      Entity entity = this.mc.getRenderViewEntity();
      if (this.mc != null && entity != null && this.mc.effectRenderer != null) {
         int k1 = this.calculateParticleLevel(minParticles);
         double d3 = entity.posX - xCoord;
         double d4 = entity.posY - yCoord;
         double d5 = entity.posZ - zCoord;
         if (particleID == EnumParticleTypes.EXPLOSION_HUGE.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.EXPLOSION_LARGE.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.EXPLOSION_NORMAL.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.SUSPENDED.getParticleID() && !Config.isWaterParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SUSPENDED_DEPTH.getParticleID() && !Config.isVoidParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SMOKE_NORMAL.getParticleID() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (particleID == EnumParticleTypes.SMOKE_LARGE.getParticleID() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_MOB.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_INSTANT.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_WITCH.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.PORTAL.getParticleID() && !Config.isPortalParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.FLAME.getParticleID() && !Config.isAnimatedFlame()) {
            return null;
         } else if (particleID == EnumParticleTypes.REDSTONE.getParticleID() && !Config.isAnimatedRedstone()) {
            return null;
         } else if (particleID == EnumParticleTypes.DRIP_WATER.getParticleID() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (particleID == EnumParticleTypes.DRIP_LAVA.getParticleID() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (particleID == EnumParticleTypes.FIREWORKS_SPARK.getParticleID() && !Config.isFireworkParticles()) {
            return null;
         } else {
            if (!ignoreRange) {
               double d6 = 1024.0;
               if (particleID == EnumParticleTypes.CRIT.getParticleID()) {
                  d6 = 38416.0;
               }

               if (d3 * d3 + d4 * d4 + d5 * d5 > d6) {
                  return null;
               }

               if (k1 > 1) {
                  return null;
               }
            }

            Particle particle = this.mc.effectRenderer.spawnEffectParticle(particleID, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
            if (particleID == EnumParticleTypes.WATER_BUBBLE.getParticleID()) {
               CustomColors.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.WATER_SPLASH.getParticleID()) {
               CustomColors.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.WATER_DROP.getParticleID()) {
               CustomColors.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.TOWN_AURA.getParticleID()) {
               CustomColors.updateMyceliumFX(particle);
            }

            if (particleID == EnumParticleTypes.PORTAL.getParticleID()) {
               CustomColors.updatePortalFX(particle);
            }

            if (particleID == EnumParticleTypes.REDSTONE.getParticleID()) {
               CustomColors.updateReddustFX(particle, this.world, xCoord, yCoord, zCoord);
            }

            return particle;
         }
      } else {
         return null;
      }
   }

   private int calculateParticleLevel(boolean minimiseLevel) {
      Minecraft var10000 = this.mc;
      int k1 = Minecraft.gameSettings.particleSetting;
      if (minimiseLevel && k1 == 2 && this.world.rand.nextInt(10) == 0) {
         k1 = 1;
      }

      if (k1 == 1 && this.world.rand.nextInt(3) == 0) {
         k1 = 2;
      }

      return k1;
   }

   public void onEntityAdded(Entity entityIn) {
      RandomEntities.entityLoaded(entityIn, this.world);
      if (Config.isDynamicLights()) {
         DynamicLights.entityAdded(entityIn, this);
      }

   }

   public void onEntityRemoved(Entity entityIn) {
      RandomEntities.entityUnloaded(entityIn, this.world);
      if (Config.isDynamicLights()) {
         DynamicLights.entityRemoved(entityIn, this);
      }

   }

   public void deleteAllDisplayLists() {
   }

   public void broadcastSound(int soundID, BlockPos pos, int data) {
      switch (soundID) {
         case 1023:
         case 1028:
         case 1038:
            Entity entity = this.mc.getRenderViewEntity();
            if (entity != null) {
               double d3 = (double)pos.getX() - entity.posX;
               double d4 = (double)pos.getY() - entity.posY;
               double d5 = (double)pos.getZ() - entity.posZ;
               double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
               double d7 = entity.posX;
               double d8 = entity.posY;
               double d9 = entity.posZ;
               if (d6 > 0.0) {
                  d7 += d3 / d6 * 2.0;
                  d8 += d4 / d6 * 2.0;
                  d9 += d5 / d6 * 2.0;
               }

               if (soundID == 1023) {
                  this.world.playSound(d7, d8, d9, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
               } else if (soundID == 1038) {
                  this.world.playSound(d7, d8, d9, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
               } else {
                  this.world.playSound(d7, d8, d9, SoundEvents.ENTITY_ENDERDRAGON_DEATH, SoundCategory.HOSTILE, 5.0F, 1.0F, false);
               }
            }
         default:
      }
   }

   public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {
      Random random = this.world.rand;
      double d6;
      double d7;
      double d8;
      double d9;
      double d11;
      int j3;
      double d29;
      double d16;
      double d27;
      switch (type) {
         case 1000:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1001:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
            break;
         case 1002:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
            break;
         case 1003:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
            break;
         case 1004:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_FIREWORK_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
            break;
         case 1005:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1006:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1007:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1008:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_FENCE_GATE_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1009:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
            break;
         case 1010:
            if (Item.getItemById(data) instanceof ItemRecord) {
               this.world.playRecord(blockPosIn, ((ItemRecord)Item.getItemById(data)).getSound());
            } else {
               this.world.playRecord(blockPosIn, (SoundEvent)null);
            }
            break;
         case 1011:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1012:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1013:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1014:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1015:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_GHAST_WARN, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1016:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1017:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ENDERDRAGON_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1018:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1019:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1020:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1021:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1022:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1024:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1025:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1026:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1027:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1029:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1030:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1031:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1032:
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_PORTAL_TRAVEL, random.nextFloat() * 0.4F + 0.8F));
            break;
         case 1033:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1034:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1035:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1036:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1037:
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2000:
            int k1 = data % 3 - 1;
            int l1 = data / 3 % 3 - 1;
            double d3 = (double)blockPosIn.getX() + (double)k1 * 0.6 + 0.5;
            double d4 = (double)blockPosIn.getY() + 0.5;
            double d5 = (double)blockPosIn.getZ() + (double)l1 * 0.6 + 0.5;

            for(int k2 = 0; k2 < 10; ++k2) {
               d6 = random.nextDouble() * 0.2 + 0.01;
               d7 = d3 + (double)k1 * 0.01 + (random.nextDouble() - 0.5) * (double)l1 * 0.5;
               d8 = d4 + (random.nextDouble() - 0.5) * 0.5;
               double d21 = d5 + (double)l1 * 0.01 + (random.nextDouble() - 0.5) * (double)k1 * 0.5;
               double d22 = (double)k1 * d6 + random.nextGaussian() * 0.01;
               d9 = -0.03 + random.nextGaussian() * 0.01;
               d11 = (double)l1 * d6 + random.nextGaussian() * 0.01;
               this.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d7, d8, d21, d22, d9, d11);
            }

            return;
         case 2001:
            Block block = Block.getBlockById(data & 4095);
            if (block.getDefaultState().getMaterial() != Material.AIR) {
               SoundType soundtype = block.getSoundType();
               if (Reflector.ForgeBlock_getSoundType.exists()) {
                  soundtype = (SoundType)Reflector.call(block, Reflector.ForgeBlock_getSoundType, Block.getStateById(data), this.world, blockPosIn, null);
               }

               this.world.playSound(blockPosIn, soundtype.getBreakSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F, false);
            }

            this.mc.effectRenderer.addBlockDestroyEffects(blockPosIn, block.getStateFromMeta(data >> 12 & 255));
            break;
         case 2002:
         case 2007:
            d6 = (double)blockPosIn.getX();
            d7 = (double)blockPosIn.getY();
            d8 = (double)blockPosIn.getZ();

            for(int i2 = 0; i2 < 8; ++i2) {
               this.spawnParticle(EnumParticleTypes.ITEM_CRACK, d6, d7, d8, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, Item.getIdFromItem(Items.SPLASH_POTION));
            }

            float f5 = (float)(data >> 16 & 255) / 255.0F;
            float f = (float)(data >> 8 & 255) / 255.0F;
            float f1 = (float)(data >> 0 & 255) / 255.0F;
            EnumParticleTypes enumparticletypes = type == 2007 ? EnumParticleTypes.SPELL_INSTANT : EnumParticleTypes.SPELL;

            for(int l2 = 0; l2 < 100; ++l2) {
               double d10 = random.nextDouble() * 4.0;
               double d12 = random.nextDouble() * Math.PI * 2.0;
               double d14 = Math.cos(d12) * d10;
               d27 = 0.01 + random.nextDouble() * 0.5;
               d29 = Math.sin(d12) * d10;
               Particle particle1 = this.spawnParticle0(enumparticletypes.getParticleID(), enumparticletypes.getShouldIgnoreRange(), d6 + d14 * 0.1, d7 + 0.3, d8 + d29 * 0.1, d14, d27, d29);
               if (particle1 != null) {
                  float f4 = 0.75F + random.nextFloat() * 0.25F;
                  particle1.setRBGColorF(f5 * f4, f * f4, f1 * f4);
                  particle1.multiplyVelocity((float)d10);
               }
            }

            this.world.playSound(blockPosIn, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2003:
            d9 = (double)blockPosIn.getX() + 0.5;
            d11 = (double)blockPosIn.getY();
            double d13 = (double)blockPosIn.getZ() + 0.5;

            for(j3 = 0; j3 < 8; ++j3) {
               this.spawnParticle(EnumParticleTypes.ITEM_CRACK, d9, d11, d13, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, Item.getIdFromItem(Items.ENDER_EYE));
            }

            for(double d25 = 0.0; d25 < 6.283185307179586; d25 += 0.15707963267948966) {
               this.spawnParticle(EnumParticleTypes.PORTAL, d9 + Math.cos(d25) * 5.0, d11 - 0.4, d13 + Math.sin(d25) * 5.0, Math.cos(d25) * -5.0, 0.0, Math.sin(d25) * -5.0);
               this.spawnParticle(EnumParticleTypes.PORTAL, d9 + Math.cos(d25) * 5.0, d11 - 0.4, d13 + Math.sin(d25) * 5.0, Math.cos(d25) * -7.0, 0.0, Math.sin(d25) * -7.0);
            }

            return;
         case 2004:
            for(j3 = 0; j3 < 20; ++j3) {
               d27 = (double)blockPosIn.getX() + 0.5 + ((double)this.world.rand.nextFloat() - 0.5) * 2.0;
               d29 = (double)blockPosIn.getY() + 0.5 + ((double)this.world.rand.nextFloat() - 0.5) * 2.0;
               d16 = (double)blockPosIn.getZ() + 0.5 + ((double)this.world.rand.nextFloat() - 0.5) * 2.0;
               this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d27, d29, d16, 0.0, 0.0, 0.0, new int[0]);
               this.world.spawnParticle(EnumParticleTypes.FLAME, d27, d29, d16, 0.0, 0.0, 0.0, new int[0]);
            }

            return;
         case 2005:
            ItemDye.spawnBonemealParticles(this.world, blockPosIn, data);
            break;
         case 2006:
            for(j3 = 0; j3 < 200; ++j3) {
               float f2 = random.nextFloat() * 4.0F;
               float f3 = random.nextFloat() * 6.2831855F;
               d29 = (double)(MathHelper.cos(f3) * f2);
               d16 = 0.01 + random.nextDouble() * 0.5;
               double d17 = (double)(MathHelper.sin(f3) * f2);
               Particle particle = this.spawnParticle0(EnumParticleTypes.DRAGON_BREATH.getParticleID(), false, (double)blockPosIn.getX() + d29 * 0.1, (double)blockPosIn.getY() + 0.3, (double)blockPosIn.getZ() + d17 * 0.1, d29, d16, d17);
               if (particle != null) {
                  particle.multiplyVelocity(f2);
               }
            }

            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ENDERDRAGON_FIREBALL_EPLD, SoundCategory.HOSTILE, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 3000:
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true, (double)blockPosIn.getX() + 0.5, (double)blockPosIn.getY() + 0.5, (double)blockPosIn.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
            this.world.playSound(blockPosIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.BLOCKS, 10.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
            break;
         case 3001:
            this.world.playSound(blockPosIn, SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.HOSTILE, 64.0F, 0.8F + this.world.rand.nextFloat() * 0.3F, false);
      }

   }

   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
      if (progress >= 0 && progress < 10) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)this.damagedBlocks.get(breakerId);
         if (destroyblockprogress == null || destroyblockprogress.getPosition().getX() != pos.getX() || destroyblockprogress.getPosition().getY() != pos.getY() || destroyblockprogress.getPosition().getZ() != pos.getZ()) {
            destroyblockprogress = new DestroyBlockProgress(breakerId, pos);
            this.damagedBlocks.put(breakerId, destroyblockprogress);
         }

         destroyblockprogress.setPartialBlockDamage(progress);
         destroyblockprogress.setCloudUpdateTick(this.cloudTickCounter);
      } else {
         this.damagedBlocks.remove(breakerId);
      }

   }

   public boolean hasNoChunkUpdates() {
      return this.chunksToUpdate.isEmpty() && this.renderDispatcher.hasNoChunkUpdates();
   }

   public void setDisplayListEntitiesDirty() {
      this.displayListEntitiesDirty = true;
   }

   public void resetClouds() {
      this.cloudRenderer.reset();
   }

   public int getCountRenderers() {
      return this.viewFrustum.renderChunks.length;
   }

   public int getCountActiveRenderers() {
      return this.renderInfos.size();
   }

   public int getCountEntitiesRendered() {
      return this.countEntitiesRendered;
   }

   public int getCountTileEntitiesRendered() {
      return this.countTileEntitiesRendered;
   }

   public int getCountLoadedChunks() {
      if (this.world == null) {
         return 0;
      } else {
         IChunkProvider ichunkprovider = this.world.getChunkProvider();
         if (ichunkprovider == null) {
            return 0;
         } else {
            if (ichunkprovider != this.worldChunkProvider) {
               this.worldChunkProvider = ichunkprovider;
               this.worldChunkProviderMap = (Long2ObjectMap)Reflector.getFieldValue(ichunkprovider, Reflector.ChunkProviderClient_chunkMapping);
            }

            return this.worldChunkProviderMap == null ? 0 : this.worldChunkProviderMap.size();
         }
      }
   }

   public int getCountChunksToUpdate() {
      return this.chunksToUpdate.size();
   }

   public RenderChunk getRenderChunk(BlockPos p_getRenderChunk_1_) {
      return this.viewFrustum.getRenderChunk(p_getRenderChunk_1_);
   }

   public WorldClient getWorld() {
      return this.world;
   }

   private void clearRenderInfos() {
      if (renderEntitiesCounter > 0) {
         this.renderInfos = new ArrayList(this.renderInfos.size() + 16);
         this.renderInfosEntities = new ArrayList(this.renderInfosEntities.size() + 16);
         this.renderInfosTileEntities = new ArrayList(this.renderInfosTileEntities.size() + 16);
      } else {
         this.renderInfos.clear();
         this.renderInfosEntities.clear();
         this.renderInfosTileEntities.clear();
      }

   }

   public void onPlayerPositionSet() {
      if (this.firstWorldLoad) {
         this.loadRenderers();
         this.firstWorldLoad = false;
      }

   }

   public void pauseChunkUpdates() {
      if (this.renderDispatcher != null) {
         this.renderDispatcher.pauseChunkUpdates();
      }

   }

   public void resumeChunkUpdates() {
      if (this.renderDispatcher != null) {
         this.renderDispatcher.resumeChunkUpdates();
      }

   }

   public void updateTileEntities(Collection<TileEntity> tileEntitiesToRemove, Collection<TileEntity> tileEntitiesToAdd) {
      synchronized(this.setTileEntities) {
         this.setTileEntities.removeAll(tileEntitiesToRemove);
         this.setTileEntities.addAll(tileEntitiesToAdd);
      }
   }

   static {
      SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(EnumFacing.VALUES)));
      renderEntitiesCounter = 0;
   }

   public static class ContainerLocalRenderInformation {
      final RenderChunk renderChunk;
      EnumFacing facing;
      int setFacing;

      public ContainerLocalRenderInformation(RenderChunk p_i5_1_, EnumFacing p_i5_2_, int p_i5_3_) {
         this.renderChunk = p_i5_1_;
         this.facing = p_i5_2_;
         this.setFacing = p_i5_3_;
      }

      public void setDirection(byte p_189561_1_, EnumFacing p_189561_2_) {
         this.setFacing = this.setFacing | p_189561_1_ | 1 << p_189561_2_.ordinal();
      }

      public boolean hasDirection(EnumFacing p_189560_1_) {
         return (this.setFacing & 1 << p_189560_1_.ordinal()) > 0;
      }

      private void initialize(EnumFacing p_initialize_1_, int p_initialize_2_) {
         this.facing = p_initialize_1_;
         this.setFacing = p_initialize_2_;
      }
   }
}
