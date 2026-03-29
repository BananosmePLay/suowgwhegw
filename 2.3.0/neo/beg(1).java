package neo;

class beg {
   public Class<? extends bef> weightClass;
   public final int weight;
   public int placeCount;
   public int maxPlaceCount;
   public boolean allowInRow;

   public beg(Class<? extends bef> p_i2055_1_, int p_i2055_2_, int p_i2055_3_, boolean p_i2055_4_) {
      this.weightClass = p_i2055_1_;
      this.weight = p_i2055_2_;
      this.maxPlaceCount = p_i2055_3_;
      this.allowInRow = p_i2055_4_;
   }

   public beg(Class<? extends bef> p_i2056_1_, int p_i2056_2_, int p_i2056_3_) {
      this(p_i2056_1_, p_i2056_2_, p_i2056_3_, false);
   }

   public boolean doPlace(int p_78822_1_) {
      return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
   }

   public boolean isValid() {
      return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
   }
}
