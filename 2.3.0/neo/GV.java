package neo;

public class GV extends Hf {
   Mg tameable;
   Iw attacker;
   private int timestamp;

   public GV(Mg theDefendingTameableIn) {
      super(theDefendingTameableIn, false);
      this.tameable = theDefendingTameableIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (!this.tameable.isTamed()) {
         return false;
      } else {
         Iw entitylivingbase = this.tameable.getOwner();
         if (entitylivingbase == null) {
            return false;
         } else {
            this.attacker = entitylivingbase.getRevengeTarget();
            int i = entitylivingbase.getRevengeTimer();
            return i != this.timestamp && this.isSuitableTarget(this.attacker, false) && this.tameable.shouldAttackEntity(this.attacker, entitylivingbase);
         }
      }
   }

   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.attacker);
      Iw entitylivingbase = this.tameable.getOwner();
      if (entitylivingbase != null) {
         this.timestamp = entitylivingbase.getRevengeTimer();
      }

      super.startExecuting();
   }
}
