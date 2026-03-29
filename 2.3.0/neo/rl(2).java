package neo;

public class rl implements pK {
   public rl() {
   }

   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      pM particle = new rm(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      ((rm)particle).setBaseSpellTextureIndex(144);
      float f = worldIn.rand.nextFloat() * 0.5F + 0.35F;
      ((pM)particle).setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
      return particle;
   }
}
