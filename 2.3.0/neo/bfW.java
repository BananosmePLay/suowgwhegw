package neo;

class bfW {
   private final int[][] grid;
   private final int width;
   private final int height;
   private final int valueIfOutside;

   public bfW(int p_i47358_1_, int p_i47358_2_, int p_i47358_3_) {
      this.width = p_i47358_1_;
      this.height = p_i47358_2_;
      this.valueIfOutside = p_i47358_3_;
      this.grid = new int[p_i47358_1_][p_i47358_2_];
   }

   public void set(int p_191144_1_, int p_191144_2_, int p_191144_3_) {
      if (p_191144_1_ >= 0 && p_191144_1_ < this.width && p_191144_2_ >= 0 && p_191144_2_ < this.height) {
         this.grid[p_191144_1_][p_191144_2_] = p_191144_3_;
      }

   }

   public void set(int p_191142_1_, int p_191142_2_, int p_191142_3_, int p_191142_4_, int p_191142_5_) {
      for(int i = p_191142_2_; i <= p_191142_4_; ++i) {
         for(int j = p_191142_1_; j <= p_191142_3_; ++j) {
            this.set(j, i, p_191142_5_);
         }
      }

   }

   public int get(int p_191145_1_, int p_191145_2_) {
      return p_191145_1_ >= 0 && p_191145_1_ < this.width && p_191145_2_ >= 0 && p_191145_2_ < this.height ? this.grid[p_191145_1_][p_191145_2_] : this.valueIfOutside;
   }

   public void setIf(int p_191141_1_, int p_191141_2_, int p_191141_3_, int p_191141_4_) {
      if (this.get(p_191141_1_, p_191141_2_) == p_191141_3_) {
         this.set(p_191141_1_, p_191141_2_, p_191141_4_);
      }

   }

   public boolean edgesTo(int p_191147_1_, int p_191147_2_, int p_191147_3_) {
      return this.get(p_191147_1_ - 1, p_191147_2_) == p_191147_3_ || this.get(p_191147_1_ + 1, p_191147_2_) == p_191147_3_ || this.get(p_191147_1_, p_191147_2_ + 1) == p_191147_3_ || this.get(p_191147_1_, p_191147_2_ - 1) == p_191147_3_;
   }

   // $FF: synthetic method
   static int access$100(bfW x0) {
      return x0.width;
   }

   // $FF: synthetic method
   static int access$200(bfW x0) {
      return x0.height;
   }
}
