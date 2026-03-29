package neo;

import net.minecraft.util.math.BlockPos;

class LU extends GL {
   private final LY rabbit;
   private boolean wantsToRaid;
   private boolean canRaid;

   public LU(LY rabbitIn) {
      super(rabbitIn, 0.699999988079071, 16);
      this.rabbit = rabbitIn;
   }

   public boolean shouldExecute() {
      if (this.runDelay <= 0) {
         if (!this.rabbit.world.getGameRules().getBoolean("mobGriefing")) {
            return false;
         }

         this.canRaid = false;
         this.wantsToRaid = LY.access$000(this.rabbit);
         this.wantsToRaid = true;
      }

      return super.shouldExecute();
   }

   public boolean shouldContinueExecuting() {
      return this.canRaid && super.shouldContinueExecuting();
   }

   public void updateTask() {
      super.updateTask();
      this.rabbit.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5, 10.0F, (float)this.rabbit.getVerticalFaceSpeed());
      if (this.getIsAboveDestination()) {
         bij world = this.rabbit.world;
         BlockPos blockpos = this.destinationBlock.up();
         in iblockstate = world.getBlockState(blockpos);
         co block = iblockstate.getBlock();
         if (this.canRaid && block instanceof cQ) {
            Integer integer = (Integer)iblockstate.getValue(cQ.AGE);
            if (integer == 0) {
               world.setBlockState(blockpos, Nk.AIR.getDefaultState(), 2);
               world.destroyBlock(blockpos, true);
            } else {
               world.setBlockState(blockpos, iblockstate.withProperty(cQ.AGE, integer - 1), 2);
               world.playEvent(2001, blockpos, co.getStateId(iblockstate));
            }

            this.rabbit.createEatingParticles();
         }

         this.canRaid = false;
         this.runDelay = 10;
      }

   }

   protected boolean shouldMoveTo(bij worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos).getBlock();
      if (block == Nk.FARMLAND && this.wantsToRaid && !this.canRaid) {
         pos = pos.up();
         in iblockstate = worldIn.getBlockState(pos);
         block = iblockstate.getBlock();
         if (block instanceof cQ && ((cQ)block).isMaxAge(iblockstate)) {
            this.canRaid = true;
            return true;
         }
      }

      return false;
   }
}
