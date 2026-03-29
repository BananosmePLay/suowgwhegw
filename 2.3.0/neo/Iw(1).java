package neo;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.CombatRules;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Iw extends Ig {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
   private static final FW SPRINTING_SPEED_BOOST;
   protected static final Rd<Byte> HAND_STATES;
   private static final Rd<Float> HEALTH;
   private static final Rd<Integer> POTION_EFFECTS;
   private static final Rd<Boolean> HIDE_PARTICLES;
   private static final Rd<Integer> ARROW_COUNT_IN_ENTITY;
   private FU attributeMap;
   private final CombatTracker combatTracker = new CombatTracker(this);
   private final Map<VW, VZ> activePotionsMap = Maps.newHashMap();
   private final NonNullList<Qy> handInventory;
   private final NonNullList<Qy> armorArray;
   public boolean isSwingInProgress;
   public EnumHand swingingHand;
   public int swingProgressInt;
   public int arrowHitTimer;
   public int hurtTime;
   public int maxHurtTime;
   public float attackedAtYaw;
   public int deathTime;
   public float prevSwingProgress;
   public float swingProgress;
   protected int ticksSinceLastSwing;
   public float prevLimbSwingAmount;
   public float limbSwingAmount;
   public float limbSwing;
   public int maxHurtResistantTime;
   public float prevCameraPitch;
   public float cameraPitch;
   public float randomUnused2;
   public float randomUnused1;
   public float renderYawOffset;
   public float prevRenderYawOffset;
   public float rotationYawHead;
   public float prevRotationYawHead;
   public float jumpMovementFactor;
   protected ME attackingPlayer;
   protected int recentlyHit;
   protected boolean dead;
   protected int idleTime;
   protected float prevOnGroundSpeedFactor;
   protected float onGroundSpeedFactor;
   protected float movedDistance;
   protected float prevMovedDistance;
   protected float unused180;
   protected int scoreValue;
   protected float lastDamage;
   protected boolean isJumping;
   public float moveStrafing;
   public float moveVertical;
   public float moveForward;
   public float randomYawVelocity;
   protected int newPosRotationIncrements;
   protected double interpTargetX;
   protected double interpTargetY;
   protected double interpTargetZ;
   protected double interpTargetYaw;
   protected double interpTargetPitch;
   private boolean potionsNeedUpdate;
   private Iw revengeTarget;
   private int revengeTimer;
   private Iw lastAttackedEntity;
   private int lastAttackedEntityTime;
   private float landMovementFactor;
   private int jumpTicks;
   private float absorptionAmount;
   protected Qy activeItemStack;
   protected int activeItemStackUseCount;
   protected int ticksElytraFlying;
   private BlockPos prevBlockpos;
   private DamageSource lastDamageSource;
   private long lastDamageStamp;

   public void onKillCommand() {
      this.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
   }

   public Iw(bij worldIn) {
      super(worldIn);
      this.handInventory = NonNullList.withSize(2, Qy.EMPTY);
      this.armorArray = NonNullList.withSize(4, Qy.EMPTY);
      this.maxHurtResistantTime = 20;
      this.jumpMovementFactor = 0.02F;
      this.potionsNeedUpdate = true;
      this.activeItemStack = Qy.EMPTY;
      this.applyEntityAttributes();
      this.setHealth(this.getMaxHealth());
      this.preventEntitySpawning = true;
      this.randomUnused1 = (float)((Math.random() + 1.0) * 0.009999999776482582);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.randomUnused2 = (float)Math.random() * 12398.0F;
      this.rotationYaw = (float)(Math.random() * 6.283185307179586);
      this.rotationYawHead = this.rotationYaw;
      this.stepHeight = 0.6F;
   }

   protected void entityInit() {
      this.dataManager.register(HAND_STATES, (byte)0);
      this.dataManager.register(POTION_EFFECTS, 0);
      this.dataManager.register(HIDE_PARTICLES, false);
      this.dataManager.register(ARROW_COUNT_IN_ENTITY, 0);
      this.dataManager.register(HEALTH, 1.0F);
   }

   protected void applyEntityAttributes() {
      this.getAttributeMap().registerAttribute(Ni.MAX_HEALTH);
      this.getAttributeMap().registerAttribute(Ni.KNOCKBACK_RESISTANCE);
      this.getAttributeMap().registerAttribute(Ni.MOVEMENT_SPEED);
      this.getAttributeMap().registerAttribute(Ni.ARMOR);
      this.getAttributeMap().registerAttribute(Ni.ARMOR_TOUGHNESS);
   }

   protected void updateFallState(double y, boolean onGroundIn, in state, BlockPos pos) {
      if (!this.isInWater()) {
         this.handleWaterMovement();
      }

      if (!this.world.isRemote && this.fallDistance > 3.0F && onGroundIn) {
         float f = (float)MathHelper.ceil(this.fallDistance - 3.0F);
         if (state.getMaterial() != hM.AIR) {
            double d0 = Math.min((double)(0.2F + f / 15.0F), 2.5);
            int i = (int)(150.0 * d0);
            ((bis)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, i, 0.0, 0.0, 0.0, 0.15000000596046448, co.getStateId(state));
         }
      }

      super.updateFallState(y, onGroundIn, state, pos);
   }

   public boolean canBreatheUnderwater() {
      return false;
   }

   public void onEntityUpdate() {
      this.prevSwingProgress = this.swingProgress;
      super.onEntityUpdate();
      this.world.profiler.startSection("livingEntityBaseTick");
      boolean flag = this instanceof ME;
      if (this.isEntityAlive()) {
         if (this.isEntityInsideOpaqueBlock()) {
            this.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
         } else if (flag && !this.world.getWorldBorder().contains(this.getEntityBoundingBox())) {
            double d0 = this.world.getWorldBorder().getClosestDistance(this) + this.world.getWorldBorder().getDamageBuffer();
            if (d0 < 0.0) {
               double d1 = this.world.getWorldBorder().getDamageAmount();
               if (d1 > 0.0) {
                  this.attackEntityFrom(DamageSource.IN_WALL, (float)Math.max(1, MathHelper.floor(-d0 * d1)));
               }
            }
         }
      }

      if (this.isImmuneToFire() || this.world.isRemote) {
         this.extinguish();
      }

      boolean flag1 = flag && ((ME)this).capabilities.disableDamage;
      if (this.isEntityAlive()) {
         if (!this.isInsideOfMaterial(hM.WATER)) {
            this.setAir(300);
         } else {
            if (!this.canBreatheUnderwater() && !this.isPotionActive(NL.WATER_BREATHING) && !flag1) {
               this.setAir(this.decreaseAirSupply(this.getAir()));
               if (this.getAir() == -20) {
                  this.setAir(0);

                  for(int i = 0; i < 8; ++i) {
                     float f2 = this.rand.nextFloat() - this.rand.nextFloat();
                     float f = this.rand.nextFloat() - this.rand.nextFloat();
                     float f1 = this.rand.nextFloat() - this.rand.nextFloat();
                     this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (double)f2, this.posY + (double)f, this.posZ + (double)f1, this.motionX, this.motionY, this.motionZ);
                  }

                  this.attackEntityFrom(DamageSource.DROWN, 2.0F);
               }
            }

            if (!this.world.isRemote && this.isRiding() && this.getRidingEntity() instanceof Iw) {
               this.dismountRidingEntity();
            }
         }

         if (!this.world.isRemote) {
            BlockPos blockpos = new BlockPos(this);
            if (!Objects.equal(this.prevBlockpos, blockpos)) {
               this.prevBlockpos = blockpos;
               this.frostWalk(blockpos);
            }
         }
      }

      if (this.isEntityAlive() && this.isWet()) {
         this.extinguish();
      }

      this.prevCameraPitch = this.cameraPitch;
      if (this.hurtTime > 0) {
         --this.hurtTime;
      }

      if (this.hurtResistantTime > 0 && !(this instanceof MG)) {
         --this.hurtResistantTime;
      }

      if (this.getHealth() <= 0.0F) {
         this.onDeathUpdate();
      }

      if (this.recentlyHit > 0) {
         --this.recentlyHit;
      } else {
         this.attackingPlayer = null;
      }

      if (this.lastAttackedEntity != null && !this.lastAttackedEntity.isEntityAlive()) {
         this.lastAttackedEntity = null;
      }

      if (this.revengeTarget != null) {
         if (!this.revengeTarget.isEntityAlive()) {
            this.setRevengeTarget((Iw)null);
         } else if (this.ticksExisted - this.revengeTimer > 100) {
            this.setRevengeTarget((Iw)null);
         }
      }

      this.updatePotionEffects();
      this.prevMovedDistance = this.movedDistance;
      this.prevRenderYawOffset = this.renderYawOffset;
      this.prevRotationYawHead = this.rotationYawHead;
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
      this.world.profiler.endSection();
   }

   protected void frostWalk(BlockPos pos) {
      int i = Ft.getMaxEnchantmentLevel(NJ.FROST_WALKER, this);
      if (i > 0) {
         Fm.freezeNearby(this, this.world, pos, i);
      }

   }

   public boolean isChild() {
      return false;
   }

   protected void onDeathUpdate() {
      ++this.deathTime;
      if (this.deathTime == 20) {
         int k;
         if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot"))) {
            k = this.getExperiencePoints(this.attackingPlayer);

            while(k > 0) {
               int j = Js.getXPSplit(k);
               k -= j;
               this.world.spawnEntity(new Js(this.world, this.posX, this.posY, this.posZ, j));
            }
         }

         this.setDead();

         for(k = 0; k < 20; ++k) {
            double d2 = this.rand.nextGaussian() * 0.02;
            double d0 = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
         }
      }

   }

   protected boolean canDropLoot() {
      return !this.isChild();
   }

   protected int decreaseAirSupply(int air) {
      int i = Ft.getRespirationModifier(this);
      return i > 0 && this.rand.nextInt(i + 1) > 0 ? air : air - 1;
   }

   protected int getExperiencePoints(ME player) {
      return 0;
   }

   protected boolean isPlayer() {
      return false;
   }

   public Random getRNG() {
      return this.rand;
   }

   @Nullable
   public Iw getRevengeTarget() {
      return this.revengeTarget;
   }

   public int getRevengeTimer() {
      return this.revengeTimer;
   }

   public void setRevengeTarget(@Nullable Iw livingBase) {
      this.revengeTarget = livingBase;
      this.revengeTimer = this.ticksExisted;
   }

   public Iw getLastAttackedEntity() {
      return this.lastAttackedEntity;
   }

   public int getLastAttackedEntityTime() {
      return this.lastAttackedEntityTime;
   }

   public void setLastAttackedEntity(Ig entityIn) {
      if (entityIn instanceof Iw) {
         this.lastAttackedEntity = (Iw)entityIn;
      } else {
         this.lastAttackedEntity = null;
      }

      this.lastAttackedEntityTime = this.ticksExisted;
   }

   public int getIdleTime() {
      return this.idleTime;
   }

   protected void playEquipSound(Qy stack) {
      if (!stack.isEmpty()) {
         SoundEvent soundevent = NO.ITEM_ARMOR_EQUIP_GENERIC;
         OL item = stack.getItem();
         if (item instanceof OR) {
            soundevent = ((OR)item).getArmorMaterial().getSoundEvent();
         } else if (item == NK.ELYTRA) {
            soundevent = NO.ITEM_ARMOR_EQIIP_ELYTRA;
         }

         this.playSound(soundevent, 1.0F, 1.0F);
      }

   }

   public void writeEntityToNBT(QQ compound) {
      compound.setFloat("Health", this.getHealth());
      compound.setShort("HurtTime", (short)this.hurtTime);
      compound.setInteger("HurtByTimestamp", this.revengeTimer);
      compound.setShort("DeathTime", (short)this.deathTime);
      compound.setFloat("AbsorptionAmount", this.getAbsorptionAmount());
      EntityEquipmentSlot[] var2 = EntityEquipmentSlot.values();
      int var3 = var2.length;

      int var4;
      EntityEquipmentSlot entityequipmentslot1;
      Qy itemstack1;
      for(var4 = 0; var4 < var3; ++var4) {
         entityequipmentslot1 = var2[var4];
         itemstack1 = this.getItemStackFromSlot(entityequipmentslot1);
         if (!itemstack1.isEmpty()) {
            this.getAttributeMap().removeAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot1));
         }
      }

      compound.setTag("Attributes", Ni.writeBaseAttributeMapToNBT(this.getAttributeMap()));
      var2 = EntityEquipmentSlot.values();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         entityequipmentslot1 = var2[var4];
         itemstack1 = this.getItemStackFromSlot(entityequipmentslot1);
         if (!itemstack1.isEmpty()) {
            this.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot1));
         }
      }

      if (!this.activePotionsMap.isEmpty()) {
         QW nbttaglist = new QW();
         Iterator var8 = this.activePotionsMap.values().iterator();

         while(var8.hasNext()) {
            VZ potioneffect = (VZ)var8.next();
            nbttaglist.appendTag(potioneffect.writeCustomPotionEffectToNBT(new QQ()));
         }

         compound.setTag("ActiveEffects", nbttaglist);
      }

      compound.setBoolean("FallFlying", this.isElytraFlying());
   }

   public void readEntityFromNBT(QQ compound) {
      this.setAbsorptionAmount(compound.getFloat("AbsorptionAmount"));
      if (compound.hasKey("Attributes", 9) && this.world != null && !this.world.isRemote) {
         Ni.setAttributeModifiers(this.getAttributeMap(), compound.getTagList("Attributes", 10));
      }

      if (compound.hasKey("ActiveEffects", 9)) {
         QW nbttaglist = compound.getTagList("ActiveEffects", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            VZ potioneffect = VZ.readCustomPotionEffectFromNBT(nbttagcompound);
            if (potioneffect != null) {
               this.activePotionsMap.put(potioneffect.getPotion(), potioneffect);
            }
         }
      }

      if (compound.hasKey("Health", 99)) {
         this.setHealth(compound.getFloat("Health"));
      }

      this.hurtTime = compound.getShort("HurtTime");
      this.deathTime = compound.getShort("DeathTime");
      this.revengeTimer = compound.getInteger("HurtByTimestamp");
      if (compound.hasKey("Team", 8)) {
         String s = compound.getString("Team");
         boolean flag = this.world.getScoreboard().addPlayerToTeam(this.getCachedUniqueIdString(), s);
         if (!flag) {
            LOGGER.warn("Unable to add mob to team \"" + s + "\" (that team probably doesn't exist)");
         }
      }

      if (compound.getBoolean("FallFlying")) {
         this.setFlag(7, true);
      }

   }

   protected void updatePotionEffects() {
      Iterator<VW> iterator = this.activePotionsMap.keySet().iterator();

      try {
         while(iterator.hasNext()) {
            VW potion = (VW)iterator.next();
            VZ potioneffect = (VZ)this.activePotionsMap.get(potion);
            if (!potioneffect.onUpdate(this)) {
               if (!this.world.isRemote) {
                  iterator.remove();
                  this.onFinishedPotionEffect(potioneffect);
               }
            } else if (potioneffect.getDuration() % 600 == 0) {
               this.onChangedPotionEffect(potioneffect, false);
            }
         }
      } catch (ConcurrentModificationException var11) {
      }

      if (this.potionsNeedUpdate) {
         if (!this.world.isRemote) {
            this.updatePotionMetadata();
         }

         this.potionsNeedUpdate = false;
      }

      int i = (Integer)this.dataManager.get(POTION_EFFECTS);
      boolean flag1 = (Boolean)this.dataManager.get(HIDE_PARTICLES);
      if (i > 0) {
         boolean flag;
         if (this.isInvisible()) {
            flag = this.rand.nextInt(15) == 0;
         } else {
            flag = this.rand.nextBoolean();
         }

         if (flag1) {
            flag &= this.rand.nextInt(5) == 0;
         }

         if (flag && i > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0;
            double d1 = (double)(i >> 8 & 255) / 255.0;
            double d2 = (double)(i >> 0 & 255) / 255.0;
            this.world.spawnParticle(flag1 ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, d0, d1, d2);
         }
      }

   }

   protected void updatePotionMetadata() {
      if (this.activePotionsMap.isEmpty()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(false);
      } else {
         Collection<VZ> collection = this.activePotionsMap.values();
         this.dataManager.set(HIDE_PARTICLES, areAllPotionsAmbient(collection));
         this.dataManager.set(POTION_EFFECTS, Wg.getPotionColorFromEffectList(collection));
         this.setInvisible(this.isPotionActive(NL.INVISIBILITY));
      }

   }

   public static boolean areAllPotionsAmbient(Collection<VZ> potionEffects) {
      Iterator var1 = potionEffects.iterator();

      VZ potioneffect;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         potioneffect = (VZ)var1.next();
      } while(potioneffect.getIsAmbient());

      return false;
   }

   protected void resetPotionEffectMetadata() {
      this.dataManager.set(HIDE_PARTICLES, false);
      this.dataManager.set(POTION_EFFECTS, 0);
   }

   public void clearActivePotions() {
      if (!this.world.isRemote) {
         Iterator<VZ> iterator = this.activePotionsMap.values().iterator();

         while(iterator.hasNext()) {
            this.onFinishedPotionEffect((VZ)iterator.next());
            iterator.remove();
         }
      }

   }

   public Collection<VZ> getActivePotionEffects() {
      return this.activePotionsMap.values();
   }

   public Map<VW, VZ> getActivePotionMap() {
      return this.activePotionsMap;
   }

   public boolean isPotionActive(VW potionIn) {
      return this.activePotionsMap.containsKey(potionIn);
   }

   @Nullable
   public VZ getActivePotionEffect(VW potionIn) {
      return (VZ)this.activePotionsMap.get(potionIn);
   }

   public void addPotionEffect(VZ potioneffectIn) {
      if (this.isPotionApplicable(potioneffectIn)) {
         VZ potioneffect = (VZ)this.activePotionsMap.get(potioneffectIn.getPotion());
         if (potioneffect == null) {
            this.activePotionsMap.put(potioneffectIn.getPotion(), potioneffectIn);
            this.onNewPotionEffect(potioneffectIn);
         } else {
            potioneffect.combine(potioneffectIn);
            this.onChangedPotionEffect(potioneffect, true);
         }
      }

   }

   public boolean isPotionApplicable(VZ potioneffectIn) {
      if (this.getCreatureAttribute() == IB.UNDEAD) {
         VW potion = potioneffectIn.getPotion();
         if (potion == NL.REGENERATION || potion == NL.POISON) {
            return false;
         }
      }

      return true;
   }

   public boolean isEntityUndead() {
      return this.getCreatureAttribute() == IB.UNDEAD;
   }

   @Nullable
   public VZ removeActivePotionEffect(@Nullable VW potioneffectin) {
      return (VZ)this.activePotionsMap.remove(potioneffectin);
   }

   public void removePotionEffect(VW potionIn) {
      VZ potioneffect = this.removeActivePotionEffect(potionIn);
      if (potioneffect != null) {
         this.onFinishedPotionEffect(potioneffect);
      }

   }

   protected void onNewPotionEffect(VZ id) {
      this.potionsNeedUpdate = true;
      if (!this.world.isRemote) {
         id.getPotion().applyAttributesModifiersToEntity(this, this.getAttributeMap(), id.getAmplifier());
      }

   }

   protected void onChangedPotionEffect(VZ id, boolean p_70695_2_) {
      this.potionsNeedUpdate = true;
      if (p_70695_2_ && !this.world.isRemote) {
         VW potion = id.getPotion();
         potion.removeAttributesModifiersFromEntity(this, this.getAttributeMap(), id.getAmplifier());
         potion.applyAttributesModifiersToEntity(this, this.getAttributeMap(), id.getAmplifier());
      }

   }

   protected void onFinishedPotionEffect(VZ effect) {
      this.potionsNeedUpdate = true;
      if (!this.world.isRemote) {
         effect.getPotion().removeAttributesModifiersFromEntity(this, this.getAttributeMap(), effect.getAmplifier());
      }

   }

   public void heal(float healAmount) {
      float f = this.getHealth();
      if (f > 0.0F) {
         this.setHealth(f + healAmount);
      }

   }

   public final float getHealth() {
      return (Float)this.dataManager.get(HEALTH);
   }

   public void setHealth(float health) {
      this.dataManager.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (this.world.isRemote) {
         return false;
      } else {
         this.idleTime = 0;
         if (this.getHealth() <= 0.0F) {
            return false;
         } else if (source.isFireDamage() && this.isPotionActive(NL.FIRE_RESISTANCE)) {
            return false;
         } else {
            float f = amount;
            if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
               this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int)(amount * 4.0F + this.rand.nextFloat() * amount * 2.0F), this);
               amount *= 0.75F;
            }

            boolean flag = false;
            if (amount > 0.0F && this.canBlockDamageSource(source)) {
               this.damageShield(amount);
               amount = 0.0F;
               if (!source.isProjectile()) {
                  Ig entity = source.getImmediateSource();
                  if (entity instanceof Iw) {
                     this.blockUsingShield((Iw)entity);
                  }
               }

               flag = true;
            }

            this.limbSwingAmount = 1.5F;
            boolean flag1 = true;
            if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F) {
               if (amount <= this.lastDamage) {
                  return false;
               }

               this.damageEntity(source, amount - this.lastDamage);
               this.lastDamage = amount;
               flag1 = false;
            } else {
               this.lastDamage = amount;
               this.hurtResistantTime = this.maxHurtResistantTime;
               this.damageEntity(source, amount);
               this.maxHurtTime = 10;
               this.hurtTime = this.maxHurtTime;
            }

            this.attackedAtYaw = 0.0F;
            Ig entity1 = source.getTrueSource();
            if (entity1 != null) {
               if (entity1 instanceof Iw) {
                  this.setRevengeTarget((Iw)entity1);
               }

               if (entity1 instanceof ME) {
                  this.recentlyHit = 100;
                  this.attackingPlayer = (ME)entity1;
               } else if (entity1 instanceof Mu) {
                  Mu entitywolf = (Mu)entity1;
                  if (entitywolf.isTamed()) {
                     this.recentlyHit = 100;
                     this.attackingPlayer = null;
                  }
               }
            }

            if (flag1) {
               if (flag) {
                  this.world.setEntityState(this, (byte)29);
               } else if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage()) {
                  this.world.setEntityState(this, (byte)33);
               } else {
                  byte b0;
                  if (source == DamageSource.DROWN) {
                     b0 = 36;
                  } else if (source.isFireDamage()) {
                     b0 = 37;
                  } else {
                     b0 = 2;
                  }

                  this.world.setEntityState(this, b0);
               }

               if (source != DamageSource.DROWN && (!flag || amount > 0.0F)) {
                  this.markVelocityChanged();
               }

               if (entity1 != null) {
                  double d1 = entity1.posX - this.posX;

                  double d0;
                  for(d0 = entity1.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4; d0 = (Math.random() - Math.random()) * 0.01) {
                     d1 = (Math.random() - Math.random()) * 0.01;
                  }

                  this.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * 57.29577951308232 - (double)this.rotationYaw);
                  this.knockBack(entity1, 0.4F, d1, d0);
               } else {
                  this.attackedAtYaw = (float)((int)(Math.random() * 2.0) * 180);
               }
            }

            if (this.getHealth() <= 0.0F) {
               if (!this.checkTotemDeathProtection(source)) {
                  SoundEvent soundevent = this.getDeathSound();
                  if (flag1 && soundevent != null) {
                     this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
                  }

                  this.onDeath(source);
               }
            } else if (flag1) {
               this.playHurtSound(source);
            }

            boolean flag2 = !flag || amount > 0.0F;
            if (flag2) {
               this.lastDamageSource = source;
               this.lastDamageStamp = this.world.getTotalWorldTime();
            }

            if (this instanceof MG) {
               bY.ENTITY_HURT_PLAYER.trigger((MG)this, source, f, amount, flag);
            }

            if (entity1 instanceof MG) {
               bY.PLAYER_HURT_ENTITY.trigger((MG)entity1, this, source, f, amount, flag);
            }

            return flag2;
         }
      }
   }

   protected void blockUsingShield(Iw p_190629_1_) {
      p_190629_1_.knockBack(this, 0.5F, this.posX - p_190629_1_.posX, this.posZ - p_190629_1_.posZ);
   }

   private boolean checkTotemDeathProtection(DamageSource p_190628_1_) {
      if (p_190628_1_.canHarmInCreative()) {
         return false;
      } else {
         Qy itemstack = null;
         EnumHand[] var3 = EnumHand.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            EnumHand enumhand = var3[var5];
            Qy itemstack1 = this.getHeldItem(enumhand);
            if (itemstack1.getItem() == NK.TOTEM_OF_UNDYING) {
               itemstack = itemstack1.copy();
               itemstack1.shrink(1);
               break;
            }
         }

         if (itemstack != null) {
            if (this instanceof MG) {
               MG entityplayermp = (MG)this;
               entityplayermp.addStat(XV.getObjectUseStats(NK.TOTEM_OF_UNDYING));
               bY.USED_TOTEM.trigger(entityplayermp, itemstack);
            }

            this.setHealth(1.0F);
            this.clearActivePotions();
            this.addPotionEffect(new VZ(NL.REGENERATION, 900, 1));
            this.addPotionEffect(new VZ(NL.ABSORPTION, 100, 1));
            this.world.setEntityState(this, (byte)35);
         }

         return itemstack != null;
      }
   }

   @Nullable
   public DamageSource getLastDamageSource() {
      if (this.world.getTotalWorldTime() - this.lastDamageStamp > 40L) {
         this.lastDamageSource = null;
      }

      return this.lastDamageSource;
   }

   protected void playHurtSound(DamageSource source) {
      SoundEvent soundevent = this.getHurtSound(source);
      if (soundevent != null) {
         this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   private boolean canBlockDamageSource(DamageSource damageSourceIn) {
      if (!damageSourceIn.isUnblockable() && this.isActiveItemStackBlocking()) {
         Vec3d vec3d = damageSourceIn.getDamageLocation();
         if (vec3d != null) {
            Vec3d vec3d1 = this.getLook(1.0F);
            Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(this.posX, this.posY, this.posZ)).normalize();
            vec3d2 = new Vec3d(vec3d2.x, 0.0, vec3d2.z);
            if (vec3d2.dotProduct(vec3d1) < 0.0) {
               return true;
            }
         }
      }

      return false;
   }

   public void renderBrokenItemStack(Qy stack) {
      this.playSound(NO.ENTITY_ITEM_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);

      for(int i = 0; i < 5; ++i) {
         Vec3d vec3d = new Vec3d(((double)this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
         vec3d = vec3d.rotatePitch(-this.rotationPitch * 0.017453292F);
         vec3d = vec3d.rotateYaw(-this.rotationYaw * 0.017453292F);
         double d0 = (double)(-this.rand.nextFloat()) * 0.6 - 0.3;
         Vec3d vec3d1 = new Vec3d(((double)this.rand.nextFloat() - 0.5) * 0.3, d0, 0.6);
         vec3d1 = vec3d1.rotatePitch(-this.rotationPitch * 0.017453292F);
         vec3d1 = vec3d1.rotateYaw(-this.rotationYaw * 0.017453292F);
         vec3d1 = vec3d1.add(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
         this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05, vec3d.z, OL.getIdFromItem(stack.getItem()));
      }

   }

   public void onDeath(DamageSource cause) {
      if (!this.dead) {
         Ig entity = cause.getTrueSource();
         Iw entitylivingbase = this.getAttackingEntity();
         if (this.scoreValue >= 0 && entitylivingbase != null) {
            entitylivingbase.awardKillScore(this, this.scoreValue, cause);
         }

         if (entity != null) {
            entity.onKillEntity(this);
         }

         this.dead = true;
         this.getCombatTracker().reset();
         if (!this.world.isRemote) {
            int i = 0;
            if (entity instanceof ME) {
               i = Ft.getLootingModifier((Iw)entity);
            }

            if (this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")) {
               boolean flag = this.recentlyHit > 0;
               this.dropLoot(flag, i, cause);
            }
         }

         this.world.setEntityState(this, (byte)3);
      }

   }

   protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
      this.dropFewItems(wasRecentlyHit, lootingModifier);
      this.dropEquipment(wasRecentlyHit, lootingModifier);
   }

   protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
   }

   public void knockBack(Ig entityIn, float strength, double xRatio, double zRatio) {
      if (this.rand.nextDouble() >= this.getEntityAttribute(Ni.KNOCKBACK_RESISTANCE).getAttributeValue()) {
         this.isAirBorne = true;
         float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
         this.motionX /= 2.0;
         this.motionZ /= 2.0;
         this.motionX -= xRatio / (double)f * (double)strength;
         this.motionZ -= zRatio / (double)f * (double)strength;
         if (this.onGround) {
            this.motionY /= 2.0;
            this.motionY += (double)strength;
            if (this.motionY > 0.4000000059604645) {
               this.motionY = 0.4000000059604645;
            }
         }
      }

   }

   @Nullable
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_GENERIC_HURT;
   }

   @Nullable
   protected SoundEvent getDeathSound() {
      return NO.ENTITY_GENERIC_DEATH;
   }

   protected SoundEvent getFallSound(int heightIn) {
      return heightIn > 4 ? NO.ENTITY_GENERIC_BIG_FALL : NO.ENTITY_GENERIC_SMALL_FALL;
   }

   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
   }

   public boolean isOnLadder() {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.getEntityBoundingBox().minY);
      int k = MathHelper.floor(this.posZ);
      if (this instanceof ME && ((ME)this).isSpectator()) {
         return false;
      } else {
         BlockPos blockpos = new BlockPos(i, j, k);
         in iblockstate = this.world.getBlockState(blockpos);
         co block = iblockstate.getBlock();
         if (block != Nk.LADDER && block != Nk.VINE) {
            return block instanceof hr && this.canGoThroughtTrapDoorOnLadder(blockpos, iblockstate);
         } else {
            return true;
         }
      }
   }

   private boolean canGoThroughtTrapDoorOnLadder(BlockPos pos, in state) {
      if ((Boolean)state.getValue(hr.OPEN)) {
         in iblockstate = this.world.getBlockState(pos.down());
         if (iblockstate.getBlock() == Nk.LADDER && iblockstate.getValue(ev.FACING) == state.getValue(hr.FACING)) {
            return true;
         }
      }

      return false;
   }

   public boolean isEntityAlive() {
      return !this.isDead && this.getHealth() > 0.0F;
   }

   public void fall(float distance, float damageMultiplier) {
      super.fall(distance, damageMultiplier);
      VZ potioneffect = this.getActivePotionEffect(NL.JUMP_BOOST);
      float f = potioneffect == null ? 0.0F : (float)(potioneffect.getAmplifier() + 1);
      int i = MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);
      if (i > 0) {
         this.playSound(this.getFallSound(i), 1.0F, 1.0F);
         this.attackEntityFrom(DamageSource.FALL, (float)i);
         int j = MathHelper.floor(this.posX);
         int k = MathHelper.floor(this.posY - 0.20000000298023224);
         int l = MathHelper.floor(this.posZ);
         in iblockstate = this.world.getBlockState(new BlockPos(j, k, l));
         if (iblockstate.getMaterial() != hM.AIR) {
            ia soundtype = iblockstate.getBlock().getSoundType();
            this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
         }
      }

   }

   public void performHurtAnimation() {
      this.maxHurtTime = 10;
      this.hurtTime = this.maxHurtTime;
      this.attackedAtYaw = 0.0F;
   }

   public int getTotalArmorValue() {
      FZ iattributeinstance = this.getEntityAttribute(Ni.ARMOR);
      return MathHelper.floor(iattributeinstance.getAttributeValue());
   }

   protected void damageArmor(float damage) {
   }

   protected void damageShield(float damage) {
   }

   protected float applyArmorCalculations(DamageSource source, float damage) {
      if (!source.isUnblockable()) {
         this.damageArmor(damage);
         damage = CombatRules.getDamageAfterAbsorb(damage, (float)this.getTotalArmorValue(), (float)this.getEntityAttribute(Ni.ARMOR_TOUGHNESS).getAttributeValue());
      }

      return damage;
   }

   protected float applyPotionDamageCalculations(DamageSource source, float damage) {
      if (source.isDamageAbsolute()) {
         return damage;
      } else {
         int k;
         if (this.isPotionActive(NL.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
            k = (this.getActivePotionEffect(NL.RESISTANCE).getAmplifier() + 1) * 5;
            int j = 25 - k;
            float f = damage * (float)j;
            damage = f / 25.0F;
         }

         if (damage <= 0.0F) {
            return 0.0F;
         } else {
            k = Ft.getEnchantmentModifierDamage(this.getArmorInventoryList(), source);
            if (k > 0) {
               damage = CombatRules.getDamageAfterMagicAbsorb(damage, (float)k);
            }

            return damage;
         }
      }
   }

   protected void damageEntity(DamageSource damageSrc, float damageAmount) {
      if (!this.isEntityInvulnerable(damageSrc)) {
         damageAmount = this.applyArmorCalculations(damageSrc, damageAmount);
         damageAmount = this.applyPotionDamageCalculations(damageSrc, damageAmount);
         float f = damageAmount;
         damageAmount = Math.max(damageAmount - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - damageAmount));
         if (damageAmount != 0.0F) {
            float f1 = this.getHealth();
            this.setHealth(f1 - damageAmount);
            this.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - damageAmount);
         }
      }

   }

   public CombatTracker getCombatTracker() {
      return this.combatTracker;
   }

   @Nullable
   public Iw getAttackingEntity() {
      if (this.combatTracker.getBestAttacker() != null) {
         return this.combatTracker.getBestAttacker();
      } else if (this.attackingPlayer != null) {
         return this.attackingPlayer;
      } else {
         return this.revengeTarget != null ? this.revengeTarget : null;
      }
   }

   public final float getMaxHealth() {
      return (float)this.getEntityAttribute(Ni.MAX_HEALTH).getAttributeValue();
   }

   public final int getArrowCountInEntity() {
      return (Integer)this.dataManager.get(ARROW_COUNT_IN_ENTITY);
   }

   public final void setArrowCountInEntity(int count) {
      this.dataManager.set(ARROW_COUNT_IN_ENTITY, count);
   }

   private int getArmSwingAnimationEnd() {
      if (this.isPotionActive(NL.HASTE)) {
         return 6 - (1 + this.getActivePotionEffect(NL.HASTE).getAmplifier());
      } else {
         return this.isPotionActive(NL.MINING_FATIGUE) ? 6 + (1 + this.getActivePotionEffect(NL.MINING_FATIGUE).getAmplifier()) * 2 : 6;
      }
   }

   public void swingArm(EnumHand hand) {
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
         this.swingingHand = hand;
         if (this.world instanceof bis) {
            ((bis)this.world).getEntityTracker().sendToTracking(this, new Tx(this, hand == EnumHand.MAIN_HAND ? 0 : 3));
         }
      }

   }

   public void handleStatusUpdate(byte id) {
      boolean flag = id == 33;
      boolean flag1 = id == 36;
      boolean flag2 = id == 37;
      if (id != 2 && !flag && !flag1 && !flag2) {
         if (id == 3) {
            SoundEvent soundevent1 = this.getDeathSound();
            if (soundevent1 != null) {
               this.playSound(soundevent1, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.setHealth(0.0F);
            this.onDeath(DamageSource.GENERIC);
         } else if (id == 30) {
            this.playSound(NO.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
         } else if (id == 29) {
            this.playSound(NO.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
         } else {
            super.handleStatusUpdate(id);
         }
      } else {
         this.limbSwingAmount = 1.5F;
         this.hurtResistantTime = this.maxHurtResistantTime;
         this.maxHurtTime = 10;
         this.hurtTime = this.maxHurtTime;
         this.attackedAtYaw = 0.0F;
         if (flag) {
            this.playSound(NO.ENCHANT_THORNS_HIT, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         DamageSource damagesource;
         if (flag2) {
            damagesource = DamageSource.ON_FIRE;
         } else if (flag1) {
            damagesource = DamageSource.DROWN;
         } else {
            damagesource = DamageSource.GENERIC;
         }

         SoundEvent soundevent = this.getHurtSound(damagesource);
         if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
      }

   }

   protected void outOfWorld() {
      this.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
   }

   protected void updateArmSwingProgress() {
      int i = this.getArmSwingAnimationEnd();
      if (this.isSwingInProgress) {
         ++this.swingProgressInt;
         if (this.swingProgressInt >= i) {
            this.swingProgressInt = 0;
            this.isSwingInProgress = false;
         }
      } else {
         this.swingProgressInt = 0;
      }

      this.swingProgress = (float)this.swingProgressInt / (float)i;
   }

   public FZ getEntityAttribute(FY attribute) {
      return this.getAttributeMap().getAttributeInstance(attribute);
   }

   public FU getAttributeMap() {
      if (this.attributeMap == null) {
         this.attributeMap = new FV();
      }

      return this.attributeMap;
   }

   public IB getCreatureAttribute() {
      return IB.UNDEFINED;
   }

   public Qy getHeldItemMainhand() {
      return this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
   }

   public Qy getHeldItemOffhand() {
      return this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
   }

   public Qy getHeldItem(EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND) {
         return this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
      } else if (hand == EnumHand.OFF_HAND) {
         return this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
      } else {
         throw new IllegalArgumentException("Invalid hand " + hand);
      }
   }

   public void setHeldItem(EnumHand hand, Qy stack) {
      if (hand == EnumHand.MAIN_HAND) {
         this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
      } else {
         if (hand != EnumHand.OFF_HAND) {
            throw new IllegalArgumentException("Invalid hand " + hand);
         }

         this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, stack);
      }

   }

   public boolean hasItemInSlot(EntityEquipmentSlot p_190630_1_) {
      return !this.getItemStackFromSlot(p_190630_1_).isEmpty();
   }

   public abstract Iterable<Qy> getArmorInventoryList();

   public abstract Qy getItemStackFromSlot(EntityEquipmentSlot var1);

   public abstract void setItemStackToSlot(EntityEquipmentSlot var1, Qy var2);

   public void setSprinting(boolean sprinting) {
      super.setSprinting(sprinting);
      FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
      if (iattributeinstance.getModifier(SPRINTING_SPEED_BOOST_ID) != null) {
         iattributeinstance.removeModifier(SPRINTING_SPEED_BOOST);
      }

      if (sprinting) {
         iattributeinstance.applyModifier(SPRINTING_SPEED_BOOST);
      }

   }

   protected float getSoundVolume() {
      return 1.0F;
   }

   protected float getSoundPitch() {
      return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
   }

   protected boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F;
   }

   public void dismountEntity(Ig entityIn) {
      double d1;
      double d4;
      if (!(entityIn instanceof IR) && !(entityIn instanceof Lw)) {
         d1 = entityIn.posX;
         double d13 = entityIn.getEntityBoundingBox().minY + (double)entityIn.height;
         double d14 = entityIn.posZ;
         EnumFacing enumfacing1 = entityIn.getAdjustedHorizontalFacing();
         if (enumfacing1 != null) {
            EnumFacing enumfacing = enumfacing1.rotateY();
            int[][] aint1 = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}};
            d4 = Math.floor(this.posX) + 0.5;
            double d6 = Math.floor(this.posZ) + 0.5;
            double d7 = this.getEntityBoundingBox().maxX - this.getEntityBoundingBox().minX;
            double d8 = this.getEntityBoundingBox().maxZ - this.getEntityBoundingBox().minZ;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(d4 - d7 / 2.0, entityIn.getEntityBoundingBox().minY, d6 - d8 / 2.0, d4 + d7 / 2.0, Math.floor(entityIn.getEntityBoundingBox().minY) + (double)this.height, d6 + d8 / 2.0);
            int[][] var20 = aint1;
            int var21 = aint1.length;

            for(int var22 = 0; var22 < var21; ++var22) {
               int[] aint = var20[var22];
               double d9 = (double)(enumfacing1.getXOffset() * aint[0] + enumfacing.getXOffset() * aint[1]);
               double d10 = (double)(enumfacing1.getZOffset() * aint[0] + enumfacing.getZOffset() * aint[1]);
               double d11 = d4 + d9;
               double d12 = d6 + d10;
               AxisAlignedBB axisalignedbb1 = axisalignedbb.offset(d9, 0.0, d10);
               if (!this.world.collidesWithAnyBlock(axisalignedbb1)) {
                  if (this.world.getBlockState(new BlockPos(d11, this.posY, d12)).isTopSolid()) {
                     this.setPositionAndUpdate(d11, this.posY + 1.0, d12);
                     return;
                  }

                  BlockPos blockpos = new BlockPos(d11, this.posY - 1.0, d12);
                  if (this.world.getBlockState(blockpos).isTopSolid() || this.world.getBlockState(blockpos).getMaterial() == hM.WATER) {
                     d1 = d11;
                     d13 = this.posY + 1.0;
                     d14 = d12;
                  }
               } else if (!this.world.collidesWithAnyBlock(axisalignedbb1.offset(0.0, 1.0, 0.0)) && this.world.getBlockState(new BlockPos(d11, this.posY + 1.0, d12)).isTopSolid()) {
                  d1 = d11;
                  d13 = this.posY + 2.0;
                  d14 = d12;
               }
            }
         }

         this.setPositionAndUpdate(d1, d13, d14);
      } else {
         d1 = (double)(this.width / 2.0F + entityIn.width / 2.0F) + 0.4;
         float f;
         if (entityIn instanceof IR) {
            f = 0.0F;
         } else {
            f = 1.5707964F * (float)(this.getPrimaryHand() == EnumHandSide.RIGHT ? -1 : 1);
         }

         float f1 = -MathHelper.sin(-this.rotationYaw * 0.017453292F - 3.1415927F + f);
         float f2 = -MathHelper.cos(-this.rotationYaw * 0.017453292F - 3.1415927F + f);
         double d2 = Math.abs(f1) > Math.abs(f2) ? d1 / (double)Math.abs(f1) : d1 / (double)Math.abs(f2);
         double d3 = this.posX + (double)f1 * d2;
         d4 = this.posZ + (double)f2 * d2;
         this.setPosition(d3, entityIn.posY + (double)entityIn.height + 0.001, d4);
         if (this.world.collidesWithAnyBlock(this.getEntityBoundingBox())) {
            this.setPosition(d3, entityIn.posY + (double)entityIn.height + 1.001, d4);
            if (this.world.collidesWithAnyBlock(this.getEntityBoundingBox())) {
               this.setPosition(entityIn.posX, entityIn.posY + (double)this.height + 0.001, entityIn.posZ);
            }
         }
      }

   }

   public boolean getAlwaysRenderNameTagForRender() {
      return this.getAlwaysRenderNameTag();
   }

   protected float getJumpUpwardsMotion() {
      return 0.42F;
   }

   protected void jump() {
      this.motionY = (double)this.getJumpUpwardsMotion();
      if (this.isPotionActive(NL.JUMP_BOOST)) {
         this.motionY += (double)((float)(this.getActivePotionEffect(NL.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
      }

      if (this.isSprinting()) {
         float f = this.rotationYaw * 0.017453292F;
         this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
         this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
      }

      this.isAirBorne = true;
   }

   protected void handleJumpWater() {
      this.motionY += 0.03999999910593033;
   }

   protected void handleJumpLava() {
      this.motionY += 0.03999999910593033;
   }

   protected float getWaterSlowDown() {
      return 0.8F;
   }

   public void travel(float strafe, float vertical, float forward) {
      double d0;
      double d6;
      double d8;
      if (this.isServerWorld() || this.canPassengerSteer()) {
         float f8;
         float f7;
         float f9;
         if (this.isInWater() && (!(this instanceof ME) || !((ME)this).capabilities.isFlying)) {
            d0 = this.posY;
            f7 = this.getWaterSlowDown();
            f8 = 0.02F;
            f9 = (float)Ft.getDepthStriderModifier(this);
            if (f9 > 3.0F) {
               f9 = 3.0F;
            }

            if (!this.onGround) {
               f9 *= 0.5F;
            }

            if (f9 > 0.0F) {
               f7 += (0.54600006F - f7) * f9 / 3.0F;
               f8 += (this.getAIMoveSpeed() - f8) * f9 / 3.0F;
            }

            this.moveRelative(strafe, vertical, forward, f8);
            this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f7;
            this.motionY *= 0.800000011920929;
            this.motionZ *= (double)f7;
            if (!this.hasNoGravity()) {
               this.motionY -= 0.02;
            }

            if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579 - this.posY + d0, this.motionZ)) {
               this.motionY = 0.30000001192092896;
            }
         } else if (!this.isInLava() || this instanceof ME && ((ME)this).capabilities.isFlying) {
            if (this.isElytraFlying()) {
               if (this.motionY > -0.5) {
                  this.fallDistance = 1.0F;
               }

               Vec3d vec3d = this.getLookVec();
               float f = this.rotationPitch * 0.017453292F;
               d6 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
               d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
               double d1 = vec3d.length();
               float f4 = MathHelper.cos(f);
               f4 = (float)((double)f4 * (double)f4 * Math.min(1.0, d1 / 0.4));
               this.motionY += -0.08 + (double)f4 * 0.06;
               double d11;
               if (this.motionY < 0.0 && d6 > 0.0) {
                  d11 = this.motionY * -0.1 * (double)f4;
                  this.motionY += d11;
                  this.motionX += vec3d.x * d11 / d6;
                  this.motionZ += vec3d.z * d11 / d6;
               }

               if (f < 0.0F) {
                  d11 = d8 * (double)(-MathHelper.sin(f)) * 0.04;
                  this.motionY += d11 * 3.2;
                  this.motionX -= vec3d.x * d11 / d6;
                  this.motionZ -= vec3d.z * d11 / d6;
               }

               if (d6 > 0.0) {
                  this.motionX += (vec3d.x / d6 * d8 - this.motionX) * 0.1;
                  this.motionZ += (vec3d.z / d6 * d8 - this.motionZ) * 0.1;
               }

               this.motionX *= 0.9900000095367432;
               this.motionY *= 0.9800000190734863;
               this.motionZ *= 0.9900000095367432;
               this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
               if (this.collidedHorizontally && !this.world.isRemote) {
                  d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                  double d3 = d8 - d11;
                  float f5 = (float)(d3 * 10.0 - 3.0);
                  if (f5 > 0.0F) {
                     this.playSound(this.getFallSound((int)f5), 1.0F, 1.0F);
                     this.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
                  }
               }

               if (this.onGround && !this.world.isRemote) {
                  this.setFlag(7, false);
               }
            } else {
               float f6 = 0.91F;
               BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0, this.posZ);
               if (this.onGround) {
                  f6 = this.world.getBlockState(blockpos$pooledmutableblockpos).getBlock().slipperiness * 0.91F;
               }

               f7 = 0.16277136F / (f6 * f6 * f6);
               if (this.onGround) {
                  f8 = this.getAIMoveSpeed() * f7;
               } else {
                  f8 = this.jumpMovementFactor;
               }

               this.moveRelative(strafe, vertical, forward, f8);
               f6 = 0.91F;
               if (this.onGround) {
                  f6 = this.world.getBlockState(blockpos$pooledmutableblockpos.setPos(this.posX, this.getEntityBoundingBox().minY - 1.0, this.posZ)).getBlock().slipperiness * 0.91F;
               }

               if (this.isOnLadder()) {
                  f9 = 0.15F;
                  this.motionX = MathHelper.clamp(this.motionX, -0.15000000596046448, 0.15000000596046448);
                  this.motionZ = MathHelper.clamp(this.motionZ, -0.15000000596046448, 0.15000000596046448);
                  this.fallDistance = 0.0F;
                  if (this.motionY < -0.15) {
                     this.motionY = -0.15;
                  }

                  boolean flag = this.isSneaking() && this instanceof ME;
                  if (flag && this.motionY < 0.0) {
                     this.motionY = 0.0;
                  }
               }

               this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
               if (this.collidedHorizontally && this.isOnLadder()) {
                  this.motionY = 0.2;
               }

               if (this.isPotionActive(NL.LEVITATION)) {
                  this.motionY += (0.05 * (double)(this.getActivePotionEffect(NL.LEVITATION).getAmplifier() + 1) - this.motionY) * 0.2;
               } else {
                  blockpos$pooledmutableblockpos.setPos(this.posX, 0.0, this.posZ);
                  if (!this.world.isRemote || this.world.isBlockLoaded(blockpos$pooledmutableblockpos) && this.world.getChunk(blockpos$pooledmutableblockpos).isLoaded()) {
                     if (!this.hasNoGravity()) {
                        this.motionY -= 0.08;
                     }
                  } else if (this.posY > 0.0) {
                     this.motionY = -0.1;
                  } else {
                     this.motionY = 0.0;
                  }
               }

               this.motionY *= 0.9800000190734863;
               this.motionX *= (double)f6;
               this.motionZ *= (double)f6;
               blockpos$pooledmutableblockpos.release();
            }
         } else {
            d0 = this.posY;
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
            if (!this.hasNoGravity()) {
               this.motionY -= 0.02;
            }

            if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579 - this.posY + d0, this.motionZ)) {
               this.motionY = 0.30000001192092896;
            }
         }
      }

      this.prevLimbSwingAmount = this.limbSwingAmount;
      d0 = this.posX - this.prevPosX;
      d6 = this.posZ - this.prevPosZ;
      d8 = this instanceof LD ? this.posY - this.prevPosY : 0.0;
      float f10 = MathHelper.sqrt(d0 * d0 + d8 * d8 + d6 * d6) * 4.0F;
      if (f10 > 1.0F) {
         f10 = 1.0F;
      }

      this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
      this.limbSwing += this.limbSwingAmount;
   }

   public float getAIMoveSpeed() {
      return this.landMovementFactor;
   }

   public void setAIMoveSpeed(float speedIn) {
      this.landMovementFactor = speedIn;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      this.setLastAttackedEntity(entityIn);
      return false;
   }

   public boolean isPlayerSleeping() {
      return false;
   }

   public void onUpdate() {
      super.onUpdate();
      this.updateActiveHand();
      if (!this.world.isRemote) {
         int i = this.getArrowCountInEntity();
         if (i > 0) {
            if (this.arrowHitTimer <= 0) {
               this.arrowHitTimer = 20 * (30 - i);
            }

            --this.arrowHitTimer;
            if (this.arrowHitTimer <= 0) {
               this.setArrowCountInEntity(i - 1);
            }
         }

         EntityEquipmentSlot[] var2 = EntityEquipmentSlot.values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            EntityEquipmentSlot entityequipmentslot = var2[var4];
            Qy itemstack;
            switch (entityequipmentslot.getSlotType()) {
               case HAND:
                  itemstack = (Qy)this.handInventory.get(entityequipmentslot.getIndex());
                  break;
               case ARMOR:
                  itemstack = (Qy)this.armorArray.get(entityequipmentslot.getIndex());
                  break;
               default:
                  continue;
            }

            Qy itemstack1 = this.getItemStackFromSlot(entityequipmentslot);
            if (!Qy.areItemStacksEqual(itemstack1, itemstack)) {
               ((bis)this.world).getEntityTracker().sendToTracking(this, new TY(this.getEntityId(), entityequipmentslot, itemstack1));
               if (!itemstack.isEmpty()) {
                  this.getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers(entityequipmentslot));
               }

               if (!itemstack1.isEmpty()) {
                  this.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot));
               }

               switch (entityequipmentslot.getSlotType()) {
                  case HAND:
                     this.handInventory.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? Qy.EMPTY : itemstack1.copy());
                     break;
                  case ARMOR:
                     this.armorArray.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? Qy.EMPTY : itemstack1.copy());
               }
            }
         }

         if (this.ticksExisted % 20 == 0) {
            this.getCombatTracker().reset();
         }

         if (!this.glowing) {
            boolean flag = this.isPotionActive(NL.GLOWING);
            if (this.getFlag(6) != flag) {
               this.setFlag(6, flag);
            }
         }
      }

      this.onLivingUpdate();
      double d0 = this.posX - this.prevPosX;
      double d1 = this.posZ - this.prevPosZ;
      float f3 = (float)(d0 * d0 + d1 * d1);
      float f4 = this.renderYawOffset;
      float f5 = 0.0F;
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      float f = 0.0F;
      if (f3 > 0.0025000002F) {
         f = 1.0F;
         f5 = (float)Math.sqrt((double)f3) * 3.0F;
         float f1 = (float)MathHelper.atan2(d1, d0) * 57.295776F - 90.0F;
         float f2 = MathHelper.abs(MathHelper.wrapDegrees(this.rotationYaw) - f1);
         if (95.0F < f2 && f2 < 265.0F) {
            f4 = f1 - 180.0F;
         } else {
            f4 = f1;
         }
      }

      if (this.swingProgress > 0.0F) {
         f4 = this.rotationYaw;
      }

      if (!this.onGround) {
         f = 0.0F;
      }

      this.onGroundSpeedFactor += (f - this.onGroundSpeedFactor) * 0.3F;
      this.world.profiler.startSection("headTurn");
      f5 = this.updateDistance(f4, f5);
      this.world.profiler.endSection();
      this.world.profiler.startSection("rangeChecks");

      while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
         this.prevRotationYaw -= 360.0F;
      }

      while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
         this.prevRotationYaw += 360.0F;
      }

      while(this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
         this.prevRenderYawOffset -= 360.0F;
      }

      while(this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
         this.prevRenderYawOffset += 360.0F;
      }

      while(this.rotationPitch - this.prevRotationPitch < -180.0F) {
         this.prevRotationPitch -= 360.0F;
      }

      while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
         this.prevRotationPitch += 360.0F;
      }

      while(this.rotationYawHead - this.prevRotationYawHead < -180.0F) {
         this.prevRotationYawHead -= 360.0F;
      }

      while(this.rotationYawHead - this.prevRotationYawHead >= 180.0F) {
         this.prevRotationYawHead += 360.0F;
      }

      this.world.profiler.endSection();
      this.movedDistance += f5;
      if (this.isElytraFlying()) {
         ++this.ticksElytraFlying;
      } else {
         this.ticksElytraFlying = 0;
      }

   }

   protected float updateDistance(float p_110146_1_, float p_110146_2_) {
      float f = MathHelper.wrapDegrees(p_110146_1_ - this.renderYawOffset);
      this.renderYawOffset += f * 0.3F;
      float f1 = MathHelper.wrapDegrees(this.rotationYaw - this.renderYawOffset);
      boolean flag = f1 < -90.0F || f1 >= 90.0F;
      if (f1 < -75.0F) {
         f1 = -75.0F;
      }

      if (f1 >= 75.0F) {
         f1 = 75.0F;
      }

      this.renderYawOffset = this.rotationYaw - f1;
      if (f1 * f1 > 2500.0F) {
         this.renderYawOffset += f1 * 0.2F;
      }

      if (flag) {
         p_110146_2_ *= -1.0F;
      }

      return p_110146_2_;
   }

   public void onLivingUpdate() {
      if (this.jumpTicks > 0) {
         --this.jumpTicks;
      }

      if (this.newPosRotationIncrements > 0 && !this.canPassengerSteer()) {
         double d0 = this.posX + (this.interpTargetX - this.posX) / (double)this.newPosRotationIncrements;
         double d1 = this.posY + (this.interpTargetY - this.posY) / (double)this.newPosRotationIncrements;
         double d2 = this.posZ + (this.interpTargetZ - this.posZ) / (double)this.newPosRotationIncrements;
         double d3 = MathHelper.wrapDegrees(this.interpTargetYaw - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.newPosRotationIncrements);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.interpTargetPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
         --this.newPosRotationIncrements;
         this.setPosition(d0, d1, d2);
         this.setRotation(this.rotationYaw, this.rotationPitch);
      } else if (!this.isServerWorld()) {
         this.motionX *= 0.98;
         this.motionY *= 0.98;
         this.motionZ *= 0.98;
      }

      if (Math.abs(this.motionX) < 0.003) {
         this.motionX = 0.0;
      }

      if (Math.abs(this.motionY) < 0.003) {
         this.motionY = 0.0;
      }

      if (Math.abs(this.motionZ) < 0.003) {
         this.motionZ = 0.0;
      }

      this.world.profiler.startSection("ai");
      if (this.isMovementBlocked()) {
         this.isJumping = false;
         this.moveStrafing = 0.0F;
         this.moveForward = 0.0F;
         this.randomYawVelocity = 0.0F;
      } else if (this.isServerWorld()) {
         this.world.profiler.startSection("newAi");
         this.updateEntityActionState();
         this.world.profiler.endSection();
      }

      this.world.profiler.endSection();
      this.world.profiler.startSection("jump");
      if (this.isJumping) {
         if (this.isInWater()) {
            this.handleJumpWater();
         } else if (this.isInLava()) {
            this.handleJumpLava();
         } else if (this.onGround && this.jumpTicks == 0) {
            this.jump();
            this.jumpTicks = 10;
         }
      } else {
         this.jumpTicks = 0;
      }

      this.world.profiler.endSection();
      this.world.profiler.startSection("travel");
      this.moveStrafing *= 0.98F;
      this.moveForward *= 0.98F;
      this.randomYawVelocity *= 0.9F;
      this.updateElytra();
      this.travel(this.moveStrafing, this.moveVertical, this.moveForward);
      this.world.profiler.endSection();
      this.world.profiler.startSection("push");
      this.collideWithNearbyEntities();
      this.world.profiler.endSection();
   }

   private void updateElytra() {
      boolean flag = this.getFlag(7);
      if (flag && !this.onGround && !this.isRiding()) {
         Qy itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
         if (itemstack.getItem() == NK.ELYTRA && Pt.isUsable(itemstack)) {
            flag = true;
            if (!this.world.isRemote && (this.ticksElytraFlying + 1) % 20 == 0) {
               itemstack.damageItem(1, this);
            }
         } else {
            flag = false;
         }
      } else {
         flag = false;
      }

      if (!this.world.isRemote) {
         this.setFlag(7, flag);
      }

   }

   protected void updateEntityActionState() {
   }

   protected void collideWithNearbyEntities() {
      List<Ig> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), EntitySelectors.getTeamCollisionPredicate(this));
      if (!list.isEmpty()) {
         int i = this.world.getGameRules().getInt("maxEntityCramming");
         int j;
         if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0) {
            j = 0;

            for(int k = 0; k < list.size(); ++k) {
               if (!((Ig)list.get(k)).isRiding()) {
                  ++j;
               }
            }

            if (j > i - 1) {
               this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
            }
         }

         for(j = 0; j < list.size(); ++j) {
            Ig entity = (Ig)list.get(j);
            this.collideWithEntity(entity);
         }
      }

   }

   protected void collideWithEntity(Ig entityIn) {
      entityIn.applyEntityCollision(this);
   }

   public void dismountRidingEntity() {
      Ig entity = this.getRidingEntity();
      super.dismountRidingEntity();
      if (entity != null && entity != this.getRidingEntity() && !this.world.isRemote) {
         this.dismountEntity(entity);
      }

   }

   public void updateRidden() {
      super.updateRidden();
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      this.onGroundSpeedFactor = 0.0F;
      this.fallDistance = 0.0F;
   }

   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
      this.interpTargetX = x;
      this.interpTargetY = y;
      this.interpTargetZ = z;
      this.interpTargetYaw = (double)yaw;
      this.interpTargetPitch = (double)pitch;
      this.newPosRotationIncrements = posRotationIncrements;
   }

   public void setJumping(boolean jumping) {
      this.isJumping = jumping;
   }

   public void onItemPickup(Ig entityIn, int quantity) {
      if (!entityIn.isDead && !this.world.isRemote) {
         Iz entitytracker = ((bis)this.world).getEntityTracker();
         if (entityIn instanceof IY || entityIn instanceof MO || entityIn instanceof Js) {
            entitytracker.sendToTracking(entityIn, new TG(entityIn.getEntityId(), this.getEntityId(), quantity));
         }
      }

   }

   public boolean canEntityBeSeen(Ig entityIn) {
      return this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), new Vec3d(entityIn.posX, entityIn.posY + (double)entityIn.getEyeHeight(), entityIn.posZ), false, true, false) == null;
   }

   public Vec3d getLook(float partialTicks) {
      if (partialTicks == 1.0F) {
         return getVectorForRotation(this.rotationPitch, this.rotationYawHead);
      } else {
         float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
         float f1 = this.prevRotationYawHead + (this.rotationYawHead - this.prevRotationYawHead) * partialTicks;
         return getVectorForRotation(f, f1);
      }
   }

   public float getSwingProgress(float partialTickTime) {
      float f = this.swingProgress - this.prevSwingProgress;
      if (f < 0.0F) {
         ++f;
      }

      return this.prevSwingProgress + f * partialTickTime;
   }

   public boolean isServerWorld() {
      return !this.world.isRemote;
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public boolean canBePushed() {
      if (0bz.method_Qm().method_Qs().method_bxY(0bT.class).method_bBh() && 0bT.field_a.method_bna() && this instanceof jh) {
         return false;
      } else {
         return this.isEntityAlive() && !this.isOnLadder();
      }
   }

   protected void markVelocityChanged() {
      this.velocityChanged = this.rand.nextDouble() >= this.getEntityAttribute(Ni.KNOCKBACK_RESISTANCE).getAttributeValue();
   }

   public float getRotationYawHead() {
      return this.rotationYawHead;
   }

   public void setRotationYawHead(float rotation) {
      this.rotationYawHead = rotation;
   }

   public void setRenderYawOffset(float offset) {
      this.renderYawOffset = offset;
   }

   public float getAbsorptionAmount() {
      return this.absorptionAmount;
   }

   public void setAbsorptionAmount(float amount) {
      if (amount < 0.0F) {
         amount = 0.0F;
      }

      this.absorptionAmount = amount;
   }

   public void sendEnterCombat() {
   }

   public void sendEndCombat() {
   }

   protected void markPotionsDirty() {
      this.potionsNeedUpdate = true;
   }

   public abstract EnumHandSide getPrimaryHand();

   public boolean isHandActive() {
      return ((Byte)this.dataManager.get(HAND_STATES) & 1) > 0;
   }

   public EnumHand getActiveHand() {
      return ((Byte)this.dataManager.get(HAND_STATES) & 2) > 0 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
   }

   protected void updateActiveHand() {
      if (this.isHandActive()) {
         Qy itemstack = this.getHeldItem(this.getActiveHand());
         if (itemstack == this.activeItemStack) {
            if (this.getItemInUseCount() <= 25 && this.getItemInUseCount() % 4 == 0) {
               this.updateItemUse(this.activeItemStack, 5);
            }

            if (--this.activeItemStackUseCount == 0 && !this.world.isRemote) {
               this.onItemUseFinish();
            }
         } else {
            this.resetActiveHand();
         }
      }

   }

   public void setActiveHand(EnumHand hand) {
      Qy itemstack = this.getHeldItem(hand);
      if (!itemstack.isEmpty() && !this.isHandActive()) {
         this.activeItemStack = itemstack;
         this.activeItemStackUseCount = itemstack.getMaxItemUseDuration();
         if (!this.world.isRemote) {
            int i = 1;
            if (hand == EnumHand.OFF_HAND) {
               i |= 2;
            }

            this.dataManager.set(HAND_STATES, (byte)i);
         }
      }

   }

   public void notifyDataManagerChange(Rd<?> key) {
      super.notifyDataManagerChange(key);
      if (HAND_STATES.equals(key) && this.world.isRemote) {
         if (this.isHandActive() && this.activeItemStack.isEmpty()) {
            this.activeItemStack = this.getHeldItem(this.getActiveHand());
            if (!this.activeItemStack.isEmpty()) {
               this.activeItemStackUseCount = this.activeItemStack.getMaxItemUseDuration();
            }
         } else if (!this.isHandActive() && !this.activeItemStack.isEmpty()) {
            this.activeItemStack = Qy.EMPTY;
            this.activeItemStackUseCount = 0;
         }
      }

   }

   protected void updateItemUse(Qy stack, int eatingParticleCount) {
      if (!stack.isEmpty() && this.isHandActive()) {
         if (stack.getItemUseAction() == Ol.DRINK) {
            this.playSound(NO.ENTITY_GENERIC_DRINK, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
         }

         if (stack.getItemUseAction() == Ol.EAT) {
            for(int i = 0; i < eatingParticleCount; ++i) {
               Vec3d vec3d = new Vec3d(((double)this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
               vec3d = vec3d.rotatePitch(-this.rotationPitch * 0.017453292F);
               vec3d = vec3d.rotateYaw(-this.rotationYaw * 0.017453292F);
               double d0 = (double)(-this.rand.nextFloat()) * 0.6 - 0.3;
               Vec3d vec3d1 = new Vec3d(((double)this.rand.nextFloat() - 0.5) * 0.3, d0, 0.6);
               vec3d1 = vec3d1.rotatePitch(-this.rotationPitch * 0.017453292F);
               vec3d1 = vec3d1.rotateYaw(-this.rotationYaw * 0.017453292F);
               vec3d1 = vec3d1.add(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
               if (stack.getHasSubtypes()) {
                  this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05, vec3d.z, OL.getIdFromItem(stack.getItem()), stack.getMetadata());
               } else {
                  this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05, vec3d.z, OL.getIdFromItem(stack.getItem()));
               }
            }

            this.playSound(NO.ENTITY_GENERIC_EAT, 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }
      }

   }

   protected void onItemUseFinish() {
      if (!this.activeItemStack.isEmpty() && this.isHandActive()) {
         this.updateItemUse(this.activeItemStack, 16);
         this.setHeldItem(this.getActiveHand(), this.activeItemStack.onItemUseFinish(this.world, this));
         this.resetActiveHand();
      }

   }

   public Qy getActiveItemStack() {
      return this.activeItemStack;
   }

   public int getItemInUseCount() {
      return this.activeItemStackUseCount;
   }

   public int getItemInUseMaxCount() {
      return this.isHandActive() ? this.activeItemStack.getMaxItemUseDuration() - this.getItemInUseCount() : 0;
   }

   public void stopActiveHand() {
      if (!this.activeItemStack.isEmpty()) {
         this.activeItemStack.onPlayerStoppedUsing(this.world, this, this.getItemInUseCount());
      }

      this.resetActiveHand();
   }

   public void resetActiveHand() {
      if (!this.world.isRemote) {
         this.dataManager.set(HAND_STATES, (byte)0);
      }

      this.activeItemStack = Qy.EMPTY;
      this.activeItemStackUseCount = 0;
   }

   public boolean isActiveItemStackBlocking() {
      if (this.isHandActive() && !this.activeItemStack.isEmpty()) {
         OL item = this.activeItemStack.getItem();
         if (item.getItemUseAction(this.activeItemStack) != Ol.BLOCK) {
            return false;
         } else {
            return item.getMaxItemUseDuration(this.activeItemStack) - this.activeItemStackUseCount >= 5;
         }
      } else {
         return false;
      }
   }

   public boolean isElytraFlying() {
      return this.getFlag(7);
   }

   public int getTicksElytraFlying() {
      return this.ticksElytraFlying;
   }

   public boolean attemptTeleport(double x, double y, double z) {
      double d0 = this.posX;
      double d1 = this.posY;
      double d2 = this.posZ;
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      boolean flag = false;
      BlockPos blockpos = new BlockPos(this);
      bij world = this.world;
      Random random = this.getRNG();
      boolean flag1;
      if (world.isBlockLoaded(blockpos)) {
         flag1 = false;

         while(!flag1 && blockpos.getY() > 0) {
            BlockPos blockpos1 = blockpos.down();
            in iblockstate = world.getBlockState(blockpos1);
            if (iblockstate.getMaterial().blocksMovement()) {
               flag1 = true;
            } else {
               --this.posY;
               blockpos = blockpos1;
            }
         }

         if (flag1) {
            this.setPositionAndUpdate(this.posX, this.posY, this.posZ);
            if (world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(this.getEntityBoundingBox())) {
               flag = true;
            }
         }
      }

      if (!flag) {
         this.setPositionAndUpdate(d0, d1, d2);
         return false;
      } else {
         flag1 = true;

         for(int j = 0; j < 128; ++j) {
            double d6 = (double)j / 127.0;
            float f = (random.nextFloat() - 0.5F) * 0.2F;
            float f1 = (random.nextFloat() - 0.5F) * 0.2F;
            float f2 = (random.nextFloat() - 0.5F) * 0.2F;
            double d3 = d0 + (this.posX - d0) * d6 + (random.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d4 = d1 + (this.posY - d1) * d6 + random.nextDouble() * (double)this.height;
            double d5 = d2 + (this.posZ - d2) * d6 + (random.nextDouble() - 0.5) * (double)this.width * 2.0;
            world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2);
         }

         if (this instanceof Ik) {
            ((Ik)this).getNavigator().clearPath();
         }

         return true;
      }
   }

   public boolean canBeHitWithPotion() {
      return true;
   }

   public boolean attackable() {
      return true;
   }

   public void setPartying(BlockPos pos, boolean isPartying) {
   }

   static {
      SPRINTING_SPEED_BOOST = (new FW(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", 0.30000001192092896, 2)).setSaved(false);
      HAND_STATES = Rv.createKey(Iw.class, Rt.BYTE);
      HEALTH = Rv.createKey(Iw.class, Rt.FLOAT);
      POTION_EFFECTS = Rv.createKey(Iw.class, Rt.VARINT);
      HIDE_PARTICLES = Rv.createKey(Iw.class, Rt.BOOLEAN);
      ARROW_COUNT_IN_ENTITY = Rv.createKey(Iw.class, Rt.VARINT);
   }
}
