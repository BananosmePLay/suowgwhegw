package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Gx extends Gi {
   private final Mg tameable;
   private Iw owner;
   bij world;
   private final double followSpeed;
   private final VL petPathfinder;
   private int timeToRecalcPath;
   float maxDist;
   float minDist;
   private float oldWaterCost;

   public Gx(Mg tameableIn, double followSpeedIn, float minDistIn, float maxDistIn) {
      this.tameable = tameableIn;
      this.world = tameableIn.world;
      this.followSpeed = followSpeedIn;
      this.petPathfinder = tameableIn.getNavigator();
      this.minDist = minDistIn;
      this.maxDist = maxDistIn;
      this.setMutexBits(3);
      if (!(tameableIn.getNavigator() instanceof VO) && !(tameableIn.getNavigator() instanceof VN)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
      }
   }

   public boolean shouldExecute() {
      Iw entitylivingbase = this.tameable.getOwner();
      if (entitylivingbase == null) {
         return false;
      } else if (entitylivingbase instanceof ME && ((ME)entitylivingbase).isSpectator()) {
         return false;
      } else if (this.tameable.isSitting()) {
         return false;
      } else if (this.tameable.getDistanceSq(entitylivingbase) < (double)(this.minDist * this.minDist)) {
         return false;
      } else {
         this.owner = entitylivingbase;
         return true;
      }
   }

   public boolean shouldContinueExecuting() {
      return !this.petPathfinder.noPath() && this.tameable.getDistanceSq(this.owner) > (double)(this.maxDist * this.maxDist) && !this.tameable.isSitting();
   }

   public void startExecuting() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.tameable.getPathPriority(VQ.WATER);
      this.tameable.setPathPriority(VQ.WATER, 0.0F);
   }

   public void resetTask() {
      this.owner = null;
      this.petPathfinder.clearPath();
      this.tameable.setPathPriority(VQ.WATER, this.oldWaterCost);
   }

   public void updateTask() {
      this.tameable.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float)this.tameable.getVerticalFaceSpeed());
      if (!this.tameable.isSitting() && --this.timeToRecalcPath <= 0) {
         this.timeToRecalcPath = 10;
         if (!this.petPathfinder.tryMoveToEntityLiving(this.owner, this.followSpeed) && !this.tameable.getLeashed() && !this.tameable.isRiding() && this.tameable.getDistanceSq(this.owner) >= 144.0) {
            int i = MathHelper.floor(this.owner.posX) - 2;
            int j = MathHelper.floor(this.owner.posZ) - 2;
            int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

            for(int l = 0; l <= 4; ++l) {
               for(int i1 = 0; i1 <= 4; ++i1) {
                  if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.isTeleportFriendlyBlock(i, j, k, l, i1)) {
                     this.tameable.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.tameable.rotationYaw, this.tameable.rotationPitch);
                     this.petPathfinder.clearPath();
                     return;
                  }
               }
            }
         }
      }

   }

   protected boolean isTeleportFriendlyBlock(int x, int z, int y, int xOffset, int zOffset) {
      BlockPos blockpos = new BlockPos(x + xOffset, y - 1, z + zOffset);
      in iblockstate = this.world.getBlockState(blockpos);
      return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == ib.SOLID && iblockstate.canEntitySpawn(this.tameable) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
   }
}
