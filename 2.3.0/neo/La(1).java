package neo;

import net.minecraft.util.math.MathHelper;

class La extends Hx {
   // $FF: synthetic field
   final Lc this$0;

   public La(Lc this$0, Lc vex) {
      super(vex);
      this.this$0 = this$0;
   }

   public void onUpdateMoveHelper() {
      if (this.action == Hw.MOVE_TO) {
         double d0 = this.posX - this.this$0.posX;
         double d1 = this.posY - this.this$0.posY;
         double d2 = this.posZ - this.this$0.posZ;
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         d3 = (double)MathHelper.sqrt(d3);
         Lc var10000;
         if (d3 < this.this$0.getEntityBoundingBox().getAverageEdgeLength()) {
            this.action = Hw.WAIT;
            var10000 = this.this$0;
            var10000.motionX *= 0.5;
            var10000 = this.this$0;
            var10000.motionY *= 0.5;
            var10000 = this.this$0;
            var10000.motionZ *= 0.5;
         } else {
            var10000 = this.this$0;
            var10000.motionX += d0 / d3 * 0.05 * this.speed;
            var10000 = this.this$0;
            var10000.motionY += d1 / d3 * 0.05 * this.speed;
            var10000 = this.this$0;
            var10000.motionZ += d2 / d3 * 0.05 * this.speed;
            if (this.this$0.getAttackTarget() == null) {
               this.this$0.rotationYaw = -((float)MathHelper.atan2(this.this$0.motionX, this.this$0.motionZ)) * 57.295776F;
               this.this$0.renderYawOffset = this.this$0.rotationYaw;
            } else {
               double d4 = this.this$0.getAttackTarget().posX - this.this$0.posX;
               double d5 = this.this$0.getAttackTarget().posZ - this.this$0.posZ;
               this.this$0.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * 57.295776F;
               this.this$0.renderYawOffset = this.this$0.rotationYaw;
            }
         }
      }

   }
}
