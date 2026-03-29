package neo;

import net.minecraft.util.ResourceLocation;

public class vC implements vw<KX> {
   private static final ResourceLocation STRAY_CLOTHES_TEXTURES = new ResourceLocation("textures/entity/skeleton/stray_overlay.png");
   private final wy<?> renderer;
   private final oE layerModel = new oE(0.25F, true);

   public vC(wy<?> p_i47183_1_) {
      this.renderer = p_i47183_1_;
   }

   public void doRenderLayer(KX entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.layerModel.setModelAttributes(this.renderer.getMainModel());
      this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.renderer.bindTexture(STRAY_CLOTHES_TEXTURES);
      this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
   }

   public boolean shouldCombineTextures() {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((KX)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
