package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fN extends fK {
   public static final hX<fI> SHAPE = hX.create("shape", fI.class, new Predicate<fI>() {
      public boolean apply(@Nullable fI p_apply_1_) {
         return p_apply_1_ != fI.NORTH_EAST && p_apply_1_ != fI.NORTH_WEST && p_apply_1_ != fI.SOUTH_EAST && p_apply_1_ != fI.SOUTH_WEST;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((fI)var1);
      }
   });
   public static final hV POWERED = hV.create("powered");

   public fN() {
      super(true);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false).withProperty(SHAPE, fI.NORTH_SOUTH));
      this.setTickRandomly(true);
   }

   public int tickRate(bij worldIn) {
      return 20;
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote && !(Boolean)state.getValue(POWERED)) {
         this.updatePoweredState(worldIn, pos, state);
      }

   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote && (Boolean)state.getValue(POWERED)) {
         this.updatePoweredState(worldIn, pos, state);
      }

   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return (Boolean)blockState.getValue(POWERED) ? 15 : 0;
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (!(Boolean)blockState.getValue(POWERED)) {
         return 0;
      } else {
         return side == EnumFacing.UP ? 15 : 0;
      }
   }

   private void updatePoweredState(bij worldIn, BlockPos pos, in state) {
      boolean flag = (Boolean)state.getValue(POWERED);
      boolean flag1 = false;
      List<Jc> list = this.findMinecarts(worldIn, pos, Jc.class);
      if (!list.isEmpty()) {
         flag1 = true;
      }

      if (flag1 && !flag) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, true), 3);
         this.updateConnectedRails(worldIn, pos, state, true);
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (!flag1 && flag) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, false), 3);
         this.updateConnectedRails(worldIn, pos, state, false);
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (flag1) {
         worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
      }

      worldIn.updateComparatorOutputLevel(pos, this);
   }

   protected void updateConnectedRails(bij worldIn, BlockPos pos, in state, boolean powered) {
      fJ blockrailbase$rail = new fJ(this, worldIn, pos, state);
      Iterator var6 = blockrailbase$rail.getConnectedRails().iterator();

      while(var6.hasNext()) {
         BlockPos blockpos = (BlockPos)var6.next();
         in iblockstate = worldIn.getBlockState(blockpos);
         if (iblockstate != null) {
            iblockstate.neighborChanged(worldIn, blockpos, iblockstate.getBlock(), pos);
         }
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      this.updatePoweredState(worldIn, pos, state);
   }

   public hT<fI> getShapeProperty() {
      return SHAPE;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      if ((Boolean)blockState.getValue(POWERED)) {
         List<Jg> list = this.findMinecarts(worldIn, pos, Jg.class);
         if (!list.isEmpty()) {
            return ((Jg)list.get(0)).getCommandBlockLogic().getSuccessCount();
         }

         List<Jc> list1 = this.findMinecarts(worldIn, pos, Jc.class, EntitySelectors.HAS_INVENTORY);
         if (!list1.isEmpty()) {
            return Container.calcRedstoneFromInventory((IInventory)list1.get(0));
         }
      }

      return 0;
   }

   protected <T extends Jc> List<T> findMinecarts(bij worldIn, BlockPos pos, Class<T> clazz, Predicate<Ig>... filter) {
      AxisAlignedBB axisalignedbb = this.getDectectionBox(pos);
      return filter.length != 1 ? worldIn.getEntitiesWithinAABB(clazz, axisalignedbb) : worldIn.getEntitiesWithinAABB(clazz, axisalignedbb, filter[0]);
   }

   private AxisAlignedBB getDectectionBox(BlockPos pos) {
      float f = 0.2F;
      return new AxisAlignedBB((double)((float)pos.getX() + 0.2F), (double)pos.getY(), (double)((float)pos.getZ() + 0.2F), (double)((float)(pos.getX() + 1) - 0.2F), (double)((float)(pos.getY() + 1) - 0.2F), (double)((float)(pos.getZ() + 1) - 0.2F));
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(SHAPE, fI.byMetadata(meta & 7)).withProperty(POWERED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fI)state.getValue(SHAPE)).getMetadata();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      return i;
   }

   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
            }
         case COUNTERCLOCKWISE_90:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
            }
         case CLOCKWISE_90:
            switch ((fI)state.getValue(SHAPE)) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_SOUTH:
                  return state.withProperty(SHAPE, fI.EAST_WEST);
               case EAST_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_SOUTH);
            }
         default:
            return state;
      }
   }

   public in withMirror(in state, Mirror mirrorIn) {
      fI blockrailbase$enumraildirection = (fI)state.getValue(SHAPE);
      switch (mirrorIn) {
         case LEFT_RIGHT:
            switch (blockrailbase$enumraildirection) {
               case ASCENDING_NORTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_SOUTH);
               case ASCENDING_SOUTH:
                  return state.withProperty(SHAPE, fI.ASCENDING_NORTH);
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               default:
                  return super.withMirror(state, mirrorIn);
            }
         case FRONT_BACK:
            switch (blockrailbase$enumraildirection) {
               case ASCENDING_EAST:
                  return state.withProperty(SHAPE, fI.ASCENDING_WEST);
               case ASCENDING_WEST:
                  return state.withProperty(SHAPE, fI.ASCENDING_EAST);
               case ASCENDING_NORTH:
               case ASCENDING_SOUTH:
               default:
                  break;
               case SOUTH_EAST:
                  return state.withProperty(SHAPE, fI.SOUTH_WEST);
               case SOUTH_WEST:
                  return state.withProperty(SHAPE, fI.SOUTH_EAST);
               case NORTH_WEST:
                  return state.withProperty(SHAPE, fI.NORTH_EAST);
               case NORTH_EAST:
                  return state.withProperty(SHAPE, fI.NORTH_WEST);
            }
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{SHAPE, POWERED});
   }
}
