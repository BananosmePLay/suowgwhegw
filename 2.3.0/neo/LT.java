package neo;

class LT extends GX {
   private final LY rabbit;

   public LT(LY rabbit, double speedIn) {
      super(rabbit, speedIn);
      this.rabbit = rabbit;
   }

   public void updateTask() {
      super.updateTask();
      this.rabbit.setMovementSpeed(this.speed);
   }
}
