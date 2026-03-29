package neo;

public class 0eg {
   private static int _DSC GG NEOWARECLIENT _;

   public static String method_bFq(int a, String b, String c, String d) {
      int e = a % (408 ^ -18166 ^ 20739 ^ -5643);
      if (e > (28045 ^ -12465 ^ 28154 ^ -12495) && e < (1587 ^ -8387 ^ 26843 ^ -20031)) {
         return d;
      } else {
         e %= 4867 ^ -13378 ^ 32668 ^ -22741;
         if (e == (11043 ^ -20283 ^ 10546 ^ -19755)) {
            return b;
         } else {
            return e > (22085 ^ -14917 ^ 18644 ^ -9429) && e < (28123 ^ -27076 ^ 25574 ^ -26620) ? c : d;
         }
      }
   }

   public static String method_bFs(0dX b, String c, int d) {
      StringBuilder e = new StringBuilder();
      int f = 22108 ^ -29121 ^ 7438 ^ -14995;
      char[] var5 = (new StringBuilder(c)).reverse().toString().toCharArray();
      int var6 = var5.length;

      for(int var7 = 19404 ^ -25371 ^ 27270 ^ -16977; var7 < var6; ++var7) {
         char a = var5[var7];
         f += b.method_bDO(String.valueOf(a));
         e.append(a);
         if (f > d) {
            break;
         }
      }

      return e.reverse().toString();
   }

   public static boolean method_bFr(String[] b, String c) {
      String[] var2 = b;
      int var3 = b.length;

      for(int var4 = 31506 ^ -15224 ^ 2403 ^ -18695; var4 < var3; ++var4) {
         String a = var2[var4];
         if (c.toLowerCase().contains(a)) {
            return (boolean)(4862 ^ -23311 ^ 1613 ^ -20413);
         }
      }

      return (boolean)(31481 ^ -31671 ^ 1610 ^ -1798);
   }

   public _eg/* $FF was: 0eg*/() {
   }
}
