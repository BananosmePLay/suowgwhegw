package neo;

import java.util.function.Supplier;

public class 0bz extends 0bC {
   public float max;
   public boolean pressed;
   public float min;
   public float value;
   public float widthAnimated;
   public float printAnimated;
   public float increment;
   public int alphaText;

   private static void oO7yaJQtuh(0bz var0, float var1) {
      var0.value = var1;
   }

   public _bz/* $FF was: 0bz*/(String name, float value, float min, float max, float increment) {
      super(name);
      this.value = value;
      this.min = min;
      this.max = max;
      this.increment = increment;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(16580 ^ -14136 ^ 25247 ^ -5486));
      });
   }

   public _bz/* $FF was: 0bz*/(String name, float value, float min, float max, float increment, Supplier<Boolean> visible) {
      super(name);
      this.value = value;
      this.min = min;
      this.max = max;
      this.increment = increment;
      this.setVisible(visible);
   }

   public void setValueNumber(float value) {
      oO7yaJQtuh(this, value);
   }
}
