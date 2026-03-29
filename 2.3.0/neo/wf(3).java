package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class wf extends ww<Kl> {
   private static final ResourceLocation EVOKER_ILLAGER = new ResourceLocation("textures/entity/illager/evoker.png");

   public wf(wC p_i47207_1_) {
      super(p_i47207_1_, new og(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new vr(this) {
         public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (((KS)entitylivingbaseIn).isSpellcasting()) {
               super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

         }

         protected void translateToHand(EnumHandSide p_191361_1_) {
            ((og)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
         }
      });
   }

   protected ResourceLocation getEntityTexture(Kl entity) {
      return EVOKER_ILLAGER;
   }

   protected void preRenderCallback(Kl entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      yh.scale(0.9375F, 0.9375F, 0.9375F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kl)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kl)var1);
   }
}
