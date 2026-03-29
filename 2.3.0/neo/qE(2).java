package neo;

public class qE implements pK {
   public qE() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new qG(worldIn, xCoordIn, yCoordIn + 0.5, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      ((pM)particle).setParticleTextureIndex(81);
      ((pM)particle).setRBGColorF(1.0F, 1.0F, 1.0F);
      return particle;
   }
}
