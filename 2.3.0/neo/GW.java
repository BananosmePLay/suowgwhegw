package neo;

public class GW extends Hf {
   Mg tameable;
   Iw attacker;
   private int timestamp;

   public GW(Mg theEntityTameableIn) {
      super(theEntityTameableIn, false);
      this.tameable = theEntityTameableIn;
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
            this.attacker = entitylivingbase.getLastAttackedEntity();
            int i = entitylivingbase.getLastAttackedEntityTime();
            return i != this.timestamp && this.isSuitableTarget(this.attacker, false) && this.tameable.shouldAttackEntity(this.attacker, entitylivingbase);
         }
      }
   }

   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.attacker);
      Iw entitylivingbase = this.tameable.getOwner();
      if (entitylivingbase != null) {
         this.timestamp = entitylivingbase.getLastAttackedEntityTime();
      }

      super.startExecuting();
   }
}
