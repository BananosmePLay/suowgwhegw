package neo;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class Lw extends Ly implements IInventoryChangedListener, IG {
   private static final Predicate<Ig> IS_HORSE_BREEDING = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof Lw && ((Lw)p_apply_1_).isBreeding();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   protected static final FY JUMP_STRENGTH = (new Gc((FY)null, "horse.jumpStrength", 0.7, 0.0, 2.0)).setDescription("Jump Strength").setShouldWatch(true);
   private static final Rd<Byte> STATUS;
   private static final Rd<Optional<UUID>> OWNER_UNIQUE_ID;
   private int eatingCounter;
   private int openMouthCounter;
   private int jumpRearingCounter;
   public int tailCounter;
   public int sprintCounter;
   protected boolean horseJumping;
   protected ContainerHorseChest horseChest;
   protected int temper;
   protected float jumpPower;
   private boolean allowStandSliding;
   private float headLean;
   private float prevHeadLean;
   private float rearingAmount;
   private float prevRearingAmount;
   private float mouthOpenness;
   private float prevMouthOpenness;
   protected boolean canGallop = true;
   protected int gallopTime;

   public Lw(bij worldIn) {
      super(worldIn);
      this.setSize(1.3964844F, 1.6F);
      this.stepHeight = 1.0F;
      this.initHorseChest();
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new GX(this, 1.2));
      this.tasks.addTask(1, new Hb(this, 1.2));
      this.tasks.addTask(2, new GI(this, 1.0, Lw.class));
      this.tasks.addTask(4, new Gz(this, 1.0));
      this.tasks.addTask(6, new Ho(this, 0.7));
      this.tasks.addTask(7, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(8, new GH(this));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(STATUS, (byte)0);
      this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
   }

   protected boolean getHorseWatchableBoolean(int p_110233_1_) {
      return ((Byte)this.dataManager.get(STATUS) & p_110233_1_) != 0;
   }

   protected void setHorseWatchableBoolean(int p_110208_1_, boolean p_110208_2_) {
      byte b0 = (Byte)this.dataManager.get(STATUS);
      if (p_110208_2_) {
         this.dataManager.set(STATUS, (byte)(b0 | p_110208_1_));
      } else {
         this.dataManager.set(STATUS, (byte)(b0 & ~p_110208_1_));
      }

   }

   public boolean isTame() {
      return this.getHorseWatchableBoolean(2);
   }

   @Nullable
   public UUID getOwnerUniqueId() {
      return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
   }

   public void setOwnerUniqueId(@Nullable UUID uniqueId) {
      this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
   }

   public float getHorseSize() {
      return 0.5F;
   }

   public void setScaleForAge(boolean child) {
      this.setScale(child ? this.getHorseSize() : 1.0F);
   }

   public boolean isHorseJumping() {
      return this.horseJumping;
   }

   public void setHorseTamed(boolean tamed) {
      this.setHorseWatchableBoolean(2, tamed);
   }

   public void setHorseJumping(boolean jumping) {
      this.horseJumping = jumping;
   }

   public boolean canBeLeashedTo(ME player) {
      return super.canBeLeashedTo(player) && this.getCreatureAttribute() != IB.UNDEAD;
   }

   protected void onLeashDistance(float p_142017_1_) {
      if (p_142017_1_ > 6.0F && this.isEatingHaystack()) {
         this.setEatingHaystack(false);
      }

   }

   public boolean isEatingHaystack() {
      return this.getHorseWatchableBoolean(16);
   }

   public boolean isRearing() {
      return this.getHorseWatchableBoolean(32);
   }

   public boolean isBreeding() {
      return this.getHorseWatchableBoolean(8);
   }

   public void setBreeding(boolean breeding) {
      this.setHorseWatchableBoolean(8, breeding);
   }

   public void setHorseSaddled(boolean saddled) {
      this.setHorseWatchableBoolean(4, saddled);
   }

   public int getTemper() {
      return this.temper;
   }

   public void setTemper(int temperIn) {
      this.temper = temperIn;
   }

   public int increaseTemper(int p_110198_1_) {
      int i = MathHelper.clamp(this.getTemper() + p_110198_1_, 0, this.getMaxTemper());
      this.setTemper(i);
      return i;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      Ig entity = source.getTrueSource();
      return this.isBeingRidden() && entity != null && this.isRidingOrBeingRiddenBy(entity) ? false : super.attackEntityFrom(source, amount);
   }

   public boolean canBePushed() {
      return !this.isBeingRidden();
   }

   private void eatingHorse() {
      this.openHorseMouth();
      if (!this.isSilent()) {
         this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_HORSE_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
      }

   }

   public void fall(float distance, float damageMultiplier) {
      if (distance > 1.0F) {
         this.playSound(NO.ENTITY_HORSE_LAND, 0.4F, 1.0F);
      }

      int i = MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);
      if (i > 0) {
         this.attackEntityFrom(DamageSource.FALL, (float)i);
         if (this.isBeingRidden()) {
            Iterator var4 = this.getRecursivePassengers().iterator();

            while(var4.hasNext()) {
               Ig entity = (Ig)var4.next();
               entity.attackEntityFrom(DamageSource.FALL, (float)i);
            }
         }

         in iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2 - (double)this.prevRotationYaw, this.posZ));
         co block = iblockstate.getBlock();
         if (iblockstate.getMaterial() != hM.AIR && !this.isSilent()) {
            ia soundtype = block.getSoundType();
            this.world.playSound((ME)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
         }
      }

   }

   protected int getInventorySize() {
      return 2;
   }

   protected void initHorseChest() {
      ContainerHorseChest containerhorsechest = this.horseChest;
      this.horseChest = new ContainerHorseChest("HorseChest", this.getInventorySize());
      this.horseChest.setCustomName(this.getName());
      if (containerhorsechest != null) {
         containerhorsechest.removeInventoryChangeListener(this);
         int i = Math.min(containerhorsechest.getSizeInventory(), this.horseChest.getSizeInventory());

         for(int j = 0; j < i; ++j) {
            Qy itemstack = containerhorsechest.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
               this.horseChest.setInventorySlotContents(j, itemstack.copy());
            }
         }
      }

      this.horseChest.addInventoryChangeListener(this);
      this.updateHorseSlots();
   }

   protected void updateHorseSlots() {
      if (!this.world.isRemote) {
         this.setHorseSaddled(!this.horseChest.getStackInSlot(0).isEmpty() && this.canBeSaddled());
      }

   }

   public void onInventoryChanged(IInventory invBasic) {
      boolean flag = this.isHorseSaddled();
      this.updateHorseSlots();
      if (this.ticksExisted > 20 && !flag && this.isHorseSaddled()) {
         this.playSound(NO.ENTITY_HORSE_SADDLE, 0.5F, 1.0F);
      }

   }

   @Nullable
   protected Lw getClosestHorse(Ig entityIn, double distance) {
      double d0 = Double.MAX_VALUE;
      Ig entity = null;
      Iterator var7 = this.world.getEntitiesInAABBexcluding(entityIn, entityIn.getEntityBoundingBox().expand(distance, distance, distance), IS_HORSE_BREEDING).iterator();

      while(var7.hasNext()) {
         Ig entity1 = (Ig)var7.next();
         double d1 = entity1.getDistanceSq(entityIn.posX, entityIn.posY, entityIn.posZ);
         if (d1 < d0) {
            entity = entity1;
            d0 = d1;
         }
      }

      return (Lw)entity;
   }

   public double getHorseJumpStrength() {
      return this.getEntityAttribute(JUMP_STRENGTH).getAttributeValue();
   }

   @Nullable
   protected SoundEvent getDeathSound() {
      this.openHorseMouth();
      return null;
   }

   @Nullable
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      this.openHorseMouth();
      if (this.rand.nextInt(3) == 0) {
         this.makeHorseRear();
      }

      return null;
   }

   @Nullable
   protected SoundEvent getAmbientSound() {
      this.openHorseMouth();
      if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked()) {
         this.makeHorseRear();
      }

      return null;
   }

   public boolean canBeSaddled() {
      return true;
   }

   public boolean isHorseSaddled() {
      return this.getHorseWatchableBoolean(4);
   }

   @Nullable
   protected SoundEvent getAngrySound() {
      this.openHorseMouth();
      this.makeHorseRear();
      return null;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      if (!blockIn.getDefaultState().getMaterial().isLiquid()) {
         ia soundtype = blockIn.getSoundType();
         if (this.world.getBlockState(pos.up()).getBlock() == Nk.SNOW_LAYER) {
            soundtype = Nk.SNOW_LAYER.getSoundType();
         }

         if (this.isBeingRidden() && this.canGallop) {
            ++this.gallopTime;
            if (this.gallopTime > 5 && this.gallopTime % 3 == 0) {
               this.playGallopSound(soundtype);
            } else if (this.gallopTime <= 5) {
               this.playSound(NO.ENTITY_HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
            }
         } else if (soundtype == ia.WOOD) {
            this.playSound(NO.ENTITY_HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
         } else {
            this.playSound(NO.ENTITY_HORSE_STEP, soundtype.getVolume() * 0.15F, soundtype.getPitch());
         }
      }

   }

   protected void playGallopSound(ia p_190680_1_) {
      this.playSound(NO.ENTITY_HORSE_GALLOP, p_190680_1_.getVolume() * 0.15F, p_190680_1_.getPitch());
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(JUMP_STRENGTH);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(53.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.22499999403953552);
   }

   public int getMaxSpawnedInChunk() {
      return 6;
   }

   public int getMaxTemper() {
      return 100;
   }

   protected float getSoundVolume() {
      return 0.8F;
   }

   public int getTalkInterval() {
      return 400;
   }

   public void openGUI(ME playerEntity) {
      if (!this.world.isRemote && (!this.isBeingRidden() || this.isPassenger(playerEntity)) && this.isTame()) {
         this.horseChest.setCustomName(this.getName());
         playerEntity.openGuiHorseInventory(this, this.horseChest);
      }

   }

   protected boolean handleEating(ME player, Qy stack) {
      boolean flag = false;
      float f = 0.0F;
      int i = 0;
      int j = 0;
      OL item = stack.getItem();
      if (item == NK.WHEAT) {
         f = 2.0F;
         i = 20;
         j = 3;
      } else if (item == NK.SUGAR) {
         f = 1.0F;
         i = 30;
         j = 3;
      } else if (item == OL.getItemFromBlock(Nk.HAY_BLOCK)) {
         f = 20.0F;
         i = 180;
      } else if (item == NK.APPLE) {
         f = 3.0F;
         i = 60;
         j = 3;
      } else if (item == NK.GOLDEN_CARROT) {
         f = 4.0F;
         i = 60;
         j = 5;
         if (this.isTame() && this.getGrowingAge() == 0 && !this.isInLove()) {
            flag = true;
            this.setInLove(player);
         }
      } else if (item == NK.GOLDEN_APPLE) {
         f = 10.0F;
         i = 240;
         j = 10;
         if (this.isTame() && this.getGrowingAge() == 0 && !this.isInLove()) {
            flag = true;
            this.setInLove(player);
         }
      }

      if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
         this.heal(f);
         flag = true;
      }

      if (this.isChild() && i > 0) {
         this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0, 0.0, 0.0);
         if (!this.world.isRemote) {
            this.addGrowth(i);
         }

         flag = true;
      }

      if (j > 0 && (flag || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
         flag = true;
         if (!this.world.isRemote) {
            this.increaseTemper(j);
         }
      }

      if (flag) {
         this.eatingHorse();
      }

      return flag;
   }

   protected void mountTo(ME player) {
      player.rotationYaw = this.rotationYaw;
      player.rotationPitch = this.rotationPitch;
      this.setEatingHaystack(false);
      this.setRearing(false);
      if (!this.world.isRemote) {
         player.startRiding(this);
      }

   }

   protected boolean isMovementBlocked() {
      return super.isMovementBlocked() && this.isBeingRidden() && this.isHorseSaddled() || this.isEatingHaystack() || this.isRearing();
   }

   public boolean isBreedingItem(Qy stack) {
      return false;
   }

   private void moveTail() {
      this.tailCounter = 1;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (!this.world.isRemote && this.horseChest != null) {
         for(int i = 0; i < this.horseChest.getSizeInventory(); ++i) {
            Qy itemstack = this.horseChest.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
               this.entityDropItem(itemstack, 0.0F);
            }
         }
      }

   }

   public void onLivingUpdate() {
      if (this.rand.nextInt(200) == 0) {
         this.moveTail();
      }

      super.onLivingUpdate();
      if (!this.world.isRemote) {
         if (this.rand.nextInt(900) == 0 && this.deathTime == 0) {
            this.heal(1.0F);
         }

         if (this.canEatGrass()) {
            if (!this.isEatingHaystack() && !this.isBeingRidden() && this.rand.nextInt(300) == 0 && this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY) - 1, MathHelper.floor(this.posZ))).getBlock() == Nk.GRASS) {
               this.setEatingHaystack(true);
            }

            if (this.isEatingHaystack() && ++this.eatingCounter > 50) {
               this.eatingCounter = 0;
               this.setEatingHaystack(false);
            }
         }

         this.followMother();
      }

   }

   protected void followMother() {
      if (this.isBreeding() && this.isChild() && !this.isEatingHaystack()) {
         Lw abstracthorse = this.getClosestHorse(this, 16.0);
         if (abstracthorse != null && this.getDistanceSq(abstracthorse) > 4.0) {
            this.navigator.getPathToEntityLiving(abstracthorse);
         }
      }

   }

   public boolean canEatGrass() {
      return true;
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
         this.openMouthCounter = 0;
         this.setHorseWatchableBoolean(64, false);
      }

      if (this.canPassengerSteer() && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
         this.jumpRearingCounter = 0;
         this.setRearing(false);
      }

      if (this.tailCounter > 0 && ++this.tailCounter > 8) {
         this.tailCounter = 0;
      }

      if (this.sprintCounter > 0) {
         ++this.sprintCounter;
         if (this.sprintCounter > 300) {
            this.sprintCounter = 0;
         }
      }

      this.prevHeadLean = this.headLean;
      if (this.isEatingHaystack()) {
         this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;
         if (this.headLean > 1.0F) {
            this.headLean = 1.0F;
         }
      } else {
         this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;
         if (this.headLean < 0.0F) {
            this.headLean = 0.0F;
         }
      }

      this.prevRearingAmount = this.rearingAmount;
      if (this.isRearing()) {
         this.headLean = 0.0F;
         this.prevHeadLean = this.headLean;
         this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;
         if (this.rearingAmount > 1.0F) {
            this.rearingAmount = 1.0F;
         }
      } else {
         this.allowStandSliding = false;
         this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;
         if (this.rearingAmount < 0.0F) {
            this.rearingAmount = 0.0F;
         }
      }

      this.prevMouthOpenness = this.mouthOpenness;
      if (this.getHorseWatchableBoolean(64)) {
         this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;
         if (this.mouthOpenness > 1.0F) {
            this.mouthOpenness = 1.0F;
         }
      } else {
         this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;
         if (this.mouthOpenness < 0.0F) {
            this.mouthOpenness = 0.0F;
         }
      }

   }

   private void openHorseMouth() {
      if (!this.world.isRemote) {
         this.openMouthCounter = 1;
         this.setHorseWatchableBoolean(64, true);
      }

   }

   public void setEatingHaystack(boolean p_110227_1_) {
      this.setHorseWatchableBoolean(16, p_110227_1_);
   }

   public void setRearing(boolean rearing) {
      if (rearing) {
         this.setEatingHaystack(false);
      }

      this.setHorseWatchableBoolean(32, rearing);
   }

   private void makeHorseRear() {
      if (this.canPassengerSteer()) {
         this.jumpRearingCounter = 1;
         this.setRearing(true);
      }

   }

   public void makeMad() {
      this.makeHorseRear();
      SoundEvent soundevent = this.getAngrySound();
      if (soundevent != null) {
         this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   public boolean setTamedBy(ME player) {
      this.setOwnerUniqueId(player.getUniqueID());
      this.setHorseTamed(true);
      if (player instanceof MG) {
         bY.TAME_ANIMAL.trigger((MG)player, this);
      }

      this.world.setEntityState(this, (byte)7);
      return true;
   }

   public void travel(float strafe, float vertical, float forward) {
      if (this.isBeingRidden() && this.canBeSteered() && this.isHorseSaddled()) {
         Iw entitylivingbase = (Iw)this.getControllingPassenger();
         this.rotationYaw = entitylivingbase.rotationYaw;
         this.prevRotationYaw = this.rotationYaw;
         this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
         this.setRotation(this.rotationYaw, this.rotationPitch);
         this.renderYawOffset = this.rotationYaw;
         this.rotationYawHead = this.renderYawOffset;
         strafe = entitylivingbase.moveStrafing * 0.5F;
         forward = entitylivingbase.moveForward;
         if (forward <= 0.0F) {
            forward *= 0.25F;
            this.gallopTime = 0;
         }

         if (this.onGround && this.jumpPower == 0.0F && this.isRearing() && !this.allowStandSliding) {
            strafe = 0.0F;
            forward = 0.0F;
         }

         if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {
            this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;
            if (this.isPotionActive(NL.JUMP_BOOST)) {
               this.motionY += (double)((float)(this.getActivePotionEffect(NL.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
            }

            this.setHorseJumping(true);
            this.isAirBorne = true;
            if (forward > 0.0F) {
               float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
               float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
               this.motionX += (double)(-0.4F * f * this.jumpPower);
               this.motionZ += (double)(0.4F * f1 * this.jumpPower);
               this.playSound(NO.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
            }

            this.jumpPower = 0.0F;
         }

         this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
         if (this.canPassengerSteer()) {
            this.setAIMoveSpeed((float)this.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue());
            super.travel(strafe, vertical, forward);
         } else if (entitylivingbase instanceof ME) {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
         }

         if (this.onGround) {
            this.jumpPower = 0.0F;
            this.setHorseJumping(false);
         }

         this.prevLimbSwingAmount = this.limbSwingAmount;
         double d1 = this.posX - this.prevPosX;
         double d0 = this.posZ - this.prevPosZ;
         float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
         if (f2 > 1.0F) {
            f2 = 1.0F;
         }

         this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
         this.limbSwing += this.limbSwingAmount;
      } else {
         this.jumpMovementFactor = 0.02F;
         super.travel(strafe, vertical, forward);
      }

   }

   public static void registerFixesAbstractHorse(DataFixer fixer, Class<?> entityClass) {
      Iu.registerFixesMob(fixer, entityClass);
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(entityClass, new String[]{"SaddleItem"}));
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("EatingHaystack", this.isEatingHaystack());
      compound.setBoolean("Bred", this.isBreeding());
      compound.setInteger("Temper", this.getTemper());
      compound.setBoolean("Tame", this.isTame());
      if (this.getOwnerUniqueId() != null) {
         compound.setString("OwnerUUID", this.getOwnerUniqueId().toString());
      }

      if (!this.horseChest.getStackInSlot(0).isEmpty()) {
         compound.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new QQ()));
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setEatingHaystack(compound.getBoolean("EatingHaystack"));
      this.setBreeding(compound.getBoolean("Bred"));
      this.setTemper(compound.getInteger("Temper"));
      this.setHorseTamed(compound.getBoolean("Tame"));
      String s;
      if (compound.hasKey("OwnerUUID", 8)) {
         s = compound.getString("OwnerUUID");
      } else {
         String s1 = compound.getString("Owner");
         s = Xg.convertMobOwnerIfNeeded(this.getServer(), s1);
      }

      if (!s.isEmpty()) {
         this.setOwnerUniqueId(UUID.fromString(s));
      }

      FZ iattributeinstance = this.getAttributeMap().getAttributeInstanceByName("Speed");
      if (iattributeinstance != null) {
         this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(iattributeinstance.getBaseValue() * 0.25);
      }

      if (compound.hasKey("SaddleItem", 10)) {
         Qy itemstack = new Qy(compound.getCompoundTag("SaddleItem"));
         if (itemstack.getItem() == NK.SADDLE) {
            this.horseChest.setInventorySlotContents(0, itemstack);
         }
      }

      this.updateHorseSlots();
   }

   public boolean canMateWith(Ly otherAnimal) {
      return false;
   }

   protected boolean canMate() {
      return !this.isBeingRidden() && !this.isRiding() && this.isTame() && !this.isChild() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
   }

   @Nullable
   public Ih createChild(Ih ageable) {
      return null;
   }

   protected void setOffspringAttributes(Ih p_190681_1_, Lw p_190681_2_) {
      double d0 = this.getEntityAttribute(Ni.MAX_HEALTH).getBaseValue() + p_190681_1_.getEntityAttribute(Ni.MAX_HEALTH).getBaseValue() + (double)this.getModifiedMaxHealth();
      p_190681_2_.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(d0 / 3.0);
      double d1 = this.getEntityAttribute(JUMP_STRENGTH).getBaseValue() + p_190681_1_.getEntityAttribute(JUMP_STRENGTH).getBaseValue() + this.getModifiedJumpStrength();
      p_190681_2_.getEntityAttribute(JUMP_STRENGTH).setBaseValue(d1 / 3.0);
      double d2 = this.getEntityAttribute(Ni.MOVEMENT_SPEED).getBaseValue() + p_190681_1_.getEntityAttribute(Ni.MOVEMENT_SPEED).getBaseValue() + this.getModifiedMovementSpeed();
      p_190681_2_.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(d2 / 3.0);
   }

   public boolean canBeSteered() {
      return this.getControllingPassenger() instanceof Iw;
   }

   public float getGrassEatingAmount(float p_110258_1_) {
      return this.prevHeadLean + (this.headLean - this.prevHeadLean) * p_110258_1_;
   }

   public float getRearingAmount(float p_110223_1_) {
      return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * p_110223_1_;
   }

   public float getMouthOpennessAngle(float p_110201_1_) {
      return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * p_110201_1_;
   }

   public void setJumpPower(int jumpPowerIn) {
      if (this.isHorseSaddled()) {
         if (jumpPowerIn < 0) {
            jumpPowerIn = 0;
         } else {
            this.allowStandSliding = true;
            this.makeHorseRear();
         }

         if (jumpPowerIn >= 90) {
            this.jumpPower = 1.0F;
         } else {
            this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
         }
      }

   }

   public boolean canJump() {
      return this.isHorseSaddled();
   }

   public void handleStartJump(int p_184775_1_) {
      this.allowStandSliding = true;
      this.makeHorseRear();
   }

   public void handleStopJump() {
   }

   protected void spawnHorseParticles(boolean p_110216_1_) {
      EnumParticleTypes enumparticletypes = p_110216_1_ ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;

      for(int i = 0; i < 7; ++i) {
         double d0 = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
      }

   }

   public void handleStatusUpdate(byte id) {
      if (id == 7) {
         this.spawnHorseParticles(true);
      } else if (id == 6) {
         this.spawnHorseParticles(false);
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public void updatePassenger(Ig passenger) {
      super.updatePassenger(passenger);
      if (passenger instanceof Iu) {
         Iu entityliving = (Iu)passenger;
         this.renderYawOffset = entityliving.renderYawOffset;
      }

      if (this.prevRearingAmount > 0.0F) {
         float f3 = MathHelper.sin(this.renderYawOffset * 0.017453292F);
         float f = MathHelper.cos(this.renderYawOffset * 0.017453292F);
         float f1 = 0.7F * this.prevRearingAmount;
         float f2 = 0.15F * this.prevRearingAmount;
         passenger.setPosition(this.posX + (double)(f1 * f3), this.posY + this.getMountedYOffset() + passenger.getYOffset() + (double)f2, this.posZ - (double)(f1 * f));
         if (passenger instanceof Iw) {
            ((Iw)passenger).renderYawOffset = this.renderYawOffset;
         }
      }

   }

   protected float getModifiedMaxHealth() {
      return 15.0F + (float)this.rand.nextInt(8) + (float)this.rand.nextInt(9);
   }

   protected double getModifiedJumpStrength() {
      return 0.4000000059604645 + this.rand.nextDouble() * 0.2 + this.rand.nextDouble() * 0.2 + this.rand.nextDouble() * 0.2;
   }

   protected double getModifiedMovementSpeed() {
      return (0.44999998807907104 + this.rand.nextDouble() * 0.3 + this.rand.nextDouble() * 0.3 + this.rand.nextDouble() * 0.3) * 0.25;
   }

   public boolean isOnLadder() {
      return false;
   }

   public float getEyeHeight() {
      return this.height;
   }

   public boolean wearsArmor() {
      return false;
   }

   public boolean isArmor(Qy stack) {
      return false;
   }

   public boolean replaceItemInInventory(int inventorySlot, Qy itemStackIn) {
      int i = inventorySlot - 400;
      if (i >= 0 && i < 2 && i < this.horseChest.getSizeInventory()) {
         if (i == 0 && itemStackIn.getItem() != NK.SADDLE) {
            return false;
         } else if (i == 1 && (!this.wearsArmor() || !this.isArmor(itemStackIn))) {
            return false;
         } else {
            this.horseChest.setInventorySlotContents(i, itemStackIn);
            this.updateHorseSlots();
            return true;
         }
      } else {
         int j = inventorySlot - 500 + 2;
         if (j >= 2 && j < this.horseChest.getSizeInventory()) {
            this.horseChest.setInventorySlotContents(j, itemStackIn);
            return true;
         } else {
            return false;
         }
      }
   }

   @Nullable
   public Ig getControllingPassenger() {
      return this.getPassengers().isEmpty() ? null : (Ig)this.getPassengers().get(0);
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      livingdata = super.onInitialSpawn(difficulty, livingdata);
      if (this.rand.nextInt(5) == 0) {
         this.setGrowingAge(-24000);
      }

      return livingdata;
   }

   static {
      STATUS = Rv.createKey(Lw.class, Rt.BYTE);
      OWNER_UNIQUE_ID = Rv.createKey(Lw.class, Rt.OPTIONAL_UNIQUE_ID);
   }
}
