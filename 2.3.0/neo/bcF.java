package neo;

public class bcF extends bco {
   private final bco biomePatternGeneratorChain;
   private final bco riverPatternGeneratorChain;

   public bcF(long p_i2129_1_, bco p_i2129_3_, bco p_i2129_4_) {
      super(p_i2129_1_);
      this.biomePatternGeneratorChain = p_i2129_3_;
      this.riverPatternGeneratorChain = p_i2129_4_;
   }

   public void initWorldGenSeed(long seed) {
      this.biomePatternGeneratorChain.initWorldGenSeed(seed);
      this.riverPatternGeneratorChain.initWorldGenSeed(seed);
      super.initWorldGenSeed(seed);
   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
      int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
      int[] aint2 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaWidth * areaHeight; ++i) {
         if (aint[i] != Zi.getIdForBiome(Nj.OCEAN) && aint[i] != Zi.getIdForBiome(Nj.DEEP_OCEAN)) {
            if (aint1[i] == Zi.getIdForBiome(Nj.RIVER)) {
               if (aint[i] == Zi.getIdForBiome(Nj.ICE_PLAINS)) {
                  aint2[i] = Zi.getIdForBiome(Nj.FROZEN_RIVER);
               } else if (aint[i] != Zi.getIdForBiome(Nj.MUSHROOM_ISLAND) && aint[i] != Zi.getIdForBiome(Nj.MUSHROOM_ISLAND_SHORE)) {
                  aint2[i] = aint1[i] & 255;
               } else {
                  aint2[i] = Zi.getIdForBiome(Nj.MUSHROOM_ISLAND_SHORE);
               }
            } else {
               aint2[i] = aint[i];
            }
         } else {
            aint2[i] = aint[i];
         }
      }

      return aint2;
   }
}
