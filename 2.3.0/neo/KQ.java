package neo;

import javax.annotation.Nullable;
import net.minecraft.util.SoundEvent;

public abstract class KQ extends Gi {
   protected int spellWarmup;
   protected int spellCooldown;
   // $FF: synthetic field
   final KS this$0;

   public KQ(KS this$0) {
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      if (this.this$0.getAttackTarget() == null) {
         return false;
      } else if (this.this$0.isSpellcasting()) {
         return false;
      } else {
         return this.this$0.ticksExisted >= this.spellCooldown;
      }
   }

   public boolean shouldContinueExecuting() {
      return this.this$0.getAttackTarget() != null && this.spellWarmup > 0;
   }

   public void startExecuting() {
      this.spellWarmup = this.getCastWarmupTime();
      this.this$0.spellTicks = this.getCastingTime();
      this.spellCooldown = this.this$0.ticksExisted + this.getCastingInterval();
      SoundEvent soundevent = this.getSpellPrepareSound();
      if (soundevent != null) {
         this.this$0.playSound(soundevent, 1.0F, 1.0F);
      }

      this.this$0.setSpellType(this.getSpellType());
   }

   public void updateTask() {
      --this.spellWarmup;
      if (this.spellWarmup == 0) {
         this.castSpell();
         this.this$0.playSound(this.this$0.getSpellSound(), 1.0F, 1.0F);
      }

   }

   protected abstract void castSpell();

   protected int getCastWarmupTime() {
      return 20;
   }

   protected abstract int getCastingTime();

   protected abstract int getCastingInterval();

   @Nullable
   protected abstract SoundEvent getSpellPrepareSound();

   protected abstract KR getSpellType();
}
