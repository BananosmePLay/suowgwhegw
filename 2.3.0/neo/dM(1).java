package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dM extends en {
   public static final hV OPEN = hV.create("open");
   public static final hV POWERED = hV.create("powered");
   public static final hV IN_WALL = hV.create("in_wall");
   protected static final AxisAlignedBB AABB_HITBOX_ZAXIS = new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 0.625);
   protected static final AxisAlignedBB AABB_HITBOX_XAXIS = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 1.0);
   protected static final AxisAlignedBB AABB_HITBOX_ZAXIS_INWALL = new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 0.8125, 0.625);
   protected static final AxisAlignedBB AABB_HITBOX_XAXIS_INWALL = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 0.8125, 1.0);
   protected static final AxisAlignedBB AABB_COLLISION_BOX_ZAXIS = new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.5, 0.625);
   protected static final AxisAlignedBB AABB_COLLISION_BOX_XAXIS = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.5, 1.0);

   public dM(fk p_i46394_1_) {
      super(hM.WOOD, p_i46394_1_.getMapColor());
      this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));
      this.setCreativeTab(EN.REDSTONE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      if ((Boolean)state.getValue(IN_WALL)) {
         return ((EnumFacing)state.getValue(FACING)).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS_INWALL : AABB_HITBOX_ZAXIS_INWALL;
      } else {
         return ((EnumFacing)state.getValue(FACING)).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS : AABB_HITBOX_ZAXIS;
      }
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      EnumFacing.Axis enumfacing$axis = ((EnumFacing)state.getValue(FACING)).getAxis();
      if (enumfacing$axis == EnumFacing.Axis.Z && (worldIn.getBlockState(pos.west()).getBlock() == Nk.COBBLESTONE_WALL || worldIn.getBlockState(pos.east()).getBlock() == Nk.COBBLESTONE_WALL) || enumfacing$axis == EnumFacing.Axis.X && (worldIn.getBlockState(pos.north()).getBlock() == Nk.COBBLESTONE_WALL || worldIn.getBlockState(pos.south()).getBlock() == Nk.COBBLESTONE_WALL)) {
         state = state.withProperty(IN_WALL, true);
      }

      return state;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      if ((Boolean)blockState.getValue(OPEN)) {
         return NULL_AABB;
      } else {
         return ((EnumFacing)blockState.getValue(FACING)).getAxis() == EnumFacing.Axis.Z ? AABB_COLLISION_BOX_ZAXIS : AABB_COLLISION_BOX_XAXIS;
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

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return (Boolean)worldIn.getBlockState(pos).getValue(OPEN);
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      boolean flag = worldIn.isBlockPowered(pos);
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, flag).withProperty(POWERED, flag).withProperty(IN_WALL, false);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if ((Boolean)state.getValue(OPEN)) {
         state = state.withProperty(OPEN, false);
         worldIn.setBlockState(pos, state, 10);
      } else {
         EnumFacing enumfacing = EnumFacing.fromAngle((double)playerIn.rotationYaw);
         if (state.getValue(FACING) == enumfacing.getOpposite()) {
            state = state.withProperty(FACING, enumfacing);
         }

         state = state.withProperty(OPEN, true);
         worldIn.setBlockState(pos, state, 10);
      }

      worldIn.playEvent(playerIn, (Boolean)state.getValue(OPEN) ? 1008 : 1014, pos, 0);
      return true;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         boolean flag = worldIn.isBlockPowered(pos);
         if ((Boolean)state.getValue(POWERED) != flag) {
            worldIn.setBlockState(pos, state.withProperty(POWERED, flag).withProperty(OPEN, flag), 2);
            if ((Boolean)state.getValue(OPEN) != flag) {
               worldIn.playEvent((ME)null, flag ? 1008 : 1014, pos, 0);
            }
         }
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(OPEN, (meta & 4) != 0).withProperty(POWERED, (meta & 8) != 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      if ((Boolean)state.getValue(OPEN)) {
         i |= 4;
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, OPEN, POWERED, IN_WALL});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      if (face != EnumFacing.UP && face != EnumFacing.DOWN) {
         return ((EnumFacing)state.getValue(FACING)).getAxis() == face.rotateY().getAxis() ? ib.MIDDLE_POLE : ib.UNDEFINED;
      } else {
         return ib.UNDEFINED;
      }
   }
}
