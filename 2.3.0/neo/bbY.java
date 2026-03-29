package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbY extends bbE {
   public bbY() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 20; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
         if (worldIn.isAirBlock(blockpos)) {
            BlockPos blockpos1 = blockpos.down();
            if (worldIn.getBlockState(blockpos1.west()).getMaterial() == hM.WATER || worldIn.getBlockState(blockpos1.east()).getMaterial() == hM.WATER || worldIn.getBlockState(blockpos1.north()).getMaterial() == hM.WATER || worldIn.getBlockState(blockpos1.south()).getMaterial() == hM.WATER) {
               int j = 2 + rand.nextInt(rand.nextInt(3) + 1);

               for(int k = 0; k < j; ++k) {
                  if (Nk.REEDS.canBlockStay(worldIn, blockpos)) {
                     worldIn.setBlockState(blockpos.up(k), Nk.REEDS.getDefaultState(), 2);
                  }
               }
            }
         }
      }

      return true;
   }
}
