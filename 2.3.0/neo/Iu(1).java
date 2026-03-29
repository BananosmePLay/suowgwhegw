package neo;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class Iu extends Iw {
   private static final Rd<Byte> AI_FLAGS;
   public int livingSoundTime;
   protected int experienceValue;
   private final Hv lookHelper;
   protected Hx moveHelper;
   protected Hu jumpHelper;
   private final Ij bodyHelper;
   protected VL navigator;
   protected final Hi tasks;
   protected final Hi targetTasks;
   private Iw attackTarget;
   private final Hy senses;
   private final NonNullList<Qy> inventoryHands;
   protected float[] inventoryHandsDropChances;
   private final NonNullList<Qy> inventoryArmor;
   protected float[] inventoryArmorDropChances;
   private boolean canPickUpLoot;
   private boolean persistenceRequired;
   private final Map<VQ, Float> mapPathPriority;
   private ResourceLocation deathLootTable;
   private long deathLootTableSeed;
   private boolean isLeashed;
   private Ig leashHolder;
   private QQ leashNBTTag;
   private UUID teamUuid;
   private String teamUuidString;

   public Iu(bij worldIn) {
      super(worldIn);
      this.inventoryHands = NonNullList.withSize(2, Qy.EMPTY);
      this.inventoryHandsDropChances = new float[2];
      this.inventoryArmor = NonNullList.withSize(4, Qy.EMPTY);
      this.inventoryArmorDropChances = new float[4];
      this.mapPathPriority = Maps.newEnumMap(VQ.class);
      this.teamUuid = null;
      this.teamUuidString = null;
      this.tasks = new Hi(worldIn != null && worldIn.profiler != null ? worldIn.profiler : null);
      this.targetTasks = new Hi(worldIn != null && worldIn.profiler != null ? worldIn.profiler : null);
      this.lookHelper = new Hv(this);
      this.moveHelper = new Hx(this);
      this.jumpHelper = new Hu(this);
      this.bodyHelper = this.createBodyHelper();
      this.navigator = this.createNavigator(worldIn);
      this.senses = new Hy(this);
      Arrays.fill(this.inventoryArmorDropChances, 0.085F);
      Arrays.fill(this.inventoryHandsDropChances, 0.085F);
      if (worldIn != null && !worldIn.isRemote) {
         this.initEntityAI();
      }

   }

   protected void initEntityAI() {
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(Ni.FOLLOW_RANGE).setBaseValue(16.0);
   }

   protected VL createNavigator(bij worldIn) {
      return new VO(this, worldIn);
   }

   public float getPathPriority(VQ nodeType) {
      Float f = (Float)this.mapPathPriority.get(nodeType);
      return f == null ? nodeType.getPriority() : f;
   }

   public void setPathPriority(VQ nodeType, float priority) {
      this.mapPathPriority.put(nodeType, priority);
   }

   protected Ij createBodyHelper() {
      return new Ij(this);
   }

   public Hv getLookHelper() {
      return this.lookHelper;
   }

   public Hx getMoveHelper() {
      return this.moveHelper;
   }

   public Hu getJumpHelper() {
      return this.jumpHelper;
   }

   public VL getNavigator() {
      return this.navigator;
   }

   public Hy getEntitySenses() {
      return this.senses;
   }

   @Nullable
   public Iw getAttackTarget() {
      return this.attackTarget;
   }

   public void setAttackTarget(@Nullable Iw entitylivingbaseIn) {
      this.attackTarget = entitylivingbaseIn;
      bnK.callVoid(bnK.ForgeHooks_onLivingSetAttackTarget, this, entitylivingbaseIn);
   }

   public boolean canAttackClass(Class<? extends Iw> cls) {
      return cls != JW.class;
   }

   public void eatGrassBonus() {
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(AI_FLAGS, (byte)0);
   }

   public int getTalkInterval() {
      return 80;
   }

   public void playLivingSound() {
      SoundEvent soundevent = this.getAmbientSound();
      if (soundevent != null) {
         this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   public void onEntityUpdate() {
      super.onEntityUpdate();
      this.world.profiler.startSection("mobBaseTick");
      if (this.isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++) {
         this.applyEntityAI();
         this.playLivingSound();
      }

      this.world.profiler.endSection();
   }

   protected void playHurtSound(DamageSource source) {
      this.applyEntityAI();
      super.playHurtSound(source);
   }

   private void applyEntityAI() {
      this.livingSoundTime = -this.getTalkInterval();
   }

   protected int getExperiencePoints(ME player) {
      if (this.experienceValue > 0) {
         int i = this.experienceValue;

         int k;
         for(k = 0; k < this.inventoryArmor.size(); ++k) {
            if (!((Qy)this.inventoryArmor.get(k)).isEmpty() && this.inventoryArmorDropChances[k] <= 1.0F) {
               i += 1 + this.rand.nextInt(3);
            }
         }

         for(k = 0; k < this.inventoryHands.size(); ++k) {
            if (!((Qy)this.inventoryHands.get(k)).isEmpty() && this.inventoryHandsDropChances[k] <= 1.0F) {
               i += 1 + this.rand.nextInt(3);
            }
         }

         return i;
      } else {
         return this.experienceValue;
      }
   }

   public void spawnExplosionParticle() {
      if (this.world.isRemote) {
         for(int i = 0; i < 20; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d0 * 10.0, this.posY + (double)(this.rand.nextFloat() * this.height) - d1 * 10.0, this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d2 * 10.0, d0, d1, d2);
         }
      } else {
         this.world.setEntityState(this, (byte)20);
      }

   }

   public void handleStatusUpdate(byte id) {
      if (id == 20) {
         this.spawnExplosionParticle();
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public void onUpdate() {
      if (XH.isSmoothWorld() && this.canSkipUpdate()) {
         this.onUpdateMinimal();
      } else {
         super.onUpdate();
         if (!this.world.isRemote) {
            this.updateLeashedState();
            if (this.ticksExisted % 5 == 0) {
               boolean flag = !(this.getControllingPassenger() instanceof Iu);
               boolean flag1 = !(this.getRidingEntity() instanceof IR);
               this.tasks.setControlFlag(1, flag);
               this.tasks.setControlFlag(4, flag && flag1);
               this.tasks.setControlFlag(2, flag);
            }
         }
      }

   }

   protected float updateDistance(float p_110146_1_, float p_110146_2_) {
      this.bodyHelper.updateRenderAngles();
      return p_110146_2_;
   }

   @Nullable
   protected SoundEvent getAmbientSound() {
      return null;
   }

   @Nullable
   protected OL getDropItem() {
      return null;
   }

   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      OL item = this.getDropItem();
      if (item != null) {
         int i = this.rand.nextInt(3);
         if (lootingModifier > 0) {
            i += this.rand.nextInt(lootingModifier + 1);
         }

         for(int j = 0; j < i; ++j) {
            this.dropItem(item, 1);
         }
      }

   }

   public static void registerFixesMob(DataFixer fixer, Class<?> name) {
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackDataLists(name, new String[]{"ArmorItems", "HandItems"}));
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("CanPickUpLoot", this.canPickUpLoot());
      compound.setBoolean("PersistenceRequired", this.persistenceRequired);
      QW nbttaglist = new QW();

      QQ nbttagcompound;
      for(Iterator var3 = this.inventoryArmor.iterator(); var3.hasNext(); nbttaglist.appendTag(nbttagcompound)) {
         Qy itemstack = (Qy)var3.next();
         nbttagcompound = new QQ();
         if (!itemstack.isEmpty()) {
            itemstack.writeToNBT(nbttagcompound);
         }
      }

      compound.setTag("ArmorItems", nbttaglist);
      QW nbttaglist1 = new QW();

      QQ nbttagcompound2;
      for(Iterator var11 = this.inventoryHands.iterator(); var11.hasNext(); nbttaglist1.appendTag(nbttagcompound2)) {
         Qy itemstack1 = (Qy)var11.next();
         nbttagcompound2 = new QQ();
         if (!itemstack1.isEmpty()) {
            itemstack1.writeToNBT(nbttagcompound2);
         }
      }

      compound.setTag("HandItems", nbttaglist1);
      QW nbttaglist2 = new QW();
      float[] var14 = this.inventoryArmorDropChances;
      int var16 = var14.length;

      int var7;
      for(var7 = 0; var7 < var16; ++var7) {
         float f = var14[var7];
         nbttaglist2.appendTag(new QT(f));
      }

      compound.setTag("ArmorDropChances", nbttaglist2);
      QW nbttaglist3 = new QW();
      float[] var17 = this.inventoryHandsDropChances;
      var7 = var17.length;

      for(int var19 = 0; var19 < var7; ++var19) {
         float f1 = var17[var19];
         nbttaglist3.appendTag(new QT(f1));
      }

      compound.setTag("HandDropChances", nbttaglist3);
      compound.setBoolean("Leashed", this.isLeashed);
      if (this.leashHolder != null) {
         nbttagcompound2 = new QQ();
         if (this.leashHolder instanceof Iw) {
            UUID uuid = this.leashHolder.getUniqueID();
            nbttagcompound2.setUniqueId("UUID", uuid);
         } else if (this.leashHolder instanceof Io) {
            BlockPos blockpos = ((Io)this.leashHolder).getHangingPosition();
            nbttagcompound2.setInteger("X", blockpos.getX());
            nbttagcompound2.setInteger("Y", blockpos.getY());
            nbttagcompound2.setInteger("Z", blockpos.getZ());
         }

         compound.setTag("Leash", nbttagcompound2);
      }

      compound.setBoolean("LeftHanded", this.isLeftHanded());
      if (this.deathLootTable != null) {
         compound.setString("DeathLootTable", this.deathLootTable.toString());
         if (this.deathLootTableSeed != 0L) {
            compound.setLong("DeathLootTableSeed", this.deathLootTableSeed);
         }
      }

      if (this.isAIDisabled()) {
         compound.setBoolean("NoAI", this.isAIDisabled());
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("CanPickUpLoot", 1)) {
         this.setCanPickUpLoot(compound.getBoolean("CanPickUpLoot"));
      }

      this.persistenceRequired = compound.getBoolean("PersistenceRequired");
      QW nbttaglist3;
      int l;
      if (compound.hasKey("ArmorItems", 9)) {
         nbttaglist3 = compound.getTagList("ArmorItems", 10);

         for(l = 0; l < this.inventoryArmor.size(); ++l) {
            this.inventoryArmor.set(l, new Qy(nbttaglist3.getCompoundTagAt(l)));
         }
      }

      if (compound.hasKey("HandItems", 9)) {
         nbttaglist3 = compound.getTagList("HandItems", 10);

         for(l = 0; l < this.inventoryHands.size(); ++l) {
            this.inventoryHands.set(l, new Qy(nbttaglist3.getCompoundTagAt(l)));
         }
      }

      if (compound.hasKey("ArmorDropChances", 9)) {
         nbttaglist3 = compound.getTagList("ArmorDropChances", 5);

         for(l = 0; l < nbttaglist3.tagCount(); ++l) {
            this.inventoryArmorDropChances[l] = nbttaglist3.getFloatAt(l);
         }
      }

      if (compound.hasKey("HandDropChances", 9)) {
         nbttaglist3 = compound.getTagList("HandDropChances", 5);

         for(l = 0; l < nbttaglist3.tagCount(); ++l) {
            this.inventoryHandsDropChances[l] = nbttaglist3.getFloatAt(l);
         }
      }

      this.isLeashed = compound.getBoolean("Leashed");
      if (this.isLeashed && compound.hasKey("Leash", 10)) {
         this.leashNBTTag = compound.getCompoundTag("Leash");
      }

      this.setLeftHanded(compound.getBoolean("LeftHanded"));
      if (compound.hasKey("DeathLootTable", 8)) {
         this.deathLootTable = new ResourceLocation(compound.getString("DeathLootTable"));
         this.deathLootTableSeed = compound.getLong("DeathLootTableSeed");
      }

      this.setNoAI(compound.getBoolean("NoAI"));
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return null;
   }

   protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
      ResourceLocation resourcelocation = this.deathLootTable;
      if (resourcelocation == null) {
         resourcelocation = this.getLootTable();
      }

      if (resourcelocation != null) {
         bhp loottable = this.world.getLootTableManager().getLootTableFromLocation(resourcelocation);
         this.deathLootTable = null;
         bhd lootcontext$builder = (new bhd((bis)this.world)).withLootedEntity(this).withDamageSource(source);
         if (wasRecentlyHit && this.attackingPlayer != null) {
            lootcontext$builder = lootcontext$builder.withPlayer(this.attackingPlayer).withLuck(this.attackingPlayer.getLuck());
         }

         Iterator var7 = loottable.generateLootForPools(this.deathLootTableSeed == 0L ? this.rand : new Random(this.deathLootTableSeed), lootcontext$builder.build()).iterator();

         while(var7.hasNext()) {
            Qy itemstack = (Qy)var7.next();
            this.entityDropItem(itemstack, 0.0F);
         }

         this.dropEquipment(wasRecentlyHit, lootingModifier);
      } else {
         super.dropLoot(wasRecentlyHit, lootingModifier, source);
      }

   }

   public void setMoveForward(float amount) {
      this.moveForward = amount;
   }

   public void setMoveVertical(float amount) {
      this.moveVertical = amount;
   }

   public void setMoveStrafing(float amount) {
      this.moveStrafing = amount;
   }

   public void setAIMoveSpeed(float speedIn) {
      super.setAIMoveSpeed(speedIn);
      this.setMoveForward(speedIn);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.world.profiler.startSection("looting");
      boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
      if (bnK.ForgeEventFactory_getMobGriefingEvent.exists()) {
         flag = bnK.callBoolean(bnK.ForgeEventFactory_getMobGriefingEvent, this.world, this);
      }

      if (!this.world.isRemote && this.canPickUpLoot() && !this.dead && flag) {
         Iterator var2 = this.world.getEntitiesWithinAABB(IY.class, this.getEntityBoundingBox().grow(1.0, 0.0, 1.0)).iterator();

         while(var2.hasNext()) {
            IY entityitem = (IY)var2.next();
            if (!entityitem.isDead && !entityitem.getItem().isEmpty() && !entityitem.cannotPickup()) {
               this.updateEquipmentIfNeeded(entityitem);
            }
         }
      }

      this.world.profiler.endSection();
   }

   protected void updateEquipmentIfNeeded(IY itemEntity) {
      Qy itemstack = itemEntity.getItem();
      EntityEquipmentSlot entityequipmentslot = getSlotForItemStack(itemstack);
      boolean flag = true;
      Qy itemstack1 = this.getItemStackFromSlot(entityequipmentslot);
      if (!itemstack1.isEmpty()) {
         if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.HAND) {
            if (itemstack.getItem() instanceof Qz && !(itemstack1.getItem() instanceof Qz)) {
               flag = true;
            } else if (itemstack.getItem() instanceof Qz && itemstack1.getItem() instanceof Qz) {
               Qz itemsword = (Qz)itemstack.getItem();
               Qz itemsword1 = (Qz)itemstack1.getItem();
               if (itemsword.getAttackDamage() == itemsword1.getAttackDamage()) {
                  flag = itemstack.getMetadata() > itemstack1.getMetadata() || itemstack.hasTagCompound() && !itemstack1.hasTagCompound();
               } else {
                  flag = itemsword.getAttackDamage() > itemsword1.getAttackDamage();
               }
            } else if (itemstack.getItem() instanceof Pd && itemstack1.getItem() instanceof Pd) {
               flag = itemstack.hasTagCompound() && !itemstack1.hasTagCompound();
            } else {
               flag = false;
            }
         } else if (itemstack.getItem() instanceof OR && !(itemstack1.getItem() instanceof OR)) {
            flag = true;
         } else if (itemstack.getItem() instanceof OR && itemstack1.getItem() instanceof OR && !Ft.hasBindingCurse(itemstack1)) {
            OR itemarmor = (OR)itemstack.getItem();
            OR itemarmor1 = (OR)itemstack1.getItem();
            if (itemarmor.damageReduceAmount == itemarmor1.damageReduceAmount) {
               flag = itemstack.getMetadata() > itemstack1.getMetadata() || itemstack.hasTagCompound() && !itemstack1.hasTagCompound();
            } else {
               flag = itemarmor.damageReduceAmount > itemarmor1.damageReduceAmount;
            }
         } else {
            flag = false;
         }
      }

      if (flag && this.canEquipItem(itemstack)) {
         double d0;
         switch (entityequipmentslot.getSlotType()) {
            case HAND:
               d0 = (double)this.inventoryHandsDropChances[entityequipmentslot.getIndex()];
               break;
            case ARMOR:
               d0 = (double)this.inventoryArmorDropChances[entityequipmentslot.getIndex()];
               break;
            default:
               d0 = 0.0;
         }

         if (!itemstack1.isEmpty() && (double)(this.rand.nextFloat() - 0.1F) < d0) {
            this.entityDropItem(itemstack1, 0.0F);
         }

         this.setItemStackToSlot(entityequipmentslot, itemstack);
         switch (entityequipmentslot.getSlotType()) {
            case HAND:
               this.inventoryHandsDropChances[entityequipmentslot.getIndex()] = 2.0F;
               break;
            case ARMOR:
               this.inventoryArmorDropChances[entityequipmentslot.getIndex()] = 2.0F;
         }

         this.persistenceRequired = true;
         this.onItemPickup(itemEntity, itemstack.getCount());
         itemEntity.setDead();
      }

   }

   protected boolean canEquipItem(Qy stack) {
      return true;
   }

   protected boolean canDespawn() {
      return true;
   }

   protected void despawnEntity() {
      Object object = null;
      Object object1 = bnK.getFieldValue(bnK.Event_Result_DEFAULT);
      Object object2 = bnK.getFieldValue(bnK.Event_Result_DENY);
      if (this.persistenceRequired) {
         this.idleTime = 0;
      } else if ((this.idleTime & 31) == 31 && (object = bnK.call(bnK.ForgeEventFactory_canEntityDespawn, this)) != object1) {
         if (object == object2) {
            this.idleTime = 0;
         } else {
            this.setDead();
         }
      } else {
         Ig entity = this.world.getClosestPlayerToEntity(this, -1.0);
         if (entity != null) {
            double d0 = entity.posX - this.posX;
            double d1 = entity.posY - this.posY;
            double d2 = entity.posZ - this.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (this.canDespawn() && d3 > 16384.0) {
               this.setDead();
            }

            if (this.idleTime > 600 && this.rand.nextInt(800) == 0 && d3 > 1024.0 && this.canDespawn()) {
               this.setDead();
            } else if (d3 < 1024.0) {
               this.idleTime = 0;
            }
         }
      }

   }

   protected final void updateEntityActionState() {
      ++this.idleTime;
      this.world.profiler.startSection("checkDespawn");
      this.despawnEntity();
      this.world.profiler.endSection();
      this.world.profiler.startSection("sensing");
      this.senses.clearSensingCache();
      this.world.profiler.endSection();
      this.world.profiler.startSection("targetSelector");
      this.targetTasks.onUpdateTasks();
      this.world.profiler.endSection();
      this.world.profiler.startSection("goalSelector");
      this.tasks.onUpdateTasks();
      this.world.profiler.endSection();
      this.world.profiler.startSection("navigation");
      this.navigator.onUpdateNavigation();
      this.world.profiler.endSection();
      this.world.profiler.startSection("mob tick");
      this.updateAITasks();
      this.world.profiler.endSection();
      if (this.isRiding() && this.getRidingEntity() instanceof Iu) {
         Iu entityliving = (Iu)this.getRidingEntity();
         entityliving.getNavigator().setPath(this.getNavigator().getPath(), 1.5);
         entityliving.getMoveHelper().read(this.getMoveHelper());
      }

      this.world.profiler.startSection("controls");
      this.world.profiler.startSection("move");
      this.moveHelper.onUpdateMoveHelper();
      this.world.profiler.endStartSection("look");
      this.lookHelper.onUpdateLook();
      this.world.profiler.endStartSection("jump");
      this.jumpHelper.doJump();
      this.world.profiler.endSection();
      this.world.profiler.endSection();
   }

   protected void updateAITasks() {
   }

   public int getVerticalFaceSpeed() {
      return 40;
   }

   public int getHorizontalFaceSpeed() {
      return 10;
   }

   public void faceEntity(Ig entityIn, float maxYawIncrease, float maxPitchIncrease) {
      double d0 = entityIn.posX - this.posX;
      double d1 = entityIn.posZ - this.posZ;
      double d2;
      if (entityIn instanceof Iw) {
         Iw entitylivingbase = (Iw)entityIn;
         d2 = entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() - (this.posY + (double)this.getEyeHeight());
      } else {
         d2 = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0 - (this.posY + (double)this.getEyeHeight());
      }

      double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1);
      float f = (float)(MathHelper.atan2(d1, d0) * 57.29577951308232) - 90.0F;
      float f1 = (float)(-(MathHelper.atan2(d2, d3) * 57.29577951308232));
      this.rotationPitch = this.updateRotation(this.rotationPitch, f1, maxPitchIncrease);
      this.rotationYaw = this.updateRotation(this.rotationYaw, f, maxYawIncrease);
   }

   private float updateRotation(float angle, float targetAngle, float maxIncrease) {
      float f = MathHelper.wrapDegrees(targetAngle - angle);
      if (f > maxIncrease) {
         f = maxIncrease;
      }

      if (f < -maxIncrease) {
         f = -maxIncrease;
      }

      return angle + f;
   }

   public boolean getCanSpawnHere() {
      in iblockstate = this.world.getBlockState((new BlockPos(this)).down());
      return iblockstate.canEntitySpawn(this);
   }

   public boolean isNotColliding() {
      return !this.world.containsAnyLiquid(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
   }

   public float getRenderSizeModifier() {
      return 1.0F;
   }

   public int getMaxSpawnedInChunk() {
      return 4;
   }

   public int getMaxFallHeight() {
      if (this.getAttackTarget() == null) {
         return 3;
      } else {
         int i = (int)(this.getHealth() - this.getMaxHealth() * 0.33F);
         i -= (3 - this.world.getDifficulty().getId()) * 4;
         if (i < 0) {
            i = 0;
         }

         return i + 3;
      }
   }

   public Iterable<Qy> getHeldEquipment() {
      return this.inventoryHands;
   }

   public Iterable<Qy> getArmorInventoryList() {
      return this.inventoryArmor;
   }

   public Qy getItemStackFromSlot(EntityEquipmentSlot slotIn) {
      switch (slotIn.getSlotType()) {
         case HAND:
            return (Qy)this.inventoryHands.get(slotIn.getIndex());
         case ARMOR:
            return (Qy)this.inventoryArmor.get(slotIn.getIndex());
         default:
            return Qy.EMPTY;
      }
   }

   public void setItemStackToSlot(EntityEquipmentSlot slotIn, Qy stack) {
      switch (slotIn.getSlotType()) {
         case HAND:
            this.inventoryHands.set(slotIn.getIndex(), stack);
            break;
         case ARMOR:
            this.inventoryArmor.set(slotIn.getIndex(), stack);
      }

   }

   protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
      EntityEquipmentSlot[] var3 = EntityEquipmentSlot.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EntityEquipmentSlot entityequipmentslot = var3[var5];
         Qy itemstack = this.getItemStackFromSlot(entityequipmentslot);
         double d0;
         switch (entityequipmentslot.getSlotType()) {
            case HAND:
               d0 = (double)this.inventoryHandsDropChances[entityequipmentslot.getIndex()];
               break;
            case ARMOR:
               d0 = (double)this.inventoryArmorDropChances[entityequipmentslot.getIndex()];
               break;
            default:
               d0 = 0.0;
         }

         boolean flag = d0 > 1.0;
         if (!itemstack.isEmpty() && !Ft.hasVanishingCurse(itemstack) && (wasRecentlyHit || flag) && (double)(this.rand.nextFloat() - (float)lootingModifier * 0.01F) < d0) {
            if (!flag && itemstack.isItemStackDamageable()) {
               itemstack.setItemDamage(itemstack.getMaxDamage() - this.rand.nextInt(1 + this.rand.nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
            }

            this.entityDropItem(itemstack, 0.0F);
         }
      }

   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      if (this.rand.nextFloat() < 0.15F * difficulty.getClampedAdditionalDifficulty()) {
         int i = this.rand.nextInt(2);
         float f = this.world.getDifficulty() == baV.HARD ? 0.1F : 0.25F;
         if (this.rand.nextFloat() < 0.095F) {
            ++i;
         }

         if (this.rand.nextFloat() < 0.095F) {
            ++i;
         }

         if (this.rand.nextFloat() < 0.095F) {
            ++i;
         }

         boolean flag = true;
         EntityEquipmentSlot[] var5 = EntityEquipmentSlot.values();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            EntityEquipmentSlot entityequipmentslot = var5[var7];
            if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
               Qy itemstack = this.getItemStackFromSlot(entityequipmentslot);
               if (!flag && this.rand.nextFloat() < f) {
                  break;
               }

               flag = false;
               if (itemstack.isEmpty()) {
                  OL item = getArmorByChance(entityequipmentslot, i);
                  if (item != null) {
                     this.setItemStackToSlot(entityequipmentslot, new Qy(item));
                  }
               }
            }
         }
      }

   }

   public static EntityEquipmentSlot getSlotForItemStack(Qy stack) {
      if (bnK.ForgeItem_getEquipmentSlot.exists()) {
         EntityEquipmentSlot entityequipmentslot = (EntityEquipmentSlot)bnK.call(stack.getItem(), bnK.ForgeItem_getEquipmentSlot, stack);
         if (entityequipmentslot != null) {
            return entityequipmentslot;
         }
      }

      if (stack.getItem() != OL.getItemFromBlock(Nk.PUMPKIN) && stack.getItem() != NK.SKULL) {
         if (stack.getItem() instanceof OR) {
            return ((OR)stack.getItem()).armorType;
         } else if (stack.getItem() == NK.ELYTRA) {
            return EntityEquipmentSlot.CHEST;
         } else {
            boolean flag = stack.getItem() == NK.SHIELD;
            if (bnK.ForgeItem_isShield.exists()) {
               flag = bnK.callBoolean(stack.getItem(), bnK.ForgeItem_isShield, stack, null);
            }

            return flag ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND;
         }
      } else {
         return EntityEquipmentSlot.HEAD;
      }
   }

   @Nullable
   public static OL getArmorByChance(EntityEquipmentSlot slotIn, int chance) {
      switch (slotIn) {
         case HEAD:
            if (chance == 0) {
               return NK.LEATHER_HELMET;
            } else if (chance == 1) {
               return NK.GOLDEN_HELMET;
            } else if (chance == 2) {
               return NK.CHAINMAIL_HELMET;
            } else if (chance == 3) {
               return NK.IRON_HELMET;
            } else if (chance == 4) {
               return NK.DIAMOND_HELMET;
            }
         case CHEST:
            if (chance == 0) {
               return NK.LEATHER_CHESTPLATE;
            } else if (chance == 1) {
               return NK.GOLDEN_CHESTPLATE;
            } else if (chance == 2) {
               return NK.CHAINMAIL_CHESTPLATE;
            } else if (chance == 3) {
               return NK.IRON_CHESTPLATE;
            } else if (chance == 4) {
               return NK.DIAMOND_CHESTPLATE;
            }
         case LEGS:
            if (chance == 0) {
               return NK.LEATHER_LEGGINGS;
            } else if (chance == 1) {
               return NK.GOLDEN_LEGGINGS;
            } else if (chance == 2) {
               return NK.CHAINMAIL_LEGGINGS;
            } else if (chance == 3) {
               return NK.IRON_LEGGINGS;
            } else if (chance == 4) {
               return NK.DIAMOND_LEGGINGS;
            }
         case FEET:
            if (chance == 0) {
               return NK.LEATHER_BOOTS;
            } else if (chance == 1) {
               return NK.GOLDEN_BOOTS;
            } else if (chance == 2) {
               return NK.CHAINMAIL_BOOTS;
            } else if (chance == 3) {
               return NK.IRON_BOOTS;
            } else if (chance == 4) {
               return NK.DIAMOND_BOOTS;
            }
         default:
            return null;
      }
   }

   protected void setEnchantmentBasedOnDifficulty(baL difficulty) {
      float f = difficulty.getClampedAdditionalDifficulty();
      if (!this.getHeldItemMainhand().isEmpty() && this.rand.nextFloat() < 0.25F * f) {
         this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, Ft.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
      }

      EntityEquipmentSlot[] var3 = EntityEquipmentSlot.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EntityEquipmentSlot entityequipmentslot = var3[var5];
         if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
            Qy itemstack = this.getItemStackFromSlot(entityequipmentslot);
            if (!itemstack.isEmpty() && this.rand.nextFloat() < 0.5F * f) {
               this.setItemStackToSlot(entityequipmentslot, Ft.addRandomEnchantment(this.rand, itemstack, (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
            }
         }
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      this.getEntityAttribute(Ni.FOLLOW_RANGE).applyModifier(new FW("Random spawn bonus", this.rand.nextGaussian() * 0.05, 1));
      if (this.rand.nextFloat() < 0.05F) {
         this.setLeftHanded(true);
      } else {
         this.setLeftHanded(false);
      }

      return livingdata;
   }

   public boolean canBeSteered() {
      return false;
   }

   public void enablePersistence() {
      this.persistenceRequired = true;
   }

   public void setDropChance(EntityEquipmentSlot slotIn, float chance) {
      switch (slotIn.getSlotType()) {
         case HAND:
            this.inventoryHandsDropChances[slotIn.getIndex()] = chance;
            break;
         case ARMOR:
            this.inventoryArmorDropChances[slotIn.getIndex()] = chance;
      }

   }

   public boolean canPickUpLoot() {
      return this.canPickUpLoot;
   }

   public void setCanPickUpLoot(boolean canPickup) {
      this.canPickUpLoot = canPickup;
   }

   public boolean isNoDespawnRequired() {
      return this.persistenceRequired;
   }

   public final boolean processInitialInteract(ME player, EnumHand hand) {
      if (this.getLeashed() && this.getLeashHolder() == player) {
         this.clearLeashed(true, !player.capabilities.isCreativeMode);
         return true;
      } else {
         Qy itemstack = player.getHeldItem(hand);
         if (itemstack.getItem() == NK.LEAD && this.canBeLeashedTo(player)) {
            this.setLeashHolder(player, true);
            itemstack.shrink(1);
            return true;
         } else {
            return this.processInteract(player, hand) ? true : super.processInitialInteract(player, hand);
         }
      }
   }

   protected boolean processInteract(ME player, EnumHand hand) {
      return false;
   }

   protected void updateLeashedState() {
      if (this.leashNBTTag != null) {
         this.recreateLeash();
      }

      if (this.isLeashed) {
         if (!this.isEntityAlive()) {
            this.clearLeashed(true, true);
         }

         if (this.leashHolder == null || this.leashHolder.isDead) {
            this.clearLeashed(true, true);
         }
      }

   }

   public void clearLeashed(boolean sendPacket, boolean dropLead) {
      if (this.isLeashed) {
         this.isLeashed = false;
         this.leashHolder = null;
         if (!this.world.isRemote && dropLead) {
            this.dropItem(NK.LEAD, 1);
         }

         if (!this.world.isRemote && sendPacket && this.world instanceof bis) {
            ((bis)this.world).getEntityTracker().sendToTracking(this, new TW(this, (Ig)null));
         }
      }

   }

   public boolean canBeLeashedTo(ME player) {
      return !this.getLeashed() && !(this instanceof Lo);
   }

   public boolean getLeashed() {
      return this.isLeashed;
   }

   public Ig getLeashHolder() {
      return this.leashHolder;
   }

   public void setLeashHolder(Ig entityIn, boolean sendAttachNotification) {
      this.isLeashed = true;
      this.leashHolder = entityIn;
      if (!this.world.isRemote && sendAttachNotification && this.world instanceof bis) {
         ((bis)this.world).getEntityTracker().sendToTracking(this, new TW(this, this.leashHolder));
      }

      if (this.isRiding()) {
         this.dismountRidingEntity();
      }

   }

   public boolean startRiding(Ig entityIn, boolean force) {
      boolean flag = super.startRiding(entityIn, force);
      if (flag && this.getLeashed()) {
         this.clearLeashed(true, true);
      }

      return flag;
   }

   private void recreateLeash() {
      if (this.isLeashed && this.leashNBTTag != null) {
         if (this.leashNBTTag.hasUniqueId("UUID")) {
            UUID uuid = this.leashNBTTag.getUniqueId("UUID");
            Iterator var2 = this.world.getEntitiesWithinAABB(Iw.class, this.getEntityBoundingBox().grow(10.0)).iterator();

            while(var2.hasNext()) {
               Iw entitylivingbase = (Iw)var2.next();
               if (entitylivingbase.getUniqueID().equals(uuid)) {
                  this.setLeashHolder(entitylivingbase, true);
                  break;
               }
            }
         } else if (this.leashNBTTag.hasKey("X", 99) && this.leashNBTTag.hasKey("Y", 99) && this.leashNBTTag.hasKey("Z", 99)) {
            BlockPos blockpos = new BlockPos(this.leashNBTTag.getInteger("X"), this.leashNBTTag.getInteger("Y"), this.leashNBTTag.getInteger("Z"));
            Ip entityleashknot = Ip.getKnotForPosition(this.world, blockpos);
            if (entityleashknot == null) {
               entityleashknot = Ip.createKnot(this.world, blockpos);
            }

            this.setLeashHolder(entityleashknot, true);
         } else {
            this.clearLeashed(false, true);
         }
      }

      this.leashNBTTag = null;
   }

   public boolean replaceItemInInventory(int inventorySlot, Qy itemStackIn) {
      EntityEquipmentSlot entityequipmentslot;
      if (inventorySlot == 98) {
         entityequipmentslot = EntityEquipmentSlot.MAINHAND;
      } else if (inventorySlot == 99) {
         entityequipmentslot = EntityEquipmentSlot.OFFHAND;
      } else if (inventorySlot == 100 + EntityEquipmentSlot.HEAD.getIndex()) {
         entityequipmentslot = EntityEquipmentSlot.HEAD;
      } else if (inventorySlot == 100 + EntityEquipmentSlot.CHEST.getIndex()) {
         entityequipmentslot = EntityEquipmentSlot.CHEST;
      } else if (inventorySlot == 100 + EntityEquipmentSlot.LEGS.getIndex()) {
         entityequipmentslot = EntityEquipmentSlot.LEGS;
      } else {
         if (inventorySlot != 100 + EntityEquipmentSlot.FEET.getIndex()) {
            return false;
         }

         entityequipmentslot = EntityEquipmentSlot.FEET;
      }

      if (!itemStackIn.isEmpty() && !isItemStackInSlot(entityequipmentslot, itemStackIn) && entityequipmentslot != EntityEquipmentSlot.HEAD) {
         return false;
      } else {
         this.setItemStackToSlot(entityequipmentslot, itemStackIn);
         return true;
      }
   }

   public boolean canPassengerSteer() {
      return this.canBeSteered() && super.canPassengerSteer();
   }

   public static boolean isItemStackInSlot(EntityEquipmentSlot slotIn, Qy stack) {
      EntityEquipmentSlot entityequipmentslot = getSlotForItemStack(stack);
      return entityequipmentslot == slotIn || entityequipmentslot == EntityEquipmentSlot.MAINHAND && slotIn == EntityEquipmentSlot.OFFHAND || entityequipmentslot == EntityEquipmentSlot.OFFHAND && slotIn == EntityEquipmentSlot.MAINHAND;
   }

   public boolean isServerWorld() {
      return super.isServerWorld() && !this.isAIDisabled();
   }

   public void setNoAI(boolean disable) {
      byte b0 = (Byte)this.dataManager.get(AI_FLAGS);
      this.dataManager.set(AI_FLAGS, disable ? (byte)(b0 | 1) : (byte)(b0 & -2));
   }

   public void setLeftHanded(boolean leftHanded) {
      byte b0 = (Byte)this.dataManager.get(AI_FLAGS);
      this.dataManager.set(AI_FLAGS, leftHanded ? (byte)(b0 | 2) : (byte)(b0 & -3));
   }

   public boolean isAIDisabled() {
      return ((Byte)this.dataManager.get(AI_FLAGS) & 1) != 0;
   }

   public boolean isLeftHanded() {
      return ((Byte)this.dataManager.get(AI_FLAGS) & 2) != 0;
   }

   public EnumHandSide getPrimaryHand() {
      return this.isLeftHanded() ? EnumHandSide.LEFT : EnumHandSide.RIGHT;
   }

   private boolean canSkipUpdate() {
      if (this.isChild()) {
         return false;
      } else if (this.hurtTime > 0) {
         return false;
      } else if (this.ticksExisted < 20) {
         return false;
      } else {
         bij world = this.getEntityWorld();
         if (world == null) {
            return false;
         } else if (world.playerEntities.size() != 1) {
            return false;
         } else {
            Ig entity = (Ig)world.playerEntities.get(0);
            double d0 = Math.max(Math.abs(this.posX - entity.posX) - 16.0, 0.0);
            double d1 = Math.max(Math.abs(this.posZ - entity.posZ) - 16.0, 0.0);
            double d2 = d0 * d0 + d1 * d1;
            return !this.isInRangeToRenderDist(d2);
         }
      }
   }

   private void onUpdateMinimal() {
      ++this.idleTime;
      if (this instanceof Kl) {
         float f = this.getBrightness();
         if (f > 0.5F) {
            this.idleTime += 2;
         }
      }

      this.despawnEntity();
   }

   public WE getTeam() {
      UUID uuid = this.getUniqueID();
      if (this.teamUuid != uuid) {
         this.teamUuid = uuid;
         this.teamUuidString = uuid.toString();
      }

      return this.world.getScoreboard().getPlayersTeam(this.teamUuidString);
   }

   static {
      AI_FLAGS = Rv.createKey(Iu.class, Rt.BYTE);
   }
}
