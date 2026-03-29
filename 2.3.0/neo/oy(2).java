package neo;

public class oy extends ou {
   private float headRotationAngleX;

   public oy() {
      super(12, 0.0F);
      this.head = new ow(this, 0, 0);
      this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
      this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
      this.body = new ow(this, 28, 8);
      this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
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
