package neo;

import net.minecraft.util.math.BlockPos;

public class rs extends pM {
   protected rs(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
      super(worldIn, xCoordIn, yCoordIn - 0.125, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      this.particleRed = 0.4F;
      this.particleGreen = 0.4F;
      this.particleBlue = 0.7F;
      this.setParticleTextureIndex(0);
      this.setSize(0.01F, 0.01F);
      this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
      this.motionX = xSpeedIn * 0.0;
      this.motionY = ySpeedIn * 0.0;
      this.motionZ = zSpeedIn * 0.0;
      this.particleMaxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.move(this.motionX, this.motionY, this.motionZ);
      if (this.world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getMaterial() != hM.WATER) {
         this.setExpired();
      }

      if (this.particleMaxAge-- <= 0) {
         this.setExpired();
      }

   }
}
