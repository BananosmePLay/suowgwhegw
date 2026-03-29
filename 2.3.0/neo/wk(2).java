package neo;

import net.minecraft.util.ResourceLocation;

public class wk extends ww<JW> {
   private static final ResourceLocation GHAST_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast.png");
   private static final ResourceLocation GHAST_SHOOTING_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

   public wk(wC renderManagerIn) {
      super(renderManagerIn, new oc(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(JW entity) {
      return entity.isAttacking() ? GHAST_SHOOTING_TEXTURES : GHAST_TEXTURES;
   }

   protected void preRenderCallback(JW entitylivingbaseIn, float partialTickTime) {
      float f = 1.0F;
      float f1 = 4.5F;
      float f2 = 4.5F;
      yh.scale(4.5F, 4.5F, 4.5F);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((JW)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JW)var1);
   }
}
