package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockFrostedIce extends BlockIce {
   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

   public BlockFrostedIce() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
   }

   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, MathHelper.clamp(meta, 0, 3));
   }

   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if ((rand.nextInt(3) == 0 || this.countNeighbors(worldIn, pos) < 4) && worldIn.getLightFromNeighbors(pos) > 11 - (Integer)state.getValue(AGE) - state.getLightOpacity()) {
         this.slightlyMelt(worldIn, pos, state, rand, true);
      } else {
         worldIn.scheduleUpdate(pos, this, MathHelper.getInt((Random)rand, 20, 40));
      }

   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      if (blockIn == this) {
         int i = this.countNeighbors(worldIn, pos);
         if (i < 2) {
            this.turnIntoWater(worldIn, pos);
         }
      }

   }

   private int countNeighbors(World worldIn, BlockPos pos) {
      int i = 0;
      EnumFacing[] var4 = EnumFacing.values();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         EnumFacing enumfacing = var4[var6];
         if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() == this) {
            ++i;
            if (i >= 4) {
               return i;
            }
         }
      }

      return i;
   }

   protected void slightlyMelt(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean meltNeighbors) {
      int i = (Integer)state.getValue(AGE);
      if (i < 3) {
         worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
         worldIn.scheduleUpdate(pos, this, MathHelper.getInt((Random)rand, 20, 40));
      } else {
         this.turnIntoWater(worldIn, pos);
         if (meltNeighbors) {
            EnumFacing[] var7 = EnumFacing.values();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               EnumFacing enumfacing = var7[var9];
               BlockPos blockpos = pos.offset(enumfacing);
               IBlockState iblockstate = worldIn.getBlockState(blockpos);
               if (iblockstate.getBlock() == this) {
                  this.slightlyMelt(worldIn, blockpos, iblockstate, rand, false);
               }
            }
         }
      }

   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{AGE});
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return ItemStack.EMPTY;
   }
}
