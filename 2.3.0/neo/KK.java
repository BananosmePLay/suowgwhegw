package neo;

class KK extends Gi {
   private final KN slime;

   public KK(KN slimeIn) {
      this.slime = slimeIn;
      this.setMutexBits(5);
      ((VO)slimeIn.getNavigator()).setCanSwim(true);
   }

   public boolean shouldExecute() {
      return this.slime.isInWater() || this.slime.isInLava();
   }

   public void updateTask() {
      if (this.slime.getRNG().nextFloat() < 0.8F) {
         this.slime.getJumpHelper().setJumping();
      }

      ((KM)this.slime.getMoveHelper()).setSpeed(1.2);
   }
}
