package neo;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class VM extends VO {
   private BlockPos targetPosition;

   public VM(Iu entityLivingIn, bij worldIn) {
      super(entityLivingIn, worldIn);
   }

   public VI getPathToPos(BlockPos pos) {
      this.targetPosition = pos;
      return super.getPathToPos(pos);
   }

   public VI getPathToEntityLiving(Ig entityIn) {
      this.targetPosition = new BlockPos(entityIn);
      return super.getPathToEntityLiving(entityIn);
   }

   public boolean tryMoveToEntityLiving(Ig entityIn, double speedIn) {
      VI path = this.getPathToEntityLiving(entityIn);
      if (path != null) {
         return this.setPath(path, speedIn);
      } else {
         this.targetPosition = new BlockPos(entityIn);
         this.speed = speedIn;
         return true;
      }
   }

   public void onUpdateNavigation() {
      if (!this.noPath()) {
         super.onUpdateNavigation();
      } else if (this.targetPosition != null) {
         double d0 = (double)(this.entity.width * this.entity.width);
         if (!(this.entity.getDistanceSqToCenter(this.targetPosition) >= d0) || !(this.entity.posY <= (double)this.targetPosition.getY()) && !(this.entity.getDistanceSqToCenter(new BlockPos(this.targetPosition.getX(), MathHelper.floor(this.entity.posY), this.targetPosition.getZ())) >= d0)) {
            this.targetPosition = null;
         } else {
            this.entity.getMoveHelper().setMoveTo((double)this.targetPosition.getX(), (double)this.targetPosition.getY(), (double)this.targetPosition.getZ(), this.speed);
         }
      }

   }
}
