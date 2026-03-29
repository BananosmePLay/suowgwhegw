package neo;

public class GG extends Gi {
   private final Kj ironGolem;
   private Mq villager;
   private int lookTime;

   public GG(Kj ironGolemIn) {
      this.ironGolem = ironGolemIn;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      if (!this.ironGolem.world.isDaytime()) {
         return false;
      } else if (this.ironGolem.getRNG().nextInt(8000) != 0) {
         return false;
      } else {
         this.villager = (Mq)this.ironGolem.world.findNearestEntityWithinAABB(Mq.class, this.ironGolem.getEntityBoundingBox().grow(6.0, 2.0, 6.0), this.ironGolem);
         return this.villager != null;
      }
   }

   public boolean shouldContinueExecuting() {
      return this.lookTime > 0;
   }

   public void startExecuting() {
      this.lookTime = 400;
      this.ironGolem.setHoldingRose(true);
   }

   public void resetTask() {
      this.ironGolem.setHoldingRose(false);
      this.villager = null;
   }

   public void updateTask() {
      this.ironGolem.getLookHelper().setLookPositionWithEntity(this.villager, 30.0F, 30.0F);
      --this.lookTime;
   }
}
