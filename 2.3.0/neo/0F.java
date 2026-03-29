package neo;

import java.util.Random;

public enum 0F {
   field_b,
   field_a;

   private static int _DSC GG NEOWARECLIENT _;

   private _F/* $FF was: 0F*/() {
   }

   public static 0F getByName(String a) {
      if (a.equalsIgnoreCase(x4t9Hlp4yP("˨˻˴˾˵˷"))) {
         return values()[(new Random()).nextInt(values().length)];
      } else {
         try {
            return valueOf(a.toUpperCase());
         } catch (IllegalArgumentException var2) {
            return null;
         }
      }
   }

   static {
      0F[] var10000 = new 0F[22075 ^ -4888 ^ 12114 ^ -27261];
      var10000[18026 ^ -17481 ^ 29759 ^ -30238] = field_b;
      var10000[17782 ^ -23498 ^ 4714 ^ -3285] = field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String x4t9Hlp4yP(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18001 ^ -5316 ^ 8060 ^ -19951; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13695 ^ -2512 ^ 6519 ^ -10078));
      }

      return var1.toString();
   }

   private static 0F[] ObR9x6GQFb() {
      return field_c;
   }
}
