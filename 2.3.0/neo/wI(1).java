package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class wI extends ww<LP> {
   public static final ResourceLocation[] PARROT_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/parrot/parrot_red_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_green.png"), new ResourceLocation("textures/entity/parrot/parrot_yellow_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_grey.png")};

   public wI(wC p_i47375_1_) {
      super(p_i47375_1_, new oq(), 0.3F);
   }

   protected ResourceLocation getEntityTexture(LP entity) {
      return PARROT_TEXTURES[entity.getVariant()];
   }

   public float handleRotationFloat(LP livingBase, float partialTicks) {
      return this.getCustomBob(livingBase, partialTicks);
   }

   private float getCustomBob(LP parrot, float p_192861_2_) {
      float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
      float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
      return (MathHelper.sin(f) + 1.0F) * f1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public float handleRotationFloat(Iw var1, float var2) {
      return this.handleRotationFloat((LP)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LP)var1);
   }
}
