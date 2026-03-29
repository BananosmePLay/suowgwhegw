package neo;

public class rh implements pK {
   public rh() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new rm(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      ((pM)particle).setAlphaF(0.15F);
      ((pM)particle).setRBGColorF((float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
      return particle;
   }
}
