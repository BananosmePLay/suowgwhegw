package neo;

import net.minecraft.util.ResourceLocation;

public class vn implements vw<JJ> {
   private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
   private final wa endermanRenderer;

   public vn(wa endermanRendererIn) {
      this.endermanRenderer = endermanRendererIn;
   }

   public void doRenderLayer(JJ entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
      yh.enableBlend();
      yh.disableAlpha();
      yh.blendFunc(ya.ONE, xR.ONE);
      yh.disableLighting();
      yh.depthMask(!entitylivingbaseIn.isInvisible());
      int i = '\uf0f0';
      int j = '\uf0f0';
      int k = false;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, 61680.0F, 0.0F);
      yh.enableLighting();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      nC.getMinecraft().entityRenderer.setupFogColor(true);
      if (XH.isShaders()) {
         bpq.beginSpiderEyes();
      }

      XH.getRenderGlobal().renderOverlayEyes = true;
      this.endermanRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      XH.getRenderGlobal().renderOverlayEyes = false;
      if (XH.isShaders()) {
         bpq.endSpiderEyes();
      }

      nC.getMinecraft().entityRenderer.setupFogColor(false);
      this.endermanRenderer.setLightmap(entitylivingbaseIn);
      yh.depthMask(true);
      yh.disableBlend();
      yh.enableAlpha();
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
