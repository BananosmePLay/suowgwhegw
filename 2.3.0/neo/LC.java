package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;

public class LC extends Lu {
   public LC(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesDonkey(DataFixer fixer) {
      Lu.registerFixesAbstractChestHorse(fixer, LC.class);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_DONKEY;
   }

   protected SoundEvent getAmbientSound() {
      super.getAmbientSound();
      return NO.ENTITY_DONKEY_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      super.getDeathSound();
      return NO.ENTITY_DONKEY_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      super.getHurtSound(damageSourceIn);
      return NO.ENTITY_DONKEY_HURT;
   }

   public boolean canMateWith(Ly otherAnimal) {
      if (otherAnimal == this) {
         return false;
      } else if (!(otherAnimal instanceof LC) && !(otherAnimal instanceof LF)) {
         return false;
      } else {
         return this.canMate() && ((Lw)otherAnimal).canMate();
      }
   }

   public Ih createChild(Ih ageable) {
      Lw abstracthorse = ageable instanceof LF ? new LM(this.world) : new LC(this.world);
      this.setOffspringAttributes(ageable, (Lw)abstracthorse);
      return (Ih)abstracthorse;
   }
}
