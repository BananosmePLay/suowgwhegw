package neo;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class yy implements bgc, AB {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
   private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
   private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("textures/environment/clouds.png");
   private static final ResourceLocation END_SKY_TEXTURES = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation FORCEFIELD_TEXTURES = new ResourceLocation("textures/misc/forcefield.png");
   public final nC mc;
   private final zf renderEngine;
   private final wC renderManager;
   private pm world;
   private Set<ug> chunksToUpdate = new ObjectLinkedOpenHashSet();
   private List<yx> renderInfos = Lists.newArrayListWithCapacity(69696);
   private final Set<Yg> setTileEntities = Sets.newHashSet();
   private zT viewFrustum;
   private int starGLCallList = -1;
   private int glSkyList = -1;
   private int glSkyList2 = -1;
   private final zO vertexBufferFormat;
   private zM starVBO;
   private zM skyVBO;
   private zM sky2VBO;
   private int cloudTickCounter;
   public final Map<Integer, va> damagedBlocks = Maps.newHashMap();
   private final Map<BlockPos, iC> mapSoundPositions = Maps.newHashMap();
   private final zd[] destroyBlockIcons = new zd[10];
   private Bn entityOutlineFramebuffer;
   private Br entityOutlineShader;
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
   private tW renderDispatcher;
   private ul renderContainer;
   private int renderDistanceChunks = -1;
   private int renderEntitiesStartupCounter = 2;
   private int countEntitiesTotal;
   private int countEntitiesRendered;
   private int countEntitiesHidden;
   private boolean debugFixTerrainFrustum;
   private uL debugFixedClippingHelper;
   private final Vector4f[] debugTerrainMatrix = new Vector4f[8];
   private final zJ debugTerrainFrustumPosition = new zJ();
   private boolean vboEnabled;
   uc renderChunkFactory;
   private double prevRenderSortX;
   private double prevRenderSortY;
   private double prevRenderSortZ;
   public boolean displayListEntitiesDirty = true;
   private boolean entityOutlinesRendered;
   private final Set<BlockPos> setLightUpdates = Sets.newHashSet();
   private bnX cloudRenderer;
   public Ig renderedEntity;
   public Set chunksToResortTransparency = new LinkedHashSet();
   public Set chunksToUpdateForced = new LinkedHashSet();
   private Set<ug> chunksToUpdatePrev = new ObjectLinkedOpenHashSet();
   private Deque visibilityDeque = new ArrayDeque();
   private List<yx> renderInfosEntities = new ArrayList(1024);
   private List<yx> renderInfosTileEntities = new ArrayList(1024);
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
   private bar worldChunkProvider = null;
   private Long2ObjectMap<bam> worldChunkProviderMap = null;
   private int countLoadedChunksPrev = 0;
   private boa renderEnv;
   public boolean renderOverlayDamaged;
   public boolean renderOverlayEyes;
   private boolean firstWorldLoad;
   private static int renderEntitiesCounter;

   public yy(nC mcIn) {
      this.renderEnv = new boa(Nk.AIR.getDefaultState(), new BlockPos(0, 0, 0));
      this.renderOverlayDamaged = false;
      this.renderOverlayEyes = false;
      this.firstWorldLoad = false;
      this.cloudRenderer = new bnX(mcIn);
      this.mc = mcIn;
      this.renderManager = mcIn.getRenderManager();
      this.renderEngine = mcIn.getTextureManager();
      this.renderEngine.bindTexture(FORCEFIELD_TEXTURES);
      yh.glTexParameteri(3553, 10242, 10497);
      yh.glTexParameteri(3553, 10243, 10497);
      yh.bindTexture(0);
      this.updateDestroyBlockIcons();
      this.vboEnabled = ys.useVbo();
      if (this.vboEnabled) {
         this.renderContainer = new zI();
         this.renderChunkFactory = new ui();
      } else {
         this.renderContainer = new yL();
         this.renderChunkFactory = new ud();
      }

      this.vertexBufferFormat = new zO();
      this.vertexBufferFormat.addElement(new zR(0, zP.FLOAT, zQ.POSITION, 3));
      this.generateStars();
      this.generateSky();
      this.generateSky2();
   }

   public void onResourceManagerReload(AA resourceManager) {
      this.updateDestroyBlockIcons();
   }

   private void updateDestroyBlockIcons() {
      zj texturemap = this.mc.getTextureMapBlocks();

      for(int i = 0; i < this.destroyBlockIcons.length; ++i) {
         this.destroyBlockIcons[i] = texturemap.getAtlasSprite("minecraft:blocks/destroy_stage_" + i);
      }

   }

   public void makeEntityOutlineShader() {
      if (ys.shadersSupported) {
         if (Bs.getStaticShaderLinkHelper() == null) {
            Bs.setNewStaticShaderLinkHelper();
         }

         ResourceLocation resourcelocation = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.entityOutlineShader = new Br(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), resourcelocation);
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
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ZERO, xR.ONE);
         this.entityOutlineFramebuffer.framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, false);
         yh.disableBlend();
      }

   }

   protected boolean isRenderEntityOutlines() {
      if (!XH.isFastRender() && !XH.isShaders() && !XH.isAntialiasing()) {
         boolean var1;
         if (this.entityOutlineFramebuffer != null && this.entityOutlineShader != null) {
            nC var10000 = this.mc;
            if (nC.player != null) {
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
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      if (this.sky2VBO != null) {
         this.sky2VBO.deleteGlBuffers();
      }

      if (this.glSkyList2 >= 0) {
         xE.deleteDisplayLists(this.glSkyList2);
         this.glSkyList2 = -1;
      }

      if (this.vboEnabled) {
         this.sky2VBO = new zM(this.vertexBufferFormat);
         this.renderSky(bufferbuilder, -16.0F, true);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.sky2VBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.glSkyList2 = xE.generateDisplayLists(1);
         yh.glNewList(this.glSkyList2, 4864);
         this.renderSky(bufferbuilder, -16.0F, true);
         tessellator.draw();
         yh.glEndList();
      }

   }

   private void generateSky() {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      if (this.skyVBO != null) {
         this.skyVBO.deleteGlBuffers();
      }

      if (this.glSkyList >= 0) {
         xE.deleteDisplayLists(this.glSkyList);
         this.glSkyList = -1;
      }

      if (this.vboEnabled) {
         this.skyVBO = new zM(this.vertexBufferFormat);
         this.renderSky(bufferbuilder, 16.0F, false);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.skyVBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.glSkyList = xE.generateDisplayLists(1);
         yh.glNewList(this.glSkyList, 4864);
         this.renderSky(bufferbuilder, 16.0F, false);
         tessellator.draw();
         yh.glEndList();
      }

   }

   private void renderSky(tN bufferBuilderIn, float posY, boolean reverseX) {
      int i = true;
      int j = true;
      bufferBuilderIn.begin(7, zK.POSITION);
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
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      if (this.starVBO != null) {
         this.starVBO.deleteGlBuffers();
      }

      if (this.starGLCallList >= 0) {
         xE.deleteDisplayLists(this.starGLCallList);
         this.starGLCallList = -1;
      }

      if (this.vboEnabled) {
         this.starVBO = new zM(this.vertexBufferFormat);
         this.renderStars(bufferbuilder);
         bufferbuilder.finishDrawing();
         bufferbuilder.reset();
         this.starVBO.bufferData(bufferbuilder.getByteBuffer());
      } else {
         this.starGLCallList = xE.generateDisplayLists(1);
         yh.pushMatrix();
         yh.glNewList(this.starGLCallList, 4864);
         this.renderStars(bufferbuilder);
         tessellator.draw();
         yh.glEndList();
         yh.popMatrix();
      }

   }

   private void renderStars(tN bufferBuilderIn) {
      Random random = new Random(10842L);
      bufferBuilderIn.begin(7, zK.POSITION);

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

   public void setWorldAndLoadRenderers(@Nullable pm worldClientIn) {
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
      if (XH.isDynamicLights()) {
         bjP.clear();
      }

      bnW.reset();
      this.worldChunkProvider = null;
      this.worldChunkProviderMap = null;
      this.renderEnv.reset((in)null, (BlockPos)null);
      bpq.checkWorldChanged(this.world);
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
            this.renderDispatcher = new tW();
         }

         this.displayListEntitiesDirty = true;
         Nk.LEAVES.setGraphicsLevel(XH.isTreesFancy());
         Nk.LEAVES2.setGraphicsLevel(XH.isTreesFancy());
         ty.updateAoLightValue();
         if (XH.isDynamicLights()) {
            bjP.clear();
         }

         bpW.update();
         nC var10001 = this.mc;
         this.renderDistanceChunks = nC.gameSettings.renderDistanceChunks;
         this.renderDistance = this.renderDistanceChunks * 16;
         this.renderDistanceSq = this.renderDistance * this.renderDistance;
         boolean flag = this.vboEnabled;
         this.vboEnabled = ys.useVbo();
         if (flag && !this.vboEnabled) {
            this.renderContainer = new yL();
            this.renderChunkFactory = new ud();
         } else if (!flag && this.vboEnabled) {
            this.renderContainer = new zI();
            this.renderChunkFactory = new ui();
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

         nC var10004 = this.mc;
         this.viewFrustum = new zT(this.world, nC.gameSettings.renderDistanceChunks, this, this.renderChunkFactory);
         if (this.world != null) {
            Ig entity = this.mc.getRenderViewEntity();
            if (entity != null) {
               this.viewFrustum.updateChunkPositions(entity.posX, entity.posZ);
            }
         }

         this.renderEntitiesStartupCounter = 2;
      }

      nC var10000 = this.mc;
      if (nC.player == null) {
         this.firstWorldLoad = true;
      }

   }

   protected void stopChunkUpdates() {
      this.chunksToUpdate.clear();
      this.renderDispatcher.stopChunkUpdates();
   }

   public void createBindEntityOutlineFbs(int width, int height) {
      if (ys.shadersSupported && this.entityOutlineShader != null) {
         this.entityOutlineShader.createBindFramebuffers(width, height);
      }

   }

   public void renderEntities(Ig renderViewEntity, uO camera, float partialTicks) {
      int i = 0;
      if (bnK.MinecraftForgeClient_getRenderPass.exists()) {
         i = bnK.callInt(bnK.MinecraftForgeClient_getRenderPass);
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
         zz.instance.prepare(this.world, this.mc.getTextureManager(), this.mc.fontRenderer, this.mc.getRenderViewEntity(), this.mc.objectMouseOver, partialTicks);
         wC var10000 = this.renderManager;
         pm var10001 = this.world;
         jH var10002 = this.mc.fontRenderer;
         Ig var10003 = this.mc.getRenderViewEntity();
         nC var10005 = this.mc;
         var10000.cacheActiveRenderInfo(var10001, var10002, var10003, this.mc.pointedEntity, nC.gameSettings, partialTicks);
         ++renderEntitiesCounter;
         if (i == 0) {
            this.countEntitiesTotal = 0;
            this.countEntitiesRendered = 0;
            this.countEntitiesHidden = 0;
            this.countTileEntitiesRendered = 0;
         }

         Ig entity = this.mc.getRenderViewEntity();
         double d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
         double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
         double d5 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
         zz.staticPlayerX = d3;
         zz.staticPlayerY = d4;
         zz.staticPlayerZ = d5;
         this.renderManager.setRenderPosition(d3, d4, d5);
         this.mc.entityRenderer.enableLightmap();
         this.world.profiler.endStartSection("global");
         List<Ig> list = this.world.getLoadedEntityList();
         if (i == 0) {
            this.countEntitiesTotal = list.size();
         }

         if (XH.isFogOff() && this.mc.entityRenderer.fogStandard) {
            yh.disableFog();
         }

         boolean flag = bnK.ForgeEntity_shouldRenderInPass.exists();
         boolean flag1 = bnK.ForgeTileEntity_shouldRenderInPass.exists();

         for(int j = 0; j < this.world.weatherEffects.size(); ++j) {
            Ig entity1 = (Ig)this.world.weatherEffects.get(j);
            if (!flag || bnK.callBoolean(entity1, bnK.ForgeEntity_shouldRenderInPass, i)) {
               ++this.countEntitiesRendered;
               if (entity1.isInRangeToRender3d(d0, d1, d2)) {
                  this.renderManager.renderEntityStatic(entity1, partialTicks, false);
               }
            }
         }

         this.world.profiler.endStartSection("entities");
         boolean flag5 = XH.isShaders();
         if (flag5) {
            bpq.beginEntities();
         }

         ArrayList list1;
         BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos;
         nC var38;
         boolean var39;
         ArrayList list2;
         label373: {
            wt.updateItemRenderDistance();
            list2 = Lists.newArrayList();
            list1 = Lists.newArrayList();
            blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
            if (bpq.isShadowPass) {
               var38 = this.mc;
               if (!nC.player.isSpectator()) {
                  var39 = true;
                  break label373;
               }
            }

            var39 = false;
         }

         boolean flag2 = var39;
         ArrayList<BlockPos> positions = new ArrayList();
         boolean module = 0bz.method_Qm().method_Qs().method_bxY(0bY.class).method_bBh();
         Iterator var28 = this.renderInfosEntities.iterator();

         label367:
         while(true) {
            ClassInheritanceMultiMap classinheritancemultimap;
            do {
               yx renderglobal$containerlocalrenderinformation1;
               if (!var28.hasNext()) {
                  blockpos$pooledmutableblockpos.release();
                  Ig entity5;
                  if (!list1.isEmpty()) {
                     var28 = list1.iterator();

                     label311:
                     while(true) {
                        do {
                           if (!var28.hasNext()) {
                              break label311;
                           }

                           entity5 = (Ig)var28.next();
                        } while(flag && !bnK.callBoolean(entity5, bnK.ForgeEntity_shouldRenderInPass, i));

                        if (flag5) {
                           bpq.nextEntity(entity5);
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
                        yh.depthFunc(519);
                        yh.disableFog();
                        this.entityOutlineFramebuffer.bindFramebuffer(false);
                        yz.disableStandardItemLighting();
                        this.renderManager.setRenderOutlines(true);

                        for(l = 0; l < list2.size(); ++l) {
                           entity5 = (Ig)list2.get(l);
                           if (!flag || bnK.callBoolean(entity5, bnK.ForgeEntity_shouldRenderInPass, i)) {
                              if (flag5) {
                                 bpq.nextEntity(entity5);
                              }

                              this.renderManager.renderEntityStatic(entity5, partialTicks, false);
                           }
                        }

                        this.renderManager.setRenderOutlines(false);
                        yz.enableStandardItemLighting();
                        yh.depthMask(false);
                        this.entityOutlineShader.render(partialTicks);
                        yh.enableLighting();
                        yh.depthMask(true);
                        yh.enableFog();
                        yh.enableBlend();
                        yh.enableColorMaterial();
                        yh.depthFunc(515);
                        yh.enableDepth();
                        yh.enableAlpha();
                     }

                     this.mc.getFramebuffer().bindFramebuffer(false);
                  }

                  if (!this.isRenderEntityOutlines() && (!list2.isEmpty() || this.entityOutlinesRendered)) {
                     this.world.profiler.endStartSection("entityOutlines");
                     this.entityOutlinesRendered = !list2.isEmpty();
                     if (!list2.isEmpty()) {
                        if (flag5) {
                           bpq.beginEntitiesGlowing();
                        }

                        yh.disableFog();
                        yh.disableDepth();
                        this.mc.entityRenderer.disableLightmap();
                        yz.disableStandardItemLighting();
                        this.renderManager.setRenderOutlines(true);
                        l = 0;

                        while(true) {
                           if (l >= list2.size()) {
                              this.renderManager.setRenderOutlines(false);
                              yz.enableStandardItemLighting();
                              this.mc.entityRenderer.enableLightmap();
                              yh.enableDepth();
                              yh.enableFog();
                              if (flag5) {
                                 bpq.endEntitiesGlowing();
                              }
                              break;
                           }

                           entity5 = (Ig)list2.get(l);
                           if (!flag || bnK.callBoolean(entity5, bnK.ForgeEntity_shouldRenderInPass, i)) {
                              if (flag5) {
                                 bpq.nextEntity(entity5);
                              }

                              this.renderManager.renderEntityStatic(entity5, partialTicks, false);
                           }

                           ++l;
                        }
                     }
                  }

                  if (flag5) {
                     bpq.endEntities();
                     bpq.beginBlockEntities();
                  }

                  this.world.profiler.endStartSection("blockentities");
                  yz.enableStandardItemLighting();
                  if (bnK.ForgeTileEntity_hasFastRenderer.exists()) {
                     zz.instance.preDrawBatch();
                  }

                  zC.updateTextRenderDistance();
                  var28 = this.renderInfosTileEntities.iterator();

                  label255:
                  while(true) {
                     List list3;
                     do {
                        if (!var28.hasNext()) {
                           synchronized(this.setTileEntities) {
                              Iterator var46 = this.setTileEntities.iterator();

                              while(true) {
                                 if (!var46.hasNext()) {
                                    break;
                                 }

                                 Yg tileentity = (Yg)var46.next();
                                 if (!flag1 || bnK.callBoolean(tileentity, bnK.ForgeTileEntity_shouldRenderInPass, i)) {
                                    if (flag5) {
                                       bpq.nextBlockEntity(tileentity);
                                    }

                                    zz.instance.render(tileentity, partialTicks, -1);
                                 }
                              }
                           }

                           if (bnK.ForgeTileEntity_hasFastRenderer.exists()) {
                              zz.instance.drawBatch(i);
                           }

                           this.renderOverlayDamaged = true;
                           this.preRenderDamagedBlocks();
                           var28 = this.damagedBlocks.values().iterator();

                           while(var28.hasNext()) {
                              va destroyblockprogress = (va)var28.next();
                              BlockPos blockpos = destroyblockprogress.getPosition();
                              if (this.world.getBlockState(blockpos).getBlock().hasTileEntity()) {
                                 Yg tileentity2 = this.world.getTileEntity(blockpos);
                                 if (tileentity2 instanceof Yn) {
                                    Yn tileentitychest = (Yn)tileentity2;
                                    if (tileentitychest.adjacentChestXNeg != null) {
                                       blockpos = blockpos.offset(EnumFacing.WEST);
                                       tileentity2 = this.world.getTileEntity(blockpos);
                                    } else if (tileentitychest.adjacentChestZNeg != null) {
                                       blockpos = blockpos.offset(EnumFacing.NORTH);
                                       tileentity2 = this.world.getTileEntity(blockpos);
                                    }
                                 }

                                 in iblockstate = this.world.getBlockState(blockpos);
                                 if (tileentity2 != null && iblockstate.hasCustomBreakingProgress()) {
                                    if (flag5) {
                                       bpq.nextBlockEntity(tileentity2);
                                    }

                                    zz.instance.render(tileentity2, partialTicks, destroyblockprogress.getPartialBlockDamage());
                                 }
                              }
                           }

                           this.postRenderDamagedBlocks();
                           this.renderOverlayDamaged = false;
                           if (flag5) {
                              bpq.endBlockEntities();
                           }

                           --renderEntitiesCounter;
                           this.mc.entityRenderer.disableLightmap();
                           this.mc.profiler.endSection();
                           return;
                        }

                        renderglobal$containerlocalrenderinformation1 = (yx)var28.next();
                        list3 = renderglobal$containerlocalrenderinformation1.renderChunk.getCompiledChunk().getTileEntities();
                     } while(list3.isEmpty());

                     Iterator iterator = list3.iterator();

                     while(true) {
                        Yg tileentity1;
                        while(true) {
                           if (!iterator.hasNext()) {
                              continue label255;
                           }

                           tileentity1 = (Yg)iterator.next();
                           if (!flag1) {
                              break;
                           }

                           if (bnK.callBoolean(tileentity1, bnK.ForgeTileEntity_shouldRenderInPass, i)) {
                              AxisAlignedBB axisalignedbb = (AxisAlignedBB)bnK.call(tileentity1, bnK.ForgeTileEntity_getRenderBoundingBox);
                              if (axisalignedbb == null || camera.isBoundingBoxInFrustum(axisalignedbb)) {
                                 break;
                              }
                           }
                        }

                        if (flag5) {
                           bpq.nextBlockEntity(tileentity1);
                        }

                        zz.instance.render(tileentity1, partialTicks, -1);
                        ++this.countTileEntitiesRendered;
                     }
                  }
               }

               renderglobal$containerlocalrenderinformation1 = (yx)var28.next();
               bam chunk = renderglobal$containerlocalrenderinformation1.renderChunk.getChunk();
               classinheritancemultimap = chunk.getEntityLists()[renderglobal$containerlocalrenderinformation1.renderChunk.getPosition().getY() / 16];
            } while(classinheritancemultimap.isEmpty());

            Iterator var32 = classinheritancemultimap.iterator();

            while(true) {
               Ig entity2;
               boolean flag4;
               do {
                  do {
                     boolean flag3;
                     do {
                        label335:
                        do {
                           do {
                              if (!var32.hasNext()) {
                                 continue label367;
                              }

                              entity2 = (Ig)var32.next();
                              if (!module) {
                                 continue label335;
                              }
                           } while((float)positions.stream().filter((blockPos) -> {
                              return blockPos.equals(entity2.getPosition());
                           }).count() >= 0bY.field_a.method_bnH());

                           positions.add(entity2.getPosition());
                        } while(flag && !bnK.callBoolean(entity2, bnK.ForgeEntity_shouldRenderInPass, i));

                        label339: {
                           if (!this.renderManager.shouldRender(entity2, camera, d0, d1, d2)) {
                              nC var40 = this.mc;
                              if (!entity2.isRidingOrBeingRiddenBy(nC.player)) {
                                 var39 = false;
                                 break label339;
                              }
                           }

                           var39 = true;
                        }

                        flag3 = var39;
                     } while(!flag3);

                     flag4 = this.mc.getRenderViewEntity() instanceof Iw ? ((Iw)this.mc.getRenderViewEntity()).isPlayerSleeping() : false;
                     if (entity2 != this.mc.getRenderViewEntity() || flag2) {
                        break;
                     }

                     var38 = this.mc;
                  } while(nC.gameSettings.thirdPersonView == 0 && !flag4);
               } while(!(entity2.posY < 0.0) && !(entity2.posY >= 256.0) && !this.world.isBlockLoaded(blockpos$pooledmutableblockpos.setPos(entity2)));

               ++this.countEntitiesRendered;
               this.renderedEntity = entity2;
               if (flag5) {
                  bpq.nextEntity(entity2);
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

   private boolean isOutlineActive(Ig entityIn, Ig viewer, uO camera) {
      boolean flag = viewer instanceof Iw && ((Iw)viewer).isPlayerSleeping();
      nC var10000;
      if (entityIn == viewer) {
         var10000 = this.mc;
         if (nC.gameSettings.thirdPersonView == 0 && !flag) {
            return false;
         }
      }

      if (entityIn.isGlowing()) {
         return true;
      } else {
         var10000 = this.mc;
         if (nC.player.isSpectator()) {
            var10000 = this.mc;
            if (nC.gameSettings.keyBindSpectatorOutlines.isKeyDown() && entityIn instanceof ME) {
               boolean var5;
               if (!entityIn.ignoreFrustumCheck && !camera.isBoundingBoxInFrustum(entityIn.getEntityBoundingBox())) {
                  nC var10001 = this.mc;
                  if (!entityIn.isRidingOrBeingRiddenBy(nC.player)) {
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
         yx renderglobal$containerlocalrenderinformation = (yx)var2.next();
         ub compiledchunk = renderglobal$containerlocalrenderinformation.renderChunk.compiledChunk;
         if (compiledchunk != ub.DUMMY && !compiledchunk.isEmpty()) {
            ++i;
         }
      }

      return i;
   }

   public String getDebugInfoEntities() {
      return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ", B: " + this.countEntitiesHidden + ", " + XH.getVersionDebug();
   }

   public void setupTerrain(Ig viewEntity, double partialTicks, uO camera, int frameCount, boolean playerSpectator) {
      nC var10000 = this.mc;
      if (nC.gameSettings.renderDistanceChunks != this.renderDistanceChunks) {
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

      if (XH.isDynamicLights()) {
         bjP.update(this);
      }

      this.world.profiler.endStartSection("renderlistcamera");
      double d3 = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
      double d4 = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
      double d5 = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
      this.renderContainer.initialize(d3, d4, d5);
      this.world.profiler.endStartSection("cull");
      if (this.debugFixedClippingHelper != null) {
         uN frustum = new uN(this.debugFixedClippingHelper);
         frustum.setPosition(this.debugTerrainFrustumPosition.x, this.debugTerrainFrustumPosition.y, this.debugTerrainFrustumPosition.z);
         camera = frustum;
      }

      this.mc.profiler.endStartSection("culling");
      BlockPos blockpos = new BlockPos(d3, d4 + (double)viewEntity.getEyeHeight(), d5);
      ug renderchunk = this.viewFrustum.getRenderChunk(blockpos);
      new BlockPos(MathHelper.floor(d3 / 16.0) * 16, MathHelper.floor(d4 / 16.0) * 16, MathHelper.floor(d5 / 16.0) * 16);
      this.displayListEntitiesDirty = this.displayListEntitiesDirty || !this.chunksToUpdate.isEmpty() || viewEntity.posX != this.lastViewEntityX || viewEntity.posY != this.lastViewEntityY || viewEntity.posZ != this.lastViewEntityZ || (double)viewEntity.rotationPitch != this.lastViewEntityPitch || (double)viewEntity.rotationYaw != this.lastViewEntityYaw;
      this.lastViewEntityX = viewEntity.posX;
      this.lastViewEntityY = viewEntity.posY;
      this.lastViewEntityZ = viewEntity.posZ;
      this.lastViewEntityPitch = (double)viewEntity.rotationPitch;
      this.lastViewEntityYaw = (double)viewEntity.rotationYaw;
      boolean flag = this.debugFixedClippingHelper != null;
      this.mc.profiler.endStartSection("update");
      bmV.timerVisibility.start();
      int i = this.getCountLoadedChunks();
      if (i != this.countLoadedChunksPrev) {
         this.countLoadedChunksPrev = i;
         this.displayListEntitiesDirty = true;
      }

      int j = 256;
      if (!bnW.isFinished()) {
         this.displayListEntitiesDirty = true;
      }

      if (!flag && this.displayListEntitiesDirty && XH.isIntegratedServerRunning()) {
         j = bnW.getMaxChunkY(this.world, viewEntity, this.renderDistanceChunks);
      }

      ug renderchunk1 = this.viewFrustum.getRenderChunk(new BlockPos(viewEntity.posX, viewEntity.posY, viewEntity.posZ));
      yx renderglobal$containerlocalrenderinformation;
      if (bpq.isShadowPass) {
         this.renderInfos = this.renderInfosShadow;
         this.renderInfosEntities = this.renderInfosEntitiesShadow;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
         if (!flag && this.displayListEntitiesDirty) {
            this.clearRenderInfos();
            if (renderchunk1 != null && renderchunk1.getPosition().getY() > j) {
               this.renderInfosEntities.add(renderchunk1.getRenderInfo());
            }

            Iterator<ug> iterator = bpu.makeShadowChunkIterator(this.world, partialTicks, viewEntity, this.renderDistanceChunks, this.viewFrustum);

            while(iterator.hasNext()) {
               ug renderchunk2 = (ug)iterator.next();
               if (renderchunk2 != null && renderchunk2.getPosition().getY() <= j) {
                  renderglobal$containerlocalrenderinformation = renderchunk2.getRenderInfo();
                  if (!renderchunk2.compiledChunk.isEmpty()) {
                     this.renderInfos.add(renderglobal$containerlocalrenderinformation);
                  }

                  if (bqk.hasEntities(renderchunk2.getChunk())) {
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

      if (!flag && this.displayListEntitiesDirty && !bpq.isShadowPass) {
         this.displayListEntitiesDirty = false;
         this.clearRenderInfos();
         this.visibilityDeque.clear();
         Deque deque = this.visibilityDeque;
         var10000 = this.mc;
         Ig.setRenderDistanceWeight(MathHelper.clamp((double)nC.gameSettings.renderDistanceChunks / 8.0, 1.0, 2.5));
         boolean flag1 = this.mc.renderChunksMany;
         boolean flag3;
         yx renderglobal$containerlocalrenderinformation5;
         if (renderchunk != null && renderchunk.getPosition().getY() <= j) {
            flag3 = false;
            renderglobal$containerlocalrenderinformation5 = new yx(renderchunk, (EnumFacing)null, 0);
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
                  ug renderchunk3 = this.viewFrustum.getRenderChunk(new BlockPos((k << 4) + 8, j1, (l << 4) + 8));
                  if (renderchunk3 != null && renderchunk3.isBoundingBoxInFrustum((uO)camera, frameCount)) {
                     renderchunk3.setFrameIndex(frameCount);
                     yx renderglobal$containerlocalrenderinformation1 = renderchunk3.getRenderInfo();
                     yx.access$000(renderglobal$containerlocalrenderinformation1, (EnumFacing)null, 0);
                     deque.add(renderglobal$containerlocalrenderinformation1);
                  }
               }
            }
         }

         this.mc.profiler.startSection("iteration");
         flag3 = XH.isFogOn();

         while(!deque.isEmpty()) {
            renderglobal$containerlocalrenderinformation5 = (yx)deque.poll();
            ug renderchunk6 = renderglobal$containerlocalrenderinformation5.renderChunk;
            EnumFacing enumfacing1 = renderglobal$containerlocalrenderinformation5.facing;
            ub compiledchunk = renderchunk6.compiledChunk;
            if (!compiledchunk.isEmpty() || renderchunk6.needsUpdate()) {
               this.renderInfos.add(renderglobal$containerlocalrenderinformation5);
            }

            if (bqk.hasEntities(renderchunk6.getChunk())) {
               this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation5);
            }

            if (compiledchunk.getTileEntities().size() > 0) {
               this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation5);
            }

            EnumFacing[] var32 = flag1 ? bnW.getFacingsNotOpposite(renderglobal$containerlocalrenderinformation5.setFacing) : EnumFacing.VALUES;
            int var33 = var32.length;

            for(int var34 = 0; var34 < var33; ++var34) {
               EnumFacing enumfacing = var32[var34];
               if (!flag1 || enumfacing1 == null || compiledchunk.isVisible(enumfacing1.getOpposite(), enumfacing)) {
                  ug renderchunk4 = this.getRenderChunkOffset(blockpos, renderchunk6, enumfacing, flag3, j);
                  if (renderchunk4 != null && renderchunk4.setFrameIndex(frameCount) && renderchunk4.isBoundingBoxInFrustum((uO)camera, frameCount)) {
                     int i1 = renderglobal$containerlocalrenderinformation5.setFacing | 1 << enumfacing.ordinal();
                     yx renderglobal$containerlocalrenderinformation2 = renderchunk4.getRenderInfo();
                     yx.access$000(renderglobal$containerlocalrenderinformation2, enumfacing, i1);
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

      bmV.timerVisibility.end();
      if (bpq.isShadowPass) {
         bpq.mcProfilerEndSection();
      } else {
         this.mc.profiler.endStartSection("rebuildNear");
         Set<ug> set = this.chunksToUpdate;
         this.chunksToUpdate = this.chunksToUpdatePrev;
         this.chunksToUpdatePrev = set;
         this.chunksToUpdate.clear();
         bmV.timerChunkUpdate.start();
         Iterator var44 = this.renderInfos.iterator();

         while(true) {
            ug renderchunk5;
            do {
               if (!var44.hasNext()) {
                  bmV.timerChunkUpdate.end();
                  this.chunksToUpdate.addAll(set);
                  this.mc.profiler.endSection();
                  return;
               }

               renderglobal$containerlocalrenderinformation = (yx)var44.next();
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
      uk visgraph = new uk();
      BlockPos blockpos = new BlockPos(pos.getX() >> 4 << 4, pos.getY() >> 4 << 4, pos.getZ() >> 4 << 4);
      bam chunk = this.world.getChunk(blockpos);
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
   private ug getRenderChunkOffset(BlockPos p_getRenderChunkOffset_1_, ug p_getRenderChunkOffset_2_, EnumFacing p_getRenderChunkOffset_3_, boolean p_getRenderChunkOffset_4_, int p_getRenderChunkOffset_5_) {
      ug renderchunk = p_getRenderChunkOffset_2_.getRenderChunkNeighbour(p_getRenderChunkOffset_3_);
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
      this.debugFixedClippingHelper = new uM();
      ((uM)this.debugFixedClippingHelper).init();
      yp matrix4f = new yp(this.debugFixedClippingHelper.modelviewMatrix);
      matrix4f.transpose();
      yp matrix4f1 = new yp(this.debugFixedClippingHelper.projectionMatrix);
      matrix4f1.transpose();
      yp matrix4f2 = new yp();
      yp.mul(matrix4f1, matrix4f, matrix4f2);
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
         yp.transform(matrix4f2, this.debugTerrainMatrix[i], this.debugTerrainMatrix[i]);
         Vector4f var10000 = this.debugTerrainMatrix[i];
         var10000.x /= this.debugTerrainMatrix[i].w;
         var10000 = this.debugTerrainMatrix[i];
         var10000.y /= this.debugTerrainMatrix[i].w;
         var10000 = this.debugTerrainMatrix[i];
         var10000.z /= this.debugTerrainMatrix[i].w;
         this.debugTerrainMatrix[i].w = 1.0F;
      }

   }

   protected Vector3f getViewVector(Ig entityIn, double partialTicks) {
      float f = (float)((double)entityIn.prevRotationPitch + (double)(entityIn.rotationPitch - entityIn.prevRotationPitch) * partialTicks);
      float f1 = (float)((double)entityIn.prevRotationYaw + (double)(entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks);
      nC.getMinecraft();
      if (nC.gameSettings.thirdPersonView == 2) {
         f += 180.0F;
      }

      float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
      float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
      float f4 = -MathHelper.cos(-f * 0.017453292F);
      float f5 = MathHelper.sin(-f * 0.017453292F);
      return new Vector3f(f3 * f4, f5, f2 * f4);
   }

   public int renderBlockLayer(BlockRenderLayer blockLayerIn, double partialTicks, int pass, Ig entityIn) {
      yz.disableStandardItemLighting();
      if (blockLayerIn == BlockRenderLayer.TRANSLUCENT && !bpq.isShadowPass) {
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
               yx renderglobal$containerlocalrenderinformation = (yx)var13.next();
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
         ug renderchunk = ((yx)this.renderInfos.get(j)).renderChunk;
         if (!renderchunk.getCompiledChunk().isLayerEmpty(blockLayerIn)) {
            ++l;
            this.renderContainer.addRenderChunk(renderchunk, blockLayerIn);
         }
      }

      if (l == 0) {
         this.mc.profiler.endSection();
         return l;
      } else {
         if (XH.isFogOff() && this.mc.entityRenderer.fogStandard) {
            yh.disableFog();
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
      if (ys.useVbo()) {
         yh.glEnableClientState(32884);
         ys.setClientActiveTexture(ys.defaultTexUnit);
         yh.glEnableClientState(32888);
         ys.setClientActiveTexture(ys.lightmapTexUnit);
         yh.glEnableClientState(32888);
         ys.setClientActiveTexture(ys.defaultTexUnit);
         yh.glEnableClientState(32886);
      }

      if (XH.isShaders()) {
         bpr.preRenderChunkLayer(blockLayerIn);
      }

      this.renderContainer.renderChunkLayer(blockLayerIn);
      if (XH.isShaders()) {
         bpr.postRenderChunkLayer(blockLayerIn);
      }

      if (ys.useVbo()) {
         Iterator var2 = zK.BLOCK.getElements().iterator();

         while(var2.hasNext()) {
            zR vertexformatelement = (zR)var2.next();
            zQ vertexformatelement$enumusage = vertexformatelement.getUsage();
            int k1 = vertexformatelement.getIndex();
            switch (vertexformatelement$enumusage) {
               case POSITION:
                  yh.glDisableClientState(32884);
                  break;
               case UV:
                  ys.setClientActiveTexture(ys.defaultTexUnit + k1);
                  yh.glDisableClientState(32888);
                  ys.setClientActiveTexture(ys.defaultTexUnit);
                  break;
               case COLOR:
                  yh.glDisableClientState(32886);
                  yh.resetColor();
            }
         }
      }

      this.mc.entityRenderer.disableLightmap();
   }

   private void cleanupDamagedBlocks(Iterator<va> iteratorIn) {
      while(iteratorIn.hasNext()) {
         va destroyblockprogress = (va)iteratorIn.next();
         int k1 = destroyblockprogress.getCreationCloudUpdateTick();
         if (this.cloudTickCounter - k1 > 400) {
            iteratorIn.remove();
         }
      }

   }

   public void updateClouds() {
      if (XH.isShaders()) {
         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(24)) {
            boS guishaderoptions = new boS((lg)null, XH.getGameSettings());
            XH.getMinecraft().displayGuiScreen(guishaderoptions);
         }

         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
            bpq.uninit();
            bpq.loadShaderPack();
            bnK.Minecraft_actionKeyF3.setValue(this.mc, Boolean.TRUE);
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
      if (XH.isSkyEnabled()) {
         yh.disableFog();
         yh.disableAlpha();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yz.disableStandardItemLighting();
         yh.depthMask(false);
         this.renderEngine.bindTexture(END_SKY_TEXTURES);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();

         for(int k1 = 0; k1 < 6; ++k1) {
            yh.pushMatrix();
            if (k1 == 1) {
               yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 2) {
               yh.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 3) {
               yh.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (k1 == 4) {
               yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (k1 == 5) {
               yh.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
            int l1 = 40;
            int i2 = 40;
            int j2 = 40;
            if (XH.isCustomColors()) {
               Vec3d vec3d = new Vec3d((double)l1 / 255.0, (double)i2 / 255.0, (double)j2 / 255.0);
               vec3d = bjy.getWorldSkyColor(vec3d, this.world, this.mc.getRenderViewEntity(), 0.0F);
               l1 = (int)(vec3d.x * 255.0);
               i2 = (int)(vec3d.y * 255.0);
               j2 = (int)(vec3d.z * 255.0);
            }

            bufferbuilder.pos(-100.0, -100.0, -100.0).tex(0.0, 0.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(-100.0, -100.0, 100.0).tex(0.0, 16.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(100.0, -100.0, 100.0).tex(16.0, 16.0).color(l1, i2, j2, 255).endVertex();
            bufferbuilder.pos(100.0, -100.0, -100.0).tex(16.0, 0.0).color(l1, i2, j2, 255).endVertex();
            tessellator.draw();
            yh.popMatrix();
         }

         yh.depthMask(true);
         yh.enableTexture2D();
         yh.enableAlpha();
         yh.disableBlend();
      }

   }

   public void renderSky(float partialTicks, int pass) {
      if (bnK.ForgeWorldProvider_getSkyRenderer.exists()) {
         bil worldprovider = this.mc.world.provider;
         Object object = bnK.call(worldprovider, bnK.ForgeWorldProvider_getSkyRenderer);
         if (object != null) {
            bnK.callVoid(object, bnK.IRenderHandler_render, partialTicks, this.world, this.mc);
            return;
         }
      }

      if (this.mc.world.provider.getDimensionType() == baM.THE_END) {
         this.renderSkyEnd();
      } else if (this.mc.world.provider.isSurfaceWorld()) {
         yh.disableTexture2D();
         boolean flag1 = XH.isShaders();
         if (flag1) {
            bpq.disableTexture2D();
         }

         Vec3d vec3d = this.world.getSkyColor(this.mc.getRenderViewEntity(), partialTicks);
         vec3d = bjy.getSkyColor(vec3d, this.mc.world, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ);
         if (flag1) {
            bpq.setSkyColor(vec3d);
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

         yh.color(f, f1, f2);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         yh.depthMask(false);
         yh.enableFog();
         if (flag1) {
            bpq.enableFog();
         }

         yh.color(f, f1, f2);
         if (flag1) {
            bpq.preSkyList();
         }

         if (XH.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.skyVBO.bindBuffer();
               yh.glEnableClientState(32884);
               yh.glVertexPointer(3, 5126, 12, 0);
               this.skyVBO.drawArrays(7);
               this.skyVBO.unbindBuffer();
               yh.glDisableClientState(32884);
            } else {
               yh.callList(this.glSkyList);
            }
         }

         yh.disableFog();
         if (flag1) {
            bpq.disableFog();
         }

         yh.disableAlpha();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yz.disableStandardItemLighting();
         float[] afloat = this.world.provider.calcSunriseSunsetColors(this.world.getCelestialAngle(partialTicks), partialTicks);
         float f15;
         float f16;
         float f17;
         float f20;
         float f22;
         float f24;
         int k2;
         if (afloat != null && XH.isSunMoonEnabled()) {
            yh.disableTexture2D();
            if (flag1) {
               bpq.disableTexture2D();
            }

            yh.shadeModel(7425);
            yh.pushMatrix();
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(MathHelper.sin(this.world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
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

            bufferbuilder.begin(6, zK.POSITION_COLOR);
            bufferbuilder.pos(0.0, 100.0, 0.0).color(f15, f16, f17, afloat[3]).endVertex();
            int l1 = true;

            for(k2 = 0; k2 <= 16; ++k2) {
               f20 = (float)k2 * 6.2831855F / 16.0F;
               f22 = MathHelper.sin(f20);
               f24 = MathHelper.cos(f20);
               bufferbuilder.pos((double)(f22 * 120.0F), (double)(f24 * 120.0F), (double)(-f24 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            tessellator.draw();
            yh.popMatrix();
            yh.shadeModel(7424);
         }

         yh.enableTexture2D();
         if (flag1) {
            bpq.enableTexture2D();
         }

         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE, ya.ONE, xR.ZERO);
         yh.pushMatrix();
         f15 = 1.0F - this.world.getRainStrength(partialTicks);
         yh.color(1.0F, 1.0F, 1.0F, f15);
         yh.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         bjM.renderSky(this.world, this.renderEngine, partialTicks);
         if (flag1) {
            bpq.preCelestialRotate();
         }

         yh.rotate(this.world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
         if (flag1) {
            bpq.postCelestialRotate();
         }

         f16 = 30.0F;
         if (XH.isSunTexture()) {
            this.renderEngine.bindTexture(SUN_TEXTURES);
            bufferbuilder.begin(7, zK.POSITION_TEX);
            bufferbuilder.pos((double)(-f16), 100.0, (double)(-f16)).tex(0.0, 0.0).endVertex();
            bufferbuilder.pos((double)f16, 100.0, (double)(-f16)).tex(1.0, 0.0).endVertex();
            bufferbuilder.pos((double)f16, 100.0, (double)f16).tex(1.0, 1.0).endVertex();
            bufferbuilder.pos((double)(-f16), 100.0, (double)f16).tex(0.0, 1.0).endVertex();
            tessellator.draw();
         }

         f16 = 20.0F;
         if (XH.isMoonTexture()) {
            this.renderEngine.bindTexture(MOON_PHASES_TEXTURES);
            int k1 = this.world.getMoonPhase();
            int i2 = k1 % 4;
            k2 = k1 / 4 % 2;
            f20 = (float)(i2 + 0) / 4.0F;
            f22 = (float)(k2 + 0) / 2.0F;
            f24 = (float)(i2 + 1) / 4.0F;
            float f14 = (float)(k2 + 1) / 2.0F;
            bufferbuilder.begin(7, zK.POSITION_TEX);
            bufferbuilder.pos((double)(-f16), -100.0, (double)f16).tex((double)f24, (double)f14).endVertex();
            bufferbuilder.pos((double)f16, -100.0, (double)f16).tex((double)f20, (double)f14).endVertex();
            bufferbuilder.pos((double)f16, -100.0, (double)(-f16)).tex((double)f20, (double)f22).endVertex();
            bufferbuilder.pos((double)(-f16), -100.0, (double)(-f16)).tex((double)f24, (double)f22).endVertex();
            tessellator.draw();
         }

         yh.disableTexture2D();
         if (flag1) {
            bpq.disableTexture2D();
         }

         f17 = this.world.getStarBrightness(partialTicks) * f15;
         if (f17 > 0.0F && XH.isStarsEnabled() && !bjM.hasSkyLayers(this.world)) {
            yh.color(f17, f17, f17, f17);
            if (this.vboEnabled) {
               this.starVBO.bindBuffer();
               yh.glEnableClientState(32884);
               yh.glVertexPointer(3, 5126, 12, 0);
               this.starVBO.drawArrays(7);
               this.starVBO.unbindBuffer();
               yh.glDisableClientState(32884);
            } else {
               yh.callList(this.starGLCallList);
            }
         }

         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableBlend();
         yh.enableAlpha();
         yh.enableFog();
         if (flag1) {
            bpq.enableFog();
         }

         yh.popMatrix();
         yh.disableTexture2D();
         if (flag1) {
            bpq.disableTexture2D();
         }

         yh.color(0.0F, 0.0F, 0.0F);
         nC var10000 = this.mc;
         double d3 = nC.player.getPositionEyes(partialTicks).y - this.world.getHorizon();
         if (d3 < 0.0) {
            yh.pushMatrix();
            yh.translate(0.0F, 12.0F, 0.0F);
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               yh.glEnableClientState(32884);
               yh.glVertexPointer(3, 5126, 12, 0);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               yh.glDisableClientState(32884);
            } else {
               yh.callList(this.glSkyList2);
            }

            yh.popMatrix();
            f20 = 1.0F;
            f22 = -((float)(d3 + 65.0));
            f24 = -1.0F;
            bufferbuilder.begin(7, zK.POSITION_COLOR);
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
            yh.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
         } else {
            yh.color(f, f1, f2);
         }

         var10000 = this.mc;
         if (nC.gameSettings.renderDistanceChunks <= 4) {
            yh.color(this.mc.entityRenderer.fogColorRed, this.mc.entityRenderer.fogColorGreen, this.mc.entityRenderer.fogColorBlue);
         }

         yh.pushMatrix();
         yh.translate(0.0F, -((float)(d3 - 16.0)), 0.0F);
         if (XH.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               yh.glEnableClientState(32884);
               yh.glVertexPointer(3, 5126, 12, 0);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               yh.glDisableClientState(32884);
            } else {
               yh.callList(this.glSkyList2);
            }
         }

         yh.popMatrix();
         yh.enableTexture2D();
         if (flag1) {
            bpq.enableTexture2D();
         }

         yh.depthMask(true);
      }

   }

   public void renderClouds(float partialTicks, int pass, double x, double y, double z) {
      if (!XH.isCloudsOff()) {
         if (bnK.ForgeWorldProvider_getCloudRenderer.exists()) {
            bil worldprovider = this.mc.world.provider;
            Object object = bnK.call(worldprovider, bnK.ForgeWorldProvider_getCloudRenderer);
            if (object != null) {
               bnK.callVoid(object, bnK.IRenderHandler_render, partialTicks, this.world, this.mc);
               return;
            }
         }

         if (this.mc.world.provider.isSurfaceWorld()) {
            if (XH.isShaders()) {
               bpq.beginClouds();
            }

            if (XH.isCloudsFancy()) {
               this.renderCloudsFancy(partialTicks, pass, x, y, z);
            } else {
               float f9 = partialTicks;
               partialTicks = 0.0F;
               yh.disableCull();
               int l2 = true;
               int k1 = true;
               yN tessellator = yN.getInstance();
               tN bufferbuilder = tessellator.getBuffer();
               this.renderEngine.bindTexture(CLOUDS_TEXTURES);
               yh.enableBlend();
               yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
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
                  nC var10001 = this.mc;
                  f6 += nC.gameSettings.ofCloudsHeight * 128.0F;
                  float f7 = (float)(d3 * 4.8828125E-4);
                  float f8 = (float)(d4 * 4.8828125E-4);
                  bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);

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
               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               yh.disableBlend();
               yh.enableCull();
            }

            if (XH.isShaders()) {
               bpq.endClouds();
            }
         }
      }

   }

   public boolean hasCloudFog(double x, double y, double z, float partialTicks) {
      return false;
   }

   private void renderCloudsFancy(float partialTicks, int pass, double x, double y, double z) {
      float f251 = 0.0F;
      yh.disableCull();
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      float f = 12.0F;
      float f1 = 4.0F;
      double d3 = (double)((float)this.cloudTickCounter + f251);
      double d4 = (x + d3 * 0.029999999329447746) / 12.0;
      double d5 = z / 12.0 + 0.33000001311302185;
      float f2 = this.world.provider.getCloudHeight() - (float)y + 0.33F;
      nC var10001 = this.mc;
      f2 += nC.gameSettings.ofCloudsHeight * 128.0F;
      int k1 = MathHelper.floor(d4 / 2048.0);
      int l1 = MathHelper.floor(d5 / 2048.0);
      d4 -= (double)(k1 * 2048);
      d5 -= (double)(l1 * 2048);
      this.renderEngine.bindTexture(CLOUDS_TEXTURES);
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
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
      yh.scale(12.0F, 1.0F, 12.0F);

      int j3;
      for(j3 = 0; j3 < 2; ++j3) {
         if (j3 == 0) {
            yh.colorMask(false, false, false, false);
         } else {
            switch (pass) {
               case 0:
                  yh.colorMask(false, true, true, true);
                  break;
               case 1:
                  yh.colorMask(true, false, false, true);
                  break;
               case 2:
                  yh.colorMask(true, true, true, true);
            }
         }

         this.cloudRenderer.renderGlList();
      }

      if (this.cloudRenderer.shouldUpdateGlList()) {
         this.cloudRenderer.startUpdateGlList();

         for(j3 = -3; j3 <= 4; ++j3) {
            for(int l2 = -3; l2 <= 4; ++l2) {
               bufferbuilder.begin(7, zK.POSITION_TEX_COLOR_NORMAL);
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

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableBlend();
      yh.enableCull();
   }

   public void updateChunks(long finishTimeNano) {
      finishTimeNano = (long)((double)finishTimeNano + 1.0E8);
      this.displayListEntitiesDirty |= this.renderDispatcher.runChunkUploads(finishTimeNano);
      Iterator iterator2;
      ug renderchunk3;
      if (this.chunksToUpdateForced.size() > 0) {
         iterator2 = this.chunksToUpdateForced.iterator();

         while(iterator2.hasNext()) {
            renderchunk3 = (ug)iterator2.next();
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
            renderchunk3 = (ug)iterator2.next();
            if (this.renderDispatcher.updateTransparencyLater(renderchunk3)) {
               iterator2.remove();
            }
         }
      }

      double d4 = 0.0;
      int k1 = XH.getUpdatesPerFrame();
      if (!this.chunksToUpdate.isEmpty()) {
         Iterator<ug> iterator1 = this.chunksToUpdate.iterator();

         while(iterator1.hasNext()) {
            ug renderchunk2 = (ug)iterator1.next();
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
               double d3 = 2.0 * bqM.getRelativeBufferSize(renderchunk2);
               d4 += d3;
               if (d4 > (double)k1) {
                  break;
               }
            }
         }
      }

   }

   public void renderWorldBorder(Ig entityIn, float partialTicks) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bab worldborder = this.world.getWorldBorder();
      nC var10000 = this.mc;
      double d3 = (double)(nC.gameSettings.renderDistanceChunks * 16);
      if (entityIn.posX >= worldborder.maxX() - d3 || entityIn.posX <= worldborder.minX() + d3 || entityIn.posZ >= worldborder.maxZ() - d3 || entityIn.posZ <= worldborder.minZ() + d3) {
         if (XH.isShaders()) {
            bpq.pushProgram();
            bpq.useProgram(bpq.ProgramTexturedLit);
         }

         double d4 = 1.0 - worldborder.getClosestDistance(entityIn) / d3;
         d4 = Math.pow(d4, 4.0);
         double d5 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
         double d6 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
         double d7 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE, ya.ONE, xR.ZERO);
         this.renderEngine.bindTexture(FORCEFIELD_TEXTURES);
         yh.depthMask(false);
         yh.pushMatrix();
         int k1 = worldborder.getStatus().getColor();
         float f = (float)(k1 >> 16 & 255) / 255.0F;
         float f1 = (float)(k1 >> 8 & 255) / 255.0F;
         float f2 = (float)(k1 & 255) / 255.0F;
         yh.color(f, f1, f2, (float)d4);
         yh.doPolygonOffset(-3.0F, -3.0F);
         yh.enablePolygonOffset();
         yh.alphaFunc(516, 0.1F);
         yh.enableAlpha();
         yh.disableCull();
         float f3 = (float)(nC.getSystemTime() % 3000L) / 3000.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 128.0F;
         bufferbuilder.begin(7, zK.POSITION_TEX);
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
         yh.enableCull();
         yh.disableAlpha();
         yh.doPolygonOffset(0.0F, 0.0F);
         yh.disablePolygonOffset();
         yh.enableAlpha();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.disableBlend();
         yh.popMatrix();
         yh.depthMask(true);
         if (XH.isShaders()) {
            bpq.popProgram();
         }
      }

   }

   private void preRenderDamagedBlocks() {
      yh.tryBlendFuncSeparate(ya.DST_COLOR, xR.SRC_COLOR, ya.ONE, xR.ZERO);
      yh.enableBlend();
      yh.color(1.0F, 1.0F, 1.0F, 0.5F);
      yh.doPolygonOffset(-1.0F, -10.0F);
      yh.enablePolygonOffset();
      yh.alphaFunc(516, 0.1F);
      yh.enableAlpha();
      yh.pushMatrix();
      if (XH.isShaders()) {
         bpr.beginBlockDamage();
      }

   }

   private void postRenderDamagedBlocks() {
      yh.disableAlpha();
      yh.doPolygonOffset(0.0F, 0.0F);
      yh.disablePolygonOffset();
      yh.enableAlpha();
      yh.depthMask(true);
      yh.popMatrix();
      if (XH.isShaders()) {
         bpr.endBlockDamage();
      }

   }

   public void drawBlockDamageTexture(yN tessellatorIn, tN bufferBuilderIn, Ig entityIn, float partialTicks) {
      double d3 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
      double d4 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
      double d5 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
      if (!this.damagedBlocks.isEmpty()) {
         this.renderEngine.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         this.preRenderDamagedBlocks();
         bufferBuilderIn.begin(7, zK.BLOCK);
         bufferBuilderIn.setTranslation(-d3, -d4, -d5);
         bufferBuilderIn.noColor();
         Iterator<va> iterator = this.damagedBlocks.values().iterator();

         while(iterator.hasNext()) {
            va destroyblockprogress = (va)iterator.next();
            BlockPos blockpos = destroyblockprogress.getPosition();
            double d6 = (double)blockpos.getX() - d3;
            double d7 = (double)blockpos.getY() - d4;
            double d8 = (double)blockpos.getZ() - d5;
            co block = this.world.getBlockState(blockpos).getBlock();
            boolean flag1;
            if (bnK.ForgeTileEntity_canRenderBreaking.exists()) {
               boolean flag2 = block instanceof cT || block instanceof dA || block instanceof gs || block instanceof gE;
               if (!flag2) {
                  Yg tileentity = this.world.getTileEntity(blockpos);
                  if (tileentity != null) {
                     flag2 = bnK.callBoolean(tileentity, bnK.ForgeTileEntity_canRenderBreaking);
                  }
               }

               flag1 = !flag2;
            } else {
               flag1 = !(block instanceof cT) && !(block instanceof dA) && !(block instanceof gs) && !(block instanceof gE);
            }

            if (flag1) {
               if (d6 * d6 + d7 * d7 + d8 * d8 > 1024.0) {
                  iterator.remove();
               } else {
                  in iblockstate = this.world.getBlockState(blockpos);
                  if (iblockstate.getMaterial() != hM.AIR) {
                     int k1 = destroyblockprogress.getPartialBlockDamage();
                     zd textureatlassprite = this.destroyBlockIcons[k1];
                     tJ blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
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

   public void drawSelectionBox(ME player, RayTraceResult movingObjectPositionIn, int execute, float partialTicks) {
      if (execute == 0 && movingObjectPositionIn.typeOfHit == RayTraceResult.Type.BLOCK) {
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.glLineWidth(2.0F);
         yh.disableTexture2D();
         if (XH.isShaders()) {
            bpq.disableTexture2D();
         }

         yh.depthMask(false);
         BlockPos blockpos = movingObjectPositionIn.getBlockPos();
         in iblockstate = this.world.getBlockState(blockpos);
         if (iblockstate.getMaterial() != hM.AIR && this.world.getWorldBorder().contains(blockpos)) {
            double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
            double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
            double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
            drawSelectionBoundingBox(iblockstate.getSelectedBoundingBox(this.world, blockpos).grow(0.0020000000949949026).offset(-d3, -d4, -d5), 0.0F, 0.0F, 0.0F, 0.4F);
         }

         yh.depthMask(true);
         yh.enableTexture2D();
         if (XH.isShaders()) {
            bpq.enableTexture2D();
         }

         yh.disableBlend();
      }

   }

   public static void drawSelectionBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha) {
      drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
   }

   public static void drawBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(3, zK.POSITION_COLOR);
      drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
      tessellator.draw();
   }

   public static void drawBoundingBox(tN buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
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
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(5, zK.POSITION_COLOR);
      addChainedFilledBoxVertices(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
      tessellator.draw();
   }

   public static void addChainedFilledBoxVertices(tN builder, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
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

   public void notifyBlockUpdate(bij worldIn, BlockPos pos, in oldState, in newState, int flags) {
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
      iC isound = (iC)this.mapSoundPositions.get(pos);
      if (isound != null) {
         this.mc.getSoundHandler().stopSound(isound);
         this.mapSoundPositions.remove(pos);
      }

      if (soundIn != null) {
         Qf itemrecord = Qf.getBySound(soundIn);
         if (itemrecord != null) {
            this.mc.ingameGUI.setRecordPlayingMessage(itemrecord.getRecordNameLocal());
         }

         iC isound1 = iN.getRecordSoundRecord(soundIn, (float)pos.getX(), (float)pos.getY(), (float)pos.getZ());
         this.mapSoundPositions.put(pos, isound1);
         this.mc.getSoundHandler().playSound(isound1);
      }

      this.setPartying(this.world, pos, soundIn != null);
   }

   private void setPartying(bij worldIn, BlockPos pos, boolean isPartying) {
      Iterator var4 = worldIn.getEntitiesWithinAABB(Iw.class, (new AxisAlignedBB(pos)).grow(3.0)).iterator();

      while(var4.hasNext()) {
         Iw entitylivingbase = (Iw)var4.next();
         entitylivingbase.setPartying(pos, isPartying);
      }

   }

   public void playSoundToAllNearExcept(@Nullable ME player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
   }

   public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      this.spawnParticle(particleID, ignoreRange, false, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   public void spawnParticle(int id, boolean ignoreRange, boolean minimiseParticleLevel, final double x, final double y, final double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      try {
         this.spawnParticle0(id, ignoreRange, minimiseParticleLevel, x, y, z, xSpeed, ySpeed, zSpeed, parameters);
      } catch (Throwable var20) {
         Throwable throwable = var20;
         Er crashreport = Er.makeCrashReport(throwable, "Exception while adding particle");
         Ey crashreportcategory = crashreport.makeCategory("Particle being added");
         crashreportcategory.addCrashSection("ID", id);
         if (parameters != null) {
            crashreportcategory.addCrashSection("Parameters", parameters);
         }

         crashreportcategory.addDetail("Position", new Ez<String>() {
            public String call() throws Exception {
               return Ey.getCoordinateInfo(x, y, z);
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
   private pM spawnParticle0(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      return this.spawnParticle0(particleID, ignoreRange, false, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
   }

   @Nullable
   private pM spawnParticle0(int particleID, boolean ignoreRange, boolean minParticles, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      Ig entity = this.mc.getRenderViewEntity();
      if (this.mc != null && entity != null && this.mc.effectRenderer != null) {
         int k1 = this.calculateParticleLevel(minParticles);
         double d3 = entity.posX - xCoord;
         double d4 = entity.posY - yCoord;
         double d5 = entity.posZ - zCoord;
         if (particleID == EnumParticleTypes.EXPLOSION_HUGE.getParticleID() && !XH.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.EXPLOSION_LARGE.getParticleID() && !XH.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.EXPLOSION_NORMAL.getParticleID() && !XH.isAnimatedExplosion()) {
            return null;
         } else if (particleID == EnumParticleTypes.SUSPENDED.getParticleID() && !XH.isWaterParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SUSPENDED_DEPTH.getParticleID() && !XH.isVoidParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SMOKE_NORMAL.getParticleID() && !XH.isAnimatedSmoke()) {
            return null;
         } else if (particleID == EnumParticleTypes.SMOKE_LARGE.getParticleID() && !XH.isAnimatedSmoke()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_MOB.getParticleID() && !XH.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID() && !XH.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL.getParticleID() && !XH.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_INSTANT.getParticleID() && !XH.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.SPELL_WITCH.getParticleID() && !XH.isPotionParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.PORTAL.getParticleID() && !XH.isPortalParticles()) {
            return null;
         } else if (particleID == EnumParticleTypes.FLAME.getParticleID() && !XH.isAnimatedFlame()) {
            return null;
         } else if (particleID == EnumParticleTypes.REDSTONE.getParticleID() && !XH.isAnimatedRedstone()) {
            return null;
         } else if (particleID == EnumParticleTypes.DRIP_WATER.getParticleID() && !XH.isDrippingWaterLava()) {
            return null;
         } else if (particleID == EnumParticleTypes.DRIP_LAVA.getParticleID() && !XH.isDrippingWaterLava()) {
            return null;
         } else if (particleID == EnumParticleTypes.FIREWORKS_SPARK.getParticleID() && !XH.isFireworkParticles()) {
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

            pM particle = this.mc.effectRenderer.spawnEffectParticle(particleID, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
            if (particleID == EnumParticleTypes.WATER_BUBBLE.getParticleID()) {
               bjy.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.WATER_SPLASH.getParticleID()) {
               bjy.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.WATER_DROP.getParticleID()) {
               bjy.updateWaterFX(particle, this.world, xCoord, yCoord, zCoord, this.renderEnv);
            }

            if (particleID == EnumParticleTypes.TOWN_AURA.getParticleID()) {
               bjy.updateMyceliumFX(particle);
            }

            if (particleID == EnumParticleTypes.PORTAL.getParticleID()) {
               bjy.updatePortalFX(particle);
            }

            if (particleID == EnumParticleTypes.REDSTONE.getParticleID()) {
               bjy.updateReddustFX(particle, this.world, xCoord, yCoord, zCoord);
            }

            return particle;
         }
      } else {
         return null;
      }
   }

   private int calculateParticleLevel(boolean minimiseLevel) {
      nC var10000 = this.mc;
      int k1 = nC.gameSettings.particleSetting;
      if (minimiseLevel && k1 == 2 && this.world.rand.nextInt(10) == 0) {
         k1 = 1;
      }

      if (k1 == 1 && this.world.rand.nextInt(3) == 0) {
         k1 = 2;
      }

      return k1;
   }

   public void onEntityAdded(Ig entityIn) {
      bnx.entityLoaded(entityIn, this.world);
      if (XH.isDynamicLights()) {
         bjP.entityAdded(entityIn, this);
      }

   }

   public void onEntityRemoved(Ig entityIn) {
      bnx.entityUnloaded(entityIn, this.world);
      if (XH.isDynamicLights()) {
         bjP.entityRemoved(entityIn, this);
      }

   }

   public void deleteAllDisplayLists() {
   }

   public void broadcastSound(int soundID, BlockPos pos, int data) {
      switch (soundID) {
         case 1023:
         case 1028:
         case 1038:
            Ig entity = this.mc.getRenderViewEntity();
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
                  this.world.playSound(d7, d8, d9, NO.ENTITY_WITHER_SPAWN, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
               } else if (soundID == 1038) {
                  this.world.playSound(d7, d8, d9, NO.BLOCK_END_PORTAL_SPAWN, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
               } else {
                  this.world.playSound(d7, d8, d9, NO.ENTITY_ENDERDRAGON_DEATH, SoundCategory.HOSTILE, 5.0F, 1.0F, false);
               }
            }
         default:
      }
   }

   public void playEvent(ME player, int type, BlockPos blockPosIn, int data) {
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
            this.world.playSound(blockPosIn, NO.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1001:
            this.world.playSound(blockPosIn, NO.BLOCK_DISPENSER_FAIL, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
            break;
         case 1002:
            this.world.playSound(blockPosIn, NO.BLOCK_DISPENSER_LAUNCH, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
            break;
         case 1003:
            this.world.playSound(blockPosIn, NO.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
            break;
         case 1004:
            this.world.playSound(blockPosIn, NO.ENTITY_FIREWORK_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
            break;
         case 1005:
            this.world.playSound(blockPosIn, NO.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1006:
            this.world.playSound(blockPosIn, NO.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1007:
            this.world.playSound(blockPosIn, NO.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1008:
            this.world.playSound(blockPosIn, NO.BLOCK_FENCE_GATE_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1009:
            this.world.playSound(blockPosIn, NO.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
            break;
         case 1010:
            if (OL.getItemById(data) instanceof Qf) {
               this.world.playRecord(blockPosIn, ((Qf)OL.getItemById(data)).getSound());
            } else {
               this.world.playRecord(blockPosIn, (SoundEvent)null);
            }
            break;
         case 1011:
            this.world.playSound(blockPosIn, NO.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1012:
            this.world.playSound(blockPosIn, NO.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1013:
            this.world.playSound(blockPosIn, NO.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1014:
            this.world.playSound(blockPosIn, NO.BLOCK_FENCE_GATE_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1015:
            this.world.playSound(blockPosIn, NO.ENTITY_GHAST_WARN, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1016:
            this.world.playSound(blockPosIn, NO.ENTITY_GHAST_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1017:
            this.world.playSound(blockPosIn, NO.ENTITY_ENDERDRAGON_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1018:
            this.world.playSound(blockPosIn, NO.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1019:
            this.world.playSound(blockPosIn, NO.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1020:
            this.world.playSound(blockPosIn, NO.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1021:
            this.world.playSound(blockPosIn, NO.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1022:
            this.world.playSound(blockPosIn, NO.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1024:
            this.world.playSound(blockPosIn, NO.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1025:
            this.world.playSound(blockPosIn, NO.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1026:
            this.world.playSound(blockPosIn, NO.ENTITY_ZOMBIE_INFECT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1027:
            this.world.playSound(blockPosIn, NO.ENTITY_ZOMBIE_VILLAGER_CONVERTED, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1029:
            this.world.playSound(blockPosIn, NO.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1030:
            this.world.playSound(blockPosIn, NO.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1031:
            this.world.playSound(blockPosIn, NO.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1032:
            this.mc.getSoundHandler().playSound(iN.getMasterRecord(NO.BLOCK_PORTAL_TRAVEL, random.nextFloat() * 0.4F + 0.8F));
            break;
         case 1033:
            this.world.playSound(blockPosIn, NO.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1034:
            this.world.playSound(blockPosIn, NO.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1035:
            this.world.playSound(blockPosIn, NO.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            break;
         case 1036:
            this.world.playSound(blockPosIn, NO.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1037:
            this.world.playSound(blockPosIn, NO.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
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
            co block = co.getBlockById(data & 4095);
            if (block.getDefaultState().getMaterial() != hM.AIR) {
               ia soundtype = block.getSoundType();
               if (bnK.ForgeBlock_getSoundType.exists()) {
                  soundtype = (ia)bnK.call(block, bnK.ForgeBlock_getSoundType, co.getStateById(data), this.world, blockPosIn, null);
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
               this.spawnParticle(EnumParticleTypes.ITEM_CRACK, d6, d7, d8, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, OL.getIdFromItem(NK.SPLASH_POTION));
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
               pM particle1 = this.spawnParticle0(enumparticletypes.getParticleID(), enumparticletypes.getShouldIgnoreRange(), d6 + d14 * 0.1, d7 + 0.3, d8 + d29 * 0.1, d14, d27, d29);
               if (particle1 != null) {
                  float f4 = 0.75F + random.nextFloat() * 0.25F;
                  particle1.setRBGColorF(f5 * f4, f * f4, f1 * f4);
                  particle1.multiplyVelocity((float)d10);
               }
            }

            this.world.playSound(blockPosIn, NO.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2003:
            d9 = (double)blockPosIn.getX() + 0.5;
            d11 = (double)blockPosIn.getY();
            double d13 = (double)blockPosIn.getZ() + 0.5;

            for(j3 = 0; j3 < 8; ++j3) {
               this.spawnParticle(EnumParticleTypes.ITEM_CRACK, d9, d11, d13, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, OL.getIdFromItem(NK.ENDER_EYE));
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
            Pq.spawnBonemealParticles(this.world, blockPosIn, data);
            break;
         case 2006:
            for(j3 = 0; j3 < 200; ++j3) {
               float f2 = random.nextFloat() * 4.0F;
               float f3 = random.nextFloat() * 6.2831855F;
               d29 = (double)(MathHelper.cos(f3) * f2);
               d16 = 0.01 + random.nextDouble() * 0.5;
               double d17 = (double)(MathHelper.sin(f3) * f2);
               pM particle = this.spawnParticle0(EnumParticleTypes.DRAGON_BREATH.getParticleID(), false, (double)blockPosIn.getX() + d29 * 0.1, (double)blockPosIn.getY() + 0.3, (double)blockPosIn.getZ() + d17 * 0.1, d29, d16, d17);
               if (particle != null) {
                  particle.multiplyVelocity(f2);
               }
            }

            this.world.playSound(blockPosIn, NO.ENTITY_ENDERDRAGON_FIREBALL_EPLD, SoundCategory.HOSTILE, 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 3000:
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true, (double)blockPosIn.getX() + 0.5, (double)blockPosIn.getY() + 0.5, (double)blockPosIn.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
            this.world.playSound(blockPosIn, NO.BLOCK_END_GATEWAY_SPAWN, SoundCategory.BLOCKS, 10.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
            break;
         case 3001:
            this.world.playSound(blockPosIn, NO.ENTITY_ENDERDRAGON_GROWL, SoundCategory.HOSTILE, 64.0F, 0.8F + this.world.rand.nextFloat() * 0.3F, false);
      }

   }

   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
      if (progress >= 0 && progress < 10) {
         va destroyblockprogress = (va)this.damagedBlocks.get(breakerId);
         if (destroyblockprogress == null || destroyblockprogress.getPosition().getX() != pos.getX() || destroyblockprogress.getPosition().getY() != pos.getY() || destroyblockprogress.getPosition().getZ() != pos.getZ()) {
            destroyblockprogress = new va(breakerId, pos);
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
         bar ichunkprovider = this.world.getChunkProvider();
         if (ichunkprovider == null) {
            return 0;
         } else {
            if (ichunkprovider != this.worldChunkProvider) {
               this.worldChunkProvider = ichunkprovider;
               this.worldChunkProviderMap = (Long2ObjectMap)bnK.getFieldValue(ichunkprovider, bnK.ChunkProviderClient_chunkMapping);
            }

            return this.worldChunkProviderMap == null ? 0 : this.worldChunkProviderMap.size();
         }
      }
   }

   public int getCountChunksToUpdate() {
      return this.chunksToUpdate.size();
   }

   public ug getRenderChunk(BlockPos p_getRenderChunk_1_) {
      return this.viewFrustum.getRenderChunk(p_getRenderChunk_1_);
   }

   public pm getWorld() {
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

   public void updateTileEntities(Collection<Yg> tileEntitiesToRemove, Collection<Yg> tileEntitiesToAdd) {
      synchronized(this.setTileEntities) {
         this.setTileEntities.removeAll(tileEntitiesToRemove);
         this.setTileEntities.addAll(tileEntitiesToAdd);
      }
   }

   static {
      SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(EnumFacing.VALUES)));
      renderEntitiesCounter = 0;
   }
}
