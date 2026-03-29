package neo;

import java.util.Random;

public class bgA implements bgv {
   private final float chance;

   public bgA(float chanceIn) {
      this.chance = chanceIn;
   }

   public boolean testCondition(Random rand, bhg context) {
      return rand.nextFloat() < this.chance;
   }

   // $FF: synthetic method
   static float access$000(bgA x0) {
      return x0.chance;
   }
}
