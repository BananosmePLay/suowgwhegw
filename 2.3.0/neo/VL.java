package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public abstract class VL {
   protected Iu entity;
   protected bij world;
   @Nullable
   protected VI currentPath;
   protected double speed;
   private final FZ pathSearchRange;
   protected int totalTicks;
   private int ticksAtLastPos;
   private Vec3d lastPosCheck;
   private Vec3d timeoutCachedNode;
   private long timeoutTimer;
   private long lastTimeoutCheck;
   private double timeoutLimit;
   protected float maxDistanceToWaypoint;
   protected boolean tryUpdatePath;
   private long lastTimeUpdated;
   protected VG nodeProcessor;
   private BlockPos targetPos;
   private final VJ pathFinder;

   public VL(Iu entityIn, bij worldIn) {
      this.lastPosCheck = Vec3d.ZERO;
      this.timeoutCachedNode = Vec3d.ZERO;
      this.maxDistanceToWaypoint = 0.5F;
      this.entity = entityIn;
      this.world = worldIn;
      this.pathSearchRange = entityIn.getEntityAttribute(Ni.FOLLOW_RANGE);
      this.pathFinder = this.getPathFinder();
   }

   protected abstract VJ getPathFinder();

   public void setSpeed(double speedIn) {
      this.speed = speedIn;
   }

   public float getPathSearchRange() {
      return (float)this.pathSearchRange.getAttributeValue();
   }

   public boolean canUpdatePathOnTimeout() {
      return this.tryUpdatePath;
   }

   public void updatePath() {
      if (this.world.getTotalWorldTime() - this.lastTimeUpdated > 20L) {
         if (this.targetPos != null) {
            this.currentPath = null;
            this.currentPath = this.getPathToPos(this.targetPos);
            this.lastTimeUpdated = this.world.getTotalWorldTime();
            this.tryUpdatePath = false;
         }
      } else {
         this.tryUpdatePath = true;
      }

   }

   @Nullable
   public final VI getPathToXYZ(double x, double y, double z) {
      return this.getPathToPos(new BlockPos(x, y, z));
   }

   @Nullable
   public VI getPathToPos(BlockPos pos) {
      if (!this.canNavigate()) {
         return null;
      } else if (this.currentPath != null && !this.currentPath.isFinished() && pos.equals(this.targetPos)) {
         return this.currentPath;
      } else {
         this.targetPos = pos;
         float f = this.getPathSearchRange();
         this.world.profiler.startSection("pathfind");
         BlockPos blockpos = new BlockPos(this.entity);
         int i = (int)(f + 8.0F);
         baI chunkcache = new baI(this.world, blockpos.add(-i, -i, -i), blockpos.add(i, i, i), 0);
         VI path = this.pathFinder.findPath(chunkcache, this.entity, (BlockPos)this.targetPos, f);
         this.world.profiler.endSection();
         return path;
      }
   }

   @Nullable
   public VI getPathToEntityLiving(Ig entityIn) {
      if (!this.canNavigate()) {
         return null;
      } else {
         BlockPos blockpos = new BlockPos(entityIn);
         if (this.currentPath != null && !this.currentPath.isFinished() && blockpos.equals(this.targetPos)) {
            return this.currentPath;
         } else {
            this.targetPos = blockpos;
            float f = this.getPathSearchRange();
            this.world.profiler.startSection("pathfind");
            BlockPos blockpos1 = (new BlockPos(this.entity)).up();
            int i = (int)(f + 16.0F);
            baI chunkcache = new baI(this.world, blockpos1.add(-i, -i, -i), blockpos1.add(i, i, i), 0);
            VI path = this.pathFinder.findPath(chunkcache, this.entity, (Ig)entityIn, f);
            this.world.profiler.endSection();
            return path;
         }
      }
   }

   public boolean tryMoveToXYZ(double x, double y, double z, double speedIn) {
      return this.setPath(this.getPathToXYZ(x, y, z), speedIn);
   }

   public boolean tryMoveToEntityLiving(Ig entityIn, double speedIn) {
      VI path = this.getPathToEntityLiving(entityIn);
      return path != null && this.setPath(path, speedIn);
   }

   public boolean setPath(@Nullable VI pathentityIn, double speedIn) {
      if (pathentityIn == null) {
         this.currentPath = null;
         return false;
      } else {
         if (!pathentityIn.isSamePath(this.currentPath)) {
            this.currentPath = pathentityIn;
         }

         this.removeSunnyPath();
         if (this.currentPath.getCurrentPathLength() <= 0) {
            return false;
         } else {
            this.speed = speedIn;
            Vec3d vec3d = this.getEntityPosition();
            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = vec3d;
            return true;
         }
      }
   }

   @Nullable
   public VI getPath() {
      return this.currentPath;
   }

   public void onUpdateNavigation() {
      ++this.totalTicks;
      if (this.tryUpdatePath) {
         this.updatePath();
      }

      if (!this.noPath()) {
         Vec3d vec3d2;
         if (this.canNavigate()) {
            this.pathFollow();
         } else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) {
            vec3d2 = this.getEntityPosition();
            Vec3d vec3d1 = this.currentPath.getVectorFromIndex(this.entity, this.currentPath.getCurrentPathIndex());
            if (vec3d2.y > vec3d1.y && !this.entity.onGround && MathHelper.floor(vec3d2.x) == MathHelper.floor(vec3d1.x) && MathHelper.floor(vec3d2.z) == MathHelper.floor(vec3d1.z)) {
               this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
            }
         }

         this.debugPathFinding();
         if (!this.noPath()) {
            vec3d2 = this.currentPath.getPosition(this.entity);
            BlockPos blockpos = (new BlockPos(vec3d2)).down();
            AxisAlignedBB axisalignedbb = this.world.getBlockState(blockpos).getBoundingBox(this.world, blockpos);
            vec3d2 = vec3d2.subtract(0.0, 1.0 - axisalignedbb.maxY, 0.0);
            this.entity.getMoveHelper().setMoveTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
         }
      }

   }

   protected void debugPathFinding() {
   }

   protected void pathFollow() {
      Vec3d vec3d = this.getEntityPosition();
      int i = this.currentPath.getCurrentPathLength();

      for(int j = this.currentPath.getCurrentPathIndex(); j < this.currentPath.getCurrentPathLength(); ++j) {
         if ((double)this.currentPath.getPathPointFromIndex(j).y != Math.floor(vec3d.y)) {
            i = j;
            break;
         }
      }

      this.maxDistanceToWaypoint = this.entity.width > 0.75F ? this.entity.width / 2.0F : 0.75F - this.entity.width / 2.0F;
      Vec3d vec3d1 = this.currentPath.getCurrentPos();
      if (MathHelper.abs((float)(this.entity.posX - (vec3d1.x + 0.5))) < this.maxDistanceToWaypoint && MathHelper.abs((float)(this.entity.posZ - (vec3d1.z + 0.5))) < this.maxDistanceToWaypoint && Math.abs(this.entity.posY - vec3d1.y) < 1.0) {
         this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
      }

      int k = MathHelper.ceil(this.entity.width);
      int l = MathHelper.ceil(this.entity.height);
      int i1 = k;

      for(int j1 = i - 1; j1 >= this.currentPath.getCurrentPathIndex(); --j1) {
         if (this.isDirectPathBetweenPoints(vec3d, this.currentPath.getVectorFromIndex(this.entity, j1), k, l, i1)) {
            this.currentPath.setCurrentPathIndex(j1);
            break;
         }
      }

      this.checkForStuck(vec3d);
   }

   protected void checkForStuck(Vec3d positionVec3) {
      if (this.totalTicks - this.ticksAtLastPos > 100) {
         if (positionVec3.squareDistanceTo(this.lastPosCheck) < 2.25) {
            this.clearPath();
         }

         this.ticksAtLastPos = this.totalTicks;
         this.lastPosCheck = positionVec3;
      }

      if (this.currentPath != null && !this.currentPath.isFinished()) {
         Vec3d vec3d = this.currentPath.getCurrentPos();
         if (vec3d.equals(this.timeoutCachedNode)) {
            this.timeoutTimer += System.currentTimeMillis() - this.lastTimeoutCheck;
         } else {
            this.timeoutCachedNode = vec3d;
            double d0 = positionVec3.distanceTo(this.timeoutCachedNode);
            this.timeoutLimit = this.entity.getAIMoveSpeed() > 0.0F ? d0 / (double)this.entity.getAIMoveSpeed() * 1000.0 : 0.0;
         }

         if (this.timeoutLimit > 0.0 && (double)this.timeoutTimer > this.timeoutLimit * 3.0) {
            this.timeoutCachedNode = Vec3d.ZERO;
            this.timeoutTimer = 0L;
            this.timeoutLimit = 0.0;
            this.clearPath();
         }

         this.lastTimeoutCheck = System.currentTimeMillis();
      }

   }

   public boolean noPath() {
      return this.currentPath == null || this.currentPath.isFinished();
   }

   public void clearPath() {
      this.currentPath = null;
   }

   protected abstract Vec3d getEntityPosition();

   protected abstract boolean canNavigate();

   protected boolean isInLiquid() {
      return this.entity.isInWater() || this.entity.isInLava();
   }

   protected void removeSunnyPath() {
      if (this.currentPath != null) {
         for(int i = 0; i < this.currentPath.getCurrentPathLength(); ++i) {
            VR pathpoint = this.currentPath.getPathPointFromIndex(i);
            VR pathpoint1 = i + 1 < this.currentPath.getCurrentPathLength() ? this.currentPath.getPathPointFromIndex(i + 1) : null;
            in iblockstate = this.world.getBlockState(new BlockPos(pathpoint.x, pathpoint.y, pathpoint.z));
            co block = iblockstate.getBlock();
            if (block == Nk.CAULDRON) {
               this.currentPath.setPoint(i, pathpoint.cloneMove(pathpoint.x, pathpoint.y + 1, pathpoint.z));
               if (pathpoint1 != null && pathpoint.y >= pathpoint1.y) {
                  this.currentPath.setPoint(i + 1, pathpoint1.cloneMove(pathpoint1.x, pathpoint.y + 1, pathpoint1.z));
               }
            }
         }
      }

   }

   protected abstract boolean isDirectPathBetweenPoints(Vec3d var1, Vec3d var2, int var3, int var4, int var5);

   public boolean canEntityStandOnPos(BlockPos pos) {
      return this.world.getBlockState(pos.down()).isFullBlock();
   }

   public VG getNodeProcessor() {
      return this.nodeProcessor;
   }
}
