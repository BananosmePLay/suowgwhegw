package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class wv extends vI<HX> {
   public wv(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(HX entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      yh.disableTexture2D();
      yh.disableLighting();
      yh.enableBlend();
      yh.blendFunc(ya.SRC_ALPHA, xR.ONE);
      double[] adouble = new double[8];
      double[] adouble1 = new double[8];
      double d0 = 0.0;
      double d1 = 0.0;
      Random random = new Random(entity.boltVertex);

      int k1;
      for(k1 = 7; k1 >= 0; --k1) {
         adouble[k1] = d0;
         adouble1[k1] = d1;
         d0 += (double)(random.nextInt(11) - 5);
         d1 += (double)(random.nextInt(11) - 5);
      }

      for(k1 = 0; k1 < 4; ++k1) {
         Random random1 = new Random(entity.boltVertex);

         for(int j = 0; j < 3; ++j) {
            int k = 7;
            int l = 0;
            if (j > 0) {
               k = 7 - j;
            }

            if (j > 0) {
               l = k - 2;
            }

            double d2 = adouble[k] - d0;
            double d3 = adouble1[k] - d1;

            for(int i1 = k; i1 >= l; --i1) {
               double d4 = d2;
               double d5 = d3;
               if (j == 0) {
                  d2 += (double)(random1.nextInt(11) - 5);
                  d3 += (double)(random1.nextInt(11) - 5);
               } else {
                  d2 += (double)(random1.nextInt(31) - 15);
                  d3 += (double)(random1.nextInt(31) - 15);
               }

               bufferbuilder.begin(5, zK.POSITION_COLOR);
               float f = 0.5F;
               float f1 = 0.45F;
               float f2 = 0.45F;
               float f3 = 0.5F;
               double d6 = 0.1 + (double)k1 * 0.2;
               if (j == 0) {
                  d6 *= (double)i1 * 0.1 + 1.0;
               }

               double d7 = 0.1 + (double)k1 * 0.2;
               if (j == 0) {
                  d7 *= (double)(i1 - 1) * 0.1 + 1.0;
               }

               for(int j1 = 0; j1 < 5; ++j1) {
                  double d8 = x + 0.5 - d6;
                  double d9 = z + 0.5 - d6;
                  if (j1 == 1 || j1 == 2) {
                     d8 += d6 * 2.0;
                  }

                  if (j1 == 2 || j1 == 3) {
                     d9 += d6 * 2.0;
                  }

                  double d10 = x + 0.5 - d7;
                  double d11 = z + 0.5 - d7;
                  if (j1 == 1 || j1 == 2) {
                     d10 += d7 * 2.0;
                  }

                  if (j1 == 2 || j1 == 3) {
                     d11 += d7 * 2.0;
                  }

                  bufferbuilder.pos(d10 + d2, y + (double)(i1 * 16), d11 + d3).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
                  bufferbuilder.pos(d8 + d4, y + (double)((i1 + 1) * 16), d9 + d5).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
               }

               tessellator.draw();
            }
         }
      }

      yh.disableBlend();
      yh.enableLighting();
      yh.enableTexture2D();
   }

   @Nullable
   protected ResourceLocation getEntityTexture(HX entity) {
      return null;
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Nullable
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((HX)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((HX)var1, var2, var4, var6, var8, var9);
   }
}
