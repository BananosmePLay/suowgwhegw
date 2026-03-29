package neo;

import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.lwjgl.input.Mouse;

public class 0s implements 0cC, 0cD {
   public final 0k field_a;
   private static String _ _;

   @Subscribe
   public void method_cr(0de a) {
      String b = a.method_bzZ();
      if (method_cE(this).method_j().method_bJ() && !b.startsWith(method_ct("п")) && !b.startsWith(method_cF().method_Qr().method_bxu())) {
         method_cG(this).method_f(b);
         a.method_bzT((boolean)(16944 ^ -17131 ^ 643 ^ -601));
      }

   }

   private static 0ct method_cP() {
      return 0bI.field_g;
   }

   private static nC method_cJ() {
      return mc;
   }

   private static jH method_cA(nC var0) {
      return var0.fontRenderer;
   }

   private static 0k method_cu(0s var0) {
      return var0.field_a;
   }

   private static 0k method_cE(0s var0) {
      return var0.field_a;
   }

   private static 0ct method_cB() {
      return 0bI.field_a;
   }

   private static 0k method_cG(0s var0) {
      return var0.field_a;
   }

   private static nC method_cI() {
      return mc;
   }

   private static int method_cM(nC var0) {
      return var0.displayHeight;
   }

   public _s/* $FF was: 0s*/(0k a) {
      this.field_a = a;
   }

   private static 0ct method_cw() {
      return 0bI.field_a;
   }

   private static 0ct method_cx() {
      return 0bI.field_g;
   }

   private static Color method_cD() {
      return Color.WHITE;
   }

   private static nC method_cL() {
      return mc;
   }

   private static 0ct method_cO() {
      return 0bI.field_a;
   }

   private static nC method_cz() {
      return mc;
   }

   private static 0k method_cH(0s var0) {
      return var0.field_a;
   }

   @Subscribe
   public void method_cs(0dh k) {
      if (method_cH(this).method_j().method_bJ()) {
         mC c = new mC(method_cI());
         int d = Mouse.getEventX() * c.getScaledWidth() / method_cK(method_cJ());
         int e = c.getScaledHeight() - Mouse.getEventY() * c.getScaledHeight() / method_cM(method_cL()) - (5618 ^ -3731 ^ 23635 ^ -18227);
         0cW f = method_cN(this).method_j().method_bK();
         int g = (int)((float)d - method_cO().method_bnH());
         int h = (int)((float)e - method_cP().method_bnH());
         int i = f.getCaptcha().getWidth() / (15913 ^ -12780 ^ 15736 ^ -12985);
         int j = f.getCaptcha().getHeight() / (12078 ^ -10129 ^ 2040 ^ -3909);
         if (g >= 0 && h >= 0 && g < i && h < j) {
            int a = g / (18408 ^ -27614 ^ 27892 ^ -16514);
            int b = h / (20209 ^ -6857 ^ 30201 ^ -8577);
            f.rotateFrame(a, b, k.method_bAh());
         }
      }

   }

   private static 0ct method_cC() {
      return 0bI.field_g;
   }

   private static 0cp method_cy() {
      return 0bI.field_n;
   }

   private static 0bz method_cF() {
      return client;
   }

   @Subscribe
   public void method_cq(0dn c) {
      if (method_cu(this).method_j().method_bJ()) {
         0cW a = method_cv(this).method_j().method_bK();
         BufferedImage b = a.getCaptcha();
         0en.method_bGq(a, (int)method_cw().method_bnH(), (int)method_cx().method_bnH(), b.getWidth() / (9217 ^ -24444 ^ 31322 ^ -291), b.getHeight() / (8659 ^ -24989 ^ 10518 ^ -26972));
         if (method_cy().method_bna() && a.getBot().getParameter(method_ct("ѼѱѣѤѽѵѣѣѱѷѵ")) != null) {
            method_cA(method_cz()).drawCenteredString(a.getBot().getStringParameter(method_ct("ѼѱѣѤѽѵѣѣѱѷѵ")), (double)((float)((int)method_cB().method_bnH()) + (float)b.getWidth() / Float.intBitsToFloat('쨗' ^ '黭' ^ 12741 ^ 270274063 ^ '잦' ^ 481700 ^ 6555 ^ 1352395945)), (double)((float)((int)method_cC().method_bnH()) + (float)b.getHeight() / Float.intBitsToFloat(3856 ^ 31199 ^ 7827 ^ 1184075115 ^ 2159 ^ 495194 ^ 501802 ^ 110349096) + Float.intBitsToFloat('옹' ^ '\ue2a3' ^ 3183 ^ 1052524631 ^ '\ud965' ^ 475753 ^ 1088 ^ 2115762158)), method_cD().getRGB());
         }
      }

   }

   private static int method_cK(nC var0) {
      return var0.displayWidth;
   }

   private static 0k method_cv(0s var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_ct(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22778 ^ -18276 ^ 23516 ^ -17478; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12002 ^ -9974 ^ 25556 ^ -28628));
      }

      return var1.toString();
   }

   private static 0k method_cN(0s var0) {
      return var0.field_a;
   }
}
