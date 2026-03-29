package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;

public class LY extends Ly {
   private static final Rd<Integer> RABBIT_TYPE;
   private int jumpTicks;
   private int jumpDuration;
   private boolean wasOnGround;
   private int currentMoveTypeDuration;
   private int carrotTicks;

   public LY(bij worldIn) {
      super(worldIn);
      this.setSize(0.4F, 0.5F);
      this.jumpHelper = new LV(this, this);
      this.moveHelper = new LW(this);
      this.setMovementSpeed(0.0);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(1, new LT(this, 2.2));
      this.tasks.addTask(2, new GI(this, 0.8));
      this.tasks.addTask(3, new Hj(this, 1.0, NK.CARROT, false));
      this.tasks.addTask(3, new Hj(this, 1.0, NK.GOLDEN_CARROT, false));
      this.tasks.addTask(3, new Hj(this, 1.0, OL.getItemFromBlock(Nk.YELLOW_FLOWER), false));
      this.tasks.addTask(4, new LR(this, ME.class, 8.0F, 2.2, 2.2));
      this.tasks.addTask(4, new LR(this, Mu.class, 10.0F, 2.2, 2.2));
      this.tasks.addTask(4, new LR(this, Kl.class, 4.0F, 2.2, 2.2));
      this.tasks.addTask(5, new LU(this));
      this.tasks.addTask(6, new Ho(this, 0.6));
      this.tasks.addTask(11, new Hq(this, ME.class, 10.0F));
   }

   protected float getJumpUpwardsMotion() {
      if (this.collidedHorizontally || this.moveHelper.isUpdating() && !(this.moveHelper.getY() <= this.posY + 0.5)) {
         return 0.5F;
      } else {
         VI path = this.navigator.getPath();
         if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
            Vec3d vec3d = path.getPosition(this);
            if (vec3d.y > this.posY + 0.5) {
               return 0.5F;
            }
         }

         return this.moveHelper.getSpeed() <= 0.6 ? 0.2F : 0.3F;
      }
   }

   protected void jump() {
      super.jump();
      double d0 = this.moveHelper.getSpeed();
      if (d0 > 0.0) {
         double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;
         if (d1 < 0.010000000000000002) {
            this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
         }
      }

      if (!this.world.isRemote) {
         this.world.setEntityState(this, (byte)1);
      }

   }

   public float getJumpCompletion(float p_175521_1_) {
      return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
   }

   public void setMovementSpeed(double newSpeed) {
      this.getNavigator().setSpeed(newSpeed);
      this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
   }

   public void setJumping(boolean jumping) {
      super.setJumping(jumping);
      if (jumping) {
         this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
      }

   }

   public void startJumping() {
      this.setJumping(true);
      this.jumpDuration = 10;
      this.jumpTicks = 0;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(RABBIT_TYPE, 0);
   }

   public void updateAITasks() {
      if (this.currentMoveTypeDuration > 0) {
         --this.currentMoveTypeDuration;
      }

      if (this.carrotTicks > 0) {
         this.carrotTicks -= this.rand.nextInt(3);
         if (this.carrotTicks < 0) {
            this.carrotTicks = 0;
         }
      }

      if (this.onGround) {
         if (!this.wasOnGround) {
            this.setJumping(false);
            this.checkLandingDelay();
         }

         if (this.getRabbitType() == 99 && this.currentMoveTypeDuration == 0) {
            Iw entitylivingbase = this.getAttackTarget();
            if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0) {
               this.calculateRotationYaw(entitylivingbase.posX, entitylivingbase.posZ);
               this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, this.moveHelper.getSpeed());
               this.startJumping();
               this.wasOnGround = true;
            }
         }

         LV entityrabbit$rabbitjumphelper = (LV)this.jumpHelper;
         if (!entityrabbit$rabbitjumphelper.getIsJumping()) {
            if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
               VI path = this.navigator.getPath();
               Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());
               if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                  vec3d = path.getPosition(this);
               }

               this.calculateRotationYaw(vec3d.x, vec3d.z);
               this.startJumping();
            }
         } else if (!entityrabbit$rabbitjumphelper.canJump()) {
            this.enableJumpControl();
         }
      }

      this.wasOnGround = this.onGround;
   }

   public void spawnRunningParticles() {
   }

   private void calculateRotationYaw(double x, double z) {
      this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * 57.29577951308232) - 90.0F;
   }

   private void enableJumpControl() {
      ((LV)this.jumpHelper).setCanJump(true);
   }

   private void disableJumpControl() {
      ((LV)this.jumpHelper).setCanJump(false);
   }

   private void updateMoveTypeDuration() {
      if (this.moveHelper.getSpeed() < 2.2) {
         this.currentMoveTypeDuration = 10;
      } else {
         this.currentMoveTypeDuration = 1;
      }

   }

   private void checkLandingDelay() {
      this.updateMoveTypeDuration();
      this.disableJumpControl();
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.jumpTicks != this.jumpDuration) {
         ++this.jumpTicks;
      } else if (this.jumpDuration != 0) {
         this.jumpTicks = 0;
         this.jumpDuration = 0;
         this.setJumping(false);
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(3.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
   }

   public static void registerFixesRabbit(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LY.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("RabbitType", this.getRabbitType());
      compound.setInteger("MoreCarrotTicks", this.carrotTicks);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setRabbitType(compound.getInteger("RabbitType"));
      this.carrotTicks = compound.getInteger("MoreCarrotTicks");
   }

   protected SoundEvent getJumpSound() {
      return NO.ENTITY_RABBIT_JUMP;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_RABBIT_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_RABBIT_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_RABBIT_DEATH;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      if (this.getRabbitType() == 99) {
         this.playSound(NO.ENTITY_RABBIT_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
      } else {
         return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
      }
   }

   public SoundCategory getSoundCategory() {
      return this.getRabbitType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_RABBIT;
   }

   private boolean isRabbitBreedingItem(OL itemIn) {
      return itemIn == NK.CARROT || itemIn == NK.GOLDEN_CARROT || itemIn == OL.getItemFromBlock(Nk.YELLOW_FLOWER);
   }

   public LY createChild(Ih ageable) {
      LY entityrabbit = new LY(this.world);
      int i = this.getRandomRabbitType();
      if (this.rand.nextInt(20) != 0) {
         if (ageable instanceof LY && this.rand.nextBoolean()) {
            i = ((LY)ageable).getRabbitType();
         } else {
            i = this.getRabbitType();
         }
      }

      entityrabbit.setRabbitType(i);
      return entityrabbit;
   }

   public boolean isBreedingItem(Qy stack) {
      return this.isRabbitBreedingItem(stack.getItem());
   }

   public int getRabbitType() {
      return (Integer)this.dataManager.get(RABBIT_TYPE);
   }

   public void setRabbitType(int rabbitTypeId) {
      if (rabbitTypeId == 99) {
         this.getEntityAttribute(Ni.ARMOR).setBaseValue(8.0);
         this.tasks.addTask(4, new LS(this));
         this.targetTasks.addTask(1, new GB(this, false, new Class[0]));
         this.targetTasks.addTask(2, new GR(this, ME.class, true));
         this.targetTasks.addTask(2, new GR(this, Mu.class, true));
         if (!this.hasCustomName()) {
            this.setCustomNameTag(I18n.translateToLocal("entity.KillerBunny.name"));
         }
      }

      this.dataManager.set(RABBIT_TYPE, rabbitTypeId);
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID livingdata = super.onInitialSpawn(difficulty, livingdata);
      int i = this.getRandomRabbitType();
      boolean flag = false;
      if (livingdata instanceof LX) {
         i = ((LX)livingdata).typeData;
         flag = true;
      } else {
         livingdata = new LX(i);
      }

      this.setRabbitType(i);
      if (flag) {
         this.setGrowingAge(-24000);
      }

      return (ID)livingdata;
   }

   private int getRandomRabbitType() {
      Zi biome = this.world.getBiome(new BlockPos(this));
      int i = this.rand.nextInt(100);
      if (biome.isSnowyBiome()) {
         return i < 80 ? 1 : 3;
      } else if (biome instanceof Zs) {
         return 4;
      } else {
         return i < 50 ? 0 : (i < 90 ? 5 : 2);
      }
   }

   private boolean isCarrotEaten() {
      return this.carrotTicks == 0;
   }

   protected void createEatingParticles() {
      cQ blockcarrot = (cQ)Nk.CARROTS;
      in iblockstate = blockcarrot.withAge(blockcarrot.getMaxAge());
      this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0, 0.0, 0.0, co.getStateId(iblockstate));
      this.carrotTicks = 40;
   }

   public void handleStatusUpdate(byte id) {
      if (id == 1) {
         this.createRunningParticles();
         this.jumpDuration = 10;
         this.jumpTicks = 0;
      } else {
         super.handleStatusUpdate(id);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   // $FF: synthetic method
   static boolean access$000(LY x0) {
      return x0.isCarrotEaten();
   }

   // $FF: synthetic method
   static boolean access$100(LY x0) {
      return x0.isJumping;
   }

   // $FF: synthetic method
   static Hu access$200(LY x0) {
      return x0.jumpHelper;
   }

   static {
      RABBIT_TYPE = Rv.createKey(LY.class, Rt.VARINT);
   }
}
