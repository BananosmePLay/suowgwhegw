package neo;

import net.minecraft.util.math.Vec3d;

public class bq extends m {
   private final bl entered;
   private final bl exited;
   private final H distance;

   public bq(bl enteredIn, bl exitedIn, H distanceIn) {
      super(bs.access$000());
      this.entered = enteredIn;
      this.exited = exitedIn;
      this.distance = distanceIn;
   }

   public boolean test(bis world, Vec3d enteredNetherPosition, double x, double y, double z) {
      if (!this.entered.test(world, enteredNetherPosition.x, enteredNetherPosition.y, enteredNetherPosition.z)) {
         return false;
      } else {
         return !this.exited.test(world, x, y, z) ? false : this.distance.test(enteredNetherPosition.x, enteredNetherPosition.y, enteredNetherPosition.z, x, y, z);
      }
   }
}
