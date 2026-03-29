package neo;

import net.minecraft.util.ResourceLocation;

public class xn extends ww<HV> {
   private static final ResourceLocation INVULNERABLE_WITHER_TEXTURES = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation WITHER_TEXTURES = new ResourceLocation("textures/entity/wither/wither.png");

   public xn(wC renderManagerIn) {
      super(renderManagerIn, new oN(0.0F), 1.0F);
      this.addLayer(new vE(this));
   }

   protected ResourceLocation getEntityTexture(HV entity) {
      int i = entity.getInvulTime();
      return i <= 0 || i <= 80 && i / 5 % 2 == 1 ? WITHER_TEXTURES : INVULNERABLE_WITHER_TEXTURES;
   }

   protected void preRenderCallback(HV entitylivingbaseIn, float partialTickTime) {
      float f = 2.0F;
      int i = entitylivingbaseIn.getInvulTime();
      if (i > 0) {
         f -= ((float)i - partialTickTime) / 220.0F * 0.5F;
      }

      yh.scale(f, f, f);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((HV)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((HV)var1);
   }
}
