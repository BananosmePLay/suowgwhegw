package neo;

import javax.annotation.Nullable;
import net.minecraft.util.SoundEvent;

class Kg extends KQ {
   // $FF: synthetic field
   final Kh this$0;

   private Kg(Kh this$0) {
      super(this$0);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else {
         return !this.this$0.isPotionActive(NL.INVISIBILITY);
      }
   }

   protected int getCastingTime() {
      return 20;
   }

   protected int getCastingInterval() {
      return 340;
   }

   protected void castSpell() {
      this.this$0.addPotionEffect(new VZ(NL.INVISIBILITY, 1200));
   }

   @Nullable
   protected SoundEvent getSpellPrepareSound() {
      return NO.ENTITY_ILLAGER_PREPARE_MIRROR;
   }

   protected KR getSpellType() {
      return KR.DISAPPEAR;
   }

   // $FF: synthetic method
   Kg(Kh x0, Object x1) {
      this(x0);
   }
}
