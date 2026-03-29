package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bbO extends bbE {
   public bbO() {
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      while(worldIn.isAirBlock(position) && position.getY() > 2) {
         position = position.down();
      }

      if (worldIn.getBlockState(position).getBlock() != Nk.SNOW) {
         return false;
      } else {
         position = position.up(rand.nextInt(4));
         int i = rand.nextInt(4) + 7;
         int j = i / 4 + rand.nextInt(2);
         if (j > 1 && rand.nextInt(60) == 0) {
            position = position.up(10 + rand.nextInt(30));
         }

         int k;
         int l;
         for(k = 0; k < i; ++k) {
            float f = (1.0F - (float)k / (float)i) * (float)j;
            l = MathHelper.ceil(f);

            for(int i1 = -l; i1 <= l; ++i1) {
               float f1 = (float)MathHelper.abs(i1) - 0.25F;

               for(int j1 = -l; j1 <= l; ++j1) {
                  float f2 = (float)MathHelper.abs(j1) - 0.25F;
                  if ((i1 == 0 && j1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (i1 != -l && i1 != l && j1 != -l && j1 != l || rand.nextFloat() <= 0.75F)) {
                     in iblockstate = worldIn.getBlockState(position.add(i1, k, j1));
                     co block = iblockstate.getBlock();
                     if (iblockstate.getMaterial() == hM.AIR || block == Nk.DIRT || block == Nk.SNOW || block == Nk.ICE) {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(i1, k, j1), Nk.PACKED_ICE.getDefaultState());
                     }

                     if (k != 0 && l > 1) {
                        iblockstate = worldIn.getBlockState(position.add(i1, -k, j1));
                        block = iblockstate.getBlock();
                        if (iblockstate.getMaterial() == hM.AIR || block == Nk.DIRT || block == Nk.SNOW || block == Nk.ICE) {
                           this.setBlockAndNotifyAdequately(worldIn, position.add(i1, -k, j1), Nk.PACKED_ICE.getDefaultState());
                        }
                     }
                  }
               }
            }
         }

         k = j - 1;
         if (k < 0) {
            k = 0;
         } else if (k > 1) {
            k = 1;
         }

         for(int l1 = -k; l1 <= k; ++l1) {
            for(l = -k; l <= k; ++l) {
               BlockPos blockpos = position.add(l1, -1, l);
               int j2 = 50;
               if (Math.abs(l1) == 1 && Math.abs(l) == 1) {
                  j2 = rand.nextInt(5);
               }

               while(blockpos.getY() > 50) {
                  in iblockstate1 = worldIn.getBlockState(blockpos);
                  co block1 = iblockstate1.getBlock();
                  if (iblockstate1.getMaterial() != hM.AIR && block1 != Nk.DIRT && block1 != Nk.SNOW && block1 != Nk.ICE && block1 != Nk.PACKED_ICE) {
                     break;
                  }

                  this.setBlockAndNotifyAdequately(worldIn, blockpos, Nk.PACKED_ICE.getDefaultState());
                  blockpos = blockpos.down();
                  --j2;
                  if (j2 <= 0) {
                     blockpos = blockpos.down(rand.nextInt(5) + 1);
                     j2 = rand.nextInt(5);
                  }
               }
            }
         }

         return true;
      }
   }
}
