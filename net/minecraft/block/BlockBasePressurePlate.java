package net.minecraft.block;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBasePressurePlate extends Block {
   protected static final AxisAlignedBB PRESSED_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.03125, 0.9375);
   protected static final AxisAlignedBB UNPRESSED_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.0625, 0.9375);
   protected static final AxisAlignedBB PRESSURE_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.25, 0.875);

   protected BlockBasePressurePlate(Material materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected BlockBasePressurePlate(Material materialIn, MapColor mapColorIn) {
      super(materialIn, mapColorIn);
      this.setCreativeTab(CreativeTabs.REDSTONE);
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      boolean flag = this.getRedstoneStrength(state) > 0;
      return flag ? PRESSED_AABB : UNPRESSED_AABB;
   }

   public int tickRate(World worldIn) {
      return 20;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return true;
   }

   public boolean canSpawnInBlock() {
      return true;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return this.canBePlacedOn(worldIn, pos.down());
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (!this.canBePlacedOn(worldIn, pos.down())) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean canBePlacedOn(World worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
   }

   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
   }

   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.isRemote) {
         int i = this.getRedstoneStrength(state);
         if (i > 0) {
            this.updateState(worldIn, pos, state, i);
         }
      }

   }

   public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      if (!worldIn.isRemote) {
         int i = this.getRedstoneStrength(state);
         if (i == 0) {
            this.updateState(worldIn, pos, state, i);
         }
      }

   }

   protected void updateState(World worldIn, BlockPos pos, IBlockState state, int oldRedstoneStrength) {
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

   protected abstract void playClickOnSound(World var1, BlockPos var2);

   protected abstract void playClickOffSound(World var1, BlockPos var2);

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
      if (this.getRedstoneStrength(state) > 0) {
         this.updateNeighbors(worldIn, pos);
      }

      super.breakBlock(worldIn, pos, state);
   }

   protected void updateNeighbors(World worldIn, BlockPos pos) {
      worldIn.notifyNeighborsOfStateChange(pos, this, false);
      worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
   }

   /** @deprecated */
   public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return this.getRedstoneStrength(blockState);
   }

   /** @deprecated */
   public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP ? this.getRedstoneStrength(blockState) : 0;
   }

   /** @deprecated */
   public boolean canProvidePower(IBlockState state) {
      return true;
   }

   /** @deprecated */
   public EnumPushReaction getPushReaction(IBlockState state) {
      return EnumPushReaction.DESTROY;
   }

   protected abstract int computeRedstoneStrength(World var1, BlockPos var2);

   protected abstract int getRedstoneStrength(IBlockState var1);

   protected abstract IBlockState setRedstoneStrength(IBlockState var1, int var2);

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }
}
