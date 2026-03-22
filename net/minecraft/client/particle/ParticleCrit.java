package net.minecraft.client.particle;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleCrit extends Particle {
   float oSize;

   protected ParticleCrit(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46284_8_, double p_i46284_10_, double p_i46284_12_) {
      this(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i46284_8_, p_i46284_10_, p_i46284_12_, 1.0F);
   }

   protected ParticleCrit(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46285_8_, double p_i46285_10_, double p_i46285_12_, float p_i46285_14_) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.motionX *= 0.10000000149011612;
      this.motionY *= 0.10000000149011612;
      this.motionZ *= 0.10000000149011612;
      this.motionX += p_i46285_8_ * 0.4;
      this.motionY += p_i46285_10_ * 0.4;
      this.motionZ += p_i46285_12_ * 0.4;
      float f = (float)(Math.random() * 0.30000001192092896 + 0.6000000238418579);
      this.particleRed = f;
      this.particleGreen = f;
      this.particleBlue = f;
      this.particleScale *= 0.75F;
      this.particleScale *= p_i46285_14_;
      this.oSize = this.particleScale;
      this.particleMaxAge = (int)(6.0 / (Math.random() * 0.8 + 0.6));
      this.particleMaxAge = (int)((float)this.particleMaxAge * p_i46285_14_);
      this.setParticleTextureIndex(65);
      this.onUpdate();
   }

   public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
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

      this.move(this.motionX, this.motionY, this.motionZ);
      this.particleGreen = (float)((double)this.particleGreen * 0.96);
      this.particleBlue = (float)((double)this.particleBlue * 0.9);
      this.motionX *= 0.699999988079071;
      this.motionY *= 0.699999988079071;
      this.motionZ *= 0.699999988079071;
      this.motionY -= 0.019999999552965164;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

   }

   public static class MagicFactory implements IParticleFactory {
      public MagicFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleCrit(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
         ((Particle)particle).setRBGColorF(((Particle)particle).getRedColorF() * 0.3F, ((Particle)particle).getGreenColorF() * 0.8F, ((Particle)particle).getBlueColorF());
         ((Particle)particle).nextTextureIndexX();
         return particle;
      }
   }

   public static class Factory implements IParticleFactory {
      public Factory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         return new ParticleCrit(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      }
   }

   public static class DamageIndicatorFactory implements IParticleFactory {
      public DamageIndicatorFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleCrit(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn + 1.0, zSpeedIn, 1.0F);
         ((Particle)particle).setMaxAge(20);
         ((Particle)particle).setParticleTextureIndex(67);
         return particle;
      }
   }
}
