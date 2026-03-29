package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bcf extends bbn {
   private static final in TRUNK;
   private static final in LEAF;

   public bcf() {
      super(false);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = rand.nextInt(5) + 7;
      int j = i - rand.nextInt(2) - 3;
      int k = i - j;
      int l = 1 + rand.nextInt(k + 1);
      if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
         boolean flag = true;

         int j3;
         int k3;
         int k2;
         for(int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
            int j1 = true;
            if (i1 - position.getY() < j) {
               k2 = 0;
            } else {
               k2 = l;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(j3 = position.getX() - k2; j3 <= position.getX() + k2 && flag; ++j3) {
               for(k3 = position.getZ() - k2; k3 <= position.getZ() + k2 && flag; ++k3) {
                  if (i1 >= 0 && i1 < 256) {
                     if (!this.canGrowInto(worldIn.getBlockState(blockpos$mutableblockpos.setPos(j3, i1, k3)).getBlock())) {
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
            if ((block == Nk.GRASS || block == Nk.DIRT) && position.getY() < 256 - i - 1) {
               this.setDirtAt(worldIn, position.down());
               k2 = 0;

               int l2;
               for(l2 = position.getY() + i; l2 >= position.getY() + j; --l2) {
                  for(j3 = position.getX() - k2; j3 <= position.getX() + k2; ++j3) {
                     k3 = j3 - position.getX();

                     for(int i2 = position.getZ() - k2; i2 <= position.getZ() + k2; ++i2) {
                        int j2 = i2 - position.getZ();
                        if (Math.abs(k3) != k2 || Math.abs(j2) != k2 || k2 <= 0) {
                           BlockPos blockpos = new BlockPos(j3, l2, i2);
                           if (!worldIn.getBlockState(blockpos).isFullBlock()) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                           }
                        }
                     }
                  }

                  if (k2 >= 1 && l2 == position.getY() + j + 1) {
                     --k2;
                  } else if (k2 < l) {
                     ++k2;
                  }
               }

               for(l2 = 0; l2 < i - 1; ++l2) {
                  hM material = worldIn.getBlockState(position.up(l2)).getMaterial();
                  if (material == hM.AIR || material == hM.LEAVES) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(l2), TRUNK);
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
