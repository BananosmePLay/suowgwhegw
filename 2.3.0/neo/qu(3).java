package neo;

import net.minecraft.util.math.MathHelper;

public class qu extends pM {
   float oSize;
   final float rotSpeed;

   protected qu(bij p_i47135_1_, double p_i47135_2_, double p_i47135_4_, double p_i47135_6_, float p_i47135_8_, float p_i47135_9_, float p_i47135_10_) {
      super(p_i47135_1_, p_i47135_2_, p_i47135_4_, p_i47135_6_, 0.0, 0.0, 0.0);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.particleRed = p_i47135_8_;
      this.particleGreen = p_i47135_9_;
      this.particleBlue = p_i47135_10_;
      float f = 0.9F;
      this.particleScale *= 0.75F;
      this.particleScale *= 0.9F;
      this.oSize = this.particleScale;
      this.particleMaxAge = (int)(32.0 / (Math.random() * 0.8 + 0.2));
      this.particleMaxAge = (int)((float)this.particleMaxAge * 0.9F);
      this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
      this.particleAngle = (float)Math.random() * 6.2831855F;
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      this.particleScale = this.oSize * f;
      super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.prevParticleAngle = this.particleAngle;
      this.particleAngle += 3.1415927F * this.rotSpeed * 2.0F;
      if (this.onGround) {
         this.prevParticleAngle = this.particleAngle = 0.0F;
      }

      this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionY -= 0.003000000026077032;
      this.motionY = Math.max(this.motionY, -0.14000000059604645);
   }
}
