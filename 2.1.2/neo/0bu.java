package neo;

import java.awt.image.BufferedImage;

public class 0bu {
   public BufferedImage currentCaptcha;
   public final int y;
   public final int width;
   public final int height;
   public final int x;

   public int getHeight() {
      return kTT6fmm4YQ(this);
   }

   public void render(int mouseX, int mouseY, float partialTicks) {
   }

   public int getX() {
      return FLC4qlIW2a(this);
   }

   public BufferedImage getCaptcha() {
      return nwFDXAo7Xi(this);
   }

   private static int FLC4qlIW2a(0bu var0) {
      return var0.x;
   }

   private static int kTT6fmm4YQ(0bu var0) {
      return var0.height;
   }

   private static BufferedImage nwFDXAo7Xi(0bu var0) {
      return var0.currentCaptcha;
   }

   public int getWidth() {
      return TyQTUjAXBT(this);
   }

   public _bu/* $FF was: 0bu*/(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   private static void O1xgXgeUyU(0bu var0, BufferedImage var1) {
      var0.currentCaptcha = var1;
   }

   public int getY() {
      return IWKQ1gLBLR(this);
   }

   private static int IWKQ1gLBLR(0bu var0) {
      return var0.y;
   }

   private static int TyQTUjAXBT(0bu var0) {
      return var0.width;
   }

   public void setCaptcha(BufferedImage currentCaptcha) {
      O1xgXgeUyU(this, currentCaptcha);
   }
}
