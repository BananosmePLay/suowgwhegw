package neo;

import com.google.common.eventbus.Subscribe;
import java.util.Arrays;
import net.minecraft.util.math.MathHelper;

public class 0ej implements 0cD {
   public static long field_c;
   public static long field_b;
   public static final float[] field_a = new float[18136 ^ -14059 ^ 9335 ^ -21586];
   public static int field_d;
   private static String _ _;

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bFB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 1607 ^ -11592 ^ 27461 ^ -16454; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27328 ^ -20062 ^ 13700 ^ -4182));
      }

      return var1.toString();
   }

   private static void method_bFx() {
      if (method_bFG() != -1L) {
         float a = (float)(System.currentTimeMillis() - method_bFH()) / Float.intBitsToFloat(10431 ^ '鶳' ^ 8901 ^ -1028303546 ^ 2081976 ^ '뽜' ^ 2086761 ^ -2033226238);
         method_bFI()[method_bFJ() % method_bFK().length] = MathHelper.clamp(Float.intBitsToFloat(18748 ^ '뀱' ^ '\uea8a' ^ -1027658952 ^ 32662 ^ 32632 ^ 3574 ^ -2095106649) / a, Float.intBitsToFloat('찤' ^ '쨉' ^ 32289 ^ -1931801857 ^ '\uf40d' ^ 31994 ^ '\uf055' ^ -1931802031), Float.intBitsToFloat('銌' ^ 107076 ^ '\ueebe' ^ 1791150437 ^ 30227 ^ 85848 ^ '\ue6b5' ^ 727894253));
         method_bFM(method_bFL() + (15138 ^ -17494 ^ 4503 ^ -28386));
      }

      method_bFN(System.currentTimeMillis());
   }

   private static long method_bFG() {
      return field_c;
   }

   private static void method_bFQ(long var0) {
      field_b = var0;
   }

   public static void method_bFv() {
      method_bFC(29842 ^ -31194 ^ 27650 ^ -24906);
      method_bFD(-1L);
      Arrays.fill(method_bFE(), Float.intBitsToFloat(19987 ^ 99903 ^ 28530 ^ -1859472660 ^ '齥' ^ 101408 ^ '관' ^ -1859474441));
      0bz.method_Qm().method_Qn().register(new 0ej());
   }

   private static nC method_bFP() {
      return mc;
   }

   public static String method_bFz() {
      long a = method_bFy() - 700L;
      if (method_bFP().isSingleplayer() || a < 0L) {
         a = 0L;
      }

      String var10000;
      Object[] var10001;
      if (a < 200L) {
         var10000 = method_bFB("ǫĭǫĠũĨ");
         var10001 = new Object[22910 ^ -28387 ^ 2165 ^ -16361];
         var10001[10223 ^ -29389 ^ 19234 ^ -7682] = a;
         return String.format(var10000, var10001);
      } else if (a < 700L) {
         var10000 = method_bFB("ǫźǫĠũĨ");
         var10001 = new Object[2076 ^ -7977 ^ 22048 ^ -16662];
         var10001[10941 ^ -13209 ^ 23909 ^ -17473] = a;
         return String.format(var10000, var10001);
      } else {
         var10000 = method_bFB("ǫįǫĠũĨ");
         var10001 = new Object[31028 ^ -26647 ^ 4612 ^ -808];
         var10001[32557 ^ -16766 ^ 20121 ^ -28874] = a;
         return String.format(var10000, var10001);
      }
   }

   public static float method_bFw() {
      float b = Float.intBitsToFloat(246453 ^ '舲' ^ 252843 ^ -225878244 ^ 231019 ^ '衭' ^ 237997 ^ -225875045);
      int c = 13981 ^ -300 ^ 2873 ^ -15504;
      float[] var2 = method_bFF();
      int var3 = var2.length;

      for(int var4 = 20071 ^ -5923 ^ 15701 ^ -25617; var4 < var3; ++var4) {
         float a = var2[var4];
         if (a > Float.intBitsToFloat('\uf2f8' ^ '걗' ^ 5278 ^ -568258563 ^ '\ued9f' ^ 73808 ^ 26779 ^ -568270696)) {
            b += a;
            ++c;
         }
      }

      return c > 0 ? MathHelper.clamp(b / (float)c, Float.intBitsToFloat('ꆉ' ^ '\udb6e' ^ 24019 ^ -1727115913 ^ '\uec86' ^ '荒' ^ 3822 ^ -1727132807), Float.intBitsToFloat(23230 ^ '\uebba' ^ 14257 ^ -1330969494 ^ 21259 ^ '쪳' ^ 10251 ^ -250924180)) : Float.intBitsToFloat(124545 ^ 113330 ^ 11033 ^ 1936504053 ^ 3876 ^ 113389 ^ 125479 ^ 1936497713);
   }

   public _ej/* $FF was: 0ej*/() {
   }

   private static long method_bFH() {
      return field_c;
   }

   private static int method_bFJ() {
      return field_d;
   }

   private static float[] method_bFF() {
      return field_a;
   }

   private static long method_bFO() {
      return field_b;
   }

   private static float[] method_bFI() {
      return field_a;
   }

   private static void method_bFM(int var0) {
      field_d = var0;
   }

   private static float[] method_bFK() {
      return field_a;
   }

   private static void method_bFN(long var0) {
      field_c = var0;
   }

   @Subscribe
   public void method_bFA(0dg a) {
      if (a.method_bAd() instanceof UW) {
         method_bFx();
      }

      if (a.method_bAd() instanceof UW || a.method_bAd() instanceof TD || a.method_bAd() instanceof UH || a.method_bAd() instanceof Vc) {
         method_bFQ(System.currentTimeMillis());
      }

   }

   public static long method_bFy() {
      return System.currentTimeMillis() - method_bFO();
   }

   private static void method_bFC(int var0) {
      field_d = var0;
   }

   private static float[] method_bFE() {
      return field_a;
   }

   private static int method_bFL() {
      return field_d;
   }

   private static void method_bFD(long var0) {
      field_c = var0;
   }
}
