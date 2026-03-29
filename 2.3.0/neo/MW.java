package neo;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class MW extends Ig implements IJ {
   public LK owner;
   private QQ ownerNbt;

   public MW(bij worldIn) {
      super(worldIn);
   }

   public MW(bij worldIn, LK p_i47273_2_) {
      super(worldIn);
      this.owner = p_i47273_2_;
      this.setPosition(p_i47273_2_.posX - (double)(p_i47273_2_.width + 1.0F) * 0.5 * (double)MathHelper.sin(p_i47273_2_.renderYawOffset * 0.017453292F), p_i47273_2_.posY + (double)p_i47273_2_.getEyeHeight() - 0.10000000149011612, p_i47273_2_.posZ + (double)(p_i47273_2_.width + 1.0F) * 0.5 * (double)MathHelper.cos(p_i47273_2_.renderYawOffset * 0.017453292F));
      this.setSize(0.25F, 0.25F);
   }

   public MW(bij worldIn, double x, double y, double z, double p_i47274_8_, double p_i47274_10_, double p_i47274_12_) {
      super(worldIn);
      this.setPosition(x, y, z);

      for(int i = 0; i < 7; ++i) {
         double d0 = 0.4 + 0.1 * (double)i;
         worldIn.spawnParticle(EnumParticleTypes.SPIT, x, y, z, p_i47274_8_ * d0, p_i47274_10_, p_i47274_12_ * d0);
      }

      this.motionX = p_i47274_8_;
      this.motionY = p_i47274_10_;
      this.motionZ = p_i47274_12_;
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.ownerNbt != null) {
         this.restoreOwnerFromSave();
      }

      Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
      Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
      vec3d = new Vec3d(this.posX, this.posY, this.posZ);
      vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      if (raytraceresult != null) {
         vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
      }

      Ig entity = this.getHitEntity(vec3d, vec3d1);
      if (entity != null) {
         raytraceresult = new RayTraceResult(entity);
      }

      if (raytraceresult != null) {
         this.onHit(raytraceresult);
      }

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
      float f1 = 0.99F;
      float f2 = 0.06F;
      if (!this.world.isMaterialInBB(this.getEntityBoundingBox(), hM.AIR)) {
         this.setDead();
      } else if (this.isInWater()) {
         this.setDead();
      } else {
         this.motionX *= 0.9900000095367432;
         this.motionY *= 0.9900000095367432;
         this.motionZ *= 0.9900000095367432;
         if (!this.hasNoGravity()) {
            this.motionY -= 0.05999999865889549;
         }

         this.setPosition(this.posX, this.posY, this.posZ);
      }

   }

   public void setVelocity(double x, double y, double z) {
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt(x * x + z * z);
         this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * 57.29577951308232);
         this.rotationYaw = (float)(MathHelper.atan2(x, z) * 57.29577951308232);
         this.prevRotationPitch = this.rotationPitch;
         this.prevRotationYaw = this.rotationYaw;
         this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }

   }

   @Nullable
   private Ig getHitEntity(Vec3d p_190538_1_, Vec3d p_190538_2_) {
      Ig entity = null;
      List<Ig> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0));
      double d0 = 0.0;
      Iterator var7 = list.iterator();

      while(true) {
         Ig entity1;
         double d1;
         do {
            RayTraceResult raytraceresult;
            do {
               do {
                  if (!var7.hasNext()) {
                     return entity;
                  }

                  entity1 = (Ig)var7.next();
               } while(entity1 == this.owner);

               AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896);
               raytraceresult = axisalignedbb.calculateIntercept(p_190538_1_, p_190538_2_);
            } while(raytraceresult == null);

            d1 = p_190538_1_.squareDistanceTo(raytraceresult.hitVec);
         } while(!(d1 < d0) && d0 != 0.0);

         entity = entity1;
         d0 = d1;
      }
   }

   public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
      float f = MathHelper.sqrt(x * x + y * y + z * z);
      x /= (double)f;
      y /= (double)f;
      z /= (double)f;
      x += this.rand.nextGaussian() * 0.007499999832361937 * (double)inaccuracy;
      y += this.rand.nextGaussian() * 0.007499999832361937 * (double)inaccuracy;
      z += this.rand.nextGaussian() * 0.007499999832361937 * (double)inaccuracy;
      x *= (double)velocity;
      y *= (double)velocity;
      z *= (double)velocity;
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      float f1 = MathHelper.sqrt(x * x + z * z);
      this.rotationYaw = (float)(MathHelper.atan2(x, z) * 57.29577951308232);
      this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * 57.29577951308232);
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
   }

   public void onHit(RayTraceResult p_190536_1_) {
      if (p_190536_1_.entityHit != null && this.owner != null) {
         p_190536_1_.entityHit.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.owner).setProjectile(), 1.0F);
      }

      if (!this.world.isRemote) {
         this.setDead();
      }

   }

   protected void entityInit() {
   }

   protected void readEntityFromNBT(QQ compound) {
      if (compound.hasKey("Owner", 10)) {
         this.ownerNbt = compound.getCompoundTag("Owner");
      }

   }

   protected void writeEntityToNBT(QQ compound) {
      if (this.owner != null) {
         QQ nbttagcompound = new QQ();
         UUID uuid = this.owner.getUniqueID();
         nbttagcompound.setUniqueId("OwnerUUID", uuid);
         compound.setTag("Owner", nbttagcompound);
      }

   }

   private void restoreOwnerFromSave() {
      if (this.ownerNbt != null && this.ownerNbt.hasUniqueId("OwnerUUID")) {
         UUID uuid = this.ownerNbt.getUniqueId("OwnerUUID");
         Iterator var2 = this.world.getEntitiesWithinAABB(LK.class, this.getEntityBoundingBox().grow(15.0)).iterator();

         while(var2.hasNext()) {
            LK entityllama = (LK)var2.next();
            if (entityllama.getUniqueID().equals(uuid)) {
               this.owner = entityllama;
               break;
            }
         }
      }

      this.ownerNbt = null;
   }
}
