package neo;

public class vt implements vw<Kj> {
   private final ws ironGolemRenderer;

   public vt(ws ironGolemRendererIn) {
      this.ironGolemRenderer = ironGolemRendererIn;
   }

   public void doRenderLayer(Kj entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.getHoldRoseTick() != 0) {
         tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
         yh.enableRescaleNormal();
         yh.pushMatrix();
         yh.rotate(5.0F + 180.0F * ((oh)this.ironGolemRenderer.getMainModel()).ironGolemRightArm.rotateAngleX / 3.1415927F, 1.0F, 0.0F, 0.0F);
         yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         yh.translate(-0.9375F, -0.625F, -0.9375F);
         float f = 0.5F;
         yh.scale(0.5F, -0.5F, 0.5F);
         int i = entitylivingbaseIn.getBrightnessForRender();
         int j = i % 65536;
         int k = i / 65536;
         ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.ironGolemRenderer.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         blockrendererdispatcher.renderBlockBrightness(Nk.RED_FLOWER.getDefaultState(), 1.0F);
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
      this.doRenderLayer((Kj)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
