package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bce extends bbn {
   private static final in TRUNK;
   private static final in LEAF;

   public bce() {
      super(false);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i;
      for(i = rand.nextInt(4) + 5; worldIn.getBlockState(position.down()).getMaterial() == hM.WATER; position = position.down()) {
      }

      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
         int i2;
         int i3;
         int j3;
         for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            i2 = 1;
            if (j == position.getY()) {
               i2 = 0;
            }

            if (j >= position.getY() + 1 + i - 2) {
               i2 = 3;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(i3 = position.getX() - i2; i3 <= position.getX() + i2 && flag; ++i3) {
               for(j3 = position.getZ() - i2; j3 <= position.getZ() + i2 && flag; ++j3) {
                  if (j >= 0 && j < 256) {
                     in iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(i3, j, j3));
                     co block = iblockstate.getBlock();
                     if (iblockstate.getMaterial() != hM.AIR && iblockstate.getMaterial() != hM.LEAVES) {
                        if (block != Nk.WATER && block != Nk.FLOWING_WATER) {
                           flag = false;
                        } else if (j > position.getY()) {
                           flag = false;
                        }
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
            if ((block1 == Nk.GRASS || block1 == Nk.DIRT) && position.getY() < 256 - i - 1) {
               this.setDirtAt(worldIn, position.down());

               BlockPos blockpos4;
               int j2;
               int l3;
               int j4;
               for(i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
                  j2 = i2 - (position.getY() + i);
                  i3 = 2 - j2 / 2;

                  for(j3 = position.getX() - i3; j3 <= position.getX() + i3; ++j3) {
                     l3 = j3 - position.getX();

                     for(j4 = position.getZ() - i3; j4 <= position.getZ() + i3; ++j4) {
                        int j1 = j4 - position.getZ();
                        if (Math.abs(l3) != i3 || Math.abs(j1) != i3 || rand.nextInt(2) != 0 && j2 != 0) {
                           blockpos4 = new BlockPos(j3, i2, j4);
                           if (!worldIn.getBlockState(blockpos4).isFullBlock()) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos4, LEAF);
                           }
                        }
                     }
                  }
               }

               for(i2 = 0; i2 < i; ++i2) {
                  in iblockstate1 = worldIn.getBlockState(position.up(i2));
                  co block2 = iblockstate1.getBlock();
                  if (iblockstate1.getMaterial() == hM.AIR || iblockstate1.getMaterial() == hM.LEAVES || block2 == Nk.FLOWING_WATER || block2 == Nk.WATER) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(i2), TRUNK);
                  }
               }

               for(i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
                  j2 = i2 - (position.getY() + i);
                  i3 = 2 - j2 / 2;
                  BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                  for(l3 = position.getX() - i3; l3 <= position.getX() + i3; ++l3) {
                     for(j4 = position.getZ() - i3; j4 <= position.getZ() + i3; ++j4) {
                        blockpos$mutableblockpos1.setPos(l3, i2, j4);
                        if (worldIn.getBlockState(blockpos$mutableblockpos1).getMaterial() == hM.LEAVES) {
                           BlockPos blockpos3 = blockpos$mutableblockpos1.west();
                           blockpos4 = blockpos$mutableblockpos1.east();
                           BlockPos blockpos1 = blockpos$mutableblockpos1.north();
                           BlockPos blockpos2 = blockpos$mutableblockpos1.south();
                           if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos3).getMaterial() == hM.AIR) {
                              this.addVine(worldIn, blockpos3, hx.EAST);
                           }

                           if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos4).getMaterial() == hM.AIR) {
                              this.addVine(worldIn, blockpos4, hx.WEST);
                           }

                           if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos1).getMaterial() == hM.AIR) {
                              this.addVine(worldIn, blockpos1, hx.SOUTH);
                           }

                           if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos2).getMaterial() == hM.AIR) {
                              this.addVine(worldIn, blockpos2, hx.NORTH);
                           }
                        }
                     }
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

   private void addVine(bij worldIn, BlockPos pos, hV prop) {
      in iblockstate = Nk.VINE.getDefaultState().withProperty(prop, true);
      this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);
      int i = 4;

      for(BlockPos blockpos = pos.down(); worldIn.getBlockState(blockpos).getMaterial() == hM.AIR && i > 0; --i) {
         this.setBlockAndNotifyAdequately(worldIn, blockpos, iblockstate);
         blockpos = blockpos.down();
      }

   }

   static {
      TRUNK = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.OAK);
      LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.OAK).withProperty(eW.CHECK_DECAY, false);
   }
}
