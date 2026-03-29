package neo;

public class EY implements EW {
   protected final double x;
   protected final double y;
   protected final double z;

   public EY(double xCoord, double yCoord, double zCoord) {
      this.x = xCoord;
      this.y = yCoord;
      this.z = zCoord;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }
}
