package neo;

import net.minecraft.util.EnumBlockRenderType;

public class vs implements vw<Lg> {
   private final xm witchRenderer;

   public vs(xm witchRendererIn) {
      this.witchRenderer = witchRendererIn;
   }

   public void doRenderLayer(Lg entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      Qy itemstack = entitylivingbaseIn.getHeldItemMainhand();
      if (!itemstack.isEmpty()) {
         yh.color(1.0F, 1.0F, 1.0F);
         yh.pushMatrix();
         if (this.witchRenderer.getMainModel().isChild) {
            yh.translate(0.0F, 0.625F, 0.0F);
            yh.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
            float f = 0.5F;
            yh.scale(0.5F, 0.5F, 0.5F);
         }

         this.witchRenderer.getMainModel().villagerNose.postRender(0.0625F);
         yh.translate(-0.0625F, 0.53125F, 0.21875F);
         OL item = itemstack.getItem();
         nC minecraft = nC.getMinecraft();
         float f3;
         if (co.getBlockFromItem(item).getDefaultState().getRenderType() == EnumBlockRenderType.ENTITYBLOCK_ANIMATED) {
            yh.translate(0.0F, 0.0625F, -0.25F);
            yh.rotate(30.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(-5.0F, 0.0F, 1.0F, 0.0F);
            f3 = 0.375F;
            yh.scale(0.375F, -0.375F, 0.375F);
         } else if (item == NK.BOW) {
            yh.translate(0.0F, 0.125F, -0.125F);
            yh.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
            f3 = 0.625F;
            yh.scale(0.625F, -0.625F, 0.625F);
            yh.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         } else if (item.isFull3D()) {
            if (item.shouldRotateAroundWhenRendering()) {
               yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
               yh.translate(0.0F, -0.0625F, 0.0F);
            }

            this.witchRenderer.transformHeldFull3DItemLayer();
            yh.translate(0.0625F, -0.125F, 0.0F);
            f3 = 0.625F;
            yh.scale(0.625F, -0.625F, 0.625F);
            yh.rotate(0.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(0.0F, 0.0F, 1.0F, 0.0F);
         } else {
            yh.translate(0.1875F, 0.1875F, 0.0F);
            f3 = 0.875F;
            yh.scale(0.875F, 0.875F, 0.875F);
            yh.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
            yh.rotate(-60.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(-30.0F, 0.0F, 0.0F, 1.0F);
         }

         yh.rotate(-15.0F, 1.0F, 0.0F, 0.0F);
         yh.rotate(40.0F, 0.0F, 0.0F, 1.0F);
         minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, sf.THIRD_PERSON_RIGHT_HAND);
         yh.popMatrix();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((Lg)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
