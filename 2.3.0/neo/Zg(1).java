package neo;

import net.minecraft.util.WeightedRandom;

public class Zg extends WeightedRandom.Item {
   public Class<? extends Iu> entityClass;
   public int minGroupCount;
   public int maxGroupCount;

   public Zg(Class<? extends Iu> entityclassIn, int weight, int groupCountMin, int groupCountMax) {
      super(weight);
      this.entityClass = entityclassIn;
      this.minGroupCount = groupCountMin;
      this.maxGroupCount = groupCountMax;
   }

   public String toString() {
      return this.entityClass.getSimpleName() + "*(" + this.minGroupCount + "-" + this.maxGroupCount + "):" + this.itemWeight;
   }
}
