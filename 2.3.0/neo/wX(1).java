package neo;

import net.minecraft.util.ResourceLocation;

public class wX extends vP<Jx> {
   private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

   public wX(wC renderManagerIn) {
      super(renderManagerIn, new oE(), 0.5F);
      this.addLayer(new vr(this));
      this.addLayer(new vf(this) {
         protected void initArmor() {
            this.modelLeggings = new oE(0.5F, true);
            this.modelArmor = new oE(1.0F, true);
         }
      });
   }

   public void transformHeldFull3DItemLayer() {
      yh.translate(0.09375F, 0.1875F, 0.0F);
   }

   protected ResourceLocation getEntityTexture(Jx entity) {
      return SKELETON_TEXTURES;
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
