package neo;

import java.util.Random;

public abstract class bgI {
   private final bgv[] conditions;

   protected bgI(bgv[] conditionsIn) {
      this.conditions = conditionsIn;
   }

   public abstract Qy apply(Qy var1, Random var2, bhg var3);

   public bgv[] getConditions() {
      return this.conditions;
   }
}
