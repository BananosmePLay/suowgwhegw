package neo;

class Kr extends GB {
   // $FF: synthetic field
   final Kv this$0;

   public Kr(Kv this$0) {
      super(this$0, false);
      this.this$0 = this$0;
   }

   public void startExecuting() {
      super.startExecuting();
      if (this.this$0.isChild()) {
         this.alertOthers();
         this.resetTask();
      }

   }

   protected void setEntityAttackTarget(Ik creatureIn, Iw entityLivingBaseIn) {
      if (creatureIn instanceof Kv && !creatureIn.isChild()) {
         super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);
      }

   }
}
