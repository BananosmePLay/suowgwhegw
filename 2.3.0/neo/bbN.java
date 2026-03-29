package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbN extends bbE {
   private final co block;
   private final int basePathWidth;

   public bbN(int basePathWidthIn) {
      this.block = Nk.PACKED_ICE;
      this.basePathWidth = basePathWidthIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      while(worldIn.isAirBlock(position) && position.getY() > 2) {
         position = position.down();
      }

      if (worldIn.getBlockState(position).getBlock() != Nk.SNOW) {
         return false;
      } else {
         int i = rand.nextInt(this.basePathWidth - 2) + 2;
         int j = true;

         for(int k = position.getX() - i; k <= position.getX() + i; ++k) {
            for(int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
               int i1 = k - position.getX();
               int j1 = l - position.getZ();
               if (i1 * i1 + j1 * j1 <= i * i) {
                  for(int k1 = position.getY() - 1; k1 <= position.getY() + 1; ++k1) {
                     BlockPos blockpos = new BlockPos(k, k1, l);
                     co block = worldIn.getBlockState(blockpos).getBlock();
                     if (block == Nk.DIRT || block == Nk.SNOW || block == Nk.ICE) {
                        worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }
}
