package neo;

class Ks extends Gd {
   // $FF: synthetic field
   final Kv this$0;

   public Ks(Kv this$0) {
      super(this$0, 1.25, true);
      this.this$0 = this$0;
   }

   protected void checkAndPerformAttack(Iw enemy, double distToEnemySqr) {
      double d0 = this.getAttackReachSqr(enemy);
      if (distToEnemySqr <= d0 && this.attackTick <= 0) {
         this.attackTick = 20;
         this.attacker.attackEntityAsMob(enemy);
         this.this$0.setStanding(false);
      } else if (distToEnemySqr <= d0 * 2.0) {
         if (this.attackTick <= 0) {
            this.this$0.setStanding(false);
            this.attackTick = 20;
         }

         if (this.attackTick <= 10) {
            this.this$0.setStanding(true);
            this.this$0.playWarningSound();
         }
      } else {
         this.attackTick = 20;
         this.this$0.setStanding(false);
      }

   }

   public void resetTask() {
      this.this$0.setStanding(false);
      super.resetTask();
   }

   protected double getAttackReachSqr(Iw attackTarget) {
      return (double)(4.0F + attackTarget.width);
   }
}
