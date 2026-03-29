package neo;

public class Gk extends Gn {
   private int breakingTime;
   private int previousBreakProgress = -1;

   public Gk(Iu entityIn) {
      super(entityIn);
   }

   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else if (!this.entity.world.getGameRules().getBoolean("mobGriefing")) {
         return false;
      } else {
         do blockdoor = this.doorBlock;
         return !do.isOpen(this.entity.world, this.doorPosition);
      }
   }

   public void startExecuting() {
      super.startExecuting();
      this.breakingTime = 0;
   }

   public boolean shouldContinueExecuting() {
      double d0 = this.entity.getDistanceSq(this.doorPosition);
      boolean flag;
      if (this.breakingTime <= 240) {
         do blockdoor = this.doorBlock;
         if (!do.isOpen(this.entity.world, this.doorPosition) && d0 < 4.0) {
            flag = true;
            return flag;
         }
      }

      flag = false;
      return flag;
   }

   public void resetTask() {
      super.resetTask();
      this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.doorPosition, -1);
   }

   public void updateTask() {
      super.updateTask();
      if (this.entity.getRNG().nextInt(20) == 0) {
         this.entity.world.playEvent(1019, this.doorPosition, 0);
      }

      ++this.breakingTime;
      int i = (int)((float)this.breakingTime / 240.0F * 10.0F);
      if (i != this.previousBreakProgress) {
         this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.doorPosition, i);
         this.previousBreakProgress = i;
      }

      if (this.breakingTime == 240 && this.entity.world.getDifficulty() == baV.HARD) {
         this.entity.world.setBlockToAir(this.doorPosition);
         this.entity.world.playEvent(1021, this.doorPosition, 0);
         this.entity.world.playEvent(2001, this.doorPosition, co.getIdFromBlock(this.doorBlock));
      }

   }
}
