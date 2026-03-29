package neo;

import net.minecraft.util.ResourceLocation;

public class bA extends m {
   private final bl location;

   public bA(ResourceLocation criterionIn, bl location) {
      super(criterionIn);
      this.location = location;
   }

   public boolean test(bis world, double x, double y, double z) {
      return this.location.test(world, x, y, z);
   }
}
