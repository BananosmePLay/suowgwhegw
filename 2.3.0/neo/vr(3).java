package neo;

import net.minecraft.util.EnumHandSide;

public class vr implements vw<Iw> {
   protected final wy<?> livingEntityRenderer;

   public vr(wy<?> livingEntityRendererIn) {
      this.livingEntityRenderer = livingEntityRendererIn;
   }

   public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
      Qy itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
      Qy itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();
      if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
         yh.pushMatrix();
         if (this.livingEntityRenderer.getMainModel().isChild) {
            float f = 0.5F;
            yh.translate(0.0F, 0.75F, 0.0F);
            yh.scale(0.5F, 0.5F, 0.5F);
         }

         this.renderHeldItem(entitylivingbaseIn, itemstack1, sf.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
         this.renderHeldItem(entitylivingbaseIn, itemstack, sf.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
         yh.popMatrix();
      }

   }

   private void renderHeldItem(Iw p_188358_1_, Qy p_188358_2_, sf p_188358_3_, EnumHandSide handSide) {
      if (!p_188358_2_.isEmpty()) {
         yh.pushMatrix();
         this.translateToHand(handSide);
         if (p_188358_1_.isSneaking()) {
            yh.translate(0.0F, 0.2F, 0.0F);
         }

         yh.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         boolean flag = handSide == EnumHandSide.LEFT;
         yh.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
         nC.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
         yh.popMatrix();
      }

   }

   protected void translateToHand(EnumHandSide p_191361_1_) {
      ((nM)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, p_191361_1_);
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
