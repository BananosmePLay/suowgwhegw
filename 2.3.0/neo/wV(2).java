package neo;

import net.minecraft.util.ResourceLocation;

public class wV extends ww<KG> {
   private static final ResourceLocation SILVERFISH_TEXTURES = new ResourceLocation("textures/entity/silverfish.png");

   public wV(wC renderManagerIn) {
      super(renderManagerIn, new oD(), 0.3F);
   }

   protected float getDeathMaxRotation(KG entityLivingBaseIn) {
      return 180.0F;
   }

   protected ResourceLocation getEntityTexture(KG entity) {
      return SILVERFISH_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float getDeathMaxRotation(Iw var1) {
      return this.getDeathMaxRotation((KG)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((KG)var1);
   }
}
