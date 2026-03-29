package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class cx extends co {
   protected static final AxisAlignedBB PRESSED_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.03125, 0.9375);
   protected static final AxisAlignedBB UNPRESSED_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.0625, 0.9375);
   protected static final AxisAlignedBB PRESSURE_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.25, 0.875);

   protected cx(hM materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected cx(hM materialIn, hK mapColorIn) {
      super(materialIn, mapColorIn);
      this.setCreativeTab(EN.REDSTONE);
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      boolean flag = this.getRedstoneStrength(state) > 0;
      return flag ? PRESSED_AABB : UNPRESSED_AABB;
   }

   public int tickRate(bij worldIn) {
      return 20;
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
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   public boolean canSpawnInBlock() {
      return true;
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return this.canBePlacedOn(worldIn, pos.down());
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.canBePlacedOn(worldIn, pos.down())) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean canBePlacedOn(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof dL;
   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         int i = this.getRedstoneStrength(state);
         if (i > 0) {
            this.updateState(worldIn, pos, state, i);
         }
      }

   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote) {
         int i = this.getRedstoneStrength(state);
         if (i == 0) {
            this.updateState(worldIn, pos, state, i);
         }
      }

   }

   protected void updateState(bij worldIn, BlockPos pos, in state, int oldRedstoneStrength) {
      int i = this.computeRedstoneStrength(worldIn, pos);
      boolean flag = oldRedstoneStrength > 0;
      boolean flag1 = i > 0;
      if (oldRedstoneStrength != i) {
         state = this.setRedstoneStrength(state, i);
         worldIn.setBlockState(pos, state, 2);
         this.updateNeighbors(worldIn, pos);
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (!flag1 && flag) {
         this.playClickOffSound(worldIn, pos);
      } else if (flag1 && !flag) {
         this.playClickOnSound(worldIn, pos);
      }

      if (flag1) {
         worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
      }

   }

   protected abstract void playClickOnSound(bij var1, BlockPos var2);

   protected abstract void playClickOffSound(bij var1, BlockPos var2);

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (this.getRedstoneStrength(state) > 0) {
         this.updateNeighbors(worldIn, pos);
      }

      super.breakBlock(worldIn, pos, state);
   }

   protected void updateNeighbors(bij worldIn, BlockPos pos) {
      worldIn.notifyNeighborsOfStateChange(pos, this, false);
      worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return this.getRedstoneStrength(blockState);
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP ? this.getRedstoneStrength(blockState) : 0;
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.DESTROY;
   }

   protected abstract int computeRedstoneStrength(bij var1, BlockPos var2);

   protected abstract int getRedstoneStrength(in var1);

   protected abstract in setRedstoneStrength(in var1, int var2);

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
