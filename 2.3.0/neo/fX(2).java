package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class fX extends en {
   protected static final AxisAlignedBB REDSTONE_DIODE_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
   protected final boolean isRepeaterPowered;

   protected fX(boolean powered) {
      super(hM.CIRCUITS);
      this.isRepeaterPowered = powered;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return REDSTONE_DIODE_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
   }

   public boolean canBlockStay(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid();
   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!this.isLocked(worldIn, pos, state)) {
         boolean flag = this.shouldBePowered(worldIn, pos, state);
         if (this.isRepeaterPowered && !flag) {
            worldIn.setBlockState(pos, this.getUnpoweredState(state), 2);
         } else if (!this.isRepeaterPowered) {
            worldIn.setBlockState(pos, this.getPoweredState(state), 2);
            if (!flag) {
               worldIn.updateBlockTick(pos, this.getPoweredState(state).getBlock(), this.getTickDelay(state), -1);
            }
         }
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side.getAxis() != EnumFacing.Axis.Y;
   }

   protected boolean isPowered(in state) {
      return this.isRepeaterPowered;
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return blockState.getWeakPower(blockAccess, pos, side);
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (!this.isPowered(blockState)) {
         return 0;
      } else {
         return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (this.canBlockStay(worldIn, pos)) {
         this.updateState(worldIn, pos, state);
      } else {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         EnumFacing[] var6 = EnumFacing.values();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            EnumFacing enumfacing = var6[var8];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }
      }

   }

   protected void updateState(bij worldIn, BlockPos pos, in state) {
      if (!this.isLocked(worldIn, pos, state)) {
         boolean flag = this.shouldBePowered(worldIn, pos, state);
         if (this.isRepeaterPowered != flag && !worldIn.isBlockTickPending(pos, this)) {
            int i = -1;
            if (this.isFacingTowardsRepeater(worldIn, pos, state)) {
               i = -3;
            } else if (this.isRepeaterPowered) {
               i = -2;
            }

            worldIn.updateBlockTick(pos, this, this.getDelay(state), i);
         }
      }

   }

   public boolean isLocked(bfZ worldIn, BlockPos pos, in state) {
      return false;
   }

   protected boolean shouldBePowered(bij worldIn, BlockPos pos, in state) {
      return this.calculateInputStrength(worldIn, pos, state) > 0;
   }

   protected int calculateInputStrength(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      BlockPos blockpos = pos.offset(enumfacing);
      int i = worldIn.getRedstonePower(blockpos, enumfacing);
      if (i >= 15) {
         return i;
      } else {
         in iblockstate = worldIn.getBlockState(blockpos);
         return Math.max(i, iblockstate.getBlock() == Nk.REDSTONE_WIRE ? (Integer)iblockstate.getValue(gf.POWER) : 0);
      }
   }

   protected int getPowerOnSides(bfZ worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      EnumFacing enumfacing1 = enumfacing.rotateY();
      EnumFacing enumfacing2 = enumfacing.rotateYCCW();
      return Math.max(this.getPowerOnSide(worldIn, pos.offset(enumfacing1), enumfacing1), this.getPowerOnSide(worldIn, pos.offset(enumfacing2), enumfacing2));
   }

   protected int getPowerOnSide(bfZ worldIn, BlockPos pos, EnumFacing side) {
      in iblockstate = worldIn.getBlockState(pos);
      co block = iblockstate.getBlock();
      if (this.isAlternateInput(iblockstate)) {
         if (block == Nk.REDSTONE_BLOCK) {
            return 15;
         } else {
            return block == Nk.REDSTONE_WIRE ? (Integer)iblockstate.getValue(gf.POWER) : worldIn.getStrongPower(pos, side);
         }
      } else {
         return 0;
      }
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      if (this.shouldBePowered(worldIn, pos, state)) {
         worldIn.scheduleUpdate(pos, this, 1);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.notifyNeighbors(worldIn, pos, state);
   }

   protected void notifyNeighbors(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      BlockPos blockpos = pos.offset(enumfacing.getOpposite());
      worldIn.neighborChanged(blockpos, this, pos);
      worldIn.notifyNeighborsOfStateExcept(blockpos, this, enumfacing);
   }

   public void onPlayerDestroy(bij worldIn, BlockPos pos, in state) {
      if (this.isRepeaterPowered) {
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing enumfacing = var4[var6];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }
      }

      super.onPlayerDestroy(worldIn, pos, state);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   protected boolean isAlternateInput(in state) {
      return state.canProvidePower();
   }

   protected int getActiveSignal(bfZ worldIn, BlockPos pos, in state) {
      return 15;
   }

   public static boolean isDiode(in state) {
      return Nk.UNPOWERED_REPEATER.isSameDiode(state) || Nk.UNPOWERED_COMPARATOR.isSameDiode(state);
   }

   public boolean isSameDiode(in state) {
      co block = state.getBlock();
      return block == this.getPoweredState(this.getDefaultState()).getBlock() || block == this.getUnpoweredState(this.getDefaultState()).getBlock();
   }

   public boolean isFacingTowardsRepeater(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = ((EnumFacing)state.getValue(FACING)).getOpposite();
      BlockPos blockpos = pos.offset(enumfacing);
      if (isDiode(worldIn.getBlockState(blockpos))) {
         return worldIn.getBlockState(blockpos).getValue(FACING) != enumfacing;
      } else {
         return false;
      }
   }

   protected int getTickDelay(in state) {
      return this.getDelay(state);
   }

   protected abstract int getDelay(in var1);

   protected abstract in getPoweredState(in var1);

   protected abstract in getUnpoweredState(in var1);

   public boolean isAssociatedBlock(co other) {
      return this.isSameDiode(other.getDefaultState());
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
