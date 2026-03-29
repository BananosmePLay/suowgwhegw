package neo;

import net.minecraft.util.EnumFacing;

class beC {
   int index;
   beC[] connections = new beC[6];
   boolean[] hasOpening = new boolean[6];
   boolean claimed;
   boolean isSource;
   int scanIndex;

   public beC(int p_i45584_1_) {
      this.index = p_i45584_1_;
   }

   public void setConnection(EnumFacing p_175957_1_, beC p_175957_2_) {
      this.connections[p_175957_1_.getIndex()] = p_175957_2_;
      p_175957_2_.connections[p_175957_1_.getOpposite().getIndex()] = this;
   }

   public void updateOpenings() {
      for(int i = 0; i < 6; ++i) {
         this.hasOpening[i] = this.connections[i] != null;
      }

   }

   public boolean findSource(int p_175959_1_) {
      if (this.isSource) {
         return true;
      } else {
         this.scanIndex = p_175959_1_;

         for(int i = 0; i < 6; ++i) {
            if (this.connections[i] != null && this.hasOpening[i] && this.connections[i].scanIndex != p_175959_1_ && this.connections[i].findSource(p_175959_1_)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isSpecial() {
      return this.index >= 75;
   }

   public int countOpenings() {
      int i = 0;

      for(int j = 0; j < 6; ++j) {
         if (this.hasOpening[j]) {
            ++i;
         }
      }

      return i;
   }
}
