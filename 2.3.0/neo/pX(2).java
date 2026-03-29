package neo;

public class pX implements pK {
   public pX() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new qa(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn + 1.0, zSpeedIn, 1.0F);
      ((pM)particle).setMaxAge(20);
      ((pM)particle).setParticleTextureIndex(67);
      return particle;
   }
}
