package neo;

class Mt<T extends Ig> extends Gh<T> {
   private final Mu wolf;
   // $FF: synthetic field
   final Mu this$0;

   public Mt(Mu this$0, Mu wolfIn, Class p_i47251_3_, float p_i47251_4_, double p_i47251_5_, double p_i47251_7_) {
      super(wolfIn, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
      this.this$0 = this$0;
      this.wolf = wolfIn;
   }

   public boolean shouldExecute() {
      if (super.shouldExecute() && this.closestLivingEntity instanceof LK) {
         return !this.wolf.isTamed() && this.avoidLlama((LK)this.closestLivingEntity);
      } else {
         return false;
      }
   }

   private boolean avoidLlama(LK p_190854_1_) {
      return p_190854_1_.getStrength() >= Mu.access$000(this.this$0).nextInt(5);
   }

   public void startExecuting() {
      this.this$0.setAttackTarget((Iw)null);
      super.startExecuting();
   }

   public void updateTask() {
      this.this$0.setAttackTarget((Iw)null);
      super.updateTask();
   }
}
