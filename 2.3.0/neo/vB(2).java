package neo;

import net.minecraft.util.ResourceLocation;

public class vB<T extends KW> implements vw<T> {
   private static final ResourceLocation SPIDER_EYES = new ResourceLocation("textures/entity/spider_eyes.png");
   private final xc<T> spiderRenderer;

   public vB(xc<T> spiderRendererIn) {
      this.spiderRenderer = spiderRendererIn;
   }

   public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.spiderRenderer.bindTexture(SPIDER_EYES);
      yh.enableBlend();
      yh.disableAlpha();
      yh.blendFunc(ya.ONE, xR.ONE);
      if (entitylivingbaseIn.isInvisible()) {
         yh.depthMask(false);
      } else {
         yh.depthMask(true);
      }

      int i = 61680;
      int j = i % 65536;
      int k = i / 65536;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      nC.getMinecraft().entityRenderer.setupFogColor(true);
      if (XH.isShaders()) {
         bpq.beginSpiderEyes();
      }

      XH.getRenderGlobal().renderOverlayEyes = true;
      this.spiderRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      XH.getRenderGlobal().renderOverlayEyes = false;
      if (XH.isShaders()) {
         bpq.endSpiderEyes();
      }

      nC.getMinecraft().entityRenderer.setupFogColor(false);
      i = entitylivingbaseIn.getBrightnessForRender();
      j = i % 65536;
      k = i / 65536;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, (float)j, (float)k);
      this.spiderRenderer.setLightmap(entitylivingbaseIn);
      yh.disableBlend();
      yh.enableAlpha();
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((KW)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
