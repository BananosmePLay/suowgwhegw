package neo;

import net.minecraft.util.ResourceLocation;

public class wp extends xt {
   private static final ResourceLocation HUSK_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

   public wp(wC p_i47204_1_) {
      super(p_i47204_1_);
   }

   protected void preRenderCallback(Lk entitylivingbaseIn, float partialTickTime) {
      float f = 1.0625F;
      yh.scale(1.0625F, 1.0625F, 1.0625F);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   protected ResourceLocation getEntityTexture(Lk entity) {
      return HUSK_ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Lk)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Lk)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lk)var1);
   }
}
