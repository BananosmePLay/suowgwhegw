package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class xh extends vI<Jr> {
   public xh(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   public void doRender(Jr entity, double x, double y, double z, float entityYaw, float partialTicks) {
      tJ blockrendererdispatcher = nC.getMinecraft().getBlockRendererDispatcher();
      yh.pushMatrix();
      yh.translate((float)x, (float)y + 0.5F, (float)z);
      float f2;
      if ((float)entity.getFuse() - partialTicks + 1.0F < 10.0F) {
         f2 = 1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 10.0F;
         f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
         f2 *= f2;
         f2 *= f2;
         float f1 = 1.0F + f2 * 0.3F;
         yh.scale(f1, f1, f1);
      }

      f2 = (1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
      this.bindEntityTexture(entity);
      yh.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
      yh.translate(-0.5F, -0.5F, 0.5F);
      blockrendererdispatcher.renderBlockBrightness(Nk.TNT.getDefaultState(), entity.getBrightness());
      yh.translate(0.0F, 0.0F, 1.0F);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
         blockrendererdispatcher.renderBlockBrightness(Nk.TNT.getDefaultState(), 1.0F);
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      } else if (entity.getFuse() / 5 % 2 == 0) {
         yh.disableTexture2D();
         yh.disableLighting();
         yh.enableBlend();
         yh.blendFunc(ya.SRC_ALPHA, xR.DST_ALPHA);
         yh.color(1.0F, 1.0F, 1.0F, f2);
         yh.doPolygonOffset(-3.0F, -3.0F);
         yh.enablePolygonOffset();
         blockrendererdispatcher.renderBlockBrightness(Nk.TNT.getDefaultState(), 1.0F);
         yh.doPolygonOffset(0.0F, 0.0F);
         yh.disablePolygonOffset();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableBlend();
         yh.enableLighting();
         yh.enableTexture2D();
      }

      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Jr entity) {
      return zj.LOCATION_BLOCKS_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Jr)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Jr)var1, var2, var4, var6, var8, var9);
   }
}
