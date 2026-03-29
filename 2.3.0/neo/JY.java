package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;

public abstract class JY extends Ik implements Mx {
   public JY(bij worldIn) {
      super(worldIn);
   }

   public void fall(float distance, float damageMultiplier) {
   }

   @Nullable
   protected SoundEvent getAmbientSound() {
      return null;
   }

   @Nullable
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return null;
   }

   @Nullable
   protected SoundEvent getDeathSound() {
      return null;
   }

   public int getTalkInterval() {
      return 120;
   }

   protected boolean canDespawn() {
      return false;
   }
}
