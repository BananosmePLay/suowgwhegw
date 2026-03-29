package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbZ extends bbE {
   private final co block;
   private final int radius;

   public bbZ(co p_i45462_1_, int p_i45462_2_) {
      this.block = p_i45462_1_;
      this.radius = p_i45462_2_;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      if (worldIn.getBlockState(position).getMaterial() != hM.WATER) {
         return false;
      } else {
         int i = rand.nextInt(this.radius - 2) + 2;
         int j = true;

         for(int k = position.getX() - i; k <= position.getX() + i; ++k) {
            for(int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
               int i1 = k - position.getX();
               int j1 = l - position.getZ();
               if (i1 * i1 + j1 * j1 <= i * i) {
                  for(int k1 = position.getY() - 2; k1 <= position.getY() + 2; ++k1) {
                     BlockPos blockpos = new BlockPos(k, k1, l);
                     co block = worldIn.getBlockState(blockpos).getBlock();
                     if (block == Nk.DIRT || block == Nk.GRASS) {
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
