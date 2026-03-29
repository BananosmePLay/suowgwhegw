package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbT extends bbE {
   public bbT() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (Nk.MELON_BLOCK.canPlaceBlockAt(worldIn, blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Nk.GRASS) {
            worldIn.setBlockState(blockpos, Nk.MELON_BLOCK.getDefaultState(), 2);
         }
      }

      return true;
   }
}
