package neo;

import net.minecraft.util.math.MathHelper;

public class xg extends wD<Jo> {
   public xg(wC renderManagerIn) {
      super(renderManagerIn);
   }

   protected void renderCartContents(Jo p_188319_1_, float partialTicks, in p_188319_3_) {
      int i = p_188319_1_.getFuseTicks();
      if (i > -1 && (float)i - partialTicks + 1.0F < 10.0F) {
         float f = 1.0F - ((float)i - partialTicks + 1.0F) / 10.0F;
         f = MathHelper.clamp(f, 0.0F, 1.0F);
         f *= f;
         f *= f;
         float f1 = 1.0F + f * 0.3F;
         yh.scale(f1, f1, f1);
      }

      super.renderCartContents(p_188319_1_, partialTicks, p_188319_3_);
      if (i > -1 && i / 5 % 2 == 0) {
         tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
         yh.disableTexture2D();
         yh.disableLighting();
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.DST_ALPHA);
         yh.color(1.0F, 1.0F, 1.0F, (1.0F - ((float)i - partialTicks + 1.0F) / 100.0F) * 0.8F);
         yh.pushMatrix();
         blockrendererdispatcher.renderBlockBrightness(Nk.TNT.getDefaultState(), 1.0F);
         yh.popMatrix();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableBlend();
         yh.enableLighting();
         yh.enableTexture2D();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderCartContents(Jc var1, float var2, in var3) {
      this.renderCartContents((Jo)var1, var2, var3);
   }
}
