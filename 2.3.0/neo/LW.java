package neo;

class LW extends Hx {
   private final LY rabbit;
   private double nextJumpSpeed;

   public LW(LY rabbit) {
      super(rabbit);
      this.rabbit = rabbit;
   }

   public void onUpdateMoveHelper() {
      if (this.rabbit.onGround && !LY.access$100(this.rabbit) && !((LV)LY.access$200(this.rabbit)).getIsJumping()) {
         this.rabbit.setMovementSpeed(0.0);
      } else if (this.isUpdating()) {
         this.rabbit.setMovementSpeed(this.nextJumpSpeed);
      }

      super.onUpdateMoveHelper();
   }

   public void setMoveTo(double x, double y, double z, double speedIn) {
      if (this.rabbit.isInWater()) {
         speedIn = 1.5;
      }

      super.setMoveTo(x, y, z, speedIn);
      if (speedIn > 0.0) {
         this.nextJumpSpeed = speedIn;
      }

   }
}
