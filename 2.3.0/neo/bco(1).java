package neo;

public abstract class bco {
   private long worldGenSeed;
   protected bco parent;
   private long chunkSeed;
   protected long baseSeed;

   public static bco[] initializeAllBiomeGenerators(long seed, bix p_180781_2_, bbk p_180781_3_) {
      bco genlayer = new bcA(1L);
      bco genlayer = new bcy(2000L, genlayer);
      bco genlayeraddisland = new bcp(1L, genlayer);
      bco genlayerzoom = new bcJ(2001L, genlayeraddisland);
      bco genlayeraddisland1 = new bcp(2L, genlayerzoom);
      genlayeraddisland1 = new bcp(50L, genlayeraddisland1);
      genlayeraddisland1 = new bcp(70L, genlayeraddisland1);
      bco genlayerremovetoomuchocean = new bcC(2L, genlayeraddisland1);
      bco genlayeraddsnow = new bcr(2L, genlayerremovetoomuchocean);
      bco genlayeraddisland2 = new bcp(3L, genlayeraddsnow);
      bco genlayeredge = new bcx(2L, genlayeraddisland2, bcw.COOL_WARM);
      genlayeredge = new bcx(2L, genlayeredge, bcw.HEAT_ICE);
      genlayeredge = new bcx(3L, genlayeredge, bcw.SPECIAL);
      bco genlayerzoom1 = new bcJ(2002L, genlayeredge);
      genlayerzoom1 = new bcJ(2003L, genlayerzoom1);
      bco genlayeraddisland3 = new bcp(4L, genlayerzoom1);
      bco genlayeraddmushroomisland = new bcq(5L, genlayeraddisland3);
      bco genlayerdeepocean = new bcu(4L, genlayeraddmushroomisland);
      bco genlayer4 = bcJ.magnify(1000L, genlayerdeepocean, 0);
      int i = 4;
      int j = i;
      if (p_180781_3_ != null) {
         i = p_180781_3_.biomeSize;
         j = p_180781_3_.riverSize;
      }

      if (p_180781_2_ == bix.LARGE_BIOMES) {
         i = 6;
      }

      bco lvt_7_1_ = bcJ.magnify(1000L, genlayer4, 0);
      bco genlayerriverinit = new bcE(100L, lvt_7_1_);
      bco lvt_8_1_ = new bcs(200L, genlayer4, p_180781_2_, p_180781_3_);
      bco genlayer6 = bcJ.magnify(1000L, lvt_8_1_, 2);
      bco genlayerbiomeedge = new bct(1000L, genlayer6);
      bco lvt_9_1_ = bcJ.magnify(1000L, genlayerriverinit, 2);
      bco genlayerhills = new bcz(1000L, genlayerbiomeedge, lvt_9_1_);
      bco genlayer5 = bcJ.magnify(1000L, genlayerriverinit, 2);
      genlayer5 = bcJ.magnify(1000L, genlayer5, j);
      bco genlayerriver = new bcD(1L, genlayer5);
      bco genlayersmooth = new bcH(1000L, genlayerriver);
      bco genlayerhills = new bcB(1001L, genlayerhills);

      for(int k = 0; k < i; ++k) {
         genlayerhills = new bcJ((long)(1000 + k), (bco)genlayerhills);
         if (k == 0) {
            genlayerhills = new bcp(3L, (bco)genlayerhills);
         }

         if (k == 1 || i == 1) {
            genlayerhills = new bcG(1000L, (bco)genlayerhills);
         }
      }

      bco genlayersmooth1 = new bcH(1000L, (bco)genlayerhills);
      bco genlayerrivermix = new bcF(100L, genlayersmooth1, genlayersmooth);
      bco genlayer3 = new bcI(10L, genlayerrivermix);
      ((bco)genlayerrivermix).initWorldGenSeed(seed);
      ((bco)genlayer3).initWorldGenSeed(seed);
      return new bco[]{genlayerrivermix, genlayer3, genlayerrivermix};
   }

   public bco(long p_i2125_1_) {
      this.baseSeed = p_i2125_1_;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += p_i2125_1_;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += p_i2125_1_;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += p_i2125_1_;
   }

   public void initWorldGenSeed(long seed) {
      this.worldGenSeed = seed;
      if (this.parent != null) {
         this.parent.initWorldGenSeed(seed);
      }

      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
   }

   public void initChunkSeed(long p_75903_1_, long p_75903_3_) {
      this.chunkSeed = this.worldGenSeed;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += p_75903_1_;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += p_75903_3_;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += p_75903_1_;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += p_75903_3_;
   }

   protected int nextInt(int p_75902_1_) {
      int i = (int)((this.chunkSeed >> 24) % (long)p_75902_1_);
      if (i < 0) {
         i += p_75902_1_;
      }

      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += this.worldGenSeed;
      return i;
   }

   public abstract int[] getInts(int var1, int var2, int var3, int var4);

   protected static boolean biomesEqualOrMesaPlateau(int biomeIDA, int biomeIDB) {
      if (biomeIDA == biomeIDB) {
         return true;
      } else {
         Zi biome = Zi.getBiome(biomeIDA);
         Zi biome1 = Zi.getBiome(biomeIDB);
         if (biome != null && biome1 != null) {
            if (biome != Nj.MESA_ROCK && biome != Nj.MESA_CLEAR_ROCK) {
               return biome == biome1 || biome.getBiomeClass() == biome1.getBiomeClass();
            } else {
               return biome1 == Nj.MESA_ROCK || biome1 == Nj.MESA_CLEAR_ROCK;
            }
         } else {
            return false;
         }
      }
   }

   protected static boolean isBiomeOceanic(int p_151618_0_) {
      Zi biome = Zi.getBiome(p_151618_0_);
      return biome == Nj.OCEAN || biome == Nj.DEEP_OCEAN || biome == Nj.FROZEN_OCEAN;
   }

   protected int selectRandom(int... p_151619_1_) {
      return p_151619_1_[this.nextInt(p_151619_1_.length)];
   }

   protected int selectModeOrRandom(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
      if (p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_) {
         return p_151617_2_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_) {
         return p_151617_1_;
      } else if (p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_) {
         return p_151617_2_;
      } else if (p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_) {
         return p_151617_2_;
      } else {
         return p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_ ? p_151617_3_ : this.selectRandom(p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_);
      }
   }
}
