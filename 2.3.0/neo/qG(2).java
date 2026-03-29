package neo;

import net.minecraft.util.math.MathHelper;

public class qG extends pM {
   float particleScaleOverTime;

   protected qG(bij worldIn, double p_i1211_2_, double p_i1211_4_, double p_i1211_6_, double p_i1211_8_, double p_i1211_10_, double p_i1211_12_) {
      this(worldIn, p_i1211_2_, p_i1211_4_, p_i1211_6_, p_i1211_8_, p_i1211_10_, p_i1211_12_, 2.0F);
   }

   protected qG(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46354_8_, double p_i46354_10_, double p_i46354_12_, float scale) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.motionX *= 0.009999999776482582;
      this.motionY *= 0.009999999776482582;
      this.motionZ *= 0.009999999776482582;
      this.motionY += 0.1;
      this.particleScale *= 0.75F;
      this.particleScale *= scale;
      this.particleScaleOverTime = this.particleScale;
      this.particleMaxAge = 16;
      this.setParticleTextureIndex(80);
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      this.particleScale = this.particleScaleOverTime * f;
      super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.move(this.motionX, this.motionY, this.motionZ);
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.8600000143051147;
      this.motionY *= 0.8600000143051147;
      this.motionZ *= 0.8600000143051147;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

   }
}
