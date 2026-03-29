package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GM extends Gi {
   private final Ik creature;
   private double movePosX;
   private double movePosY;
   private double movePosZ;
   private final double movementSpeed;

   public GM(Ik creatureIn, double speedIn) {
      this.creature = creatureIn;
      this.movementSpeed = speedIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (this.creature.isWithinHomeDistanceCurrentPosition()) {
         return false;
      } else {
         BlockPos blockpos = this.creature.getHomePosition();
         Vec3d vec3d = HA.findRandomTargetBlockTowards(this.creature, 16, 7, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()));
         if (vec3d == null) {
            return false;
         } else {
            this.movePosX = vec3d.x;
            this.movePosY = vec3d.y;
            this.movePosZ = vec3d.z;
            return true;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.creature.getNavigator().noPath();
   }

   public void startExecuting() {
      this.creature.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
   }
}
