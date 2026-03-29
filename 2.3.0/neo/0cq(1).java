package neo;

import java.awt.Color;
import java.util.function.Supplier;

public class 0cq extends 0cv {
   public int field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static int method_bni(0cq var0) {
      return var0.field_a;
   }

   public int method_bnf() {
      return method_bnj(this);
   }

   public Color method_bne() {
      return new Color(method_bni(this));
   }

   private static int method_bnj(0cq var0) {
      return var0.field_a;
   }

   public int method_bob() {
      return 20338 ^ -31408 ^ 14239 ^ -625;
   }

   public _cq/* $FF was: 0cq*/(String a, int b) {
      super(a);
      this.field_a = b;
      this.method_bnZ(() -> {
         return Boolean.valueOf((boolean)(20602 ^ -22269 ^ 4984 ^ -5632));
      });
   }

   public void method_bng(int a) {
      method_bnk(this, a);
   }

   private static void method_bnk(0cq var0, int var1) {
      var0.field_a = var1;
   }

   public _cq/* $FF was: 0cq*/(String a, int b, Supplier<Boolean> c) {
      super(a);
      this.field_a = b;
      this.method_bnZ(c);
   }
}
