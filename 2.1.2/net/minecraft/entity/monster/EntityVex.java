package net.minecraft.entity.monster;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityVex extends EntityMob {
   protected static final DataParameter<Byte> VEX_FLAGS;
   private EntityLiving owner;
   @Nullable
   private BlockPos boundOrigin;
   private boolean limitedLifespan;
   private int limitedLifeTicks;

   public EntityVex(World worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
      this.moveHelper = new AIMoveControl(this);
      this.setSize(0.4F, 0.8F);
      this.experienceValue = 3;
   }

   public void move(MoverType type, double x, double y, double z) {
      super.move(type, x, y, z);
      this.doBlockCollisions();
   }

   public void onUpdate() {
      this.noClip = true;
      super.onUpdate();
      this.noClip = false;
      this.setNoGravity(true);
      if (this.limitedLifespan && --this.limitedLifeTicks <= 0) {
         this.limitedLifeTicks = 20;
         this.attackEntityFrom(DamageSource.STARVE, 1.0F);
      }

   }

   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(4, new AIChargeAttack());
      this.tasks.addTask(8, new AIMoveRandom());
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
      this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityVex.class}));
      this.targetTasks.addTask(2, new AICopyOwnerTarget(this));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(VEX_FLAGS, (byte)0);
   }

   public static void registerFixesVex(DataFixer fixer) {
      EntityLiving.registerFixesMob(fixer, EntityVex.class);
   }

   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("BoundX")) {
         this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"), compound.getInteger("BoundZ"));
      }

      if (compound.hasKey("LifeTicks")) {
         this.setLimitedLife(compound.getInteger("LifeTicks"));
      }

   }

   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      if (this.boundOrigin != null) {
         compound.setInteger("BoundX", this.boundOrigin.getX());
         compound.setInteger("BoundY", this.boundOrigin.getY());
         compound.setInteger("BoundZ", this.boundOrigin.getZ());
      }

      if (this.limitedLifespan) {
         compound.setInteger("LifeTicks", this.limitedLifeTicks);
      }

   }

   public EntityLiving getOwner() {
      return this.owner;
   }

   @Nullable
   public BlockPos getBoundOrigin() {
      return this.boundOrigin;
   }

   public void setBoundOrigin(@Nullable BlockPos boundOriginIn) {
      this.boundOrigin = boundOriginIn;
   }

   private boolean getVexFlag(int mask) {
      int i = (Byte)this.dataManager.get(VEX_FLAGS);
      return (i & mask) != 0;
   }

   private void setVexFlag(int mask, boolean value) {
      int i = (Byte)this.dataManager.get(VEX_FLAGS);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.dataManager.set(VEX_FLAGS, (byte)(i & 255));
   }

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setCharging(boolean charging) {
      this.setVexFlag(1, charging);
   }

   public void setOwner(EntityLiving ownerIn) {
      this.owner = ownerIn;
   }

   public void setLimitedLife(int limitedLifeTicksIn) {
      this.limitedLifespan = true;
      this.limitedLifeTicks = limitedLifeTicksIn;
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_VEX_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_VEX_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return SoundEvents.ENTITY_VEX_HURT;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return LootTableList.ENTITIES_VEX;
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public float getBrightness() {
      return 1.0F;
   }

   @Nullable
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      return super.onInitialSpawn(difficulty, livingdata);
   }

   protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
      this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
   }

   static {
      VEX_FLAGS = EntityDataManager.createKey(EntityVex.class, DataSerializers.BYTE);
   }

   class AIMoveRandom extends EntityAIBase {
      public AIMoveRandom() {
         this.setMutexBits(1);
      }

      public boolean shouldExecute() {
         return !EntityVex.this.getMoveHelper().isUpdating() && EntityVex.this.rand.nextInt(7) == 0;
      }

      public boolean shouldContinueExecuting() {
         return false;
      }

      public void updateTask() {
         BlockPos blockpos = EntityVex.this.getBoundOrigin();
         if (blockpos == null) {
            blockpos = new BlockPos(EntityVex.this);
         }

         for(int i = 0; i < 3; ++i) {
            BlockPos blockpos1 = blockpos.add(EntityVex.this.rand.nextInt(15) - 7, EntityVex.this.rand.nextInt(11) - 5, EntityVex.this.rand.nextInt(15) - 7);
            if (EntityVex.this.world.isAirBlock(blockpos1)) {
               EntityVex.this.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 0.25);
               if (EntityVex.this.getAttackTarget() == null) {
                  EntityVex.this.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 180.0F, 20.0F);
               }
               break;
            }
         }

      }
   }

   class AIMoveControl extends EntityMoveHelper {
      public AIMoveControl(EntityVex vex) {
         super(vex);
      }

      public void onUpdateMoveHelper() {
         if (this.action == EntityMoveHelper.Action.MOVE_TO) {
            double d0 = this.posX - EntityVex.this.posX;
            double d1 = this.posY - EntityVex.this.posY;
            double d2 = this.posZ - EntityVex.this.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = (double)MathHelper.sqrt(d3);
            EntityVex var10000;
            if (d3 < EntityVex.this.getEntityBoundingBox().getAverageEdgeLength()) {
               this.action = EntityMoveHelper.Action.WAIT;
               var10000 = EntityVex.this;
               var10000.motionX *= 0.5;
               var10000 = EntityVex.this;
               var10000.motionY *= 0.5;
               var10000 = EntityVex.this;
               var10000.motionZ *= 0.5;
            } else {
               var10000 = EntityVex.this;
               var10000.motionX += d0 / d3 * 0.05 * this.speed;
               var10000 = EntityVex.this;
               var10000.motionY += d1 / d3 * 0.05 * this.speed;
               var10000 = EntityVex.this;
               var10000.motionZ += d2 / d3 * 0.05 * this.speed;
               if (EntityVex.this.getAttackTarget() == null) {
                  EntityVex.this.rotationYaw = -((float)MathHelper.atan2(EntityVex.this.motionX, EntityVex.this.motionZ)) * 57.295776F;
                  EntityVex.this.renderYawOffset = EntityVex.this.rotationYaw;
               } else {
                  double d4 = EntityVex.this.getAttackTarget().posX - EntityVex.this.posX;
                  double d5 = EntityVex.this.getAttackTarget().posZ - EntityVex.this.posZ;
                  EntityVex.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * 57.295776F;
                  EntityVex.this.renderYawOffset = EntityVex.this.rotationYaw;
               }
            }
         }

      }
   }

   class AICopyOwnerTarget extends EntityAITarget {
      public AICopyOwnerTarget(EntityCreature creature) {
         super(creature, false);
      }

      public boolean shouldExecute() {
         return EntityVex.this.owner != null && EntityVex.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityVex.this.owner.getAttackTarget(), false);
      }

      public void startExecuting() {
         EntityVex.this.setAttackTarget(EntityVex.this.owner.getAttackTarget());
         super.startExecuting();
      }
   }

   class AIChargeAttack extends EntityAIBase {
      public AIChargeAttack() {
         this.setMutexBits(1);
      }

      public boolean shouldExecute() {
         if (EntityVex.this.getAttackTarget() != null && !EntityVex.this.getMoveHelper().isUpdating() && EntityVex.this.rand.nextInt(7) == 0) {
            return EntityVex.this.getDistanceSq(EntityVex.this.getAttackTarget()) > 4.0;
         } else {
            return false;
         }
      }

      public boolean shouldContinueExecuting() {
         return EntityVex.this.getMoveHelper().isUpdating() && EntityVex.this.isCharging() && EntityVex.this.getAttackTarget() != null && EntityVex.this.getAttackTarget().isEntityAlive();
      }

      public void startExecuting() {
         EntityLivingBase entitylivingbase = EntityVex.this.getAttackTarget();
         Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
         EntityVex.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
         EntityVex.this.setCharging(true);
         EntityVex.this.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
      }

      public void resetTask() {
         EntityVex.this.setCharging(false);
      }

      public void updateTask() {
         EntityLivingBase entitylivingbase = EntityVex.this.getAttackTarget();
         if (EntityVex.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox())) {
            EntityVex.this.attackEntityAsMob(entitylivingbase);
            EntityVex.this.setCharging(false);
         } else {
            double d0 = EntityVex.this.getDistanceSq(entitylivingbase);
            if (d0 < 9.0) {
               Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
               EntityVex.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
            }
         }

      }
   }
}
