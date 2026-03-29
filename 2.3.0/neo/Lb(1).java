package neo;

import net.minecraft.util.math.BlockPos;

class Lb extends Gi {
   // $FF: synthetic field
   final Lc this$0;

   public Lb(Lc this$0) {
      this.this$0 = this$0;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      return !this.this$0.getMoveHelper().isUpdating() && Lc.access$400(this.this$0).nextInt(7) == 0;
   }

   public boolean shouldContinueExecuting() {
      return false;
   }

   public void updateTask() {
      BlockPos blockpos = this.this$0.getBoundOrigin();
      if (blockpos == null) {
         blockpos = new BlockPos(this.this$0);
      }

      for(int i = 0; i < 3; ++i) {
         BlockPos blockpos1 = blockpos.add(Lc.access$500(this.this$0).nextInt(15) - 7, Lc.access$600(this.this$0).nextInt(11) - 5, Lc.access$700(this.this$0).nextInt(15) - 7);
         if (this.this$0.world.isAirBlock(blockpos1)) {
            Lc.access$800(this.this$0).setMoveTo((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 0.25);
            if (this.this$0.getAttackTarget() == null) {
               this.this$0.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 180.0F, 20.0F);
            }
            break;
         }
      }

   }
}
