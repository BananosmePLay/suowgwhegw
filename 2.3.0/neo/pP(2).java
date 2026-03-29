package neo;

public class pP implements pK {
   public pP() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      int i = p_178902_15_.length > 1 ? p_178902_15_[1] : 0;
      return new pS(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, OL.getItemById(p_178902_15_[0]), i);
   }
}
