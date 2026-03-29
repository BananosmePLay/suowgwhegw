package neo;

public class ru implements pK {
   public ru() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new rv(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      ((pM)particle).setParticleTextureIndex(82);
      ((pM)particle).setRBGColorF(1.0F, 1.0F, 1.0F);
      return particle;
   }
}
