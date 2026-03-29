package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

public class bbf implements bcn {
   protected static final in AIR;
   protected static final in NETHERRACK;
   protected static final in BEDROCK;
   protected static final in LAVA;
   protected static final in GRAVEL;
   protected static final in SOUL_SAND;
   private final bij world;
   private final boolean generateStructures;
   private final Random rand;
   private double[] slowsandNoise = new double[256];
   private double[] gravelNoise = new double[256];
   private double[] depthBuffer = new double[256];
   private double[] buffer;
   private final bcS lperlinNoise1;
   private final bcS lperlinNoise2;
   private final bcS perlinNoise1;
   private final bcS slowsandGravelNoiseGen;
   private final bcS netherrackExculsivityNoiseGen;
   public final bcS scaleNoise;
   public final bcS depthNoise;
   private final bbG fireFeature = new bbG();
   private final bbJ lightGemGen = new bbJ();
   private final bbK hellPortalGen = new bbK();
   private final bbE quartzGen;
   private final bbE magmaGen;
   private final bbL lavaTrapGen;
   private final bbL hellSpringGen;
   private final bbt brownMushroomFeature;
   private final bbt redMushroomFeature;
   private final bdj genNetherBridge;
   private final bcM genNetherCaves;
   double[] pnr;
   double[] ar;
   double[] br;
   double[] noiseData4;
   double[] dr;

   public bbf(bij worldIn, boolean p_i45637_2_, long seed) {
      this.quartzGen = new bbW(Nk.QUARTZ_ORE.getDefaultState(), 14, ip.forBlock(Nk.NETHERRACK));
      this.magmaGen = new bbW(Nk.MAGMA.getDefaultState(), 33, ip.forBlock(Nk.NETHERRACK));
      this.lavaTrapGen = new bbL(Nk.FLOWING_LAVA, true);
      this.hellSpringGen = new bbL(Nk.FLOWING_LAVA, false);
      this.brownMushroomFeature = new bbt(Nk.BROWN_MUSHROOM);
      this.redMushroomFeature = new bbt(Nk.RED_MUSHROOM);
      this.genNetherBridge = new bdj();
      this.genNetherCaves = new bcO();
      this.world = worldIn;
      this.generateStructures = p_i45637_2_;
      this.rand = new Random(seed);
      this.lperlinNoise1 = new bcS(this.rand, 16);
      this.lperlinNoise2 = new bcS(this.rand, 16);
      this.perlinNoise1 = new bcS(this.rand, 8);
      this.slowsandGravelNoiseGen = new bcS(this.rand, 4);
      this.netherrackExculsivityNoiseGen = new bcS(this.rand, 4);
      this.scaleNoise = new bcS(this.rand, 10);
      this.depthNoise = new bcS(this.rand, 16);
      worldIn.setSeaLevel(63);
   }

   public void prepareHeights(int p_185936_1_, int p_185936_2_, ban primer) {
      int i = true;
      int j = this.world.getSeaLevel() / 2 + 1;
      int k = true;
      int l = true;
      int i1 = true;
      this.buffer = this.getHeights(this.buffer, p_185936_1_ * 4, 0, p_185936_2_ * 4, 5, 17, 5);

      for(int j1 = 0; j1 < 4; ++j1) {
         for(int k1 = 0; k1 < 4; ++k1) {
            for(int l1 = 0; l1 < 16; ++l1) {
               double d0 = 0.125;
               double d1 = this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 0];
               double d2 = this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 0];
               double d3 = this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 0];
               double d4 = this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 0];
               double d5 = (this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 1] - d1) * 0.125;
               double d6 = (this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125;
               double d7 = (this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 1] - d3) * 0.125;
               double d8 = (this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125;

               for(int i2 = 0; i2 < 8; ++i2) {
                  double d9 = 0.25;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * 0.25;
                  double d13 = (d4 - d2) * 0.25;

                  for(int j2 = 0; j2 < 4; ++j2) {
                     double d14 = 0.25;
                     double d15 = d10;
                     double d16 = (d11 - d10) * 0.25;

                     for(int k2 = 0; k2 < 4; ++k2) {
                        in iblockstate = null;
                        if (l1 * 8 + i2 < j) {
                           iblockstate = LAVA;
                        }

                        if (d15 > 0.0) {
                           iblockstate = NETHERRACK;
                        }

                        int l2 = j2 + j1 * 4;
                        int i3 = i2 + l1 * 8;
                        int j3 = k2 + k1 * 4;
                        primer.setBlockState(l2, i3, j3, iblockstate);
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

   public void buildSurfaces(int p_185937_1_, int p_185937_2_, ban primer) {
      int i = this.world.getSeaLevel() + 1;
      double d0 = 0.03125;
      this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1, 0.03125, 0.03125, 1.0);
      this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, p_185937_1_ * 16, 109, p_185937_2_ * 16, 16, 1, 16, 0.03125, 1.0, 0.03125);
      this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1, 0.0625, 0.0625, 0.0625);

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            boolean flag = this.slowsandNoise[j + k * 16] + this.rand.nextDouble() * 0.2 > 0.0;
            boolean flag1 = this.gravelNoise[j + k * 16] + this.rand.nextDouble() * 0.2 > 0.0;
            int l = (int)(this.depthBuffer[j + k * 16] / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);
            int i1 = -1;
            in iblockstate = NETHERRACK;
            in iblockstate1 = NETHERRACK;

            for(int j1 = 127; j1 >= 0; --j1) {
               if (j1 < 127 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5)) {
                  in iblockstate2 = primer.getBlockState(k, j1, j);
                  if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != hM.AIR) {
                     if (iblockstate2.getBlock() == Nk.NETHERRACK) {
                        if (i1 == -1) {
                           if (l <= 0) {
                              iblockstate = AIR;
                              iblockstate1 = NETHERRACK;
                           } else if (j1 >= i - 4 && j1 <= i + 1) {
                              iblockstate = NETHERRACK;
                              iblockstate1 = NETHERRACK;
                              if (flag1) {
                                 iblockstate = GRAVEL;
                                 iblockstate1 = NETHERRACK;
                              }

                              if (flag) {
                                 iblockstate = SOUL_SAND;
                                 iblockstate1 = SOUL_SAND;
                              }
                           }

                           if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == hM.AIR)) {
                              iblockstate = LAVA;
                           }

                           i1 = l;
                           if (j1 >= i - 1) {
                              primer.setBlockState(k, j1, j, iblockstate);
                           } else {
                              primer.setBlockState(k, j1, j, iblockstate1);
                           }
                        } else if (i1 > 0) {
                           --i1;
                           primer.setBlockState(k, j1, j, iblockstate1);
                        }
                     }
                  } else {
                     i1 = -1;
                  }
               } else {
                  primer.setBlockState(k, j1, j, BEDROCK);
               }
            }
         }
      }

   }

   public bam generateChunk(int x, int z) {
      this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
      ban chunkprimer = new ban();
      this.prepareHeights(x, z, chunkprimer);
      this.buildSurfaces(x, z, chunkprimer);
      this.genNetherCaves.generate(this.world, x, z, chunkprimer);
      if (this.generateStructures) {
         this.genNetherBridge.generate(this.world, x, z, chunkprimer);
      }

      bam chunk = new bam(this.world, chunkprimer, x, z);
      Zi[] abiome = this.world.getBiomeProvider().getBiomes((Zi[])null, x * 16, z * 16, 16, 16);
      byte[] abyte = chunk.getBiomeArray();

      for(int i = 0; i < abyte.length; ++i) {
         abyte[i] = (byte)Zi.getIdForBiome(abiome[i]);
      }

      chunk.resetRelightChecks();
      return chunk;
   }

   private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_) {
      if (p_185938_1_ == null) {
         p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
      }

      double d0 = 684.412;
      double d1 = 2053.236;
      this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0, 0.0, 1.0);
      this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0, 0.0, 100.0);
      this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 8.555150000000001, 34.2206, 8.555150000000001);
      this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412, 2053.236, 684.412);
      this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412, 2053.236, 684.412);
      int i = 0;
      double[] adouble = new double[p_185938_6_];

      int j;
      for(j = 0; j < p_185938_6_; ++j) {
         adouble[j] = Math.cos((double)j * Math.PI * 6.0 / (double)p_185938_6_) * 2.0;
         double d2 = (double)j;
         if (j > p_185938_6_ / 2) {
            d2 = (double)(p_185938_6_ - 1 - j);
         }

         if (d2 < 4.0) {
            d2 = 4.0 - d2;
            adouble[j] -= d2 * d2 * d2 * 10.0;
         }
      }

      for(j = 0; j < p_185938_5_; ++j) {
         for(int i1 = 0; i1 < p_185938_7_; ++i1) {
            double d3 = 0.0;

            for(int k = 0; k < p_185938_6_; ++k) {
               double d4 = adouble[k];
               double d5 = this.ar[i] / 512.0;
               double d6 = this.br[i] / 512.0;
               double d7 = (this.pnr[i] / 10.0 + 1.0) / 2.0;
               double d8;
               if (d7 < 0.0) {
                  d8 = d5;
               } else if (d7 > 1.0) {
                  d8 = d6;
               } else {
                  d8 = d5 + (d6 - d5) * d7;
               }

               d8 -= d4;
               double d10;
               if (k > p_185938_6_ - 4) {
                  d10 = (double)((float)(k - (p_185938_6_ - 4)) / 3.0F);
                  d8 = d8 * (1.0 - d10) + -10.0 * d10;
               }

               if ((double)k < 0.0) {
                  d10 = (0.0 - (double)k) / 4.0;
                  d10 = MathHelper.clamp(d10, 0.0, 1.0);
                  d8 = d8 * (1.0 - d10) + -10.0 * d10;
               }

               p_185938_1_[i] = d8;
               ++i;
            }
         }
      }

      return p_185938_1_;
   }

   public void populate(int x, int z) {
      dH.fallInstantly = true;
      int i = x * 16;
      int j = z * 16;
      BlockPos blockpos = new BlockPos(i, 0, j);
      Zi biome = this.world.getBiome(blockpos.add(16, 0, 16));
      ChunkPos chunkpos = new ChunkPos(x, z);
      this.genNetherBridge.generateStructure(this.world, this.rand, chunkpos);

      int i2;
      for(i2 = 0; i2 < 8; ++i2) {
         this.hellSpringGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
      }

      for(i2 = 0; i2 < this.rand.nextInt(this.rand.nextInt(10) + 1) + 1; ++i2) {
         this.fireFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
      }

      for(i2 = 0; i2 < this.rand.nextInt(this.rand.nextInt(10) + 1); ++i2) {
         this.lightGemGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
      }

      for(i2 = 0; i2 < 10; ++i2) {
         this.hellPortalGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
      }

      if (this.rand.nextBoolean()) {
         this.brownMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
      }

      if (this.rand.nextBoolean()) {
         this.redMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
      }

      for(i2 = 0; i2 < 16; ++i2) {
         this.quartzGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
      }

      i2 = this.world.getSeaLevel() / 2 + 1;

      int j2;
      for(j2 = 0; j2 < 4; ++j2) {
         this.magmaGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), i2 - 5 + this.rand.nextInt(10), this.rand.nextInt(16)));
      }

      for(j2 = 0; j2 < 16; ++j2) {
         this.lavaTrapGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
      }

      biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
      dH.fallInstantly = false;
   }

   public boolean generateStructures(bam chunkIn, int x, int z) {
      return false;
   }

   public List<Zg> getPossibleCreatures(IC creatureType, BlockPos pos) {
      if (creatureType == IC.MONSTER) {
         if (this.genNetherBridge.isInsideStructure(pos)) {
            return this.genNetherBridge.getSpawnList();
         }

         if (this.genNetherBridge.isPositionInStructure(this.world, pos) && this.world.getBlockState(pos.down()).getBlock() == Nk.NETHER_BRICK) {
            return this.genNetherBridge.getSpawnList();
         }
      }

      Zi biome = this.world.getBiome(pos);
      return biome.getSpawnableList(creatureType);
   }

   @Nullable
   public BlockPos getNearestStructurePos(bij worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      return "Fortress".equals(structureName) && this.genNetherBridge != null ? this.genNetherBridge.getNearestStructurePos(worldIn, position, findUnexplored) : null;
   }

   public boolean isInsideStructure(bij worldIn, String structureName, BlockPos pos) {
      return "Fortress".equals(structureName) && this.genNetherBridge != null ? this.genNetherBridge.isInsideStructure(pos) : false;
   }

   public void recreateStructures(bam chunkIn, int x, int z) {
      this.genNetherBridge.generate(this.world, x, z, (ban)null);
   }

   static {
      AIR = Nk.AIR.getDefaultState();
      NETHERRACK = Nk.NETHERRACK.getDefaultState();
      BEDROCK = Nk.BEDROCK.getDefaultState();
      LAVA = Nk.LAVA.getDefaultState();
      GRAVEL = Nk.GRAVEL.getDefaultState();
      SOUL_SAND = Nk.SOUL_SAND.getDefaultState();
   }
}
