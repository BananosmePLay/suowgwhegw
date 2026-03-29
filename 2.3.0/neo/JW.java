package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class JW extends Il implements Lo {
   private static final Rd<Boolean> ATTACKING;
   private int explosionStrength = 1;

   public JW(bij worldIn) {
      super(worldIn);
      this.setSize(4.0F, 4.0F);
      this.isImmuneToFire = true;
      this.experienceValue = 5;
      this.moveHelper = new JV(this);
   }

   protected void initEntityAI() {
      this.tasks.addTask(5, new JU(this));
      this.tasks.addTask(7, new JT(this));
      this.tasks.addTask(7, new JS(this));
      this.targetTasks.addTask(1, new Gs(this));
   }

   public boolean isAttacking() {
      return (Boolean)this.dataManager.get(ATTACKING);
   }

   public void setAttacking(boolean attacking) {
      this.dataManager.set(ATTACKING, attacking);
   }

   public int getFireballStrength() {
      return this.explosionStrength;
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == baV.PEACEFUL) {
         this.setDead();
      }

   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else if (source.getImmediateSource() instanceof MV && source.getTrueSource() instanceof ME) {
         super.attackEntityFrom(source, 1000.0F);
         return true;
      } else {
         return super.attackEntityFrom(source, amount);
      }
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(ATTACKING, false);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(100.0);
   }

   public SoundCategory getSoundCategory() {
      return SoundCategory.HOSTILE;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_GHAST_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_GHAST_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_GHAST_DEATH;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_GHAST;
   }

   protected float getSoundVolume() {
      return 10.0F;
   }

   public boolean getCanSpawnHere() {
      return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.world.getDifficulty() != baV.PEACEFUL;
   }

   public int getMaxSpawnedInChunk() {
      return 1;
   }

   public static void registerFixesGhast(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JW.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("ExplosionPower", this.explosionStrength);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("ExplosionPower", 99)) {
         this.explosionStrength = compound.getInteger("ExplosionPower");
      }

   }

   public float getEyeHeight() {
      return 2.6F;
   }

   static {
      ATTACKING = Rv.createKey(JW.class, Rt.BOOLEAN);
   }
}
