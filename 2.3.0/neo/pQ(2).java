package neo;

public class pQ implements pK {
   public pQ() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      return new pS(worldIn, xCoordIn, yCoordIn, zCoordIn, NK.SLIME_BALL);
   }
}
