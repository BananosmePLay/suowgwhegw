package neo;

public enum ZY {
   GROWING(4259712),
   SHRINKING(16724016),
   STATIONARY(2138367);

   private final int color;

   private ZY(int color) {
      this.color = color;
   }

   public int getColor() {
      return this.color;
   }
}
