package neo;

class KM extends Hx {
   private float yRot;
   private int jumpDelay;
   private final KN slime;
   private boolean isAggressive;

   public KM(KN slimeIn) {
      super(slimeIn);
      this.slime = slimeIn;
      this.yRot = 180.0F * slimeIn.rotationYaw / 3.1415927F;
   }

   public void setDirection(float p_179920_1_, boolean p_179920_2_) {
      this.yRot = p_179920_1_;
      this.isAggressive = p_179920_2_;
   }

   public void setSpeed(double speedIn) {
      this.speed = speedIn;
      this.action = Hw.MOVE_TO;
   }

   public void onUpdateMoveHelper() {
      this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
      this.entity.rotationYawHead = this.entity.rotationYaw;
      this.entity.renderYawOffset = this.entity.rotationYaw;
      if (this.action != Hw.MOVE_TO) {
         this.entity.setMoveForward(0.0F);
      } else {
         this.action = Hw.WAIT;
         if (this.entity.onGround) {
            this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue()));
            if (this.jumpDelay-- <= 0) {
               this.jumpDelay = this.slime.getJumpDelay();
               if (this.isAggressive) {
                  this.jumpDelay /= 3;
               }

               this.slime.getJumpHelper().setJumping();
               if (this.slime.makesSoundOnJump()) {
                  this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
               }
            } else {
               this.slime.moveStrafing = 0.0F;
               this.slime.moveForward = 0.0F;
               this.entity.setAIMoveSpeed(0.0F);
            }
         } else {
            this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue()));
         }
      }

   }
}
