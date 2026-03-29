package neo;

import java.util.Random;

class bfV extends bfU {
   private bfV() {
      super(null);
   }

   public String get1x1(Random p_191104_1_) {
      return "1x1_b" + (p_191104_1_.nextInt(4) + 1);
   }

   public String get1x1Secret(Random p_191099_1_) {
      return "1x1_as" + (p_191099_1_.nextInt(4) + 1);
   }

   public String get1x2SideEntrance(Random p_191100_1_, boolean p_191100_2_) {
      return p_191100_2_ ? "1x2_c_stairs" : "1x2_c" + (p_191100_1_.nextInt(4) + 1);
   }

   public String get1x2FrontEntrance(Random p_191098_1_, boolean p_191098_2_) {
      return p_191098_2_ ? "1x2_d_stairs" : "1x2_d" + (p_191098_1_.nextInt(5) + 1);
   }

   public String get1x2Secret(Random p_191102_1_) {
      return "1x2_se" + (p_191102_1_.nextInt(1) + 1);
   }

   public String get2x2(Random p_191101_1_) {
      return "2x2_b" + (p_191101_1_.nextInt(5) + 1);
   }

   public String get2x2Secret(Random p_191103_1_) {
      return "2x2_s1";
   }

   // $FF: synthetic method
   bfV(Object x0) {
      this();
   }
}
