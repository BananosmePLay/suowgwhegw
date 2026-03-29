package neo;

public class oF extends nH {
   public ow skeletonHead;

   public oF() {
      this(0, 35, 64, 64);
   }

   public oF(int p_i1155_1_, int p_i1155_2_, int p_i1155_3_, int p_i1155_4_) {
      this.textureWidth = p_i1155_3_;
      this.textureHeight = p_i1155_4_;
      this.skeletonHead = new ow(this, p_i1155_1_, p_i1155_2_);
      this.skeletonHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
      this.skeletonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.skeletonHead.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      this.skeletonHead.rotateAngleY = netHeadYaw * 0.017453292F;
      this.skeletonHead.rotateAngleX = headPitch * 0.017453292F;
   }
}
