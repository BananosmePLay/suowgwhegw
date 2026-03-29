package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bci extends bbn {
   private static final in DEFAULT_TRUNK;
   private static final in DEFAULT_LEAF;
   private final int minTreeHeight;
   private final boolean vinesGrow;
   private final in metaWood;
   private final in metaLeaves;

   public bci(boolean p_i2027_1_) {
      this(p_i2027_1_, 4, DEFAULT_TRUNK, DEFAULT_LEAF, false);
   }

   public bci(boolean notify, int minTreeHeightIn, in woodMeta, in p_i46446_4_, boolean growVines) {
      super(notify);
      this.minTreeHeight = minTreeHeightIn;
      this.metaWood = woodMeta;
      this.metaLeaves = p_i46446_4_;
      this.vinesGrow = growVines;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      int i = rand.nextInt(3) + this.minTreeHeight;
      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
         int l3;
         int i4;
         for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            int k = 1;
            if (j == position.getY()) {
               k = 0;
            }

            if (j >= position.getY() + 1 + i - 2) {
               k = 2;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(l3 = position.getX() - k; l3 <= position.getX() + k && flag; ++l3) {
               for(i4 = position.getZ() - k; i4 <= position.getZ() + k && flag; ++i4) {
                  if (j >= 0 && j < 256) {
                     if (!this.canGrowInto(worldIn.getBlockState(blockpos$mutableblockpos.setPos(l3, j, i4)).getBlock())) {
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
               int k2 = true;
               int l2 = false;

               int k4;
               int l4;
               int i5;
               BlockPos blockpos3;
               for(l3 = position.getY() - 3 + i; l3 <= position.getY() + i; ++l3) {
                  i4 = l3 - (position.getY() + i);
                  k4 = 1 - i4 / 2;

                  for(int k1 = position.getX() - k4; k1 <= position.getX() + k4; ++k1) {
                     l4 = k1 - position.getX();

                     for(i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5) {
                        int j2 = i5 - position.getZ();
                        if (Math.abs(l4) != k4 || Math.abs(j2) != k4 || rand.nextInt(2) != 0 && i4 != 0) {
                           blockpos3 = new BlockPos(k1, l3, i5);
                           hM material = worldIn.getBlockState(blockpos3).getMaterial();
                           if (material == hM.AIR || material == hM.LEAVES || material == hM.VINE) {
                              this.setBlockAndNotifyAdequately(worldIn, blockpos3, this.metaLeaves);
                           }
                        }
                     }
                  }
               }

               for(l3 = 0; l3 < i; ++l3) {
                  hM material1 = worldIn.getBlockState(position.up(l3)).getMaterial();
                  if (material1 == hM.AIR || material1 == hM.LEAVES || material1 == hM.VINE) {
                     this.setBlockAndNotifyAdequately(worldIn, position.up(l3), this.metaWood);
                     if (this.vinesGrow && l3 > 0) {
                        if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(-1, l3, 0))) {
                           this.addVine(worldIn, position.add(-1, l3, 0), hx.EAST);
                        }

                        if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(1, l3, 0))) {
                           this.addVine(worldIn, position.add(1, l3, 0), hx.WEST);
                        }

                        if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(0, l3, -1))) {
                           this.addVine(worldIn, position.add(0, l3, -1), hx.SOUTH);
                        }

                        if (rand.nextInt(3) > 0 && worldIn.isAirBlock(position.add(0, l3, 1))) {
                           this.addVine(worldIn, position.add(0, l3, 1), hx.NORTH);
                        }
                     }
                  }
               }

               if (this.vinesGrow) {
                  for(l3 = position.getY() - 3 + i; l3 <= position.getY() + i; ++l3) {
                     i4 = l3 - (position.getY() + i);
                     k4 = 2 - i4 / 2;
                     BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                     for(l4 = position.getX() - k4; l4 <= position.getX() + k4; ++l4) {
                        for(i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5) {
                           blockpos$mutableblockpos1.setPos(l4, l3, i5);
                           if (worldIn.getBlockState(blockpos$mutableblockpos1).getMaterial() == hM.LEAVES) {
                              BlockPos blockpos2 = blockpos$mutableblockpos1.west();
                              blockpos3 = blockpos$mutableblockpos1.east();
                              BlockPos blockpos4 = blockpos$mutableblockpos1.north();
                              BlockPos blockpos1 = blockpos$mutableblockpos1.south();
                              if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos2).getMaterial() == hM.AIR) {
                                 this.addHangingVine(worldIn, blockpos2, hx.EAST);
                              }

                              if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos3).getMaterial() == hM.AIR) {
                                 this.addHangingVine(worldIn, blockpos3, hx.WEST);
                              }

                              if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos4).getMaterial() == hM.AIR) {
                                 this.addHangingVine(worldIn, blockpos4, hx.SOUTH);
                              }

                              if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos1).getMaterial() == hM.AIR) {
                                 this.addHangingVine(worldIn, blockpos1, hx.NORTH);
                              }
                           }
                        }
                     }
                  }

                  if (rand.nextInt(5) == 0 && i > 5) {
                     for(l3 = 0; l3 < 2; ++l3) {
                        Iterator var23 = EnumFacing.Plane.HORIZONTAL.iterator();

                        while(var23.hasNext()) {
                           EnumFacing enumfacing = (EnumFacing)var23.next();
                           if (rand.nextInt(4 - l3) == 0) {
                              EnumFacing enumfacing1 = enumfacing.getOpposite();
                              this.placeCocoa(worldIn, rand.nextInt(3), position.add(enumfacing1.getXOffset(), i - 5 + l3, enumfacing1.getZOffset()), enumfacing);
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

   private void placeCocoa(bij worldIn, int p_181652_2_, BlockPos pos, EnumFacing side) {
      this.setBlockAndNotifyAdequately(worldIn, pos, Nk.COCOA.getDefaultState().withProperty(cY.AGE, p_181652_2_).withProperty(cY.FACING, side));
   }

   private void addVine(bij worldIn, BlockPos pos, hV prop) {
      this.setBlockAndNotifyAdequately(worldIn, pos, Nk.VINE.getDefaultState().withProperty(prop, true));
   }

   private void addHangingVine(bij worldIn, BlockPos pos, hV prop) {
      this.addVine(worldIn, pos, prop);
      int i = 4;

      for(BlockPos blockpos = pos.down(); worldIn.getBlockState(blockpos).getMaterial() == hM.AIR && i > 0; --i) {
         this.addVine(worldIn, blockpos, prop);
         blockpos = blockpos.down();
      }

   }

   static {
      DEFAULT_TRUNK = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.OAK);
      DEFAULT_LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.OAK).withProperty(ew.CHECK_DECAY, false);
   }
}
