package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fd extends co {
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.5625), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
   private final boolean canDrop;

   protected fd(hM materialIn, boolean canDrop) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.canDrop = canDrop;
      this.setCreativeTab(EN.DECORATIONS);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      if (!isActualState) {
         state = this.getActualState(state, worldIn, pos);
      }

      addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[0]);
      if ((Boolean)state.getValue(NORTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
      }

      if ((Boolean)state.getValue(EAST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
      }

      if ((Boolean)state.getValue(WEST)) {
         addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
      }

   }

   private static int getBoundingBoxIndex(EnumFacing p_185729_0_) {
      return 1 << p_185729_0_.getHorizontalIndex();
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = this.getActualState(state, source, pos);
      return AABB_BY_INDEX[getBoundingBoxIndex(state)];
   }

   private static int getBoundingBoxIndex(in state) {
      int i = 0;
      if ((Boolean)state.getValue(NORTH)) {
         i |= getBoundingBoxIndex(EnumFacing.NORTH);
      }

      if ((Boolean)state.getValue(EAST)) {
         i |= getBoundingBoxIndex(EnumFacing.EAST);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         i |= getBoundingBoxIndex(EnumFacing.SOUTH);
      }

      if ((Boolean)state.getValue(WEST)) {
         i |= getBoundingBoxIndex(EnumFacing.WEST);
      }

      return i;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      return state.withProperty(NORTH, this.attachesTo(worldIn, worldIn.getBlockState(pos.north()), pos.north(), EnumFacing.SOUTH)).withProperty(SOUTH, this.attachesTo(worldIn, worldIn.getBlockState(pos.south()), pos.south(), EnumFacing.NORTH)).withProperty(WEST, this.attachesTo(worldIn, worldIn.getBlockState(pos.west()), pos.west(), EnumFacing.EAST)).withProperty(EAST, this.attachesTo(worldIn, worldIn.getBlockState(pos.east()), pos.east(), EnumFacing.WEST));
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return !this.canDrop ? NK.AIR : super.getItemDropped(state, rand, fortune);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
   }

   public final boolean attachesTo(bfZ p_193393_1_, in state, BlockPos pos, EnumFacing facing) {
      co block = state.getBlock();
      ib blockfaceshape = state.getBlockFaceShape(p_193393_1_, pos, facing);
      return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == ib.SOLID || blockfaceshape == ib.MIDDLE_POLE_THIN;
   }

   protected static boolean isExcepBlockForAttachWithPiston(co p_193394_0_) {
      return p_193394_0_ instanceof gr || p_193394_0_ instanceof ew || p_193394_0_ == Nk.BEACON || p_193394_0_ == Nk.CAULDRON || p_193394_0_ == Nk.GLOWSTONE || p_193394_0_ == Nk.ICE || p_193394_0_ == Nk.SEA_LANTERN || p_193394_0_ == Nk.PISTON || p_193394_0_ == Nk.STICKY_PISTON || p_193394_0_ == Nk.PISTON_HEAD || p_193394_0_ == Nk.MELON_BLOCK || p_193394_0_ == Nk.PUMPKIN || p_193394_0_ == Nk.LIT_PUMPKIN || p_193394_0_ == Nk.BARRIER;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public int getMetaFromState(in state) {
      return 0;
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
      return face != EnumFacing.UP && face != EnumFacing.DOWN ? ib.MIDDLE_POLE_THIN : ib.CENTER_SMALL;
   }
}
