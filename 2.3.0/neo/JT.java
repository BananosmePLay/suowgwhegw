package neo;

import net.minecraft.util.math.MathHelper;

class JT extends Gi {
   private final JW parentEntity;

   public JT(JW ghast) {
      this.parentEntity = ghast;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      return true;
   }

   public void updateTask() {
      if (this.parentEntity.getAttackTarget() == null) {
         this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * 57.295776F;
         this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
      } else {
         Iw entitylivingbase = this.parentEntity.getAttackTarget();
         double d0 = 64.0;
         if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0) {
            double d1 = entitylivingbase.posX - this.parentEntity.posX;
            double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
            this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * 57.295776F;
            this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
         }
      }

   }
}
