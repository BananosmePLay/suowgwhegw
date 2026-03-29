package neo;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class qh extends pM {
   private final hM materialType;
   private int bobTimer;

   protected qh(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, hM p_i1203_8_) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      if (p_i1203_8_ == hM.WATER) {
         this.particleRed = 0.0F;
         this.particleGreen = 0.0F;
         this.particleBlue = 1.0F;
      } else {
         this.particleRed = 1.0F;
         this.particleGreen = 0.0F;
         this.particleBlue = 0.0F;
      }

      this.setParticleTextureIndex(113);
      this.setSize(0.01F, 0.01F);
      this.particleGravity = 0.06F;
      this.materialType = p_i1203_8_;
      this.bobTimer = 40;
      this.particleMaxAge = (int)(64.0 / (Math.random() * 0.8 + 0.2));
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
   }

   public int getBrightnessForRender(float partialTick) {
      return this.materialType == hM.WATER ? super.getBrightnessForRender(partialTick) : 257;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.materialType == hM.WATER) {
         this.particleRed = 0.2F;
         this.particleGreen = 0.3F;
         this.particleBlue = 1.0F;
      } else {
         this.particleRed = 1.0F;
         this.particleGreen = 16.0F / (float)(40 - this.bobTimer + 16);
         this.particleBlue = 4.0F / (float)(40 - this.bobTimer + 8);
      }

      this.motionY -= (double)this.particleGravity;
      if (this.bobTimer-- > 0) {
         this.motionX *= 0.02;
         this.motionY *= 0.02;
         this.motionZ *= 0.02;
         this.setParticleTextureIndex(113);
      } else {
         this.setParticleTextureIndex(112);
      }

      this.move(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9800000190734863;
      this.motionY *= 0.9800000190734863;
      this.motionZ *= 0.9800000190734863;
      if (this.particleMaxAge-- <= 0) {
         this.setExpired();
      }

      if (this.onGround) {
         if (this.materialType == hM.WATER) {
            this.setExpired();
            this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
         } else {
            this.setParticleTextureIndex(114);
         }

         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

      BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
      in iblockstate = this.world.getBlockState(blockpos);
      hM material = iblockstate.getMaterial();
      if (material.isLiquid() || material.isSolid()) {
         double d0 = 0.0;
         if (iblockstate.getBlock() instanceof eB) {
            d0 = (double)eB.getLiquidHeightPercent((Integer)iblockstate.getValue(eB.LEVEL));
         }

         double d1 = (double)(MathHelper.floor(this.posY) + 1) - d0;
         if (this.posY < d1) {
            this.setExpired();
         }
      }

   }
}
