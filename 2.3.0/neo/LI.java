package neo;

class LI extends GB {
   public LI(LK llama) {
      super(llama, false);
   }

   public boolean shouldContinueExecuting() {
      if (this.taskOwner instanceof LK) {
         LK entityllama = (LK)this.taskOwner;
         if (LK.access$100(entityllama)) {
            LK.access$200(entityllama, false);
            return false;
         }
      }

      return super.shouldContinueExecuting();
   }
}
