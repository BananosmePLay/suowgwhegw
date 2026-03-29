package neo;

import net.minecraft.util.ResourceLocation;

public class wN extends ww<Kv> {
   private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation("textures/entity/bear/polarbear.png");

   public wN(wC p_i47197_1_) {
      super(p_i47197_1_, new ot(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(Kv entity) {
      return POLAR_BEAR_TEXTURE;
   }

   public void doRender(Kv entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(Kv entitylivingbaseIn, float partialTickTime) {
      yh.scale(1.2F, 1.2F, 1.2F);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kv)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kv)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kv)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kv)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kv)var1, var2, var4, var6, var8, var9);
   }
}
