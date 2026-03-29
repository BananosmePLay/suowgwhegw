package neo;

import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class wa extends ww<JJ> {
   private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
   private final Random rnd = new Random();

   public wa(wC renderManagerIn) {
      super(renderManagerIn, new nZ(0.0F), 0.5F);
      this.addLayer(new vn(this));
      this.addLayer(new vq(this));
   }

   public nZ getMainModel() {
      return (nZ)super.getMainModel();
   }

   public void doRender(JJ entity, double x, double y, double z, float entityYaw, float partialTicks) {
      in iblockstate = entity.getHeldBlockState();
      nZ modelenderman = this.getMainModel();
      modelenderman.isCarrying = iblockstate != null;
      modelenderman.isAttacking = entity.isScreaming();
      if (entity.isScreaming()) {
         double d0 = 0.02;
         x += this.rnd.nextGaussian() * 0.02;
         z += this.rnd.nextGaussian() * 0.02;
      }

      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(JJ entity) {
      return ENDERMAN_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((JJ)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((JJ)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JJ)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((JJ)var1, var2, var4, var6, var8, var9);
   }
}
