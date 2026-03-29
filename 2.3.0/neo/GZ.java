package neo;

import net.minecraft.util.math.BlockPos;

public class GZ extends Gi {
   private final Ik entity;
   private Zc frontDoor;

   public GZ(Ik creatureIn) {
      this.entity = creatureIn;
      if (!(creatureIn.getNavigator() instanceof VO)) {
         throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
      }
   }

   public boolean shouldExecute() {
      if (this.entity.world.isDaytime()) {
         return false;
      } else {
         BlockPos blockpos = new BlockPos(this.entity);
         Za village = this.entity.world.getVillageCollection().getNearestVillage(blockpos, 16);
         if (village == null) {
            return false;
         } else {
            this.frontDoor = village.getNearestDoor(blockpos);
            if (this.frontDoor == null) {
               return false;
            } else {
               return (double)this.frontDoor.getDistanceToInsideBlockSq(blockpos) < 2.25;
            }
         }
      }
   }

   public boolean shouldContinueExecuting() {
      if (this.entity.world.isDaytime()) {
         return false;
      } else {
         return !this.frontDoor.getIsDetachedFromVillageFlag() && this.frontDoor.isInsideSide(new BlockPos(this.entity));
      }
   }

   public void startExecuting() {
      ((VO)this.entity.getNavigator()).setBreakDoors(false);
      ((VO)this.entity.getNavigator()).setEnterDoors(false);
   }

   public void resetTask() {
      ((VO)this.entity.getNavigator()).setBreakDoors(true);
      ((VO)this.entity.getNavigator()).setEnterDoors(true);
      this.frontDoor = null;
   }

   public void updateTask() {
      this.frontDoor.incrementDoorOpeningRestrictionCounter();
   }
}
