package neo;

public class bct extends bco {
   public bct(long p_i45475_1_, bco p_i45475_3_) {
      super(p_i45475_1_);
      this.parent = p_i45475_3_;
   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
      int[] aint1 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaHeight; ++i) {
         for(int j = 0; j < areaWidth; ++j) {
            this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
            int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
            if (!this.replaceBiomeEdgeIfNecessary(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.EXTREME_HILLS), Zi.getIdForBiome(Nj.EXTREME_HILLS_EDGE)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.MESA_ROCK), Zi.getIdForBiome(Nj.MESA)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.MESA_CLEAR_ROCK), Zi.getIdForBiome(Nj.MESA)) && !this.replaceBiomeEdge(aint, aint1, j, i, areaWidth, k, Zi.getIdForBiome(Nj.REDWOOD_TAIGA), Zi.getIdForBiome(Nj.TAIGA))) {
               int l;
               int i1;
               int j1;
               int k1;
               if (k == Zi.getIdForBiome(Nj.DESERT)) {
                  l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                  i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                  j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                  k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                  if (l != Zi.getIdForBiome(Nj.ICE_PLAINS) && i1 != Zi.getIdForBiome(Nj.ICE_PLAINS) && j1 != Zi.getIdForBiome(Nj.ICE_PLAINS) && k1 != Zi.getIdForBiome(Nj.ICE_PLAINS)) {
                     aint1[j + i * areaWidth] = k;
                  } else {
                     aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.EXTREME_HILLS_WITH_TREES);
                  }
               } else if (k == Zi.getIdForBiome(Nj.SWAMPLAND)) {
                  l = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                  i1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                  j1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                  k1 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                  if (l != Zi.getIdForBiome(Nj.DESERT) && i1 != Zi.getIdForBiome(Nj.DESERT) && j1 != Zi.getIdForBiome(Nj.DESERT) && k1 != Zi.getIdForBiome(Nj.DESERT) && l != Zi.getIdForBiome(Nj.COLD_TAIGA) && i1 != Zi.getIdForBiome(Nj.COLD_TAIGA) && j1 != Zi.getIdForBiome(Nj.COLD_TAIGA) && k1 != Zi.getIdForBiome(Nj.COLD_TAIGA) && l != Zi.getIdForBiome(Nj.ICE_PLAINS) && i1 != Zi.getIdForBiome(Nj.ICE_PLAINS) && j1 != Zi.getIdForBiome(Nj.ICE_PLAINS) && k1 != Zi.getIdForBiome(Nj.ICE_PLAINS)) {
                     if (l != Zi.getIdForBiome(Nj.JUNGLE) && k1 != Zi.getIdForBiome(Nj.JUNGLE) && i1 != Zi.getIdForBiome(Nj.JUNGLE) && j1 != Zi.getIdForBiome(Nj.JUNGLE)) {
                        aint1[j + i * areaWidth] = k;
                     } else {
                        aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.JUNGLE_EDGE);
                     }
                  } else {
                     aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.PLAINS);
                  }
               } else {
                  aint1[j + i * areaWidth] = k;
               }
            }
         }
      }

      return aint1;
   }

   private boolean replaceBiomeEdgeIfNecessary(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_) {
      if (!biomesEqualOrMesaPlateau(p_151636_6_, p_151636_7_)) {
         return false;
      } else {
         int i = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
         int j = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int k = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int l = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];
         if (this.canBiomesBeNeighbors(i, p_151636_7_) && this.canBiomesBeNeighbors(j, p_151636_7_) && this.canBiomesBeNeighbors(k, p_151636_7_) && this.canBiomesBeNeighbors(l, p_151636_7_)) {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
         } else {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
         }

         return true;
      }
   }

   private boolean replaceBiomeEdge(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
      if (p_151635_6_ != p_151635_7_) {
         return false;
      } else {
         int i = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
         int j = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int k = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int l = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];
         if (biomesEqualOrMesaPlateau(i, p_151635_7_) && biomesEqualOrMesaPlateau(j, p_151635_7_) && biomesEqualOrMesaPlateau(k, p_151635_7_) && biomesEqualOrMesaPlateau(l, p_151635_7_)) {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
         } else {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
         }

         return true;
      }
   }

   private boolean canBiomesBeNeighbors(int p_151634_1_, int p_151634_2_) {
      if (biomesEqualOrMesaPlateau(p_151634_1_, p_151634_2_)) {
         return true;
      } else {
         Zi biome = Zi.getBiome(p_151634_1_);
         Zi biome1 = Zi.getBiome(p_151634_2_);
         if (biome != null && biome1 != null) {
            Zh biome$tempcategory = biome.getTempCategory();
            Zh biome$tempcategory1 = biome1.getTempCategory();
            return biome$tempcategory == biome$tempcategory1 || biome$tempcategory == Zh.MEDIUM || biome$tempcategory1 == Zh.MEDIUM;
         } else {
            return false;
         }
      }
   }
}
