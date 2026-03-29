package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bch extends bbE {
   private final in tallGrassState;

   public bch(hj p_i45629_1_) {
      this.tallGrassState = Nk.TALLGRASS.getDefaultState().withProperty(hk.TYPE, p_i45629_1_);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(in iblockstate = worldIn.getBlockState(position); (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
         position = position.down();
      }

      for(int i = 0; i < 128; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && Nk.TALLGRASS.canBlockStay(worldIn, blockpos, this.tallGrassState)) {
            worldIn.setBlockState(blockpos, this.tallGrassState, 2);
         }
      }

      return true;
   }
}
