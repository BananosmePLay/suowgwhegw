package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

class Ky extends GR<ME> {
   // $FF: synthetic field
   final KD this$0;

   public Ky(KD this$0, KD shulker) {
      super(shulker, ME.class, true);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      return this.this$0.world.getDifficulty() == baV.PEACEFUL ? false : super.shouldExecute();
   }

   protected AxisAlignedBB getTargetableArea(double targetDistance) {
      EnumFacing enumfacing = ((KD)this.taskOwner).getAttachmentFacing();
      if (enumfacing.getAxis() == EnumFacing.Axis.X) {
         return this.taskOwner.getEntityBoundingBox().grow(4.0, targetDistance, targetDistance);
      } else {
         return enumfacing.getAxis() == EnumFacing.Axis.Z ? this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, 4.0) : this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0, targetDistance);
      }
   }
}
