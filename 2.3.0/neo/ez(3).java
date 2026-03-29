package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ez extends co {
   public static final hX<ey> FACING = hX.create("facing", ey.class);
   public static final hV POWERED = hV.create("powered");
   protected static final AxisAlignedBB LEVER_NORTH_AABB = new AxisAlignedBB(0.3125, 0.20000000298023224, 0.625, 0.6875, 0.800000011920929, 1.0);
   protected static final AxisAlignedBB LEVER_SOUTH_AABB = new AxisAlignedBB(0.3125, 0.20000000298023224, 0.0, 0.6875, 0.800000011920929, 0.375);
   protected static final AxisAlignedBB LEVER_WEST_AABB = new AxisAlignedBB(0.625, 0.20000000298023224, 0.3125, 1.0, 0.800000011920929, 0.6875);
   protected static final AxisAlignedBB LEVER_EAST_AABB = new AxisAlignedBB(0.0, 0.20000000298023224, 0.3125, 0.375, 0.800000011920929, 0.6875);
   protected static final AxisAlignedBB LEVER_UP_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.6000000238418579, 0.75);
   protected static final AxisAlignedBB LEVER_DOWN_AABB = new AxisAlignedBB(0.25, 0.4000000059604645, 0.25, 0.75, 1.0, 0.75);

   protected ez() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, ey.NORTH).withProperty(POWERED, false));
      this.setCreativeTab(EN.REDSTONE);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return canAttachTo(worldIn, pos, side);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      EnumFacing[] var3 = EnumFacing.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFacing enumfacing = var3[var5];
         if (canAttachTo(worldIn, pos, enumfacing)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean canAttachTo(bij worldIn, BlockPos p_181090_1_, EnumFacing p_181090_2_) {
      return cK.canPlaceBlock(worldIn, p_181090_1_, p_181090_2_);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = this.getDefaultState().withProperty(POWERED, false);
      if (canAttachTo(worldIn, pos, facing)) {
         return iblockstate.withProperty(FACING, ey.forFacings(facing, placer.getHorizontalFacing()));
      } else {
         Iterator var10 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing;
         do {
            if (!var10.hasNext()) {
               if (worldIn.getBlockState(pos.down()).isTopSolid()) {
                  return iblockstate.withProperty(FACING, ey.forFacings(EnumFacing.UP, placer.getHorizontalFacing()));
               }

               return iblockstate;
            }

            enumfacing = (EnumFacing)var10.next();
         } while(enumfacing == facing || !canAttachTo(worldIn, pos, enumfacing));

         return iblockstate.withProperty(FACING, ey.forFacings(enumfacing, placer.getHorizontalFacing()));
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (this.checkCanSurvive(worldIn, pos, state) && !canAttachTo(worldIn, pos, ((ey)state.getValue(FACING)).getFacing())) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean checkCanSurvive(bij worldIn, BlockPos pos, in state) {
      if (this.canPlaceBlockAt(worldIn, pos)) {
         return true;
      } else {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         return false;
      }
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((ey)state.getValue(FACING)) {
         case EAST:
         default:
            return LEVER_EAST_AABB;
         case WEST:
            return LEVER_WEST_AABB;
         case SOUTH:
            return LEVER_SOUTH_AABB;
         case NORTH:
            return LEVER_NORTH_AABB;
         case UP_Z:
         case UP_X:
            return LEVER_UP_AABB;
         case DOWN_X:
         case DOWN_Z:
            return LEVER_DOWN_AABB;
      }
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         state = state.cycleProperty(POWERED);
         worldIn.setBlockState(pos, state, 3);
         float f = (Boolean)state.getValue(POWERED) ? 0.6F : 0.5F;
         worldIn.playSound((ME)null, pos, NO.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         EnumFacing enumfacing = ((ey)state.getValue(FACING)).getFacing();
         worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing.getOpposite()), this, false);
         return true;
      }
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if ((Boolean)state.getValue(POWERED)) {
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         EnumFacing enumfacing = ((ey)state.getValue(FACING)).getFacing();
         worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing.getOpposite()), this, false);
      }

      super.breakBlock(worldIn, pos, state);
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
         return ((ey)blockState.getValue(FACING)).getFacing() == side ? 15 : 0;
      }
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, ey.byMetadata(meta & 7)).withProperty(POWERED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((ey)state.getValue(FACING)).getMetadata();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            switch ((ey)state.getValue(FACING)) {
               case EAST:
                  return state.withProperty(FACING, ey.WEST);
               case WEST:
                  return state.withProperty(FACING, ey.EAST);
               case SOUTH:
                  return state.withProperty(FACING, ey.NORTH);
               case NORTH:
                  return state.withProperty(FACING, ey.SOUTH);
               default:
                  return state;
            }
         case COUNTERCLOCKWISE_90:
            switch ((ey)state.getValue(FACING)) {
               case EAST:
                  return state.withProperty(FACING, ey.NORTH);
               case WEST:
                  return state.withProperty(FACING, ey.SOUTH);
               case SOUTH:
                  return state.withProperty(FACING, ey.EAST);
               case NORTH:
                  return state.withProperty(FACING, ey.WEST);
               case UP_Z:
                  return state.withProperty(FACING, ey.UP_X);
               case UP_X:
                  return state.withProperty(FACING, ey.UP_Z);
               case DOWN_X:
                  return state.withProperty(FACING, ey.DOWN_Z);
               case DOWN_Z:
                  return state.withProperty(FACING, ey.DOWN_X);
            }
         case CLOCKWISE_90:
            switch ((ey)state.getValue(FACING)) {
               case EAST:
                  return state.withProperty(FACING, ey.SOUTH);
               case WEST:
                  return state.withProperty(FACING, ey.NORTH);
               case SOUTH:
                  return state.withProperty(FACING, ey.WEST);
               case NORTH:
                  return state.withProperty(FACING, ey.EAST);
               case UP_Z:
                  return state.withProperty(FACING, ey.UP_X);
               case UP_X:
                  return state.withProperty(FACING, ey.UP_Z);
               case DOWN_X:
                  return state.withProperty(FACING, ey.DOWN_Z);
               case DOWN_Z:
                  return state.withProperty(FACING, ey.DOWN_X);
            }
         default:
            return state;
      }
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation(((ey)state.getValue(FACING)).getFacing()));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, POWERED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
