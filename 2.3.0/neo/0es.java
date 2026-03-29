package neo;

import java.util.Arrays;
import net.minecraft.client.main.Main;

public class 0es {
   private static String _ _;

   public _es/* $FF was: 0es*/() {
   }

   public static <T> T[] method_bJR(T[] a, T[] b) {
      T[] c = Arrays.copyOf(a, a.length + b.length);
      System.arraycopy(b, 19252 ^ -24446 ^ 6083 ^ -907, c, a.length, b.length);
      return c;
   }

   public static void main(String[] a) {
      String[] var10000 = new String[6452 ^ -11213 ^ 28782 ^ -17053];
      var10000[15374 ^ -13818 ^ 27378 ^ -25350] = method_bJS("ččŖŅŒœŉŏŎ");
      var10000[230 ^ -16728 ^ 11595 ^ -27900] = method_bJS("ōŃŐ");
      var10000[6789 ^ -18902 ^ 26935 ^ -14950] = method_bJS("ččŁŃŃŅœœŴŏŋŅŎ");
      var10000[15575 ^ -9815 ^ 8889 ^ -14396] = method_bJS("Đ");
      var10000[27325 ^ -29420 ^ 2802 ^ -4769] = method_bJS("ččŁœœŅŔœŤŉŒ");
      var10000[1924 ^ -12985 ^ 7310 ^ -10680] = method_bJS("ŁœœŅŔœ");
      var10000[28640 ^ -13243 ^ 3294 ^ -20611] = method_bJS("ččŁœœŅŔũŎńŅŘ");
      var10000[3346 ^ -640 ^ 8041 ^ -4100] = method_bJS("đĎđĒ");
      var10000[13176 ^ -2912 ^ 17124 ^ -31436] = method_bJS("ččŕœŅŒŰŒŏŐŅŒŔŉŅœ");
      var10000[25708 ^ -31431 ^ 3064 ^ -5468] = method_bJS("śŝ");
      Main.main((String[])method_bJR(var10000, a));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bJS(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14718 ^ -23292 ^ 8049 ^ -31989; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3103 ^ -1698 ^ 31591 ^ -28922));
      }

      return var1.toString();
   }
}
