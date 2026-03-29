package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Kv extends Ly {
   private static final Rd<Boolean> IS_STANDING;
   private float clientSideStandAnimation0;
   private float clientSideStandAnimation;
   private int warningSoundTicks;

   public Kv(bij worldIn) {
      super(worldIn);
      this.setSize(1.3F, 1.4F);
   }

   public Ih createChild(Ih ageable) {
      return new Kv(this.world);
   }

   public boolean isBreedingItem(Qy stack) {
      return false;
   }

   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new Ks(this));
      this.tasks.addTask(1, new Kt(this));
      this.tasks.addTask(4, new Gz(this, 1.25));
      this.tasks.addTask(5, new Hn(this, 1.0));
      this.tasks.addTask(6, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(7, new GH(this));
      this.targetTasks.addTask(1, new Kr(this));
      this.targetTasks.addTask(2, new Kq(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(30.0);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(20.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
      this.getAttributeMap().registerAttribute(Ni.ATTACK_DAMAGE);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(6.0);
   }

   protected SoundEvent getAmbientSound() {
      return this.isChild() ? NO.ENTITY_POLAR_BEAR_BABY_AMBIENT : NO.ENTITY_POLAR_BEAR_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_POLAR_BEAR_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_POLAR_BEAR_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
   }

   protected void playWarningSound() {
      if (this.warningSoundTicks <= 0) {
         this.playSound(NO.ENTITY_POLAR_BEAR_WARNING, 1.0F, 1.0F);
         this.warningSoundTicks = 40;
      }

   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_POLAR_BEAR;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(IS_STANDING, false);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote) {
         this.clientSideStandAnimation0 = this.clientSideStandAnimation;
         if (this.isStanding()) {
            this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
         } else {
            this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
         }
      }

      if (this.warningSoundTicks > 0) {
         --this.warningSoundTicks;
      }

   }

   public boolean attackEntityAsMob(Ig entityIn) {
      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(Ni.ATTACK_DAMAGE).getAttributeValue()));
      if (flag) {
         this.applyEnchantments(this, entityIn);
      }

      return flag;
   }

   public boolean isStanding() {
      return (Boolean)this.dataManager.get(IS_STANDING);
   }

   public void setStanding(boolean standing) {
      this.dataManager.set(IS_STANDING, standing);
   }

   public float getStandingAnimationScale(float p_189795_1_) {
      return (this.clientSideStandAnimation0 + (this.clientSideStandAnimation - this.clientSideStandAnimation0) * p_189795_1_) / 6.0F;
   }

   protected float getWaterSlowDown() {
      return 0.98F;
   }

   public ID onInitialSpawn(baL difficulty, ID livingdata) {
      if (livingdata instanceof Ku) {
         if (((Ku)livingdata).madeParent) {
            this.setGrowingAge(-24000);
         }
      } else {
         Ku entitypolarbear$groupdata = new Ku();
         entitypolarbear$groupdata.madeParent = true;
         livingdata = entitypolarbear$groupdata;
      }

      return (ID)livingdata;
   }

   static {
      IS_STANDING = Rv.createKey(Kv.class, Rt.BOOLEAN);
   }
}
