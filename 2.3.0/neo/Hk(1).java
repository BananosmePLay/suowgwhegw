package neo;

public class Hk extends Gi {
   private final Mq villager;

   public Hk(Mq villagerIn) {
      this.villager = villagerIn;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      if (!this.villager.isEntityAlive()) {
         return false;
      } else if (this.villager.isInWater()) {
         return false;
      } else if (!this.villager.onGround) {
         return false;
      } else if (this.villager.velocityChanged) {
         return false;
      } else {
         ME entityplayer = this.villager.getCustomer();
         if (entityplayer == null) {
            return false;
         } else if (this.villager.getDistanceSq(entityplayer) > 16.0) {
            return false;
         } else {
            return entityplayer.openContainer != null;
         }
      }
   }

   public void startExecuting() {
      this.villager.getNavigator().clearPath();
   }

   public void resetTask() {
      this.villager.setCustomer((ME)null);
   }
}
