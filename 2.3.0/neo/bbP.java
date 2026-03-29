package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbP extends bbE {
   private final co block;

   public bbP(co blockIn) {
      this.block = blockIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down()) {
      }

      if (position.getY() <= 4) {
         return false;
      } else {
         position = position.down(4);
         boolean[] aboolean = new boolean[2048];
         int i = rand.nextInt(4) + 4;

         int k2;
         for(k2 = 0; k2 < i; ++k2) {
            double d0 = rand.nextDouble() * 6.0 + 3.0;
            double d1 = rand.nextDouble() * 4.0 + 2.0;
            double d2 = rand.nextDouble() * 6.0 + 3.0;
            double d3 = rand.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
            double d4 = rand.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
            double d5 = rand.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;

            for(int l = 1; l < 15; ++l) {
               for(int i1 = 1; i1 < 15; ++i1) {
                  for(int j1 = 1; j1 < 7; ++j1) {
                     double d6 = ((double)l - d3) / (d0 / 2.0);
                     double d7 = ((double)j1 - d4) / (d1 / 2.0);
                     double d8 = ((double)i1 - d5) / (d2 / 2.0);
                     double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                     if (d9 < 1.0) {
                        aboolean[(l * 16 + i1) * 8 + j1] = true;
                     }
                  }
               }
            }
         }

         int k4;
         int l3;
         boolean flag1;
         for(k2 = 0; k2 < 16; ++k2) {
            for(l3 = 0; l3 < 16; ++l3) {
               for(k4 = 0; k4 < 8; ++k4) {
                  flag1 = !aboolean[(k2 * 16 + l3) * 8 + k4] && (k2 < 15 && aboolean[((k2 + 1) * 16 + l3) * 8 + k4] || k2 > 0 && aboolean[((k2 - 1) * 16 + l3) * 8 + k4] || l3 < 15 && aboolean[(k2 * 16 + l3 + 1) * 8 + k4] || l3 > 0 && aboolean[(k2 * 16 + (l3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(k2 * 16 + l3) * 8 + k4 + 1] || k4 > 0 && aboolean[(k2 * 16 + l3) * 8 + (k4 - 1)]);
                  if (flag1) {
                     hM material = worldIn.getBlockState(position.add(k2, k4, l3)).getMaterial();
                     if (k4 >= 4 && material.isLiquid()) {
                        return false;
                     }

                     if (k4 < 4 && !material.isSolid() && worldIn.getBlockState(position.add(k2, k4, l3)).getBlock() != this.block) {
                        return false;
                     }
                  }
               }
            }
         }

         for(k2 = 0; k2 < 16; ++k2) {
            for(l3 = 0; l3 < 16; ++l3) {
               for(k4 = 0; k4 < 8; ++k4) {
                  if (aboolean[(k2 * 16 + l3) * 8 + k4]) {
                     worldIn.setBlockState(position.add(k2, k4, l3), k4 >= 4 ? Nk.AIR.getDefaultState() : this.block.getDefaultState(), 2);
                  }
               }
            }
         }

         for(k2 = 0; k2 < 16; ++k2) {
            for(l3 = 0; l3 < 16; ++l3) {
               for(k4 = 4; k4 < 8; ++k4) {
                  if (aboolean[(k2 * 16 + l3) * 8 + k4]) {
                     BlockPos blockpos = position.add(k2, k4 - 1, l3);
                     if (worldIn.getBlockState(blockpos).getBlock() == Nk.DIRT && worldIn.getLightFor(baW.SKY, position.add(k2, k4, l3)) > 0) {
                        Zi biome = worldIn.getBiome(blockpos);
                        if (biome.topBlock.getBlock() == Nk.MYCELIUM) {
                           worldIn.setBlockState(blockpos, Nk.MYCELIUM.getDefaultState(), 2);
                        } else {
                           worldIn.setBlockState(blockpos, Nk.GRASS.getDefaultState(), 2);
                        }
                     }
                  }
               }
            }
         }

         if (this.block.getDefaultState().getMaterial() == hM.LAVA) {
            for(k2 = 0; k2 < 16; ++k2) {
               for(l3 = 0; l3 < 16; ++l3) {
                  for(k4 = 0; k4 < 8; ++k4) {
                     flag1 = !aboolean[(k2 * 16 + l3) * 8 + k4] && (k2 < 15 && aboolean[((k2 + 1) * 16 + l3) * 8 + k4] || k2 > 0 && aboolean[((k2 - 1) * 16 + l3) * 8 + k4] || l3 < 15 && aboolean[(k2 * 16 + l3 + 1) * 8 + k4] || l3 > 0 && aboolean[(k2 * 16 + (l3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(k2 * 16 + l3) * 8 + k4 + 1] || k4 > 0 && aboolean[(k2 * 16 + l3) * 8 + (k4 - 1)]);
                     if (flag1 && (k4 < 4 || rand.nextInt(2) != 0) && worldIn.getBlockState(position.add(k2, k4, l3)).getMaterial().isSolid()) {
                        worldIn.setBlockState(position.add(k2, k4, l3), Nk.STONE.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         if (this.block.getDefaultState().getMaterial() == hM.WATER) {
            for(k2 = 0; k2 < 16; ++k2) {
               for(l3 = 0; l3 < 16; ++l3) {
                  int l4 = true;
                  if (worldIn.canBlockFreezeWater(position.add(k2, 4, l3))) {
                     worldIn.setBlockState(position.add(k2, 4, l3), Nk.ICE.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }
}
