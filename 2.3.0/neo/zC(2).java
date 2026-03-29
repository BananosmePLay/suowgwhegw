package neo;

import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class zC extends zF<YQ> {
   private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
   private final oC model = new oC();
   private static double textRenderDistanceSq = 4096.0;

   public zC() {
   }

   public void render(YQ te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      co block = te.getBlockType();
      yh.pushMatrix();
      float f = 0.6666667F;
      float f3;
      if (block == Nk.STANDING_SIGN) {
         yh.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
         float f1 = (float)(te.getBlockMetadata() * 360) / 16.0F;
         yh.rotate(-f1, 0.0F, 1.0F, 0.0F);
         this.model.signStick.showModel = true;
      } else {
         int k = te.getBlockMetadata();
         f3 = 0.0F;
         if (k == 2) {
            f3 = 180.0F;
         }

         if (k == 4) {
            f3 = 90.0F;
         }

         if (k == 5) {
            f3 = -90.0F;
         }

         yh.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
         yh.rotate(-f3, 0.0F, 1.0F, 0.0F);
         yh.translate(0.0F, -0.3125F, -0.4375F);
         this.model.signStick.showModel = false;
      }

      if (destroyStage >= 0) {
         this.bindTexture(DESTROY_STAGES[destroyStage]);
         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(4.0F, 2.0F, 1.0F);
         yh.translate(0.0625F, 0.0625F, 0.0625F);
         yh.matrixMode(5888);
      } else {
         this.bindTexture(SIGN_TEXTURE);
      }

      yh.enableRescaleNormal();
      yh.pushMatrix();
      yh.scale(0.6666667F, -0.6666667F, -0.6666667F);
      this.model.renderSign();
      yh.popMatrix();
      if (isRenderText(te)) {
         jH fontrenderer = this.getFontRenderer();
         f3 = 0.010416667F;
         yh.translate(0.0F, 0.33333334F, 0.046666667F);
         yh.scale(0.010416667F, -0.010416667F, 0.010416667F);
         yh.glNormal3f(0.0F, 0.0F, -0.010416667F);
         yh.depthMask(false);
         int i = 0;
         if (XH.isCustomColors()) {
            i = bjy.getSignTextColor(i);
         }

         if (destroyStage < 0) {
            for(int j = 0; j < te.signText.length; ++j) {
               if (te.signText[j] != null) {
                  ITextComponent itextcomponent = te.signText[j];
                  List<ITextComponent> list = lF.splitText(itextcomponent, 90, fontrenderer, false, true);
                  String s = list != null && !list.isEmpty() ? ((ITextComponent)list.get(0)).getFormattedText() : "";
                  if (j == te.lineBeingEdited) {
                     s = "> " + s + " <";
                     fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5, i);
                  } else {
                     fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5, i);
                  }
               }
            }
         }
      }

      yh.depthMask(true);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.popMatrix();
      if (destroyStage >= 0) {
         yh.matrixMode(5890);
         yh.popMatrix();
         yh.matrixMode(5888);
      }

   }

   private static boolean isRenderText(YQ p_isRenderText_0_) {
      if (bpq.isShadowPass) {
         return false;
      } else {
         if (!XH.zoomMode && p_isRenderText_0_.lineBeingEdited < 0) {
            Ig entity = XH.getMinecraft().getRenderViewEntity();
            double d0 = p_isRenderText_0_.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (d0 > textRenderDistanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   public static void updateTextRenderDistance() {
      nC minecraft = XH.getMinecraft();
      double d0 = (double)XH.limit(nC.gameSettings.fovSetting, 1.0F, 120.0F);
      double d1 = Math.max(1.5 * (double)minecraft.displayHeight / d0, 16.0);
      textRenderDistanceSq = d1 * d1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((YQ)var1, var2, var4, var6, var8, var9, var10);
   }
}
