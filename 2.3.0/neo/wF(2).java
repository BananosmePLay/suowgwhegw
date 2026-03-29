package neo;

import net.minecraft.util.ResourceLocation;

public class wF extends ww<LL> {
   private static final ResourceLocation MOOSHROOM_TEXTURES = new ResourceLocation("textures/entity/cow/mooshroom.png");

   public wF(wC p_i47200_1_) {
      super(p_i47200_1_, new nT(), 0.7F);
      this.addLayer(new vv(this));
   }

   public nT getMainModel() {
      return (nT)super.getMainModel();
   }

   protected ResourceLocation getEntityTexture(LL entity) {
      return MOOSHROOM_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LL)var1);
   }
}
