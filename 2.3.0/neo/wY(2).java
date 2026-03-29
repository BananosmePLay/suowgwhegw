package neo;

import net.minecraft.util.ResourceLocation;

public class wY extends ww<KN> {
   private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

   public wY(wC p_i47193_1_) {
      super(p_i47193_1_, new oG(16), 0.25F);
      this.addLayer(new vz(this));
   }

   public void doRender(KN entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F * (float)entity.getSlimeSize();
      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(KN entitylivingbaseIn, float partialTickTime) {
      float f = 0.999F;
      yh.scale(0.999F, 0.999F, 0.999F);
      float f1 = (float)entitylivingbaseIn.getSlimeSize();
      float f2 = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      yh.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   protected ResourceLocation getEntityTexture(KN entity) {
      return SLIME_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KN)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((KN)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KN)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((KN)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KN)var1, var2, var4, var6, var8, var9);
   }
}
