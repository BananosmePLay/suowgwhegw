package neo;

import net.minecraft.util.ResourceLocation;

public class vQ extends ww<Jz> {
   private static final ResourceLocation BLAZE_TEXTURES = new ResourceLocation("textures/entity/blaze.png");

   public vQ(wC renderManagerIn) {
      super(renderManagerIn, new nN(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(Jz entity) {
      return BLAZE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Jz)var1);
   }
}
