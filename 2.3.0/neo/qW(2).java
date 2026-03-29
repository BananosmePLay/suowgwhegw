package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class qW extends pM {
   protected qW(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.motionX *= 0.30000001192092896;
      this.motionY = Math.random() * 0.20000000298023224 + 0.10000000149011612;
      this.motionZ *= 0.30000001192092896;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.setParticleTextureIndex(19 + this.rand.nextInt(4));
      this.setSize(0.01F, 0.01F);
      this.particleGravity = 0.06F;
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
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
      if (this.particleMaxAge-- <= 0) {
         this.setExpired();
      }

      if (this.onGround) {
         if (Math.random() < 0.5) {
            this.setExpired();
         }

         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }

      BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
      in iblockstate = this.world.getBlockState(blockpos);
      hM material = iblockstate.getMaterial();
      if (material.isLiquid() || material.isSolid()) {
         double d0;
         if (iblockstate.getBlock() instanceof eB) {
            d0 = (double)(1.0F - eB.getLiquidHeightPercent((Integer)iblockstate.getValue(eB.LEVEL)));
         } else {
            d0 = iblockstate.getBoundingBox(this.world, blockpos).maxY;
         }

         double d1 = (double)MathHelper.floor(this.posY) + d0;
         if (this.posY < d1) {
            this.setExpired();
         }
      }

   }
}
