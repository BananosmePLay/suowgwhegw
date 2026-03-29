package neo;

import net.minecraft.util.math.MathHelper;

public class Ht extends Hx {
   public Ht(Iu p_i47418_1_) {
      super(p_i47418_1_);
   }

   public void onUpdateMoveHelper() {
      if (this.action == Hw.MOVE_TO) {
         this.action = Hw.WAIT;
         this.entity.setNoGravity(true);
         double d0 = this.posX - this.entity.posX;
         double d1 = this.posY - this.entity.posY;
         double d2 = this.posZ - this.entity.posZ;
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         if (d3 < 2.500000277905201E-7) {
            this.entity.setMoveVertical(0.0F);
            this.entity.setMoveForward(0.0F);
            return;
         }

         float f = (float)(MathHelper.atan2(d2, d0) * 57.29577951308232) - 90.0F;
         this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 10.0F);
         float f1;
         if (this.entity.onGround) {
            f1 = (float)(this.speed * this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue());
         } else {
            f1 = (float)(this.speed * this.entity.getEntityAttribute(Ni.FLYING_SPEED).getAttributeValue());
         }

         this.entity.setAIMoveSpeed(f1);
         double d4 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
         float f2 = (float)(-(MathHelper.atan2(d1, d4) * 57.29577951308232));
         this.entity.rotationPitch = this.limitAngle(this.entity.rotationPitch, f2, 10.0F);
         this.entity.setMoveVertical(d1 > 0.0 ? f1 : -f1);
      } else {
         this.entity.setNoGravity(false);
         this.entity.setMoveVertical(0.0F);
         this.entity.setMoveForward(0.0F);
      }

   }
}
