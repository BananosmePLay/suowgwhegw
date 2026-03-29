package neo;

class Kt extends GX {
   // $FF: synthetic field
   final Kv this$0;

   public Kt(Kv this$0) {
      super(this$0, 2.0);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      return !this.this$0.isChild() && !this.this$0.isBurning() ? false : super.shouldExecute();
   }
}
