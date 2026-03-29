package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class wM extends wy<jf> {
   private final boolean smallArms;

   public wM(wC renderManager) {
      this(renderManager, false);
   }

   public wM(wC renderManager, boolean useSmallArms) {
      super(renderManager, new os(0.0F, useSmallArms), 0.5F);
      this.smallArms = useSmallArms;
      this.addLayer(new vf(this));
      this.addLayer(new vr(this));
      this.addLayer(new vd(this));
      this.addLayer(new vj(this));
      this.addLayer(new vg(this));
      this.addLayer(new vi(this.getMainModel().bipedHead));
      this.addLayer(new vk(this));
      this.addLayer(new vp(renderManager));
   }

   public os getMainModel() {
      return (os)super.getMainModel();
   }

   public void doRender(jf entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!entity.isUser() || this.renderManager.renderViewEntity == entity) {
         double d0 = y;
         if (entity.isSneaking()) {
            d0 = y - 0.125;
         }

         this.setModelVisibilities(entity);
         yh.enableBlendProfile(xZ.PLAYER_SKIN);
         super.doRender((Iw)entity, x, d0, z, entityYaw, partialTicks);
         yh.disableBlendProfile(xZ.PLAYER_SKIN);
      }

   }

   private void setModelVisibilities(jf clientPlayer) {
      os modelplayer = this.getMainModel();
      if (clientPlayer.isSpectator()) {
         modelplayer.setVisible(false);
         modelplayer.bipedHead.showModel = true;
         modelplayer.bipedHeadwear.showModel = true;
      } else {
         Qy itemstack = clientPlayer.getHeldItemMainhand();
         Qy itemstack1 = clientPlayer.getHeldItemOffhand();
         modelplayer.setVisible(true);
         modelplayer.bipedHeadwear.showModel = clientPlayer.isWearing(MH.HAT);
         modelplayer.bipedBodyWear.showModel = clientPlayer.isWearing(MH.JACKET);
         modelplayer.bipedLeftLegwear.showModel = clientPlayer.isWearing(MH.LEFT_PANTS_LEG);
         modelplayer.bipedRightLegwear.showModel = clientPlayer.isWearing(MH.RIGHT_PANTS_LEG);
         modelplayer.bipedLeftArmwear.showModel = clientPlayer.isWearing(MH.LEFT_SLEEVE);
         modelplayer.bipedRightArmwear.showModel = clientPlayer.isWearing(MH.RIGHT_SLEEVE);
         modelplayer.isSneak = clientPlayer.isSneaking();
         nL modelbiped$armpose = nL.EMPTY;
         nL modelbiped$armpose1 = nL.EMPTY;
         Ol enumaction1;
         if (!itemstack.isEmpty()) {
            modelbiped$armpose = nL.ITEM;
            if (clientPlayer.getItemInUseCount() > 0) {
               enumaction1 = itemstack.getItemUseAction();
               if (enumaction1 == Ol.BLOCK) {
                  modelbiped$armpose = nL.BLOCK;
               } else if (enumaction1 == Ol.BOW) {
                  modelbiped$armpose = nL.BOW_AND_ARROW;
               }
            }
         }

         if (!itemstack1.isEmpty()) {
            modelbiped$armpose1 = nL.ITEM;
            if (clientPlayer.getItemInUseCount() > 0) {
               enumaction1 = itemstack1.getItemUseAction();
               if (enumaction1 == Ol.BLOCK) {
                  modelbiped$armpose1 = nL.BLOCK;
               }
            }
         }

         if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT) {
            modelplayer.rightArmPose = modelbiped$armpose;
            modelplayer.leftArmPose = modelbiped$armpose1;
         } else {
            modelplayer.rightArmPose = modelbiped$armpose1;
            modelplayer.leftArmPose = modelbiped$armpose;
         }
      }

   }

   public ResourceLocation getEntityTexture(jf entity) {
      return entity.getLocationSkin();
   }

   public void transformHeldFull3DItemLayer() {
      yh.translate(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(jf entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      yh.scale(0.9375F, 0.9375F, 0.9375F);
   }

   protected void renderEntityName(jf entityIn, double x, double y, double z, String name, double distanceSq) {
      if (distanceSq < 100.0) {
         Ws scoreboard = entityIn.getWorldScoreboard();
         Wz scoreobjective = scoreboard.getObjectiveInDisplaySlot(2);
         if (scoreobjective != null) {
            Wr score = scoreboard.getOrCreateScore(entityIn.getName(), scoreobjective);
            this.renderLivingLabel(entityIn, score.getScorePoints() + " " + scoreobjective.getDisplayName(), x, y, z, 64);
            y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.025F);
         }
      }

      super.renderEntityName(entityIn, x, y, z, name, distanceSq);
   }

   public void renderRightArm(jf clientPlayer) {
      float f = 1.0F;
      yh.color(1.0F, 1.0F, 1.0F);
      float f1 = 0.0625F;
      os modelplayer = this.getMainModel();
      this.setModelVisibilities(clientPlayer);
      yh.enableBlend();
      modelplayer.swingProgress = 0.0F;
      modelplayer.isSneak = false;
      modelplayer.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
      modelplayer.bipedRightArm.rotateAngleX = 0.0F;
      modelplayer.bipedRightArm.render(0.0625F);
      modelplayer.bipedRightArmwear.rotateAngleX = 0.0F;
      modelplayer.bipedRightArmwear.render(0.0625F);
      yh.disableBlend();
   }

   public void renderLeftArm(jf clientPlayer) {
      float f = 1.0F;
      yh.color(1.0F, 1.0F, 1.0F);
      float f1 = 0.0625F;
      os modelplayer = this.getMainModel();
      this.setModelVisibilities(clientPlayer);
      yh.enableBlend();
      modelplayer.isSneak = false;
      modelplayer.swingProgress = 0.0F;
      modelplayer.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
      modelplayer.bipedLeftArm.rotateAngleX = 0.0F;
      modelplayer.bipedLeftArm.render(0.0625F);
      modelplayer.bipedLeftArmwear.rotateAngleX = 0.0F;
      modelplayer.bipedLeftArmwear.render(0.0625F);
      yh.disableBlend();
   }

   protected void renderLivingAt(jf entityLivingBaseIn, double x, double y, double z) {
      if (entityLivingBaseIn.isEntityAlive() && entityLivingBaseIn.isPlayerSleeping()) {
         super.renderLivingAt(entityLivingBaseIn, x + (double)entityLivingBaseIn.renderOffsetX, y + (double)entityLivingBaseIn.renderOffsetY, z + (double)entityLivingBaseIn.renderOffsetZ);
      } else {
         super.renderLivingAt(entityLivingBaseIn, x, y, z);
      }

   }

   protected void applyRotations(jf entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.isEntityAlive() && entityLiving.isPlayerSleeping()) {
         yh.rotate(entityLiving.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
         yh.rotate(this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
         yh.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if (entityLiving.isElytraFlying()) {
         super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
         float f = (float)entityLiving.getTicksElytraFlying() + partialTicks;
         float f1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
         yh.rotate(f1 * (-90.0F - entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
         Vec3d vec3d = entityLiving.getLook(partialTicks);
         double d0 = entityLiving.motionX * entityLiving.motionX + entityLiving.motionZ * entityLiving.motionZ;
         double d1 = vec3d.x * vec3d.x + vec3d.z * vec3d.z;
         if (d0 > 0.0 && d1 > 0.0) {
            double d2 = (entityLiving.motionX * vec3d.x + entityLiving.motionZ * vec3d.z) / (Math.sqrt(d0) * Math.sqrt(d1));
            double d3 = entityLiving.motionX * vec3d.z - entityLiving.motionZ * vec3d.x;
            yh.rotate((float)(Math.signum(d3) * Math.acos(d2)) * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
         }
      } else {
         super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((jf)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((jf)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderLivingAt(Iw var1, double var2, double var4, double var6) {
      this.renderLivingAt((jf)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((jf)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((jf)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderEntityName(Ig var1, double var2, double var4, double var6, String var8, double var9) {
      this.renderEntityName((jf)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((jf)var1, var2, var4, var6, var8, var9);
   }
}
