package neo;

import net.minecraft.util.DamageSource;

class JZ extends Gi {
   private final Kc guardian;
   private int tickCounter;
   private final boolean isElder;

   public JZ(Kc guardian) {
      this.guardian = guardian;
      this.isElder = guardian instanceof JD;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.guardian.getAttackTarget();
      return entitylivingbase != null && entitylivingbase.isEntityAlive();
   }

   public boolean shouldContinueExecuting() {
      return super.shouldContinueExecuting() && (this.isElder || this.guardian.getDistanceSq(this.guardian.getAttackTarget()) > 9.0);
   }

   public void startExecuting() {
      this.tickCounter = -10;
      this.guardian.getNavigator().clearPath();
      this.guardian.getLookHelper().setLookPositionWithEntity(this.guardian.getAttackTarget(), 90.0F, 90.0F);
      this.guardian.isAirBorne = true;
   }

   public void resetTask() {
      Kc.access$000(this.guardian, 0);
      this.guardian.setAttackTarget((Iw)null);
      this.guardian.wander.makeUpdate();
   }

   public void updateTask() {
      Iw entitylivingbase = this.guardian.getAttackTarget();
      this.guardian.getNavigator().clearPath();
      this.guardian.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
      if (!this.guardian.canEntityBeSeen(entitylivingbase)) {
         this.guardian.setAttackTarget((Iw)null);
      } else {
         ++this.tickCounter;
         if (this.tickCounter == 0) {
            Kc.access$000(this.guardian, this.guardian.getAttackTarget().getEntityId());
            this.guardian.world.setEntityState(this.guardian, (byte)21);
         } else if (this.tickCounter >= this.guardian.getAttackDuration()) {
            float f = 1.0F;
            if (this.guardian.world.getDifficulty() == baV.HARD) {
               f += 2.0F;
            }

            if (this.isElder) {
               f += 2.0F;
            }

            entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.guardian, this.guardian), f);
            entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.guardian), (float)this.guardian.getEntityAttribute(Ni.ATTACK_DAMAGE).getAttributeValue());
            this.guardian.setAttackTarget((Iw)null);
         }

         super.updateTask();
      }

   }
}
