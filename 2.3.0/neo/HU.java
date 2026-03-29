package neo;

class HU extends Gi {
   // $FF: synthetic field
   final HV this$0;

   public HU(HV this$0) {
      this.this$0 = this$0;
      this.setMutexBits(7);
   }

   public boolean shouldExecute() {
      return this.this$0.getInvulTime() > 0;
   }
}
