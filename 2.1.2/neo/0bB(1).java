package neo;

import java.awt.Color;

public class 0bB extends 0bC {
   public Color oneColor;
   public Color twoColor;

   public void setOneColor(Color oneColor) {
      jwHFaFzs8G(this, oneColor);
   }

   private static void jwHFaFzs8G(0bB var0, Color var1) {
      var0.oneColor = var1;
   }

   public Color getOneColor() {
      return uvi6vjaPcT(this);
   }

   public void setTwoColor(Color twoColor) {
      qemmRA7QRO(this, twoColor);
   }

   private static void qemmRA7QRO(0bB var0, Color var1) {
      var0.twoColor = var1;
   }

   public 0eA getTheme() {
      return new 0eA(9Wi4P4w2eZ(this), this.getOneColor(), this.getTwoColor());
   }

   private static Color uvi6vjaPcT(0bB var0) {
      return var0.oneColor;
   }

   public _bB/* $FF was: 0bB*/(String name, Color one, Color two) {
      super(name);
      this.oneColor = one;
      this.twoColor = two;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(7359 ^ -15392 ^ 23719 ^ -31751));
      });
   }

   private static String _Wi4P4w2eZ/* $FF was: 9Wi4P4w2eZ*/(0bB var0) {
      return var0.name;
   }

   public Color getTwoColor() {
      return 7Ulf8Fx3d2(this);
   }

   private static Color _Ulf8Fx3d2/* $FF was: 7Ulf8Fx3d2*/(0bB var0) {
      return var0.twoColor;
   }
}
