package neo;

public class vj implements vw<jf> {
   private final wM playerRenderer;

   public vj(wM playerRendererIn) {
      this.playerRenderer = playerRendererIn;
   }

   public void doRenderLayer(jf entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if ("deadmau5".equals(entitylivingbaseIn.getName()) && entitylivingbaseIn.hasSkin() && !entitylivingbaseIn.isInvisible()) {
         this.playerRenderer.bindTexture(entitylivingbaseIn.getLocationSkin());

         for(int i = 0; i < 2; ++i) {
            float f = entitylivingbaseIn.prevRotationYaw + (entitylivingbaseIn.rotationYaw - entitylivingbaseIn.prevRotationYaw) * partialTicks - (entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks);
            float f1 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTicks;
            yh.pushMatrix();
            yh.rotate(f, 0.0F, 1.0F, 0.0F);
            yh.rotate(f1, 1.0F, 0.0F, 0.0F);
            yh.translate(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
            yh.translate(0.0F, -0.375F, 0.0F);
            yh.rotate(-f1, 1.0F, 0.0F, 0.0F);
            yh.rotate(-f, 0.0F, 1.0F, 0.0F);
            float f2 = 1.3333334F;
            yh.scale(1.3333334F, 1.3333334F, 1.3333334F);
            this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625F);
            yh.popMatrix();
         }
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((jf)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
