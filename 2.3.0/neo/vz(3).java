package neo;

public class vz implements vw<KN> {
   private final wY slimeRenderer;
   private final nH slimeModel = new oG(0);

   public vz(wY slimeRendererIn) {
      this.slimeRenderer = slimeRendererIn;
   }

   public void doRenderLayer(KN entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (!entitylivingbaseIn.isInvisible()) {
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableNormalize();
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
         this.slimeModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         yh.disableBlend();
         yh.disableNormalize();
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((KN)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
