package neo;

public class GU extends Gn {
   boolean closeDoor;
   int closeDoorTemporisation;

   public GU(Iu entitylivingIn, boolean shouldClose) {
      super(entitylivingIn);
      this.entity = entitylivingIn;
      this.closeDoor = shouldClose;
   }

   public boolean shouldContinueExecuting() {
      return this.closeDoor && this.closeDoorTemporisation > 0 && super.shouldContinueExecuting();
   }

   public void startExecuting() {
      this.closeDoorTemporisation = 20;
      this.doorBlock.toggleDoor(this.entity.world, this.doorPosition, true);
   }

   public void resetTask() {
      if (this.closeDoor) {
         this.doorBlock.toggleDoor(this.entity.world, this.doorPosition, false);
      }

   }

   public void updateTask() {
      --this.closeDoorTemporisation;
      super.updateTask();
   }
}
