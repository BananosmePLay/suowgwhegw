package neo;

public class Hc extends Gi {
   private final Mg tameable;
   private boolean isSitting;

   public Hc(Mg entityIn) {
      this.tameable = entityIn;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      if (!this.tameable.isTamed()) {
         return false;
      } else if (this.tameable.isInWater()) {
         return false;
      } else if (!this.tameable.onGround) {
         return false;
      } else {
         Iw entitylivingbase = this.tameable.getOwner();
         if (entitylivingbase == null) {
            return true;
         } else {
            return this.tameable.getDistanceSq(entitylivingbase) < 144.0 && entitylivingbase.getRevengeTarget() != null ? false : this.isSitting;
         }
      }
   }

   public void startExecuting() {
      this.tameable.getNavigator().clearPath();
      this.tameable.setSitting(true);
   }

   public void resetTask() {
      this.tameable.setSitting(false);
   }

   public void setSitting(boolean sitting) {
      this.isSitting = sitting;
   }
}
