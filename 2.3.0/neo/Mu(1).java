package neo;

import com.google.common.base.Predicate;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Mu extends Mg {
   private static final Rd<Float> DATA_HEALTH_ID;
   private static final Rd<Boolean> BEGGING;
   private static final Rd<Integer> COLLAR_COLOR;
   private float headRotationCourse;
   private float headRotationCourseOld;
   private boolean isWet;
   private boolean isShaking;
   private float timeWolfIsShaking;
   private float prevTimeWolfIsShaking;

   public Mu(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 0.85F);
      this.setTamed(false);
   }

   protected void initEntityAI() {
      this.aiSit = new Hc(this);
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(3, new Mt(this, this, LK.class, 24.0F, 1.5, 1.5));
      this.tasks.addTask(4, new GD(this, 0.4F));
      this.tasks.addTask(5, new Gd(this, 1.0, true));
      this.tasks.addTask(6, new Gx(this, 1.0, 10.0F, 2.0F));
      this.tasks.addTask(7, new GI(this, 1.0));
      this.tasks.addTask(8, new Ho(this, 1.0));
      this.tasks.addTask(9, new Gj(this, 8.0F));
      this.tasks.addTask(10, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(10, new GH(this));
      this.targetTasks.addTask(1, new GV(this));
      this.targetTasks.addTask(2, new GW(this));
      this.targetTasks.addTask(3, new GB(this, true, new Class[0]));
      this.targetTasks.addTask(4, new Hg(this, Ly.class, false, new Predicate<Ig>() {
         public boolean apply(@Nullable Ig p_apply_1_) {
            return p_apply_1_ instanceof Mb || p_apply_1_ instanceof LY;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ig)var1);
         }
      }));
      this.targetTasks.addTask(5, new GR(this, Jx.class, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
      if (this.isTamed()) {
         this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(8.0);
      }

      this.getAttributeMap().registerAttribute(Ni.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   public void setAttackTarget(@Nullable Iw entitylivingbaseIn) {
      super.setAttackTarget(entitylivingbaseIn);
      if (entitylivingbaseIn == null) {
         this.setAngry(false);
      } else if (!this.isTamed()) {
         this.setAngry(true);
      }

   }

   protected void updateAITasks() {
      this.dataManager.set(DATA_HEALTH_ID, this.getHealth());
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
      this.dataManager.register(BEGGING, false);
      this.dataManager.register(COLLAR_COLOR, Om.RED.getDyeDamage());
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_WOLF_STEP, 0.15F, 1.0F);
   }

   public static void registerFixesWolf(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Mu.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Angry", this.isAngry());
      compound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setAngry(compound.getBoolean("Angry"));
      if (compound.hasKey("CollarColor", 99)) {
         this.setCollarColor(Om.byDyeDamage(compound.getByte("CollarColor")));
      }

   }

   protected SoundEvent getAmbientSound() {
      if (this.isAngry()) {
         return NO.ENTITY_WOLF_GROWL;
      } else if (this.rand.nextInt(3) != 0) {
         return NO.ENTITY_WOLF_AMBIENT;
      } else {
         return this.isTamed() && (Float)this.dataManager.get(DATA_HEALTH_ID) < 10.0F ? NO.ENTITY_WOLF_WHINE : NO.ENTITY_WOLF_PANT;
      }
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_WOLF_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_WOLF_DEATH;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_WOLF;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.world.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
         this.world.setEntityState(this, (byte)8);
      }

      if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
         this.setAngry(false);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      this.headRotationCourseOld = this.headRotationCourse;
      if (this.isBegging()) {
         this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
      } else {
         this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
      }

      if (this.isWet()) {
         this.isWet = true;
         this.isShaking = false;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else if ((this.isWet || this.isShaking) && this.isShaking) {
         if (this.timeWolfIsShaking == 0.0F) {
            this.playSound(NO.ENTITY_WOLF_SHAKE, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
         this.timeWolfIsShaking += 0.05F;
         if (this.prevTimeWolfIsShaking >= 2.0F) {
            this.isWet = false;
            this.isShaking = false;
            this.prevTimeWolfIsShaking = 0.0F;
            this.timeWolfIsShaking = 0.0F;
         }

         if (this.timeWolfIsShaking > 0.4F) {
            float f = (float)this.getEntityBoundingBox().minY;
            int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);

            for(int j = 0; j < i; ++j) {
               float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
               this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
            }
         }
      }

   }

   public boolean isWolfWet() {
      return this.isWet;
   }

   public float getShadingWhileWet(float p_70915_1_) {
      return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
   }

   public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
      float f = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;
      if (f < 0.0F) {
         f = 0.0F;
      } else if (f > 1.0F) {
         f = 1.0F;
      }

      return MathHelper.sin(f * 3.1415927F) * MathHelper.sin(f * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
   }

   public float getInterestedAngle(float p_70917_1_) {
      return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * 3.1415927F;
   }

   public float getEyeHeight() {
      return this.height * 0.8F;
   }

   public int getVerticalFaceSpeed() {
      return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         Ig entity = source.getTrueSource();
         if (this.aiSit != null) {
            this.aiSit.setSitting(false);
         }

         if (entity != null && !(entity instanceof ME) && !(entity instanceof MO)) {
            amount = (amount + 1.0F) / 2.0F;
         }

         return super.attackEntityFrom(source, amount);
      }
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(Ni.ATTACK_DAMAGE).getAttributeValue()));
      if (flag) {
         this.applyEnchantments(this, entityIn);
      }

      return flag;
   }

   public void setTamed(boolean tamed) {
      super.setTamed(tamed);
      if (tamed) {
         this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(20.0);
      } else {
         this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(8.0);
      }

      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(4.0);
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (this.isTamed()) {
         if (!itemstack.isEmpty()) {
            if (itemstack.getItem() instanceof PI) {
               PI itemfood = (PI)itemstack.getItem();
               if (itemfood.isWolfsFavoriteMeat() && (Float)this.dataManager.get(DATA_HEALTH_ID) < 20.0F) {
                  if (!player.capabilities.isCreativeMode) {
                     itemstack.shrink(1);
                  }

                  this.heal((float)itemfood.getHealAmount(itemstack));
                  return true;
               }
            } else if (itemstack.getItem() == NK.DYE) {
               Om enumdyecolor = Om.byDyeDamage(itemstack.getMetadata());
               if (enumdyecolor != this.getCollarColor()) {
                  this.setCollarColor(enumdyecolor);
                  if (!player.capabilities.isCreativeMode) {
                     itemstack.shrink(1);
                  }

                  return true;
               }
            }
         }

         if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
            this.aiSit.setSitting(!this.isSitting());
            this.isJumping = false;
            this.navigator.clearPath();
            this.setAttackTarget((Iw)null);
         }
      } else if (itemstack.getItem() == NK.BONE && !this.isAngry()) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         if (!this.world.isRemote) {
            if (this.rand.nextInt(3) == 0) {
               this.setTamedBy(player);
               this.navigator.clearPath();
               this.setAttackTarget((Iw)null);
               this.aiSit.setSitting(true);
               this.setHealth(20.0F);
               this.playTameEffect(true);
               this.world.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.world.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.processInteract(player, hand);
   }

   public void handleStatusUpdate(byte id) {
      if (id == 8) {
         this.isShaking = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public float getTailRotation() {
      if (this.isAngry()) {
         return 1.5393804F;
      } else {
         return this.isTamed() ? (0.55F - (this.getMaxHealth() - (Float)this.dataManager.get(DATA_HEALTH_ID)) * 0.02F) * 3.1415927F : 0.62831855F;
      }
   }

   public boolean isBreedingItem(Qy stack) {
      return stack.getItem() instanceof PI && ((PI)stack.getItem()).isWolfsFavoriteMeat();
   }

   public int getMaxSpawnedInChunk() {
      return 8;
   }

   public boolean isAngry() {
      return ((Byte)this.dataManager.get(TAMED) & 2) != 0;
   }

   public void setAngry(boolean angry) {
      byte b0 = (Byte)this.dataManager.get(TAMED);
      if (angry) {
         this.dataManager.set(TAMED, (byte)(b0 | 2));
      } else {
         this.dataManager.set(TAMED, (byte)(b0 & -3));
      }

   }

   public Om getCollarColor() {
      return Om.byDyeDamage((Integer)this.dataManager.get(COLLAR_COLOR) & 15);
   }

   public void setCollarColor(Om collarcolor) {
      this.dataManager.set(COLLAR_COLOR, collarcolor.getDyeDamage());
   }

   public Mu createChild(Ih ageable) {
      Mu entitywolf = new Mu(this.world);
      UUID uuid = this.getOwnerId();
      if (uuid != null) {
         entitywolf.setOwnerId(uuid);
         entitywolf.setTamed(true);
      }

      return entitywolf;
   }

   public void setBegging(boolean beg) {
      this.dataManager.set(BEGGING, beg);
   }

   public boolean canMateWith(Ly otherAnimal) {
      if (otherAnimal == this) {
         return false;
      } else if (!this.isTamed()) {
         return false;
      } else if (!(otherAnimal instanceof Mu)) {
         return false;
      } else {
         Mu entitywolf = (Mu)otherAnimal;
         if (!entitywolf.isTamed()) {
            return false;
         } else if (entitywolf.isSitting()) {
            return false;
         } else {
            return this.isInLove() && entitywolf.isInLove();
         }
      }
   }

   public boolean isBegging() {
      return (Boolean)this.dataManager.get(BEGGING);
   }

   public boolean shouldAttackEntity(Iw target, Iw owner) {
      if (!(target instanceof JB) && !(target instanceof JW)) {
         if (target instanceof Mu) {
            Mu entitywolf = (Mu)target;
            if (entitywolf.isTamed() && entitywolf.getOwner() == owner) {
               return false;
            }
         }

         if (target instanceof ME && owner instanceof ME && !((ME)owner).canAttackPlayer((ME)target)) {
            return false;
         } else {
            return !(target instanceof Lw) || !((Lw)target).isTame();
         }
      } else {
         return false;
      }
   }

   public boolean canBeLeashedTo(ME player) {
      return !this.isAngry() && super.canBeLeashedTo(player);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   // $FF: synthetic method
   static Random access$000(Mu x0) {
      return x0.rand;
   }

   static {
      DATA_HEALTH_ID = Rv.createKey(Mu.class, Rt.FLOAT);
      BEGGING = Rv.createKey(Mu.class, Rt.BOOLEAN);
      COLLAR_COLOR = Rv.createKey(Mu.class, Rt.VARINT);
   }
}
