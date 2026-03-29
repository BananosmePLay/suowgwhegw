package neo;

import net.minecraft.util.math.BlockPos;

public abstract class GL extends Gi {
   private final Ik creature;
   private final double movementSpeed;
   protected int runDelay;
   private int timeoutCounter;
   private int maxStayTicks;
   protected BlockPos destinationBlock;
   private boolean isAboveDestination;
   private final int searchLength;

   public GL(Ik creature, double speedIn, int length) {
      this.destinationBlock = BlockPos.ORIGIN;
      this.creature = creature;
      this.movementSpeed = speedIn;
      this.searchLength = length;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      if (this.runDelay > 0) {
         --this.runDelay;
         return false;
      } else {
         this.runDelay = 200 + this.creature.getRNG().nextInt(200);
         return this.searchForDestination();
      }
   }

   public boolean shouldContinueExecuting() {
      return this.timeoutCounter >= -this.maxStayTicks && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.creature.world, this.destinationBlock);
   }

   public void startExecuting() {
      this.creature.getNavigator().tryMoveToXYZ((double)((float)this.destinationBlock.getX()) + 0.5, (double)(this.destinationBlock.getY() + 1), (double)((float)this.destinationBlock.getZ()) + 0.5, this.movementSpeed);
      this.timeoutCounter = 0;
      this.maxStayTicks = this.creature.getRNG().nextInt(this.creature.getRNG().nextInt(1200) + 1200) + 1200;
   }

   public void updateTask() {
      if (this.creature.getDistanceSqToCenter(this.destinationBlock.up()) > 1.0) {
         this.isAboveDestination = false;
         ++this.timeoutCounter;
         if (this.timeoutCounter % 40 == 0) {
            this.creature.getNavigator().tryMoveToXYZ((double)((float)this.destinationBlock.getX()) + 0.5, (double)(this.destinationBlock.getY() + 1), (double)((float)this.destinationBlock.getZ()) + 0.5, this.movementSpeed);
         }
      } else {
         this.isAboveDestination = true;
         --this.timeoutCounter;
      }

   }

   protected boolean getIsAboveDestination() {
      return this.isAboveDestination;
   }

   private boolean searchForDestination() {
      int i = this.searchLength;
      int j = true;
      BlockPos blockpos = new BlockPos(this.creature);

      for(int k = 0; k <= 1; k = k > 0 ? -k : 1 - k) {
         for(int l = 0; l < i; ++l) {
            for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
               for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                  BlockPos blockpos1 = blockpos.add(i1, k - 1, j1);
                  if (this.creature.isWithinHomeDistanceFromPosition(blockpos1) && this.shouldMoveTo(this.creature.world, blockpos1)) {
                     this.destinationBlock = blockpos1;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   protected abstract boolean shouldMoveTo(bij var1, BlockPos var2);
}
