package neo;

import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hr extends co {
   public static final hW FACING;
   public static final hV OPEN;
   public static final hX<hq> HALF;
   protected static final AxisAlignedBB EAST_OPEN_AABB;
   protected static final AxisAlignedBB WEST_OPEN_AABB;
   protected static final AxisAlignedBB SOUTH_OPEN_AABB;
   protected static final AxisAlignedBB NORTH_OPEN_AABB;
   protected static final AxisAlignedBB BOTTOM_AABB;
   protected static final AxisAlignedBB TOP_AABB;

   protected hr(hM materialIn) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false).withProperty(HALF, hq.BOTTOM));
      this.setCreativeTab(EN.REDSTONE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      AxisAlignedBB axisalignedbb;
      if ((Boolean)state.getValue(OPEN)) {
         switch ((EnumFacing)state.getValue(FACING)) {
            case NORTH:
            default:
               axisalignedbb = NORTH_OPEN_AABB;
               break;
            case SOUTH:
               axisalignedbb = SOUTH_OPEN_AABB;
               break;
            case WEST:
               axisalignedbb = WEST_OPEN_AABB;
               break;
            case EAST:
               axisalignedbb = EAST_OPEN_AABB;
         }
      } else if (state.getValue(HALF) == hq.TOP) {
         axisalignedbb = TOP_AABB;
      } else {
         axisalignedbb = BOTTOM_AABB;
      }

      return axisalignedbb;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return !(Boolean)worldIn.getBlockState(pos).getValue(OPEN);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (this.material == hM.IRON) {
         return false;
      } else {
         state = state.cycleProperty(OPEN);
         worldIn.setBlockState(pos, state, 2);
         this.playSound(playerIn, worldIn, pos, (Boolean)state.getValue(OPEN));
         return true;
      }
   }

   protected void playSound(@Nullable ME player, bij worldIn, BlockPos pos, boolean p_185731_4_) {
      int i;
      if (p_185731_4_) {
         i = this.material == hM.IRON ? 1037 : 1007;
         worldIn.playEvent(player, i, pos, 0);
      } else {
         i = this.material == hM.IRON ? 1036 : 1013;
         worldIn.playEvent(player, i, pos, 0);
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         boolean flag = worldIn.isBlockPowered(pos);
         if (flag || blockIn.getDefaultState().canProvidePower()) {
            boolean flag1 = (Boolean)state.getValue(OPEN);
            if (flag1 != flag) {
               worldIn.setBlockState(pos, state.withProperty(OPEN, flag), 2);
               this.playSound((ME)null, worldIn, pos, flag);
            }
         }
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = this.getDefaultState();
      if (facing.getAxis().isHorizontal()) {
         iblockstate = iblockstate.withProperty(FACING, facing).withProperty(OPEN, false);
         iblockstate = iblockstate.withProperty(HALF, hitY > 0.5F ? hq.TOP : hq.BOTTOM);
      } else {
         iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(OPEN, false);
         iblockstate = iblockstate.withProperty(HALF, facing == EnumFacing.UP ? hq.BOTTOM : hq.TOP);
      }

      if (worldIn.isBlockPowered(pos)) {
         iblockstate = iblockstate.withProperty(OPEN, true);
      }

      return iblockstate;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return true;
   }

   protected static EnumFacing getFacing(int meta) {
      switch (meta & 3) {
         case 0:
            return EnumFacing.NORTH;
         case 1:
            return EnumFacing.SOUTH;
         case 2:
            return EnumFacing.WEST;
         case 3:
         default:
            return EnumFacing.EAST;
      }
   }

   protected static int getMetaForFacing(EnumFacing facing) {
      switch (facing) {
         case NORTH:
            return 0;
         case SOUTH:
            return 1;
         case WEST:
            return 2;
         case EAST:
         default:
            return 3;
      }
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, (meta & 4) != 0).withProperty(HALF, (meta & 8) == 0 ? hq.BOTTOM : hq.TOP);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= getMetaForFacing((EnumFacing)state.getValue(FACING));
      if ((Boolean)state.getValue(OPEN)) {
         i |= 4;
      }

      if (state.getValue(HALF) == hq.TOP) {
         i |= 8;
      }

      return i;
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
      return new ii(this, new hT[]{FACING, OPEN, HALF});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return (face == EnumFacing.UP && state.getValue(HALF) == hq.TOP || face == EnumFacing.DOWN && state.getValue(HALF) == hq.BOTTOM) && !(Boolean)state.getValue(OPEN) ? ib.SOLID : ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      OPEN = hV.create("open");
      HALF = hX.create("half", hq.class);
      EAST_OPEN_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.1875, 1.0, 1.0);
      WEST_OPEN_AABB = new AxisAlignedBB(0.8125, 0.0, 0.0, 1.0, 1.0, 1.0);
      SOUTH_OPEN_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.1875);
      NORTH_OPEN_AABB = new AxisAlignedBB(0.0, 0.0, 0.8125, 1.0, 1.0, 1.0);
      BOTTOM_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0);
      TOP_AABB = new AxisAlignedBB(0.0, 0.8125, 0.0, 1.0, 1.0, 1.0);
   }
}
