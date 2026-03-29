package neo;

import net.minecraft.util.ResourceLocation;

public class wG extends ww<LN> {
   private static final ResourceLocation BLACK_OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/black.png");
   private static final ResourceLocation OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/ocelot.png");
   private static final ResourceLocation RED_OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/red.png");
   private static final ResourceLocation SIAMESE_OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/siamese.png");

   public wG(wC p_i47199_1_) {
      super(p_i47199_1_, new oo(), 0.4F);
   }

   protected ResourceLocation getEntityTexture(LN entity) {
      switch (entity.getTameSkin()) {
         case 0:
         default:
            return OCELOT_TEXTURES;
         case 1:
            return BLACK_OCELOT_TEXTURES;
         case 2:
            return RED_OCELOT_TEXTURES;
         case 3:
            return SIAMESE_OCELOT_TEXTURES;
      }
   }

   protected void preRenderCallback(LN entitylivingbaseIn, float partialTickTime) {
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
      if (entitylivingbaseIn.isTamed()) {
         yh.scale(0.8F, 0.8F, 0.8F);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((LN)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LN)var1);
   }
}
