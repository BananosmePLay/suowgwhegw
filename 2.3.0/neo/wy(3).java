package neo;

import com.google.common.collect.Lists;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class wy<T extends Iw> extends vI<T> {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final yP TEXTURE_BRIGHTNESS = new yP(16, 16);
   public nH mainModel;
   protected FloatBuffer brightnessBuffer = xE.createDirectFloatBuffer(4);
   protected List<vw<T>> layerRenderers = Lists.newArrayList();
   protected boolean renderMarker;
   public static float NAME_TAG_RANGE = 64.0F;
   public static float NAME_TAG_RANGE_SNEAK = 32.0F;
   public Iw renderEntity;
   public float renderLimbSwing;
   public float renderLimbSwingAmount;
   public float renderAgeInTicks;
   public float renderHeadYaw;
   public float renderHeadPitch;
   public float renderScaleFactor;
   public float renderPartialTicks;
   private boolean renderModelPushMatrix;
   private boolean renderLayersPushMatrix;
   public static final boolean animateModelLiving = Boolean.getBoolean("animate.model.living");

   public wy(wC renderManagerIn, nH modelBaseIn, float shadowSizeIn) {
      super(renderManagerIn);
      this.mainModel = modelBaseIn;
      this.shadowSize = shadowSizeIn;
      this.renderModelPushMatrix = this.mainModel instanceof oI;
   }

   public <V extends Iw, U extends vw<V>> boolean addLayer(U layer) {
      return this.layerRenderers.add(layer);
   }

   public nH getMainModel() {
      return this.mainModel;
   }

   protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
      float f;
      for(f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F) {
      }

      while(f >= 180.0F) {
         f -= 360.0F;
      }

      return prevYawOffset + partialTicks * f;
   }

   public void transformHeldFull3DItemLayer() {
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (animateModelLiving) {
         entity.limbSwingAmount = 1.0F;
      }

      yh.pushMatrix();
      yh.disableCull();
      this.mainModel.swingProgress = this.getSwingProgress(entity, partialTicks);
      this.mainModel.isRiding = entity.isRiding();
      this.mainModel.isChild = entity.isChild();

      try {
         float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
         float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
         float f2 = f1 - f;
         float f8;
         if (this.mainModel.isRiding && entity.getRidingEntity() instanceof Iw) {
            Iw entitylivingbase = (Iw)entity.getRidingEntity();
            f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            f2 = f1 - f;
            f8 = MathHelper.wrapDegrees(f2);
            if (f8 < -85.0F) {
               f8 = -85.0F;
            }

            if (f8 >= 85.0F) {
               f8 = 85.0F;
            }

            f = f1 - f8;
            if (f8 * f8 > 2500.0F) {
               f += f8 * 0.2F;
            }

            f2 = f1 - f;
         }

         float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
         this.renderLivingAt(entity, x, y, z);
         f8 = this.handleRotationFloat(entity, partialTicks);
         this.applyRotations(entity, f8, f, partialTicks);
         float f4 = this.prepareScale(entity, partialTicks);
         float f5 = 0.0F;
         float f6 = 0.0F;
         if (!entity.isRiding()) {
            f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
            f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
            if (entity.isChild()) {
               f6 *= 3.0F;
            }

            if (f5 > 1.0F) {
               f5 = 1.0F;
            }
         }

         yh.enableAlpha();
         this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
         this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);
         if (bki.isActive()) {
            this.renderLimbSwing = f6;
            this.renderLimbSwingAmount = f5;
            this.renderAgeInTicks = f8;
            this.renderHeadYaw = f2;
            this.renderHeadPitch = f7;
            this.renderScaleFactor = f4;
         }

         boolean flag1;
         if (this.renderOutlines) {
            flag1 = this.setScoreTeamColor(entity);
            yh.enableColorMaterial();
            yh.enableOutlineMode(this.getTeamColor(entity));
            if (!this.renderMarker) {
               this.renderModel(entity, f6, f5, f8, f2, f7, f4);
            }

            if (!(entity instanceof ME) || !((ME)entity).isSpectator()) {
               this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }

            yh.disableOutlineMode();
            yh.disableColorMaterial();
            if (flag1) {
               this.unsetScoreTeamColor();
            }
         } else {
            flag1 = this.setDoRenderBrightness(entity, partialTicks);
            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
            if (flag1) {
               this.unsetBrightness();
            }

            yh.depthMask(true);
            if (!(entity instanceof ME) || !((ME)entity).isSpectator()) {
               this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }
         }

         yh.disableRescaleNormal();
      } catch (Exception var19) {
         Exception exception1 = var19;
         LOGGER.error("Couldn't render entity", exception1);
      }

      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.enableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
      yh.enableCull();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   public float prepareScale(T entitylivingbaseIn, float partialTicks) {
      yh.enableRescaleNormal();
      yh.scale(-1.0F, -1.0F, 1.0F);
      this.preRenderCallback(entitylivingbaseIn, partialTicks);
      float f = 0.0625F;
      yh.translate(0.0F, -1.501F, 0.0F);
      return 0.0625F;
   }

   protected boolean setScoreTeamColor(T entityLivingBaseIn) {
      yh.disableLighting();
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.disableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
      return true;
   }

   protected void unsetScoreTeamColor() {
      yh.enableLighting();
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.enableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
   }

   protected void renderModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
      boolean var10000;
      boolean flag;
      label30: {
         flag = this.isVisible(entitylivingbaseIn);
         if (!flag) {
            nC.getMinecraft();
            if (!entitylivingbaseIn.isInvisibleToPlayer(nC.player)) {
               var10000 = true;
               break label30;
            }
         }

         var10000 = false;
      }

      boolean flag1 = var10000;
      if (flag || flag1) {
         if (!this.bindEntityTexture(entitylivingbaseIn)) {
            return;
         }

         if (flag1) {
            yh.enableBlendProfile(xZ.TRANSPARENT_MODEL);
         }

         this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         if (flag1) {
            yh.disableBlendProfile(xZ.TRANSPARENT_MODEL);
         }
      }

   }

   protected boolean isVisible(T p_193115_1_) {
      return !p_193115_1_.isInvisible() || this.renderOutlines;
   }

   protected boolean setDoRenderBrightness(T entityLivingBaseIn, float partialTicks) {
      return this.setBrightness(entityLivingBaseIn, partialTicks, true);
   }

   protected boolean setBrightness(T entitylivingbaseIn, float partialTicks, boolean combineTextures) {
      float f = entitylivingbaseIn.getBrightness();
      int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
      boolean flag = (i >> 24 & 255) > 0;
      boolean flag1 = entitylivingbaseIn.hurtTime > 0 || entitylivingbaseIn.deathTime > 0;
      if (!flag && !flag1) {
         return false;
      } else if (!flag && !combineTextures) {
         return false;
      } else {
         yh.setActiveTexture(ys.defaultTexUnit);
         yh.enableTexture2D();
         yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
         yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, 8448);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, ys.defaultTexUnit);
         yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.GL_PRIMARY_COLOR);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 7681);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, ys.defaultTexUnit);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
         yh.setActiveTexture(ys.lightmapTexUnit);
         yh.enableTexture2D();
         yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
         yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, ys.GL_INTERPOLATE);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, ys.GL_CONSTANT);
         yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.GL_PREVIOUS);
         yh.glTexEnvi(8960, ys.GL_SOURCE2_RGB, ys.GL_CONSTANT);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_OPERAND2_RGB, 770);
         yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 7681);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, ys.GL_PREVIOUS);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
         this.brightnessBuffer.position(0);
         if (flag1) {
            this.brightnessBuffer.put(1.0F);
            this.brightnessBuffer.put(0.0F);
            this.brightnessBuffer.put(0.0F);
            this.brightnessBuffer.put(0.3F);
            if (XH.isShaders()) {
               bpq.setEntityColor(1.0F, 0.0F, 0.0F, 0.3F);
            }
         } else {
            float f1 = (float)(i >> 24 & 255) / 255.0F;
            float f2 = (float)(i >> 16 & 255) / 255.0F;
            float f3 = (float)(i >> 8 & 255) / 255.0F;
            float f4 = (float)(i & 255) / 255.0F;
            this.brightnessBuffer.put(f2);
            this.brightnessBuffer.put(f3);
            this.brightnessBuffer.put(f4);
            this.brightnessBuffer.put(1.0F - f1);
            if (XH.isShaders()) {
               bpq.setEntityColor(f2, f3, f4, 1.0F - f1);
            }
         }

         this.brightnessBuffer.flip();
         yh.glTexEnv(8960, 8705, this.brightnessBuffer);
         yh.setActiveTexture(ys.GL_TEXTURE2);
         yh.enableTexture2D();
         yh.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
         yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
         yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, 8448);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, ys.GL_PREVIOUS);
         yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.lightmapTexUnit);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
         yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 7681);
         yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, ys.GL_PREVIOUS);
         yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
         yh.setActiveTexture(ys.defaultTexUnit);
         return true;
      }
   }

   protected void unsetBrightness() {
      yh.setActiveTexture(ys.defaultTexUnit);
      yh.enableTexture2D();
      yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
      yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, 8448);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, ys.defaultTexUnit);
      yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.GL_PRIMARY_COLOR);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 8448);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, ys.defaultTexUnit);
      yh.glTexEnvi(8960, ys.GL_SOURCE1_ALPHA, ys.GL_PRIMARY_COLOR);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
      yh.glTexEnvi(8960, ys.GL_OPERAND1_ALPHA, 770);
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
      yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, 8448);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, 5890);
      yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.GL_PREVIOUS);
      yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 8448);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, 5890);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.setActiveTexture(ys.GL_TEXTURE2);
      yh.disableTexture2D();
      yh.bindTexture(0);
      yh.glTexEnvi(8960, 8704, ys.GL_COMBINE);
      yh.glTexEnvi(8960, ys.GL_COMBINE_RGB, 8448);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_OPERAND1_RGB, 768);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_RGB, 5890);
      yh.glTexEnvi(8960, ys.GL_SOURCE1_RGB, ys.GL_PREVIOUS);
      yh.glTexEnvi(8960, ys.GL_COMBINE_ALPHA, 8448);
      yh.glTexEnvi(8960, ys.GL_OPERAND0_ALPHA, 770);
      yh.glTexEnvi(8960, ys.GL_SOURCE0_ALPHA, 5890);
      yh.setActiveTexture(ys.defaultTexUnit);
      if (XH.isShaders()) {
         bpq.setEntityColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

   }

   protected void renderLivingAt(T entityLivingBaseIn, double x, double y, double z) {
      yh.translate((float)x, (float)y, (float)z);
   }

   protected void applyRotations(T entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      yh.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      if (entityLiving.deathTime > 0) {
         float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
         f = MathHelper.sqrt(f);
         if (f > 1.0F) {
            f = 1.0F;
         }

         yh.rotate(f * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
      } else {
         String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());
         if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof ME) || ((ME)entityLiving).isWearing(MH.CAPE))) {
            yh.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
            yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }

   }

   protected float getSwingProgress(T livingBase, float partialTickTime) {
      return livingBase.getSwingProgress(partialTickTime);
   }

   protected float handleRotationFloat(T livingBase, float partialTicks) {
      return (float)livingBase.ticksExisted + partialTicks;
   }

   protected void renderLayers(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
      Iterator var9 = this.layerRenderers.iterator();

      while(var9.hasNext()) {
         vw<T> layerrenderer = (vw)var9.next();
         boolean flag = this.setBrightness(entitylivingbaseIn, partialTicks, layerrenderer.shouldCombineTextures());
         if (bjR.isActive()) {
            bjR.beginRender();
         }

         if (this.renderLayersPushMatrix) {
            yh.pushMatrix();
         }

         layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
         if (this.renderLayersPushMatrix) {
            yh.popMatrix();
         }

         if (bjR.isActive()) {
            if (bjR.hasEmissive()) {
               this.renderLayersPushMatrix = true;
               bjR.beginRenderEmissive();
               yh.pushMatrix();
               layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
               yh.popMatrix();
               bjR.endRenderEmissive();
            }

            bjR.endRender();
         }

         if (flag) {
            this.unsetBrightness();
         }
      }

   }

   protected float getDeathMaxRotation(T entityLivingBaseIn) {
      return 90.0F;
   }

   protected int getColorMultiplier(T entitylivingbaseIn, float lightBrightness, float partialTickTime) {
      return 0;
   }

   protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
   }

   public void renderName(T entity, double x, double y, double z) {
      if (!bnK.RenderLivingEvent_Specials_Pre_Constructor.exists() || !bnK.postForgeBusEvent(bnK.RenderLivingEvent_Specials_Pre_Constructor, entity, this, x, y, z)) {
         if (this.canRenderName(entity)) {
            double d0 = entity.getDistanceSq(this.renderManager.renderViewEntity);
            float f = entity.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
            if (d0 < (double)(f * f)) {
               String s = entity.getDisplayName().getFormattedText();
               yh.alphaFunc(516, 0.1F);
               this.renderEntityName(entity, x, y, z, s, d0);
            }
         }

         if (bnK.RenderLivingEvent_Specials_Post_Constructor.exists()) {
            bnK.postForgeBusEvent(bnK.RenderLivingEvent_Specials_Post_Constructor, entity, this, x, y, z);
         }
      }

   }

   protected boolean canRenderName(T entity) {
      nC.getMinecraft();
      jh entityplayersp = nC.player;
      boolean flag = !entity.isInvisibleToPlayer(entityplayersp);
      if (entity != entityplayersp) {
         WE team = entity.getTeam();
         WE team1 = entityplayersp.getTeam();
         if (team != null) {
            WD team$enumvisible = team.getNameTagVisibility();
            switch (team$enumvisible) {
               case ALWAYS:
                  return flag;
               case NEVER:
                  return false;
               case HIDE_FOR_OTHER_TEAMS:
                  return team1 == null ? flag : team.isSameTeam(team1) && (team.getSeeFriendlyInvisiblesEnabled() || flag);
               case HIDE_FOR_OWN_TEAM:
                  return team1 == null ? flag : !team.isSameTeam(team1) && flag;
               default:
                  return true;
            }
         }
      }

      return nC.isGuiEnabled() && entity != this.renderManager.renderViewEntity && flag && !entity.isBeingRidden();
   }

   public List<vw<T>> getLayerRenderers() {
      return this.layerRenderers;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Ig var1) {
      return this.canRenderName((Iw)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderName(Ig var1, double var2, double var4, double var6) {
      this.renderName((Iw)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Iw)var1, var2, var4, var6, var8, var9);
   }

   static {
      int[] aint = TEXTURE_BRIGHTNESS.getTextureData();

      for(int i = 0; i < 256; ++i) {
         aint[i] = -1;
      }

      TEXTURE_BRIGHTNESS.updateDynamicTexture();
   }
}
