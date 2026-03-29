package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbr extends bbn {
   private static final in LOG;
   private static final in LEAF;
   private final boolean useExtraRandomHeight;

   public bbr(boolean notify, boolean useExtraRandomHeightIn) {
      super(notify);
      this.useExtraRandomHeight = useExtraRandomHeightIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = rand.nextInt(3) + 5;
      if (this.useExtraRandomHeight) {
         i += rand.nextInt(7);
      }

      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
         int i2;
         int l2;
         int i3;
         for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            i2 = 1;
            if (j == position.getY()) {
               i2 = 0;
            }

            if (j >= position.getY() + 1 + i - 2) {
               i2 = 2;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(l2 = position.getX() - i2; l2 <= position.getX() + i2 && flag; ++l2) {
               for(i3 = position.getZ() - i2; i3 <= position.getZ() + i2 && flag; ++i3) {
                  if (j >= 0 && j < 256) {
                     if (!this.canGrowInto(worldIn.getBlockState(blockpos$mutableblockpos.setPos(l2, j, i3)).getBlock())) {
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

               for(i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
                  int k2 = i2 - (position.getY() + i);
                  l2 = 1 - k2 / 2;

                  for(i3 = position.getX() - l2; i3 <= position.getX() + l2; ++i3) {
                     int j1 = i3 - position.getX();

                     for(int k1 = position.getZ() - l2; k1 <= position.getZ() + l2; ++k1) {
                        int l1 = k1 - position.getZ();
                        if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
                           BlockPos blockpos = new BlockPos(i3, i2, k1);
                           hM material = worldIn.getBlockState(blockpos).getMaterial();
                           if (material == hM.AIR || material == hM.LEAVES) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                           }
                        }
                     }
                  }
               }

               for(i2 = 0; i2 < i; ++i2) {
                  hM material1 = worldIn.getBlockState(position.up(i2)).getMaterial();
                  if (material1 == hM.AIR || material1 == hM.LEAVES) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(i2), LOG);
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
      LOG = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.BIRCH);
      LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.BIRCH).withProperty(eW.CHECK_DECAY, false);
   }
}
