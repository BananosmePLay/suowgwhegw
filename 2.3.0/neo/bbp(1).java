package neo;

import net.minecraft.util.math.BlockPos;

class bbp extends BlockPos {
   private final int branchBase;

   public bbp(BlockPos pos, int p_i45635_2_) {
      super(pos.getX(), pos.getY(), pos.getZ());
      this.branchBase = p_i45635_2_;
   }

   public int getBranchBase() {
      return this.branchBase;
   }
}
