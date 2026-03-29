package neo;

class Kx extends Gi {
   private int attackTime;
   // $FF: synthetic field
   final KD this$0;

   public Kx(KD this$0) {
      this.this$0 = this$0;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.this$0.getAttackTarget();
      if (entitylivingbase != null && entitylivingbase.isEntityAlive()) {
         return this.this$0.world.getDifficulty() != baV.PEACEFUL;
      } else {
         return false;
      }
   }

   public void startExecuting() {
      this.attackTime = 20;
      this.this$0.updateArmorModifier(100);
   }

   public void resetTask() {
      this.this$0.updateArmorModifier(0);
   }

   public void updateTask() {
      if (this.this$0.world.getDifficulty() != baV.PEACEFUL) {
         --this.attackTime;
         Iw entitylivingbase = this.this$0.getAttackTarget();
         this.this$0.getLookHelper().setLookPositionWithEntity(entitylivingbase, 180.0F, 180.0F);
         double d0 = this.this$0.getDistanceSq(entitylivingbase);
         if (d0 < 400.0) {
            if (this.attackTime <= 0) {
               this.attackTime = 20 + KD.access$100(this.this$0).nextInt(10) * 20 / 2;
               MZ entityshulkerbullet = new MZ(this.this$0.world, this.this$0, entitylivingbase, this.this$0.getAttachmentFacing().getAxis());
               this.this$0.world.spawnEntity(entityshulkerbullet);
               this.this$0.playSound(NO.ENTITY_SHULKER_SHOOT, 2.0F, (KD.access$200(this.this$0).nextFloat() - KD.access$300(this.this$0).nextFloat()) * 0.2F + 1.0F);
            }
         } else {
            this.this$0.setAttackTarget((Iw)null);
         }

         super.updateTask();
      }

   }
}
