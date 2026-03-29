package neo;

import net.minecraft.util.math.MathHelper;

public class GD extends Gi {
   Iu leaper;
   Iw leapTarget;
   float leapMotionY;

   public GD(Iu leapingEntity, float leapMotionYIn) {
      this.leaper = leapingEntity;
      this.leapMotionY = leapMotionYIn;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      this.leapTarget = this.leaper.getAttackTarget();
      if (this.leapTarget == null) {
         return false;
      } else {
         double d0 = this.leaper.getDistanceSq(this.leapTarget);
         if (d0 >= 4.0 && d0 <= 16.0) {
            if (!this.leaper.onGround) {
               return false;
            } else {
               return this.leaper.getRNG().nextInt(5) == 0;
            }
         } else {
            return false;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.leaper.onGround;
   }

   public void startExecuting() {
      double d0 = this.leapTarget.posX - this.leaper.posX;
      double d1 = this.leapTarget.posZ - this.leaper.posZ;
      float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
      if ((double)f >= 1.0E-4) {
         Iu var10000 = this.leaper;
         var10000.motionX += d0 / (double)f * 0.5 * 0.800000011920929 + this.leaper.motionX * 0.20000000298023224;
         var10000 = this.leaper;
         var10000.motionZ += d1 / (double)f * 0.5 * 0.800000011920929 + this.leaper.motionZ * 0.20000000298023224;
      }

      this.leaper.motionY = (double)this.leapMotionY;
   }
}
