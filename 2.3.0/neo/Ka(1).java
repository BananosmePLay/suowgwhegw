package neo;

import net.minecraft.util.math.MathHelper;

class Ka extends Hx {
   private final Kc entityGuardian;

   public Ka(Kc guardian) {
      super(guardian);
      this.entityGuardian = guardian;
   }

   public void onUpdateMoveHelper() {
      if (this.action == Hw.MOVE_TO && !this.entityGuardian.getNavigator().noPath()) {
         double d0 = this.posX - this.entityGuardian.posX;
         double d1 = this.posY - this.entityGuardian.posY;
         double d2 = this.posZ - this.entityGuardian.posZ;
         double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
         d1 /= d3;
         float f = (float)(MathHelper.atan2(d2, d0) * 57.29577951308232) - 90.0F;
         this.entityGuardian.rotationYaw = this.limitAngle(this.entityGuardian.rotationYaw, f, 90.0F);
         this.entityGuardian.renderYawOffset = this.entityGuardian.rotationYaw;
         float f1 = (float)(this.speed * this.entityGuardian.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue());
         this.entityGuardian.setAIMoveSpeed(this.entityGuardian.getAIMoveSpeed() + (f1 - this.entityGuardian.getAIMoveSpeed()) * 0.125F);
         double d4 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.5) * 0.05;
         double d5 = Math.cos((double)(this.entityGuardian.rotationYaw * 0.017453292F));
         double d6 = Math.sin((double)(this.entityGuardian.rotationYaw * 0.017453292F));
         Kc var10000 = this.entityGuardian;
         var10000.motionX += d4 * d5;
         var10000 = this.entityGuardian;
         var10000.motionZ += d4 * d6;
         d4 = Math.sin((double)(this.entityGuardian.ticksExisted + this.entityGuardian.getEntityId()) * 0.75) * 0.05;
         var10000 = this.entityGuardian;
         var10000.motionY += d4 * (d6 + d5) * 0.25;
         var10000 = this.entityGuardian;
         var10000.motionY += (double)this.entityGuardian.getAIMoveSpeed() * d1 * 0.1;
         Hv entitylookhelper = this.entityGuardian.getLookHelper();
         double d7 = this.entityGuardian.posX + d0 / d3 * 2.0;
         double d8 = (double)this.entityGuardian.getEyeHeight() + this.entityGuardian.posY + d1 / d3;
         double d9 = this.entityGuardian.posZ + d2 / d3 * 2.0;
         double d10 = entitylookhelper.getLookPosX();
         double d11 = entitylookhelper.getLookPosY();
         double d12 = entitylookhelper.getLookPosZ();
         if (!entitylookhelper.getIsLooking()) {
            d10 = d7;
            d11 = d8;
            d12 = d9;
         }

         this.entityGuardian.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125, d11 + (d8 - d11) * 0.125, d12 + (d9 - d12) * 0.125, 10.0F, 40.0F);
         Kc.access$100(this.entityGuardian, true);
      } else {
         this.entityGuardian.setAIMoveSpeed(0.0F);
         Kc.access$100(this.entityGuardian, false);
      }

   }
}
