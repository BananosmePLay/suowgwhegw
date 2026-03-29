package neo;

public class pZ implements pK {
   public pZ() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new qa(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      ((pM)particle).setRBGColorF(((pM)particle).getRedColorF() * 0.3F, ((pM)particle).getGreenColorF() * 0.8F, ((pM)particle).getBlueColorF());
      ((pM)particle).nextTextureIndexX();
      return particle;
   }
}
