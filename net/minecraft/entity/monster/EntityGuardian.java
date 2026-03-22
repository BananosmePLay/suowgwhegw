package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityGuardian extends EntityMob {
   private static final DataParameter<Boolean> MOVING;
   private static final DataParameter<Integer> TARGET_ENTITY;
   protected float clientSideTailAnimation;
   protected float clientSideTailAnimationO;
   protected float clientSideTailAnimationSpeed;
   protected float clientSideSpikesAnimation;
   protected float clientSideSpikesAnimationO;
   private EntityLivingBase targetedEntity;
   private int clientSideAttackTime;
   private boolean clientSideTouchedGround;
   protected EntityAIWander wander;

   public EntityGuardian(World worldIn) {
      super(worldIn);
      this.experienceValue = 10;
      this.setSize(0.85F, 0.85F);
      this.moveHelper = new GuardianMoveHelper(this);
      this.clientSideTailAnimation = this.rand.nextFloat();
      this.clientSideTailAnimationO = this.clientSideTailAnimation;
   }

   protected void initEntityAI() {
      EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0);
      this.wander = new EntityAIWander(this, 1.0, 80);
      this.tasks.addTask(4, new AIGuardianAttack(this));
      this.tasks.addTask(5, entityaimovetowardsrestriction);
      this.tasks.addTask(7, this.wander);
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.wander.setMutexBits(3);
      entityaimovetowardsrestriction.setMutexBits(3);
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new GuardianTargetSelector(this)));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
   }

   public static void registerFixesGuardian(DataFixer fixer) {
      EntityLiving.registerFixesMob(fixer, EntityGuardian.class);
   }

   protected PathNavigate createNavigator(World worldIn) {
      return new PathNavigateSwimmer(this, worldIn);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(MOVING, false);
      this.dataManager.register(TARGET_ENTITY, 0);
   }

   public boolean isMoving() {
      return (Boolean)this.dataManager.get(MOVING);
   }

   private void setMoving(boolean moving) {
      this.dataManager.set(MOVING, moving);
   }

   public int getAttackDuration() {
      return 80;
   }

   private void setTargetedEntity(int entityId) {
      this.dataManager.set(TARGET_ENTITY, entityId);
   }

   public boolean hasTargetedEntity() {
      return (Integer)this.dataManager.get(TARGET_ENTITY) != 0;
   }

   @Nullable
   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.world.isRemote) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entity = this.world.getEntityByID((Integer)this.dataManager.get(TARGET_ENTITY));
            if (entity instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.getAttackTarget();
      }
   }

   public void notifyDataManagerChange(DataParameter<?> key) {
      super.notifyDataManagerChange(key);
      if (TARGET_ENTITY.equals(key)) {
         this.clientSideAttackTime = 0;
         this.targetedEntity = null;
      }

   }

   public int getTalkInterval() {
      return 160;
   }

   protected SoundEvent getAmbientSound() {
      return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_AMBIENT : SoundEvents.ENTITY_GUARDIAN_AMBIENT_LAND;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_HURT : SoundEvents.ENTITY_GUARDIAN_HURT_LAND;
   }

   protected SoundEvent getDeathSound() {
      return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_DEATH : SoundEvents.ENTITY_GUARDIAN_DEATH_LAND;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public float getEyeHeight() {
      return this.height * 0.5F;
   }

   public float getBlockPathWeight(BlockPos pos) {
      return this.world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
   }

   public void onLivingUpdate() {
      if (this.world.isRemote) {
         this.clientSideTailAnimationO = this.clientSideTailAnimation;
         if (!this.isInWater()) {
            this.clientSideTailAnimationSpeed = 2.0F;
            if (this.motionY > 0.0 && this.clientSideTouchedGround && !this.isSilent()) {
               this.world.playSound(this.posX, this.posY, this.posZ, this.getFlopSound(), this.getSoundCategory(), 1.0F, 1.0F, false);
            }

            this.clientSideTouchedGround = this.motionY < 0.0 && this.world.isBlockNormalCube((new BlockPos(this)).down(), false);
         } else if (this.isMoving()) {
            if (this.clientSideTailAnimationSpeed < 0.5F) {
               this.clientSideTailAnimationSpeed = 4.0F;
            } else {
               this.clientSideTailAnimationSpeed += (0.5F - this.clientSideTailAnimationSpeed) * 0.1F;
            }
         } else {
            this.clientSideTailAnimationSpeed += (0.125F - this.clientSideTailAnimationSpeed) * 0.2F;
         }

         this.clientSideTailAnimation += this.clientSideTailAnimationSpeed;
         this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
         if (!this.isInWater()) {
            this.clientSideSpikesAnimation = this.rand.nextFloat();
         } else if (this.isMoving()) {
            this.clientSideSpikesAnimation += (0.0F - this.clientSideSpikesAnimation) * 0.25F;
         } else {
            this.clientSideSpikesAnimation += (1.0F - this.clientSideSpikesAnimation) * 0.06F;
         }

         if (this.isMoving() && this.isInWater()) {
            Vec3d vec3d = this.getLook(0.0F);

            for(int i = 0; i < 2; ++i) {
               this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width - vec3d.x * 1.5, this.posY + this.rand.nextDouble() * (double)this.height - vec3d.y * 1.5, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width - vec3d.z * 1.5, 0.0, 0.0, 0.0);
            }
         }

         if (this.hasTargetedEntity()) {
            if (this.clientSideAttackTime < this.getAttackDuration()) {
               ++this.clientSideAttackTime;
            }

            EntityLivingBase entitylivingbase = this.getTargetedEntity();
            if (entitylivingbase != null) {
               this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
               this.getLookHelper().onUpdateLook();
               double d5 = (double)this.getAttackAnimationScale(0.0F);
               double d0 = entitylivingbase.posX - this.posX;
               double d1 = entitylivingbase.posY + (double)(entitylivingbase.height * 0.5F) - (this.posY + (double)this.getEyeHeight());
               double d2 = entitylivingbase.posZ - this.posZ;
               double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
               d0 /= d3;
               d1 /= d3;
               d2 /= d3;
               double d4 = this.rand.nextDouble();

               while(d4 < d3) {
                  d4 += 1.8 - d5 + this.rand.nextDouble() * (1.7 - d5);
                  this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + d0 * d4, this.posY + d1 * d4 + (double)this.getEyeHeight(), this.posZ + d2 * d4, 0.0, 0.0, 0.0);
               }
            }
         }
      }

      if (this.inWater) {
         this.setAir(300);
      } else if (this.onGround) {
         this.motionY += 0.5;
         this.motionX += (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.motionZ += (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.rotationYaw = this.rand.nextFloat() * 360.0F;
         this.onGround = false;
         this.isAirBorne = true;
      }

      if (this.hasTargetedEntity()) {
         this.rotationYaw = this.rotationYawHead;
      }

      super.onLivingUpdate();
   }

   protected SoundEvent getFlopSound() {
      return SoundEvents.ENTITY_GUARDIAN_FLOP;
   }

   public float getTailAnimation(float p_175471_1_) {
      return this.clientSideTailAnimationO + (this.clientSideTailAnimation - this.clientSideTailAnimationO) * p_175471_1_;
   }

   public float getSpikesAnimation(float p_175469_1_) {
      return this.clientSideSpikesAnimationO + (this.clientSideSpikesAnimation - this.clientSideSpikesAnimationO) * p_175469_1_;
   }

   public float getAttackAnimationScale(float p_175477_1_) {
      return ((float)this.clientSideAttackTime + p_175477_1_) / (float)this.getAttackDuration();
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return LootTableList.ENTITIES_GUARDIAN;
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public boolean isNotColliding() {
      return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
   }

   public boolean getCanSpawnHere() {
      return (this.rand.nextInt(20) == 0 || !this.world.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (!this.isMoving() && !source.isMagicDamage() && source.getImmediateSource() instanceof EntityLivingBase) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)source.getImmediateSource();
         if (!source.isExplosion()) {
            entitylivingbase.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
         }
      }

      if (this.wander != null) {
         this.wander.makeUpdate();
      }

      return super.attackEntityFrom(source, amount);
   }

   public int getVerticalFaceSpeed() {
      return 180;
   }

   public void travel(float strafe, float vertical, float forward) {
      if (this.isServerWorld() && this.isInWater()) {
         this.moveRelative(strafe, vertical, forward, 0.1F);
         this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.8999999761581421;
         this.motionY *= 0.8999999761581421;
         this.motionZ *= 0.8999999761581421;
         if (!this.isMoving() && this.getAttackTarget() == null) {
            this.motionY -= 0.005;
         }
      } else {
         super.travel(strafe, vertical, forward);
      }

   }

   static {
      MOVING = EntityDataManager.createKey(EntityGuardian.class, DataSerializers.BOOLEAN);
      TARGET_ENTITY = EntityDataManager.createKey(EntityGuardian.class, DataSerializers.VARINT);
   }

   static class GuardianTargetSelector implements Predicate<EntityLivingBase> {
      private final EntityGuardian parentEntity;

      public GuardianTargetSelector(EntityGuardian guardian) {
         this.parentEntity = guardian;
      }

      public boolean apply(@Nullable EntityLivingBase p_apply_1_) {
         return (p_apply_1_ instanceof EntityPlayer || p_apply_1_ instanceof EntitySquid) && p_apply_1_.getDistanceSq(this.parentEntity) > 9.0;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((EntityLivingBase)var1);
      }
   }

   static class GuardianMoveHelper extends EntityMoveHelper {
      private final EntityGuardian entityGuardian;

      public GuardianMoveHelper(EntityGuardian guardian) {
         super(guardian);
         this.entityGuardian = guardian;
      }

      public void onUpdateMoveHelper() {
         if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.entityGuardian.getNavigator().noPath()) {
            double d0 = this.posX - this.entityGuardian.posX;
            double d1 = this.posY - this.entityGuardian.posY;
            double d2 = this.posZ - this.entityGuardian.posZ;
            double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d1 /= d3;
            float f = (float)(MathHelper.atan2(d2, d0) * 57.29577951308232) - 90.0F;
            this.entityGuardian.rotationYaw = this.limitAngle(this.entityGuardian.rotationYaw, f, 90.0F);
            this.entityGuardian.renderYawOffset = this.entityGuardian.rotationYaw;
            float f1 = (float)(this.speed * this.entityGuardian.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
            this.entityGuardian.setAIMoveSpeed(this.entityGuardian.getAIMoveSpeed() + (f1 - this.entityGuardian.getAIMoveSpeed()) * 0.125F);
            double d4 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.5) * 0.05;
            double d5 = Math.cos((double)(this.entityGuardian.rotationYaw * 0.017453292F));
            double d6 = Math.sin((double)(this.entityGuardian.rotationYaw * 0.017453292F));
            EntityGuardian var10000 = this.entityGuardian;
            var10000.motionX += d4 * d5;
            var10000 = this.entityGuardian;
            var10000.motionZ += d4 * d6;
            d4 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.75) * 0.05;
            var10000 = this.entityGuardian;
            var10000.motionY += d4 * (d6 + d5) * 0.25;
            var10000 = this.entityGuardian;
            var10000.motionY += (double)this.entityGuardian.getAIMoveSpeed() * d1 * 0.1;
            EntityLookHelper entitylookhelper = this.entityGuardian.getLookHelper();
            double d7 = this.entityGuardian.posX + d0 / d3 * 2.0;
            double d8 = (double)this.entityGuardian.getEyeHeight() + this.entityGuardian.posY + d1 / d3;
            double d9 = this.entityGuardian.posZ + d2 / d3 * 2.0;
            double d10 = entitylookhelper.getLookPosX();
            double d11 = entitylookhelper.getLookPosY();
            double d12 = entitylookhelper.getLookPosZ();
            if (!entitylookhelper.getIsLooking()) {
               d10 = d7;
               d11 = d8;
               d12 = d9;
            }

            this.entityGuardian.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125, d11 + (d8 - d11) * 0.125, d12 + (d9 - d12) * 0.125, 10.0F, 40.0F);
            this.entityGuardian.setMoving(true);
         } else {
            this.entityGuardian.setAIMoveSpeed(0.0F);
            this.entityGuardian.setMoving(false);
         }

      }
   }

   static class AIGuardianAttack extends EntityAIBase {
      private final EntityGuardian guardian;
      private int tickCounter;
      private final boolean isElder;

      public AIGuardianAttack(EntityGuardian guardian) {
         this.guardian = guardian;
         this.isElder = guardian instanceof EntityElderGuardian;
         this.setMutexBits(3);
      }

      public boolean shouldExecute() {
         EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();
         return entitylivingbase != null && entitylivingbase.isEntityAlive();
      }

      public boolean shouldContinueExecuting() {
         return super.shouldContinueExecuting() && (this.isElder || this.guardian.getDistanceSq(this.guardian.getAttackTarget()) > 9.0);
      }

      public void startExecuting() {
         this.tickCounter = -10;
         this.guardian.getNavigator().clearPath();
         this.guardian.getLookHelper().setLookPositionWithEntity(this.guardian.getAttackTarget(), 90.0F, 90.0F);
         this.guardian.isAirBorne = true;
      }

      public void resetTask() {
         this.guardian.setTargetedEntity(0);
         this.guardian.setAttackTarget((EntityLivingBase)null);
         this.guardian.wander.makeUpdate();
      }

      public void updateTask() {
         EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();
         this.guardian.getNavigator().clearPath();
         this.guardian.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
         if (!this.guardian.canEntityBeSeen(entitylivingbase)) {
            this.guardian.setAttackTarget((EntityLivingBase)null);
         } else {
            ++this.tickCounter;
            if (this.tickCounter == 0) {
               this.guardian.setTargetedEntity(this.guardian.getAttackTarget().getEntityId());
               this.guardian.world.setEntityState(this.guardian, (byte)21);
            } else if (this.tickCounter >= this.guardian.getAttackDuration()) {
               float f = 1.0F;
               if (this.guardian.world.getDifficulty() == EnumDifficulty.HARD) {
                  f += 2.0F;
               }

               if (this.isElder) {
                  f += 2.0F;
               }

               entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.guardian, this.guardian), f);
               entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.guardian), (float)this.guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
               this.guardian.setAttackTarget((EntityLivingBase)null);
            }

            super.updateTask();
         }

      }
   }
}
