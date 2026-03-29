package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class Kl extends Ik implements Lo {
   public Kl(bij worldIn) {
      super(worldIn);
      this.experienceValue = 5;
   }

   public SoundCategory getSoundCategory() {
      return SoundCategory.HOSTILE;
   }

   public void onLivingUpdate() {
      this.updateArmSwingProgress();
      float f = this.getBrightness();
      if (f > 0.5F) {
         this.idleTime += 2;
      }

      super.onLivingUpdate();
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == baV.PEACEFUL) {
         this.setDead();
      }

   }

   protected SoundEvent getSwimSound() {
      return NO.ENTITY_HOSTILE_SWIM;
   }

   protected SoundEvent getSplashSound() {
      return NO.ENTITY_HOSTILE_SPLASH;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_HOSTILE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_HOSTILE_DEATH;
   }

   protected SoundEvent getFallSound(int heightIn) {
      return heightIn > 4 ? NO.ENTITY_HOSTILE_BIG_FALL : NO.ENTITY_HOSTILE_SMALL_FALL;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      float f = (float)this.getEntityAttribute(Ni.ATTACK_DAMAGE).getAttributeValue();
      int i = 0;
      if (entityIn instanceof Iw) {
         f += Ft.getModifierForCreature(this.getHeldItemMainhand(), ((Iw)entityIn).getCreatureAttribute());
         i += Ft.getKnockbackModifier(this);
      }

      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
      if (flag) {
         if (i > 0 && entityIn instanceof Iw) {
            ((Iw)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int j = Ft.getFireAspectModifier(this);
         if (j > 0) {
            entityIn.setFire(j * 4);
         }

         if (entityIn instanceof ME) {
            ME entityplayer = (ME)entityIn;
            Qy itemstack = this.getHeldItemMainhand();
            Qy itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : Qy.EMPTY;
            if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof OU && itemstack1.getItem() == NK.SHIELD) {
               float f1 = 0.25F + (float)Ft.getEfficiencyModifier(this) * 0.05F;
               if (this.rand.nextFloat() < f1) {
                  entityplayer.getCooldownTracker().setCooldown(NK.SHIELD, 100);
                  this.world.setEntityState(entityplayer, (byte)30);
               }
            }
         }

         this.applyEnchantments(this, entityIn);
      }

      return flag;
   }

   public float getBlockPathWeight(BlockPos pos) {
      return 0.5F - this.world.getLightBrightness(pos);
   }

   protected boolean isValidLightLevel() {
      BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
      if (this.world.getLightFor(baW.SKY, blockpos) > this.rand.nextInt(32)) {
         return false;
      } else {
         int i = this.world.getLightFromNeighbors(blockpos);
         if (this.world.isThundering()) {
            int j = this.world.getSkylightSubtracted();
            this.world.setSkylightSubtracted(10);
            i = this.world.getLightFromNeighbors(blockpos);
            this.world.setSkylightSubtracted(j);
         }

         return i <= this.rand.nextInt(8);
      }
   }

   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != baV.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(Ni.ATTACK_DAMAGE);
   }

   protected boolean canDropLoot() {
      return true;
   }

   public boolean isPreventingPlayerRest(ME playerIn) {
      return true;
   }
}
