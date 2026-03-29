package neo;

public class oC extends nH {
   public ow signBoard = new ow(this, 0, 0);
   public ow signStick;

   public oC() {
      this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
      this.signStick = new ow(this, 0, 14);
      this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
   }

   public void renderSign() {
      this.signBoard.render(0.0625F);
      this.signStick.render(0.0625F);
   }
}
