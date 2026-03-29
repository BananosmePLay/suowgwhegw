package neo;

public class Hu {
   private final Iu entity;
   protected boolean isJumping;

   public Hu(Iu entityIn) {
      this.entity = entityIn;
   }

   public void setJumping() {
      this.isJumping = true;
   }

   public void doJump() {
      this.entity.setJumping(this.isJumping);
      this.isJumping = false;
   }
}
