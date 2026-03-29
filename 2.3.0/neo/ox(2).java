package neo;

public class ox extends ou {
   private float headRotationAngleX;

   public ox() {
      super(12, 0.0F);
      this.head = new ow(this, 0, 0);
      this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
      this.body = new ow(this, 28, 8);
      this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
      float f = 0.5F;
      this.leg1 = new ow(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
      this.leg2 = new ow(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
      this.leg3 = new ow(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
      this.leg4 = new ow(this, 0, 16);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
      this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
   }

   public void setLivingAnimations(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
      super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
      this.head.rotationPointY = 6.0F + ((Mb)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
      this.headRotationAngleX = ((Mb)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      this.head.rotateAngleX = this.headRotationAngleX;
   }
}
