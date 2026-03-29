package neo;

import java.util.Random;

public class bgS extends bgI {
   private final bhC countRange;

   public bgS(bgv[] conditionsIn, bhC countRangeIn) {
      super(conditionsIn);
      this.countRange = countRangeIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      stack.setCount(this.countRange.generateInt(rand));
      return stack;
   }

   // $FF: synthetic method
   static bhC access$000(bgS x0) {
      return x0.countRange;
   }
}
