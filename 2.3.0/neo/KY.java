package neo;

import net.minecraft.util.math.Vec3d;

class KY extends Gi {
   // $FF: synthetic field
   final Lc this$0;

   public KY(Lc this$0) {
      this.this$0 = this$0;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (this.this$0.getAttackTarget() != null && !this.this$0.getMoveHelper().isUpdating() && Lc.access$000(this.this$0).nextInt(7) == 0) {
         return this.this$0.getDistanceSq(this.this$0.getAttackTarget()) > 4.0;
      } else {
         return false;
      }
   }

   public boolean shouldContinueExecuting() {
      return this.this$0.getMoveHelper().isUpdating() && this.this$0.isCharging() && this.this$0.getAttackTarget() != null && this.this$0.getAttackTarget().isEntityAlive();
   }

   public void startExecuting() {
      Iw entitylivingbase = this.this$0.getAttackTarget();
      Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
      Lc.access$100(this.this$0).setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
      this.this$0.setCharging(true);
      this.this$0.playSound(NO.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
   }

   public void resetTask() {
      this.this$0.setCharging(false);
   }

   public void updateTask() {
      Iw entitylivingbase = this.this$0.getAttackTarget();
      if (this.this$0.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox())) {
         this.this$0.attackEntityAsMob(entitylivingbase);
         this.this$0.setCharging(false);
      } else {
         double d0 = this.this$0.getDistanceSq(entitylivingbase);
         if (d0 < 9.0) {
            Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
            Lc.access$200(this.this$0).setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0);
         }
      }

   }
}
