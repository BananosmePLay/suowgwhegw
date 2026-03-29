package neo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;

public class 0dX {
   protected final yN field_h = yN.getInstance();
   protected yP field_b;
   private final int field_a;
   public final tN field_f;
   public int field_c;
   public 0dW[] field_e;
   protected Font field_d;
   public int field_g;
   private static String _ _;

   public void method_bDL(0dW[] b, char c, float d, float e) throws ArrayIndexOutOfBoundsException {
      try {
         this.method_bDM(d, e, (float)method_bEl(b[c]), (float)method_bEm(b[c]), (float)method_bEn(b[c]), (float)method_bEo(b[c]), (float)method_bEp(b[c]), (float)method_bEq(b[c]));
      } catch (Exception var6) {
         Exception a = var6;
         a.printStackTrace();
      }

   }

   private static tN method_bEw(0dX var0) {
      return var0.field_f;
   }

   public static Font method_bDJ(String c, float d) {
      try {
         Font a = Font.createFont(4591 ^ -24131 ^ 12682 ^ -32296, nC.getMinecraft().getResourceManager().getResource(new ResourceLocation(method_bDP("ƳƸƲƪƼƯƸǲƻƲƳƩƮǲ") + c)).getInputStream());
         return a.deriveFont(d);
      } catch (Exception var3) {
         Exception b = var3;
         b.printStackTrace();
         return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bDP(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31584 ^ -17091 ^ 28686 ^ -18861; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16461 ^ -23914 ^ 26886 ^ -30208));
      }

      return var1.toString();
   }

   private static void method_bEj(0dX var0, int var1) {
      var0.field_c = var1;
   }

   private static int method_bEi(0dW var0) {
      return var0.field_a;
   }

   private static int method_bEg(0dW var0) {
      return var0.field_a;
   }

   private static tN method_bEt(0dX var0) {
      return var0.field_f;
   }

   public _dX/* $FF was: 0dX*/(Font a) {
      this.field_f = this.field_h.getBuffer();
      this.field_a = 28309 ^ -31312 ^ 19326 ^ -23973;
      this.field_e = new 0dW[31663 ^ 12394 ^ 19558 ^ 1013];
      this.field_c = -11161 ^ -21965 ^ 14939 ^ -17424;
      this.field_g = 26402 ^ -14368 ^ 7013 ^ -17497;
      this.field_d = a;
      this.field_b = this.method_bDI(a, this.field_e);
   }

   private static int method_bEA(0dW var0) {
      return var0.field_c;
   }

   public int method_bDN() {
      return (method_bEx(this) - (7146 ^ -17521 ^ 14456 ^ -26603)) / (13030 ^ -529 ^ 17071 ^ -29276);
   }

   protected void method_bDM(float a, float b, float c, float d, float e, float f, float g, float h) {
      float i = e / Float.intBitsToFloat(11449 ^ 129427 ^ 19398 ^ -416902914 ^ '蝜' ^ 116078 ^ '퇼' ^ -1557750308);
      float j = f / Float.intBitsToFloat('\udf67' ^ 17329 ^ '\ue9b4' ^ 202320811 ^ '펑' ^ 80063 ^ 23256 ^ 1208938303);
      float k = g / Float.intBitsToFloat(18273 ^ 235138 ^ 8776 ^ -1998467492 ^ 18572 ^ '謁' ^ '\ue94a' ^ -857598160);
      float l = h / Float.intBitsToFloat(10038 ^ 1023053 ^ 687 ^ -961041402 ^ '麇' ^ 1045203 ^ '놫' ^ -2101884883);
      method_bEr(this).pos((double)(a + c), (double)b, Double.longBitsToDouble(5448159201799856608L ^ 5448159201799856608L)).tex((double)(i + k), (double)j).endVertex();
      method_bEs(this).pos((double)a, (double)b, Double.longBitsToDouble(1503644167008411425L ^ 1503644167008411425L)).tex((double)i, (double)j).endVertex();
      method_bEt(this).pos((double)a, (double)(b + d), Double.longBitsToDouble(5543448235994926629L ^ 5543448235994926629L)).tex((double)i, (double)(j + l)).endVertex();
      method_bEu(this).pos((double)a, (double)(b + d), Double.longBitsToDouble(-28983289907007531L ^ -28983289907007531L)).tex((double)i, (double)(j + l)).endVertex();
      method_bEv(this).pos((double)(a + c), (double)(b + d), Double.longBitsToDouble(-3351900048351461606L ^ -3351900048351461606L)).tex((double)(i + k), (double)(j + l)).endVertex();
      method_bEw(this).pos((double)(a + c), (double)b, Double.longBitsToDouble(-6485283861759807792L ^ -6485283861759807792L)).tex((double)(i + k), (double)j).endVertex();
   }

   private static Object method_bDS() {
      return RenderingHints.VALUE_FRACTIONALMETRICS_ON;
   }

   private static int method_bDZ(Rectangle var0) {
      return var0.height;
   }

   protected BufferedImage method_bDK(Font e, 0dW[] f) {
      int g = 14682 ^ -27054 ^ 12017 ^ -31751;
      BufferedImage h = new BufferedImage(g, g, 30630 ^ -15520 ^ 3130 ^ -18178);
      Graphics2D i = (Graphics2D)h.getGraphics();
      i.setFont(e);
      i.setColor(new Color(12949 ^ -10956 ^ 20750 ^ -18864, 13275 ^ -27148 ^ 25478 ^ -15018, 11735 ^ -26618 ^ 17911 ^ -3879, 16077 ^ -32627 ^ 15058 ^ -31598));
      i.fillRect(1327 ^ -30422 ^ 11015 ^ -22782, 8674 ^ -13309 ^ 22604 ^ -19027, g, g);
      i.setColor(method_bDQ());
      i.setRenderingHint(method_bDR(), method_bDS());
      i.setRenderingHint(method_bDT(), method_bDU());
      i.setRenderingHint(method_bDV(), method_bDW());
      FontMetrics j = i.getFontMetrics();
      int k = 26512 ^ -31902 ^ 28064 ^ -30382;
      int l = 4897 ^ -10965 ^ 10985 ^ -4893;
      int m = 32342 ^ -13077 ^ 13667 ^ -30753;

      for(int d = 11339 ^ -14490 ^ 31564 ^ -28575; d < f.length; ++d) {
         char c = (char)d;
         if (c <= (10362 ^ -8672 ^ 4637 ^ -8119) && c >= (20596 ^ -3116 ^ 19454 ^ -5794)) {
            f[d] = null;
         } else {
            0dW a = new 0dW();
            Rectangle2D b = j.getStringBounds(String.valueOf(c), i);
            method_bDY(a, method_bDX(b.getBounds()) + (28983 ^ -1325 ^ 5453 ^ -24927));
            method_bEa(a, method_bDZ(b.getBounds()));
            if (l + method_bEb(a) >= g) {
               l = 17701 ^ -10698 ^ 32503 ^ -4636;
               m += k;
               k = 29648 ^ -6857 ^ 28489 ^ -1618;
            }

            if (method_bEc(a) > k) {
               k = method_bEd(a);
            }

            method_bEe(a, l);
            method_bEf(a, m);
            if (method_bEg(a) > method_bEh(this)) {
               method_bEj(this, method_bEi(a));
            }

            f[d] = a;
            i.drawString(String.valueOf(c), l + (1015 ^ -31426 ^ 15956 ^ -18273), m + j.getAscent());
            l += method_bEk(a);
         }
      }

      return h;
   }

   private static int method_bEp(0dW var0) {
      return var0.field_c;
   }

   private static 0dW[] method_bEz(0dX var0) {
      return var0.field_e;
   }

   private static int method_bEc(0dW var0) {
      return var0.field_a;
   }

   private static int method_bEm(0dW var0) {
      return var0.field_a;
   }

   private static tN method_bEu(0dX var0) {
      return var0.field_f;
   }

   public int method_bDO(String b) {
      int c = 13793 ^ -856 ^ 18173 ^ -28748;
      char[] var3 = b.toCharArray();
      int var4 = var3.length;

      for(int var5 = 24936 ^ -12357 ^ 2808 ^ -23509; var5 < var4; ++var5) {
         char a = var3[var5];
         if (a < method_bEy(this).length) {
            c += method_bEA(method_bEz(this)[a]) - (26889 ^ -25365 ^ 23995 ^ -22447) + method_bEB(this);
         }
      }

      return c / (1950 ^ -29612 ^ 31753 ^ -2111);
   }

   private static RenderingHints.Key method_bDR() {
      return RenderingHints.KEY_FRACTIONALMETRICS;
   }

   private static Color method_bDQ() {
      return Color.WHITE;
   }

   private static 0dW[] method_bEy(0dX var0) {
      return var0.field_e;
   }

   private static tN method_bEs(0dX var0) {
      return var0.field_f;
   }

   private static int method_bEb(0dW var0) {
      return var0.field_c;
   }

   private static RenderingHints.Key method_bDV() {
      return RenderingHints.KEY_ANTIALIASING;
   }

   private static int method_bEx(0dX var0) {
      return var0.field_c;
   }

   private static Object method_bDW() {
      return RenderingHints.VALUE_ANTIALIAS_ON;
   }

   private static int method_bEd(0dW var0) {
      return var0.field_a;
   }

   private static int method_bEn(0dW var0) {
      return var0.field_d;
   }

   private static Object method_bDU() {
      return RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
   }

   private static int method_bEB(0dX var0) {
      return var0.field_g;
   }

   private static RenderingHints.Key method_bDT() {
      return RenderingHints.KEY_TEXT_ANTIALIASING;
   }

   private static tN method_bEv(0dX var0) {
      return var0.field_f;
   }

   private static tN method_bEr(0dX var0) {
      return var0.field_f;
   }

   private static int method_bEh(0dX var0) {
      return var0.field_c;
   }

   private static int method_bDX(Rectangle var0) {
      return var0.width;
   }

   private static int method_bEl(0dW var0) {
      return var0.field_c;
   }

   private static void method_bEe(0dW var0, int var1) {
      var0.field_d = var1;
   }

   private static void method_bEf(0dW var0, int var1) {
      var0.field_b = var1;
   }

   private static void method_bEa(0dW var0, int var1) {
      var0.field_a = var1;
   }

   private static int method_bEo(0dW var0) {
      return var0.field_b;
   }

   private static int method_bEk(0dW var0) {
      return var0.field_c;
   }

   private static int method_bEq(0dW var0) {
      return var0.field_a;
   }

   private static void method_bDY(0dW var0, int var1) {
      var0.field_c = var1;
   }

   protected yP method_bDI(Font b, 0dW[] c) {
      BufferedImage d = this.method_bDK(b, c);

      try {
         return new yP(d);
      } catch (Exception var5) {
         Exception a = var5;
         a.printStackTrace();
         return null;
      }
   }
}
