package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bbX extends bbE {
   public bbX() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Nk.GRASS && Nk.PUMPKIN.canPlaceBlockAt(worldIn, blockpos)) {
            worldIn.setBlockState(blockpos, Nk.PUMPKIN.getDefaultState().withProperty(fx.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);
         }
      }

      return true;
   }
}
