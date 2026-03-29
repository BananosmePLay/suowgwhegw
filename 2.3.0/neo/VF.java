package neo;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class VF extends VU {
   public VF() {
   }

   public void init(bfZ sourceIn, Iu mob) {
      super.init(sourceIn, mob);
      this.avoidsWater = mob.getPathPriority(VQ.WATER);
   }

   public void postProcess() {
      this.entity.setPathPriority(VQ.WATER, this.avoidsWater);
      super.postProcess();
   }

   public VR getStart() {
      int i;
      if (this.getCanSwim() && this.entity.isInWater()) {
         i = (int)this.entity.getEntityBoundingBox().minY;
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));

         for(co block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock(); block == Nk.FLOWING_WATER || block == Nk.WATER; block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock()) {
            ++i;
            blockpos$mutableblockpos.setPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));
         }
      } else {
         i = MathHelper.floor(this.entity.getEntityBoundingBox().minY + 0.5);
      }

      BlockPos blockpos1 = new BlockPos(this.entity);
      VQ pathnodetype1 = this.getPathNodeType(this.entity, blockpos1.getX(), i, blockpos1.getZ());
      if (this.entity.getPathPriority(pathnodetype1) < 0.0F) {
         Set<BlockPos> set = Sets.newHashSet();
         set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, (double)i, this.entity.getEntityBoundingBox().minZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, (double)i, this.entity.getEntityBoundingBox().maxZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, (double)i, this.entity.getEntityBoundingBox().minZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, (double)i, this.entity.getEntityBoundingBox().maxZ));
         Iterator var5 = set.iterator();

         while(var5.hasNext()) {
            BlockPos blockpos = (BlockPos)var5.next();
            VQ pathnodetype = this.getPathNodeType(this.entity, blockpos);
            if (this.entity.getPathPriority(pathnodetype) >= 0.0F) {
               return super.openPoint(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }
         }
      }

      return super.openPoint(blockpos1.getX(), i, blockpos1.getZ());
   }

   public VR getPathPointToCoords(double x, double y, double z) {
      return super.openPoint(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
   }

   public int findPathOptions(VR[] pathOptions, VR currentPoint, VR targetPoint, float maxDistance) {
      int i = 0;
      VR pathpoint = this.openPoint(currentPoint.x, currentPoint.y, currentPoint.z + 1);
      VR pathpoint1 = this.openPoint(currentPoint.x - 1, currentPoint.y, currentPoint.z);
      VR pathpoint2 = this.openPoint(currentPoint.x + 1, currentPoint.y, currentPoint.z);
      VR pathpoint3 = this.openPoint(currentPoint.x, currentPoint.y, currentPoint.z - 1);
      VR pathpoint4 = this.openPoint(currentPoint.x, currentPoint.y + 1, currentPoint.z);
      VR pathpoint5 = this.openPoint(currentPoint.x, currentPoint.y - 1, currentPoint.z);
      if (pathpoint != null && !pathpoint.visited && pathpoint.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint;
      }

      if (pathpoint1 != null && !pathpoint1.visited && pathpoint1.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint1;
      }

      if (pathpoint2 != null && !pathpoint2.visited && pathpoint2.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint2;
      }

      if (pathpoint3 != null && !pathpoint3.visited && pathpoint3.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint3;
      }

      if (pathpoint4 != null && !pathpoint4.visited && pathpoint4.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint4;
      }

      if (pathpoint5 != null && !pathpoint5.visited && pathpoint5.distanceTo(targetPoint) < maxDistance) {
         pathOptions[i++] = pathpoint5;
      }

      boolean flag = pathpoint3 == null || pathpoint3.costMalus != 0.0F;
      boolean flag1 = pathpoint == null || pathpoint.costMalus != 0.0F;
      boolean flag2 = pathpoint2 == null || pathpoint2.costMalus != 0.0F;
      boolean flag3 = pathpoint1 == null || pathpoint1.costMalus != 0.0F;
      boolean flag4 = pathpoint4 == null || pathpoint4.costMalus != 0.0F;
      boolean flag5 = pathpoint5 == null || pathpoint5.costMalus != 0.0F;
      VR pathpoint17;
      if (flag && flag3) {
         pathpoint17 = this.openPoint(currentPoint.x - 1, currentPoint.y, currentPoint.z - 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag && flag2) {
         pathpoint17 = this.openPoint(currentPoint.x + 1, currentPoint.y, currentPoint.z - 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag1 && flag3) {
         pathpoint17 = this.openPoint(currentPoint.x - 1, currentPoint.y, currentPoint.z + 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag1 && flag2) {
         pathpoint17 = this.openPoint(currentPoint.x + 1, currentPoint.y, currentPoint.z + 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag && flag4) {
         pathpoint17 = this.openPoint(currentPoint.x, currentPoint.y + 1, currentPoint.z - 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag1 && flag4) {
         pathpoint17 = this.openPoint(currentPoint.x, currentPoint.y + 1, currentPoint.z + 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag2 && flag4) {
         pathpoint17 = this.openPoint(currentPoint.x + 1, currentPoint.y + 1, currentPoint.z);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag3 && flag4) {
         pathpoint17 = this.openPoint(currentPoint.x - 1, currentPoint.y + 1, currentPoint.z);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag && flag5) {
         pathpoint17 = this.openPoint(currentPoint.x, currentPoint.y - 1, currentPoint.z - 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag1 && flag5) {
         pathpoint17 = this.openPoint(currentPoint.x, currentPoint.y - 1, currentPoint.z + 1);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag2 && flag5) {
         pathpoint17 = this.openPoint(currentPoint.x + 1, currentPoint.y - 1, currentPoint.z);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      if (flag3 && flag5) {
         pathpoint17 = this.openPoint(currentPoint.x - 1, currentPoint.y - 1, currentPoint.z);
         if (pathpoint17 != null && !pathpoint17.visited && pathpoint17.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint17;
         }
      }

      return i;
   }

   @Nullable
   protected VR openPoint(int x, int y, int z) {
      VR pathpoint = null;
      VQ pathnodetype = this.getPathNodeType(this.entity, x, y, z);
      float f = this.entity.getPathPriority(pathnodetype);
      if (f >= 0.0F) {
         pathpoint = super.openPoint(x, y, z);
         pathpoint.nodeType = pathnodetype;
         pathpoint.costMalus = Math.max(pathpoint.costMalus, f);
         if (pathnodetype == VQ.WALKABLE) {
            ++pathpoint.costMalus;
         }
      }

      return pathnodetype != VQ.OPEN && pathnodetype != VQ.WALKABLE ? pathpoint : pathpoint;
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z, Iu entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
      EnumSet<VQ> enumset = EnumSet.noneOf(VQ.class);
      VQ pathnodetype = VQ.BLOCKED;
      BlockPos blockpos = new BlockPos(entitylivingIn);
      pathnodetype = this.getPathNodeType(blockaccessIn, x, y, z, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn, enumset, pathnodetype, blockpos);
      if (enumset.contains(VQ.FENCE)) {
         return VQ.FENCE;
      } else {
         VQ pathnodetype1 = VQ.BLOCKED;
         Iterator var15 = enumset.iterator();

         while(var15.hasNext()) {
            VQ pathnodetype2 = (VQ)var15.next();
            if (entitylivingIn.getPathPriority(pathnodetype2) < 0.0F) {
               return pathnodetype2;
            }

            if (entitylivingIn.getPathPriority(pathnodetype2) >= entitylivingIn.getPathPriority(pathnodetype1)) {
               pathnodetype1 = pathnodetype2;
            }
         }

         if (pathnodetype == VQ.OPEN && entitylivingIn.getPathPriority(pathnodetype1) == 0.0F) {
            return VQ.OPEN;
         } else {
            return pathnodetype1;
         }
      }
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z) {
      VQ pathnodetype = this.getPathNodeTypeRaw(blockaccessIn, x, y, z);
      if (pathnodetype == VQ.OPEN && y >= 1) {
         co block = blockaccessIn.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
         VQ pathnodetype1 = this.getPathNodeTypeRaw(blockaccessIn, x, y - 1, z);
         if (pathnodetype1 != VQ.DAMAGE_FIRE && block != Nk.MAGMA && pathnodetype1 != VQ.LAVA) {
            if (pathnodetype1 == VQ.DAMAGE_CACTUS) {
               pathnodetype = VQ.DAMAGE_CACTUS;
            } else {
               pathnodetype = pathnodetype1 != VQ.WALKABLE && pathnodetype1 != VQ.OPEN && pathnodetype1 != VQ.WATER ? VQ.WALKABLE : VQ.OPEN;
            }
         } else {
            pathnodetype = VQ.DAMAGE_FIRE;
         }
      }

      pathnodetype = this.checkNeighborBlocks(blockaccessIn, x, y, z, pathnodetype);
      return pathnodetype;
   }

   private VQ getPathNodeType(Iu p_192559_1_, BlockPos p_192559_2_) {
      return this.getPathNodeType(p_192559_1_, p_192559_2_.getX(), p_192559_2_.getY(), p_192559_2_.getZ());
   }

   private VQ getPathNodeType(Iu p_192558_1_, int p_192558_2_, int p_192558_3_, int p_192558_4_) {
      return this.getPathNodeType(this.blockaccess, p_192558_2_, p_192558_3_, p_192558_4_, p_192558_1_, this.entitySizeX, this.entitySizeY, this.entitySizeZ, this.getCanOpenDoors(), this.getCanEnterDoors());
   }
}
