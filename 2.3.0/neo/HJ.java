package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HJ extends HD {
   private VI currentPath;
   private Vec3d targetLocation;

   public HJ(HS dragonIn) {
      super(dragonIn);
   }

   public HK<HJ> getType() {
      return HK.LANDING_APPROACH;
   }

   public void initPhase() {
      this.currentPath = null;
      this.targetLocation = null;
   }

   public void doLocalUpdate() {
      double d0 = this.targetLocation == null ? 0.0 : this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
      if (d0 < 100.0 || d0 > 22500.0 || this.dragon.collidedHorizontally || this.dragon.collidedVertically) {
         this.findNewTarget();
      }

   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   private void findNewTarget() {
      if (this.currentPath == null || this.currentPath.isFinished()) {
         int i = this.dragon.initPathPoints();
         BlockPos blockpos = this.dragon.world.getTopSolidOrLiquidBlock(bbD.END_PODIUM_LOCATION);
         ME entityplayer = this.dragon.world.getNearestAttackablePlayer(blockpos, 128.0, 128.0);
         int j;
         if (entityplayer != null) {
            Vec3d vec3d = (new Vec3d(entityplayer.posX, 0.0, entityplayer.posZ)).normalize();
            j = this.dragon.getNearestPpIdx(-vec3d.x * 40.0, 105.0, -vec3d.z * 40.0);
         } else {
            j = this.dragon.getNearestPpIdx(40.0, (double)blockpos.getY(), 0.0);
         }

         VR pathpoint = new VR(blockpos.getX(), blockpos.getY(), blockpos.getZ());
         this.currentPath = this.dragon.findPath(i, j, pathpoint);
         if (this.currentPath != null) {
            this.currentPath.incrementPathIndex();
         }
      }

      this.navigateToNextPathNode();
      if (this.currentPath != null && this.currentPath.isFinished()) {
         this.dragon.getPhaseManager().setPhase(HK.LANDING);
      }

   }

   private void navigateToNextPathNode() {
      if (this.currentPath != null && !this.currentPath.isFinished()) {
         Vec3d vec3d = this.currentPath.getCurrentPos();
         this.currentPath.incrementPathIndex();
         double d0 = vec3d.x;
         double d1 = vec3d.z;

         double d2;
         do {
            d2 = vec3d.y + (double)(this.dragon.getRNG().nextFloat() * 20.0F);
         } while(!(d2 >= vec3d.y));

         this.targetLocation = new Vec3d(d0, d2, d1);
      }

   }
}
