package neo;

import net.minecraft.util.math.MathHelper;

public class Hx {
   protected final Iu entity;
   protected double posX;
   protected double posY;
   protected double posZ;
   protected double speed;
   protected float moveForward;
   protected float moveStrafe;
   public Hw action;

   public Hx(Iu entitylivingIn) {
      this.action = Hw.WAIT;
      this.entity = entitylivingIn;
   }

   public boolean isUpdating() {
      return this.action == Hw.MOVE_TO;
   }

   public double getSpeed() {
      return this.speed;
   }

   public void setMoveTo(double x, double y, double z, double speedIn) {
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      this.speed = speedIn;
      this.action = Hw.MOVE_TO;
   }

   public void strafe(float forward, float strafe) {
      this.action = Hw.STRAFE;
      this.moveForward = forward;
      this.moveStrafe = strafe;
      this.speed = 0.25;
   }

   public void read(Hx that) {
      this.action = that.action;
      this.posX = that.posX;
      this.posY = that.posY;
      this.posZ = that.posZ;
      this.speed = Math.max(that.speed, 1.0);
      this.moveForward = that.moveForward;
      this.moveStrafe = that.moveStrafe;
   }

   public void onUpdateMoveHelper() {
      float f8;
      if (this.action == Hw.STRAFE) {
         float f = (float)this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue();
         float f1 = (float)this.speed * f;
         float f2 = this.moveForward;
         float f3 = this.moveStrafe;
         float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
         if (f4 < 1.0F) {
            f4 = 1.0F;
         }

         f4 = f1 / f4;
         f2 *= f4;
         f3 *= f4;
         float f5 = MathHelper.sin(this.entity.rotationYaw * 0.017453292F);
         float f6 = MathHelper.cos(this.entity.rotationYaw * 0.017453292F);
         float f7 = f2 * f6 - f3 * f5;
         f8 = f3 * f6 + f2 * f5;
         VL pathnavigate = this.entity.getNavigator();
         if (pathnavigate != null) {
            VG nodeprocessor = pathnavigate.getNodeProcessor();
            if (nodeprocessor != null && nodeprocessor.getPathNodeType(this.entity.world, MathHelper.floor(this.entity.posX + (double)f7), MathHelper.floor(this.entity.posY), MathHelper.floor(this.entity.posZ + (double)f8)) != VQ.WALKABLE) {
               this.moveForward = 1.0F;
               this.moveStrafe = 0.0F;
               f1 = f;
            }
         }

         this.entity.setAIMoveSpeed(f1);
         this.entity.setMoveForward(this.moveForward);
         this.entity.setMoveStrafing(this.moveStrafe);
         this.action = Hw.WAIT;
      } else if (this.action == Hw.MOVE_TO) {
         this.action = Hw.WAIT;
         double d0 = this.posX - this.entity.posX;
         double d1 = this.posZ - this.entity.posZ;
         double d2 = this.posY - this.entity.posY;
         double d3 = d0 * d0 + d2 * d2 + d1 * d1;
         if (d3 < 2.500000277905201E-7) {
            this.entity.setMoveForward(0.0F);
            return;
         }

         f8 = (float)(MathHelper.atan2(d1, d0) * 57.29577951308232) - 90.0F;
         this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f8, 90.0F);
         this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue()));
         if (d2 > (double)this.entity.stepHeight && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.entity.width)) {
            this.entity.getJumpHelper().setJumping();
            this.action = Hw.JUMPING;
         }
      } else if (this.action == Hw.JUMPING) {
         this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue()));
         if (this.entity.onGround) {
            this.action = Hw.WAIT;
         }
      } else {
         this.entity.setMoveForward(0.0F);
      }

   }

   protected float limitAngle(float sourceAngle, float targetAngle, float maximumChange) {
      float f = MathHelper.wrapDegrees(targetAngle - sourceAngle);
      if (f > maximumChange) {
         f = maximumChange;
      }

      if (f < -maximumChange) {
         f = -maximumChange;
      }

      float f1 = sourceAngle + f;
      if (f1 < 0.0F) {
         f1 += 360.0F;
      } else if (f1 > 360.0F) {
         f1 -= 360.0F;
      }

      return f1;
   }

   public double getX() {
      return this.posX;
   }

   public double getY() {
      return this.posY;
   }

   public double getZ() {
      return this.posZ;
   }
}
