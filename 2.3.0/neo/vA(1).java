package neo;

public class vA implements vw<KO> {
   private final xa snowManRenderer;

   public vA(xa snowManRendererIn) {
      this.snowManRenderer = snowManRendererIn;
   }

   public void doRenderLayer(KO entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isPumpkinEquipped()) {
         yh.pushMatrix();
         this.snowManRenderer.getMainModel().head.postRender(0.0625F);
         float f = 0.625F;
         yh.translate(0.0F, -0.34375F, 0.0F);
         yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         yh.scale(0.625F, -0.625F, -0.625F);
         nC.getMinecraft().getItemRenderer().renderItem(entitylivingbaseIn, new Qy(Nk.PUMPKIN, 1), sf.HEAD);
         yh.popMatrix();
      }

   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((KO)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
