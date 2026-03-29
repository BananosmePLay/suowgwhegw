package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class LM extends Lu {
   public LM(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesMule(DataFixer fixer) {
      Lu.registerFixesAbstractChestHorse(fixer, LM.class);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_MULE;
   }

   protected SoundEvent getAmbientSound() {
      super.getAmbientSound();
      return NO.ENTITY_MULE_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      super.getDeathSound();
      return NO.ENTITY_MULE_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      super.getHurtSound(damageSourceIn);
      return NO.ENTITY_MULE_HURT;
   }

   protected void playChestEquipSound() {
      this.playSound(NO.ENTITY_MULE_CHEST, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
   }
}
