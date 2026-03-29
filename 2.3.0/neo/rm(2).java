package neo;

import java.util.Random;

public class rm extends pM {
   private static final Random RANDOM = new Random();
   private int baseSpellTextureIndex = 128;

   protected rm(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1229_8_, double ySpeed, double p_i1229_12_) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5 - RANDOM.nextDouble(), ySpeed, 0.5 - RANDOM.nextDouble());
      this.motionY *= 0.20000000298023224;
      if (p_i1229_8_ == 0.0 && p_i1229_12_ == 0.0) {
         this.motionX *= 0.10000000149011612;
         this.motionZ *= 0.10000000149011612;
      }

      this.particleScale *= 0.75F;
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }

   public boolean shouldDisableDepth() {
      return true;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.setParticleTextureIndex(this.baseSpellTextureIndex + (7 - this.particleAge * 8 / this.particleMaxAge));
      this.motionY += 0.004;
      this.move(this.motionX, this.motionY, this.motionZ);
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.9599999785423279;
      this.motionY *= 0.9599999785423279;
      this.motionZ *= 0.9599999785423279;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

   }

   public void setBaseSpellTextureIndex(int baseSpellTextureIndexIn) {
      this.baseSpellTextureIndex = baseSpellTextureIndexIn;
   }
}
