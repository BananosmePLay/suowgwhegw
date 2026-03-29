package neo;

public class ol extends nH {
   private final ow main;

   public ol() {
      this(0.0F);
   }

   public ol(float p_i47225_1_) {
      this.main = new ow(this);
      int i = true;
      this.main.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, 0.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 0.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -4.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(2.0F, 0.0F, 0.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(0.0F, 2.0F, 0.0F, 2, 2, 2, p_i47225_1_);
      this.main.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 2.0F, 2, 2, 2, p_i47225_1_);
      this.main.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.main.render(scale);
   }
}
