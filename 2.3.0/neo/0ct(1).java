package neo;

import java.util.function.Supplier;

public class 0ct extends 0cv {
   public final float field_b;
   public float field_a;
   public final float field_c;
   private static String _DSC GG NEOWARECLIENT _;

   public float method_bnJ() {
      return method_bnO(this);
   }

   public int method_bob() {
      return 14268 ^ -17197 ^ 17541 ^ -12290;
   }

   public float method_bnH() {
      return method_bnM(this);
   }

   private static void method_bnL(0ct var0, float var1) {
      var0.field_a = var1;
   }

   private static float method_bnO(0ct var0) {
      return var0.field_c;
   }

   public _ct/* $FF was: 0ct*/(String a, float b, float c, float d, Supplier<Boolean> e) {
      super(a);
      this.field_a = b;
      this.field_b = c;
      this.field_c = d;
      this.method_bnZ(e);
   }

   public float method_bnI() {
      return method_bnN(this);
   }

   private static float method_bnN(0ct var0) {
      return var0.field_b;
   }

   private static float method_bnM(0ct var0) {
      return var0.field_a;
   }

   public _ct/* $FF was: 0ct*/(String a, float b, float c, float d) {
      super(a);
      this.field_a = b;
      this.field_b = c;
      this.field_c = d;
      this.method_bnZ(() -> {
         return Boolean.valueOf((boolean)(4399 ^ -8891 ^ 12360 ^ -989));
      });
   }

   public void method_bnG(float a) {
      method_bnL(this, a);
   }
}
