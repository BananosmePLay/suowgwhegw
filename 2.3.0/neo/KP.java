package neo;

public class KP extends Gi {
   // $FF: synthetic field
   final KS this$0;

   public KP(KS this$0) {
      this.this$0 = this$0;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      return this.this$0.getSpellTicks() > 0;
   }

   public void startExecuting() {
      super.startExecuting();
      KS.access$200(this.this$0).clearPath();
   }

   public void resetTask() {
      super.resetTask();
      this.this$0.setSpellType(KR.NONE);
   }

   public void updateTask() {
      if (this.this$0.getAttackTarget() != null) {
         this.this$0.getLookHelper().setLookPositionWithEntity(this.this$0.getAttackTarget(), (float)this.this$0.getHorizontalFaceSpeed(), (float)this.this$0.getVerticalFaceSpeed());
      }

   }
}
