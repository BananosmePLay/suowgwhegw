package neo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bcz extends bco {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bco riverLayer;

   public bcz(long p_i45479_1_, bco p_i45479_3_, bco p_i45479_4_) {
      super(p_i45479_1_);
      this.parent = p_i45479_3_;
      this.riverLayer = p_i45479_4_;
   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
      int[] aint1 = this.riverLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
      int[] aint2 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaHeight; ++i) {
         for(int j = 0; j < areaWidth; ++j) {
            this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
            int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
            int l = aint1[j + 1 + (i + 1) * (areaWidth + 2)];
            boolean flag = (l - 2) % 29 == 0;
            if (k > 255) {
               LOGGER.debug("old! {}", k);
            }

            Zi biome = Zi.getBiomeForId(k);
            boolean flag1 = biome != null && biome.isMutation();
            Zi biome1;
            if (k != 0 && l >= 2 && (l - 2) % 29 == 1 && !flag1) {
               biome1 = Zi.getMutationForBiome(biome);
               aint2[j + i * areaWidth] = biome1 == null ? k : Zi.getIdForBiome(biome1);
            } else if (this.nextInt(3) != 0 && !flag) {
               aint2[j + i * areaWidth] = k;
            } else {
               biome1 = biome;
               int i1;
               if (biome == Nj.DESERT) {
                  biome1 = Nj.DESERT_HILLS;
               } else if (biome == Nj.FOREST) {
                  biome1 = Nj.FOREST_HILLS;
               } else if (biome == Nj.BIRCH_FOREST) {
                  biome1 = Nj.BIRCH_FOREST_HILLS;
               } else if (biome == Nj.ROOFED_FOREST) {
                  biome1 = Nj.PLAINS;
               } else if (biome == Nj.TAIGA) {
                  biome1 = Nj.TAIGA_HILLS;
               } else if (biome == Nj.REDWOOD_TAIGA) {
                  biome1 = Nj.REDWOOD_TAIGA_HILLS;
               } else if (biome == Nj.COLD_TAIGA) {
                  biome1 = Nj.COLD_TAIGA_HILLS;
               } else if (biome == Nj.PLAINS) {
                  if (this.nextInt(3) == 0) {
                     biome1 = Nj.FOREST_HILLS;
                  } else {
                     biome1 = Nj.FOREST;
                  }
               } else if (biome == Nj.ICE_PLAINS) {
                  biome1 = Nj.ICE_MOUNTAINS;
               } else if (biome == Nj.JUNGLE) {
                  biome1 = Nj.JUNGLE_HILLS;
               } else if (biome == Nj.OCEAN) {
                  biome1 = Nj.DEEP_OCEAN;
               } else if (biome == Nj.EXTREME_HILLS) {
                  biome1 = Nj.EXTREME_HILLS_WITH_TREES;
               } else if (biome == Nj.SAVANNA) {
                  biome1 = Nj.SAVANNA_PLATEAU;
               } else if (biomesEqualOrMesaPlateau(k, Zi.getIdForBiome(Nj.MESA_ROCK))) {
                  biome1 = Nj.MESA;
               } else if (biome == Nj.DEEP_OCEAN && this.nextInt(3) == 0) {
                  i1 = this.nextInt(2);
                  if (i1 == 0) {
                     biome1 = Nj.PLAINS;
                  } else {
                     biome1 = Nj.FOREST;
                  }
               }

               i1 = Zi.getIdForBiome(biome1);
               if (flag && i1 != k) {
                  Zi biome2 = Zi.getMutationForBiome(biome1);
                  i1 = biome2 == null ? k : Zi.getIdForBiome(biome2);
               }

               if (i1 == k) {
                  aint2[j + i * areaWidth] = k;
               } else {
                  int k2 = aint[j + 1 + (i + 0) * (areaWidth + 2)];
                  int j1 = aint[j + 2 + (i + 1) * (areaWidth + 2)];
                  int k1 = aint[j + 0 + (i + 1) * (areaWidth + 2)];
                  int l1 = aint[j + 1 + (i + 2) * (areaWidth + 2)];
                  int i2 = 0;
                  if (biomesEqualOrMesaPlateau(k2, k)) {
                     ++i2;
                  }

                  if (biomesEqualOrMesaPlateau(j1, k)) {
                     ++i2;
                  }

                  if (biomesEqualOrMesaPlateau(k1, k)) {
                     ++i2;
                  }

                  if (biomesEqualOrMesaPlateau(l1, k)) {
                     ++i2;
                  }

                  if (i2 >= 3) {
                     aint2[j + i * areaWidth] = i1;
                  } else {
                     aint2[j + i * areaWidth] = k;
                  }
               }
            }
         }
      }

      return aint2;
   }
}
