package neo;

import net.minecraft.util.ResourceLocation;

public class xt extends vP<Lk> {
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

   public xt(wC renderManagerIn) {
      super(renderManagerIn, new oP(), 0.5F);
      vf layerbipedarmor = new vf(this) {
         protected void initArmor() {
            this.modelLeggings = new oP(0.5F, true);
            this.modelArmor = new oP(1.0F, true);
         }
      };
      this.addLayer(layerbipedarmor);
   }

   protected ResourceLocation getEntityTexture(Lk entity) {
      return ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Lk)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lk)var1);
   }
}
