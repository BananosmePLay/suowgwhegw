package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Kk extends KN {
   public Kk(bij worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
   }

   public static void registerFixesMagmaCube(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Kk.class);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.20000000298023224);
   }

   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != baV.PEACEFUL;
   }

   public boolean isNotColliding() {
      return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
   }

   protected void setSlimeSize(int size, boolean resetHealth) {
      super.setSlimeSize(size, resetHealth);
      this.getEntityAttribute(Ni.ARMOR).setBaseValue((double)(size * 3));
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public float getBrightness() {
      return 1.0F;
   }

   protected EnumParticleTypes getParticleType() {
      return EnumParticleTypes.FLAME;
   }

   protected KN createInstance() {
      return new Kk(this.world);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return this.isSmallSlime() ? bhq.EMPTY : bhq.ENTITIES_MAGMA_CUBE;
   }

   public boolean isBurning() {
      return false;
   }

   protected int getJumpDelay() {
      return super.getJumpDelay() * 4;
   }

   protected void alterSquishAmount() {
      this.squishAmount *= 0.9F;
   }

   protected void jump() {
      this.motionY = (double)(0.42F + (float)this.getSlimeSize() * 0.1F);
      this.isAirBorne = true;
   }

   protected void handleJumpLava() {
      this.motionY = (double)(0.22F + (float)this.getSlimeSize() * 0.05F);
      this.isAirBorne = true;
   }

   public void fall(float distance, float damageMultiplier) {
   }

   protected boolean canDamagePlayer() {
      return true;
   }

   protected int getAttackStrength() {
      return super.getAttackStrength() + 2;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_MAGMACUBE_HURT : NO.ENTITY_MAGMACUBE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_MAGMACUBE_DEATH : NO.ENTITY_MAGMACUBE_DEATH;
   }

   protected SoundEvent getSquishSound() {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_MAGMACUBE_SQUISH : NO.ENTITY_MAGMACUBE_SQUISH;
   }

   protected SoundEvent getJumpSound() {
      return NO.ENTITY_MAGMACUBE_JUMP;
   }
}
