package neo;

class KT extends Gd {
   public KT(KW spider) {
      super(spider, 1.0, true);
   }

   public boolean shouldContinueExecuting() {
      float f = this.attacker.getBrightness();
      if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
         this.attacker.setAttackTarget((Iw)null);
         return false;
      } else {
         return super.shouldContinueExecuting();
      }
   }

   protected double getAttackReachSqr(Iw attackTarget) {
      return (double)(4.0F + attackTarget.width);
   }
}
