package neo;

public abstract class Gi {
   private int mutexBits;

   public Gi() {
   }

   public abstract boolean shouldExecute();

   public boolean shouldContinueExecuting() {
      return this.shouldExecute();
   }

   public boolean isInterruptible() {
      return true;
   }

   public void startExecuting() {
   }

   public void resetTask() {
   }

   public void updateTask() {
   }

   public void setMutexBits(int mutexBitsIn) {
      this.mutexBits = mutexBitsIn;
   }

   public int getMutexBits() {
      return this.mutexBits;
   }
}
