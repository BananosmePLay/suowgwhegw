package neo;

import java.util.Random;

public abstract class bdA {
   protected in blockstate;

   public bdA() {
      this.blockstate = Nk.AIR.getDefaultState();
   }

   public abstract void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5);

   public in getBlockState() {
      return this.blockstate;
   }
}
