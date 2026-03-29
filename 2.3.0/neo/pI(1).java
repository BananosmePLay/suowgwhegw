package neo;

public class pI implements pK {
   public pI() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      return new pJ(worldIn, xCoordIn, yCoordIn, zCoordIn, OL.getItemFromBlock(Nk.BARRIER));
   }
}
