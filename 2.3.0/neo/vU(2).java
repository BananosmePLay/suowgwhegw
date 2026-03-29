package neo;

import net.minecraft.util.ResourceLocation;

public class vU extends ww<LB> {
   private static final ResourceLocation COW_TEXTURES = new ResourceLocation("textures/entity/cow/cow.png");

   public vU(wC p_i47210_1_) {
      super(p_i47210_1_, new nT(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(LB entity) {
      return COW_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LB)var1);
   }
}
