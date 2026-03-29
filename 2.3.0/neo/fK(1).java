package neo;

import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class fK extends co {
   protected static final AxisAlignedBB FLAT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
   protected static final AxisAlignedBB ASCENDING_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
   protected final boolean isPowered;

   public static boolean isRailBlock(bij worldIn, BlockPos pos) {
      return isRailBlock(worldIn.getBlockState(pos));
   }

   public static boolean isRailBlock(in state) {
      co block = state.getBlock();
      return block == Nk.RAIL || block == Nk.GOLDEN_RAIL || block == Nk.DETECTOR_RAIL || block == Nk.ACTIVATOR_RAIL;
   }

   protected fK(boolean isPowered) {
      super(hM.CIRCUITS);
      this.isPowered = isPowered;
      this.setCreativeTab(EN.TRANSPORTATION);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      fI blockrailbase$enumraildirection = state.getBlock() == this ? (fI)state.getValue(this.getShapeProperty()) : null;
      return blockrailbase$enumraildirection != null && blockrailbase$enumraildirection.isAscending() ? ASCENDING_AABB : FLAT_AABB;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid();
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         state = this.updateDir(worldIn, pos, state, true);
         if (this.isPowered) {
            state.neighborChanged(worldIn, pos, this, pos);
         }
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         fI blockrailbase$enumraildirection = (fI)state.getValue(this.getShapeProperty());
         boolean flag = false;
         if (!worldIn.getBlockState(pos.down()).isTopSolid()) {
            flag = true;
         }

         if (blockrailbase$enumraildirection == fI.ASCENDING_EAST && !worldIn.getBlockState(pos.east()).isTopSolid()) {
            flag = true;
         } else if (blockrailbase$enumraildirection == fI.ASCENDING_WEST && !worldIn.getBlockState(pos.west()).isTopSolid()) {
            flag = true;
         } else if (blockrailbase$enumraildirection == fI.ASCENDING_NORTH && !worldIn.getBlockState(pos.north()).isTopSolid()) {
            flag = true;
         } else if (blockrailbase$enumraildirection == fI.ASCENDING_SOUTH && !worldIn.getBlockState(pos.south()).isTopSolid()) {
            flag = true;
         }

         if (flag && !worldIn.isAirBlock(pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         } else {
            this.updateState(state, worldIn, pos, blockIn);
         }
      }

   }

   protected void updateState(in state, bij worldIn, BlockPos pos, co blockIn) {
   }

   protected in updateDir(bij worldIn, BlockPos pos, in state, boolean initialPlacement) {
      return worldIn.isRemote ? state : (new fJ(this, worldIn, pos, state)).place(worldIn.isBlockPowered(pos), initialPlacement).getBlockState();
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.NORMAL;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      if (((fI)state.getValue(this.getShapeProperty())).isAscending()) {
         worldIn.notifyNeighborsOfStateChange(pos.up(), this, false);
      }

      if (this.isPowered) {
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
      }

   }

   public abstract hT<fI> getShapeProperty();
}
