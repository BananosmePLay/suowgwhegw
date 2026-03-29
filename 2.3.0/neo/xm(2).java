package neo;

import net.minecraft.util.ResourceLocation;

public class xm extends ww<Lg> {
   private static final ResourceLocation WITCH_TEXTURES = new ResourceLocation("textures/entity/witch.png");

   public xm(wC renderManagerIn) {
      super(renderManagerIn, new oM(0.0F), 0.5F);
      this.addLayer(new vs(this));
   }

   public oM getMainModel() {
      return (oM)super.getMainModel();
   }

   public void doRender(Lg entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ((oM)this.mainModel).holdingItem = !entity.getHeldItemMainhand().isEmpty();
      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Lg entity) {
      return WITCH_TEXTURES;
   }

   public void transformHeldFull3DItemLayer() {
      yh.translate(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(Lg entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      yh.scale(0.9375F, 0.9375F, 0.9375F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lg)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Lg)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lg)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lg)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lg)var1, var2, var4, var6, var8, var9);
   }
}
