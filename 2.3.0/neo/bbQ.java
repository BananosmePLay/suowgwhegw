package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbQ extends bbE {
   private final co block;

   public bbQ(co blockIn) {
      this.block = blockIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (worldIn.getBlockState(position.up()).getBlock() != Nk.STONE) {
         return false;
      } else if (worldIn.getBlockState(position.down()).getBlock() != Nk.STONE) {
         return false;
      } else {
         in iblockstate = worldIn.getBlockState(position);
         if (iblockstate.getMaterial() != hM.AIR && iblockstate.getBlock() != Nk.STONE) {
            return false;
         } else {
            int i = 0;
            if (worldIn.getBlockState(position.west()).getBlock() == Nk.STONE) {
               ++i;
            }

            if (worldIn.getBlockState(position.east()).getBlock() == Nk.STONE) {
               ++i;
            }

            if (worldIn.getBlockState(position.north()).getBlock() == Nk.STONE) {
               ++i;
            }

            if (worldIn.getBlockState(position.south()).getBlock() == Nk.STONE) {
               ++i;
            }

            int j = 0;
            if (worldIn.isAirBlock(position.west())) {
               ++j;
            }

            if (worldIn.isAirBlock(position.east())) {
               ++j;
            }

            if (worldIn.isAirBlock(position.north())) {
               ++j;
            }

            if (worldIn.isAirBlock(position.south())) {
               ++j;
            }

            if (i == 3 && j == 1) {
               in iblockstate1 = this.block.getDefaultState();
               worldIn.setBlockState(position, iblockstate1, 2);
               worldIn.immediateBlockTick(position, iblockstate1, rand);
            }

            return true;
         }
      }
   }
}
