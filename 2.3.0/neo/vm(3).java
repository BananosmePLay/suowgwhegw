package neo;

import net.minecraft.util.ResourceLocation;

public class vm implements vw<HS> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
   private final vW dragonRenderer;

   public vm(vW dragonRendererIn) {
      this.dragonRenderer = dragonRendererIn;
   }

   public void doRenderLayer(HS entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.dragonRenderer.bindTexture(TEXTURE);
      yh.enableBlend();
      yh.disableAlpha();
      yh.blendFunc(ya.ONE, xR.ONE);
      yh.disableLighting();
      yh.depthFunc(514);
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
      this.dragonRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      XH.getRenderGlobal().renderOverlayEyes = false;
      if (XH.isShaders()) {
         bpq.endSpiderEyes();
      }

      nC.getMinecraft().entityRenderer.setupFogColor(false);
      this.dragonRenderer.setLightmap(entitylivingbaseIn);
      yh.disableBlend();
      yh.enableAlpha();
      yh.depthFunc(515);
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((HS)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
