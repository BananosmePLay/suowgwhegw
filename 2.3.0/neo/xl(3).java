package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class xl extends ww<Kl> {
   private static final ResourceLocation VINDICATOR_TEXTURE = new ResourceLocation("textures/entity/illager/vindicator.png");

   public xl(wC p_i47189_1_) {
      super(p_i47189_1_, new og(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new vr(this) {
         public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (((Lf)entitylivingbaseIn).isAggressive()) {
               super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

         }

         protected void translateToHand(EnumHandSide p_191361_1_) {
            ((og)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
         }
      });
   }

   public void doRender(Kl entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Kl entity) {
      return VINDICATOR_TEXTURE;
   }

   protected void preRenderCallback(Kl entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      yh.scale(0.9375F, 0.9375F, 0.9375F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kl)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kl)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }
}
