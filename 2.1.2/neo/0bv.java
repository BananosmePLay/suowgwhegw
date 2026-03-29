package neo;

import java.util.function.Supplier;

public class 0bv extends 0bC {
   public boolean value;
   public double animation;

   public _bv/* $FF was: 0bv*/(String name, boolean value, Supplier<Boolean> visible) {
      super(name);
      this.value = value;
      this.setVisible(visible);
   }

   public _bv/* $FF was: 0bv*/(String name, boolean value) {
      super(name);
      this.value = value;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(2449 ^ -16208 ^ 28327 ^ -22649));
      });
   }

   private static void qVV4YVMy31(0bv var0, boolean var1) {
      var0.value = var1;
   }

   public void setBoolValue(boolean value) {
      qVV4YVMy31(this, value);
   }
}
