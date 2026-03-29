package neo;

import net.minecraft.util.math.Vec3d;

public class GN extends Gi {
   private final Ik creature;
   private Iw targetEntity;
   private double movePosX;
   private double movePosY;
   private double movePosZ;
   private final double speed;
   private final float maxTargetDistance;

   public GN(Ik creature, double speedIn, float targetMaxDistance) {
      this.creature = creature;
      this.speed = speedIn;
      this.maxTargetDistance = targetMaxDistance;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      this.targetEntity = this.creature.getAttackTarget();
      if (this.targetEntity == null) {
         return false;
      } else if (this.targetEntity.getDistanceSq(this.creature) > (double)(this.maxTargetDistance * this.maxTargetDistance)) {
         return false;
      } else {
         Vec3d vec3d = HA.findRandomTargetBlockTowards(this.creature, 16, 7, new Vec3d(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));
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
      return !this.creature.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSq(this.creature) < (double)(this.maxTargetDistance * this.maxTargetDistance);
   }

   public void resetTask() {
      this.targetEntity = null;
   }

   public void startExecuting() {
      this.creature.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
   }
}
