package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HG extends HD {
   private VI currentPath;
   private Vec3d targetLocation;
   private boolean clockwise;

   public HG(HS dragonIn) {
      super(dragonIn);
   }

   public HK<HG> getType() {
      return HK.HOLDING_PATTERN;
   }

   public void doLocalUpdate() {
      double d0 = this.targetLocation == null ? 0.0 : this.targetLocation.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
      if (d0 < 100.0 || d0 > 22500.0 || this.dragon.collidedHorizontally || this.dragon.collidedVertically) {
         this.findNewTarget();
      }

   }

   public void initPhase() {
      this.currentPath = null;
      this.targetLocation = null;
   }

   @Nullable
   public Vec3d getTargetLocation() {
      return this.targetLocation;
   }

   private void findNewTarget() {
      int k;
      if (this.currentPath != null && this.currentPath.isFinished()) {
         BlockPos blockpos = this.dragon.world.getTopSolidOrLiquidBlock(new BlockPos(bbD.END_PODIUM_LOCATION));
         k = this.dragon.getFightManager() == null ? 0 : this.dragon.getFightManager().getNumAliveCrystals();
         if (this.dragon.getRNG().nextInt(k + 3) == 0) {
            this.dragon.getPhaseManager().setPhase(HK.LANDING_APPROACH);
            return;
         }

         double d0 = 64.0;
         ME entityplayer = this.dragon.world.getNearestAttackablePlayer(blockpos, d0, d0);
         if (entityplayer != null) {
            d0 = entityplayer.getDistanceSqToCenter(blockpos) / 512.0;
         }

         if (entityplayer != null && (this.dragon.getRNG().nextInt(MathHelper.abs((int)d0) + 2) == 0 || this.dragon.getRNG().nextInt(k + 2) == 0)) {
            this.strafePlayer(entityplayer);
            return;
         }
      }

      if (this.currentPath == null || this.currentPath.isFinished()) {
         int j = this.dragon.initPathPoints();
         k = j;
         if (this.dragon.getRNG().nextInt(8) == 0) {
            this.clockwise = !this.clockwise;
            k = j + 6;
         }

         if (this.clockwise) {
            ++k;
         } else {
            --k;
         }

         if (this.dragon.getFightManager() != null && this.dragon.getFightManager().getNumAliveCrystals() >= 0) {
            k %= 12;
            if (k < 0) {
               k += 12;
            }
         } else {
            k -= 12;
            k &= 7;
            k += 12;
         }

         this.currentPath = this.dragon.findPath(j, k, (VR)null);
         if (this.currentPath != null) {
            this.currentPath.incrementPathIndex();
         }
      }

      this.navigateToNextPathNode();
   }

   private void strafePlayer(ME player) {
      this.dragon.getPhaseManager().setPhase(HK.STRAFE_PLAYER);
      ((HQ)this.dragon.getPhaseManager().getPhase(HK.STRAFE_PLAYER)).setTarget(player);
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

   public void onCrystalDestroyed(IS crystal, BlockPos pos, DamageSource dmgSrc, @Nullable ME plyr) {
      if (plyr != null && !plyr.capabilities.disableDamage) {
         this.strafePlayer(plyr);
      }

   }
}
