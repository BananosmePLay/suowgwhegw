package neo;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

class JV extends Hx {
   private final JW parentEntity;
   private int courseChangeCooldown;

   public JV(JW ghast) {
      super(ghast);
      this.parentEntity = ghast;
   }

   public void onUpdateMoveHelper() {
      if (this.action == Hw.MOVE_TO) {
         double d0 = this.posX - this.parentEntity.posX;
         double d1 = this.posY - this.parentEntity.posY;
         double d2 = this.posZ - this.parentEntity.posZ;
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt(d3);
            if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
               JW var10000 = this.parentEntity;
               var10000.motionX += d0 / d3 * 0.1;
               var10000 = this.parentEntity;
               var10000.motionY += d1 / d3 * 0.1;
               var10000 = this.parentEntity;
               var10000.motionZ += d2 / d3 * 0.1;
            } else {
               this.action = Hw.WAIT;
            }
         }
      }

   }

   private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
      double d0 = (x - this.parentEntity.posX) / p_179926_7_;
      double d1 = (y - this.parentEntity.posY) / p_179926_7_;
      double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
      AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

      for(int i = 1; (double)i < p_179926_7_; ++i) {
         axisalignedbb = axisalignedbb.offset(d0, d1, d2);
         if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty()) {
            return false;
         }
      }

      return true;
   }
}
