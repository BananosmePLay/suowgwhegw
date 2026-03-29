package neo;

class JN extends KP {
   // $FF: synthetic field
   final JR this$0;

   private JN(JR this$0) {
      super(this$0);
      this.this$0 = this$0;
   }

   public void updateTask() {
      if (this.this$0.getAttackTarget() != null) {
         this.this$0.getLookHelper().setLookPositionWithEntity(this.this$0.getAttackTarget(), (float)this.this$0.getHorizontalFaceSpeed(), (float)this.this$0.getVerticalFaceSpeed());
      } else if (JR.access$300(this.this$0) != null) {
         this.this$0.getLookHelper().setLookPositionWithEntity(JR.access$300(this.this$0), (float)this.this$0.getHorizontalFaceSpeed(), (float)this.this$0.getVerticalFaceSpeed());
      }

   }

   // $FF: synthetic method
   JN(JR x0, Object x1) {
      this(x0);
   }
}
