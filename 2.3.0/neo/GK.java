package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class GK extends Gi {
   private final Ik entity;
   private final double movementSpeed;
   private VI path;
   private Zc doorInfo;
   private final boolean isNocturnal;
   private final List<Zc> doorList = Lists.newArrayList();

   public GK(Ik entityIn, double movementSpeedIn, boolean isNocturnalIn) {
      this.entity = entityIn;
      this.movementSpeed = movementSpeedIn;
      this.isNocturnal = isNocturnalIn;
      this.setMutexBits(1);
      if (!(entityIn.getNavigator() instanceof VO)) {
         throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
      }
   }

   public boolean shouldExecute() {
      this.resizeDoorList();
      if (this.isNocturnal && this.entity.world.isDaytime()) {
         return false;
      } else {
         Za village = this.entity.world.getVillageCollection().getNearestVillage(new BlockPos(this.entity), 0);
         if (village == null) {
            return false;
         } else {
            this.doorInfo = this.findNearestDoor(village);
            if (this.doorInfo == null) {
               return false;
            } else {
               VO pathnavigateground = (VO)this.entity.getNavigator();
               boolean flag = pathnavigateground.getEnterDoors();
               pathnavigateground.setBreakDoors(false);
               this.path = pathnavigateground.getPathToPos(this.doorInfo.getDoorBlockPos());
               pathnavigateground.setBreakDoors(flag);
               if (this.path != null) {
                  return true;
               } else {
                  Vec3d vec3d = HA.findRandomTargetBlockTowards(this.entity, 10, 7, new Vec3d((double)this.doorInfo.getDoorBlockPos().getX(), (double)this.doorInfo.getDoorBlockPos().getY(), (double)this.doorInfo.getDoorBlockPos().getZ()));
                  if (vec3d == null) {
                     return false;
                  } else {
                     pathnavigateground.setBreakDoors(false);
                     this.path = this.entity.getNavigator().getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                     pathnavigateground.setBreakDoors(flag);
                     return this.path != null;
                  }
               }
            }
         }
      }
   }

   public boolean shouldContinueExecuting() {
      if (this.entity.getNavigator().noPath()) {
         return false;
      } else {
         float f = this.entity.width + 4.0F;
         return this.entity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
      }
   }

   public void startExecuting() {
      this.entity.getNavigator().setPath(this.path, this.movementSpeed);
   }

   public void resetTask() {
      if (this.entity.getNavigator().noPath() || this.entity.getDistanceSq(this.doorInfo.getDoorBlockPos()) < 16.0) {
         this.doorList.add(this.doorInfo);
      }

   }

   private Zc findNearestDoor(Za villageIn) {
      Zc villagedoorinfo = null;
      int i = Integer.MAX_VALUE;
      Iterator var4 = villageIn.getVillageDoorInfoList().iterator();

      while(var4.hasNext()) {
         Zc villagedoorinfo1 = (Zc)var4.next();
         int j = villagedoorinfo1.getDistanceSquared(MathHelper.floor(this.entity.posX), MathHelper.floor(this.entity.posY), MathHelper.floor(this.entity.posZ));
         if (j < i && !this.doesDoorListContain(villagedoorinfo1)) {
            villagedoorinfo = villagedoorinfo1;
            i = j;
         }
      }

      return villagedoorinfo;
   }

   private boolean doesDoorListContain(Zc doorInfoIn) {
      Iterator var2 = this.doorList.iterator();

      Zc villagedoorinfo;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         villagedoorinfo = (Zc)var2.next();
      } while(!doorInfoIn.getDoorBlockPos().equals(villagedoorinfo.getDoorBlockPos()));

      return true;
   }

   private void resizeDoorList() {
      if (this.doorList.size() > 15) {
         this.doorList.remove(0);
      }

   }
}
