package neo;

import java.util.Random;
import net.minecraft.util.Tuple;

class Mo extends Tuple<Integer, Integer> {
   public Mo(int p_i45810_1_, int p_i45810_2_) {
      super(p_i45810_1_, p_i45810_2_);
      if (p_i45810_2_ < p_i45810_1_) {
         Mq.access$000().warn("PriceRange({}, {}) invalid, {} smaller than {}", p_i45810_1_, p_i45810_2_, p_i45810_2_, p_i45810_1_);
      }

   }

   public int getPrice(Random rand) {
      return (Integer)this.getFirst() >= (Integer)this.getSecond() ? (Integer)this.getFirst() : (Integer)this.getFirst() + rand.nextInt((Integer)this.getSecond() - (Integer)this.getFirst() + 1);
   }
}
