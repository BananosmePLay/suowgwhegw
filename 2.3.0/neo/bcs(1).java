package neo;

public class bcs extends bco {
   private Zi[] warmBiomes;
   private final Zi[] mediumBiomes;
   private final Zi[] coldBiomes;
   private final Zi[] iceBiomes;
   private final bbk settings;

   public bcs(long p_i45560_1_, bco p_i45560_3_, bix p_i45560_4_, bbk p_i45560_5_) {
      super(p_i45560_1_);
      this.warmBiomes = new Zi[]{Nj.DESERT, Nj.DESERT, Nj.DESERT, Nj.SAVANNA, Nj.SAVANNA, Nj.PLAINS};
      this.mediumBiomes = new Zi[]{Nj.FOREST, Nj.ROOFED_FOREST, Nj.EXTREME_HILLS, Nj.PLAINS, Nj.BIRCH_FOREST, Nj.SWAMPLAND};
      this.coldBiomes = new Zi[]{Nj.FOREST, Nj.EXTREME_HILLS, Nj.TAIGA, Nj.PLAINS};
      this.iceBiomes = new Zi[]{Nj.ICE_PLAINS, Nj.ICE_PLAINS, Nj.ICE_PLAINS, Nj.COLD_TAIGA};
      this.parent = p_i45560_3_;
      if (p_i45560_4_ == bix.DEFAULT_1_1) {
         this.warmBiomes = new Zi[]{Nj.DESERT, Nj.FOREST, Nj.EXTREME_HILLS, Nj.SWAMPLAND, Nj.PLAINS, Nj.TAIGA};
         this.settings = null;
      } else {
         this.settings = p_i45560_5_;
      }

   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
      int[] aint1 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaHeight; ++i) {
         for(int j = 0; j < areaWidth; ++j) {
            this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
            int k = aint[j + i * areaWidth];
            int l = (k & 3840) >> 8;
            k &= -3841;
            if (this.settings != null && this.settings.fixedBiome >= 0) {
               aint1[j + i * areaWidth] = this.settings.fixedBiome;
            } else if (isBiomeOceanic(k)) {
               aint1[j + i * areaWidth] = k;
            } else if (k == Zi.getIdForBiome(Nj.MUSHROOM_ISLAND)) {
               aint1[j + i * areaWidth] = k;
            } else if (k == 1) {
               if (l > 0) {
                  if (this.nextInt(3) == 0) {
                     aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.MESA_CLEAR_ROCK);
                  } else {
                     aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.MESA_ROCK);
                  }
               } else {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(this.warmBiomes[this.nextInt(this.warmBiomes.length)]);
               }
            } else if (k == 2) {
               if (l > 0) {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.JUNGLE);
               } else {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(this.mediumBiomes[this.nextInt(this.mediumBiomes.length)]);
               }
            } else if (k == 3) {
               if (l > 0) {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.REDWOOD_TAIGA);
               } else {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(this.coldBiomes[this.nextInt(this.coldBiomes.length)]);
               }
            } else if (k == 4) {
               aint1[j + i * areaWidth] = Zi.getIdForBiome(this.iceBiomes[this.nextInt(this.iceBiomes.length)]);
            } else {
               aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.MUSHROOM_ISLAND);
            }
         }
      }

      return aint1;
   }
}
