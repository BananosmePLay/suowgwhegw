package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class VJ {
   private final VK path = new VK();
   private final Set<VR> closedSet = Sets.newHashSet();
   private final VR[] pathOptions = new VR[32];
   private final VG nodeProcessor;

   public VJ(VG processor) {
      this.nodeProcessor = processor;
   }

   @Nullable
   public VI findPath(bfZ worldIn, Iu entitylivingIn, Ig targetEntity, float maxDistance) {
      return this.findPath(worldIn, entitylivingIn, targetEntity.posX, targetEntity.getEntityBoundingBox().minY, targetEntity.posZ, maxDistance);
   }

   @Nullable
   public VI findPath(bfZ worldIn, Iu entitylivingIn, BlockPos targetPos, float maxDistance) {
      return this.findPath(worldIn, entitylivingIn, (double)((float)targetPos.getX() + 0.5F), (double)((float)targetPos.getY() + 0.5F), (double)((float)targetPos.getZ() + 0.5F), maxDistance);
   }

   @Nullable
   private VI findPath(bfZ worldIn, Iu entitylivingIn, double x, double y, double z, float maxDistance) {
      this.path.clearPath();
      this.nodeProcessor.init(worldIn, entitylivingIn);
      VR pathpoint = this.nodeProcessor.getStart();
      VR pathpoint1 = this.nodeProcessor.getPathPointToCoords(x, y, z);
      VI path = this.findPath(pathpoint, pathpoint1, maxDistance);
      this.nodeProcessor.postProcess();
      return path;
   }

   @Nullable
   private VI findPath(VR pathFrom, VR pathTo, float maxDistance) {
      pathFrom.totalPathDistance = 0.0F;
      pathFrom.distanceToNext = pathFrom.distanceManhattan(pathTo);
      pathFrom.distanceToTarget = pathFrom.distanceToNext;
      this.path.clearPath();
      this.closedSet.clear();
      this.path.addPoint(pathFrom);
      VR pathpoint = pathFrom;
      int i = 0;

      while(!this.path.isPathEmpty()) {
         ++i;
         if (i >= 200) {
            break;
         }

         VR pathpoint1 = this.path.dequeue();
         if (pathpoint1.equals(pathTo)) {
            pathpoint = pathTo;
            break;
         }

         if (pathpoint1.distanceManhattan(pathTo) < pathpoint.distanceManhattan(pathTo)) {
            pathpoint = pathpoint1;
         }

         pathpoint1.visited = true;
         int j = this.nodeProcessor.findPathOptions(this.pathOptions, pathpoint1, pathTo, maxDistance);

         for(int k = 0; k < j; ++k) {
            VR pathpoint2 = this.pathOptions[k];
            float f = pathpoint1.distanceManhattan(pathpoint2);
            pathpoint2.distanceFromOrigin = pathpoint1.distanceFromOrigin + f;
            pathpoint2.cost = f + pathpoint2.costMalus;
            float f1 = pathpoint1.totalPathDistance + pathpoint2.cost;
            if (pathpoint2.distanceFromOrigin < maxDistance && (!pathpoint2.isAssigned() || f1 < pathpoint2.totalPathDistance)) {
               pathpoint2.previous = pathpoint1;
               pathpoint2.totalPathDistance = f1;
               pathpoint2.distanceToNext = pathpoint2.distanceManhattan(pathTo) + pathpoint2.costMalus;
               if (pathpoint2.isAssigned()) {
                  this.path.changeDistance(pathpoint2, pathpoint2.totalPathDistance + pathpoint2.distanceToNext);
               } else {
                  pathpoint2.distanceToTarget = pathpoint2.totalPathDistance + pathpoint2.distanceToNext;
                  this.path.addPoint(pathpoint2);
               }
            }
         }
      }

      if (pathpoint == pathFrom) {
         return null;
      } else {
         VI path = this.createPath(pathFrom, pathpoint);
         return path;
      }
   }

   private VI createPath(VR start, VR end) {
      int i = 1;

      for(VR pathpoint = end; pathpoint.previous != null; pathpoint = pathpoint.previous) {
         ++i;
      }

      VR[] apathpoint = new VR[i];
      VR pathpoint1 = end;
      --i;

      for(apathpoint[i] = end; pathpoint1.previous != null; apathpoint[i] = pathpoint1) {
         pathpoint1 = pathpoint1.previous;
         --i;
      }

      return new VI(apathpoint);
   }
}
