package net.minecraft.client.renderer.entity;

import com.google.common.collect.Lists;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;
import neo.0I;
import neo.0J;
import neo.0T;
import neo.0U;
import neo.0m;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.Team;
import net.minecraft.src.Config;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.optifine.EmissiveTextures;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class RenderLivingBase<T extends EntityLivingBase> extends Render<T> {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
   public ModelBase mainModel;
   protected FloatBuffer brightnessBuffer = GLAllocation.createDirectFloatBuffer(4);
   protected List<LayerRenderer<T>> layerRenderers = Lists.newArrayList();
   protected boolean renderMarker;
   public static float NAME_TAG_RANGE = 64.0F;
   public static float NAME_TAG_RANGE_SNEAK = 32.0F;
   public EntityLivingBase renderEntity;
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

   public RenderLivingBase(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
      super(renderManagerIn);
      this.mainModel = modelBaseIn;
      this.shadowSize = shadowSizeIn;
      this.renderModelPushMatrix = this.mainModel instanceof ModelSpider;
   }

   public <V extends EntityLivingBase, U extends LayerRenderer<V>> boolean addLayer(U layer) {
      return this.layerRenderers.add(layer);
   }

   public ModelBase getMainModel() {
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

      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      this.mainModel.swingProgress = this.getSwingProgress(entity, partialTicks);
      this.mainModel.isRiding = entity.isRiding();
      this.mainModel.isChild = entity.isChild();

      try {
         float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
         float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
         float f2 = f1 - f;
         float f8;
         if (this.mainModel.isRiding && entity.getRidingEntity() instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity.getRidingEntity();
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

         GlStateManager.enableAlpha();
         this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
         this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);
         if (CustomEntityModels.isActive()) {
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
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            if (!this.renderMarker) {
               this.renderModel(entity, f6, f5, f8, f2, f7, f4, partialTicks);
            }

            if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) {
               this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }

            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
            if (flag1) {
               this.unsetScoreTeamColor();
            }
         } else {
            flag1 = this.setDoRenderBrightness(entity, partialTicks);
            this.renderModel(entity, f6, f5, f8, f2, f7, f4, partialTicks);
            if (flag1) {
               this.unsetBrightness();
            }

            GlStateManager.depthMask(true);
            if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) {
               this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }
         }

         GlStateManager.disableRescaleNormal();
      } catch (Exception var19) {
         Exception exception1 = var19;
         LOGGER.error("Couldn't render entity", exception1);
      }

      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableCull();
      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   public float prepareScale(T entitylivingbaseIn, float partialTicks) {
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      this.preRenderCallback(entitylivingbaseIn, partialTicks);
      float f = 0.0625F;
      GlStateManager.translate(0.0F, -1.501F, 0.0F);
      return 0.0625F;
   }

   protected boolean setScoreTeamColor(T entityLivingBaseIn) {
      GlStateManager.disableLighting();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      return true;
   }

   protected void unsetScoreTeamColor() {
      GlStateManager.enableLighting();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   protected void renderModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, float partialTicks) {
      boolean flag;
      boolean var10000;
      label34: {
         flag = this.isVisible(entitylivingbaseIn);
         if (!flag) {
            Minecraft.getMinecraft();
            if (!entitylivingbaseIn.isInvisibleToPlayer(Minecraft.player)) {
               var10000 = true;
               break label34;
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
            GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
         }

         0J eventPre = new 0J(entitylivingbaseIn, 0I.PRE);
         0m.call(eventPre);
         this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         if (entitylivingbaseIn instanceof EntityPlayer) {
            0T erp = new 0T((EntityPlayer)entitylivingbaseIn, () -> {
               this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               this.renderLayers(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            });
            0m.call(erp);
         }

         0J eventPost = new 0J(entitylivingbaseIn, 0I.POST);
         0m.call(eventPost);
         if (flag1) {
            GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
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
         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
         GlStateManager.enableTexture2D();
         GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GlStateManager.enableTexture2D();
         GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         this.brightnessBuffer.position(0);
         if (flag1) {
            this.brightnessBuffer.put(1.0F);
            this.brightnessBuffer.put(0.0F);
            this.brightnessBuffer.put(0.0F);
            this.brightnessBuffer.put(0.3F);
            if (Config.isShaders()) {
               Shaders.setEntityColor(1.0F, 0.0F, 0.0F, 0.3F);
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
            if (Config.isShaders()) {
               Shaders.setEntityColor(f2, f3, f4, 1.0F - f1);
            }
         }

         this.brightnessBuffer.flip();
         GlStateManager.glTexEnv(8960, 8705, this.brightnessBuffer);
         GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
         GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
         GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
         return true;
      }
   }

   protected void unsetBrightness() {
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_ALPHA, OpenGlHelper.GL_PRIMARY_COLOR);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_ALPHA, 770);
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
      GlStateManager.disableTexture2D();
      GlStateManager.bindTexture(0);
      GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      if (Config.isShaders()) {
         Shaders.setEntityColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

   }

   protected void renderLivingAt(T entityLivingBaseIn, double x, double y, double z) {
      GlStateManager.translate((float)x, (float)y, (float)z);
   }

   protected void applyRotations(T entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      if (entityLiving.deathTime > 0) {
         float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
         f = MathHelper.sqrt(f);
         if (f > 1.0F) {
            f = 1.0F;
         }

         GlStateManager.rotate(f * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
      } else {
         String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());
         if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer)entityLiving).isWearing(EnumPlayerModelParts.CAPE))) {
            GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
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
         LayerRenderer<T> layerrenderer = (LayerRenderer)var9.next();
         boolean flag = this.setBrightness(entitylivingbaseIn, partialTicks, layerrenderer.shouldCombineTextures());
         if (EmissiveTextures.isActive()) {
            EmissiveTextures.beginRender();
         }

         if (this.renderLayersPushMatrix) {
            GlStateManager.pushMatrix();
         }

         layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
         if (this.renderLayersPushMatrix) {
            GlStateManager.popMatrix();
         }

         if (EmissiveTextures.isActive()) {
            if (EmissiveTextures.hasEmissive()) {
               this.renderLayersPushMatrix = true;
               EmissiveTextures.beginRenderEmissive();
               GlStateManager.pushMatrix();
               layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
               GlStateManager.popMatrix();
               EmissiveTextures.endRenderEmissive();
            }

            EmissiveTextures.endRender();
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
      if (!Reflector.RenderLivingEvent_Specials_Pre_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Specials_Pre_Constructor, entity, this, x, y, z)) {
         if (this.canRenderName(entity)) {
            double d0 = entity.getDistanceSq(this.renderManager.renderViewEntity);
            float f = entity.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
            if (d0 < (double)(f * f)) {
               0U eventNameTag = new 0U(entity, entity.getDisplayName().getFormattedText());
               0m.call(eventNameTag);
               if (eventNameTag.isCancelled()) {
                  return;
               }

               String s = eventNameTag.getRenderedName();
               GlStateManager.alphaFunc(516, 0.1F);
               this.renderEntityName(entity, x, y, z, s, d0);
            }
         }

         if (Reflector.RenderLivingEvent_Specials_Post_Constructor.exists()) {
            Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Specials_Post_Constructor, entity, this, x, y, z);
         }
      }

   }

   protected boolean canRenderName(T entity) {
      Minecraft.getMinecraft();
      EntityPlayerSP entityplayersp = Minecraft.player;
      boolean flag = !entity.isInvisibleToPlayer(entityplayersp);
      if (entity != entityplayersp) {
         Team team = entity.getTeam();
         Team team1 = entityplayersp.getTeam();
         if (team != null) {
            Team.EnumVisible team$enumvisible = team.getNameTagVisibility();
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

      return Minecraft.isGuiEnabled() && entity != this.renderManager.renderViewEntity && flag && !entity.isBeingRidden();
   }

   public List<LayerRenderer<T>> getLayerRenderers() {
      return this.layerRenderers;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Entity var1) {
      return this.canRenderName((EntityLivingBase)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderName(Entity var1, double var2, double var4, double var6) {
      this.renderName((EntityLivingBase)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityLivingBase)var1, var2, var4, var6, var8, var9);
   }

   static {
      int[] aint = TEXTURE_BRIGHTNESS.getTextureData();

      for(int i = 0; i < 256; ++i) {
         aint[i] = -1;
      }

      TEXTURE_BRIGHTNESS.updateDynamicTexture();
   }
}
