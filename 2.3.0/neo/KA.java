package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

class KA extends GR<Iw> {
   public KA(KD shulker) {
      super(shulker, Iw.class, 10, true, false, new Predicate<Iw>() {
         public boolean apply(@Nullable Iw p_apply_1_) {
            return p_apply_1_ instanceof Lo;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iw)var1);
         }
      });
   }

   public boolean shouldExecute() {
      return this.taskOwner.getTeam() == null ? false : super.shouldExecute();
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
