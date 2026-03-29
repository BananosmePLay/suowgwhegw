package neo;

import java.awt.Color;
import java.util.function.Supplier;

public class 0bw extends 0bC {
   public int color;
   public int picker;

   public void setColorValue(int color) {
      WWaxlSAXWP(this, color);
   }

   public int getColorValue() {
      return jl1MvXmtwz(this);
   }

   public _bw/* $FF was: 0bw*/(String name, int color) {
      super(name);
      this.color = color;
      this.setVisible(() -> {
         return Boolean.valueOf((boolean)(7038 ^ -5008 ^ 6626 ^ -4371));
      });
   }

   public void setPicker(int picker) {
      4QYOCWYSJB(this, picker);
   }

   private static void _QYOCWYSJB/* $FF was: 4QYOCWYSJB*/(0bw var0, int var1) {
      var0.picker = var1;
   }

   private static int jl1MvXmtwz(0bw var0) {
      return var0.color;
   }

   public _bw/* $FF was: 0bw*/(String name, int color, Supplier<Boolean> visible) {
      super(name);
      this.color = color;
      this.setVisible(visible);
   }

   private static int N4nwasfLlh(0bw var0) {
      return var0.color;
   }

   public int getPicker() {
      return uYIQQF3TgN(this);
   }

   public Color getColorc() {
      return new Color(N4nwasfLlh(this));
   }

   private static void WWaxlSAXWP(0bw var0, int var1) {
      var0.color = var1;
   }

   private static int uYIQQF3TgN(0bw var0) {
      return var0.picker;
   }
}
