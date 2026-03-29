package neo;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class VU extends VG {
   protected float avoidsWater;

   public VU() {
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
      BlockPos blockpos;
      if (this.getCanSwim() && this.entity.isInWater()) {
         i = (int)this.entity.getEntityBoundingBox().minY;
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));

         for(co block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock(); block == Nk.FLOWING_WATER || block == Nk.WATER; block = this.blockaccess.getBlockState(blockpos$mutableblockpos).getBlock()) {
            ++i;
            blockpos$mutableblockpos.setPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ));
         }
      } else if (this.entity.onGround) {
         i = MathHelper.floor(this.entity.getEntityBoundingBox().minY + 0.5);
      } else {
         for(blockpos = new BlockPos(this.entity); (this.blockaccess.getBlockState(blockpos).getMaterial() == hM.AIR || this.blockaccess.getBlockState(blockpos).getBlock().isPassable(this.blockaccess, blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down()) {
         }

         i = blockpos.up().getY();
      }

      blockpos = new BlockPos(this.entity);
      VQ pathnodetype1 = this.getPathNodeType(this.entity, blockpos.getX(), i, blockpos.getZ());
      if (this.entity.getPathPriority(pathnodetype1) < 0.0F) {
         Set<BlockPos> set = Sets.newHashSet();
         set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, (double)i, this.entity.getEntityBoundingBox().minZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().minX, (double)i, this.entity.getEntityBoundingBox().maxZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, (double)i, this.entity.getEntityBoundingBox().minZ));
         set.add(new BlockPos(this.entity.getEntityBoundingBox().maxX, (double)i, this.entity.getEntityBoundingBox().maxZ));
         Iterator var5 = set.iterator();

         while(var5.hasNext()) {
            BlockPos blockpos1 = (BlockPos)var5.next();
            VQ pathnodetype = this.getPathNodeType(this.entity, blockpos1);
            if (this.entity.getPathPriority(pathnodetype) >= 0.0F) {
               return this.openPoint(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
            }
         }
      }

      return this.openPoint(blockpos.getX(), i, blockpos.getZ());
   }

   public VR getPathPointToCoords(double x, double y, double z) {
      return this.openPoint(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
   }

   public int findPathOptions(VR[] pathOptions, VR currentPoint, VR targetPoint, float maxDistance) {
      int i = 0;
      int j = 0;
      VQ pathnodetype = this.getPathNodeType(this.entity, currentPoint.x, currentPoint.y + 1, currentPoint.z);
      if (this.entity.getPathPriority(pathnodetype) >= 0.0F) {
         j = MathHelper.floor(Math.max(1.0F, this.entity.stepHeight));
      }

      BlockPos blockpos = (new BlockPos(currentPoint.x, currentPoint.y, currentPoint.z)).down();
      double d0 = (double)currentPoint.y - (1.0 - this.blockaccess.getBlockState(blockpos).getBoundingBox(this.blockaccess, blockpos).maxY);
      VR pathpoint = this.getSafePoint(currentPoint.x, currentPoint.y, currentPoint.z + 1, j, d0, EnumFacing.SOUTH);
      VR pathpoint1 = this.getSafePoint(currentPoint.x - 1, currentPoint.y, currentPoint.z, j, d0, EnumFacing.WEST);
      VR pathpoint2 = this.getSafePoint(currentPoint.x + 1, currentPoint.y, currentPoint.z, j, d0, EnumFacing.EAST);
      VR pathpoint3 = this.getSafePoint(currentPoint.x, currentPoint.y, currentPoint.z - 1, j, d0, EnumFacing.NORTH);
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

      boolean flag = pathpoint3 == null || pathpoint3.nodeType == VQ.OPEN || pathpoint3.costMalus != 0.0F;
      boolean flag1 = pathpoint == null || pathpoint.nodeType == VQ.OPEN || pathpoint.costMalus != 0.0F;
      boolean flag2 = pathpoint2 == null || pathpoint2.nodeType == VQ.OPEN || pathpoint2.costMalus != 0.0F;
      boolean flag3 = pathpoint1 == null || pathpoint1.nodeType == VQ.OPEN || pathpoint1.costMalus != 0.0F;
      VR pathpoint7;
      if (flag && flag3) {
         pathpoint7 = this.getSafePoint(currentPoint.x - 1, currentPoint.y, currentPoint.z - 1, j, d0, EnumFacing.NORTH);
         if (pathpoint7 != null && !pathpoint7.visited && pathpoint7.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint7;
         }
      }

      if (flag && flag2) {
         pathpoint7 = this.getSafePoint(currentPoint.x + 1, currentPoint.y, currentPoint.z - 1, j, d0, EnumFacing.NORTH);
         if (pathpoint7 != null && !pathpoint7.visited && pathpoint7.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint7;
         }
      }

      if (flag1 && flag3) {
         pathpoint7 = this.getSafePoint(currentPoint.x - 1, currentPoint.y, currentPoint.z + 1, j, d0, EnumFacing.SOUTH);
         if (pathpoint7 != null && !pathpoint7.visited && pathpoint7.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint7;
         }
      }

      if (flag1 && flag2) {
         pathpoint7 = this.getSafePoint(currentPoint.x + 1, currentPoint.y, currentPoint.z + 1, j, d0, EnumFacing.SOUTH);
         if (pathpoint7 != null && !pathpoint7.visited && pathpoint7.distanceTo(targetPoint) < maxDistance) {
            pathOptions[i++] = pathpoint7;
         }
      }

      return i;
   }

   @Nullable
   private VR getSafePoint(int x, int y, int z, int p_186332_4_, double p_186332_5_, EnumFacing facing) {
      VR pathpoint = null;
      BlockPos blockpos = new BlockPos(x, y, z);
      BlockPos blockpos1 = blockpos.down();
      double d0 = (double)y - (1.0 - this.blockaccess.getBlockState(blockpos1).getBoundingBox(this.blockaccess, blockpos1).maxY);
      if (d0 - p_186332_5_ > 1.125) {
         return null;
      } else {
         VQ pathnodetype = this.getPathNodeType(this.entity, x, y, z);
         float f = this.entity.getPathPriority(pathnodetype);
         double d1 = (double)this.entity.width / 2.0;
         if (f >= 0.0F) {
            pathpoint = this.openPoint(x, y, z);
            pathpoint.nodeType = pathnodetype;
            pathpoint.costMalus = Math.max(pathpoint.costMalus, f);
         }

         if (pathnodetype == VQ.WALKABLE) {
            return pathpoint;
         } else {
            if (pathpoint == null && p_186332_4_ > 0 && pathnodetype != VQ.FENCE && pathnodetype != VQ.TRAPDOOR) {
               pathpoint = this.getSafePoint(x, y + 1, z, p_186332_4_ - 1, p_186332_5_, facing);
               if (pathpoint != null && (pathpoint.nodeType == VQ.OPEN || pathpoint.nodeType == VQ.WALKABLE) && this.entity.width < 1.0F) {
                  double d2 = (double)(x - facing.getXOffset()) + 0.5;
                  double d3 = (double)(z - facing.getZOffset()) + 0.5;
                  AxisAlignedBB axisalignedbb = new AxisAlignedBB(d2 - d1, (double)y + 0.001, d3 - d1, d2 + d1, (double)((float)y + this.entity.height), d3 + d1);
                  AxisAlignedBB axisalignedbb1 = this.blockaccess.getBlockState(blockpos).getBoundingBox(this.blockaccess, blockpos);
                  AxisAlignedBB axisalignedbb2 = axisalignedbb.expand(0.0, axisalignedbb1.maxY - 0.002, 0.0);
                  if (this.entity.world.collidesWithAnyBlock(axisalignedbb2)) {
                     pathpoint = null;
                  }
               }
            }

            if (pathnodetype == VQ.OPEN) {
               AxisAlignedBB axisalignedbb3 = new AxisAlignedBB((double)x - d1 + 0.5, (double)y + 0.001, (double)z - d1 + 0.5, (double)x + d1 + 0.5, (double)((float)y + this.entity.height), (double)z + d1 + 0.5);
               if (this.entity.world.collidesWithAnyBlock(axisalignedbb3)) {
                  return null;
               }

               if (this.entity.width >= 1.0F) {
                  VQ pathnodetype1 = this.getPathNodeType(this.entity, x, y - 1, z);
                  if (pathnodetype1 == VQ.BLOCKED) {
                     pathpoint = this.openPoint(x, y, z);
                     pathpoint.nodeType = VQ.WALKABLE;
                     pathpoint.costMalus = Math.max(pathpoint.costMalus, f);
                     return pathpoint;
                  }
               }

               int i = 0;

               while(y > 0 && pathnodetype == VQ.OPEN) {
                  --y;
                  if (i++ >= this.entity.getMaxFallHeight()) {
                     return null;
                  }

                  pathnodetype = this.getPathNodeType(this.entity, x, y, z);
                  f = this.entity.getPathPriority(pathnodetype);
                  if (pathnodetype != VQ.OPEN && f >= 0.0F) {
                     pathpoint = this.openPoint(x, y, z);
                     pathpoint.nodeType = pathnodetype;
                     pathpoint.costMalus = Math.max(pathpoint.costMalus, f);
                     break;
                  }

                  if (f < 0.0F) {
                     return null;
                  }
               }
            }

            return pathpoint;
         }
      }
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z, Iu entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
      EnumSet<VQ> enumset = EnumSet.noneOf(VQ.class);
      VQ pathnodetype = VQ.BLOCKED;
      double d0 = (double)entitylivingIn.width / 2.0;
      BlockPos blockpos = new BlockPos(entitylivingIn);
      pathnodetype = this.getPathNodeType(blockaccessIn, x, y, z, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn, enumset, pathnodetype, blockpos);
      if (enumset.contains(VQ.FENCE)) {
         return VQ.FENCE;
      } else {
         VQ pathnodetype1 = VQ.BLOCKED;
         Iterator var17 = enumset.iterator();

         while(var17.hasNext()) {
            VQ pathnodetype2 = (VQ)var17.next();
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

   public VQ getPathNodeType(bfZ p_193577_1_, int x, int y, int z, int xSize, int ySize, int zSize, boolean canOpenDoorsIn, boolean canEnterDoorsIn, EnumSet<VQ> p_193577_10_, VQ p_193577_11_, BlockPos p_193577_12_) {
      for(int i = 0; i < xSize; ++i) {
         for(int j = 0; j < ySize; ++j) {
            for(int k = 0; k < zSize; ++k) {
               int l = i + x;
               int i1 = j + y;
               int j1 = k + z;
               VQ pathnodetype = this.getPathNodeType(p_193577_1_, l, i1, j1);
               if (pathnodetype == VQ.DOOR_WOOD_CLOSED && canOpenDoorsIn && canEnterDoorsIn) {
                  pathnodetype = VQ.WALKABLE;
               }

               if (pathnodetype == VQ.DOOR_OPEN && !canEnterDoorsIn) {
                  pathnodetype = VQ.BLOCKED;
               }

               if (pathnodetype == VQ.RAIL && !(p_193577_1_.getBlockState(p_193577_12_).getBlock() instanceof fK) && !(p_193577_1_.getBlockState(p_193577_12_.down()).getBlock() instanceof fK)) {
                  pathnodetype = VQ.FENCE;
               }

               if (i == 0 && j == 0 && k == 0) {
                  p_193577_11_ = pathnodetype;
               }

               p_193577_10_.add(pathnodetype);
            }
         }
      }

      return p_193577_11_;
   }

   private VQ getPathNodeType(Iu entitylivingIn, BlockPos pos) {
      return this.getPathNodeType(entitylivingIn, pos.getX(), pos.getY(), pos.getZ());
   }

   private VQ getPathNodeType(Iu entitylivingIn, int x, int y, int z) {
      return this.getPathNodeType(this.blockaccess, x, y, z, entitylivingIn, this.entitySizeX, this.entitySizeY, this.entitySizeZ, this.getCanOpenDoors(), this.getCanEnterDoors());
   }

   public VQ getPathNodeType(bfZ blockaccessIn, int x, int y, int z) {
      VQ pathnodetype = this.getPathNodeTypeRaw(blockaccessIn, x, y, z);
      if (pathnodetype == VQ.OPEN && y >= 1) {
         co block = blockaccessIn.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
         VQ pathnodetype1 = this.getPathNodeTypeRaw(blockaccessIn, x, y - 1, z);
         pathnodetype = pathnodetype1 != VQ.WALKABLE && pathnodetype1 != VQ.OPEN && pathnodetype1 != VQ.WATER && pathnodetype1 != VQ.LAVA ? VQ.WALKABLE : VQ.OPEN;
         if (pathnodetype1 == VQ.DAMAGE_FIRE || block == Nk.MAGMA) {
            pathnodetype = VQ.DAMAGE_FIRE;
         }

         if (pathnodetype1 == VQ.DAMAGE_CACTUS) {
            pathnodetype = VQ.DAMAGE_CACTUS;
         }
      }

      pathnodetype = this.checkNeighborBlocks(blockaccessIn, x, y, z, pathnodetype);
      return pathnodetype;
   }

   public VQ checkNeighborBlocks(bfZ p_193578_1_, int p_193578_2_, int p_193578_3_, int p_193578_4_, VQ p_193578_5_) {
      BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
      if (p_193578_5_ == VQ.WALKABLE) {
         for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
               if (i != 0 || j != 0) {
                  co block = p_193578_1_.getBlockState(blockpos$pooledmutableblockpos.setPos(i + p_193578_2_, p_193578_3_, j + p_193578_4_)).getBlock();
                  if (block == Nk.CACTUS) {
                     p_193578_5_ = VQ.DANGER_CACTUS;
                  } else if (block == Nk.FIRE) {
                     p_193578_5_ = VQ.DANGER_FIRE;
                  }
               }
            }
         }
      }

      blockpos$pooledmutableblockpos.release();
      return p_193578_5_;
   }

   protected VQ getPathNodeTypeRaw(bfZ p_189553_1_, int p_189553_2_, int p_189553_3_, int p_189553_4_) {
      BlockPos blockpos = new BlockPos(p_189553_2_, p_189553_3_, p_189553_4_);
      in iblockstate = p_189553_1_.getBlockState(blockpos);
      co block = iblockstate.getBlock();
      hM material = iblockstate.getMaterial();
      if (material == hM.AIR) {
         return VQ.OPEN;
      } else if (block != Nk.TRAPDOOR && block != Nk.IRON_TRAPDOOR && block != Nk.WATERLILY) {
         if (block == Nk.FIRE) {
            return VQ.DAMAGE_FIRE;
         } else if (block == Nk.CACTUS) {
            return VQ.DAMAGE_CACTUS;
         } else if (block instanceof do && material == hM.WOOD && !(Boolean)iblockstate.getValue(do.OPEN)) {
            return VQ.DOOR_WOOD_CLOSED;
         } else if (block instanceof do && material == hM.IRON && !(Boolean)iblockstate.getValue(do.OPEN)) {
            return VQ.DOOR_IRON_CLOSED;
         } else if (block instanceof do && (Boolean)iblockstate.getValue(do.OPEN)) {
            return VQ.DOOR_OPEN;
         } else if (block instanceof fK) {
            return VQ.RAIL;
         } else if (!(block instanceof dL) && !(block instanceof hz) && (!(block instanceof dM) || (Boolean)iblockstate.getValue(dM.OPEN))) {
            if (material == hM.WATER) {
               return VQ.WATER;
            } else if (material == hM.LAVA) {
               return VQ.LAVA;
            } else {
               return block.isPassable(p_189553_1_, blockpos) ? VQ.OPEN : VQ.BLOCKED;
            }
         } else {
            return VQ.FENCE;
         }
      } else {
         return VQ.TRAPDOOR;
      }
   }
}
