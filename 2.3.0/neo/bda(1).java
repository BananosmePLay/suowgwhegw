package neo;

import java.util.Random;

class bda extends bdA {
   private bda() {
   }

   public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
      if (rand.nextFloat() < 0.4F) {
         this.blockstate = Nk.COBBLESTONE.getDefaultState();
      } else {
         this.blockstate = Nk.MOSSY_COBBLESTONE.getDefaultState();
      }

   }

   // $FF: synthetic method
   bda(Object x0) {
      this();
   }
}
