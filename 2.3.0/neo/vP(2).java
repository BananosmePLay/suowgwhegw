package neo;

import net.minecraft.util.ResourceLocation;

public class vP<T extends Iu> extends ww<T> {
   private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");

   public vP(wC renderManagerIn, nM modelBipedIn, float shadowSize) {
      super(renderManagerIn, modelBipedIn, shadowSize);
      this.addLayer(new vi(modelBipedIn.bipedHead));
      this.addLayer(new vk(this));
      this.addLayer(new vr(this));
   }

   protected ResourceLocation getEntityTexture(T entity) {
      return DEFAULT_RES_LOC;
   }

   public void transformHeldFull3DItemLayer() {
      yh.translate(0.0F, 0.1875F, 0.0F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Iu)var1);
   }
}
