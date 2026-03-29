package neo;

import com.google.common.base.Predicate;

class LH extends GR<Mu> {
   public LH(LK llama) {
      super(llama, Mu.class, 16, false, true, (Predicate)null);
   }

   public boolean shouldExecute() {
      if (super.shouldExecute() && this.targetEntity != null && !((Mu)this.targetEntity).isTamed()) {
         return true;
      } else {
         this.taskOwner.setAttackTarget((Iw)null);
         return false;
      }
   }

   protected double getTargetDistance() {
      return super.getTargetDistance() * 0.25;
   }
}
