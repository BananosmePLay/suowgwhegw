package neo;

public class bcB extends bco {
   public bcB(long p_i45478_1_, bco p_i45478_3_) {
      super(p_i45478_1_);
      this.parent = p_i45478_3_;
   }

   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
      int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
      int[] aint1 = bcK.getIntCache(areaWidth * areaHeight);

      for(int i = 0; i < areaHeight; ++i) {
         for(int j = 0; j < areaWidth; ++j) {
            this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
            int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
            if (this.nextInt(57) == 0) {
               if (k == Zi.getIdForBiome(Nj.PLAINS)) {
                  aint1[j + i * areaWidth] = Zi.getIdForBiome(Nj.MUTATED_PLAINS);
               } else {
                  aint1[j + i * areaWidth] = k;
               }
            } else {
               aint1[j + i * areaWidth] = k;
            }
         }
      }

      return aint1;
   }
}
