package neo;

public class or extends ou {
   public or() {
      this(0.0F);
   }

   public or(float scale) {
      super(6, scale);
      this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, scale);
      this.childYOffset = 4.0F;
   }
}
