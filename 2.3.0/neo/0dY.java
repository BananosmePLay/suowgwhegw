package neo;

import java.awt.Color;
import java.awt.Font;
import org.lwjgl.opengl.GL11;

public class 0dY extends 0dX {
   public final int[] field_a = new int[13450 ^ -21221 ^ 28256 ^ -2095];
   private static int _DSC GG NEOWARECLIENT _;

   public float method_bEC(String a, double b, double c, int d) {
      return Math.max(this.method_bEI(a, b + Double.longBitsToDouble(5711982935608284912L ^ 8116905136624129776L), c + Double.longBitsToDouble(5311661651465435679L ^ 8527231785407969823L), d, (boolean)(1584 ^ -12061 ^ 3623 ^ -9995)), this.method_bEI(a, b, c, d, (boolean)(16081 ^ -7901 ^ 25531 ^ -17335)));
   }

   private static int method_bEU(0dW var0) {
      return var0.field_c;
   }

   private static 0dW[] method_bES(0dY var0) {
      return var0.field_e;
   }

   private static int method_bFb(0dW var0) {
      return var0.field_c;
   }

   private static int[] method_bEQ(0dY var0) {
      return var0.field_a;
   }

   public float method_bEE(String a, float b, float c, int d) {
      return this.method_bED(a, b - (float)this.method_bDO(a) / Float.intBitsToFloat(20027 ^ 'ꙷ' ^ 3558 ^ -1563273893 ^ 29036 ^ 'ꊈ' ^ 7495 ^ -489521582), c, d);
   }

   private static int method_bFc(0dY var0) {
      return var0.field_g;
   }

   public float method_bED(String a, float b, float c, int d) {
      return this.method_bEI(a, (double)b, (double)c, d, (boolean)(5924 ^ -11785 ^ 14717 ^ -82));
   }

   private static 0dW[] method_bER(0dY var0) {
      return var0.field_e;
   }

   private static zO method_bEO() {
      return zK.POSITION_TEX;
   }

   public int method_bDO(String c) {
      int d = 801 ^ -28742 ^ 12038 ^ -23651;
      if (c == null) {
         return 16072 ^ -32525 ^ 5128 ^ -21965;
      } else {
         c = this.method_bEJ(c);

         for(int b = 12733 ^ -10704 ^ 8492 ^ -14687; b < c.length(); ++b) {
            char a = c.charAt(b);
            if (a == (2541 ^ -15393 ^ 7939 ^ -10858)) {
               ++b;
            } else if (a < method_bEZ(this).length) {
               d += method_bFb(method_bFa(this)[a]) - (1543 ^ -7991 ^ 30603 ^ -28339) + method_bFc(this);
            }
         }

         return d / (14096 ^ -31198 ^ 15611 ^ -29237);
      }
   }

   public _dY/* $FF was: 0dY*/(Font f) {
      super(f);

      for(int e = 4782 ^ -27763 ^ 24233 ^ -8310; e < (6524 ^ -26510 ^ 25175 ^ -7303); ++e) {
         int a = (e >> (27906 ^ -11156 ^ 27867 ^ -10826) & (19750 ^ -13819 ^ 3924 ^ -30602)) * (17607 ^ -29302 ^ 28835 ^ -17989);
         int b = (e >> (12331 ^ -10840 ^ 28572 ^ -30179) & (20903 ^ -6748 ^ 20692 ^ -6954)) * (27619 ^ -4449 ^ 25262 ^ -6280) + a;
         int c = (e >> (16386 ^ -11576 ^ 19902 ^ -8331) & (31182 ^ -8004 ^ 21381 ^ -13578)) * (4439 ^ -23845 ^ 31240 ^ -14034) + a;
         int d = (e & (2842 ^ -722 ^ 29441 ^ -31436)) * (25343 ^ -29007 ^ 14455 ^ -11117) + a;
         if (e == (23772 ^ -29808 ^ 9422 ^ -3196)) {
            b += 85;
         }

         if (e >= (19028 ^ -13984 ^ 9385 ^ -22643)) {
            b /= 18800 ^ -27258 ^ 30205 ^ -22257;
            c /= 31770 ^ -10409 ^ 13728 ^ -24855;
            d /= 17569 ^ -18098 ^ 12425 ^ -12958;
         }

         this.field_a[e] = (b & (4320 ^ -9856 ^ 16638 ^ -30367)) << (9228 ^ -2772 ^ 3407 ^ -9089) | (c & (29114 ^ -12543 ^ 12464 ^ -28940)) << (28475 ^ -9489 ^ 30350 ^ -15534) | d & (12254 ^ -23474 ^ 22614 ^ -11463);
      }

   }

   public float method_bEI(String h, double i, double j, int k, boolean l) {
      try {
         if (h == null) {
            return Float.intBitsToFloat(256006 ^ 26806 ^ 262061 ^ -2048281590 ^ 232481 ^ '롿' ^ 240711 ^ -2048306418);
         }

         i -= Double.longBitsToDouble(8979953830930878639L ^ 4859160171886874799L);
         h = this.method_bEJ(h);
         GL11.glColor4f(Float.intBitsToFloat('\uec8a' ^ 'ﶝ' ^ 15347 ^ -1526447484 ^ 'ꆗ' ^ 'ꯇ' ^ 25082 ^ -1702624310), Float.intBitsToFloat(20793 ^ 244420 ^ 261504 ^ 1239527111 ^ 234476 ^ 214766 ^ 12991 ^ 1986092807), Float.intBitsToFloat(24527 ^ 18360 ^ 5711 ^ -745893183 ^ 21507 ^ '픸' ^ 26280 ^ -334857366), Float.intBitsToFloat(16027 ^ 30517 ^ 7382 ^ 716933440 ^ 511861 ^ 498473 ^ 23075 ^ 356241991));
         yh.color(Float.intBitsToFloat('雈' ^ 515734 ^ '츨' ^ 1086045585 ^ '諠' ^ 479252 ^ 16370 ^ 2134630113), Float.intBitsToFloat('靯' ^ 28396 ^ '쿧' ^ 568527418 ^ 29119 ^ 113882 ^ 11715 ^ 509795576), Float.intBitsToFloat('롷' ^ 19978 ^ '\uda5f' ^ -2126898878 ^ '腮' ^ 27177 ^ '쳎' ^ -1095101719), Float.intBitsToFloat('遁' ^ 129750 ^ '\ue977' ^ -1664051256 ^ 2602 ^ 101425 ^ 31275 ^ -1554978792));
         GL11.glEnable(14289 ^ -47891 ^ '뷇' ^ -15079);
         if (h == null) {
            return Float.intBitsToFloat(10077 ^ '뛡' ^ '씱' ^ -1230303060 ^ '\uf117' ^ 93687 ^ 4369 ^ -1230321200);
         }

         if (k == (33524711 ^ 33536528 ^ 25664 ^ 553626184)) {
            k = 33543001 ^ 33523432 ^ 13033 ^ 16750759;
         }

         if ((k & (20674 ^ 134195784 ^ 23259 ^ -67133359)) == 0) {
            k |= 18490 ^ 33543666 ^ 4205 ^ -16806491;
         }

         if (l) {
            k = (k & (121872 ^ 126203 ^ 17728 ^ 16550231)) >> (19560 ^ -17565 ^ 18756 ^ -16819) | k & (new Color(8084 ^ -23313 ^ 5467 ^ -20940, 11596 ^ -15852 ^ 4200 ^ -220, 4321 ^ -30257 ^ 20910 ^ -14188, 16282 ^ -26475 ^ 4645 ^ -18974)).getRGB();
         }

         float f = (float)(k >> (6640 ^ -20724 ^ 29535 ^ -14917) & (10547 ^ -27360 ^ 25357 ^ -8223)) / Float.intBitsToFloat(236868 ^ 256237 ^ 26684 ^ 1638252805 ^ 14704 ^ 10583 ^ 1643 ^ 584764124);
         i *= Double.longBitsToDouble(126987044480409289L ^ 4738673062907797193L);
         j = (j - Double.longBitsToDouble(-7218526354328608203L ^ -2604588536087535051L)) * Double.longBitsToDouble(-510264312481488921L ^ -5121950330908876825L);
         GL11.glPushMatrix();
         yh.scale(Float.intBitsToFloat('ﬢ' ^ '\ue912' ^ 30958 ^ 1818503614 ^ '\uf405' ^ 493703 ^ '찫' ^ 1399059401), Float.intBitsToFloat(21564 ^ 108497 ^ 114718 ^ 1493993473 ^ 19117 ^ 110176 ^ 120104 ^ 1712097815), Float.intBitsToFloat(10573 ^ 506712 ^ 12821 ^ -1112823546 ^ 8410 ^ 489569 ^ '갎' ^ -2102657613));
         yh.enableBlend();
         yh.blendFunc(26595 ^ -1843 ^ 5388 ^ -30432, 26292 ^ -2076 ^ 25256 ^ -3845);
         yh.color((float)(k >> (2631 ^ -31249 ^ 20382 ^ -16346) & (23635 ^ -19466 ^ 20360 ^ -24366)) / Float.intBitsToFloat(2984 ^ '麻' ^ 26196 ^ 565073808 ^ 23363 ^ 215884 ^ 253401 ^ 1657891073), (float)(k >> (3566 ^ -22632 ^ 27217 ^ -16337) & (5389 ^ -14189 ^ 28965 ^ -21436)) / Float.intBitsToFloat(1038776 ^ 1015854 ^ 10367 ^ 1817685662 ^ 123827 ^ 76517 ^ 29453 ^ 791176492), (float)(k & (13653 ^ -32352 ^ 23767 ^ -5923)) / Float.intBitsToFloat('\udfd5' ^ 96087 ^ 11794 ^ -1178274467 ^ '鮌' ^ '\uda7c' ^ 17355 ^ -88444426), f);
         yh.enableTexture2D();
         yh.bindTexture(method_bEL(this).getGlTextureId());
         GL11.glBindTexture(23889 ^ -38520 ^ '\udc10' ^ -6872, method_bEM(this).getGlTextureId());
         method_bEN(this).begin(121 ^ -4534 ^ 25437 ^ -29334, method_bEO());

         for(int e = 26978 ^ -26534 ^ 26735 ^ -26281; e < h.length(); ++e) {
            char d = h.charAt(e);
            if (String.valueOf(d).equals(method_bEK("η"))) {
               char b = h.charAt(e + (2357 ^ -30168 ^ 5359 ^ -26637));
               int c = method_bEK("̡̢̧̨̠̣̤̥̦̩ͱͲͳʹ͵Ͷͻͼͽ;Ϳ͢").indexOf(b);
               if (b == (10025 ^ -12312 ^ 11415 ^ -15324)) {
                  yh.color((float)(k >> (1876 ^ -20518 ^ 31297 ^ -11553) & (7000 ^ -2928 ^ 4377 ^ -466)) / Float.intBitsToFloat('萅' ^ '쑴' ^ 12810 ^ 222566188 ^ '\ue8bf' ^ 19929 ^ '視' ^ 1312504400), (float)(k >> (29373 ^ -26176 ^ 1500 ^ -4439) & (25155 ^ -21434 ^ 10807 ^ -6963)) / Float.intBitsToFloat(10125 ^ 4167787 ^ 4179754 ^ 1356838006 ^ 122427 ^ 90667 ^ 6109 ^ 329279351), (float)(k & (16546 ^ -21835 ^ 6079 ^ -681)) / Float.intBitsToFloat(9601 ^ 6950 ^ 16298 ^ 85609472 ^ 9012 ^ '骤' ^ '헻' ^ 1181032806), f);
               } else if (c < (26633 ^ -28001 ^ 31863 ^ -30991)) {
                  yh.bindTexture(method_bEP(this).getGlTextureId());
                  if (c < 0) {
                     c = 6504 ^ -30001 ^ 10793 ^ -18047;
                  }

                  if (l) {
                     c += 16;
                  }

                  int a = method_bEQ(this)[c];
                  yh.color((float)(a >> (22858 ^ -5853 ^ 30679 ^ -14418) & (1209 ^ -32463 ^ 7736 ^ -25777)) / Float.intBitsToFloat('\ude91' ^ 8460 ^ '\ue317' ^ 520439710 ^ '阵' ^ 204981 ^ 830 ^ 1551494826), (float)(a >> (7193 ^ -17116 ^ 10084 ^ -31151) & (21757 ^ -26963 ^ 5444 ^ -10261)) / Float.intBitsToFloat('만' ^ 86387 ^ 31803 ^ 2136797278 ^ '믰' ^ 24077 ^ 'ꁺ' ^ 1008869725), (float)(a & (30487 ^ -16472 ^ 21558 ^ -25482)) / Float.intBitsToFloat(507853 ^ 507473 ^ 28927 ^ 484147296 ^ 103607 ^ 116269 ^ 26985 ^ 1604635376), f);
               }

               ++e;
            } else if (d < method_bER(this).length) {
               this.method_bDL(method_bES(this), d, (float)i, (float)j);
               i += (double)(method_bEU(method_bET(this)[d]) - (26206 ^ -5923 ^ 2338 ^ -30807) + method_bEV(this));
            }
         }

         method_bEW(this).draw();
         GL11.glHint(24440 ^ -30008 ^ 6027 ^ -12696, 14991 ^ -3919 ^ 30399 ^ -21119);
         GL11.glPopMatrix();
      } catch (Exception var14) {
         Exception g = var14;
         g.printStackTrace();
      }

      return (float)i / Float.intBitsToFloat(4554 ^ 233125 ^ 29458 ^ -693509524 ^ 13903 ^ 214360 ^ '뢬' ^ -1767258710);
   }

   public float method_bEF(String a, float b, float c, int d, boolean e) {
      return this.method_bEI(a, (double)(b - (float)this.method_bDO(a) / Float.intBitsToFloat(24621 ^ '융' ^ 28277 ^ -1728816021 ^ 99458 ^ '햏' ^ 108725 ^ -655070018)), (double)c, d, e);
   }

   private static yP method_bEP(0dY var0) {
      return var0.field_b;
   }

   private static 0dW[] method_bEX(0dY var0) {
      return var0.field_e;
   }

   public String method_bEJ(String b) {
      StringBuilder c = new StringBuilder();
      char[] d = b.toCharArray();
      char[] var4 = d;
      int var5 = d.length;

      for(int var6 = 15059 ^ -17871 ^ 27803 ^ -4999; var6 < var5; ++var6) {
         char a = var4[var6];
         if (a < method_bEX(this).length && method_bEY(this)[a] != null) {
            c.append(a);
         }
      }

      return c.toString();
   }

   private static 0dW[] method_bEZ(0dY var0) {
      return var0.field_e;
   }

   private static yP method_bEM(0dY var0) {
      return var0.field_b;
   }

   private static yN method_bEW(0dY var0) {
      return var0.field_h;
   }

   private static 0dW[] method_bFa(0dY var0) {
      return var0.field_e;
   }

   private static tN method_bEN(0dY var0) {
      return var0.field_f;
   }

   private static 0dW[] method_bET(0dY var0) {
      return var0.field_e;
   }

   public void method_bEH(String a, float b, float c) {
      this.method_bEG(a, b - (float)this.method_bDO(a) / Float.intBitsToFloat(8594 ^ '\ue171' ^ 6887 ^ 1044143220 ^ 12431 ^ '\uf152' ^ 32343 ^ 2117876218), c);
   }

   private static yP method_bEL(0dY var0) {
      return var0.field_b;
   }

   private static 0dW[] method_bEY(0dY var0) {
      return var0.field_e;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bEK(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3435 ^ -15052 ^ 21761 ^ -25250; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7774 ^ -10456 ^ 9382 ^ -4416));
      }

      return var1.toString();
   }

   public void method_bEG(String b, float c, float d) {
      int e = 5541 ^ -28450 ^ 30682 ^ -3423;
      0cS f = 0bz.method_Qm().method_Qt();
      char[] var6 = b.toCharArray();
      int var7 = var6.length;

      for(int var8 = 23723 ^ -8444 ^ 954 ^ -32747; var8 < var7; ++var8) {
         char a = var6[var8];
         this.method_bED(String.valueOf(a), c + (float)e, d, f.method_byP(e).getRGB());
         e += this.method_bDO(String.valueOf(a));
      }

   }

   private static int method_bEV(0dY var0) {
      return var0.field_g;
   }
}
