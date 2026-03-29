package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbG extends bbE {
   public bbG() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Nk.NETHERRACK) {
            worldIn.setBlockState(blockpos, Nk.FIRE.getDefaultState(), 2);
         }
      }

      return true;
   }
}
