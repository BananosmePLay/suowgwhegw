package neo;

import net.minecraft.util.ResourceLocation;

public class wL extends vP<Ko> {
   private static final ResourceLocation ZOMBIE_PIGMAN_TEXTURE = new ResourceLocation("textures/entity/zombie_pigman.png");

   public wL(wC renderManagerIn) {
      super(renderManagerIn, new oP(), 0.5F);
      this.addLayer(new vf(this) {
         protected void initArmor() {
            this.modelLeggings = new oP(0.5F, true);
            this.modelArmor = new oP(1.0F, true);
         }
      });
   }

   protected ResourceLocation getEntityTexture(Ko entity) {
      return ZOMBIE_PIGMAN_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Ko)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Ko)var1);
   }
}
