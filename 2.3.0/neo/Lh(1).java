package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class Lh extends Jx {
   public Lh(bij worldIn) {
      super(worldIn);
      this.setSize(0.7F, 2.4F);
      this.isImmuneToFire = true;
   }

   public static void registerFixesWitherSkeleton(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Lh.class);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_WITHER_SKELETON;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_WITHER_SKELETON_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_WITHER_SKELETON_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_WITHER_SKELETON_DEATH;
   }

   SoundEvent getStepSound() {
      return NO.ENTITY_WITHER_SKELETON_STEP;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (cause.getTrueSource() instanceof JB) {
         JB entitycreeper = (JB)cause.getTrueSource();
         if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop()) {
            entitycreeper.incrementDroppedSkulls();
            this.entityDropItem(new Qy(NK.SKULL, 1, 1), 0.0F);
         }
      }

   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.STONE_SWORD));
   }

   protected void setEnchantmentBasedOnDifficulty(baL difficulty) {
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(4.0);
      this.setCombatTask();
      return ientitylivingdata;
   }

   public float getEyeHeight() {
      return 2.1F;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      if (!super.attackEntityAsMob(entityIn)) {
         return false;
      } else {
         if (entityIn instanceof Iw) {
            ((Iw)entityIn).addPotionEffect(new VZ(NL.WITHER, 200));
         }

         return true;
      }
   }

   protected MO getArrow(float p_190726_1_) {
      MO entityarrow = super.getArrow(p_190726_1_);
      entityarrow.setFire(100);
      return entityarrow;
   }
}
