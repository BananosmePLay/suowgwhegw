package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class gW extends eB {
   protected gW(hM materialIn) {
      super(materialIn);
      this.setTickRandomly(false);
      if (materialIn == hM.LAVA) {
         this.setTickRandomly(true);
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.checkForMixing(worldIn, pos, state)) {
         this.updateLiquid(worldIn, pos, state);
      }

   }

   private void updateLiquid(bij worldIn, BlockPos pos, in state) {
      dx blockdynamicliquid = getFlowingBlock(this.material);
      worldIn.setBlockState(pos, blockdynamicliquid.getDefaultState().withProperty(LEVEL, state.getValue(LEVEL)), 2);
      worldIn.scheduleUpdate(pos, blockdynamicliquid, this.tickRate(worldIn));
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (this.material == hM.LAVA && worldIn.getGameRules().getBoolean("doFireTick")) {
         int i = rand.nextInt(3);
         if (i > 0) {
            BlockPos blockpos = pos;

            for(int j = 0; j < i; ++j) {
               blockpos = blockpos.add(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);
               if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
                  return;
               }

               co block = worldIn.getBlockState(blockpos).getBlock();
               if (block.material == hM.AIR) {
                  if (this.isSurroundingBlockFlammable(worldIn, blockpos)) {
                     worldIn.setBlockState(blockpos, Nk.FIRE.getDefaultState());
                     return;
                  }
               } else if (block.material.blocksMovement()) {
                  return;
               }
            }
         } else {
            for(int k = 0; k < 3; ++k) {
               BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
               if (blockpos1.getY() >= 0 && blockpos1.getY() < 256 && !worldIn.isBlockLoaded(blockpos1)) {
                  return;
               }

               if (worldIn.isAirBlock(blockpos1.up()) && this.getCanBlockBurn(worldIn, blockpos1)) {
                  worldIn.setBlockState(blockpos1.up(), Nk.FIRE.getDefaultState());
               }
            }
         }
      }

   }

   protected boolean isSurroundingBlockFlammable(bij worldIn, BlockPos pos) {
      EnumFacing[] var3 = EnumFacing.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFacing enumfacing = var3[var5];
         if (this.getCanBlockBurn(worldIn, pos.offset(enumfacing))) {
            return true;
         }
      }

      return false;
   }

   private boolean getCanBlockBurn(bij worldIn, BlockPos pos) {
      return pos.getY() >= 0 && pos.getY() < 256 && !worldIn.isBlockLoaded(pos) ? false : worldIn.getBlockState(pos).getMaterial().getCanBurn();
   }
}
