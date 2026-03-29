package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbo extends bbE {
   private final co mushroomType;

   public bbo(co p_i46449_1_) {
      super(true);
      this.mushroomType = p_i46449_1_;
   }

   public bbo() {
      super(false);
      this.mushroomType = null;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      co block = this.mushroomType;
      if (block == null) {
         block = rand.nextBoolean() ? Nk.BROWN_MUSHROOM_BLOCK : Nk.RED_MUSHROOM_BLOCK;
      }

      int i = rand.nextInt(3) + 4;
      if (rand.nextInt(12) == 0) {
         i *= 2;
      }

      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 < 256) {
         int k2;
         int j3;
         int k3;
         for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            k2 = 3;
            if (j <= position.getY() + 3) {
               k2 = 0;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(j3 = position.getX() - k2; j3 <= position.getX() + k2 && flag; ++j3) {
               for(k3 = position.getZ() - k2; k3 <= position.getZ() + k2 && flag; ++k3) {
                  if (j >= 0 && j < 256) {
                     hM material = worldIn.getBlockState(blockpos$mutableblockpos.setPos(j3, j, k3)).getMaterial();
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
            co block1 = worldIn.getBlockState(position.down()).getBlock();
            if (block1 != Nk.DIRT && block1 != Nk.GRASS && block1 != Nk.MYCELIUM) {
               return false;
            } else {
               k2 = position.getY() + i;
               if (block == Nk.RED_MUSHROOM_BLOCK) {
                  k2 = position.getY() + i - 3;
               }

               int l2;
               for(l2 = k2; l2 <= position.getY() + i; ++l2) {
                  j3 = 1;
                  if (l2 < position.getY() + i) {
                     ++j3;
                  }

                  if (block == Nk.BROWN_MUSHROOM_BLOCK) {
                     j3 = 3;
                  }

                  k3 = position.getX() - j3;
                  int l3 = position.getX() + j3;
                  int j1 = position.getZ() - j3;
                  int k1 = position.getZ() + j3;

                  for(int l1 = k3; l1 <= l3; ++l1) {
                     for(int i2 = j1; i2 <= k1; ++i2) {
                        int j2 = 5;
                        if (l1 == k3) {
                           --j2;
                        } else if (l1 == l3) {
                           ++j2;
                        }

                        if (i2 == j1) {
                           j2 -= 3;
                        } else if (i2 == k1) {
                           j2 += 3;
                        }

                        ep blockhugemushroom$enumtype = ep.byMetadata(j2);
                        if (block == Nk.BROWN_MUSHROOM_BLOCK || l2 < position.getY() + i) {
                           if ((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1)) {
                              continue;
                           }

                           if (l1 == position.getX() - (j3 - 1) && i2 == j1) {
                              blockhugemushroom$enumtype = ep.NORTH_WEST;
                           }

                           if (l1 == k3 && i2 == position.getZ() - (j3 - 1)) {
                              blockhugemushroom$enumtype = ep.NORTH_WEST;
                           }

                           if (l1 == position.getX() + (j3 - 1) && i2 == j1) {
                              blockhugemushroom$enumtype = ep.NORTH_EAST;
                           }

                           if (l1 == l3 && i2 == position.getZ() - (j3 - 1)) {
                              blockhugemushroom$enumtype = ep.NORTH_EAST;
                           }

                           if (l1 == position.getX() - (j3 - 1) && i2 == k1) {
                              blockhugemushroom$enumtype = ep.SOUTH_WEST;
                           }

                           if (l1 == k3 && i2 == position.getZ() + (j3 - 1)) {
                              blockhugemushroom$enumtype = ep.SOUTH_WEST;
                           }

                           if (l1 == position.getX() + (j3 - 1) && i2 == k1) {
                              blockhugemushroom$enumtype = ep.SOUTH_EAST;
                           }

                           if (l1 == l3 && i2 == position.getZ() + (j3 - 1)) {
                              blockhugemushroom$enumtype = ep.SOUTH_EAST;
                           }
                        }

                        if (blockhugemushroom$enumtype == ep.CENTER && l2 < position.getY() + i) {
                           blockhugemushroom$enumtype = ep.ALL_INSIDE;
                        }

                        if (position.getY() >= position.getY() + i - 1 || blockhugemushroom$enumtype != ep.ALL_INSIDE) {
                           BlockPos blockpos = new BlockPos(l1, l2, i2);
                           if (!worldIn.getBlockState(blockpos).isFullBlock()) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos, block.getDefaultState().withProperty(eq.VARIANT, blockhugemushroom$enumtype));
                           }
                        }
                     }
                  }
               }

               for(l2 = 0; l2 < i; ++l2) {
                  in iblockstate = worldIn.getBlockState(position.up(l2));
                  if (!iblockstate.isFullBlock()) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(l2), block.getDefaultState().withProperty(eq.VARIANT, ep.STEM));
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
