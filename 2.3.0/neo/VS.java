package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class VS implements bgc {
   private final List<VL> navigations = Lists.newArrayList();

   public VS() {
   }

   public void notifyBlockUpdate(bij worldIn, BlockPos pos, in oldState, in newState, int flags) {
      if (this.didBlockChange(worldIn, pos, oldState, newState)) {
         int i = 0;

         for(int j = this.navigations.size(); i < j; ++i) {
            VL pathnavigate = (VL)this.navigations.get(i);
            if (pathnavigate != null && !pathnavigate.canUpdatePathOnTimeout()) {
               VI path = pathnavigate.getPath();
               if (path != null && !path.isFinished() && path.getCurrentPathLength() != 0) {
                  VR pathpoint = pathnavigate.currentPath.getFinalPathPoint();
                  double d0 = pos.distanceSq(((double)pathpoint.x + pathnavigate.entity.posX) / 2.0, ((double)pathpoint.y + pathnavigate.entity.posY) / 2.0, ((double)pathpoint.z + pathnavigate.entity.posZ) / 2.0);
                  int k = (path.getCurrentPathLength() - path.getCurrentPathIndex()) * (path.getCurrentPathLength() - path.getCurrentPathIndex());
                  if (d0 < (double)k) {
                     pathnavigate.updatePath();
                  }
               }
            }
         }
      }

   }

   protected boolean didBlockChange(bij worldIn, BlockPos pos, in oldState, in newState) {
      AxisAlignedBB axisalignedbb = oldState.getCollisionBoundingBox(worldIn, pos);
      AxisAlignedBB axisalignedbb1 = newState.getCollisionBoundingBox(worldIn, pos);
      return axisalignedbb != axisalignedbb1 && (axisalignedbb == null || !axisalignedbb.equals(axisalignedbb1));
   }

   public void notifyLightSet(BlockPos pos) {
   }

   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
   }

   public void playSoundToAllNearExcept(@Nullable ME player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
   }

   public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
   }

   public void spawnParticle(int id, boolean ignoreRange, boolean minimiseParticleLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
   }

   public void onEntityAdded(Ig entityIn) {
      if (entityIn instanceof Iu) {
         this.navigations.add(((Iu)entityIn).getNavigator());
      }

   }

   public void onEntityRemoved(Ig entityIn) {
      if (entityIn instanceof Iu) {
         this.navigations.remove(((Iu)entityIn).getNavigator());
      }

   }

   public void playRecord(SoundEvent soundIn, BlockPos pos) {
   }

   public void broadcastSound(int soundID, BlockPos pos, int data) {
   }

   public void playEvent(ME player, int type, BlockPos blockPosIn, int data) {
   }

   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
   }
}
