package neo;

public class baB {
   private final int yBase;
   private int blockRefCount;
   private int tickRefCount;
   private final bag data;
   private bas blockLight;
   private bas skyLight;

   public baB(int y, boolean storeSkylight) {
      this.yBase = y;
      this.data = new bag();
      this.blockLight = new bas();
      if (storeSkylight) {
         this.skyLight = new bas();
      }

   }

   public in get(int x, int y, int z) {
      return this.data.get(x, y, z);
   }

   public void set(int x, int y, int z, in state) {
      if (bnK.IExtendedBlockState.isInstance(state)) {
         state = (in)bnK.call(state, bnK.IExtendedBlockState_getClean);
      }

      in iblockstate = this.get(x, y, z);
      co block = iblockstate.getBlock();
      co block1 = state.getBlock();
      if (block != Nk.AIR) {
         --this.blockRefCount;
         if (block.getTickRandomly()) {
            --this.tickRefCount;
         }
      }

      if (block1 != Nk.AIR) {
         ++this.blockRefCount;
         if (block1.getTickRandomly()) {
            ++this.tickRefCount;
         }
      }

      this.data.set(x, y, z, state);
   }

   public boolean isEmpty() {
      return this.blockRefCount == 0;
   }

   public boolean needsRandomTick() {
      return this.tickRefCount > 0;
   }

   public int getYLocation() {
      return this.yBase;
   }

   public void setSkyLight(int x, int y, int z, int value) {
      this.skyLight.set(x, y, z, value);
   }

   public int getSkyLight(int x, int y, int z) {
      return this.skyLight.get(x, y, z);
   }

   public void setBlockLight(int x, int y, int z, int value) {
      this.blockLight.set(x, y, z, value);
   }

   public int getBlockLight(int x, int y, int z) {
      return this.blockLight.get(x, y, z);
   }

   public void recalculateRefCounts() {
      in iblockstate = Nk.AIR.getDefaultState();
      int i = 0;
      int j = 0;

      for(int k = 0; k < 16; ++k) {
         for(int l = 0; l < 16; ++l) {
            for(int i1 = 0; i1 < 16; ++i1) {
               in iblockstate1 = this.data.get(i1, k, l);
               if (iblockstate1 != iblockstate) {
                  ++i;
                  co block = iblockstate1.getBlock();
                  if (block.getTickRandomly()) {
                     ++j;
                  }
               }
            }
         }
      }

      this.blockRefCount = i;
      this.tickRefCount = j;
   }

   public bag getData() {
      return this.data;
   }

   public bas getBlockLight() {
      return this.blockLight;
   }

   public bas getSkyLight() {
      return this.skyLight;
   }

   public void setBlockLight(bas newBlocklightArray) {
      this.blockLight = newBlocklightArray;
   }

   public void setSkyLight(bas newSkylightArray) {
      this.skyLight = newSkylightArray;
   }

   public int getBlockRefCount() {
      return this.blockRefCount;
   }
}
