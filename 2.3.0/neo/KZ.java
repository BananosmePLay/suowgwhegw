package neo;

class KZ extends Hf {
   // $FF: synthetic field
   final Lc this$0;

   public KZ(Lc this$0, Ik creature) {
      super(creature, false);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      return Lc.access$300(this.this$0) != null && Lc.access$300(this.this$0).getAttackTarget() != null && this.isSuitableTarget(Lc.access$300(this.this$0).getAttackTarget(), false);
   }

   public void startExecuting() {
      this.this$0.setAttackTarget(Lc.access$300(this.this$0).getAttackTarget());
      super.startExecuting();
   }
}
