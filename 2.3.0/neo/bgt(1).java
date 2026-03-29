package neo;

import java.util.Random;

public class bgt implements bgv {
   private final boolean inverse;

   public bgt(boolean inverseIn) {
      this.inverse = inverseIn;
   }

   public boolean testCondition(Random rand, bhg context) {
      boolean flag = context.getKillerPlayer() != null;
      return flag == !this.inverse;
   }

   // $FF: synthetic method
   static boolean access$000(bgt x0) {
      return x0.inverse;
   }
}
