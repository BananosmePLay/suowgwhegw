package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

public class bbd implements bcn {
   private final Random rand;
   protected static final in END_STONE;
   protected static final in AIR;
   private final bcS lperlinNoise1;
   private final bcS lperlinNoise2;
   private final bcS perlinNoise1;
   public bcS noiseGen5;
   public bcS noiseGen6;
   private final bij world;
   private final boolean mapFeaturesEnabled;
   private final BlockPos spawnPoint;
   private final bdf endCityGen = new bdf(this);
   private final bcU islandNoise;
   private double[] buffer;
   private Zi[] biomesForGeneration;
   double[] pnr;
   double[] ar;
   double[] br;
   private final bbC endIslands = new bbC();

   public bbd(bij p_i47241_1_, boolean p_i47241_2_, long p_i47241_3_, BlockPos p_i47241_5_) {
      this.world = p_i47241_1_;
      this.mapFeaturesEnabled = p_i47241_2_;
      this.spawnPoint = p_i47241_5_;
      this.rand = new Random(p_i47241_3_);
      this.lperlinNoise1 = new bcS(this.rand, 16);
      this.lperlinNoise2 = new bcS(this.rand, 16);
      this.perlinNoise1 = new bcS(this.rand, 8);
      this.noiseGen5 = new bcS(this.rand, 10);
      this.noiseGen6 = new bcS(this.rand, 16);
      this.islandNoise = new bcU(this.rand);
   }

   public void setBlocksInChunk(int x, int z, ban primer) {
      int i = true;
      int j = true;
      int k = true;
      int l = true;
      this.buffer = this.getHeights(this.buffer, x * 2, 0, z * 2, 3, 33, 3);

      for(int i1 = 0; i1 < 2; ++i1) {
         for(int j1 = 0; j1 < 2; ++j1) {
            for(int k1 = 0; k1 < 32; ++k1) {
               double d0 = 0.25;
               double d1 = this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 0];
               double d2 = this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 0];
               double d3 = this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 0];
               double d4 = this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 0];
               double d5 = (this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 1] - d1) * 0.25;
               double d6 = (this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25;
               double d7 = (this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 1] - d3) * 0.25;
               double d8 = (this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25;

               for(int l1 = 0; l1 < 4; ++l1) {
                  double d9 = 0.125;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * 0.125;
                  double d13 = (d4 - d2) * 0.125;

                  for(int i2 = 0; i2 < 8; ++i2) {
                     double d14 = 0.125;
                     double d15 = d10;
                     double d16 = (d11 - d10) * 0.125;

                     for(int j2 = 0; j2 < 8; ++j2) {
                        in iblockstate = AIR;
                        if (d15 > 0.0) {
                           iblockstate = END_STONE;
                        }

                        int k2 = i2 + i1 * 8;
                        int l2 = l1 + k1 * 4;
                        int i3 = j2 + j1 * 8;
                        primer.setBlockState(k2, l2, i3, iblockstate);
                        d15 += d16;
                     }

                     d10 += d12;
                     d11 += d13;
                  }

                  d1 += d5;
                  d2 += d6;
                  d3 += d7;
                  d4 += d8;
               }
            }
         }
      }

   }

   public void buildSurfaces(ban primer) {
      for(int i = 0; i < 16; ++i) {
         for(int j = 0; j < 16; ++j) {
            int k = true;
            int l = -1;
            in iblockstate = END_STONE;
            in iblockstate1 = END_STONE;

            for(int i1 = 127; i1 >= 0; --i1) {
               in iblockstate2 = primer.getBlockState(i, i1, j);
               if (iblockstate2.getMaterial() == hM.AIR) {
                  l = -1;
               } else if (iblockstate2.getBlock() == Nk.STONE) {
                  if (l == -1) {
                     l = 1;
                     if (i1 >= 0) {
                        primer.setBlockState(i, i1, j, iblockstate);
                     } else {
                        primer.setBlockState(i, i1, j, iblockstate1);
                     }
                  } else if (l > 0) {
                     --l;
                     primer.setBlockState(i, i1, j, iblockstate1);
                  }
               }
            }
         }
      }

   }

   public bam generateChunk(int x, int z) {
      this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
      ban chunkprimer = new ban();
      this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
      this.setBlocksInChunk(x, z, chunkprimer);
      this.buildSurfaces(chunkprimer);
      if (this.mapFeaturesEnabled) {
         this.endCityGen.generate(this.world, x, z, chunkprimer);
      }

      bam chunk = new bam(this.world, chunkprimer, x, z);
      byte[] abyte = chunk.getBiomeArray();

      for(int i = 0; i < abyte.length; ++i) {
         abyte[i] = (byte)Zi.getIdForBiome(this.biomesForGeneration[i]);
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_) {
      float f = (float)(p_185960_1_ * 2 + p_185960_3_);
      float f1 = (float)(p_185960_2_ * 2 + p_185960_4_);
      float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;
      if (f2 > 80.0F) {
         f2 = 80.0F;
      }

      if (f2 < -100.0F) {
         f2 = -100.0F;
      }

      for(int i = -12; i <= 12; ++i) {
         for(int j = -12; j <= 12; ++j) {
            long k = (long)(p_185960_1_ + i);
            long l = (long)(p_185960_2_ + j);
            if (k * k + l * l > 4096L && this.islandNoise.getValue((double)k, (double)l) < -0.8999999761581421) {
               float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
               f = (float)(p_185960_3_ - i * 2);
               f1 = (float)(p_185960_4_ - j * 2);
               float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;
               if (f4 > 80.0F) {
                  f4 = 80.0F;
               }

               if (f4 < -100.0F) {
                  f4 = -100.0F;
               }

               if (f4 > f2) {
                  f2 = f4;
               }
            }
         }
      }

      return f2;
   }

   public boolean isIslandChunk(int p_185961_1_, int p_185961_2_) {
      return (long)p_185961_1_ * (long)p_185961_1_ + (long)p_185961_2_ * (long)p_185961_2_ > 4096L && this.getIslandHeightValue(p_185961_1_, p_185961_2_, 1, 1) >= 0.0F;
   }

   private double[] getHeights(double[] p_185963_1_, int p_185963_2_, int p_185963_3_, int p_185963_4_, int p_185963_5_, int p_185963_6_, int p_185963_7_) {
      if (p_185963_1_ == null) {
         p_185963_1_ = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];
      }

      double d0 = 684.412;
      double d1 = 684.412;
      d0 *= 2.0;
      this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0, 4.277575000000001, d0 / 80.0);
      this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412, d0);
      this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412, d0);
      int i = p_185963_2_ / 2;
      int j = p_185963_4_ / 2;
      int k = 0;

      for(int l = 0; l < p_185963_5_; ++l) {
         for(int i1 = 0; i1 < p_185963_7_; ++i1) {
            float f = this.getIslandHeightValue(i, j, l, i1);

            for(int j1 = 0; j1 < p_185963_6_; ++j1) {
               double d2 = this.ar[k] / 512.0;
               double d3 = this.br[k] / 512.0;
               double d5 = (this.pnr[k] / 10.0 + 1.0) / 2.0;
               double d4;
               if (d5 < 0.0) {
                  d4 = d2;
               } else if (d5 > 1.0) {
                  d4 = d3;
               } else {
                  d4 = d2 + (d3 - d2) * d5;
               }

               d4 -= 8.0;
               d4 += (double)f;
               int k1 = 2;
               double d7;
               if (j1 > p_185963_6_ / 2 - k1) {
                  d7 = (double)((float)(j1 - (p_185963_6_ / 2 - k1)) / 64.0F);
                  d7 = MathHelper.clamp(d7, 0.0, 1.0);
                  d4 = d4 * (1.0 - d7) + -3000.0 * d7;
               }

               k1 = 8;
               if (j1 < k1) {
                  d7 = (double)((float)(k1 - j1) / ((float)k1 - 1.0F));
                  d4 = d4 * (1.0 - d7) + -30.0 * d7;
               }

               p_185963_1_[k] = d4;
               ++k;
            }
         }
      }

      return p_185963_1_;
   }

   public void populate(int x, int z) {
      dH.fallInstantly = true;
      BlockPos blockpos = new BlockPos(x * 16, 0, z * 16);
      if (this.mapFeaturesEnabled) {
         this.endCityGen.generateStructure(this.world, this.rand, new ChunkPos(x, z));
      }

      this.world.getBiome(blockpos.add(16, 0, 16)).decorate(this.world, this.world.rand, blockpos);
      long i = (long)x * (long)x + (long)z * (long)z;
      if (i > 4096L) {
         float f = this.getIslandHeightValue(x, z, 1, 1);
         if (f < -20.0F && this.rand.nextInt(14) == 0) {
            this.endIslands.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, 55 + this.rand.nextInt(16), this.rand.nextInt(16) + 8));
            if (this.rand.nextInt(4) == 0) {
               this.endIslands.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, 55 + this.rand.nextInt(16), this.rand.nextInt(16) + 8));
            }
         }

         if (this.getIslandHeightValue(x, z, 1, 1) > 40.0F) {
            int j = this.rand.nextInt(5);

            int l1;
            int i2;
            int j2;
            int k2;
            for(l1 = 0; l1 < j; ++l1) {
               i2 = this.rand.nextInt(16) + 8;
               j2 = this.rand.nextInt(16) + 8;
               k2 = this.world.getHeight(blockpos.add(i2, 0, j2)).getY();
               if (k2 > 0) {
                  int k1 = k2 - 1;
                  if (this.world.isAirBlock(blockpos.add(i2, k1 + 1, j2)) && this.world.getBlockState(blockpos.add(i2, k1, j2)).getBlock() == Nk.END_STONE) {
                     cU.generatePlant(this.world, blockpos.add(i2, k1 + 1, j2), this.rand, 8);
                  }
               }
            }

            if (this.rand.nextInt(700) == 0) {
               l1 = this.rand.nextInt(16) + 8;
               i2 = this.rand.nextInt(16) + 8;
               j2 = this.world.getHeight(blockpos.add(l1, 0, i2)).getY();
               if (j2 > 0) {
                  k2 = j2 + 3 + this.rand.nextInt(7);
                  BlockPos blockpos1 = blockpos.add(l1, k2, i2);
                  (new bbB()).generate(this.world, this.rand, blockpos1);
                  Yg tileentity = this.world.getTileEntity(blockpos1);
                  if (tileentity instanceof Yx) {
                     Yx tileentityendgateway = (Yx)tileentity;
                     tileentityendgateway.setExactPosition(this.spawnPoint);
                  }
               }
            }
         }
      }

      dH.fallInstantly = false;
   }

   public boolean generateStructures(bam chunkIn, int x, int z) {
      return false;
   }

   public List<Zg> getPossibleCreatures(IC creatureType, BlockPos pos) {
      return this.world.getBiome(pos).getSpawnableList(creatureType);
   }

   @Nullable
   public BlockPos getNearestStructurePos(bij worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      return "EndCity".equals(structureName) && this.endCityGen != null ? this.endCityGen.getNearestStructurePos(worldIn, position, findUnexplored) : null;
   }

   public boolean isInsideStructure(bij worldIn, String structureName, BlockPos pos) {
      return "EndCity".equals(structureName) && this.endCityGen != null ? this.endCityGen.isInsideStructure(pos) : false;
   }

   public void recreateStructures(bam chunkIn, int x, int z) {
   }

   static {
      END_STONE = Nk.END_STONE.getDefaultState();
      AIR = Nk.AIR.getDefaultState();
   }
}
