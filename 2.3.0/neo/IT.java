package neo;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class IT extends Ig {
   private double targetX;
   private double targetY;
   private double targetZ;
   private int despawnTimer;
   private boolean shatterOrDrop;

   public IT(bij worldIn) {
      super(worldIn);
      this.setSize(0.25F, 0.25F);
   }

   protected void entityInit() {
   }

   public boolean isInRangeToRenderDist(double distance) {
      double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(d0)) {
         d0 = 4.0;
      }

      d0 *= 64.0;
      return distance < d0 * d0;
   }

   public IT(bij worldIn, double x, double y, double z) {
      super(worldIn);
      this.despawnTimer = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(x, y, z);
   }

   public void moveTowards(BlockPos pos) {
      double d0 = (double)pos.getX();
      int i = pos.getY();
      double d1 = (double)pos.getZ();
      double d2 = d0 - this.posX;
      double d3 = d1 - this.posZ;
      float f = MathHelper.sqrt(d2 * d2 + d3 * d3);
      if (f > 12.0F) {
         this.targetX = this.posX + d2 / (double)f * 12.0;
         this.targetZ = this.posZ + d3 / (double)f * 12.0;
         this.targetY = this.posY + 8.0;
      } else {
         this.targetX = d0;
         this.targetY = (double)i;
         this.targetZ = d1;
      }

      this.despawnTimer = 0;
      this.shatterOrDrop = this.rand.nextInt(5) > 0;
   }

   public void setVelocity(double x, double y, double z) {
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt(x * x + z * z);
         this.rotationYaw = (float)(MathHelper.atan2(x, z) * 57.29577951308232);
         this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * 57.29577951308232);
         this.prevRotationYaw = this.rotationYaw;
         this.prevRotationPitch = this.rotationPitch;
      }

   }

   public void onUpdate() {
      this.lastTickPosX = this.posX;
      this.lastTickPosY = this.posY;
      this.lastTickPosZ = this.posZ;
      super.onUpdate();
      this.posX += this.motionX;
      this.posY += this.motionY;
      this.posZ += this.motionZ;
      float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 57.29577951308232);

      for(this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * 57.29577951308232); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
      }

      while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
         this.prevRotationPitch += 360.0F;
      }

      while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
         this.prevRotationYaw -= 360.0F;
      }

      while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
         this.prevRotationYaw += 360.0F;
      }

      this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
      this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
      if (!this.world.isRemote) {
         double d0 = this.targetX - this.posX;
         double d1 = this.targetZ - this.posZ;
         float f1 = (float)Math.sqrt(d0 * d0 + d1 * d1);
         float f2 = (float)MathHelper.atan2(d1, d0);
         double d2 = (double)f + (double)(f1 - f) * 0.0025;
         if (f1 < 1.0F) {
            d2 *= 0.8;
            this.motionY *= 0.8;
         }

         this.motionX = Math.cos((double)f2) * d2;
         this.motionZ = Math.sin((double)f2) * d2;
         if (this.posY < this.targetY) {
            this.motionY += (1.0 - this.motionY) * 0.014999999664723873;
         } else {
            this.motionY += (-1.0 - this.motionY) * 0.014999999664723873;
         }
      }

      float f3 = 0.25F;
      if (this.isInWater()) {
         for(int i = 0; i < 4; ++i) {
            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25, this.posY - this.motionY * 0.25, this.posZ - this.motionZ * 0.25, this.motionX, this.motionY, this.motionZ);
         }
      } else {
         this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX - this.motionX * 0.25 + this.rand.nextDouble() * 0.6 - 0.3, this.posY - this.motionY * 0.25 - 0.5, this.posZ - this.motionZ * 0.25 + this.rand.nextDouble() * 0.6 - 0.3, this.motionX, this.motionY, this.motionZ);
      }

      if (!this.world.isRemote) {
         this.setPosition(this.posX, this.posY, this.posZ);
         ++this.despawnTimer;
         if (this.despawnTimer > 80 && !this.world.isRemote) {
            this.playSound(NO.ENTITY_ENDEREYE_DEATH, 1.0F, 1.0F);
            this.setDead();
            if (this.shatterOrDrop) {
               this.world.spawnEntity(new IY(this.world, this.posX, this.posY, this.posZ, new Qy(NK.ENDER_EYE)));
            } else {
               this.world.playEvent(2003, new BlockPos(this), 0);
            }
         }
      }

   }

   public void writeEntityToNBT(QQ compound) {
   }

   public void readEntityFromNBT(QQ compound) {
   }

   public float getBrightness() {
      return 1.0F;
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public boolean canBeAttackedWithItem() {
      return false;
   }
}
