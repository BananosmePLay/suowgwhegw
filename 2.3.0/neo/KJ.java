package neo;

class KJ extends Gi {
   private final KN slime;
   private float chosenDegrees;
   private int nextRandomizeTime;

   public KJ(KN slimeIn) {
      this.slime = slimeIn;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.isPotionActive(NL.LEVITATION));
   }

   public void updateTask() {
      if (--this.nextRandomizeTime <= 0) {
         this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
         this.chosenDegrees = (float)this.slime.getRNG().nextInt(360);
      }

      ((KM)this.slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
   }
}
