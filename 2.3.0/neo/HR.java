package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HR extends HD {
   private boolean firstTick;
   private VI currentPath;
   private Vec3d targetLocation;

   public HR(HS dragonIn) {
      super(dragonIn);
   }

   public void doLocalUpdate() {
      if (!this.firstTick && this.currentPath != null) {
         BlockPos blockpos = this.dragon.world.getTopSolidOrLiquidBlock(bbD.END_PODIUM_LOCATION);
         double d0 = this.dragon.getDistanceSqToCenter(blockpos);
         if (d0 > 100.0) {
            this.dragon.getPhaseManager().setPhase(HK.HOLDING_PATTERN);
         }
      } else {
         this.firstTick = false;
         this.findNewTarget();
      }

   }

   public void initPhase() {
      this.firstTick = true;
      this.currentPath = null;
      this.targetLocation = null;
   }

   private void findNewTarget() {
      int i = this.dragon.initPathPoints();
      Vec3d vec3d = this.dragon.getHeadLookVec(1.0F);
      int j = this.dragon.getNearestPpIdx(-vec3d.x * 40.0, 105.0, -vec3d.z * 40.0);
      if (this.dragon.getFightManager() != null && this.dragon.getFightManager().getNumAliveCrystals() > 0) {
         j %= 12;
         if (j < 0) {
            j += 12;
         }
      } else {
         j -= 12;
         j &= 7;
         j += 12;
      }

      this.currentPath = this.dragon.findPath(i, j, (VR)null);
      if (this.currentPath != null) {
         this.currentPath.incrementPathIndex();
         this.navigateToNextPathNode();
      }

   }

   private void navigateToNextPathNode() {
      Vec3d vec3d = this.currentPath.getCurrentPos();
      this.currentPath.incrementPathIndex();

      double d0;
      do {
         d0 = vec3d.y + (double)(this.dragon.getRNG().nextFloat() * 20.0F);
      } while(!(d0 >= vec3d.y));

      this.targetLocation = new Vec3d(vec3d.x, d0, vec3d.z);
   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   public HK<HR> getType() {
      return HK.TAKEOFF;
   }
}
