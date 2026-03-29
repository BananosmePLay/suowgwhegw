package neo;

import java.util.Iterator;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ev extends co {
   public static final hW FACING;
   protected static final AxisAlignedBB LADDER_EAST_AABB;
   protected static final AxisAlignedBB LADDER_WEST_AABB;
   protected static final AxisAlignedBB LADDER_SOUTH_AABB;
   protected static final AxisAlignedBB LADDER_NORTH_AABB;

   protected ev() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case NORTH:
            return LADDER_NORTH_AABB;
         case SOUTH:
            return LADDER_SOUTH_AABB;
         case WEST:
            return LADDER_WEST_AABB;
         case EAST:
         default:
            return LADDER_EAST_AABB;
      }
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
      if (this.canAttachTo(worldIn, pos.west(), side)) {
         return true;
      } else if (this.canAttachTo(worldIn, pos.east(), side)) {
         return true;
      } else {
         return this.canAttachTo(worldIn, pos.north(), side) ? true : this.canAttachTo(worldIn, pos.south(), side);
      }
   }

   private boolean canAttachTo(bij p_193392_1_, BlockPos p_193392_2_, EnumFacing p_193392_3_) {
      in iblockstate = p_193392_1_.getBlockState(p_193392_2_);
      boolean flag = isExceptBlockForAttachWithPiston(iblockstate.getBlock());
      return !flag && iblockstate.getBlockFaceShape(p_193392_1_, p_193392_2_, p_193392_3_) == ib.SOLID && !iblockstate.canProvidePower();
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      if (facing.getAxis().isHorizontal() && this.canAttachTo(worldIn, pos.offset(facing.getOpposite()), facing)) {
         return this.getDefaultState().withProperty(FACING, facing);
      } else {
         Iterator var9 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing;
         do {
            if (!var9.hasNext()) {
               return this.getDefaultState();
            }

            enumfacing = (EnumFacing)var9.next();
         } while(!this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing));

         return this.getDefaultState().withProperty(FACING, enumfacing);
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      if (!this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.byIndex(meta);
      if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      LADDER_EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.1875, 1.0, 1.0);
      LADDER_WEST_AABB = new AxisAlignedBB(0.8125, 0.0, 0.0, 1.0, 1.0, 1.0);
      LADDER_SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.1875);
      LADDER_NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.8125, 1.0, 1.0, 1.0);
   }
}
