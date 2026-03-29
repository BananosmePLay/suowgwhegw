package neo;

import java.util.function.Supplier;

public abstract class 0cv {
   public final String field_a;
   public Supplier<Boolean> field_b;
   private static String _DSC GG NEOWARECLIENT _;

   public boolean method_bnY() {
      return (Boolean)method_boc(this).get();
   }

   private static void method_bod(0cv var0, Supplier var1) {
      var0.field_b = var1;
   }

   public void method_bnZ(Supplier<Boolean> a) {
      method_bod(this, a);
   }

   private static String method_boe(0cv var0) {
      return var0.field_a;
   }

   private static Supplier method_boc(0cv var0) {
      return var0.field_b;
   }

   public _cv/* $FF was: 0cv*/(String a) {
      this.field_a = a;
   }

   public abstract int method_bob();

   public String method_boa() {
      return method_boe(this);
   }
}
