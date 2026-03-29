package neo;

public class GS extends Gi {
   bij world;
   Iu entity;
   Iw target;
   int attackCountdown;

   public GS(Iu theEntityIn) {
      this.entity = theEntityIn;
      this.world = theEntityIn.world;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.entity.getAttackTarget();
      if (entitylivingbase == null) {
         return false;
      } else {
         this.target = entitylivingbase;
         return true;
      }
   }

   public boolean shouldContinueExecuting() {
      if (!this.target.isEntityAlive()) {
         return false;
      } else if (this.entity.getDistanceSq(this.target) > 225.0) {
         return false;
      } else {
         return !this.entity.getNavigator().noPath() || this.shouldExecute();
      }
   }

   public void resetTask() {
      this.target = null;
      this.entity.getNavigator().clearPath();
   }

   public void updateTask() {
      this.entity.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
      double d0 = (double)(this.entity.width * 2.0F * this.entity.width * 2.0F);
      double d1 = this.entity.getDistanceSq(this.target.posX, this.target.getEntityBoundingBox().minY, this.target.posZ);
      double d2 = 0.8;
      if (d1 > d0 && d1 < 16.0) {
         d2 = 1.33;
      } else if (d1 < 225.0) {
         d2 = 0.6;
      }

      this.entity.getNavigator().tryMoveToEntityLiving(this.target, d2);
      this.attackCountdown = Math.max(this.attackCountdown - 1, 0);
      if (d1 <= d0 && this.attackCountdown <= 0) {
         this.attackCountdown = 20;
         this.entity.attackEntityAsMob(this.target);
      }

   }
}
