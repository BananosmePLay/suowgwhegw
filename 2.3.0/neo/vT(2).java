package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vT extends ww<LA> {
   private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation("textures/entity/chicken.png");

   public vT(wC p_i47211_1_) {
      super(p_i47211_1_, new nS(), 0.3F);
   }

   protected ResourceLocation getEntityTexture(LA entity) {
      return CHICKEN_TEXTURES;
   }

   protected float handleRotationFloat(LA livingBase, float partialTicks) {
      float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
      float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
      return (MathHelper.sin(f) + 1.0F) * f1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float handleRotationFloat(Iw var1, float var2) {
      return this.handleRotationFloat((LA)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LA)var1);
   }
}
