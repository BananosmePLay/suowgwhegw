package neo;

public class qv implements pK {
   public qv() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      qx particlefirework$spark = new qx(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, nC.getMinecraft().effectRenderer);
      particlefirework$spark.setAlphaF(0.99F);
      return particlefirework$spark;
   }
}
