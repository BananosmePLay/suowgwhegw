package neo;

import net.minecraft.util.ResourceLocation;

public class xo extends wX {
   private static final ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

   public xo(wC p_i47188_1_) {
      super(p_i47188_1_);
   }

   protected ResourceLocation getEntityTexture(Jx entity) {
      return WITHER_SKELETON_TEXTURES;
   }

   protected void preRenderCallback(Jx entitylivingbaseIn, float partialTickTime) {
      yh.scale(1.2F, 1.2F, 1.2F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Jx)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Jx)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Jx)var1);
   }
}
