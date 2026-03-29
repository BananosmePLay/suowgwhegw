package neo;

import java.util.function.Supplier;

public class 0bA extends 0bC {
   public String text;
   public boolean typing;

   private static String k3aJT0ADQN(0bA var0) {
      return var0.text;
   }

   public void setTextValue(String txt) {
      tBtvVZtArC(this, txt);
   }

   private static void tBtvVZtArC(0bA var0, String var1) {
      var0.text = var1;
   }

   public String get() {
      return k3aJT0ADQN(this);
   }

   public _bA/* $FF was: 0bA*/(String name, String text, Supplier<Boolean> visible) {
      super(name);
      this.text = text;
      this.setVisible(visible);
   }

   public _bA/* $FF was: 0bA*/(String name, String text) {
      super(name);
      this.text = text;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(26195 ^ -3557 ^ 26459 ^ -3310));
      });
   }
}
