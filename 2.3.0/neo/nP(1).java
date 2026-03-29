package neo;

import net.minecraft.util.math.MathHelper;

public class nP extends nH {
   public ow coverRight = (new ow(this)).setTextureOffset(0, 0).addBox(-6.0F, -5.0F, 0.0F, 6, 10, 0);
   public ow coverLeft = (new ow(this)).setTextureOffset(16, 0).addBox(0.0F, -5.0F, 0.0F, 6, 10, 0);
   public ow pagesRight = (new ow(this)).setTextureOffset(0, 10).addBox(0.0F, -4.0F, -0.99F, 5, 8, 1);
   public ow pagesLeft = (new ow(this)).setTextureOffset(12, 10).addBox(0.0F, -4.0F, -0.01F, 5, 8, 1);
   public ow flippingPageRight = (new ow(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
   public ow flippingPageLeft = (new ow(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
   public ow bookSpine = (new ow(this)).setTextureOffset(12, 0).addBox(-1.0F, -5.0F, 0.0F, 2, 10, 0);

   public nP() {
      this.coverRight.setRotationPoint(0.0F, 0.0F, -1.0F);
      this.coverLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
      this.bookSpine.rotateAngleY = 1.5707964F;
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.coverRight.render(scale);
      this.coverLeft.render(scale);
      this.bookSpine.render(scale);
      this.pagesRight.render(scale);
      this.pagesLeft.render(scale);
      this.flippingPageRight.render(scale);
      this.flippingPageLeft.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      float f = (MathHelper.sin(limbSwing * 0.02F) * 0.1F + 1.25F) * netHeadYaw;
      this.coverRight.rotateAngleY = 3.1415927F + f;
      this.coverLeft.rotateAngleY = -f;
      this.pagesRight.rotateAngleY = f;
      this.pagesLeft.rotateAngleY = -f;
      this.flippingPageRight.rotateAngleY = f - f * 2.0F * limbSwingAmount;
      this.flippingPageLeft.rotateAngleY = f - f * 2.0F * ageInTicks;
      this.pagesRight.rotationPointX = MathHelper.sin(f);
      this.pagesLeft.rotationPointX = MathHelper.sin(f);
      this.flippingPageRight.rotationPointX = MathHelper.sin(f);
      this.flippingPageLeft.rotationPointX = MathHelper.sin(f);
   }
}
