package neo;

import com.google.common.collect.ComparisonChain;
import net.minecraft.util.WeightedRandom;

class ti extends WeightedRandom.Item implements Comparable<ti> {
   protected final sc model;

   public ti(sc modelIn, int itemWeightIn) {
      super(itemWeightIn);
      this.model = modelIn;
   }

   public int compareTo(ti p_compareTo_1_) {
      return ComparisonChain.start().compare(p_compareTo_1_.itemWeight, this.itemWeight).result();
   }

   public String toString() {
      return "MyWeighedRandomItem{weight=" + this.itemWeight + ", model=" + this.model + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((ti)var1);
   }
}
