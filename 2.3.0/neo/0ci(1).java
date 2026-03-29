package neo;

import java.awt.Color;
import net.minecraft.util.ResourceLocation;

public class 0ci extends jK {
   public int field_a;
   private static String _DSC GG NEOWARECLIENT _;

   private static int method_baA(0ci var0) {
      return var0.y;
   }

   private static int method_baM(0ci var0) {
      return var0.x;
   }

   private static int method_baL(0ci var0) {
      return var0.y;
   }

   private static int method_bbc(0ci var0) {
      return var0.height;
   }

   private static int method_baw(0ci var0) {
      return var0.x;
   }

   private static int method_baH(0ci var0) {
      return var0.field_a;
   }

   private static int method_baW(0ci var0) {
      return var0.field_a;
   }

   private static int method_baV(0ci var0) {
      return var0.field_a;
   }

   private static int method_baQ(0ci var0) {
      return var0.x;
   }

   private static String method_baY(0ci var0) {
      return var0.displayString;
   }

   private static int method_baI(0ci var0) {
      return var0.field_a;
   }

   private static void method_baJ(0ci var0, int var1) {
      var0.field_a = var1;
   }

   private static void method_baC(0ci var0, boolean var1) {
      var0.hovered = var1;
   }

   private static int method_baU(0ci var0) {
      return var0.field_a;
   }

   private static void method_baG(0ci var0, int var1) {
      var0.field_a = var1;
   }

   private static int method_baF(0ci var0) {
      return var0.field_a;
   }

   private static int method_baB(0ci var0) {
      return var0.height;
   }

   public void drawButton(nC c, int d, int e, float f) {
      if (method_bau(this)) {
         c.getTextureManager().bindTexture(method_bav());
         yh.color(Float.intBitsToFloat('셆' ^ 222657 ^ 1400 ^ -480382196 ^ 1158 ^ '\uebef' ^ '鼄' ^ -589421922), Float.intBitsToFloat(19484 ^ 110200 ^ 130425 ^ 1742776905 ^ 17891 ^ 17425 ^ 8334 ^ 1482724392), Float.intBitsToFloat(122706 ^ 107805 ^ 13086 ^ 375165263 ^ 115429 ^ 113087 ^ 14644 ^ 702323312), Float.intBitsToFloat('麾' ^ 23919 ^ '낃' ^ 209266519 ^ '\uf43b' ^ 99921 ^ '췽' ^ 871951250));
         method_baC(this, (boolean)(d >= method_baw(this) && e >= method_bax(this) && d < method_bay(this) + method_baz(this) && e < method_baA(this) + method_baB(this) ? 25553 ^ -25434 ^ 12023 ^ -11903 : 27657 ^ -13647 ^ 19743 ^ -5209));
         if (method_baD(this)) {
            if (method_baE(this) < (6119 ^ -17311 ^ 7346 ^ -18660)) {
               method_baG(this, method_baF(this) + (13019 ^ -14850 ^ 1494 ^ -3343));
            }
         } else if (method_baH(this) > (5656 ^ -26517 ^ 19094 ^ -15117)) {
            method_baJ(this, method_baI(this) - (29119 ^ -14881 ^ 22814 ^ -4740));
         }

         int var10000;
         if (d >= method_baK(this) && e >= method_baL(this) && d < method_baM(this) + method_baN(this) && e < method_baO(this) + method_baP(this)) {
            var10000 = 6748 ^ -19979 ^ 28076 ^ -14844;
         } else {
            var10000 = 19946 ^ -17609 ^ 30676 ^ -32503;
         }

         new Color(1111 ^ -8539 ^ 27982 ^ -18526, 19339 ^ -26207 ^ 3695 ^ -9125, 32578 ^ -1169 ^ 6270 ^ -25523, 28052 ^ -18344 ^ 1915 ^ -11705);
         yh.enableBlend();
         yh.tryBlendFuncSeparate(30082 ^ -6540 ^ 8780 ^ -19784, 9073 ^ -29137 ^ 21120 ^ -803, 1033 ^ -29663 ^ 8579 ^ -22102, 9348 ^ -14617 ^ 20536 ^ -19877);
         yh.blendFunc(21300 ^ -31023 ^ 16874 ^ -26867, 10284 ^ -165 ^ 15927 ^ -5565);
         0eo.method_bIF((float)method_baQ(this), (float)method_baR(this), (float)method_baS(this), (float)method_baT(this), Float.intBitsToFloat(9427 ^ 'ﴏ' ^ 27024 ^ -153076628 ^ 17684 ^ 11047 ^ 25501 ^ -1230995058), new Color(method_baU(this), method_baV(this), method_baW(this), 25812 ^ -9987 ^ 5436 ^ -22195));
         method_baX(nC.getMinecraft()).drawCenteredString(method_baY(this), (double)(method_baZ(this) + method_bba(this) / (23947 ^ -10290 ^ 24629 ^ -5518)), (double)(method_bbb(this) + (method_bbc(this) - (28181 ^ -29539 ^ 30659 ^ -27319)) / (14934 ^ -24621 ^ 24821 ^ -14989)), -20003 ^ -9523 ^ 6556 ^ -29325);
         this.mouseDragged(c, d, e);
      }

   }

   private static int method_baP(0ci var0) {
      return var0.height;
   }

   private static int method_baE(0ci var0) {
      return var0.field_a;
   }

   private static int method_bba(0ci var0) {
      return var0.width;
   }

   private static int method_bay(0ci var0) {
      return var0.x;
   }

   private static ResourceLocation method_bav() {
      return BUTTON_TEXTURES;
   }

   private static int method_baK(0ci var0) {
      return var0.x;
   }

   private static int method_baS(0ci var0) {
      return var0.width;
   }

   private static int method_baO(0ci var0) {
      return var0.y;
   }

   private static int method_baz(0ci var0) {
      return var0.width;
   }

   private static int method_baN(0ci var0) {
      return var0.width;
   }

   private static boolean method_baD(0ci var0) {
      return var0.hovered;
   }

   private static boolean method_bau(0ci var0) {
      return var0.visible;
   }

   private static int method_baR(0ci var0) {
      return var0.y;
   }

   private static int method_baZ(0ci var0) {
      return var0.x;
   }

   private static int method_bax(0ci var0) {
      return var0.y;
   }

   private static jH method_baX(nC var0) {
      return var0.fontRenderer;
   }

   private static int method_baT(0ci var0) {
      return var0.height;
   }

   public _ci/* $FF was: 0ci*/(int a, int b, int c, String d) {
      this(a, b, c, 8870 ^ -15939 ^ 22559 ^ -17460, 27088 ^ -10505 ^ 7057 ^ -23390, d);
   }

   private static int method_bbb(0ci var0) {
      return var0.y;
   }

   public _ci/* $FF was: 0ci*/(int a, int b, int c, int d, int e, String f) {
      super(a, b, c, d, e, f);
      this.field_a = 30573 ^ -25894 ^ 2416 ^ -6929;
   }
}
