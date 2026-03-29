package neo;

import java.util.Random;

public class bgC implements bgv {
   private final float chance;
   private final float lootingMultiplier;

   public bgC(float chanceIn, float lootingMultiplierIn) {
      this.chance = chanceIn;
      this.lootingMultiplier = lootingMultiplierIn;
   }

   public boolean testCondition(Random rand, bhg context) {
      int i = 0;
      if (context.getKiller() instanceof Iw) {
         i = Ft.getLootingModifier((Iw)context.getKiller());
      }

      return rand.nextFloat() < this.chance + (float)i * this.lootingMultiplier;
   }

   // $FF: synthetic method
   static float access$000(bgC x0) {
      return x0.chance;
   }

   // $FF: synthetic method
   static float access$100(bgC x0) {
      return x0.lootingMultiplier;
   }
}
