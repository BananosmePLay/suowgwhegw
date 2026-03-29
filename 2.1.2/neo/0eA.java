package neo;

import java.awt.Color;

public class 0eA {
   public Color twoColor;
   public String name;
   public Color oneColor;

   public _eA/* $FF was: 0eA*/(String name, Color oneColor, Color twoColor) {
      this.name = name;
      this.oneColor = oneColor;
      this.twoColor = twoColor;
   }

   private static String TrVid9Wy9P(0eA var0) {
      return var0.name;
   }

   private static Color IYuyYrikSl(0eA var0) {
      return var0.twoColor;
   }

   public String getName() {
      return TrVid9Wy9P(this);
   }

   private static void QlGvDvd4ww(0eA var0, Color var1) {
      var0.oneColor = var1;
   }

   private static void dYXhprGAiv(0eA var0, String var1) {
      var0.name = var1;
   }

   public void setName(String name) {
      dYXhprGAiv(this, name);
   }

   public void setTwoColor(Color twoColor) {
      DyvGVK46uc(this, twoColor);
   }

   public Color getTwoColor() {
      return IYuyYrikSl(this);
   }

   private static void DyvGVK46uc(0eA var0, Color var1) {
      var0.twoColor = var1;
   }

   private static Color BgNeZtD3uh(0eA var0) {
      return var0.oneColor;
   }

   public Color getOneColor() {
      return BgNeZtD3uh(this);
   }

   public void setOneColor(Color oneColor) {
      QlGvDvd4ww(this, oneColor);
   }
}
