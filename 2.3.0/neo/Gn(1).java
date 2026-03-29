package neo;

import net.minecraft.util.math.BlockPos;

public abstract class Gn extends Gi {
   protected Iu entity;
   protected BlockPos doorPosition;
   protected do doorBlock;
   boolean hasStoppedDoorInteraction;
   float entityPositionX;
   float entityPositionZ;

   public Gn(Iu entityIn) {
      this.doorPosition = BlockPos.ORIGIN;
      this.entity = entityIn;
      if (!(entityIn.getNavigator() instanceof VO)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   public boolean shouldExecute() {
      if (!this.entity.collidedHorizontally) {
         return false;
      } else {
         VO pathnavigateground = (VO)this.entity.getNavigator();
         VI path = pathnavigateground.getPath();
         if (path != null && !path.isFinished() && pathnavigateground.getEnterDoors()) {
            for(int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i) {
               VR pathpoint = path.getPathPointFromIndex(i);
               this.doorPosition = new BlockPos(pathpoint.x, pathpoint.y + 1, pathpoint.z);
               if (this.entity.getDistanceSq((double)this.doorPosition.getX(), this.entity.posY, (double)this.doorPosition.getZ()) <= 2.25) {
                  this.doorBlock = this.getBlockDoor(this.doorPosition);
                  if (this.doorBlock != null) {
                     return true;
                  }
               }
            }

            this.doorPosition = (new BlockPos(this.entity)).up();
            this.doorBlock = this.getBlockDoor(this.doorPosition);
            return this.doorBlock != null;
         } else {
            return false;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.hasStoppedDoorInteraction;
   }

   public void startExecuting() {
      this.hasStoppedDoorInteraction = false;
      this.entityPositionX = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.entity.posX);
      this.entityPositionZ = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.entity.posZ);
   }

   public void updateTask() {
      float f = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.entity.posX);
      float f1 = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.entity.posZ);
      float f2 = this.entityPositionX * f + this.entityPositionZ * f1;
      if (f2 < 0.0F) {
         this.hasStoppedDoorInteraction = true;
      }

   }

   private do getBlockDoor(BlockPos pos) {
      in iblockstate = this.entity.world.getBlockState(pos);
      co block = iblockstate.getBlock();
      return block instanceof do && iblockstate.getMaterial() == hM.WOOD ? (do)block : null;
   }
}
