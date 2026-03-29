package net.minecraft.client.particle;

import net.minecraft.world.World;

public class ParticleExplosion extends Particle {
   protected ParticleExplosion(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      this.motionX = xSpeedIn + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
      this.motionY = ySpeedIn + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
      this.motionZ = zSpeedIn + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
      float f = this.rand.nextFloat() * 0.3F + 0.7F;
      this.particleRed = f;
      this.particleGreen = f;
      this.particleBlue = f;
      this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F;
      this.particleMaxAge = (int)(16.0 / ((double)this.rand.nextFloat() * 0.8 + 0.2)) + 2;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
      this.motionY += 0.004;
      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.8999999761581421;
      this.motionY *= 0.8999999761581421;
      this.motionZ *= 0.8999999761581421;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

   }

   public static class Factory implements IParticleFactory {
      public Factory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         return new ParticleExplosion(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      }
   }
}
