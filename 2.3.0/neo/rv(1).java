package neo;

public class rv extends pM {
   protected rv(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
      float f = this.rand.nextFloat() * 0.1F + 0.2F;
      this.particleRed = f;
      this.particleGreen = f;
      this.particleBlue = f;
      this.setParticleTextureIndex(0);
      this.setSize(0.02F, 0.02F);
      this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
      this.motionX *= 0.019999999552965164;
      this.motionY *= 0.019999999552965164;
      this.motionZ *= 0.019999999552965164;
      this.particleMaxAge = (int)(20.0 / (Math.random() * 0.8 + 0.2));
   }

   public void move(double x, double y, double z) {
      this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
      this.resetPositionToBB();
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.99;
      this.motionY *= 0.99;
      this.motionZ *= 0.99;
      if (this.particleMaxAge-- <= 0) {
         this.setExpired();
      }

   }
}
