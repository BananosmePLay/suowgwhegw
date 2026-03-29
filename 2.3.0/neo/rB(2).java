package neo;

public class rB extends pM {
   protected rB(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i45073_8_, double p_i45073_10_, double p_i45073_12_) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.motionX *= 0.30000001192092896;
      this.motionY = Math.random() * 0.20000000298023224 + 0.10000000149011612;
      this.motionZ *= 0.30000001192092896;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.setParticleTextureIndex(19);
      this.setSize(0.01F, 0.01F);
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.particleGravity = 0.0F;
      this.motionX = p_i45073_8_;
      this.motionY = p_i45073_10_;
      this.motionZ = p_i45073_12_;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= (double)this.particleGravity;
      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9800000190734863;
      this.motionY *= 0.9800000190734863;
      this.motionZ *= 0.9800000190734863;
      int i = 60 - this.particleMaxAge;
      float f = (float)i * 0.001F;
      this.setSize(f, f);
      this.setParticleTextureIndex(19 + i % 4);
      if (this.particleMaxAge-- <= 0) {
         this.setExpired();
      }

   }
}
