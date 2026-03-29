package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bck extends bbE {
   public bck() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 10; ++i) {
         int j = position.getX() + rand.nextInt(8) - rand.nextInt(8);
         int k = position.getY() + rand.nextInt(4) - rand.nextInt(4);
         int l = position.getZ() + rand.nextInt(8) - rand.nextInt(8);
         if (worldIn.isAirBlock(new BlockPos(j, k, l)) && Nk.WATERLILY.canPlaceBlockAt(worldIn, new BlockPos(j, k, l))) {
            worldIn.setBlockState(new BlockPos(j, k, l), Nk.WATERLILY.getDefaultState(), 2);
         }
      }

      return true;
   }
}
