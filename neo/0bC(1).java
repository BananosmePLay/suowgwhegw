package neo;

import java.util.function.Supplier;

public class 0bC {
   public String name;
   public Supplier<Boolean> visible;

   private static Supplier lJVbHHZ2iH(0bC var0) {
      return var0.visible;
   }

   public void setVisible(Supplier<Boolean> visible) {
      wAl4WxVdb1(this, visible);
   }

   private static void wAl4WxVdb1(0bC var0, Supplier var1) {
      var0.visible = var1;
   }

   public boolean isVisible() {
      return (Boolean)lJVbHHZ2iH(this).get();
   }

   public _bC/* $FF was: 0bC*/(String name) {
      this.name = name;
   }
}
