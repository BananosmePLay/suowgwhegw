package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZW extends Zr {
   public ZW() {
   }

   public void decorate(bij worldIn, Random random, Zi biome, BlockPos pos) {
      BlockPos blockpos = worldIn.getSpawnPoint();
      int i = true;
      double d0 = blockpos.distanceSq(pos.add(8, blockpos.getY(), 8));
      if (d0 <= 1024.0) {
         BlockPos blockpos1 = new BlockPos(blockpos.getX() - 16, blockpos.getY() - 1, blockpos.getZ() - 16);
         BlockPos blockpos2 = new BlockPos(blockpos.getX() + 16, blockpos.getY() - 1, blockpos.getZ() + 16);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(blockpos1);

         for(int j = pos.getZ(); j < pos.getZ() + 16; ++j) {
            for(int k = pos.getX(); k < pos.getX() + 16; ++k) {
               if (j >= blockpos1.getZ() && j <= blockpos2.getZ() && k >= blockpos1.getX() && k <= blockpos2.getX()) {
                  blockpos$mutableblockpos.setPos(k, blockpos$mutableblockpos.getY(), j);
                  if (blockpos.getX() == k && blockpos.getZ() == j) {
                     worldIn.setBlockState(blockpos$mutableblockpos, Nk.COBBLESTONE.getDefaultState(), 2);
                  } else {
                     worldIn.setBlockState(blockpos$mutableblockpos, Nk.STONE.getDefaultState(), 2);
                  }
               }
            }
         }
      }

   }
}
