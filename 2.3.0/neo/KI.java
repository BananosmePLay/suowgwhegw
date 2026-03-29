package neo;

class KI extends Gi {
   private final KN slime;
   private int growTieredTimer;

   public KI(KN slimeIn) {
      this.slime = slimeIn;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.slime.getAttackTarget();
      if (entitylivingbase == null) {
         return false;
      } else if (!entitylivingbase.isEntityAlive()) {
         return false;
      } else {
         return !(entitylivingbase instanceof ME) || !((ME)entitylivingbase).capabilities.disableDamage;
      }
   }

   public void startExecuting() {
      this.growTieredTimer = 300;
      super.startExecuting();
   }

   public boolean shouldContinueExecuting() {
      Iw entitylivingbase = this.slime.getAttackTarget();
      if (entitylivingbase == null) {
         return false;
      } else if (!entitylivingbase.isEntityAlive()) {
         return false;
      } else if (entitylivingbase instanceof ME && ((ME)entitylivingbase).capabilities.disableDamage) {
         return false;
      } else {
         return --this.growTieredTimer > 0;
      }
   }

   public void updateTask() {
      this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
      ((KM)this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, this.slime.canDamagePlayer());
   }
}
