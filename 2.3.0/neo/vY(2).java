package neo;

import net.minecraft.util.ResourceLocation;

public class vY extends wn {
   private static final ResourceLocation GUARDIAN_ELDER_TEXTURE = new ResourceLocation("textures/entity/guardian_elder.png");

   public vY(wC p_i47209_1_) {
      super(p_i47209_1_);
   }

   protected void preRenderCallback(Kc entitylivingbaseIn, float partialTickTime) {
      yh.scale(2.35F, 2.35F, 2.35F);
   }

   protected ResourceLocation getEntityTexture(Kc entity) {
      return GUARDIAN_ELDER_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kc)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kc)var1);
   }
}
