package neo;

import java.util.function.Supplier;

public class 0cr extends 0cv {
   public final String[] field_a;
   private static String _ _;

   private static String[] method_bno(0cr var0) {
      return var0.field_a;
   }

   private static String[] method_bnp(0cr var0) {
      return var0.field_a;
   }

   public String[] method_bnl() {
      return method_bno(this);
   }

   public _cr/* $FF was: 0cr*/(String... a) {
      super(method_bnn(""));
      this.field_a = a;
      this.method_bnZ(() -> {
         return Boolean.valueOf((boolean)(5892 ^ -8060 ^ 20392 ^ -18391));
      });
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bnn(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23934 ^ -6909 ^ 4615 ^ -21894; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13361 ^ -25406 ^ 15921 ^ -27984));
      }

      return var1.toString();
   }

   public _cr/* $FF was: 0cr*/(Supplier<Boolean> a, String... b) {
      super(method_bnn(""));
      this.field_a = b;
      this.method_bnZ(a);
   }

   public int method_bob() {
      return method_bnp(this).length * (11608 ^ -14218 ^ 28681 ^ -27347);
   }
}
