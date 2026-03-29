package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bcg extends bbn {
   private static final in TRUNK;
   private static final in LEAF;

   public bcg(boolean p_i2025_1_) {
      super(p_i2025_1_);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = rand.nextInt(4) + 6;
      int j = 1 + rand.nextInt(2);
      int k = i - j;
      int l = 2 + rand.nextInt(2);
      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
         int i3;
         int l1;
         for(int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
            if (i1 - position.getY() < j) {
               i3 = 0;
            } else {
               i3 = l;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k1 = position.getX() - i3; k1 <= position.getX() + i3 && flag; ++k1) {
               for(l1 = position.getZ() - i3; l1 <= position.getZ() + i3 && flag; ++l1) {
                  if (i1 >= 0 && i1 < 256) {
                     hM material = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1)).getMaterial();
                     if (material != hM.AIR && material != hM.LEAVES) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            co block = worldIn.getBlockState(position.down()).getBlock();
            if ((block == Nk.GRASS || block == Nk.DIRT || block == Nk.FARMLAND) && position.getY() < 256 - i - 1) {
               this.setDirtAt(worldIn, position.down());
               i3 = rand.nextInt(2);
               int j3 = 1;
               int k3 = 0;

               int j4;
               for(l1 = 0; l1 <= k; ++l1) {
                  j4 = position.getY() + i - l1;

                  for(int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                     int j2 = i2 - position.getX();

                     for(int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                        int l2 = k2 - position.getZ();
                        if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                           BlockPos blockpos = new BlockPos(i2, j4, k2);
                           if (!worldIn.getBlockState(blockpos).isFullBlock()) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                           }
                        }
                     }
                  }

                  if (i3 >= j3) {
                     i3 = k3;
                     k3 = 1;
                     ++j3;
                     if (j3 > l) {
                        j3 = l;
                     }
                  } else {
                     ++i3;
                  }
               }

               l1 = rand.nextInt(3);

               for(j4 = 0; j4 < i - l1; ++j4) {
                  hM material1 = worldIn.getBlockState(position.up(j4)).getMaterial();
                  if (material1 == hM.AIR || material1 == hM.LEAVES) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(j4), TRUNK);
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   static {
      TRUNK = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.SPRUCE);
      LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.SPRUCE).withProperty(ew.CHECK_DECAY, false);
   }
}
