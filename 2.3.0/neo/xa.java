package neo;

import net.minecraft.util.ResourceLocation;

public class xa extends ww<KO> {
   private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snowman.png");

   public xa(wC renderManagerIn) {
      super(renderManagerIn, new oH(), 0.5F);
      this.addLayer(new vA(this));
   }

   protected ResourceLocation getEntityTexture(KO entity) {
      return SNOW_MAN_TEXTURES;
   }

   public oH getMainModel() {
      return (oH)super.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((KO)var1);
   }
}
