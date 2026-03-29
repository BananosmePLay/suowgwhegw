package neo;

import net.minecraft.util.ResourceLocation;

public class wb extends ww<JK> {
   private static final ResourceLocation ENDERMITE_TEXTURES = new ResourceLocation("textures/entity/endermite.png");

   public wb(wC renderManagerIn) {
      super(renderManagerIn, new oa(), 0.3F);
   }

   protected float getDeathMaxRotation(JK entityLivingBaseIn) {
      return 180.0F;
   }

   protected ResourceLocation getEntityTexture(JK entity) {
      return ENDERMITE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float getDeathMaxRotation(Iw var1) {
      return this.getDeathMaxRotation((JK)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JK)var1);
   }
}
