package neo;

public class He extends Gi {
   private final Iu entity;

   public He(Iu entityIn) {
      this.entity = entityIn;
      this.setMutexBits(4);
      if (entityIn.getNavigator() instanceof VO) {
         ((VO)entityIn.getNavigator()).setCanSwim(true);
      } else if (entityIn.getNavigator() instanceof VN) {
         ((VN)entityIn.getNavigator()).setCanFloat(true);
      }

   }

   public boolean shouldExecute() {
      return this.entity.isInWater() || this.entity.isInLava();
   }

   public void updateTask() {
      if (this.entity.getRNG().nextFloat() < 0.8F) {
         this.entity.getJumpHelper().setJumping();
      }

   }
}
