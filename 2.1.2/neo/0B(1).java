package neo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class 0B extends 0n {
   public final BlockPos pos;
   public AxisAlignedBB collision;
   public final BlockLiquid blockLiquid;

   public AxisAlignedBB getCollision() {
      return 7Va6JO8qCS(this);
   }

   public BlockPos getPos() {
      return n4dUd7oRrd(this);
   }

   private static void gSlBKDGpdQ(0B var0, AxisAlignedBB var1) {
      var0.collision = var1;
   }

   public BlockLiquid getBlock() {
      return Tn2S9buoQD(this);
   }

   private static BlockLiquid Tn2S9buoQD(0B var0) {
      return var0.blockLiquid;
   }

   public _B/* $FF was: 0B*/(BlockLiquid blockLiquid, BlockPos pos) {
      this.blockLiquid = blockLiquid;
      this.pos = pos;
      this.collision = Block.NULL_AABB;
   }

   private static AxisAlignedBB _Va6JO8qCS/* $FF was: 7Va6JO8qCS*/(0B var0) {
      return var0.collision;
   }

   private static BlockPos n4dUd7oRrd(0B var0) {
      return var0.pos;
   }

   public void setCollision(AxisAlignedBB collision) {
      gSlBKDGpdQ(this, collision);
   }
}
