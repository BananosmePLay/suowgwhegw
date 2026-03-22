package neo;

import java.util.Random;

public class 0ej implements 0eB {
   public static String randomStringByPattern(int length, String pattern) {
      Random random = new Random();
      StringBuilder randomWord = new StringBuilder();

      for(int i = 4350 ^ -2300 ^ 16220 ^ -10074; i < length; ++i) {
         int index = random.nextInt(pattern.length());
         char randomChar = pattern.charAt(index);
         randomWord.append(randomChar);
      }

      return randomWord.toString();
   }

   public static int getIntRandom(int endnumber) {
      return (int)(Math.random() * (double)endnumber + Double.longBitsToDouble(-1877981089171743998L ^ -2738168617999508734L));
   }

   public static double getRandom(int endnumber) {
      return Math.random() * (double)endnumber + Double.longBitsToDouble(-603091883748537785L ^ -4012316801668003257L);
   }

   public static String randomNumber(int length) {
      StringBuilder builder = new StringBuilder();
      char[] buffer = TJ2F1AdaiN("\u05ca\u05c9\u05c8\u05cf\u05ce\u05cd\u05cc׃ׂ\u05cb").toCharArray();

      for(int i = 151 ^ -30918 ^ 17397 ^ -15272; i < length; ++i) {
         Random rand = new Random();
         char[] var10002 = new char[32258 ^ -3500 ^ 16161 ^ -19594];
         var10002[14879 ^ -23473 ^ 15902 ^ -24498] = buffer[rand.nextInt(buffer.length)];
         String s = new String(var10002);
         builder.append(rand.nextBoolean() ? s : s.toUpperCase());
      }

      return builder.toString();
   }

   public _ej/* $FF was: 0ej*/() {
   }

   public static int intRandom(int min, int max) {
      return (int)(Math.random() * (double)(max - min)) + min;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String TJ2F1AdaiN(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23209 ^ -21614 ^ 20829 ^ -24474; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7107 ^ -1802 ^ 15065 ^ -9193));
      }

      return var1.toString();
   }

   public static String randomChineseString(int length) {
      StringBuilder sb = new StringBuilder();
      Random random = new Random();

      for(int i = 9421 ^ -5707 ^ 8934 ^ -4194; i < length; ++i) {
         int codePoint = ('\ue39c' ^ -44753 ^ 7133 ^ -6290) + random.nextInt('璉' ^ 'ꉫ' ^ 13683 ^ 15500);
         sb.append(Character.toChars(codePoint));
      }

      return sb.toString();
   }

   public static double getDoubleRandom2(double endnumber) {
      return Math.random() * endnumber + Double.longBitsToDouble(4013945650017219847L ^ 595713532843013383L);
   }

   public static String randomString(int length) {
      StringBuilder builder = new StringBuilder();
      char[] buffer = TJ2F1AdaiN("֊\u058c֞։֏ւ֎֒֔\u058b֚ֈ֑֟֝֜֓\u0590֗ցփ֘֍֖֙֕\u05ca\u05c9\u05c8\u05cf\u05ce\u05cd\u05cc׃ׂ\u05cb").toCharArray();

      for(int i = 1441 ^ -15259 ^ 27613 ^ -21991; i < length; ++i) {
         Random rand = new Random();
         char[] var10002 = new char[19908 ^ -10265 ^ 22103 ^ -13195];
         var10002[25233 ^ -32716 ^ 23399 ^ -17982] = buffer[rand.nextInt(buffer.length)];
         String s = new String(var10002);
         builder.append(rand.nextBoolean() ? s : s.toUpperCase());
      }

      return builder.toString();
   }

   public static double getDoubleRandom(int endnumber) {
      return Math.random() * (double)endnumber + Double.longBitsToDouble(-1343746559239778780L ^ -3266783600126980572L);
   }

   public static float floatRandom(float min, float max) {
      return (float)((double)min + (double)(max - min) * Math.random());
   }
}
