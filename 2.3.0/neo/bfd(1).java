package neo;

import java.util.Random;

class bfd extends bdA {
   private bfd() {
   }

   public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
      if (wall) {
         float f = rand.nextFloat();
         if (f < 0.2F) {
            this.blockstate = Nk.STONEBRICK.getStateFromMeta(hb.CRACKED_META);
         } else if (f < 0.5F) {
            this.blockstate = Nk.STONEBRICK.getStateFromMeta(hb.MOSSY_META);
         } else if (f < 0.55F) {
            this.blockstate = Nk.MONSTER_EGG.getStateFromMeta(gA.STONEBRICK.getMetadata());
         } else {
            this.blockstate = Nk.STONEBRICK.getDefaultState();
         }
      } else {
         this.blockstate = Nk.AIR.getDefaultState();
      }

   }

   // $FF: synthetic method
   bfd(Object x0) {
      this();
   }
}
