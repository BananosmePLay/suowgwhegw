package neo;

import java.util.Random;

public class bgG extends bgI {
   private final bhC randomLevel;
   private final boolean isTreasure;

   public bgG(bgv[] conditionsIn, bhC randomRange, boolean isTreasureIn) {
      super(conditionsIn);
      this.randomLevel = randomRange;
      this.isTreasure = isTreasureIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      return Ft.addRandomEnchantment(rand, stack, this.randomLevel.generateInt(rand), this.isTreasure);
   }

   // $FF: synthetic method
   static bhC access$000(bgG x0) {
      return x0.randomLevel;
   }

   // $FF: synthetic method
   static boolean access$100(bgG x0) {
      return x0.isTreasure;
   }
}
