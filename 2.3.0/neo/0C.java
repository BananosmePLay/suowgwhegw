package neo;

import java.util.Random;

public enum 0C {
   field_a,
   field_c;

   private static String _ _;

   // $FF: synthetic method
   // $FF: bridge method
   private static String T95AtVg0eP(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7182 ^ -31427 ^ 16356 ^ -22825; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20529 ^ -7535 ^ 31380 ^ -13726));
      }

      return var1.toString();
   }

   static {
      0C[] var10000 = new 0C[28944 ^ -25220 ^ 9549 ^ -14045];
      var10000[9617 ^ -14204 ^ 15388 ^ -12023] = field_c;
      var10000[15002 ^ -6082 ^ 13217 ^ -7932] = field_a;
   }

   private _C/* $FF was: 0C*/() {
   }

   private static 0C[] j67vyTlV4D() {
      return field_b;
   }

   public static 0C getByName(String a) {
      if (a.equalsIgnoreCase(T95AtVg0eP("ȤȷȸȲȹȻ"))) {
         return values()[(new Random()).nextInt(values().length)];
      } else {
         try {
            return valueOf(a.toUpperCase());
         } catch (IllegalArgumentException var2) {
            return null;
         }
      }
   }
}
