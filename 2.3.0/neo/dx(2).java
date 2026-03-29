package neo;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class dx extends eB {
   int adjacentSourceBlocks;

   protected dx(hM materialIn) {
      super(materialIn);
   }

   private void placeStaticBlock(bij worldIn, BlockPos pos, in currentState) {
      worldIn.setBlockState(pos, getStaticBlock(this.material).getDefaultState().withProperty(LEVEL, currentState.getValue(LEVEL)), 2);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      int i = (Integer)state.getValue(LEVEL);
      int j = 1;
      if (this.material == hM.LAVA && !worldIn.provider.doesWaterVaporize()) {
         j = 2;
      }

      int k = this.tickRate(worldIn);
      int k1;
      if (i > 0) {
         int l = -100;
         this.adjacentSourceBlocks = 0;

         EnumFacing enumfacing;
         for(Iterator var9 = EnumFacing.Plane.HORIZONTAL.iterator(); var9.hasNext(); l = this.checkAdjacentBlock(worldIn, pos.offset(enumfacing), l)) {
            enumfacing = (EnumFacing)var9.next();
         }

         int i1 = l + j;
         if (i1 >= 8 || l < 0) {
            i1 = -1;
         }

         k1 = this.getDepth(worldIn.getBlockState(pos.up()));
         if (k1 >= 0) {
            if (k1 >= 8) {
               i1 = k1;
            } else {
               i1 = k1 + 8;
            }
         }

         if (this.adjacentSourceBlocks >= 2 && this.material == hM.WATER) {
            in iblockstate = worldIn.getBlockState(pos.down());
            if (iblockstate.getMaterial().isSolid()) {
               i1 = 0;
            } else if (iblockstate.getMaterial() == this.material && (Integer)iblockstate.getValue(LEVEL) == 0) {
               i1 = 0;
            }
         }

         if (this.material == hM.LAVA && i < 8 && i1 < 8 && i1 > i && rand.nextInt(4) != 0) {
            k *= 4;
         }

         if (i1 == i) {
            this.placeStaticBlock(worldIn, pos, state);
         } else {
            i = i1;
            if (i1 < 0) {
               worldIn.setBlockToAir(pos);
            } else {
               state = state.withProperty(LEVEL, i1);
               worldIn.setBlockState(pos, state, 2);
               worldIn.scheduleUpdate(pos, this, k);
               worldIn.notifyNeighborsOfStateChange(pos, this, false);
            }
         }
      } else {
         this.placeStaticBlock(worldIn, pos, state);
      }

      in iblockstate1 = worldIn.getBlockState(pos.down());
      if (this.canFlowInto(worldIn, pos.down(), iblockstate1)) {
         if (this.material == hM.LAVA && worldIn.getBlockState(pos.down()).getMaterial() == hM.WATER) {
            worldIn.setBlockState(pos.down(), Nk.STONE.getDefaultState());
            this.triggerMixEffects(worldIn, pos.down());
            return;
         }

         if (i >= 8) {
            this.tryFlowInto(worldIn, pos.down(), iblockstate1, i);
         } else {
            this.tryFlowInto(worldIn, pos.down(), iblockstate1, i + 8);
         }
      } else if (i >= 0 && (i == 0 || this.isBlocked(worldIn, pos.down(), iblockstate1))) {
         Set<EnumFacing> set = this.getPossibleFlowDirections(worldIn, pos);
         k1 = i + j;
         if (i >= 8) {
            k1 = 1;
         }

         if (k1 >= 8) {
            return;
         }

         Iterator var16 = set.iterator();

         while(var16.hasNext()) {
            EnumFacing enumfacing1 = (EnumFacing)var16.next();
            this.tryFlowInto(worldIn, pos.offset(enumfacing1), worldIn.getBlockState(pos.offset(enumfacing1)), k1);
         }
      }

   }

   private void tryFlowInto(bij worldIn, BlockPos pos, in state, int level) {
      if (this.canFlowInto(worldIn, pos, state)) {
         if (state.getMaterial() != hM.AIR) {
            if (this.material == hM.LAVA) {
               this.triggerMixEffects(worldIn, pos);
            } else {
               state.getBlock().dropBlockAsItem(worldIn, pos, state, 0);
            }
         }

         worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, level), 3);
      }

   }

   private int getSlopeDistance(bij worldIn, BlockPos pos, int distance, EnumFacing calculateFlowCost) {
      int i = 1000;
      Iterator var6 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         EnumFacing enumfacing;
         BlockPos blockpos;
         in iblockstate;
         do {
            do {
               do {
                  if (!var6.hasNext()) {
                     return i;
                  }

                  enumfacing = (EnumFacing)var6.next();
               } while(enumfacing == calculateFlowCost);

               blockpos = pos.offset(enumfacing);
               iblockstate = worldIn.getBlockState(blockpos);
            } while(this.isBlocked(worldIn, blockpos, iblockstate));
         } while(iblockstate.getMaterial() == this.material && (Integer)iblockstate.getValue(LEVEL) <= 0);

         if (!this.isBlocked(worldIn, blockpos.down(), iblockstate)) {
            return distance;
         }

         if (distance < this.getSlopeFindDistance(worldIn)) {
            int j = this.getSlopeDistance(worldIn, blockpos, distance + 1, enumfacing.getOpposite());
            if (j < i) {
               i = j;
            }
         }
      }
   }

   private int getSlopeFindDistance(bij worldIn) {
      return this.material == hM.LAVA && !worldIn.provider.doesWaterVaporize() ? 2 : 4;
   }

   private Set<EnumFacing> getPossibleFlowDirections(bij worldIn, BlockPos pos) {
      int i = 1000;
      Set<EnumFacing> set = EnumSet.noneOf(EnumFacing.class);
      Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         EnumFacing enumfacing;
         BlockPos blockpos;
         in iblockstate;
         do {
            do {
               if (!var5.hasNext()) {
                  return set;
               }

               enumfacing = (EnumFacing)var5.next();
               blockpos = pos.offset(enumfacing);
               iblockstate = worldIn.getBlockState(blockpos);
            } while(this.isBlocked(worldIn, blockpos, iblockstate));
         } while(iblockstate.getMaterial() == this.material && (Integer)iblockstate.getValue(LEVEL) <= 0);

         int j;
         if (this.isBlocked(worldIn, blockpos.down(), worldIn.getBlockState(blockpos.down()))) {
            j = this.getSlopeDistance(worldIn, blockpos, 1, enumfacing.getOpposite());
         } else {
            j = 0;
         }

         if (j < i) {
            set.clear();
         }

         if (j <= i) {
            set.add(enumfacing);
            i = j;
         }
      }
   }

   private boolean isBlocked(bij worldIn, BlockPos pos, in state) {
      co block = worldIn.getBlockState(pos).getBlock();
      if (!(block instanceof do) && block != Nk.STANDING_SIGN && block != Nk.LADDER && block != Nk.REEDS) {
         return block.material != hM.PORTAL && block.material != hM.STRUCTURE_VOID ? block.material.blocksMovement() : true;
      } else {
         return true;
      }
   }

   protected int checkAdjacentBlock(bij worldIn, BlockPos pos, int currentMinLevel) {
      int i = this.getDepth(worldIn.getBlockState(pos));
      if (i < 0) {
         return currentMinLevel;
      } else {
         if (i == 0) {
            ++this.adjacentSourceBlocks;
         }

         if (i >= 8) {
            i = 0;
         }

         return currentMinLevel >= 0 && i >= currentMinLevel ? currentMinLevel : i;
      }
   }

   private boolean canFlowInto(bij worldIn, BlockPos pos, in state) {
      hM material = state.getMaterial();
      return material != this.material && material != hM.LAVA && !this.isBlocked(worldIn, pos, state);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!this.checkForMixing(worldIn, pos, state)) {
         worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
      }

   }
}
