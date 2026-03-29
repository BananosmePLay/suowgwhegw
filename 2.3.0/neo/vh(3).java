package neo;

import net.minecraft.util.ResourceLocation;

public class vh implements vw<JB> {
   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   private final vV creeperRenderer;
   private final nU creeperModel = new nU(2.0F);

   public vh(vV creeperRendererIn) {
      this.creeperRenderer = creeperRendererIn;
   }

   public void doRenderLayer(JB entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.getPowered()) {
         boolean flag = entitylivingbaseIn.isInvisible();
         yh.depthMask(!flag);
         this.creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
         yh.matrixMode(5890);
         yh.loadIdentity();
         float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
         yh.translate(f * 0.01F, f * 0.01F, 0.0F);
         yh.matrixMode(5888);
         yh.enableBlend();
         float f1 = 0.5F;
         yh.color(0.5F, 0.5F, 0.5F, 1.0F);
         yh.disableLighting();
         yh.blendFunc(ya.ONE, xR.ONE);
         this.creeperModel.setModelAttributes(this.creeperRenderer.getMainModel());
         nC.getMinecraft().entityRenderer.setupFogColor(true);
         this.creeperModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         nC.getMinecraft().entityRenderer.setupFogColor(false);
         yh.matrixMode(5890);
         yh.loadIdentity();
         yh.matrixMode(5888);
         yh.enableLighting();
         yh.disableBlend();
         yh.depthMask(flag);
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((JB)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
