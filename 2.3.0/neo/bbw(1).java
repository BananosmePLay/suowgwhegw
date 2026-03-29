package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbw extends bbE {
   private final co block;
   private final int numberOfBlocks;

   public bbw(int p_i2011_1_) {
      this.block = Nk.CLAY;
      this.numberOfBlocks = p_i2011_1_;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (worldIn.getBlockState(position).getMaterial() != hM.WATER) {
         return false;
      } else {
         int i = rand.nextInt(this.numberOfBlocks - 2) + 2;
         int j = true;

         for(int k = position.getX() - i; k <= position.getX() + i; ++k) {
            for(int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
               int i1 = k - position.getX();
               int j1 = l - position.getZ();
               if (i1 * i1 + j1 * j1 <= i * i) {
                  for(int k1 = position.getY() - 1; k1 <= position.getY() + 1; ++k1) {
                     BlockPos blockpos = new BlockPos(k, k1, l);
                     co block = worldIn.getBlockState(blockpos).getBlock();
                     if (block == Nk.DIRT || block == Nk.CLAY) {
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
