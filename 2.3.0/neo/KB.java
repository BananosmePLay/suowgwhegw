package neo;

class KB extends Gi {
   private int peekTime;
   // $FF: synthetic field
   final KD this$0;

   private KB(KD this$0) {
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      return this.this$0.getAttackTarget() == null && KD.access$400(this.this$0).nextInt(40) == 0;
   }

   public boolean shouldContinueExecuting() {
      return this.this$0.getAttackTarget() == null && this.peekTime > 0;
   }

   public void startExecuting() {
      this.peekTime = 20 * (1 + KD.access$500(this.this$0).nextInt(3));
      this.this$0.updateArmorModifier(30);
   }

   public void resetTask() {
      if (this.this$0.getAttackTarget() == null) {
         this.this$0.updateArmorModifier(0);
      }

   }

   public void updateTask() {
      --this.peekTime;
   }

   // $FF: synthetic method
   KB(KD x0, Object x1) {
      this(x0);
   }
}
