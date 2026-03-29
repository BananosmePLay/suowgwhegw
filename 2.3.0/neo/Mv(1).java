package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Mv extends Lw {
   public Mv(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesZombieHorse(DataFixer fixer) {
      Lw.registerFixesAbstractHorse(fixer, Mv.class);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(15.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.20000000298023224);
      this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
   }

   public IB getCreatureAttribute() {
      return IB.UNDEAD;
   }

   protected SoundEvent getAmbientSound() {
      super.getAmbientSound();
      return NO.ENTITY_ZOMBIE_HORSE_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      super.getDeathSound();
      return NO.ENTITY_ZOMBIE_HORSE_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      super.getHurtSound(damageSourceIn);
      return NO.ENTITY_ZOMBIE_HORSE_HURT;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ZOMBIE_HORSE;
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
            if (!this.isHorseSaddled() && itemstack.getItem() == NK.SADDLE) {
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
