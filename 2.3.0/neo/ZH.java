package neo;

import java.util.Arrays;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZH extends Zi {
   protected static final in COARSE_DIRT;
   protected static final in GRASS;
   protected static final in HARDENED_CLAY;
   protected static final in STAINED_HARDENED_CLAY;
   protected static final in ORANGE_STAINED_HARDENED_CLAY;
   protected static final in RED_SAND;
   private in[] clayBands;
   private long worldSeed;
   private bcT pillarNoise;
   private bcT pillarRoofNoise;
   private bcT clayBandsOffsetNoise;
   private final boolean brycePillars;
   private final boolean hasForest;

   public ZH(boolean p_i46704_1_, boolean p_i46704_2_, Zf properties) {
      super(properties);
      this.brycePillars = p_i46704_1_;
      this.hasForest = p_i46704_2_;
      this.spawnableCreatureList.clear();
      this.topBlock = RED_SAND;
      this.fillerBlock = STAINED_HARDENED_CLAY;
      this.decorator.treesPerChunk = -999;
      this.decorator.deadBushPerChunk = 20;
      this.decorator.reedsPerChunk = 3;
      this.decorator.cactiPerChunk = 5;
      this.decorator.flowersPerChunk = 0;
      this.spawnableCreatureList.clear();
      if (p_i46704_2_) {
         this.decorator.treesPerChunk = 5;
      }

   }

   protected Zr createBiomeDecorator() {
      return new ZG(this);
   }

   public bbn getRandomTreeFeature(Random rand) {
      return TREE_FEATURE;
   }

   public int getFoliageColorAtPos(BlockPos pos) {
      return 10387789;
   }

   public int getGrassColorAtPos(BlockPos pos) {
      return 9470285;
   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      if (this.clayBands == null || this.worldSeed != worldIn.getSeed()) {
         this.generateBands(worldIn.getSeed());
      }

      if (this.pillarNoise == null || this.pillarRoofNoise == null || this.worldSeed != worldIn.getSeed()) {
         Random random = new Random(this.worldSeed);
         this.pillarNoise = new bcT(random, 4);
         this.pillarRoofNoise = new bcT(random, 1);
      }

      this.worldSeed = worldIn.getSeed();
      double d4 = 0.0;
      int k1;
      int l1;
      if (this.brycePillars) {
         k1 = (x & -16) + (z & 15);
         l1 = (z & -16) + (x & 15);
         double d0 = Math.min(Math.abs(noiseVal), this.pillarNoise.getValue((double)k1 * 0.25, (double)l1 * 0.25));
         if (d0 > 0.0) {
            double d1 = 0.001953125;
            double d2 = Math.abs(this.pillarRoofNoise.getValue((double)k1 * 0.001953125, (double)l1 * 0.001953125));
            d4 = d0 * d0 * 2.5;
            double d3 = Math.ceil(d2 * 50.0) + 14.0;
            if (d4 > d3) {
               d4 = d3;
            }

            d4 += 64.0;
         }
      }

      k1 = x & 15;
      l1 = z & 15;
      int i2 = worldIn.getSeaLevel();
      in iblockstate = STAINED_HARDENED_CLAY;
      in iblockstate3 = this.fillerBlock;
      int k = (int)(noiseVal / 3.0 + 3.0 + rand.nextDouble() * 0.25);
      boolean flag = Math.cos(noiseVal / 3.0 * Math.PI) > 0.0;
      int l = -1;
      boolean flag1 = false;
      int i1 = 0;

      for(int j1 = 255; j1 >= 0; --j1) {
         if (chunkPrimerIn.getBlockState(l1, j1, k1).getMaterial() == hM.AIR && j1 < (int)d4) {
            chunkPrimerIn.setBlockState(l1, j1, k1, STONE);
         }

         if (j1 <= rand.nextInt(5)) {
            chunkPrimerIn.setBlockState(l1, j1, k1, BEDROCK);
         } else if (i1 < 15 || this.brycePillars) {
            in iblockstate1 = chunkPrimerIn.getBlockState(l1, j1, k1);
            if (iblockstate1.getMaterial() == hM.AIR) {
               l = -1;
            } else if (iblockstate1.getBlock() == Nk.STONE) {
               if (l == -1) {
                  flag1 = false;
                  if (k <= 0) {
                     iblockstate = AIR;
                     iblockstate3 = STONE;
                  } else if (j1 >= i2 - 4 && j1 <= i2 + 1) {
                     iblockstate = STAINED_HARDENED_CLAY;
                     iblockstate3 = this.fillerBlock;
                  }

                  if (j1 < i2 && (iblockstate == null || iblockstate.getMaterial() == hM.AIR)) {
                     iblockstate = WATER;
                  }

                  l = k + Math.max(0, j1 - i2);
                  if (j1 >= i2 - 1) {
                     if (this.hasForest && j1 > 86 + k * 2) {
                        if (flag) {
                           chunkPrimerIn.setBlockState(l1, j1, k1, COARSE_DIRT);
                        } else {
                           chunkPrimerIn.setBlockState(l1, j1, k1, GRASS);
                        }
                     } else if (j1 > i2 + 3 + k) {
                        in iblockstate2;
                        if (j1 >= 64 && j1 <= 127) {
                           if (flag) {
                              iblockstate2 = HARDENED_CLAY;
                           } else {
                              iblockstate2 = this.getBand(x, j1, z);
                           }
                        } else {
                           iblockstate2 = ORANGE_STAINED_HARDENED_CLAY;
                        }

                        chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate2);
                     } else {
                        chunkPrimerIn.setBlockState(l1, j1, k1, this.topBlock);
                        flag1 = true;
                     }
                  } else {
                     chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate3);
                     if (iblockstate3.getBlock() == Nk.STAINED_HARDENED_CLAY) {
                        chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
                     }
                  }
               } else if (l > 0) {
                  --l;
                  if (flag1) {
                     chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
                  } else {
                     chunkPrimerIn.setBlockState(l1, j1, k1, this.getBand(x, j1, z));
                  }
               }

               ++i1;
            }
         }
      }

   }

   private void generateBands(long p_150619_1_) {
      this.clayBands = new in[64];
      Arrays.fill(this.clayBands, HARDENED_CLAY);
      Random random = new Random(p_150619_1_);
      this.clayBandsOffsetNoise = new bcT(random, 1);

      int i2;
      for(i2 = 0; i2 < 64; ++i2) {
         i2 += random.nextInt(5) + 1;
         if (i2 < 64) {
            this.clayBands[i2] = ORANGE_STAINED_HARDENED_CLAY;
         }
      }

      i2 = random.nextInt(4) + 2;

      int j2;
      int l2;
      int k3;
      int j4;
      for(j2 = 0; j2 < i2; ++j2) {
         l2 = random.nextInt(3) + 1;
         k3 = random.nextInt(64);

         for(j4 = 0; k3 + j4 < 64 && j4 < l2; ++j4) {
            this.clayBands[k3 + j4] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.YELLOW);
         }
      }

      j2 = random.nextInt(4) + 2;

      int k4;
      for(l2 = 0; l2 < j2; ++l2) {
         k3 = random.nextInt(3) + 2;
         j4 = random.nextInt(64);

         for(k4 = 0; j4 + k4 < 64 && k4 < k3; ++k4) {
            this.clayBands[j4 + k4] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.BROWN);
         }
      }

      l2 = random.nextInt(4) + 2;

      for(k3 = 0; k3 < l2; ++k3) {
         j4 = random.nextInt(3) + 1;
         k4 = random.nextInt(64);

         for(int j1 = 0; k4 + j1 < 64 && j1 < j4; ++j1) {
            this.clayBands[k4 + j1] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.RED);
         }
      }

      k3 = random.nextInt(3) + 3;
      j4 = 0;

      for(k4 = 0; k4 < k3; ++k4) {
         int i5 = true;
         j4 += random.nextInt(16) + 4;

         for(int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1) {
            this.clayBands[j4 + k1] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.WHITE);
            if (j4 + k1 > 1 && random.nextBoolean()) {
               this.clayBands[j4 + k1 - 1] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.SILVER);
            }

            if (j4 + k1 < 63 && random.nextBoolean()) {
               this.clayBands[j4 + k1 + 1] = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.SILVER);
            }
         }
      }

   }

   private in getBand(int p_180629_1_, int p_180629_2_, int p_180629_3_) {
      int i = (int)Math.round(this.clayBandsOffsetNoise.getValue((double)p_180629_1_ / 512.0, (double)p_180629_1_ / 512.0) * 2.0);
      return this.clayBands[(p_180629_2_ + i + 64) % 64];
   }

   static {
      COARSE_DIRT = Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.COARSE_DIRT);
      GRASS = Nk.GRASS.getDefaultState();
      HARDENED_CLAY = Nk.HARDENED_CLAY.getDefaultState();
      STAINED_HARDENED_CLAY = Nk.STAINED_HARDENED_CLAY.getDefaultState();
      ORANGE_STAINED_HARDENED_CLAY = STAINED_HARDENED_CLAY.withProperty(cZ.COLOR, Om.ORANGE);
      RED_SAND = Nk.SAND.getDefaultState().withProperty(gk.VARIANT, gj.RED_SAND);
   }
}
