package neo;

public class vq implements vw<JJ> {
   private final wa endermanRenderer;

   public vq(wa endermanRendererIn) {
      this.endermanRenderer = endermanRendererIn;
   }

   public void doRenderLayer(JJ entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      in iblockstate = entitylivingbaseIn.getHeldBlockState();
      if (iblockstate != null) {
         tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
         yh.enableRescaleNormal();
         yh.pushMatrix();
         yh.translate(0.0F, 0.6875F, -0.75F);
         yh.rotate(20.0F, 1.0F, 0.0F, 0.0F);
         yh.rotate(45.0F, 0.0F, 1.0F, 0.0F);
         yh.translate(0.25F, 0.1875F, 0.25F);
         float f = 0.5F;
         yh.scale(-0.5F, -0.5F, 0.5F);
         int i = entitylivingbaseIn.getBrightnessForRender();
         int j = i % 65536;
         int k = i / 65536;
         ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.endermanRenderer.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);
         yh.popMatrix();
         yh.disableRescaleNormal();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((JJ)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
