package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class xz implements AB {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation RAIN_TEXTURES = new ResourceLocation("textures/environment/rain.png");
   private static final ResourceLocation SNOW_TEXTURES = new ResourceLocation("textures/environment/snow.png");
   public static boolean anaglyphEnable;
   public static int anaglyphField;
   private final nC mc;
   private final AA resourceManager;
   private final Random random = new Random();
   private float farPlaneDistance;
   public yo itemRenderer;
   private final mo mapItemRenderer;
   private int rendererUpdateCount;
   private Ig pointedEntity;
   private MouseFilter mouseFilterXAxis = new MouseFilter();
   private MouseFilter mouseFilterYAxis = new MouseFilter();
   private final float thirdPersonDistance = 4.0F;
   private float thirdPersonDistancePrev = 4.0F;
   private float smoothCamYaw;
   private float smoothCamPitch;
   private float smoothCamFilterX;
   private float smoothCamFilterY;
   private float smoothCamPartialTicks;
   private float fovModifierHand;
   private float fovModifierHandPrev;
   private float bossColorModifier;
   private float bossColorModifierPrev;
   private boolean cloudFog;
   private boolean renderHand = true;
   private boolean drawBlockOutline = true;
   private long timeWorldIcon;
   private long prevFrameTime = nC.getSystemTime();
   private long renderEndNanoTime;
   private final yP lightmapTexture;
   private final int[] lightmapColors;
   private final ResourceLocation locationLightMap;
   private boolean lightmapUpdateNeeded;
   private float torchFlickerX;
   private float torchFlickerDX;
   private int rainSoundCounter;
   private final float[] rainXCoords = new float[1024];
   private final float[] rainYCoords = new float[1024];
   private final FloatBuffer fogColorBuffer = xE.createDirectFloatBuffer(16);
   public float fogColorRed;
   public float fogColorGreen;
   public float fogColorBlue;
   private float fogColor2;
   private float fogColor1;
   private int debugViewDirection;
   private boolean debugView;
   private double cameraZoom = 1.0;
   private double cameraYaw;
   private double cameraPitch;
   private Qy itemActivationItem;
   private int itemActivationTicks;
   private float itemActivationOffX;
   private float itemActivationOffY;
   private Br shaderGroup;
   private static final ResourceLocation[] SHADERS_TEXTURES = new ResourceLocation[]{new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/creeper.json"), new ResourceLocation("shaders/post/spider.json")};
   public static final int SHADER_COUNT;
   private int shaderIndex;
   private boolean useShader;
   public int frameCount;
   private boolean initialized = false;
   private bij updatedWorld = null;
   public boolean fogStandard = false;
   private float clipDistance = 128.0F;
   private long lastServerTime = 0L;
   private int lastServerTicks = 0;
   private int serverWaitTime = 0;
   private int serverWaitTimeCurrent = 0;
   private float avgServerTimeDiff = 0.0F;
   private float avgServerTickDiff = 0.0F;
   private Br[] fxaaShaders = new Br[10];
   private boolean loadVisibleChunks = false;

   public xz(nC mcIn, AA resourceManagerIn) {
      this.shaderIndex = SHADER_COUNT;
      this.mc = mcIn;
      this.resourceManager = resourceManagerIn;
      this.itemRenderer = mcIn.getItemRenderer();
      this.mapItemRenderer = new mo(mcIn.getTextureManager());
      this.lightmapTexture = new yP(16, 16);
      this.locationLightMap = mcIn.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
      this.lightmapColors = this.lightmapTexture.getTextureData();
      this.shaderGroup = null;

      for(int i = 0; i < 32; ++i) {
         for(int j = 0; j < 32; ++j) {
            float f = (float)(j - 16);
            float f1 = (float)(i - 16);
            float f2 = MathHelper.sqrt(f * f + f1 * f1);
            this.rainXCoords[i << 5 | j] = -f1 / f2;
            this.rainYCoords[i << 5 | j] = f / f2;
         }
      }

   }

   public boolean isShaderActive() {
      return ys.shadersSupported && this.shaderGroup != null;
   }

   public void stopUseShader() {
      if (this.shaderGroup != null) {
         this.shaderGroup.deleteShaderGroup();
      }

      this.shaderGroup = null;
      this.shaderIndex = SHADER_COUNT;
   }

   public void switchUseShader() {
      this.useShader = !this.useShader;
   }

   public void loadEntityShader(@Nullable Ig entityIn) {
      if (ys.shadersSupported) {
         if (this.shaderGroup != null) {
            this.shaderGroup.deleteShaderGroup();
         }

         this.shaderGroup = null;
         if (entityIn instanceof JB) {
            this.loadShader(new ResourceLocation("shaders/post/creeper.json"));
         } else if (entityIn instanceof KW) {
            this.loadShader(new ResourceLocation("shaders/post/spider.json"));
         } else if (entityIn instanceof JJ) {
            this.loadShader(new ResourceLocation("shaders/post/invert.json"));
         } else if (bnK.ForgeHooksClient_loadEntityShader.exists()) {
            bnK.call(bnK.ForgeHooksClient_loadEntityShader, entityIn, this);
         }
      }

   }

   private void loadShader(ResourceLocation resourceLocationIn) {
      if (ys.isFramebufferEnabled()) {
         try {
            this.shaderGroup = new Br(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), resourceLocationIn);
            this.shaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.useShader = true;
         } catch (IOException var3) {
            IOException ioexception = var3;
            LOGGER.warn("Failed to load shader: {}", resourceLocationIn, ioexception);
            this.shaderIndex = SHADER_COUNT;
            this.useShader = false;
         } catch (JsonSyntaxException var4) {
            JsonSyntaxException jsonsyntaxexception = var4;
            LOGGER.warn("Failed to load shader: {}", resourceLocationIn, jsonsyntaxexception);
            this.shaderIndex = SHADER_COUNT;
            this.useShader = false;
         }
      }

   }

   public void onResourceManagerReload(AA resourceManager) {
      if (this.shaderGroup != null) {
         this.shaderGroup.deleteShaderGroup();
      }

      this.shaderGroup = null;
      if (this.shaderIndex == SHADER_COUNT) {
         this.loadEntityShader(this.mc.getRenderViewEntity());
      } else {
         this.loadShader(SHADERS_TEXTURES[this.shaderIndex]);
      }

   }

   public void updateRenderer() {
      if (ys.shadersSupported && Bs.getStaticShaderLinkHelper() == null) {
         Bs.setNewStaticShaderLinkHelper();
      }

      this.updateFovModifierHand();
      this.updateTorchFlicker();
      this.fogColor2 = this.fogColor1;
      this.thirdPersonDistancePrev = 4.0F;
      nC var10000 = this.mc;
      if (nC.gameSettings.smoothCamera) {
         var10000 = this.mc;
         float f = nC.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f1 = f * f * f * 8.0F;
         this.smoothCamFilterX = this.mouseFilterXAxis.smooth(this.smoothCamYaw, 0.05F * f1);
         this.smoothCamFilterY = this.mouseFilterYAxis.smooth(this.smoothCamPitch, 0.05F * f1);
         this.smoothCamPartialTicks = 0.0F;
         this.smoothCamYaw = 0.0F;
         this.smoothCamPitch = 0.0F;
      } else {
         this.smoothCamFilterX = 0.0F;
         this.smoothCamFilterY = 0.0F;
         this.mouseFilterXAxis.reset();
         this.mouseFilterYAxis.reset();
      }

      if (this.mc.getRenderViewEntity() == null) {
         nC var10001 = this.mc;
         this.mc.setRenderViewEntity(nC.player);
      }

      Ig entity = this.mc.getRenderViewEntity();
      double d2 = entity.posX;
      double d0 = entity.posY + (double)entity.getEyeHeight();
      double d1 = entity.posZ;
      float f2 = this.mc.world.getLightBrightness(new BlockPos(d2, d0, d1));
      var10000 = this.mc;
      float f3 = (float)nC.gameSettings.renderDistanceChunks / 16.0F;
      f3 = MathHelper.clamp(f3, 0.0F, 1.0F);
      float f4 = f2 * (1.0F - f3) + f3;
      this.fogColor1 += (f4 - this.fogColor1) * 0.1F;
      ++this.rendererUpdateCount;
      this.itemRenderer.updateEquippedItem();
      this.addRainParticles();
      this.bossColorModifierPrev = this.bossColorModifier;
      if (this.mc.ingameGUI.getBossOverlay().shouldDarkenSky()) {
         this.bossColorModifier += 0.05F;
         if (this.bossColorModifier > 1.0F) {
            this.bossColorModifier = 1.0F;
         }
      } else if (this.bossColorModifier > 0.0F) {
         this.bossColorModifier -= 0.0125F;
      }

      if (this.itemActivationTicks > 0) {
         --this.itemActivationTicks;
         if (this.itemActivationTicks == 0) {
            this.itemActivationItem = null;
         }
      }

   }

   public Br getShaderGroup() {
      return this.shaderGroup;
   }

   public void updateShaderGroupSize(int width, int height) {
      if (ys.shadersSupported) {
         if (this.shaderGroup != null) {
            this.shaderGroup.createBindFramebuffers(width, height);
         }

         this.mc.renderGlobal.createBindEntityOutlineFbs(width, height);
      }

   }

   public void getMouseOver(float partialTicks) {
      Ig entity = this.mc.getRenderViewEntity();
      if (entity != null && this.mc.world != null) {
         this.mc.profiler.startSection("pick");
         this.mc.pointedEntity = null;
         double d0 = (double)this.mc.playerController.getBlockReachDistance();
         this.mc.objectMouseOver = entity.rayTrace(d0, partialTicks);
         Vec3d vec3d = entity.getPositionEyes(partialTicks);
         boolean flag = false;
         int i = true;
         double d1 = d0;
         if (this.mc.playerController.extendedReach()) {
            d1 = 6.0;
            d0 = d1;
         } else if (d0 > 3.0) {
            flag = true;
         }

         if (this.mc.objectMouseOver != null) {
            d1 = this.mc.objectMouseOver.hitVec.distanceTo(vec3d);
         }

         Vec3d vec3d1 = entity.getLook(1.0F);
         Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
         this.pointedEntity = null;
         Vec3d vec3d3 = null;
         float f = 1.0F;
         List<Ig> list = this.mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0, 1.0, 1.0), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Ig>() {
            public boolean apply(@Nullable Ig p_apply_1_) {
               return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((Ig)var1);
            }
         }));
         double d2 = d1;

         for(int j = 0; j < list.size(); ++j) {
            Ig entity1 = (Ig)list.get(j);
            AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
            if (axisalignedbb.contains(vec3d)) {
               if (d2 >= 0.0) {
                  this.pointedEntity = entity1;
                  vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                  d2 = 0.0;
               }
            } else if (raytraceresult != null) {
               double d3 = vec3d.distanceTo(raytraceresult.hitVec);
               if (d3 < d2 || d2 == 0.0) {
                  boolean flag1 = false;
                  if (bnK.ForgeEntity_canRiderInteract.exists()) {
                     flag1 = bnK.callBoolean(entity1, bnK.ForgeEntity_canRiderInteract);
                  }

                  if (!flag1 && entity1.getLowestRidingEntity() == entity.getLowestRidingEntity()) {
                     if (d2 == 0.0) {
                        this.pointedEntity = entity1;
                        vec3d3 = raytraceresult.hitVec;
                     }
                  } else {
                     this.pointedEntity = entity1;
                     vec3d3 = raytraceresult.hitVec;
                     d2 = d3;
                  }
               }
            }
         }

         if (this.pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 3.0) {
            this.pointedEntity = null;
            this.mc.objectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
         }

         if (this.pointedEntity != null && (d2 < d1 || this.mc.objectMouseOver == null)) {
            this.mc.objectMouseOver = new RayTraceResult(this.pointedEntity, vec3d3);
            if (this.pointedEntity instanceof Iw || this.pointedEntity instanceof IZ) {
               this.mc.pointedEntity = this.pointedEntity;
            }
         }

         this.mc.profiler.endSection();
      }

   }

   private void updateFovModifierHand() {
      float f = 1.0F;
      if (this.mc.getRenderViewEntity() instanceof jf) {
         jf abstractclientplayer = (jf)this.mc.getRenderViewEntity();
         f = abstractclientplayer.getFovModifier();
      }

      this.fovModifierHandPrev = this.fovModifierHand;
      this.fovModifierHand += (f - this.fovModifierHand) * 0.5F;
      if (this.fovModifierHand > 1.5F) {
         this.fovModifierHand = 1.5F;
      }

      if (this.fovModifierHand < 0.1F) {
         this.fovModifierHand = 0.1F;
      }

   }

   private float getFOVModifier(float partialTicks, boolean useFOVSetting) {
      if (this.debugView) {
         return 90.0F;
      } else {
         Ig entity = this.mc.getRenderViewEntity();
         float f = 70.0F;
         nC var10000;
         if (useFOVSetting) {
            var10000 = this.mc;
            f = nC.gameSettings.fovSetting;
            if (XH.isDynamicFov()) {
               f *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * partialTicks;
            }
         }

         boolean flag = false;
         if (this.mc.currentScreen == null) {
            var10000 = this.mc;
            flag = Bj.isKeyDown(nC.gameSettings.ofKeyBindZoom);
         }

         if (flag) {
            if (!XH.zoomMode) {
               XH.zoomMode = true;
               this.mc.renderGlobal.displayListEntitiesDirty = true;
            }

            if (XH.zoomMode) {
               f /= 4.0F;
            }
         } else if (XH.zoomMode) {
            XH.zoomMode = false;
            var10000 = this.mc;
            nC.gameSettings.smoothCamera = XH.zoomSmoothCamera;
            this.mouseFilterXAxis = new MouseFilter();
            this.mouseFilterYAxis = new MouseFilter();
            this.mc.renderGlobal.displayListEntitiesDirty = true;
         }

         if (entity instanceof Iw && ((Iw)entity).getHealth() <= 0.0F) {
            float f1 = (float)((Iw)entity).deathTime + partialTicks;
            f /= (1.0F - 500.0F / (f1 + 500.0F)) * 2.0F + 1.0F;
         }

         in iblockstate = rF.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
         if (iblockstate.getMaterial() == hM.WATER) {
            f = f * 60.0F / 70.0F;
         }

         return bnK.ForgeHooksClient_getFOVModifier.exists() ? bnK.callFloat(bnK.ForgeHooksClient_getFOVModifier, this, entity, iblockstate, partialTicks, f) : f;
      }
   }

   private void hurtCameraEffect(float partialTicks) {
      if (this.mc.getRenderViewEntity() instanceof Iw) {
         Iw entitylivingbase = (Iw)this.mc.getRenderViewEntity();
         float f = (float)entitylivingbase.hurtTime - partialTicks;
         float f2;
         if (entitylivingbase.getHealth() <= 0.0F) {
            f2 = (float)entitylivingbase.deathTime + partialTicks;
            yh.rotate(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
         }

         if (f < 0.0F) {
            return;
         }

         f /= (float)entitylivingbase.maxHurtTime;
         f = MathHelper.sin(f * f * f * f * 3.1415927F);
         f2 = entitylivingbase.attackedAtYaw;
         yh.rotate(-f2, 0.0F, 1.0F, 0.0F);
         yh.rotate(-f * 14.0F, 0.0F, 0.0F, 1.0F);
         yh.rotate(f2, 0.0F, 1.0F, 0.0F);
      }

   }

   private void applyBobbing(float partialTicks) {
      if (this.mc.getRenderViewEntity() instanceof ME) {
         ME entityplayer = (ME)this.mc.getRenderViewEntity();
         float f = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
         float f1 = -(entityplayer.distanceWalkedModified + f * partialTicks);
         float f2 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * partialTicks;
         float f3 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * partialTicks;
         yh.translate(MathHelper.sin(f1 * 3.1415927F) * f2 * 0.5F, -Math.abs(MathHelper.cos(f1 * 3.1415927F) * f2), 0.0F);
         yh.rotate(MathHelper.sin(f1 * 3.1415927F) * f2 * 3.0F, 0.0F, 0.0F, 1.0F);
         yh.rotate(Math.abs(MathHelper.cos(f1 * 3.1415927F - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
         yh.rotate(f3, 1.0F, 0.0F, 0.0F);
      }

   }

   private void orientCamera(float partialTicks) {
      Ig entity = this.mc.getRenderViewEntity();
      float f = entity.getEyeHeight();
      double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
      double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + (double)f;
      double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
      float f1;
      nC var10000;
      if (entity instanceof Iw && ((Iw)entity).isPlayerSleeping()) {
         f = (float)((double)f + 1.0);
         yh.translate(0.0F, 0.3F, 0.0F);
         var10000 = this.mc;
         if (!nC.gameSettings.debugCamEnable) {
            BlockPos blockpos = new BlockPos(entity);
            in iblockstate = this.mc.world.getBlockState(blockpos);
            co block = iblockstate.getBlock();
            if (bnK.ForgeHooksClient_orientBedCamera.exists()) {
               bnK.callVoid(bnK.ForgeHooksClient_orientBedCamera, this.mc.world, blockpos, iblockstate, entity);
            } else if (block == Nk.BED) {
               int j = ((EnumFacing)iblockstate.getValue(cC.FACING)).getHorizontalIndex();
               yh.rotate((float)(j * 90), 0.0F, 1.0F, 0.0F);
            }

            yh.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
            yh.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
         }
      } else {
         var10000 = this.mc;
         if (nC.gameSettings.thirdPersonView > 0) {
            double d3 = (double)(this.thirdPersonDistancePrev + (4.0F - this.thirdPersonDistancePrev) * partialTicks);
            var10000 = this.mc;
            if (nC.gameSettings.debugCamEnable) {
               yh.translate(0.0F, 0.0F, (float)(-d3));
            } else {
               f1 = entity.rotationYaw;
               float f2 = entity.rotationPitch;
               var10000 = this.mc;
               if (nC.gameSettings.thirdPersonView == 2) {
                  f2 += 180.0F;
               }

               double d4 = (double)(-MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F)) * d3;
               double d5 = (double)(MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F)) * d3;
               double d6 = (double)(-MathHelper.sin(f2 * 0.017453292F)) * d3;

               for(int i = 0; i < 8; ++i) {
                  float f3 = (float)((i & 1) * 2 - 1);
                  float f4 = (float)((i >> 1 & 1) * 2 - 1);
                  float f5 = (float)((i >> 2 & 1) * 2 - 1);
                  f3 *= 0.1F;
                  f4 *= 0.1F;
                  f5 *= 0.1F;
                  RayTraceResult raytraceresult = this.mc.world.rayTraceBlocks(new Vec3d(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), new Vec3d(d0 - d4 + (double)f3 + (double)f5, d1 - d6 + (double)f4, d2 - d5 + (double)f5));
                  if (raytraceresult != null) {
                     double d7 = raytraceresult.hitVec.distanceTo(new Vec3d(d0, d1, d2));
                     if (d7 < d3) {
                        d3 = d7;
                     }
                  }
               }

               var10000 = this.mc;
               if (nC.gameSettings.thirdPersonView == 2) {
                  yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               }

               yh.rotate(entity.rotationPitch - f2, 1.0F, 0.0F, 0.0F);
               yh.rotate(entity.rotationYaw - f1, 0.0F, 1.0F, 0.0F);
               yh.translate(0.0F, 0.0F, (float)(-d3));
               yh.rotate(f1 - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
               yh.rotate(f2 - entity.rotationPitch, 1.0F, 0.0F, 0.0F);
            }
         } else {
            yh.translate(0.0F, 0.0F, 0.05F);
         }
      }

      if (bnK.EntityViewRenderEvent_CameraSetup_Constructor.exists()) {
         var10000 = this.mc;
         if (!nC.gameSettings.debugCamEnable) {
            float f6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F;
            float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
            f1 = 0.0F;
            if (entity instanceof Ly) {
               Ly entityanimal1 = (Ly)entity;
               f6 = entityanimal1.prevRotationYawHead + (entityanimal1.rotationYawHead - entityanimal1.prevRotationYawHead) * partialTicks + 180.0F;
            }

            in iblockstate1 = rF.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
            Object object = bnK.newInstance(bnK.EntityViewRenderEvent_CameraSetup_Constructor, this, entity, iblockstate1, partialTicks, f6, f7, f1);
            bnK.postForgeBusEvent(object);
            f1 = bnK.callFloat(object, bnK.EntityViewRenderEvent_CameraSetup_getRoll);
            f7 = bnK.callFloat(object, bnK.EntityViewRenderEvent_CameraSetup_getPitch);
            f6 = bnK.callFloat(object, bnK.EntityViewRenderEvent_CameraSetup_getYaw);
            yh.rotate(f1, 0.0F, 0.0F, 1.0F);
            yh.rotate(f7, 1.0F, 0.0F, 0.0F);
            yh.rotate(f6, 0.0F, 1.0F, 0.0F);
         }
      } else {
         var10000 = this.mc;
         if (!nC.gameSettings.debugCamEnable) {
            yh.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
            if (entity instanceof Ly) {
               Ly entityanimal = (Ly)entity;
               yh.rotate(entityanimal.prevRotationYawHead + (entityanimal.rotationYawHead - entityanimal.prevRotationYawHead) * partialTicks + 180.0F, 0.0F, 1.0F, 0.0F);
            } else {
               yh.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F, 0.0F, 1.0F, 0.0F);
            }
         }
      }

      yh.translate(0.0F, -f, 0.0F);
      d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
      d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + (double)f;
      d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
      this.cloudFog = this.mc.renderGlobal.hasCloudFog(d0, d1, d2, partialTicks);
   }

   public void setupCameraTransform(float partialTicks, int pass) {
      nC var10001 = this.mc;
      this.farPlaneDistance = (float)(nC.gameSettings.renderDistanceChunks * 16);
      if (XH.isFogFancy()) {
         this.farPlaneDistance *= 0.95F;
      }

      if (XH.isFogFast()) {
         this.farPlaneDistance *= 0.83F;
      }

      yh.matrixMode(5889);
      yh.loadIdentity();
      float f = 0.07F;
      nC var10000 = this.mc;
      if (nC.gameSettings.anaglyph) {
         yh.translate((float)(-(pass * 2 - 1)) * 0.07F, 0.0F, 0.0F);
      }

      this.clipDistance = this.farPlaneDistance * 2.0F;
      if (this.clipDistance < 173.0F) {
         this.clipDistance = 173.0F;
      }

      if (this.cameraZoom != 1.0) {
         yh.translate((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
         yh.scale(this.cameraZoom, this.cameraZoom, 1.0);
      }

      Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.clipDistance);
      yh.matrixMode(5888);
      yh.loadIdentity();
      var10000 = this.mc;
      if (nC.gameSettings.anaglyph) {
         yh.translate((float)(pass * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }

      this.hurtCameraEffect(partialTicks);
      var10000 = this.mc;
      if (nC.gameSettings.viewBobbing) {
         this.applyBobbing(partialTicks);
      }

      var10000 = this.mc;
      var10001 = this.mc;
      nC var10002 = this.mc;
      float f1 = nC.player.prevTimeInPortal + (nC.player.timeInPortal - nC.player.prevTimeInPortal) * partialTicks;
      if (f1 > 0.0F) {
         int i = 20;
         var10000 = this.mc;
         if (nC.player.isPotionActive(NL.NAUSEA)) {
            i = 7;
         }

         float f2 = 5.0F / (f1 * f1 + 5.0F) - f1 * 0.04F;
         f2 *= f2;
         yh.rotate(((float)this.rendererUpdateCount + partialTicks) * (float)i, 0.0F, 1.0F, 1.0F);
         yh.scale(1.0F / f2, 1.0F, 1.0F);
         yh.rotate(-((float)this.rendererUpdateCount + partialTicks) * (float)i, 0.0F, 1.0F, 1.0F);
      }

      this.orientCamera(partialTicks);
      if (this.debugView) {
         switch (this.debugViewDirection) {
            case 0:
               yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 1:
               yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 2:
               yh.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 3:
               yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               break;
            case 4:
               yh.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         }
      }

   }

   private void renderHand(float partialTicks, int pass) {
      this.renderHand(partialTicks, pass, true, true, false);
   }

   public void renderHand(float p_renderHand_1_, int p_renderHand_2_, boolean p_renderHand_3_, boolean p_renderHand_4_, boolean p_renderHand_5_) {
      if (!this.debugView) {
         yh.matrixMode(5889);
         yh.loadIdentity();
         float f = 0.07F;
         nC var10000 = this.mc;
         if (nC.gameSettings.anaglyph) {
            yh.translate((float)(-(p_renderHand_2_ * 2 - 1)) * 0.07F, 0.0F, 0.0F);
         }

         if (XH.isShaders()) {
            bpq.applyHandDepth();
         }

         Project.gluPerspective(this.getFOVModifier(p_renderHand_1_, false), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
         yh.matrixMode(5888);
         yh.loadIdentity();
         var10000 = this.mc;
         if (nC.gameSettings.anaglyph) {
            yh.translate((float)(p_renderHand_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
         }

         boolean flag = false;
         if (p_renderHand_3_) {
            yh.pushMatrix();
            this.hurtCameraEffect(p_renderHand_1_);
            var10000 = this.mc;
            if (nC.gameSettings.viewBobbing) {
               this.applyBobbing(p_renderHand_1_);
            }

            flag = this.mc.getRenderViewEntity() instanceof Iw && ((Iw)this.mc.getRenderViewEntity()).isPlayerSleeping();
            boolean flag1 = !bnQ.renderFirstPersonHand(this.mc.renderGlobal, p_renderHand_1_, p_renderHand_2_);
            if (flag1) {
               var10000 = this.mc;
               if (nC.gameSettings.thirdPersonView == 0 && !flag) {
                  var10000 = this.mc;
                  if (!nC.gameSettings.hideGUI && !this.mc.playerController.isSpectator()) {
                     this.enableLightmap();
                     if (XH.isShaders()) {
                        bpr.renderItemFP(this.itemRenderer, p_renderHand_1_, p_renderHand_5_);
                     } else {
                        this.itemRenderer.renderItemInFirstPerson(p_renderHand_1_);
                     }

                     this.disableLightmap();
                  }
               }
            }

            yh.popMatrix();
         }

         if (!p_renderHand_4_) {
            return;
         }

         this.disableLightmap();
         var10000 = this.mc;
         if (nC.gameSettings.thirdPersonView == 0 && !flag) {
            this.itemRenderer.renderOverlays(p_renderHand_1_);
            this.hurtCameraEffect(p_renderHand_1_);
         }

         var10000 = this.mc;
         if (nC.gameSettings.viewBobbing) {
            this.applyBobbing(p_renderHand_1_);
         }
      }

   }

   public void disableLightmap() {
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.disableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
      if (XH.isShaders()) {
         bpq.disableLightmap();
      }

   }

   public void enableLightmap() {
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.matrixMode(5890);
      yh.loadIdentity();
      yh.scale(0.00390625F, 0.00390625F, 0.00390625F);
      yh.translate(8.0F, 8.0F, 8.0F);
      yh.matrixMode(5888);
      this.mc.getTextureManager().bindTexture(this.locationLightMap);
      yh.glTexParameteri(3553, 10241, 9729);
      yh.glTexParameteri(3553, 10240, 9729);
      yh.glTexParameteri(3553, 10242, 33071);
      yh.glTexParameteri(3553, 10243, 33071);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.enableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
      if (XH.isShaders()) {
         bpq.enableLightmap();
      }

   }

   private void updateTorchFlicker() {
      this.torchFlickerDX = (float)((double)this.torchFlickerDX + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.torchFlickerDX = (float)((double)this.torchFlickerDX * 0.9);
      this.torchFlickerX += this.torchFlickerDX - this.torchFlickerX;
      this.lightmapUpdateNeeded = true;
   }

   private void updateLightmap(float partialTicks) {
      if (this.lightmapUpdateNeeded) {
         this.mc.profiler.startSection("lightTex");
         bij world = this.mc.world;
         if (world != null) {
            if (XH.isCustomColors()) {
               nC var10003 = this.mc;
               if (bjy.updateLightmap(world, this.torchFlickerX, this.lightmapColors, nC.player.isPotionActive(NL.NIGHT_VISION), partialTicks)) {
                  this.lightmapTexture.updateDynamicTexture();
                  this.lightmapUpdateNeeded = false;
                  this.mc.profiler.endSection();
                  return;
               }
            }

            float f = ((bij)world).getSunBrightness(1.0F);
            float f1 = f * 0.95F + 0.05F;

            for(int i = 0; i < 256; ++i) {
               float f2 = world.provider.getLightBrightnessTable()[i / 16] * f1;
               float f3 = world.provider.getLightBrightnessTable()[i % 16] * (this.torchFlickerX * 0.1F + 1.5F);
               if (((bij)world).getLastLightningBolt() > 0) {
                  f2 = world.provider.getLightBrightnessTable()[i / 16];
               }

               float f4 = f2 * (f * 0.65F + 0.35F);
               float f5 = f2 * (f * 0.65F + 0.35F);
               float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
               float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
               float f8 = f4 + f3;
               float f9 = f5 + f6;
               float f10 = f2 + f7;
               f8 = f8 * 0.96F + 0.03F;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               float f16;
               if (this.bossColorModifier > 0.0F) {
                  f16 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * partialTicks;
                  f8 = f8 * (1.0F - f16) + f8 * 0.7F * f16;
                  f9 = f9 * (1.0F - f16) + f9 * 0.6F * f16;
                  f10 = f10 * (1.0F - f16) + f10 * 0.6F * f16;
               }

               if (world.provider.getDimensionType().getId() == 1) {
                  f8 = 0.22F + f3 * 0.75F;
                  f9 = 0.28F + f6 * 0.75F;
                  f10 = 0.25F + f7 * 0.75F;
               }

               if (bnK.ForgeWorldProvider_getLightmapColors.exists()) {
                  float[] afloat = new float[]{f8, f9, f10};
                  bnK.call(world.provider, bnK.ForgeWorldProvider_getLightmapColors, partialTicks, f, f2, f3, afloat);
                  f8 = afloat[0];
                  f9 = afloat[1];
                  f10 = afloat[2];
               }

               f8 = MathHelper.clamp(f8, 0.0F, 1.0F);
               f9 = MathHelper.clamp(f9, 0.0F, 1.0F);
               f10 = MathHelper.clamp(f10, 0.0F, 1.0F);
               nC var10000 = this.mc;
               float f17;
               if (nC.player.isPotionActive(NL.NIGHT_VISION)) {
                  nC var10001 = this.mc;
                  f16 = this.getNightVisionBrightness(nC.player, partialTicks);
                  f17 = 1.0F / f8;
                  if (f17 > 1.0F / f9) {
                     f17 = 1.0F / f9;
                  }

                  if (f17 > 1.0F / f10) {
                     f17 = 1.0F / f10;
                  }

                  f8 = f8 * (1.0F - f16) + f8 * f17 * f16;
                  f9 = f9 * (1.0F - f16) + f9 * f17 * f16;
                  f10 = f10 * (1.0F - f16) + f10 * f17 * f16;
               }

               if (f8 > 1.0F) {
                  f8 = 1.0F;
               }

               if (f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if (f10 > 1.0F) {
                  f10 = 1.0F;
               }

               var10000 = this.mc;
               f16 = nC.gameSettings.gammaSetting;
               f17 = 1.0F - f8;
               float f13 = 1.0F - f9;
               float f14 = 1.0F - f10;
               f17 = 1.0F - f17 * f17 * f17 * f17;
               f13 = 1.0F - f13 * f13 * f13 * f13;
               f14 = 1.0F - f14 * f14 * f14 * f14;
               f8 = f8 * (1.0F - f16) + f17 * f16;
               f9 = f9 * (1.0F - f16) + f13 * f16;
               f10 = f10 * (1.0F - f16) + f14 * f16;
               f8 = f8 * 0.96F + 0.03F;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               if (f8 > 1.0F) {
                  f8 = 1.0F;
               }

               if (f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if (f10 > 1.0F) {
                  f10 = 1.0F;
               }

               if (f8 < 0.0F) {
                  f8 = 0.0F;
               }

               if (f9 < 0.0F) {
                  f9 = 0.0F;
               }

               if (f10 < 0.0F) {
                  f10 = 0.0F;
               }

               int j = true;
               int k = (int)(f8 * 255.0F);
               int l = (int)(f9 * 255.0F);
               int i1 = (int)(f10 * 255.0F);
               this.lightmapColors[i] = -16777216 | k << 16 | l << 8 | i1;
            }

            this.lightmapTexture.updateDynamicTexture();
            this.lightmapUpdateNeeded = false;
            this.mc.profiler.endSection();
         }
      }

   }

   public float getNightVisionBrightness(Iw entitylivingbaseIn, float partialTicks) {
      int i = entitylivingbaseIn.getActivePotionEffect(NL.NIGHT_VISION).getDuration();
      return i > 200 ? 1.0F : 0.7F + MathHelper.sin(((float)i - partialTicks) * 3.1415927F * 0.2F) * 0.3F;
   }

   public void updateCameraAndRender(float partialTicks, long nanoTime) {
      boolean flag;
      nC var10000;
      label115: {
         this.frameInit();
         flag = Display.isActive();
         if (!flag) {
            var10000 = this.mc;
            if (nC.gameSettings.pauseOnLostFocus) {
               var10000 = this.mc;
               if (!nC.gameSettings.touchscreen || !Mouse.isButtonDown(1)) {
                  if (nC.getSystemTime() - this.prevFrameTime > 500L) {
                     this.mc.displayInGameMenu();
                  }
                  break label115;
               }
            }
         }

         this.prevFrameTime = nC.getSystemTime();
      }

      this.mc.profiler.startSection("mouse");
      if (flag && nC.IS_RUNNING_ON_MAC && this.mc.inGameHasFocus && !Mouse.isInsideWindow()) {
         Mouse.setGrabbed(false);
         Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2 - 20);
         Mouse.setGrabbed(true);
      }

      final int l1;
      if (this.mc.inGameHasFocus && flag) {
         this.mc.mouseHelper.mouseXYChange();
         this.mc.getTutorial().handleMouse(this.mc.mouseHelper);
         var10000 = this.mc;
         float f = nC.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f1 = f * f * f * 8.0F;
         float f2 = (float)this.mc.mouseHelper.deltaX * f1;
         float f3 = (float)this.mc.mouseHelper.deltaY * f1;
         l1 = 1;
         var10000 = this.mc;
         if (nC.gameSettings.invertMouse) {
            l1 = -1;
         }

         var10000 = this.mc;
         if (nC.gameSettings.smoothCamera) {
            this.smoothCamYaw += f2;
            this.smoothCamPitch += f3;
            float f4 = partialTicks - this.smoothCamPartialTicks;
            this.smoothCamPartialTicks = partialTicks;
            f2 = this.smoothCamFilterX * f4;
            f3 = this.smoothCamFilterY * f4;
            var10000 = this.mc;
            nC.player.turn(f2, f3 * (float)l1);
         } else {
            this.smoothCamYaw = 0.0F;
            this.smoothCamPitch = 0.0F;
            var10000 = this.mc;
            nC.player.turn(f2, f3 * (float)l1);
         }
      }

      this.mc.profiler.endSection();
      if (!this.mc.skipRenderWorld) {
         var10000 = this.mc;
         anaglyphEnable = nC.gameSettings.anaglyph;
         final mC scaledresolution = new mC(this.mc);
         int i1 = scaledresolution.getScaledWidth();
         int j1 = scaledresolution.getScaledHeight();
         final int k1 = Mouse.getX() * i1 / this.mc.displayWidth;
         l1 = j1 - Mouse.getY() * j1 / this.mc.displayHeight - 1;
         var10000 = this.mc;
         int i2 = nC.gameSettings.limitFramerate;
         if (this.mc.world == null) {
            yh.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
            yh.matrixMode(5889);
            yh.loadIdentity();
            yh.matrixMode(5888);
            yh.loadIdentity();
            this.setupOverlayRendering();
            this.renderEndNanoTime = System.nanoTime();
            zz.instance.renderEngine = this.mc.getTextureManager();
            zz.instance.fontRenderer = this.mc.fontRenderer;
         } else {
            this.mc.profiler.startSection("level");
            int j = Math.min(nC.getDebugFPS(), i2);
            j = Math.max(j, 60);
            long k = System.nanoTime() - nanoTime;
            long l = Math.max((long)(1000000000 / j / 4) - k, 0L);
            this.renderWorld(partialTicks, System.nanoTime() + l);
            if (this.mc.isSingleplayer() && this.timeWorldIcon < nC.getSystemTime() - 1000L) {
               this.timeWorldIcon = nC.getSystemTime();
               if (!this.mc.getIntegratedServer().isWorldIconSet()) {
                  this.createWorldIcon();
               }
            }

            if (ys.shadersSupported) {
               this.mc.renderGlobal.renderEntityOutlineFramebuffer();
               if (this.shaderGroup != null && this.useShader) {
                  yh.matrixMode(5890);
                  yh.pushMatrix();
                  yh.loadIdentity();
                  this.shaderGroup.render(partialTicks);
                  yh.popMatrix();
               }

               this.mc.getFramebuffer().bindFramebuffer(true);
            }

            this.renderEndNanoTime = System.nanoTime();
            this.mc.profiler.endStartSection("gui");
            var10000 = this.mc;
            if (!nC.gameSettings.hideGUI || this.mc.currentScreen != null) {
               yh.alphaFunc(516, 0.1F);
               this.setupOverlayRendering();
               this.renderItemActivation(i1, j1, partialTicks);
               this.mc.ingameGUI.renderGameOverlay(partialTicks);
               var10000 = this.mc;
               if (nC.gameSettings.ofShowFps) {
                  var10000 = this.mc;
                  if (!nC.gameSettings.showDebugInfo) {
                     XH.drawFps();
                  }
               }

               var10000 = this.mc;
               if (nC.gameSettings.showDebugInfo) {
                  bmV.showLagometer(scaledresolution);
               }
            }

            this.mc.profiler.endSection();
         }

         if (this.mc.currentScreen != null) {
            yh.clear(256);

            try {
               if (bnK.ForgeHooksClient_drawScreen.exists()) {
                  bnK.callVoid(bnK.ForgeHooksClient_drawScreen, this.mc.currentScreen, k1, l1, this.mc.getTickLength());
               } else {
                  this.mc.currentScreen.drawScreen(k1, l1, this.mc.getTickLength());
               }
            } catch (Throwable var16) {
               Throwable throwable1 = var16;
               Er crashreport = Er.makeCrashReport(throwable1, "Rendering screen");
               Ey crashreportcategory = crashreport.makeCategory("Screen render details");
               crashreportcategory.addDetail("Screen name", new Ez<String>() {
                  public String call() throws Exception {
                     return xz.this.mc.currentScreen.getClass().getCanonicalName();
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  public Object call() throws Exception {
                     return this.call();
                  }
               });
               crashreportcategory.addDetail("Mouse location", new Ez<String>() {
                  public String call() throws Exception {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", k1, l1, Mouse.getX(), Mouse.getY());
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  public Object call() throws Exception {
                     return this.call();
                  }
               });
               crashreportcategory.addDetail("Screen size", new Ez<String>() {
                  public String call() throws Exception {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), xz.this.mc.displayWidth, xz.this.mc.displayHeight, scaledresolution.getScaleFactor());
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
      }

      this.frameFinish();
      this.waitForServerThread();
      bqH.update();
      bmV.updateLagometer();
      var10000 = this.mc;
      if (nC.gameSettings.ofProfiler) {
         var10000 = this.mc;
         nC.gameSettings.showDebugProfilerChart = true;
      }

   }

   private void createWorldIcon() {
      if (this.mc.renderGlobal.getRenderedChunks() > 10 && this.mc.renderGlobal.hasNoChunkUpdates() && !this.mc.getIntegratedServer().isWorldIconSet()) {
         BufferedImage bufferedimage = ScreenShotHelper.createScreenshot(this.mc.displayWidth, this.mc.displayHeight, this.mc.getFramebuffer());
         int i = bufferedimage.getWidth();
         int j = bufferedimage.getHeight();
         int k = 0;
         int l = 0;
         if (i > j) {
            k = (i - j) / 2;
            i = j;
         } else {
            l = (j - i) / 2;
         }

         try {
            BufferedImage bufferedimage1 = new BufferedImage(64, 64, 1);
            Graphics graphics = bufferedimage1.createGraphics();
            ((Graphics)graphics).drawImage(bufferedimage, 0, 0, 64, 64, k, l, k + i, l + i, (ImageObserver)null);
            ((Graphics)graphics).dispose();
            ImageIO.write(bufferedimage1, "png", this.mc.getIntegratedServer().getWorldIconFile());
         } catch (IOException var8) {
            IOException ioexception1 = var8;
            LOGGER.warn("Couldn't save auto screenshot", ioexception1);
         }
      }

   }

   public void renderStreamIndicator(float partialTicks) {
      this.setupOverlayRendering();
   }

   private boolean isDrawBlockOutline() {
      if (!this.drawBlockOutline) {
         return false;
      } else {
         Ig entity;
         boolean var7;
         label51: {
            entity = this.mc.getRenderViewEntity();
            if (entity instanceof ME) {
               nC var10000 = this.mc;
               if (!nC.gameSettings.hideGUI) {
                  var7 = true;
                  break label51;
               }
            }

            var7 = false;
         }

         boolean flag = var7;
         if (flag && !((ME)entity).capabilities.allowEdit) {
            Qy itemstack = ((ME)entity).getHeldItemMainhand();
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
               BlockPos blockpos = this.mc.objectMouseOver.getBlockPos();
               in iblockstate = this.mc.world.getBlockState(blockpos);
               co block = iblockstate.getBlock();
               if (this.mc.playerController.getCurrentGameType() == bbb.SPECTATOR) {
                  flag = bnQ.blockHasTileEntity(iblockstate) && this.mc.world.getTileEntity(blockpos) instanceof IInventory;
               } else {
                  flag = !itemstack.isEmpty() && (itemstack.canDestroy(block) || itemstack.canPlaceOn(block));
               }
            }
         }

         return flag;
      }
   }

   public void renderWorld(float partialTicks, long finishTimeNano) {
      this.updateLightmap(partialTicks);
      if (this.mc.getRenderViewEntity() == null) {
         nC var10001 = this.mc;
         this.mc.setRenderViewEntity(nC.player);
      }

      this.getMouseOver(partialTicks);
      if (XH.isShaders()) {
         bpq.beginRender(this.mc, partialTicks, finishTimeNano);
      }

      yh.enableDepth();
      yh.enableAlpha();
      yh.alphaFunc(516, 0.1F);
      this.mc.profiler.startSection("center");
      nC var10000 = this.mc;
      if (nC.gameSettings.anaglyph) {
         anaglyphField = 0;
         yh.colorMask(false, true, true, false);
         this.renderWorldPass(0, partialTicks, finishTimeNano);
         anaglyphField = 1;
         yh.colorMask(true, false, false, false);
         this.renderWorldPass(1, partialTicks, finishTimeNano);
         yh.colorMask(true, true, true, false);
      } else {
         this.renderWorldPass(2, partialTicks, finishTimeNano);
      }

      this.mc.profiler.endSection();
   }

   private void renderWorldPass(int pass, float partialTicks, long finishTimeNano) {
      boolean flag = XH.isShaders();
      if (flag) {
         bpq.beginRenderPass(pass, partialTicks, finishTimeNano);
      }

      yy renderglobal = this.mc.renderGlobal;
      qO particlemanager = this.mc.effectRenderer;
      if (renderglobal != null && particlemanager != null && this.mc.getTextureManager() != null) {
         boolean flag1 = this.isDrawBlockOutline();
         yh.enableCull();
         this.mc.profiler.endStartSection("clear");
         if (flag) {
            bpq.setViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
         } else {
            yh.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
         }

         this.updateFogColor(partialTicks);
         yh.clear(16640);
         if (flag) {
            bpq.clearRenderBuffer();
         }

         this.mc.profiler.endStartSection("camera");
         this.setupCameraTransform(partialTicks, pass);
         if (flag) {
            bpq.setCamera(partialTicks);
         }

         nC var19;
         nC var10004;
         if (bnK.ActiveRenderInfo_updateRenderInfo2.exists()) {
            bnR var10000 = bnK.ActiveRenderInfo_updateRenderInfo2;
            Object[] var10001 = new Object[]{this.mc.getRenderViewEntity(), null};
            var10004 = this.mc;
            var10001[1] = nC.gameSettings.thirdPersonView == 2;
            bnK.call(var10000, var10001);
         } else {
            var19 = this.mc;
            nC var21 = this.mc;
            rF.updateRenderInfo(nC.player, nC.gameSettings.thirdPersonView == 2);
         }

         this.mc.profiler.endStartSection("frustum");
         uL clippinghelper = uM.getInstance();
         this.mc.profiler.endStartSection("culling");
         clippinghelper.disabled = XH.isShaders() && !bpq.isFrustumCulling();
         uO icamera = new uN(clippinghelper);
         Ig entity = this.mc.getRenderViewEntity();
         double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
         double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
         double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
         if (flag) {
            bpr.setFrustrumPosition(icamera, d0, d1, d2);
         } else {
            icamera.setPosition(d0, d1, d2);
         }

         if ((XH.isSkyEnabled() || XH.isSunMoonEnabled() || XH.isStarsEnabled()) && !bpq.isShadowPass) {
            this.setupFog(-1, partialTicks);
            this.mc.profiler.endStartSection("sky");
            yh.matrixMode(5889);
            yh.loadIdentity();
            Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.clipDistance);
            yh.matrixMode(5888);
            if (flag) {
               bpq.beginSky();
            }

            renderglobal.renderSky(partialTicks, pass);
            if (flag) {
               bpq.endSky();
            }

            yh.matrixMode(5889);
            yh.loadIdentity();
            Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.clipDistance);
            yh.matrixMode(5888);
         } else {
            yh.disableBlend();
         }

         this.setupFog(0, partialTicks);
         yh.shadeModel(7425);
         double var20 = entity.posY + (double)entity.getEyeHeight();
         nC var10002 = this.mc;
         if (var20 < 128.0 + (double)(nC.gameSettings.ofCloudsHeight * 128.0F)) {
            this.renderCloudsCheck(renderglobal, partialTicks, pass, d0, d1, d2);
         }

         this.mc.profiler.endStartSection("prepareterrain");
         this.setupFog(0, partialTicks);
         this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         yz.disableStandardItemLighting();
         this.mc.profiler.endStartSection("terrain_setup");
         var10004 = this.mc;
         this.checkLoadVisibleChunks(entity, partialTicks, icamera, nC.player.isSpectator());
         nC var10005;
         int var10006;
         double var23;
         if (flag) {
            var23 = (double)partialTicks;
            var10006 = this.frameCount++;
            var10005 = this.mc;
            bpr.setupTerrain(renderglobal, entity, var23, icamera, var10006, nC.player.isSpectator());
         } else {
            var23 = (double)partialTicks;
            var10006 = this.frameCount++;
            var10005 = this.mc;
            renderglobal.setupTerrain(entity, var23, icamera, var10006, nC.player.isSpectator());
         }

         if (pass == 0 || pass == 2) {
            this.mc.profiler.endStartSection("updatechunks");
            bmV.timerChunkUpload.start();
            this.mc.renderGlobal.updateChunks(finishTimeNano);
            bmV.timerChunkUpload.end();
         }

         this.mc.profiler.endStartSection("terrain");
         bmV.timerTerrain.start();
         var19 = this.mc;
         if (nC.gameSettings.ofSmoothFps && pass > 0) {
            this.mc.profiler.endStartSection("finish");
            GL11.glFinish();
            this.mc.profiler.endStartSection("terrain");
         }

         yh.matrixMode(5888);
         yh.pushMatrix();
         yh.disableAlpha();
         if (flag) {
            bpr.beginTerrainSolid();
         }

         renderglobal.renderBlockLayer(BlockRenderLayer.SOLID, (double)partialTicks, pass, entity);
         yh.enableAlpha();
         if (flag) {
            bpr.beginTerrainCutoutMipped();
         }

         yR var22 = this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE);
         var10002 = this.mc;
         var22.setBlurMipmap(false, nC.gameSettings.mipmapLevels > 0);
         renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT_MIPPED, (double)partialTicks, pass, entity);
         this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
         this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
         if (flag) {
            bpr.beginTerrainCutout();
         }

         renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT, (double)partialTicks, pass, entity);
         this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
         if (flag) {
            bpr.endTerrain();
         }

         bmV.timerTerrain.end();
         yh.shadeModel(7424);
         yh.alphaFunc(516, 0.1F);
         if (!this.debugView) {
            yh.matrixMode(5888);
            yh.popMatrix();
            yh.pushMatrix();
            yz.enableStandardItemLighting();
            this.mc.profiler.endStartSection("entities");
            if (bnK.ForgeHooksClient_setRenderPass.exists()) {
               bnK.callVoid(bnK.ForgeHooksClient_setRenderPass, 0);
            }

            renderglobal.renderEntities(entity, icamera, partialTicks);
            if (bnK.ForgeHooksClient_setRenderPass.exists()) {
               bnK.callVoid(bnK.ForgeHooksClient_setRenderPass, -1);
            }

            yz.disableStandardItemLighting();
            this.disableLightmap();
         }

         yh.matrixMode(5888);
         yh.popMatrix();
         if (flag1 && this.mc.objectMouseOver != null && !entity.isInsideOfMaterial(hM.WATER)) {
            ME entityplayer = (ME)entity;
            yh.disableAlpha();
            this.mc.profiler.endStartSection("outline");
            if (!bnK.ForgeHooksClient_onDrawBlockHighlight.exists() || !bnK.callBoolean(bnK.ForgeHooksClient_onDrawBlockHighlight, renderglobal, entityplayer, this.mc.objectMouseOver, 0, partialTicks)) {
               renderglobal.drawSelectionBox(entityplayer, this.mc.objectMouseOver, 0, partialTicks);
            }

            yh.enableAlpha();
         }

         if (this.mc.debugRenderer.shouldRender()) {
            boolean flag2 = yh.isFogEnabled();
            yh.disableFog();
            this.mc.debugRenderer.renderDebug(partialTicks, finishTimeNano);
            yh.setFogEnabled(flag2);
         }

         if (!renderglobal.damagedBlocks.isEmpty()) {
            this.mc.profiler.endStartSection("destroyProgress");
            yh.enableBlend();
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE, ya.ONE, xR.ZERO);
            this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            renderglobal.drawBlockDamageTexture(yN.getInstance(), yN.getInstance().getBuffer(), entity, partialTicks);
            this.mc.getTextureManager().getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            yh.disableBlend();
         }

         yh.tryBlendFuncSeparate(770, 771, 1, 0);
         yh.disableBlend();
         if (!this.debugView) {
            this.enableLightmap();
            this.mc.profiler.endStartSection("litParticles");
            if (flag) {
               bpq.beginLitParticles();
            }

            particlemanager.renderLitParticles(entity, partialTicks);
            yz.disableStandardItemLighting();
            this.setupFog(0, partialTicks);
            this.mc.profiler.endStartSection("particles");
            if (flag) {
               bpq.beginParticles();
            }

            particlemanager.renderParticles(entity, partialTicks);
            if (flag) {
               bpq.endParticles();
            }

            this.disableLightmap();
         }

         yh.depthMask(false);
         if (XH.isShaders()) {
            yh.depthMask(bpq.isRainDepth());
         }

         yh.enableCull();
         this.mc.profiler.endStartSection("weather");
         if (flag) {
            bpq.beginWeather();
         }

         this.renderRainSnow(partialTicks);
         if (flag) {
            bpq.endWeather();
         }

         yh.depthMask(true);
         renderglobal.renderWorldBorder(entity, partialTicks);
         if (flag) {
            bpr.renderHand0(this, partialTicks, pass);
            bpq.preWater();
         }

         yh.disableBlend();
         yh.enableCull();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.alphaFunc(516, 0.1F);
         this.setupFog(0, partialTicks);
         yh.enableBlend();
         yh.depthMask(false);
         this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         yh.shadeModel(7425);
         this.mc.profiler.endStartSection("translucent");
         if (flag) {
            bpq.beginWater();
         }

         renderglobal.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, (double)partialTicks, pass, entity);
         if (flag) {
            bpq.endWater();
         }

         if (bnK.ForgeHooksClient_setRenderPass.exists() && !this.debugView) {
            yz.enableStandardItemLighting();
            this.mc.profiler.endStartSection("entities");
            bnK.callVoid(bnK.ForgeHooksClient_setRenderPass, 1);
            this.mc.renderGlobal.renderEntities(entity, icamera, partialTicks);
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
            bnK.callVoid(bnK.ForgeHooksClient_setRenderPass, -1);
            yz.disableStandardItemLighting();
         }

         yh.shadeModel(7424);
         yh.depthMask(true);
         yh.enableCull();
         yh.disableBlend();
         yh.disableFog();
         var20 = entity.posY + (double)entity.getEyeHeight();
         var10002 = this.mc;
         if (var20 >= 128.0 + (double)(nC.gameSettings.ofCloudsHeight * 128.0F)) {
            this.mc.profiler.endStartSection("aboveClouds");
            this.renderCloudsCheck(renderglobal, partialTicks, pass, d0, d1, d2);
         }

         if (bnK.ForgeHooksClient_dispatchRenderLast.exists()) {
            this.mc.profiler.endStartSection("forge_render_last");
            bnK.callVoid(bnK.ForgeHooksClient_dispatchRenderLast, renderglobal, partialTicks);
         }

         0bz.method_Qm().method_Qn().post(new 0do(partialTicks));
         this.mc.profiler.endStartSection("hand");
         if (this.renderHand && !bpq.isShadowPass) {
            if (flag) {
               bpr.renderHand1(this, partialTicks, pass);
               bpq.renderCompositeFinal();
            }

            yh.clear(256);
            if (flag) {
               bpr.renderFPOverlay(this, partialTicks, pass);
            } else {
               this.renderHand(partialTicks, pass);
            }
         }

         if (flag) {
            bpq.endRender();
         }

      } else {
         System.out.println("RenderGlobal or ParticleManager or TextureManager is null!");
      }
   }

   private void renderCloudsCheck(yy renderGlobalIn, float partialTicks, int pass, double x, double y, double z) {
      nC var10000 = this.mc;
      if (nC.gameSettings.renderDistanceChunks >= 4 && !XH.isCloudsOff()) {
         var10000 = this.mc;
         if (bpq.shouldRenderClouds(nC.gameSettings)) {
            this.mc.profiler.endStartSection("clouds");
            yh.matrixMode(5889);
            yh.loadIdentity();
            Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.clipDistance * 4.0F);
            yh.matrixMode(5888);
            yh.pushMatrix();
            this.setupFog(0, partialTicks);
            renderGlobalIn.renderClouds(partialTicks, pass, x, y, z);
            yh.disableFog();
            yh.popMatrix();
            yh.matrixMode(5889);
            yh.loadIdentity();
            Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.clipDistance);
            yh.matrixMode(5888);
         }
      }

   }

   private void addRainParticles() {
      float f = this.mc.world.getRainStrength(1.0F);
      if (!XH.isRainFancy()) {
         f /= 2.0F;
      }

      if (f != 0.0F && XH.isRainSplash()) {
         this.random.setSeed((long)this.rendererUpdateCount * 312987231L);
         Ig entity = this.mc.getRenderViewEntity();
         bij world = this.mc.world;
         BlockPos blockpos = new BlockPos(entity);
         int i = true;
         double d0 = 0.0;
         double d1 = 0.0;
         double d2 = 0.0;
         int j = 0;
         int k = (int)(100.0F * f * f);
         nC var10000 = this.mc;
         if (nC.gameSettings.particleSetting == 1) {
            k >>= 1;
         } else {
            var10000 = this.mc;
            if (nC.gameSettings.particleSetting == 2) {
               k = 0;
            }
         }

         for(int l = 0; l < k; ++l) {
            BlockPos blockpos1 = ((bij)world).getPrecipitationHeight(blockpos.add(this.random.nextInt(10) - this.random.nextInt(10), 0, this.random.nextInt(10) - this.random.nextInt(10)));
            Zi biome = ((bij)world).getBiome(blockpos1);
            BlockPos blockpos2 = blockpos1.down();
            in iblockstate = ((bij)world).getBlockState(blockpos2);
            if (blockpos1.getY() <= blockpos.getY() + 10 && blockpos1.getY() >= blockpos.getY() - 10 && biome.canRain() && biome.getTemperature(blockpos1) >= 0.15F) {
               double d3 = this.random.nextDouble();
               double d4 = this.random.nextDouble();
               AxisAlignedBB axisalignedbb = iblockstate.getBoundingBox(world, blockpos2);
               if (iblockstate.getMaterial() != hM.LAVA && iblockstate.getBlock() != Nk.MAGMA) {
                  if (iblockstate.getMaterial() != hM.AIR) {
                     ++j;
                     if (this.random.nextInt(j) == 0) {
                        d0 = (double)blockpos2.getX() + d3;
                        d1 = (double)((float)blockpos2.getY() + 0.1F) + axisalignedbb.maxY - 1.0;
                        d2 = (double)blockpos2.getZ() + d4;
                     }

                     this.mc.world.spawnParticle(EnumParticleTypes.WATER_DROP, (double)blockpos2.getX() + d3, (double)((float)blockpos2.getY() + 0.1F) + axisalignedbb.maxY, (double)blockpos2.getZ() + d4, 0.0, 0.0, 0.0, new int[0]);
                  }
               } else {
                  this.mc.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)blockpos1.getX() + d3, (double)((float)blockpos1.getY() + 0.1F) - axisalignedbb.minY, (double)blockpos1.getZ() + d4, 0.0, 0.0, 0.0, new int[0]);
               }
            }
         }

         if (j > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
            this.rainSoundCounter = 0;
            if (d1 > (double)(blockpos.getY() + 1) && ((bij)world).getPrecipitationHeight(blockpos).getY() > MathHelper.floor((float)blockpos.getY())) {
               this.mc.world.playSound(d0, d1, d2, NO.WEATHER_RAIN_ABOVE, SoundCategory.WEATHER, 0.1F, 0.5F, false);
            } else {
               this.mc.world.playSound(d0, d1, d2, NO.WEATHER_RAIN, SoundCategory.WEATHER, 0.2F, 1.0F, false);
            }
         }
      }

   }

   protected void renderRainSnow(float partialTicks) {
      if (bnK.ForgeWorldProvider_getWeatherRenderer.exists()) {
         bil worldprovider = this.mc.world.provider;
         Object object = bnK.call(worldprovider, bnK.ForgeWorldProvider_getWeatherRenderer);
         if (object != null) {
            bnK.callVoid(object, bnK.IRenderHandler_render, partialTicks, this.mc.world, this.mc);
            return;
         }
      }

      float f5 = this.mc.world.getRainStrength(partialTicks);
      if (f5 > 0.0F) {
         if (XH.isRainOff()) {
            return;
         }

         this.enableLightmap();
         Ig entity = this.mc.getRenderViewEntity();
         bij world = this.mc.world;
         int i = MathHelper.floor(entity.posX);
         int j = MathHelper.floor(entity.posY);
         int k = MathHelper.floor(entity.posZ);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         yh.disableCull();
         yh.glNormal3f(0.0F, 1.0F, 0.0F);
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.alphaFunc(516, 0.1F);
         double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
         double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
         double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
         int l = MathHelper.floor(d1);
         int i1 = 5;
         if (XH.isRainFancy()) {
            i1 = 10;
         }

         int j1 = -1;
         float f = (float)this.rendererUpdateCount + partialTicks;
         bufferbuilder.setTranslation(-d0, -d1, -d2);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k1 = k - i1; k1 <= k + i1; ++k1) {
            for(int l1 = i - i1; l1 <= i + i1; ++l1) {
               int i2 = (k1 - k + 16) * 32 + l1 - i + 16;
               double d3 = (double)this.rainXCoords[i2] * 0.5;
               double d4 = (double)this.rainYCoords[i2] * 0.5;
               blockpos$mutableblockpos.setPos(l1, 0, k1);
               Zi biome = ((bij)world).getBiome(blockpos$mutableblockpos);
               if (biome.canRain() || biome.getEnableSnow()) {
                  int j2 = ((bij)world).getPrecipitationHeight(blockpos$mutableblockpos).getY();
                  int k2 = j - i1;
                  int l2 = j + i1;
                  if (k2 < j2) {
                     k2 = j2;
                  }

                  if (l2 < j2) {
                     l2 = j2;
                  }

                  int i3 = j2;
                  if (j2 < l) {
                     i3 = l;
                  }

                  if (k2 != l2) {
                     this.random.setSeed((long)(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761));
                     blockpos$mutableblockpos.setPos(l1, k2, k1);
                     float f1 = biome.getTemperature(blockpos$mutableblockpos);
                     double d5;
                     double d6;
                     double d7;
                     if (((bij)world).getBiomeProvider().getTemperatureAtHeight(f1, j2) >= 0.15F) {
                        if (j1 != 0) {
                           if (j1 >= 0) {
                              tessellator.draw();
                           }

                           j1 = 0;
                           this.mc.getTextureManager().bindTexture(RAIN_TEXTURES);
                           bufferbuilder.begin(7, zK.PARTICLE_POSITION_TEX_COLOR_LMAP);
                        }

                        d5 = -((double)(this.rendererUpdateCount + l1 * l1 * 3121 + l1 * 45238971 + k1 * k1 * 418711 + k1 * 13761 & 31) + (double)partialTicks) / 32.0 * (3.0 + this.random.nextDouble());
                        d6 = (double)((float)l1 + 0.5F) - entity.posX;
                        d7 = (double)((float)k1 + 0.5F) - entity.posZ;
                        float f2 = MathHelper.sqrt(d6 * d6 + d7 * d7) / (float)i1;
                        float f3 = ((1.0F - f2 * f2) * 0.5F + 0.5F) * f5;
                        blockpos$mutableblockpos.setPos(l1, i3, k1);
                        int j3 = ((bij)world).getCombinedLight(blockpos$mutableblockpos, 0);
                        int k3 = j3 >> 16 & '\uffff';
                        int l3 = j3 & '\uffff';
                        bufferbuilder.pos((double)l1 - d3 + 0.5, (double)l2, (double)k1 - d4 + 0.5).tex(0.0, (double)k2 * 0.25 + d5).color(1.0F, 1.0F, 1.0F, f3).lightmap(k3, l3).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5, (double)l2, (double)k1 + d4 + 0.5).tex(1.0, (double)k2 * 0.25 + d5).color(1.0F, 1.0F, 1.0F, f3).lightmap(k3, l3).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5, (double)k2, (double)k1 + d4 + 0.5).tex(1.0, (double)l2 * 0.25 + d5).color(1.0F, 1.0F, 1.0F, f3).lightmap(k3, l3).endVertex();
                        bufferbuilder.pos((double)l1 - d3 + 0.5, (double)k2, (double)k1 - d4 + 0.5).tex(0.0, (double)l2 * 0.25 + d5).color(1.0F, 1.0F, 1.0F, f3).lightmap(k3, l3).endVertex();
                     } else {
                        if (j1 != 1) {
                           if (j1 >= 0) {
                              tessellator.draw();
                           }

                           j1 = 1;
                           this.mc.getTextureManager().bindTexture(SNOW_TEXTURES);
                           bufferbuilder.begin(7, zK.PARTICLE_POSITION_TEX_COLOR_LMAP);
                        }

                        d5 = (double)(-((float)(this.rendererUpdateCount & 511) + partialTicks) / 512.0F);
                        d6 = this.random.nextDouble() + (double)f * 0.01 * (double)((float)this.random.nextGaussian());
                        d7 = this.random.nextDouble() + (double)(f * (float)this.random.nextGaussian()) * 0.001;
                        double d11 = (double)((float)l1 + 0.5F) - entity.posX;
                        double d12 = (double)((float)k1 + 0.5F) - entity.posZ;
                        float f6 = MathHelper.sqrt(d11 * d11 + d12 * d12) / (float)i1;
                        float f4 = ((1.0F - f6 * f6) * 0.3F + 0.5F) * f5;
                        blockpos$mutableblockpos.setPos(l1, i3, k1);
                        int i4 = (((bij)world).getCombinedLight(blockpos$mutableblockpos, 0) * 3 + 15728880) / 4;
                        int j4 = i4 >> 16 & '\uffff';
                        int k4 = i4 & '\uffff';
                        bufferbuilder.pos((double)l1 - d3 + 0.5, (double)l2, (double)k1 - d4 + 0.5).tex(0.0 + d6, (double)k2 * 0.25 + d5 + d7).color(1.0F, 1.0F, 1.0F, f4).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5, (double)l2, (double)k1 + d4 + 0.5).tex(1.0 + d6, (double)k2 * 0.25 + d5 + d7).color(1.0F, 1.0F, 1.0F, f4).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 + d3 + 0.5, (double)k2, (double)k1 + d4 + 0.5).tex(1.0 + d6, (double)l2 * 0.25 + d5 + d7).color(1.0F, 1.0F, 1.0F, f4).lightmap(j4, k4).endVertex();
                        bufferbuilder.pos((double)l1 - d3 + 0.5, (double)k2, (double)k1 - d4 + 0.5).tex(0.0 + d6, (double)l2 * 0.25 + d5 + d7).color(1.0F, 1.0F, 1.0F, f4).lightmap(j4, k4).endVertex();
                     }
                  }
               }
            }
         }

         if (j1 >= 0) {
            tessellator.draw();
         }

         bufferbuilder.setTranslation(0.0, 0.0, 0.0);
         yh.enableCull();
         yh.disableBlend();
         yh.alphaFunc(516, 0.1F);
         this.disableLightmap();
      }

   }

   public void setupOverlayRendering() {
      mC scaledresolution = new mC(this.mc);
      yh.clear(256);
      yh.matrixMode(5889);
      yh.loadIdentity();
      yh.ortho(0.0, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
      yh.matrixMode(5888);
      yh.loadIdentity();
      yh.translate(0.0F, 0.0F, -2000.0F);
   }

   private void updateFogColor(float partialTicks) {
      bij world = this.mc.world;
      Ig entity = this.mc.getRenderViewEntity();
      nC var10002 = this.mc;
      float f = 0.25F + 0.75F * (float)nC.gameSettings.renderDistanceChunks / 32.0F;
      f = 1.0F - (float)Math.pow((double)f, 0.25);
      Vec3d vec3d = ((bij)world).getSkyColor(this.mc.getRenderViewEntity(), partialTicks);
      vec3d = bjy.getWorldSkyColor(vec3d, world, this.mc.getRenderViewEntity(), partialTicks);
      float f1 = (float)vec3d.x;
      float f2 = (float)vec3d.y;
      float f3 = (float)vec3d.z;
      Vec3d vec3d1 = ((bij)world).getFogColor(partialTicks);
      vec3d1 = bjy.getWorldFogColor(vec3d1, world, this.mc.getRenderViewEntity(), partialTicks);
      this.fogColorRed = (float)vec3d1.x;
      this.fogColorGreen = (float)vec3d1.y;
      this.fogColorBlue = (float)vec3d1.z;
      nC var10000 = this.mc;
      float f12;
      if (nC.gameSettings.renderDistanceChunks >= 4) {
         double d0 = MathHelper.sin(((bij)world).getCelestialAngleRadians(partialTicks)) > 0.0F ? -1.0 : 1.0;
         Vec3d vec3d2 = new Vec3d(d0, 0.0, 0.0);
         f12 = (float)entity.getLook(partialTicks).dotProduct(vec3d2);
         if (f12 < 0.0F) {
            f12 = 0.0F;
         }

         if (f12 > 0.0F) {
            float[] afloat = world.provider.calcSunriseSunsetColors(((bij)world).getCelestialAngle(partialTicks), partialTicks);
            if (afloat != null) {
               f12 *= afloat[3];
               this.fogColorRed = this.fogColorRed * (1.0F - f12) + afloat[0] * f12;
               this.fogColorGreen = this.fogColorGreen * (1.0F - f12) + afloat[1] * f12;
               this.fogColorBlue = this.fogColorBlue * (1.0F - f12) + afloat[2] * f12;
            }
         }
      }

      this.fogColorRed += (f1 - this.fogColorRed) * f;
      this.fogColorGreen += (f2 - this.fogColorGreen) * f;
      this.fogColorBlue += (f3 - this.fogColorBlue) * f;
      float f8 = ((bij)world).getRainStrength(partialTicks);
      float f9;
      float f11;
      if (f8 > 0.0F) {
         f9 = 1.0F - f8 * 0.5F;
         f11 = 1.0F - f8 * 0.4F;
         this.fogColorRed *= f9;
         this.fogColorGreen *= f9;
         this.fogColorBlue *= f11;
      }

      f9 = ((bij)world).getThunderStrength(partialTicks);
      if (f9 > 0.0F) {
         f11 = 1.0F - f9 * 0.5F;
         this.fogColorRed *= f11;
         this.fogColorGreen *= f11;
         this.fogColorBlue *= f11;
      }

      in iblockstate1 = rF.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
      Vec3d vec3d7;
      if (this.cloudFog) {
         vec3d7 = ((bij)world).getCloudColour(partialTicks);
         this.fogColorRed = (float)vec3d7.x;
         this.fogColorGreen = (float)vec3d7.y;
         this.fogColorBlue = (float)vec3d7.z;
      } else if (bnK.ForgeBlock_getFogColor.exists()) {
         vec3d7 = rF.projectViewFromEntity(entity, (double)partialTicks);
         BlockPos blockpos = new BlockPos(vec3d7);
         in iblockstate = this.mc.world.getBlockState(blockpos);
         Vec3d vec3d3 = (Vec3d)bnK.call(iblockstate.getBlock(), bnK.ForgeBlock_getFogColor, this.mc.world, blockpos, iblockstate, entity, new Vec3d((double)this.fogColorRed, (double)this.fogColorGreen, (double)this.fogColorBlue), partialTicks);
         this.fogColorRed = (float)vec3d3.x;
         this.fogColorGreen = (float)vec3d3.y;
         this.fogColorBlue = (float)vec3d3.z;
      } else if (iblockstate1.getMaterial() == hM.WATER) {
         f12 = 0.0F;
         if (entity instanceof Iw) {
            f12 = (float)Ft.getRespirationModifier((Iw)entity) * 0.2F;
            f12 = XH.limit(f12, 0.0F, 0.6F);
            if (((Iw)entity).isPotionActive(NL.WATER_BREATHING)) {
               f12 = f12 * 0.3F + 0.6F;
            }
         }

         this.fogColorRed = 0.02F + f12;
         this.fogColorGreen = 0.02F + f12;
         this.fogColorBlue = 0.2F + f12;
      } else if (iblockstate1.getMaterial() == hM.LAVA) {
         this.fogColorRed = 0.6F;
         this.fogColorGreen = 0.1F;
         this.fogColorBlue = 0.0F;
      }

      if (iblockstate1.getMaterial() == hM.WATER) {
         vec3d7 = bjy.getUnderwaterColor(this.mc.world, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ);
         if (vec3d7 != null) {
            this.fogColorRed = (float)vec3d7.x;
            this.fogColorGreen = (float)vec3d7.y;
            this.fogColorBlue = (float)vec3d7.z;
         }
      } else if (iblockstate1.getMaterial() == hM.LAVA) {
         vec3d7 = bjy.getUnderlavaColor(this.mc.world, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ);
         if (vec3d7 != null) {
            this.fogColorRed = (float)vec3d7.x;
            this.fogColorGreen = (float)vec3d7.y;
            this.fogColorBlue = (float)vec3d7.z;
         }
      }

      f12 = this.fogColor2 + (this.fogColor1 - this.fogColor2) * partialTicks;
      this.fogColorRed *= f12;
      this.fogColorGreen *= f12;
      this.fogColorBlue *= f12;
      double d1 = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks) * world.provider.getVoidFogYFactor();
      if (entity instanceof Iw && ((Iw)entity).isPotionActive(NL.BLINDNESS)) {
         int i = ((Iw)entity).getActivePotionEffect(NL.BLINDNESS).getDuration();
         if (i < 20) {
            d1 *= (double)(1.0F - (float)i / 20.0F);
         } else {
            d1 = 0.0;
         }
      }

      if (d1 < 1.0) {
         if (d1 < 0.0) {
            d1 = 0.0;
         }

         d1 *= d1;
         this.fogColorRed = (float)((double)this.fogColorRed * d1);
         this.fogColorGreen = (float)((double)this.fogColorGreen * d1);
         this.fogColorBlue = (float)((double)this.fogColorBlue * d1);
      }

      float f15;
      if (this.bossColorModifier > 0.0F) {
         f15 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * partialTicks;
         this.fogColorRed = this.fogColorRed * (1.0F - f15) + this.fogColorRed * 0.7F * f15;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f15) + this.fogColorGreen * 0.6F * f15;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f15) + this.fogColorBlue * 0.6F * f15;
      }

      float f6;
      if (entity instanceof Iw && ((Iw)entity).isPotionActive(NL.NIGHT_VISION)) {
         f15 = this.getNightVisionBrightness((Iw)entity, partialTicks);
         f6 = 1.0F / this.fogColorRed;
         if (f6 > 1.0F / this.fogColorGreen) {
            f6 = 1.0F / this.fogColorGreen;
         }

         if (f6 > 1.0F / this.fogColorBlue) {
            f6 = 1.0F / this.fogColorBlue;
         }

         if (Float.isInfinite(f6)) {
            f6 = Math.nextAfter(f6, 0.0);
         }

         this.fogColorRed = this.fogColorRed * (1.0F - f15) + this.fogColorRed * f6 * f15;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f15) + this.fogColorGreen * f6 * f15;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f15) + this.fogColorBlue * f6 * f15;
      }

      var10000 = this.mc;
      if (nC.gameSettings.anaglyph) {
         f15 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
         f6 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
         float f7 = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
         this.fogColorRed = f15;
         this.fogColorGreen = f6;
         this.fogColorBlue = f7;
      }

      if (bnK.EntityViewRenderEvent_FogColors_Constructor.exists()) {
         Object object = bnK.newInstance(bnK.EntityViewRenderEvent_FogColors_Constructor, this, entity, iblockstate1, partialTicks, this.fogColorRed, this.fogColorGreen, this.fogColorBlue);
         bnK.postForgeBusEvent(object);
         this.fogColorRed = bnK.callFloat(object, bnK.EntityViewRenderEvent_FogColors_getRed);
         this.fogColorGreen = bnK.callFloat(object, bnK.EntityViewRenderEvent_FogColors_getGreen);
         this.fogColorBlue = bnK.callFloat(object, bnK.EntityViewRenderEvent_FogColors_getBlue);
      }

      bpq.setClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
   }

   private void setupFog(int startCoords, float partialTicks) {
      this.fogStandard = false;
      Ig entity = this.mc.getRenderViewEntity();
      this.setupFogColor(false);
      yh.glNormal3f(0.0F, -1.0F, 0.0F);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      in iblockstate = rF.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
      float f = -1.0F;
      if (bnK.ForgeHooksClient_getFogDensity.exists()) {
         f = bnK.callFloat(bnK.ForgeHooksClient_getFogDensity, this, entity, iblockstate, partialTicks, 0.1F);
      }

      if (f >= 0.0F) {
         yh.setFogDensity(f);
      } else {
         float f3;
         if (entity instanceof Iw && ((Iw)entity).isPotionActive(NL.BLINDNESS)) {
            f3 = 5.0F;
            int i = ((Iw)entity).getActivePotionEffect(NL.BLINDNESS).getDuration();
            if (i < 20) {
               f3 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - (float)i / 20.0F);
            }

            yh.setFog(xS.LINEAR);
            if (startCoords == -1) {
               yh.setFogStart(0.0F);
               yh.setFogEnd(f3 * 0.8F);
            } else {
               yh.setFogStart(f3 * 0.25F);
               yh.setFogEnd(f3);
            }

            if (GLContext.getCapabilities().GL_NV_fog_distance && XH.isFogFancy()) {
               yh.glFogi(34138, 34139);
            }
         } else if (this.cloudFog) {
            yh.setFog(xS.EXP);
            yh.setFogDensity(0.1F);
         } else if (iblockstate.getMaterial() == hM.WATER) {
            yh.setFog(xS.EXP);
            f3 = XH.isClearWater() ? 0.02F : 0.1F;
            if (entity instanceof Iw) {
               if (((Iw)entity).isPotionActive(NL.WATER_BREATHING)) {
                  yh.setFogDensity(0.01F);
               } else {
                  float f2 = 0.1F - (float)Ft.getRespirationModifier((Iw)entity) * 0.03F;
                  yh.setFogDensity(XH.limit(f2, 0.0F, f3));
               }
            } else {
               yh.setFogDensity(f3);
            }
         } else if (iblockstate.getMaterial() == hM.LAVA) {
            yh.setFog(xS.EXP);
            yh.setFogDensity(2.0F);
         } else {
            f3 = this.farPlaneDistance;
            this.fogStandard = true;
            yh.setFog(xS.LINEAR);
            if (startCoords == -1) {
               yh.setFogStart(0.0F);
               yh.setFogEnd(f3);
            } else {
               yh.setFogStart(f3 * XH.getFogStart());
               yh.setFogEnd(f3);
            }

            if (GLContext.getCapabilities().GL_NV_fog_distance) {
               if (XH.isFogFancy()) {
                  yh.glFogi(34138, 34139);
               }

               if (XH.isFogFast()) {
                  yh.glFogi(34138, 34140);
               }
            }

            if (this.mc.world.provider.doesXZShowFog((int)entity.posX, (int)entity.posZ) || this.mc.ingameGUI.getBossOverlay().shouldCreateFog()) {
               yh.setFogStart(f3 * 0.05F);
               yh.setFogEnd(f3);
            }

            if (bnK.ForgeHooksClient_onFogRender.exists()) {
               bnK.callVoid(bnK.ForgeHooksClient_onFogRender, this, entity, iblockstate, partialTicks, startCoords, f3);
            }
         }
      }

      yh.enableColorMaterial();
      yh.enableFog();
      yh.colorMaterial(1028, 4608);
   }

   public void setupFogColor(boolean black) {
      if (black) {
         yh.glFog(2918, this.setFogColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      } else {
         yh.glFog(2918, this.setFogColorBuffer(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
      }

   }

   private FloatBuffer setFogColorBuffer(float red, float green, float blue, float alpha) {
      if (XH.isShaders()) {
         bpq.setFogColor(red, green, blue);
      }

      this.fogColorBuffer.clear();
      this.fogColorBuffer.put(red).put(green).put(blue).put(alpha);
      this.fogColorBuffer.flip();
      return this.fogColorBuffer;
   }

   public void resetData() {
      this.itemActivationItem = null;
      this.mapItemRenderer.clearLoadedMaps();
   }

   public mo getMapItemRenderer() {
      return this.mapItemRenderer;
   }

   private void waitForServerThread() {
      this.serverWaitTimeCurrent = 0;
      if (XH.isSmoothWorld() && XH.isSingleProcessor()) {
         if (this.mc.isIntegratedServerRunning()) {
            WK integratedserver = this.mc.getIntegratedServer();
            if (integratedserver != null) {
               boolean flag = this.mc.isGamePaused();
               if (!flag && !(this.mc.currentScreen instanceof ke)) {
                  if (this.serverWaitTime > 0) {
                     bmV.timerServer.start();
                     XH.sleep((long)this.serverWaitTime);
                     bmV.timerServer.end();
                     this.serverWaitTimeCurrent = this.serverWaitTime;
                  }

                  long i = System.nanoTime() / 1000000L;
                  if (this.lastServerTime != 0L && this.lastServerTicks != 0) {
                     long j = i - this.lastServerTime;
                     if (j < 0L) {
                        this.lastServerTime = i;
                        j = 0L;
                     }

                     if (j >= 50L) {
                        this.lastServerTime = i;
                        int k = integratedserver.getTickCounter();
                        int l = k - this.lastServerTicks;
                        if (l < 0) {
                           this.lastServerTicks = k;
                           l = 0;
                        }

                        if (l < 1 && this.serverWaitTime < 100) {
                           this.serverWaitTime += 2;
                        }

                        if (l > 1 && this.serverWaitTime > 0) {
                           --this.serverWaitTime;
                        }

                        this.lastServerTicks = k;
                     }
                  } else {
                     this.lastServerTime = i;
                     this.lastServerTicks = integratedserver.getTickCounter();
                     this.avgServerTickDiff = 1.0F;
                     this.avgServerTimeDiff = 50.0F;
                  }
               } else {
                  if (this.mc.currentScreen instanceof ke) {
                     XH.sleep(20L);
                  }

                  this.lastServerTime = 0L;
                  this.lastServerTicks = 0;
               }
            }
         }
      } else {
         this.lastServerTime = 0L;
         this.lastServerTicks = 0;
      }

   }

   private void frameInit() {
      bmk.frameStart();
      if (!this.initialized) {
         bnT.resolve();
         bqS.registerResourceListener();
         if (XH.getBitsOs() == 64 && XH.getBitsJre() == 32) {
            XH.setNotify64BitJava(true);
         }

         this.initialized = true;
      }

      XH.checkDisplayMode();
      bij world = this.mc.world;
      if (world != null) {
         if (XH.getNewRelease() != null) {
            String s = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light");
            String s1 = s + " " + XH.getNewRelease();
            TextComponentString textcomponentstring = new TextComponentString(Ax.format("of.message.newVersion", "§n" + s1 + "§r"));
            textcomponentstring.setStyle((new Style()).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://optifine.net/downloads")));
            this.mc.ingameGUI.getChatGUI().printChatMessage(textcomponentstring);
            XH.setNewRelease((String)null);
         }

         if (XH.isNotify64BitJava()) {
            XH.setNotify64BitJava(false);
            TextComponentString textcomponentstring1 = new TextComponentString(Ax.format("of.message.java64Bit"));
            this.mc.ingameGUI.getChatGUI().printChatMessage(textcomponentstring1);
         }
      }

      if (this.mc.currentScreen instanceof 0cx) {
         this.updateMainMenu((0cx)this.mc.currentScreen);
      }

      if (this.updatedWorld != world) {
         bnx.worldChanged(this.updatedWorld, world);
         XH.updateThreadPriorities();
         this.lastServerTime = 0L;
         this.lastServerTicks = 0;
         this.updatedWorld = world;
      }

      if (!this.setFxaaShader(bpq.configAntialiasingLevel)) {
         bpq.configAntialiasingLevel = 0;
      }

      if (this.mc.currentScreen != null && this.mc.currentScreen.getClass() == jP.class) {
         this.mc.displayGuiScreen(new bmn((jP)this.mc.currentScreen));
      }

   }

   private void frameFinish() {
      if (this.mc.world != null && XH.isShowGlErrors() && bqU.isActive("CheckGlErrorFrameFinish", 10000L)) {
         int i = yh.glGetError();
         if (i != 0 && bmk.isEnabled(i)) {
            String s = XH.getGlErrorString(i);
            TextComponentString textcomponentstring = new TextComponentString(Ax.format("of.message.openglError", i, s));
            this.mc.ingameGUI.getChatGUI().printChatMessage(textcomponentstring);
         }
      }

   }

   private void updateMainMenu(0cx p_updateMainMenu_1_) {
      try {
         String s = null;
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         int i = calendar.get(5);
         int j = calendar.get(2) + 1;
         if (i == 8 && j == 4) {
            s = "Happy birthday, OptiFine!";
         }

         if (i == 14 && j == 8) {
            s = "Happy birthday, sp614x!";
         }

         if (s == null) {
            return;
         }

         bnK.setFieldValue(p_updateMainMenu_1_, bnK.GuiMainMenu_splashText, s);
      } catch (Throwable var6) {
      }

   }

   public boolean setFxaaShader(int p_setFxaaShader_1_) {
      if (!ys.isFramebufferEnabled()) {
         return false;
      } else if (this.shaderGroup != null && this.shaderGroup != this.fxaaShaders[2] && this.shaderGroup != this.fxaaShaders[4]) {
         return true;
      } else if (p_setFxaaShader_1_ != 2 && p_setFxaaShader_1_ != 4) {
         if (this.shaderGroup == null) {
            return true;
         } else {
            this.shaderGroup.deleteShaderGroup();
            this.shaderGroup = null;
            return true;
         }
      } else if (this.shaderGroup != null && this.shaderGroup == this.fxaaShaders[p_setFxaaShader_1_]) {
         return true;
      } else if (this.mc.world == null) {
         return true;
      } else {
         this.loadShader(new ResourceLocation("shaders/post/fxaa_of_" + p_setFxaaShader_1_ + "x.json"));
         this.fxaaShaders[p_setFxaaShader_1_] = this.shaderGroup;
         return this.useShader;
      }
   }

   private void checkLoadVisibleChunks(Ig p_checkLoadVisibleChunks_1_, float p_checkLoadVisibleChunks_2_, uO p_checkLoadVisibleChunks_3_, boolean p_checkLoadVisibleChunks_4_) {
      int i = 201435902;
      if (this.loadVisibleChunks) {
         this.loadVisibleChunks = false;
         this.loadAllVisibleChunks(p_checkLoadVisibleChunks_1_, (double)p_checkLoadVisibleChunks_2_, p_checkLoadVisibleChunks_3_, p_checkLoadVisibleChunks_4_);
         this.mc.ingameGUI.getChatGUI().deleteChatLine(i);
      }

      if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(38)) {
         nC var10000 = this.mc;
         if (nC.gameSettings.keyBindAdvancements.getKeyCode() == 38) {
            if (this.mc.currentScreen instanceof jx) {
               this.mc.displayGuiScreen((lg)null);
            }

            while(Keyboard.next()) {
            }
         }

         if (this.mc.currentScreen != null) {
            return;
         }

         this.loadVisibleChunks = true;
         TextComponentString textcomponentstring = new TextComponentString(Ax.format("of.message.loadingVisibleChunks"));
         this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(textcomponentstring, i);
         bnK.Minecraft_actionKeyF3.setValue(this.mc, Boolean.TRUE);
      }

   }

   private void loadAllVisibleChunks(Ig p_loadAllVisibleChunks_1_, double p_loadAllVisibleChunks_2_, uO p_loadAllVisibleChunks_4_, boolean p_loadAllVisibleChunks_5_) {
      nC var10000 = this.mc;
      int i = nC.gameSettings.ofChunkUpdates;
      var10000 = this.mc;
      boolean flag = nC.gameSettings.ofLazyChunkLoading;

      try {
         var10000 = this.mc;
         nC.gameSettings.ofChunkUpdates = 1000;
         var10000 = this.mc;
         nC.gameSettings.ofLazyChunkLoading = false;
         yy renderglobal = XH.getRenderGlobal();
         int j = renderglobal.getCountLoadedChunks();
         long k = System.currentTimeMillis();
         XH.dbg("Loading visible chunks");
         long l = System.currentTimeMillis() + 5000L;
         int i1 = 0;
         boolean flag1 = false;

         do {
            flag1 = false;

            for(int j1 = 0; j1 < 100; ++j1) {
               renderglobal.displayListEntitiesDirty = true;
               renderglobal.setupTerrain(p_loadAllVisibleChunks_1_, p_loadAllVisibleChunks_2_, p_loadAllVisibleChunks_4_, this.frameCount++, p_loadAllVisibleChunks_5_);
               if (!renderglobal.hasNoChunkUpdates()) {
                  flag1 = true;
               }

               i1 += renderglobal.getCountChunksToUpdate();

               while(!renderglobal.hasNoChunkUpdates()) {
                  renderglobal.updateChunks(System.nanoTime() + 1000000000L);
               }

               i1 -= renderglobal.getCountChunksToUpdate();
               if (!flag1) {
                  break;
               }
            }

            if (renderglobal.getCountLoadedChunks() != j) {
               flag1 = true;
               j = renderglobal.getCountLoadedChunks();
            }

            if (System.currentTimeMillis() > l) {
               XH.log("Chunks loaded: " + i1);
               l = System.currentTimeMillis() + 5000L;
            }
         } while(flag1);

         XH.log("Chunks loaded: " + i1);
         XH.log("Finished loading visible chunks");
         ug.renderChunksUpdated = 0;
      } finally {
         var10000 = this.mc;
         nC.gameSettings.ofChunkUpdates = i;
         var10000 = this.mc;
         nC.gameSettings.ofLazyChunkLoading = flag;
      }

   }

   public static void drawNameplate(jH fontRendererIn, String str, float x, float y, float z, int verticalShift, float viewerYaw, float viewerPitch, boolean isThirdPersonFrontal, boolean isSneaking) {
      yh.pushMatrix();
      yh.translate(x, y, z);
      yh.glNormal3f(0.0F, 1.0F, 0.0F);
      yh.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
      yh.rotate((float)(isThirdPersonFrontal ? -1 : 1) * viewerPitch, 1.0F, 0.0F, 0.0F);
      yh.scale(-0.025F, -0.025F, 0.025F);
      yh.disableLighting();
      yh.depthMask(false);
      if (!isSneaking) {
         yh.disableDepth();
      }

      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      int i = fontRendererIn.getStringWidth(str) / 2;
      yh.disableTexture2D();
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_COLOR);
      bufferbuilder.pos((double)(-i - 1), (double)(-1 + verticalShift), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      bufferbuilder.pos((double)(-i - 1), (double)(8 + verticalShift), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      bufferbuilder.pos((double)(i + 1), (double)(8 + verticalShift), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      bufferbuilder.pos((double)(i + 1), (double)(-1 + verticalShift), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      tessellator.draw();
      yh.enableTexture2D();
      if (!isSneaking) {
         fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, 553648127);
         yh.enableDepth();
      }

      yh.depthMask(true);
      fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, isSneaking ? 553648127 : -1);
      yh.enableLighting();
      yh.disableBlend();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.popMatrix();
   }

   public void displayItemActivation(Qy stack) {
      this.itemActivationItem = stack;
      this.itemActivationTicks = 40;
      this.itemActivationOffX = this.random.nextFloat() * 2.0F - 1.0F;
      this.itemActivationOffY = this.random.nextFloat() * 2.0F - 1.0F;
   }

   private void renderItemActivation(int p_190563_1_, int p_190563_2_, float p_190563_3_) {
      if (this.itemActivationItem != null && this.itemActivationTicks > 0) {
         int i = 40 - this.itemActivationTicks;
         float f = ((float)i + p_190563_3_) / 40.0F;
         float f1 = f * f;
         float f2 = f * f1;
         float f3 = 10.25F * f2 * f1 + -24.95F * f1 * f1 + 25.5F * f2 + -13.8F * f1 + 4.0F * f;
         float f4 = f3 * 3.1415927F;
         float f5 = this.itemActivationOffX * (float)(p_190563_1_ / 4);
         float f6 = this.itemActivationOffY * (float)(p_190563_2_ / 4);
         yh.enableAlpha();
         yh.pushMatrix();
         yh.pushAttrib();
         yh.enableDepth();
         yh.disableCull();
         yz.enableStandardItemLighting();
         yh.translate((float)(p_190563_1_ / 2) + f5 * MathHelper.abs(MathHelper.sin(f4 * 2.0F)), (float)(p_190563_2_ / 2) + f6 * MathHelper.abs(MathHelper.sin(f4 * 2.0F)), -50.0F);
         float f7 = 50.0F + 175.0F * MathHelper.sin(f4);
         yh.scale(f7, -f7, f7);
         yh.rotate(900.0F * MathHelper.abs(MathHelper.sin(f4)), 0.0F, 1.0F, 0.0F);
         yh.rotate(6.0F * MathHelper.cos(f * 8.0F), 1.0F, 0.0F, 0.0F);
         yh.rotate(6.0F * MathHelper.cos(f * 8.0F), 0.0F, 0.0F, 1.0F);
         this.mc.getRenderItem().renderItem(this.itemActivationItem, sf.FIXED);
         yh.popAttrib();
         yh.popMatrix();
         yz.disableStandardItemLighting();
         yh.enableCull();
         yh.disableDepth();
      }

   }

   static {
      SHADER_COUNT = SHADERS_TEXTURES.length;
   }
}
