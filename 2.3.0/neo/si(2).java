package neo;

class si {
   private final sj spanFacing;
   private int min;
   private int max;
   private final int anchor;

   public si(sj spanFacingIn, int p_i46216_2_, int p_i46216_3_) {
      this.spanFacing = spanFacingIn;
      this.min = p_i46216_2_;
      this.max = p_i46216_2_;
      this.anchor = p_i46216_3_;
   }

   public void expand(int p_178382_1_) {
      if (p_178382_1_ < this.min) {
         this.min = p_178382_1_;
      } else if (p_178382_1_ > this.max) {
         this.max = p_178382_1_;
      }

   }

   public sj getFacing() {
      return this.spanFacing;
   }

   public int getMin() {
      return this.min;
   }

   public int getMax() {
      return this.max;
   }

   public int getAnchor() {
      return this.anchor;
   }
}
