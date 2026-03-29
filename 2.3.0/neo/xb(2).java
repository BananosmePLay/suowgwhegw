package neo;

import net.minecraft.util.ResourceLocation;

public class xb extends vN<Nc> {
   public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");

   public xb(wC manager) {
      super(manager);
   }

   protected ResourceLocation getEntityTexture(Nc entity) {
      return RES_SPECTRAL_ARROW;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Nc)var1);
   }
}
