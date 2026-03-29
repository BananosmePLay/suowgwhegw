package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class zo extends zF<Yh> {
   private final nG bannerModel = new nG();

   public zo() {
   }

   public void render(Yh te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      boolean flag = te.getWorld() != null;
      boolean flag1 = !flag || te.getBlockType() == Nk.STANDING_BANNER;
      int i = flag ? te.getBlockMetadata() : 0;
      long j = flag ? te.getWorld().getTotalWorldTime() : 0L;
      yh.pushMatrix();
      float f = 0.6666667F;
      float f2;
      if (flag1) {
         yh.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
         f2 = (float)(i * 360) / 16.0F;
         yh.rotate(-f2, 0.0F, 1.0F, 0.0F);
         this.bannerModel.bannerStand.showModel = true;
      } else {
         f2 = 0.0F;
         if (i == 2) {
            f2 = 180.0F;
         }

         if (i == 4) {
            f2 = 90.0F;
         }

         if (i == 5) {
            f2 = -90.0F;
         }

         yh.translate((float)x + 0.5F, (float)y - 0.16666667F, (float)z + 0.5F);
         yh.rotate(-f2, 0.0F, 1.0F, 0.0F);
         yh.translate(0.0F, -0.3125F, -0.4375F);
         this.bannerModel.bannerStand.showModel = false;
      }

      BlockPos blockpos = te.getPos();
      float f3 = (float)(blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13) + (float)j + partialTicks;
      this.bannerModel.bannerSlate.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(f3 * 3.1415927F * 0.02F)) * 3.1415927F;
      yh.enableRescaleNormal();
      ResourceLocation resourcelocation = this.getBannerResourceLocation(te);
      if (resourcelocation != null) {
         this.bindTexture(resourcelocation);
         yh.pushMatrix();
         yh.scale(0.6666667F, -0.6666667F, -0.6666667F);
         this.bannerModel.renderBanner();
         yh.popMatrix();
      }

      yh.color(1.0F, 1.0F, 1.0F, alpha);
      yh.popMatrix();
   }

   @Nullable
   private ResourceLocation getBannerResourceLocation(Yh bannerObj) {
      return rJ.BANNER_DESIGNS.getResourceLocation(bannerObj.getPatternResourceLocation(), bannerObj.getPatternList(), bannerObj.getColorList());
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yh)var1, var2, var4, var6, var8, var9, var10);
   }
}
