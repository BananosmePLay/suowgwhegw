package neo;

import java.util.Random;

public class bhw implements bhy {
   private final boolean onFire;

   public bhw(boolean onFireIn) {
      this.onFire = onFireIn;
   }

   public boolean testProperty(Random random, Ig entityIn) {
      return entityIn.isBurning() == this.onFire;
   }

   // $FF: synthetic method
   static boolean access$000(bhw x0) {
      return x0.onFire;
   }
}
