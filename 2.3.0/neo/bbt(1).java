package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbt extends bbE {
   private final cI block;

   public bbt(cI blockIn) {
      this.block = blockIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && this.block.canBlockStay(worldIn, blockpos, this.block.getDefaultState())) {
            worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
         }
      }

      return true;
   }
}
