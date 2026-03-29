package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Md extends Lw {
   private final Hd skeletonTrapAI = new Hd(this);
   private boolean skeletonTrap;
   private int skeletonTrapTime;

   public Md(bij worldIn) {
      super(worldIn);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(15.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.20000000298023224);
      this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
   }

   protected SoundEvent getAmbientSound() {
      super.getAmbientSound();
      return NO.ENTITY_SKELETON_HORSE_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      super.getDeathSound();
      return NO.ENTITY_SKELETON_HORSE_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      super.getHurtSound(damageSourceIn);
      return NO.ENTITY_SKELETON_HORSE_HURT;
   }

   public IB getCreatureAttribute() {
      return IB.UNDEAD;
   }

   public double getMountedYOffset() {
      return super.getMountedYOffset() - 0.1875;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_SKELETON_HORSE;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.isTrap() && this.skeletonTrapTime++ >= 18000) {
         this.setDead();
      }

   }

   public static void registerFixesSkeletonHorse(DataFixer fixer) {
      Lw.registerFixesAbstractHorse(fixer, Md.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("SkeletonTrap", this.isTrap());
      compound.setInteger("SkeletonTrapTime", this.skeletonTrapTime);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setTrap(compound.getBoolean("SkeletonTrap"));
      this.skeletonTrapTime = compound.getInteger("SkeletonTrapTime");
   }

   public boolean isTrap() {
      return this.skeletonTrap;
   }

   public void setTrap(boolean trap) {
      if (trap != this.skeletonTrap) {
         this.skeletonTrap = trap;
         if (trap) {
            this.tasks.addTask(1, this.skeletonTrapAI);
         } else {
            this.tasks.removeTask(this.skeletonTrapAI);
         }
      }

   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      boolean flag = !itemstack.isEmpty();
      if (flag && itemstack.getItem() == NK.SPAWN_EGG) {
         return super.processInteract(player, hand);
      } else if (!this.isTame()) {
         return false;
      } else if (this.isChild()) {
         return super.processInteract(player, hand);
      } else if (player.isSneaking()) {
         this.openGUI(player);
         return true;
      } else if (this.isBeingRidden()) {
         return super.processInteract(player, hand);
      } else {
         if (flag) {
            if (itemstack.getItem() == NK.SADDLE && !this.isHorseSaddled()) {
               this.openGUI(player);
               return true;
            }

            if (itemstack.interactWithEntity(player, this, hand)) {
               return true;
            }
         }

         this.mountTo(player);
         return true;
      }
   }
}
