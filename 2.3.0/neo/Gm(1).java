package neo;

public class Gm extends Hf {
   Kj irongolem;
   Iw villageAgressorTarget;

   public Gm(Kj ironGolemIn) {
      super(ironGolemIn, false, true);
      this.irongolem = ironGolemIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      Za village = this.irongolem.getVillage();
      if (village == null) {
         return false;
      } else {
         this.villageAgressorTarget = village.findNearestVillageAggressor(this.irongolem);
         if (this.villageAgressorTarget instanceof JB) {
            return false;
         } else if (this.isSuitableTarget(this.villageAgressorTarget, false)) {
            return true;
         } else if (this.taskOwner.getRNG().nextInt(20) == 0) {
            this.villageAgressorTarget = village.getNearestTargetPlayer(this.irongolem);
            return this.isSuitableTarget(this.villageAgressorTarget, false);
         } else {
            return false;
         }
      }
   }

   public void startExecuting() {
      this.irongolem.setAttackTarget(this.villageAgressorTarget);
      super.startExecuting();
   }
}
