package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Kc extends Kl {
   private static final Rd<Boolean> MOVING;
   private static final Rd<Integer> TARGET_ENTITY;
   protected float clientSideTailAnimation;
   protected float clientSideTailAnimationO;
   protected float clientSideTailAnimationSpeed;
   protected float clientSideSpikesAnimation;
   protected float clientSideSpikesAnimationO;
   private Iw targetedEntity;
   private int clientSideAttackTime;
   private boolean clientSideTouchedGround;
   protected Hn wander;

   public Kc(bij worldIn) {
      super(worldIn);
      this.experienceValue = 10;
      this.setSize(0.85F, 0.85F);
      this.moveHelper = new Ka(this);
      this.clientSideTailAnimation = this.rand.nextFloat();
      this.clientSideTailAnimationO = this.clientSideTailAnimation;
   }

   protected void initEntityAI() {
      GM entityaimovetowardsrestriction = new GM(this, 1.0);
      this.wander = new Hn(this, 1.0, 80);
      this.tasks.addTask(4, new JZ(this));
      this.tasks.addTask(5, entityaimovetowardsrestriction);
      this.tasks.addTask(7, this.wander);
      this.tasks.addTask(8, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(8, new Hq(this, Kc.class, 12.0F, 0.01F));
      this.tasks.addTask(9, new GH(this));
      this.wander.setMutexBits(3);
      entityaimovetowardsrestriction.setMutexBits(3);
      this.targetTasks.addTask(1, new GR(this, Iw.class, 10, true, false, new Kb(this)));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(6.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(16.0);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(30.0);
   }

   public static void registerFixesGuardian(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Kc.class);
   }

   protected VL createNavigator(bij worldIn) {
      return new VP(this, worldIn);
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
   public Iw getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.world.isRemote) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Ig entity = this.world.getEntityByID((Integer)this.dataManager.get(TARGET_ENTITY));
            if (entity instanceof Iw) {
               this.targetedEntity = (Iw)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.getAttackTarget();
      }
   }

   public void notifyDataManagerChange(Rd<?> key) {
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
      return this.isInWater() ? NO.ENTITY_GUARDIAN_AMBIENT : NO.ENTITY_GUARDIAN_AMBIENT_LAND;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isInWater() ? NO.ENTITY_GUARDIAN_HURT : NO.ENTITY_GUARDIAN_HURT_LAND;
   }

   protected SoundEvent getDeathSound() {
      return this.isInWater() ? NO.ENTITY_GUARDIAN_DEATH : NO.ENTITY_GUARDIAN_DEATH_LAND;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public float getEyeHeight() {
      return this.height * 0.5F;
   }

   public float getBlockPathWeight(BlockPos pos) {
      return this.world.getBlockState(pos).getMaterial() == hM.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
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

            Iw entitylivingbase = this.getTargetedEntity();
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
      return NO.ENTITY_GUARDIAN_FLOP;
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
      return bhq.ENTITIES_GUARDIAN;
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
      if (!this.isMoving() && !source.isMagicDamage() && source.getImmediateSource() instanceof Iw) {
         Iw entitylivingbase = (Iw)source.getImmediateSource();
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
         this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
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

   // $FF: synthetic method
   static void access$000(Kc x0, int x1) {
      x0.setTargetedEntity(x1);
   }

   // $FF: synthetic method
   static void access$100(Kc x0, boolean x1) {
      x0.setMoving(x1);
   }

   static {
      MOVING = Rv.createKey(Kc.class, Rt.BOOLEAN);
      TARGET_ENTITY = Rv.createKey(Kc.class, Rt.VARINT);
   }
}
