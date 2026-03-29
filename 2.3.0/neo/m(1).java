package neo;

import net.minecraft.util.ResourceLocation;

public class m implements cg {
   private final ResourceLocation criterion;

   public m(ResourceLocation criterionIn) {
      this.criterion = criterionIn;
   }

   public ResourceLocation getId() {
      return this.criterion;
   }

   public String toString() {
      return "AbstractCriterionInstance{criterion=" + this.criterion + '}';
   }
}
