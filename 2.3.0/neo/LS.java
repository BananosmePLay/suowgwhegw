package neo;

class LS extends Gd {
   public LS(LY rabbit) {
      super(rabbit, 1.4, true);
   }

   protected double getAttackReachSqr(Iw attackTarget) {
      return (double)(4.0F + attackTarget.width);
   }
}
