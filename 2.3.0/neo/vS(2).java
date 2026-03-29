package neo;

import net.minecraft.util.ResourceLocation;

public class vS extends xc<JA> {
   private static final ResourceLocation CAVE_SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/cave_spider.png");

   public vS(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize *= 0.7F;
   }

   protected void preRenderCallback(JA entitylivingbaseIn, float partialTickTime) {
      yh.scale(0.7F, 0.7F, 0.7F);
   }

   protected ResourceLocation getEntityTexture(JA entity) {
      return CAVE_SPIDER_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(KW var1) {
      return this.getEntityTexture((JA)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((JA)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JA)var1);
   }
}
