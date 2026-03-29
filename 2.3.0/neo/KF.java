package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

class KF extends Gi {
   private final KG silverfish;
   private int lookForFriends;

   public KF(KG silverfishIn) {
      this.silverfish = silverfishIn;
   }

   public void notifyHurt() {
      if (this.lookForFriends == 0) {
         this.lookForFriends = 20;
      }

   }

   public boolean shouldExecute() {
      return this.lookForFriends > 0;
   }

   public void updateTask() {
      --this.lookForFriends;
      if (this.lookForFriends <= 0) {
         bij world = this.silverfish.world;
         Random random = this.silverfish.getRNG();
         BlockPos blockpos = new BlockPos(this.silverfish);

         for(int i = 0; i <= 5 && i >= -5; i = (i <= 0 ? 1 : 0) - i) {
            for(int j = 0; j <= 10 && j >= -10; j = (j <= 0 ? 1 : 0) - j) {
               for(int k = 0; k <= 10 && k >= -10; k = (k <= 0 ? 1 : 0) - k) {
                  BlockPos blockpos1 = blockpos.add(j, i, k);
                  in iblockstate = world.getBlockState(blockpos1);
                  if (iblockstate.getBlock() == Nk.MONSTER_EGG) {
                     if (world.getGameRules().getBoolean("mobGriefing")) {
                        world.destroyBlock(blockpos1, true);
                     } else {
                        world.setBlockState(blockpos1, ((gA)iblockstate.getValue(gB.VARIANT)).getModelBlock(), 3);
                     }

                     if (random.nextBoolean()) {
                        return;
                     }
                  }
               }
            }
         }
      }

   }
}
