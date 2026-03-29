package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class KH extends Jx {
   public KH(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesSkeleton(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KH.class);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_SKELETON;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_SKELETON_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_SKELETON_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_SKELETON_DEATH;
   }

   SoundEvent getStepSound() {
      return NO.ENTITY_SKELETON_STEP;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (cause.getTrueSource() instanceof JB) {
         JB entitycreeper = (JB)cause.getTrueSource();
         if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop()) {
            entitycreeper.incrementDroppedSkulls();
            this.entityDropItem(new Qy(NK.SKULL, 1, 0), 0.0F);
         }
      }

   }

   protected MO getArrow(float p_190726_1_) {
      Qy itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
      if (itemstack.getItem() == NK.SPECTRAL_ARROW) {
         Nc entityspectralarrow = new Nc(this.world, this);
         entityspectralarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
         return entityspectralarrow;
      } else {
         MO entityarrow = super.getArrow(p_190726_1_);
         if (itemstack.getItem() == NK.TIPPED_ARROW && entityarrow instanceof Ne) {
            ((Ne)entityarrow).setPotionEffect(itemstack);
         }

         return entityarrow;
      }
   }
}
