package neo;

public class bcG extends bco {
   public bcG(long p_i2130_1_, bco p_i2130_3_) {
      super(p_i2130_1_);
      this.parent = p_i2130_3_;
   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
      int[] aint1 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaHeight; ++i) {
         for(int j = 0; j < areaWidth; ++j) {
            this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
            int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
            Zi biome = Zi.getBiome(k);
            int l;
            int i1;
            int j1;
            int k1;
            if (k == Zi.getIdForBiome(Nj.MUSHROOM_ISLAND)) {
               l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
               i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
               j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
               k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
               if (l != Zi.getIdForBiome(Nj.OCEAN) && i1 != Zi.getIdForBiome(Nj.OCEAN) && j1 != Zi.getIdForBiome(Nj.OCEAN) && k1 != Zi.getIdForBiome(Nj.OCEAN)) {
                  aint1[j + i * areaWidth] = k;
               } else {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.MUSHROOM_ISLAND_SHORE);
               }
            } else if (biome != null && biome.getBiomeClass() == ZE.class) {
               l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
               i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
               j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
               k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
               if (this.isJungleCompatible(l) && this.isJungleCompatible(i1) && this.isJungleCompatible(j1) && this.isJungleCompatible(k1)) {
                  if (!isBiomeOceanic(l) && !isBiomeOceanic(i1) && !isBiomeOceanic(j1) && !isBiomeOceanic(k1)) {
                     aint1[j + i * areaWidth] = k;
                  } else {
                     aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.BEACH);
                  }
               } else {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.JUNGLE_EDGE);
               }
            } else if (k != Zi.getIdForBiome(Nj.EXTREME_HILLS) && k != Zi.getIdForBiome(Nj.EXTREME_HILLS_WITH_TREES) && k != Zi.getIdForBiome(Nj.EXTREME_HILLS_EDGE)) {
               if (biome != null && biome.isSnowyBiome()) {
                  this.replaceIfNeighborOcean(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.COLD_BEACH));
               } else if (k != Zi.getIdForBiome(Nj.MESA) && k != Zi.getIdForBiome(Nj.MESA_ROCK)) {
                  if (k != Zi.getIdForBiome(Nj.OCEAN) && k != Zi.getIdForBiome(Nj.DEEP_OCEAN) && k != Zi.getIdForBiome(Nj.RIVER) && k != Zi.getIdForBiome(Nj.SWAMPLAND)) {
                     l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                     i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                     j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                     k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                     if (!isBiomeOceanic(l) && !isBiomeOceanic(i1) && !isBiomeOceanic(j1) && !isBiomeOceanic(k1)) {
                        aint1[j + i * areaWidth] = k;
                     } else {
                        aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.BEACH);
                     }
                  } else {
                     aint1[j + i * areaWidth] = k;
                  }
               } else {
                  l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                  i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                  j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                  k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                  if (!isBiomeOceanic(l) && !isBiomeOceanic(i1) && !isBiomeOceanic(j1) && !isBiomeOceanic(k1)) {
                     if (this.isMesa(l) && this.isMesa(i1) && this.isMesa(j1) && this.isMesa(k1)) {
                        aint1[j + i * areaWidth] = k;
                     } else {
                        aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.DESERT);
                     }
                  } else {
                     aint1[j + i * areaWidth] = k;
                  }
               }
            } else {
               this.replaceIfNeighborOcean(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.STONE_BEACH));
            }
         }
      }

      return aint1;
   }

   private void replaceIfNeighborOcean(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_) {
      if (isBiomeOceanic(p_151632_6_)) {
         p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
      } else {
         int i = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
         int j = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
         int k = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
         int l = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];
         if (!isBiomeOceanic(i) && !isBiomeOceanic(j) && !isBiomeOceanic(k) && !isBiomeOceanic(l)) {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
         } else {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
         }
      }

   }

   private boolean isJungleCompatible(int p_151631_1_) {
      if (Zi.getBiome(p_151631_1_) != null && Zi.getBiome(p_151631_1_).getBiomeClass() == ZE.class) {
         return true;
      } else {
         return p_151631_1_ == Zi.getIdForBiome(Nj.JUNGLE_EDGE) || p_151631_1_ == Zi.getIdForBiome(Nj.JUNGLE) || p_151631_1_ == Zi.getIdForBiome(Nj.JUNGLE_HILLS) || p_151631_1_ == Zi.getIdForBiome(Nj.FOREST) || p_151631_1_ == Zi.getIdForBiome(Nj.TAIGA) || isBiomeOceanic(p_151631_1_);
      }
   }

   private boolean isMesa(int p_151633_1_) {
      return Zi.getBiome(p_151633_1_) instanceof ZH;
   }
}
