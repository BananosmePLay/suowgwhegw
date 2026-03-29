package neo;

import net.minecraft.util.ResourceLocation;

public class vx implements vw<LQ> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   private final wJ pigRenderer;
   private final or pigModel = new or(0.5F);

   public vx(wJ pigRendererIn) {
      this.pigRenderer = pigRendererIn;
   }

   public void doRenderLayer(LQ entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.getSaddled()) {
         this.pigRenderer.bindTexture(TEXTURE);
         this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
         this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((LQ)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
