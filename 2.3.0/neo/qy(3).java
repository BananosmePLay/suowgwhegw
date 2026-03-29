package neo;

import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class qy extends pM {
   private int fireworkAge;
   private final qO manager;
   private QW fireworkExplosions;
   boolean twinkle;

   public qy(bij p_i46464_1_, double p_i46464_2_, double p_i46464_4_, double p_i46464_6_, double p_i46464_8_, double p_i46464_10_, double p_i46464_12_, qO p_i46464_14_, @Nullable QQ p_i46464_15_) {
      super(p_i46464_1_, p_i46464_2_, p_i46464_4_, p_i46464_6_, 0.0, 0.0, 0.0);
      this.motionX = p_i46464_8_;
      this.motionY = p_i46464_10_;
      this.motionZ = p_i46464_12_;
      this.manager = p_i46464_14_;
      this.particleMaxAge = 8;
      if (p_i46464_15_ != null) {
         this.fireworkExplosions = p_i46464_15_.getTagList("Explosions", 10);
         if (this.fireworkExplosions.isEmpty()) {
            this.fireworkExplosions = null;
         } else {
            this.particleMaxAge = this.fireworkExplosions.tagCount() * 2 - 1;

            for(int i = 0; i < this.fireworkExplosions.tagCount(); ++i) {
               QQ nbttagcompound = this.fireworkExplosions.getCompoundTagAt(i);
               if (nbttagcompound.getBoolean("Flicker")) {
                  this.twinkle = true;
                  this.particleMaxAge += 15;
                  break;
               }
            }
         }
      }

   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
   }

   public void onUpdate() {
      boolean flag3;
      if (this.fireworkAge == 0 && this.fireworkExplosions != null) {
         flag3 = this.isFarFromCamera();
         boolean flag1 = false;
         if (this.fireworkExplosions.tagCount() >= 3) {
            flag1 = true;
         } else {
            for(int i = 0; i < this.fireworkExplosions.tagCount(); ++i) {
               QQ nbttagcompound = this.fireworkExplosions.getCompoundTagAt(i);
               if (nbttagcompound.getByte("Type") == 1) {
                  flag1 = true;
                  break;
               }
            }
         }

         SoundEvent soundevent1;
         if (flag1) {
            soundevent1 = flag3 ? NO.ENTITY_FIREWORK_LARGE_BLAST_FAR : NO.ENTITY_FIREWORK_LARGE_BLAST;
         } else {
            soundevent1 = flag3 ? NO.ENTITY_FIREWORK_BLAST_FAR : NO.ENTITY_FIREWORK_BLAST;
         }

         this.world.playSound(this.posX, this.posY, this.posZ, soundevent1, SoundCategory.AMBIENT, 20.0F, 0.95F + this.rand.nextFloat() * 0.1F, true);
      }

      if (this.fireworkAge % 2 == 0 && this.fireworkExplosions != null && this.fireworkAge / 2 < this.fireworkExplosions.tagCount()) {
         int k = this.fireworkAge / 2;
         QQ nbttagcompound1 = this.fireworkExplosions.getCompoundTagAt(k);
         int l = nbttagcompound1.getByte("Type");
         boolean flag4 = nbttagcompound1.getBoolean("Trail");
         boolean flag2 = nbttagcompound1.getBoolean("Flicker");
         int[] aint = nbttagcompound1.getIntArray("Colors");
         int[] aint1 = nbttagcompound1.getIntArray("FadeColors");
         if (aint.length == 0) {
            aint = new int[]{Pq.DYE_COLORS[0]};
         }

         if (l == 1) {
            this.createBall(0.5, 4, aint, aint1, flag4, flag2);
         } else if (l == 2) {
            this.createShaped(0.5, new double[][]{{0.0, 1.0}, {0.3455, 0.309}, {0.9511, 0.309}, {0.3795918367346939, -0.12653061224489795}, {0.6122448979591837, -0.8040816326530612}, {0.0, -0.35918367346938773}}, aint, aint1, flag4, flag2, false);
         } else if (l == 3) {
            this.createShaped(0.5, new double[][]{{0.0, 0.2}, {0.2, 0.2}, {0.2, 0.6}, {0.6, 0.6}, {0.6, 0.2}, {0.2, 0.2}, {0.2, 0.0}, {0.4, 0.0}, {0.4, -0.6}, {0.2, -0.6}, {0.2, -0.4}, {0.0, -0.4}}, aint, aint1, flag4, flag2, true);
         } else if (l == 4) {
            this.createBurst(aint, aint1, flag4, flag2);
         } else {
            this.createBall(0.25, 2, aint, aint1, flag4, flag2);
         }

         int j = aint[0];
         float f = (float)((j & 16711680) >> 16) / 255.0F;
         float f1 = (float)((j & '\uff00') >> 8) / 255.0F;
         float f2 = (float)((j & 255) >> 0) / 255.0F;
         qw particlefirework$overlay = new qw(this.world, this.posX, this.posY, this.posZ);
         particlefirework$overlay.setRBGColorF(f, f1, f2);
         this.manager.addEffect(particlefirework$overlay);
      }

      ++this.fireworkAge;
      if (this.fireworkAge > this.particleMaxAge) {
         if (this.twinkle) {
            flag3 = this.isFarFromCamera();
            SoundEvent soundevent = flag3 ? NO.ENTITY_FIREWORK_TWINKLE_FAR : NO.ENTITY_FIREWORK_TWINKLE;
            this.world.playSound(this.posX, this.posY, this.posZ, soundevent, SoundCategory.AMBIENT, 20.0F, 0.9F + this.rand.nextFloat() * 0.15F, true);
         }

         this.setExpired();
      }

   }

   private boolean isFarFromCamera() {
      nC minecraft = nC.getMinecraft();
      return minecraft == null || minecraft.getRenderViewEntity() == null || minecraft.getRenderViewEntity().getDistanceSq(this.posX, this.posY, this.posZ) >= 256.0;
   }

   private void createParticle(double p_92034_1_, double p_92034_3_, double p_92034_5_, double p_92034_7_, double p_92034_9_, double p_92034_11_, int[] p_92034_13_, int[] p_92034_14_, boolean p_92034_15_, boolean p_92034_16_) {
      qx particlefirework$spark = new qx(this.world, p_92034_1_, p_92034_3_, p_92034_5_, p_92034_7_, p_92034_9_, p_92034_11_, this.manager);
      particlefirework$spark.setAlphaF(0.99F);
      particlefirework$spark.setTrail(p_92034_15_);
      particlefirework$spark.setTwinkle(p_92034_16_);
      int i = this.rand.nextInt(p_92034_13_.length);
      particlefirework$spark.setColor(p_92034_13_[i]);
      if (p_92034_14_ != null && p_92034_14_.length > 0) {
         particlefirework$spark.setColorFade(p_92034_14_[this.rand.nextInt(p_92034_14_.length)]);
      }

      this.manager.addEffect(particlefirework$spark);
   }

   private void createBall(double speed, int size, int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn) {
      double d0 = this.posX;
      double d1 = this.posY;
      double d2 = this.posZ;

      for(int i = -size; i <= size; ++i) {
         for(int j = -size; j <= size; ++j) {
            for(int k = -size; k <= size; ++k) {
               double d3 = (double)j + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
               double d4 = (double)i + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
               double d5 = (double)k + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
               double d6 = (double)MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5) / speed + this.rand.nextGaussian() * 0.05;
               this.createParticle(d0, d1, d2, d3 / d6, d4 / d6, d5 / d6, colours, fadeColours, trail, twinkleIn);
               if (i != -size && i != size && j != -size && j != size) {
                  k += size * 2 - 1;
               }
            }
         }
      }

   }

   private void createShaped(double speed, double[][] shape, int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn, boolean p_92038_8_) {
      double d0 = shape[0][0];
      double d1 = shape[0][1];
      this.createParticle(this.posX, this.posY, this.posZ, d0 * speed, d1 * speed, 0.0, colours, fadeColours, trail, twinkleIn);
      float f = this.rand.nextFloat() * 3.1415927F;
      double d2 = p_92038_8_ ? 0.034 : 0.34;

      for(int i = 0; i < 3; ++i) {
         double d3 = (double)f + (double)((float)i * 3.1415927F) * d2;
         double d4 = d0;
         double d5 = d1;

         for(int j = 1; j < shape.length; ++j) {
            double d6 = shape[j][0];
            double d7 = shape[j][1];

            for(double d8 = 0.25; d8 <= 1.0; d8 += 0.25) {
               double d9 = (d4 + (d6 - d4) * d8) * speed;
               double d10 = (d5 + (d7 - d5) * d8) * speed;
               double d11 = d9 * Math.sin(d3);
               d9 *= Math.cos(d3);

               for(double d12 = -1.0; d12 <= 1.0; d12 += 2.0) {
                  this.createParticle(this.posX, this.posY, this.posZ, d9 * d12, d10, d11 * d12, colours, fadeColours, trail, twinkleIn);
               }
            }

            d4 = d6;
            d5 = d7;
         }
      }

   }

   private void createBurst(int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn) {
      double d0 = this.rand.nextGaussian() * 0.05;
      double d1 = this.rand.nextGaussian() * 0.05;

      for(int i = 0; i < 70; ++i) {
         double d2 = this.motionX * 0.5 + this.rand.nextGaussian() * 0.15 + d0;
         double d3 = this.motionZ * 0.5 + this.rand.nextGaussian() * 0.15 + d1;
         double d4 = this.motionY * 0.5 + this.rand.nextDouble() * 0.5;
         this.createParticle(this.posX, this.posY, this.posZ, d2, d4, d3, colours, fadeColours, trail, twinkleIn);
      }

   }

   public int getFXLayer() {
      return 0;
   }
}
