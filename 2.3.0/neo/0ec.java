package neo;

import java.util.Random;

public class 0ec {
   private static String _ _;

   public _ec/* $FF was: 0ec*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String OlNZJ4aAoA(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6930 ^ -9272 ^ 17311 ^ -31931; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 1872 ^ -6526 ^ 12426 ^ -12044));
      }

      return var1.toString();
   }

   public static String randomWithPattern(int d, String e) {
      Random f = new Random();
      StringBuilder g = new StringBuilder();

      for(int c = 31760 ^ -22361 ^ 21833 ^ -32258; c < d; ++c) {
         int a = f.nextInt(e.length());
         char b = e.charAt(a);
         g.append(b);
      }

      return g.toString();
   }

   public static float floatRandom(float a, float b) {
      return (float)((double)a + (double)(b - a) * Math.random());
   }

   public static int intRandom(int a, int b) {
      return (int)(Math.random() * (double)(b - a)) + a;
   }

   public static String randomNumber(int a) {
      return randomWithPattern(a, OlNZJ4aAoA("ƝƞƟƘƙƚƛƔƕƜ"));
   }

   public static String randomChineseString(int c) {
      StringBuilder d = new StringBuilder();
      Random e = new Random();

      for(int b = 17188 ^ -12459 ^ 29852 ^ -1811; b < c; ++b) {
         int a = ('텇' ^ 24364 ^ '쌆' ^ 877) + e.nextInt('\ue87e' ^ -29304 ^ '\ud8aa' ^ -4260);
         d.append(Character.toChars(a));
      }

      return d.toString();
   }

   public static String randomString(int a) {
      return randomWithPattern(a, OlNZJ4aAoA("ǝǛǉǞǘǕǙǅǃǜǍǟǈǊǋǄǆǇǀǖǔǏǚǎǂǁƝƞƟƘƙƚƛƔƕƜ"));
   }
}
