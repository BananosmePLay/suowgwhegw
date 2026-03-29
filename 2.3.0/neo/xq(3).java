package neo;

import net.minecraft.util.ResourceLocation;

public class xq extends ww<Mu> {
   private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf.png");
   private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   private static final ResourceLocation ANRGY_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   public xq(wC p_i47187_1_) {
      super(p_i47187_1_, new oO(), 0.5F);
      this.addLayer(new vF(this));
   }

   protected float handleRotationFloat(Mu livingBase, float partialTicks) {
      return livingBase.getTailRotation();
   }

   public void doRender(Mu entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.isWolfWet()) {
         float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
         yh.color(f, f, f);
      }

      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Mu entity) {
      if (entity.isTamed()) {
         return TAMED_WOLF_TEXTURES;
      } else {
         return entity.isAngry() ? ANRGY_WOLF_TEXTURES : WOLF_TEXTURES;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Mu)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float handleRotationFloat(Iw var1, float var2) {
      return this.handleRotationFloat((Mu)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Mu)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Mu)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Mu)var1, var2, var4, var6, var8, var9);
   }
}
