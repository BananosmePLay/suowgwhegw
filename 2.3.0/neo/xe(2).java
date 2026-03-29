package neo;

import net.minecraft.util.ResourceLocation;

public class xe extends wX {
   private static final ResourceLocation STRAY_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/stray.png");

   public xe(wC p_i47191_1_) {
      super(p_i47191_1_);
      this.addLayer(new vC(this));
   }

   protected ResourceLocation getEntityTexture(Jx entity) {
      return STRAY_SKELETON_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Jx)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Jx)var1);
   }
}
