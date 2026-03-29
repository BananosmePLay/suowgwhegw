package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class VT extends VG {
   public VT() {
   }

   public VR getStart() {
      return this.openPoint(MathHelper.floor(this.entity.getEntityBoundingBox().minX), MathHelper.floor(this.entity.getEntityBoundingBox().minY + 0.5), MathHelper.floor(this.entity.getEntityBoundingBox().minZ));
   }

   public VR getPathPointToCoords(double x, double y, double z) {
      return this.openPoint(MathHelper.floor(x - (double)(this.entity.width / 2.0F)), MathHelper.floor(y + 0.5), MathHelper.floor(z - (double)(this.entity.width / 2.0F)));
   }

   public int findPathOptions(VR[] pathOptions, VR currentPoint, VR targetPoint, float maxDistance) {
      int i = 0;
      EnumFacing[] var6 = EnumFacing.values();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         EnumFacing enumfacing = var6[var8];
         VR pathpoint = this.getWaterNode(currentPoint.x + enumfacing.getXOffset(), currentPoint.y + enumfacing.getYOffset(), currentPoint.z + enumfacing.getZOffset());
         if (pathpoint != null && !pathpoint.visited && pathpoint.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint;
         }
      }

      return i;
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z, Iu entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
      return VQ.WATER;
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z) {
      return VQ.WATER;
   }

   @Nullable
   private VR getWaterNode(int p_186328_1_, int p_186328_2_, int p_186328_3_) {
      VQ pathnodetype = this.isFree(p_186328_1_, p_186328_2_, p_186328_3_);
      return pathnodetype == VQ.WATER ? this.openPoint(p_186328_1_, p_186328_2_, p_186328_3_) : null;
   }

   private VQ isFree(int p_186327_1_, int p_186327_2_, int p_186327_3_) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int i = p_186327_1_; i < p_186327_1_ + this.entitySizeX; ++i) {
         for(int j = p_186327_2_; j < p_186327_2_ + this.entitySizeY; ++j) {
            for(int k = p_186327_3_; k < p_186327_3_ + this.entitySizeZ; ++k) {
               in iblockstate = this.blockaccess.getBlockState(blockpos$mutableblockpos.setPos(i, j, k));
               if (iblockstate.getMaterial() != hM.WATER) {
                  return VQ.BLOCKED;
               }
            }
         }
      }

      return VQ.WATER;
   }
}
