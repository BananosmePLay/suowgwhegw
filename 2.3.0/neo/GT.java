package neo;

import net.minecraft.util.math.BlockPos;

public class GT extends GL {
   private final LN ocelot;

   public GT(LN ocelotIn, double p_i45315_2_) {
      super(ocelotIn, p_i45315_2_, 8);
      this.ocelot = ocelotIn;
   }

   public boolean shouldExecute() {
      return this.ocelot.isTamed() && !this.ocelot.isSitting() && super.shouldExecute();
   }

   public void startExecuting() {
      super.startExecuting();
      this.ocelot.getAISit().setSitting(false);
   }

   public void resetTask() {
      super.resetTask();
      this.ocelot.setSitting(false);
   }

   public void updateTask() {
      super.updateTask();
      this.ocelot.getAISit().setSitting(false);
      if (!this.getIsAboveDestination()) {
         this.ocelot.setSitting(false);
      } else if (!this.ocelot.isSitting()) {
         this.ocelot.setSitting(true);
      }

   }

   protected boolean shouldMoveTo(bij worldIn, BlockPos pos) {
      if (!worldIn.isAirBlock(pos.up())) {
         return false;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (block == Nk.CHEST) {
            Yg tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof Yn && ((Yn)tileentity).numPlayersUsing < 1) {
               return true;
            }
         } else {
            if (block == Nk.LIT_FURNACE) {
               return true;
            }

            if (block == Nk.BED && iblockstate.getValue(cC.PART) != cB.HEAD) {
               return true;
            }
         }

         return false;
      }
   }
}
