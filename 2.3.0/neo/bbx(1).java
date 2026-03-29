package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbx extends bbE {
   public bbx() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(in iblockstate = worldIn.getBlockState(position); (iblockstate.getMaterial() == hM.AIR || iblockstate.getMaterial() == hM.LEAVES) && position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
         position = position.down();
      }

      for(int i = 0; i < 4; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && Nk.DEADBUSH.canBlockStay(worldIn, blockpos, Nk.DEADBUSH.getDefaultState())) {
            worldIn.setBlockState(blockpos, Nk.DEADBUSH.getDefaultState(), 2);
         }
      }

      return true;
   }
}
