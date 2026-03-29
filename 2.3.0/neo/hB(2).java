package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hB extends gs {
   public static final hW FACING;
   protected static final AxisAlignedBB SIGN_EAST_AABB;
   protected static final AxisAlignedBB SIGN_WEST_AABB;
   protected static final AxisAlignedBB SIGN_SOUTH_AABB;
   protected static final AxisAlignedBB SIGN_NORTH_AABB;

   public hB() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case NORTH:
         default:
            return SIGN_NORTH_AABB;
         case SOUTH:
            return SIGN_SOUTH_AABB;
         case WEST:
            return SIGN_WEST_AABB;
         case EAST:
            return SIGN_EAST_AABB;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid()) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
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

   static {
      FACING = en.FACING;
      SIGN_EAST_AABB = new AxisAlignedBB(0.0, 0.28125, 0.0, 0.125, 0.78125, 1.0);
      SIGN_WEST_AABB = new AxisAlignedBB(0.875, 0.28125, 0.0, 1.0, 0.78125, 1.0);
      SIGN_SOUTH_AABB = new AxisAlignedBB(0.0, 0.28125, 0.0, 1.0, 0.78125, 0.125);
      SIGN_NORTH_AABB = new AxisAlignedBB(0.0, 0.28125, 0.875, 1.0, 0.78125, 1.0);
   }
}
