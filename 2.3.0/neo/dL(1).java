package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dL extends co {
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.5, 0.625);
   public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.625, 0.625, 1.5, 1.0);
   public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.375, 0.375, 1.5, 0.625);
   public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.5, 0.375);
   public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625, 0.0, 0.375, 1.0, 1.5, 0.625);

   public dL(hM materialIn, hK mapColorIn) {
      super(materialIn, mapColorIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.setCreativeTab(EN.DECORATIONS);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!isActualState) {
         state = state.getActualState(worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);
      if ((Boolean)state.getValue(NORTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
      }

      if ((Boolean)state.getValue(EAST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
      }

      if ((Boolean)state.getValue(WEST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return BOUNDING_BOXES[getBoundingBoxIdx(state)];
   }

   private static int getBoundingBoxIdx(in state) {
      int i = 0;
      if ((Boolean)state.getValue(NORTH)) {
         i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(EAST)) {
         i |= 1 << EnumFacing.EAST.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(SOUTH)) {
         i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
      }

      if ((Boolean)state.getValue(WEST)) {
         i |= 1 << EnumFacing.WEST.getHorizontalIndex();
      }

      return i;
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
      return false;
   }

   public boolean canConnectTo(bfZ worldIn, BlockPos pos, EnumFacing facing) {
      in iblockstate = worldIn.getBlockState(pos);
      ib blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
      co block = iblockstate.getBlock();
      boolean flag = blockfaceshape == ib.MIDDLE_POLE && (iblockstate.getMaterial() == this.material || block instanceof dM);
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == ib.SOLID || flag;
   }

   protected static boolean isExcepBlockForAttachWithPiston(co p_194142_0_) {
      return co.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Nk.BARRIER || p_194142_0_ == Nk.MELON_BLOCK || p_194142_0_ == Nk.PUMPKIN || p_194142_0_ == Nk.LIT_PUMPKIN;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         return PP.attachToFence(playerIn, worldIn, pos);
      } else {
         Qy itemstack = playerIn.getHeldItem(hand);
         return itemstack.getItem() == NK.LEAD || itemstack.isEmpty();
      }
   }

   public int getMetaFromState(in state) {
      return 0;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state.withProperty(NORTH, this.canConnectTo(worldIn, pos.north(), EnumFacing.SOUTH)).withProperty(EAST, this.canConnectTo(worldIn, pos.east(), EnumFacing.WEST)).withProperty(SOUTH, this.canConnectTo(worldIn, pos.south(), EnumFacing.NORTH)).withProperty(WEST, this.canConnectTo(worldIn, pos.west(), EnumFacing.EAST));
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
         case COUNTERCLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
         case CLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
         default:
            return state;
      }
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case LEFT_RIGHT:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
         case FRONT_BACK:
            return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{NORTH, EAST, WEST, SOUTH});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? ib.MIDDLE_POLE : ib.CENTER;
   }
}
