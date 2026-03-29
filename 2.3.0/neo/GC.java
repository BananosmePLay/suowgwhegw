package neo;

public class GC extends Gi {
   private final Mc entity;
   private ME owner;
   private boolean isSittingOnShoulder;

   public GC(Mc entityIn) {
      this.entity = entityIn;
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.entity.getOwner();
      boolean flag = entitylivingbase != null && !((ME)entitylivingbase).isSpectator() && !((ME)entitylivingbase).capabilities.isFlying && !entitylivingbase.isInWater();
      return !this.entity.isSitting() && flag && this.entity.canSitOnShoulder();
   }

   public boolean isInterruptible() {
      return !this.isSittingOnShoulder;
   }

   public void startExecuting() {
      this.owner = (ME)this.entity.getOwner();
      this.isSittingOnShoulder = false;
   }

   public void updateTask() {
      if (!this.isSittingOnShoulder && !this.entity.isSitting() && !this.entity.getLeashed() && this.entity.getEntityBoundingBox().intersects(this.owner.getEntityBoundingBox())) {
         this.isSittingOnShoulder = this.entity.setEntityOnShoulder(this.owner);
      }

   }
}
