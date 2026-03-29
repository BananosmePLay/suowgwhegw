package neo;

public class LV extends Hu {
   private final LY rabbit;
   private boolean canJump;
   // $FF: synthetic field
   final LY this$0;

   public LV(LY this$0, LY rabbit) {
      super(rabbit);
      this.this$0 = this$0;
      this.rabbit = rabbit;
   }

   public boolean getIsJumping() {
      return this.isJumping;
   }

   public boolean canJump() {
      return this.canJump;
   }

   public void setCanJump(boolean canJumpIn) {
      this.canJump = canJumpIn;
   }

   public void doJump() {
      if (this.isJumping) {
         this.rabbit.startJumping();
         this.isJumping = false;
      }

   }
}
