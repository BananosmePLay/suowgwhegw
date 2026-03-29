package neo;

class KL extends Gi {
   private final KN slime;

   public KL(KN slimeIn) {
      this.slime = slimeIn;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      return true;
   }

   public void updateTask() {
      ((KM)this.slime.getMoveHelper()).setSpeed(1.0);
   }
}
