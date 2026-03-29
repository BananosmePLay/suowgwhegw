package neo;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

class Kf extends KQ {
   private int lastTargetId;
   // $FF: synthetic field
   final Kh this$0;

   private Kf(Kh this$0) {
      super(this$0);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else if (this.this$0.getAttackTarget() == null) {
         return false;
      } else {
         return this.this$0.getAttackTarget().getEntityId() == this.lastTargetId ? false : this.this$0.world.getDifficultyForLocation(new BlockPos(this.this$0)).isHarderThan((float)baV.NORMAL.ordinal());
      }
   }

   public void startExecuting() {
      super.startExecuting();
      this.lastTargetId = this.this$0.getAttackTarget().getEntityId();
   }

   protected int getCastingTime() {
      return 20;
   }

   protected int getCastingInterval() {
      return 180;
   }

   protected void castSpell() {
      this.this$0.getAttackTarget().addPotionEffect(new VZ(NL.BLINDNESS, 400));
   }

   protected SoundEvent getSpellPrepareSound() {
      return NO.ENTITY_ILLAGER_PREPARE_BLINDNESS;
   }

   protected KR getSpellType() {
      return KR.BLINDNESS;
   }

   // $FF: synthetic method
   Kf(Kh x0, Object x1) {
      this(x0);
   }
}
