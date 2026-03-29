package neo;

public class nY extends nH {
   private final ow cube;
   private final ow glass = new ow(this, "glass");
   private ow base;

   public nY(float p_i1170_1_, boolean renderBase) {
      this.glass.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      this.cube = new ow(this, "cube");
      this.cube.setTextureOffset(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      if (renderBase) {
         this.base = new ow(this, "base");
         this.base.setTextureOffset(0, 16).addBox(-6.0F, 0.0F, -6.0F, 12, 4, 12);
      }

   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      yh.pushMatrix();
      yh.scale(2.0F, 2.0F, 2.0F);
      yh.translate(0.0F, -0.5F, 0.0F);
      if (this.base != null) {
         this.base.render(scale);
      }

      yh.rotate(limbSwingAmount, 0.0F, 1.0F, 0.0F);
      yh.translate(0.0F, 0.8F + ageInTicks, 0.0F);
      yh.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      this.glass.render(scale);
      float f = 0.875F;
      yh.scale(0.875F, 0.875F, 0.875F);
      yh.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      yh.rotate(limbSwingAmount, 0.0F, 1.0F, 0.0F);
      this.glass.render(scale);
      yh.scale(0.875F, 0.875F, 0.875F);
      yh.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      yh.rotate(limbSwingAmount, 0.0F, 1.0F, 0.0F);
      this.cube.render(scale);
      yh.popMatrix();
   }
}
