package neo;

import net.minecraft.util.ResourceLocation;

public class wB extends ww<Kk> {
   private static final ResourceLocation MAGMA_CUBE_TEXTURES = new ResourceLocation("textures/entity/slime/magmacube.png");

   public wB(wC renderManagerIn) {
      super(renderManagerIn, new om(), 0.25F);
   }

   protected ResourceLocation getEntityTexture(Kk entity) {
      return MAGMA_CUBE_TEXTURES;
   }

   protected void preRenderCallback(Kk entitylivingbaseIn, float partialTickTime) {
      int i = entitylivingbaseIn.getSlimeSize();
      float f = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / ((float)i * 0.5F + 1.0F);
      float f1 = 1.0F / (f + 1.0F);
      yh.scale(f1 * (float)i, 1.0F / f1 * (float)i, f1 * (float)i);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kk)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kk)var1);
   }
}
