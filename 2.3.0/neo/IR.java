package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class IR extends Ig {
   private static final Rd<Integer> TIME_SINCE_HIT;
   private static final Rd<Integer> FORWARD_DIRECTION;
   private static final Rd<Float> DAMAGE_TAKEN;
   private static final Rd<Integer> BOAT_TYPE;
   private static final Rd<Boolean>[] DATA_ID_PADDLE;
   private final float[] paddlePositions;
   private float momentum;
   private float outOfControlTicks;
   private float deltaRotation;
   private int lerpSteps;
   private double lerpX;
   private double lerpY;
   private double lerpZ;
   private double lerpYaw;
   private double lerpPitch;
   private boolean leftInputDown;
   private boolean rightInputDown;
   private boolean forwardInputDown;
   private boolean backInputDown;
   private double waterLevel;
   private float boatGlide;
   private IP status;
   private IP previousStatus;
   private double lastYd;

   public IR(bij worldIn) {
      super(worldIn);
      this.paddlePositions = new float[2];
      this.preventEntitySpawning = true;
      this.setSize(1.375F, 0.5625F);
   }

   public IR(bij worldIn, double x, double y, double z) {
      this(worldIn);
      this.setPosition(x, y, z);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = x;
      this.prevPosY = y;
      this.prevPosZ = z;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      this.dataManager.register(TIME_SINCE_HIT, 0);
      this.dataManager.register(FORWARD_DIRECTION, 1);
      this.dataManager.register(DAMAGE_TAKEN, 0.0F);
      this.dataManager.register(BOAT_TYPE, IQ.OAK.ordinal());
      Rd[] var1 = DATA_ID_PADDLE;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Rd<Boolean> dataparameter = var1[var3];
         this.dataManager.register(dataparameter, false);
      }

   }

   @Nullable
   public AxisAlignedBB getCollisionBox(Ig entityIn) {
      return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox() {
      return this.getEntityBoundingBox();
   }

   public boolean canBePushed() {
      return true;
   }

   public double getMountedYOffset() {
      return -0.1;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (!this.world.isRemote && !this.isDead) {
         if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null && this.isPassenger(source.getTrueSource())) {
            return false;
         } else {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
            this.markVelocityChanged();
            boolean flag = source.getTrueSource() instanceof ME && ((ME)source.getTrueSource()).capabilities.isCreativeMode;
            if (flag || this.getDamageTaken() > 40.0F) {
               if (!flag && this.world.getGameRules().getBoolean("doEntityDrops")) {
                  this.dropItemWithOffset(this.getItemBoat(), 1, 0.0F);
               }

               this.setDead();
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void applyEntityCollision(Ig entityIn) {
      if (entityIn instanceof IR) {
         if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
            super.applyEntityCollision(entityIn);
         }
      } else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY) {
         super.applyEntityCollision(entityIn);
      }

   }

   public OL getItemBoat() {
      switch (this.getBoatType()) {
         case OAK:
         default:
            return NK.BOAT;
         case SPRUCE:
            return NK.SPRUCE_BOAT;
         case BIRCH:
            return NK.BIRCH_BOAT;
         case JUNGLE:
            return NK.JUNGLE_BOAT;
         case ACACIA:
            return NK.ACACIA_BOAT;
         case DARK_OAK:
            return NK.DARK_OAK_BOAT;
      }
   }

   public void performHurtAnimation() {
      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(10);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
      this.lerpX = x;
      this.lerpY = y;
      this.lerpZ = z;
      this.lerpYaw = (double)yaw;
      this.lerpPitch = (double)pitch;
      this.lerpSteps = 10;
   }

   public EnumFacing getAdjustedHorizontalFacing() {
      return this.getHorizontalFacing().rotateY();
   }

   public void onUpdate() {
      this.previousStatus = this.status;
      this.status = this.getBoatStatus();
      if (this.status != IP.UNDER_WATER && this.status != IP.UNDER_FLOWING_WATER) {
         this.outOfControlTicks = 0.0F;
      } else {
         ++this.outOfControlTicks;
      }

      if (!this.world.isRemote && this.outOfControlTicks >= 60.0F) {
         this.removePassengers();
      }

      if (this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if (this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      super.onUpdate();
      this.tickLerp();
      if (this.canPassengerSteer()) {
         if (this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof ME)) {
            this.setPaddleState(false, false);
         }

         this.updateMotion();
         if (this.world.isRemote) {
            this.controlBoat();
            this.world.sendPacketToServer(new Tl(this.getPaddleState(0), this.getPaddleState(1)));
         }

         this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
      } else {
         this.motionX = 0.0;
         this.motionY = 0.0;
         this.motionZ = 0.0;
      }

      for(int i = 0; i <= 1; ++i) {
         if (this.getPaddleState(i)) {
            if (!this.isSilent() && (double)(this.paddlePositions[i] % 6.2831855F) <= 0.7853981633974483 && ((double)this.paddlePositions[i] + 0.39269909262657166) % 6.283185307179586 >= 0.7853981633974483) {
               SoundEvent soundevent = this.getPaddleSound();
               if (soundevent != null) {
                  Vec3d vec3d = this.getLook(1.0F);
                  double d0 = i == 1 ? -vec3d.z : vec3d.z;
                  double d1 = i == 1 ? vec3d.x : -vec3d.x;
                  this.world.playSound((ME)null, this.posX + d0, this.posY, this.posZ + d1, soundevent, this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat());
               }
            }

            this.paddlePositions[i] = (float)((double)this.paddlePositions[i] + 0.39269909262657166);
         } else {
            this.paddlePositions[i] = 0.0F;
         }
      }

      this.doBlockCollisions();
      List<Ig> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224, -0.009999999776482582, 0.20000000298023224), EntitySelectors.getTeamCollisionPredicate(this));
      if (!list.isEmpty()) {
         boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof ME);

         for(int j = 0; j < list.size(); ++j) {
            Ig entity = (Ig)list.get(j);
            if (!entity.isPassenger(this)) {
               if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width && entity instanceof Iw && !(entity instanceof Mr) && !(entity instanceof ME)) {
                  entity.startRiding(this);
               } else {
                  this.applyEntityCollision(entity);
               }
            }
         }
      }

   }

   @Nullable
   protected SoundEvent getPaddleSound() {
      switch (this.getBoatStatus()) {
         case IN_WATER:
         case UNDER_WATER:
         case UNDER_FLOWING_WATER:
            return NO.ENTITY_BOAT_PADDLE_WATER;
         case ON_LAND:
            return NO.ENTITY_BOAT_PADDLE_LAND;
         case IN_AIR:
         default:
            return null;
      }
   }

   private void tickLerp() {
      if (this.lerpSteps > 0 && !this.canPassengerSteer()) {
         double d0 = this.posX + (this.lerpX - this.posX) / (double)this.lerpSteps;
         double d1 = this.posY + (this.lerpY - this.posY) / (double)this.lerpSteps;
         double d2 = this.posZ + (this.lerpZ - this.posZ) / (double)this.lerpSteps;
         double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.lerpSteps);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.lerpPitch - (double)this.rotationPitch) / (double)this.lerpSteps);
         --this.lerpSteps;
         this.setPosition(d0, d1, d2);
         this.setRotation(this.rotationYaw, this.rotationPitch);
      }

   }

   public void setPaddleState(boolean left, boolean right) {
      this.dataManager.set(DATA_ID_PADDLE[0], left);
      this.dataManager.set(DATA_ID_PADDLE[1], right);
   }

   public float getRowingTime(int side, float limbSwing) {
      return this.getPaddleState(side) ? (float)MathHelper.clampedLerp((double)this.paddlePositions[side] - 0.39269909262657166, (double)this.paddlePositions[side], (double)limbSwing) : 0.0F;
   }

   private IP getBoatStatus() {
      IP entityboat$status = this.getUnderwaterStatus();
      if (entityboat$status != null) {
         this.waterLevel = this.getEntityBoundingBox().maxY;
         return entityboat$status;
      } else if (this.checkInWater()) {
         return IP.IN_WATER;
      } else {
         float f = this.getBoatGlide();
         if (f > 0.0F) {
            this.boatGlide = f;
            return IP.ON_LAND;
         } else {
            return IP.IN_AIR;
         }
      }
   }

   public float getWaterLevelAbove() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      int i = MathHelper.floor(axisalignedbb.minX);
      int j = MathHelper.ceil(axisalignedbb.maxX);
      int k = MathHelper.floor(axisalignedbb.maxY);
      int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
      int i1 = MathHelper.floor(axisalignedbb.minZ);
      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      float f;
      try {
         label108:
         for(int k1 = k; k1 < l; ++k1) {
            f = 0.0F;

            for(int l1 = i; l1 < j; ++l1) {
               for(int i2 = i1; i2 < j1; ++i2) {
                  blockpos$pooledmutableblockpos.setPos(l1, k1, i2);
                  in iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);
                  if (iblockstate.getMaterial() == hM.WATER) {
                     f = Math.max(f, eB.getBlockLiquidHeight(iblockstate, this.world, blockpos$pooledmutableblockpos));
                  }

                  if (f >= 1.0F) {
                     continue label108;
                  }
               }
            }

            if (f < 1.0F) {
               float f2 = (float)blockpos$pooledmutableblockpos.getY() + f;
               float var19 = f2;
               return var19;
            }
         }

         float f1 = (float)(l + 1);
         f = f1;
      } finally {
         blockpos$pooledmutableblockpos.release();
      }

      return f;
   }

   public float getBoatGlide() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
      int i = MathHelper.floor(axisalignedbb1.minX) - 1;
      int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
      int k = MathHelper.floor(axisalignedbb1.minY) - 1;
      int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
      int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
      int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
      List<AxisAlignedBB> list = Lists.newArrayList();
      float f = 0.0F;
      int k1 = 0;
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      try {
         for(int l1 = i; l1 < j; ++l1) {
            for(int i2 = i1; i2 < j1; ++i2) {
               int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
               if (j2 != 2) {
                  for(int k2 = k; k2 < l; ++k2) {
                     if (j2 <= 0 || k2 != k && k2 != l - 1) {
                        blockpos$pooledmutableblockpos.setPos(l1, k2, i2);
                        in iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);
                        iblockstate.addCollisionBoxToList(this.world, blockpos$pooledmutableblockpos, axisalignedbb1, list, this, false);
                        if (!list.isEmpty()) {
                           f += iblockstate.getBlock().slipperiness;
                           ++k1;
                        }

                        list.clear();
                     }
                  }
               }
            }
         }
      } finally {
         blockpos$pooledmutableblockpos.release();
      }

      return f / (float)k1;
   }

   private boolean checkInWater() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      int i = MathHelper.floor(axisalignedbb.minX);
      int j = MathHelper.ceil(axisalignedbb.maxX);
      int k = MathHelper.floor(axisalignedbb.minY);
      int l = MathHelper.ceil(axisalignedbb.minY + 0.001);
      int i1 = MathHelper.floor(axisalignedbb.minZ);
      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
      boolean flag = false;
      this.waterLevel = Double.MIN_VALUE;
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      try {
         for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
               for(int i2 = i1; i2 < j1; ++i2) {
                  blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                  in iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);
                  if (iblockstate.getMaterial() == hM.WATER) {
                     float f = eB.getLiquidHeight(iblockstate, this.world, blockpos$pooledmutableblockpos);
                     this.waterLevel = Math.max((double)f, this.waterLevel);
                     flag |= axisalignedbb.minY < (double)f;
                  }
               }
            }
         }
      } finally {
         blockpos$pooledmutableblockpos.release();
      }

      return flag;
   }

   @Nullable
   private IP getUnderwaterStatus() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      double d0 = axisalignedbb.maxY + 0.001;
      int i = MathHelper.floor(axisalignedbb.minX);
      int j = MathHelper.ceil(axisalignedbb.maxX);
      int k = MathHelper.floor(axisalignedbb.maxY);
      int l = MathHelper.ceil(d0);
      int i1 = MathHelper.floor(axisalignedbb.minZ);
      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
      boolean flag = false;
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

      try {
         for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
               for(int i2 = i1; i2 < j1; ++i2) {
                  blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
                  in iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos);
                  if (iblockstate.getMaterial() == hM.WATER && d0 < (double)eB.getLiquidHeight(iblockstate, this.world, blockpos$pooledmutableblockpos)) {
                     if ((Integer)iblockstate.getValue(eB.LEVEL) != 0) {
                        IP entityboat$status = IP.UNDER_FLOWING_WATER;
                        IP var17 = entityboat$status;
                        return var17;
                     }

                     flag = true;
                  }
               }
            }
         }

         return flag ? IP.UNDER_WATER : null;
      } finally {
         blockpos$pooledmutableblockpos.release();
      }
   }

   private void updateMotion() {
      double d0 = -0.03999999910593033;
      double d1 = this.hasNoGravity() ? 0.0 : -0.03999999910593033;
      double d2 = 0.0;
      this.momentum = 0.05F;
      if (this.previousStatus == IP.IN_AIR && this.status != IP.IN_AIR && this.status != IP.ON_LAND) {
         this.waterLevel = this.getEntityBoundingBox().minY + (double)this.height;
         this.setPosition(this.posX, (double)(this.getWaterLevelAbove() - this.height) + 0.101, this.posZ);
         this.motionY = 0.0;
         this.lastYd = 0.0;
         this.status = IP.IN_WATER;
      } else {
         if (this.status == IP.IN_WATER) {
            d2 = (this.waterLevel - this.getEntityBoundingBox().minY) / (double)this.height;
            this.momentum = 0.9F;
         } else if (this.status == IP.UNDER_FLOWING_WATER) {
            d1 = -7.0E-4;
            this.momentum = 0.9F;
         } else if (this.status == IP.UNDER_WATER) {
            d2 = 0.009999999776482582;
            this.momentum = 0.45F;
         } else if (this.status == IP.IN_AIR) {
            this.momentum = 0.9F;
         } else if (this.status == IP.ON_LAND) {
            this.momentum = this.boatGlide;
            if (this.getControllingPassenger() instanceof ME) {
               this.boatGlide /= 2.0F;
            }
         }

         this.motionX *= (double)this.momentum;
         this.motionZ *= (double)this.momentum;
         this.deltaRotation *= this.momentum;
         this.motionY += d1;
         if (d2 > 0.0) {
            double d3 = 0.65;
            this.motionY += d2 * 0.06153846016296973;
            double d4 = 0.75;
            this.motionY *= 0.75;
         }
      }

   }

   private void controlBoat() {
      if (this.isBeingRidden()) {
         float f = 0.0F;
         if (this.leftInputDown) {
            this.deltaRotation += -1.0F;
         }

         if (this.rightInputDown) {
            ++this.deltaRotation;
         }

         if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
            f += 0.005F;
         }

         this.rotationYaw += this.deltaRotation;
         if (this.forwardInputDown) {
            f += 0.04F;
         }

         if (this.backInputDown) {
            f -= 0.005F;
         }

         this.motionX += (double)(MathHelper.sin(-this.rotationYaw * 0.017453292F) * f);
         this.motionZ += (double)(MathHelper.cos(this.rotationYaw * 0.017453292F) * f);
         this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
      }

   }

   public void updatePassenger(Ig passenger) {
      if (this.isPassenger(passenger)) {
         float f = 0.0F;
         float f1 = (float)((this.isDead ? 0.009999999776482582 : this.getMountedYOffset()) + passenger.getYOffset());
         if (this.getPassengers().size() > 1) {
            int i = this.getPassengers().indexOf(passenger);
            if (i == 0) {
               f = 0.2F;
            } else {
               f = -0.6F;
            }

            if (passenger instanceof Ly) {
               f = (float)((double)f + 0.2);
            }
         }

         Vec3d vec3d = (new Vec3d((double)f, 0.0, 0.0)).rotateYaw(-this.rotationYaw * 0.017453292F - 1.5707964F);
         passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
         passenger.rotationYaw += this.deltaRotation;
         passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
         this.applyYawToEntity(passenger);
         if (passenger instanceof Ly && this.getPassengers().size() > 1) {
            int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
            passenger.setRenderYawOffset(((Ly)passenger).renderYawOffset + (float)j);
            passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
         }
      }

   }

   protected void applyYawToEntity(Ig entityToUpdate) {
      entityToUpdate.setRenderYawOffset(this.rotationYaw);
      float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
      float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
      entityToUpdate.prevRotationYaw += f1 - f;
      entityToUpdate.rotationYaw += f1 - f;
      entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
   }

   public void applyOrientationToEntity(Ig entityToUpdate) {
      this.applyYawToEntity(entityToUpdate);
   }

   protected void writeEntityToNBT(QQ compound) {
      compound.setString("Type", this.getBoatType().getName());
   }

   protected void readEntityFromNBT(QQ compound) {
      if (compound.hasKey("Type", 8)) {
         this.setBoatType(IQ.getTypeFromString(compound.getString("Type")));
      }

   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      if (player.isSneaking()) {
         return false;
      } else {
         if (!this.world.isRemote && this.outOfControlTicks < 60.0F) {
            player.startRiding(this);
         }

         return true;
      }
   }

   protected void updateFallState(double y, boolean onGroundIn, in state, BlockPos pos) {
      this.lastYd = this.motionY;
      if (!this.isRiding()) {
         if (onGroundIn) {
            if (this.fallDistance > 3.0F) {
               if (this.status != IP.ON_LAND) {
                  this.fallDistance = 0.0F;
                  return;
               }

               this.fall(this.fallDistance, 1.0F);
               if (!this.world.isRemote && !this.isDead) {
                  this.setDead();
                  if (this.world.getGameRules().getBoolean("doEntityDrops")) {
                     int j;
                     for(j = 0; j < 3; ++j) {
                        this.entityDropItem(new Qy(OL.getItemFromBlock(Nk.PLANKS), 1, this.getBoatType().getMetadata()), 0.0F);
                     }

                     for(j = 0; j < 2; ++j) {
                        this.dropItemWithOffset(NK.STICK, 1, 0.0F);
                     }
                  }
               }
            }

            this.fallDistance = 0.0F;
         } else if (this.world.getBlockState((new BlockPos(this)).down()).getMaterial() != hM.WATER && y < 0.0) {
            this.fallDistance = (float)((double)this.fallDistance - y);
         }
      }

   }

   public boolean getPaddleState(int side) {
      return (Boolean)this.dataManager.get(DATA_ID_PADDLE[side]) && this.getControllingPassenger() != null;
   }

   public void setDamageTaken(float damageTaken) {
      this.dataManager.set(DAMAGE_TAKEN, damageTaken);
   }

   public float getDamageTaken() {
      return (Float)this.dataManager.get(DAMAGE_TAKEN);
   }

   public void setTimeSinceHit(int timeSinceHit) {
      this.dataManager.set(TIME_SINCE_HIT, timeSinceHit);
   }

   public int getTimeSinceHit() {
      return (Integer)this.dataManager.get(TIME_SINCE_HIT);
   }

   public void setForwardDirection(int forwardDirection) {
      this.dataManager.set(FORWARD_DIRECTION, forwardDirection);
   }

   public int getForwardDirection() {
      return (Integer)this.dataManager.get(FORWARD_DIRECTION);
   }

   public void setBoatType(IQ boatType) {
      this.dataManager.set(BOAT_TYPE, boatType.ordinal());
   }

   public IQ getBoatType() {
      return IQ.byId((Integer)this.dataManager.get(BOAT_TYPE));
   }

   protected boolean canFitPassenger(Ig passenger) {
      return this.getPassengers().size() < 2;
   }

   @Nullable
   public Ig getControllingPassenger() {
      List<Ig> list = this.getPassengers();
      return list.isEmpty() ? null : (Ig)list.get(0);
   }

   public void updateInputs(boolean p_184442_1_, boolean p_184442_2_, boolean p_184442_3_, boolean p_184442_4_) {
      this.leftInputDown = p_184442_1_;
      this.rightInputDown = p_184442_2_;
      this.forwardInputDown = p_184442_3_;
      this.backInputDown = p_184442_4_;
   }

   static {
      TIME_SINCE_HIT = Rv.createKey(IR.class, Rt.VARINT);
      FORWARD_DIRECTION = Rv.createKey(IR.class, Rt.VARINT);
      DAMAGE_TAKEN = Rv.createKey(IR.class, Rt.FLOAT);
      BOAT_TYPE = Rv.createKey(IR.class, Rt.VARINT);
      DATA_ID_PADDLE = new Rd[]{Rv.createKey(IR.class, Rt.BOOLEAN), Rv.createKey(IR.class, Rt.BOOLEAN)};
   }
}
