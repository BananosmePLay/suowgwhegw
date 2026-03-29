package neo;

import net.minecraft.util.math.MathHelper;

public class oN extends nH {
   private final ow[] upperBodyParts;
   private final ow[] heads;

   public oN(float p_i46302_1_) {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.upperBodyParts = new ow[3];
      this.upperBodyParts[0] = new ow(this, 0, 16);
      this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, p_i46302_1_);
      this.upperBodyParts[1] = (new ow(this)).setTextureSize(this.textureWidth, this.textureHeight);
      this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
      this.upperBodyParts[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, p_i46302_1_);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, p_i46302_1_);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, p_i46302_1_);
      this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, p_i46302_1_);
      this.upperBodyParts[2] = new ow(this, 12, 22);
      this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, p_i46302_1_);
      this.heads = new ow[3];
      this.heads[0] = new ow(this, 0, 0);
      this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, p_i46302_1_);
      this.heads[1] = new ow(this, 32, 0);
      this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
      this.heads[1].rotationPointX = -8.0F;
      this.heads[1].rotationPointY = 4.0F;
      this.heads[2] = new ow(this, 32, 0);
      this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
      this.heads[2].rotationPointX = 10.0F;
      this.heads[2].rotationPointY = 4.0F;
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      ow[] var8 = this.heads;
      int var9 = var8.length;

      int var10;
      ow modelrenderer1;
      for(var10 = 0; var10 < var9; ++var10) {
         modelrenderer1 = var8[var10];
         modelrenderer1.render(scale);
      }

      var8 = this.upperBodyParts;
      var9 = var8.length;

      for(var10 = 0; var10 < var9; ++var10) {
         modelrenderer1 = var8[var10];
         modelrenderer1.render(scale);
      }

   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      float f = MathHelper.cos(ageInTicks * 0.1F);
      this.upperBodyParts[1].rotateAngleX = (0.065F + 0.05F * f) * 3.1415927F;
      this.upperBodyParts[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].rotateAngleX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].rotateAngleX) * 10.0F);
      this.upperBodyParts[2].rotateAngleX = (0.265F + 0.1F * f) * 3.1415927F;
      this.heads[0].rotateAngleY = netHeadYaw * 0.017453292F;
      this.heads[0].rotateAngleX = headPitch * 0.017453292F;
   }

   public void setLivingAnimations(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
      HV entitywither = (HV)entitylivingbaseIn;

      for(int i = 1; i < 3; ++i) {
         this.heads[i].rotateAngleY = (entitywither.getHeadYRotation(i - 1) - entitylivingbaseIn.renderYawOffset) * 0.017453292F;
         this.heads[i].rotateAngleX = entitywither.getHeadXRotation(i - 1) * 0.017453292F;
      }

   }
}
