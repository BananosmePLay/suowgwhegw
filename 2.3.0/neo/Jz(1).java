package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Jz extends Kl {
   private float heightOffset = 0.5F;
   private int heightOffsetUpdateTime;
   private static final Rd<Byte> ON_FIRE;

   public Jz(bij worldIn) {
      super(worldIn);
      this.setPathPriority(VQ.WATER, -1.0F);
      this.setPathPriority(VQ.LAVA, 8.0F);
      this.setPathPriority(VQ.DANGER_FIRE, 0.0F);
      this.setPathPriority(VQ.DAMAGE_FIRE, 0.0F);
      this.isImmuneToFire = true;
      this.experienceValue = 10;
   }

   public static void registerFixesBlaze(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Jz.class);
   }

   protected void initEntityAI() {
      this.tasks.addTask(4, new Jy(this));
      this.tasks.addTask(5, new GM(this, 1.0));
      this.tasks.addTask(7, new Ho(this, 1.0, 0.0F));
      this.tasks.addTask(8, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(8, new GH(this));
      this.targetTasks.addTask(1, new GB(this, true, new Class[0]));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(6.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.23000000417232513);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(48.0);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(ON_FIRE, (byte)0);
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_BLAZE_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_BLAZE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_BLAZE_DEATH;
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public float getBrightness() {
      return 1.0F;
   }

   public void onLivingUpdate() {
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      if (this.world.isRemote) {
         if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
            this.world.playSound(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, NO.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
         }

         for(int i = 0; i < 2; ++i) {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
         }
      }

      super.onLivingUpdate();
   }

   protected void updateAITasks() {
      if (this.isWet()) {
         this.attackEntityFrom(DamageSource.DROWN, 1.0F);
      }

      --this.heightOffsetUpdateTime;
      if (this.heightOffsetUpdateTime <= 0) {
         this.heightOffsetUpdateTime = 100;
         this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
      }

      Iw entitylivingbase = this.getAttackTarget();
      if (entitylivingbase != null && entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
         this.motionY += (0.30000001192092896 - this.motionY) * 0.30000001192092896;
         this.isAirBorne = true;
      }

      super.updateAITasks();
   }

   public void fall(float distance, float damageMultiplier) {
   }

   public boolean isBurning() {
      return this.isCharged();
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_BLAZE;
   }

   public boolean isCharged() {
      return ((Byte)this.dataManager.get(ON_FIRE) & 1) != 0;
   }

   public void setOnFire(boolean onFire) {
      byte b0 = (Byte)this.dataManager.get(ON_FIRE);
      if (onFire) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 &= -2;
      }

      this.dataManager.set(ON_FIRE, b0);
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   static {
      ON_FIRE = Rv.createKey(Jz.class, Rt.BYTE);
   }
}
