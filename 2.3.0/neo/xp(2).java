package neo;

import net.minecraft.util.ResourceLocation;

public class xp extends vI<Nf> {
   private static final ResourceLocation INVULNERABLE_WITHER_TEXTURES = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation WITHER_TEXTURES = new ResourceLocation("textures/entity/wither/wither.png");
   private final oF skeletonHeadModel = new oF();

   public xp(wC renderManagerIn) {
      super(renderManagerIn);
   }

   private float getRenderYaw(float p_82400_1_, float p_82400_2_, float p_82400_3_) {
      float f;
      for(f = p_82400_2_ - p_82400_1_; f < -180.0F; f += 360.0F) {
      }

      while(f >= 180.0F) {
         f -= 360.0F;
      }

      return p_82400_1_ + p_82400_3_ * f;
   }

   public void doRender(Nf entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      yh.disableCull();
      float f = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
      float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
      yh.translate((float)x, (float)y, (float)z);
      float f2 = 0.0625F;
      yh.enableRescaleNormal();
      yh.scale(-1.0F, -1.0F, 1.0F);
      yh.enableAlpha();
      this.bindEntityTexture(entity);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      this.skeletonHeadModel.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.0625F);
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Nf entity) {
      return entity.isInvulnerable() ? INVULNERABLE_WITHER_TEXTURES : WITHER_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Nf)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Nf)var1, var2, var4, var6, var8, var9);
   }
}
