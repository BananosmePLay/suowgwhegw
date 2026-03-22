package net.minecraft.client.particle;

import java.util.Random;
import net.minecraft.world.World;

public class ParticleSpell extends Particle {
   private static final Random RANDOM = new Random();
   private int baseSpellTextureIndex = 128;

   protected ParticleSpell(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1229_8_, double ySpeed, double p_i1229_12_) {
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

   public static class WitchFactory implements IParticleFactory {
      public WitchFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleSpell(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
         ((ParticleSpell)particle).setBaseSpellTextureIndex(144);
         float f = worldIn.rand.nextFloat() * 0.5F + 0.35F;
         ((Particle)particle).setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
         return particle;
      }
   }

   public static class MobFactory implements IParticleFactory {
      public MobFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleSpell(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
         ((Particle)particle).setRBGColorF((float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
         return particle;
      }
   }

   public static class InstantFactory implements IParticleFactory {
      public InstantFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleSpell(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
         ((ParticleSpell)particle).setBaseSpellTextureIndex(144);
         return particle;
      }
   }

   public static class Factory implements IParticleFactory {
      public Factory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         return new ParticleSpell(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
      }
   }

   public static class AmbientMobFactory implements IParticleFactory {
      public AmbientMobFactory() {
      }

      public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
         Particle particle = new ParticleSpell(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
         ((Particle)particle).setAlphaF(0.15F);
         ((Particle)particle).setRBGColorF((float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
         return particle;
      }
   }
}
