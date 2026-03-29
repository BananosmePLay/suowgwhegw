package neo;

public class qb implements pK {
   public qb() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      return (new qc(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, co.getStateById(p_178902_15_[0]))).init();
   }
}
