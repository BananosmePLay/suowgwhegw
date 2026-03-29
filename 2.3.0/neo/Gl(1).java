package neo;

public class Gl extends Gi {
   JB swellingCreeper;
   Iw creeperAttackTarget;

   public Gl(JB entitycreeperIn) {
      this.swellingCreeper = entitycreeperIn;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.swellingCreeper.getAttackTarget();
      return this.swellingCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingCreeper.getDistanceSq(entitylivingbase) < 9.0;
   }

   public void startExecuting() {
      this.swellingCreeper.getNavigator().clearPath();
      this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
   }

   public void resetTask() {
      this.creeperAttackTarget = null;
   }

   public void updateTask() {
      if (this.creeperAttackTarget == null) {
         this.swellingCreeper.setCreeperState(-1);
      } else if (this.swellingCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0) {
         this.swellingCreeper.setCreeperState(-1);
      } else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
         this.swellingCreeper.setCreeperState(-1);
      } else {
         this.swellingCreeper.setCreeperState(1);
      }

   }
}
