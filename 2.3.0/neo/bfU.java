package neo;

import java.util.Random;

abstract class bfU {
   private bfU() {
   }

   public abstract String get1x1(Random var1);

   public abstract String get1x1Secret(Random var1);

   public abstract String get1x2SideEntrance(Random var1, boolean var2);

   public abstract String get1x2FrontEntrance(Random var1, boolean var2);

   public abstract String get1x2Secret(Random var1);

   public abstract String get2x2(Random var1);

   public abstract String get2x2Secret(Random var1);

   // $FF: synthetic method
   bfU(Object x0) {
      this();
   }
}
