package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fi extends dh {
   public static final hX<fh> TYPE = hX.create("type", fh.class);
   public static final hV SHORT = hV.create("short");
   protected static final AxisAlignedBB PISTON_EXTENSION_EAST_AABB = new AxisAlignedBB(0.75, 0.0, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_EXTENSION_WEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.25, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_EXTENSION_SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.75, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_EXTENSION_NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.25);
   protected static final AxisAlignedBB PISTON_EXTENSION_UP_AABB = new AxisAlignedBB(0.0, 0.75, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_EXTENSION_DOWN_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
   protected static final AxisAlignedBB UP_ARM_AABB = new AxisAlignedBB(0.375, -0.25, 0.375, 0.625, 0.75, 0.625);
   protected static final AxisAlignedBB DOWN_ARM_AABB = new AxisAlignedBB(0.375, 0.25, 0.375, 0.625, 1.25, 0.625);
   protected static final AxisAlignedBB SOUTH_ARM_AABB = new AxisAlignedBB(0.375, 0.375, -0.25, 0.625, 0.625, 0.75);
   protected static final AxisAlignedBB NORTH_ARM_AABB = new AxisAlignedBB(0.375, 0.375, 0.25, 0.625, 0.625, 1.25);
   protected static final AxisAlignedBB EAST_ARM_AABB = new AxisAlignedBB(-0.25, 0.375, 0.375, 0.75, 0.625, 0.625);
   protected static final AxisAlignedBB WEST_ARM_AABB = new AxisAlignedBB(0.25, 0.375, 0.375, 1.25, 0.625, 0.625);
   protected static final AxisAlignedBB SHORT_UP_ARM_AABB = new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.75, 0.625);
   protected static final AxisAlignedBB SHORT_DOWN_ARM_AABB = new AxisAlignedBB(0.375, 0.25, 0.375, 0.625, 1.0, 0.625);
   protected static final AxisAlignedBB SHORT_SOUTH_ARM_AABB = new AxisAlignedBB(0.375, 0.375, 0.0, 0.625, 0.625, 0.75);
   protected static final AxisAlignedBB SHORT_NORTH_ARM_AABB = new AxisAlignedBB(0.375, 0.375, 0.25, 0.625, 0.625, 1.0);
   protected static final AxisAlignedBB SHORT_EAST_ARM_AABB = new AxisAlignedBB(0.0, 0.375, 0.375, 0.75, 0.625, 0.625);
   protected static final AxisAlignedBB SHORT_WEST_ARM_AABB = new AxisAlignedBB(0.25, 0.375, 0.375, 1.0, 0.625, 0.625);

   public fi() {
      super(hM.PISTON);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, fh.DEFAULT).withProperty(SHORT, false));
      this.setSoundType(ia.STONE);
      this.setHardness(0.5F);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case DOWN:
         default:
            return PISTON_EXTENSION_DOWN_AABB;
         case UP:
            return PISTON_EXTENSION_UP_AABB;
         case NORTH:
            return PISTON_EXTENSION_NORTH_AABB;
         case SOUTH:
            return PISTON_EXTENSION_SOUTH_AABB;
         case WEST:
            return PISTON_EXTENSION_WEST_AABB;
         case EAST:
            return PISTON_EXTENSION_EAST_AABB;
      }
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
      addCollisionBoxToList(pos, entityBox, collidingBoxes, this.getArmShape(state));
   }

   private AxisAlignedBB getArmShape(in state) {
      boolean flag = (Boolean)state.getValue(SHORT);
      switch ((EnumFacing)state.getValue(FACING)) {
         case DOWN:
         default:
            return flag ? SHORT_DOWN_ARM_AABB : DOWN_ARM_AABB;
         case UP:
            return flag ? SHORT_UP_ARM_AABB : UP_ARM_AABB;
         case NORTH:
            return flag ? SHORT_NORTH_ARM_AABB : NORTH_ARM_AABB;
         case SOUTH:
            return flag ? SHORT_SOUTH_ARM_AABB : SOUTH_ARM_AABB;
         case WEST:
            return flag ? SHORT_WEST_ARM_AABB : WEST_ARM_AABB;
         case EAST:
            return flag ? SHORT_EAST_ARM_AABB : EAST_ARM_AABB;
      }
   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return state.getValue(FACING) == EnumFacing.UP;
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (player.capabilities.isCreativeMode) {
         BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
         co block = worldIn.getBlockState(blockpos).getBlock();
         if (block == Nk.PISTON || block == Nk.STICKY_PISTON) {
            worldIn.setBlockToAir(blockpos);
         }
      }

      super.onBlockHarvested(worldIn, pos, state, player);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      EnumFacing enumfacing = ((EnumFacing)state.getValue(FACING)).getOpposite();
      pos = pos.offset(enumfacing);
      in iblockstate = worldIn.getBlockState(pos);
      if ((iblockstate.getBlock() == Nk.PISTON || iblockstate.getBlock() == Nk.STICKY_PISTON) && (Boolean)iblockstate.getValue(ff.EXTENDED)) {
         iblockstate.getBlock().dropBlockAsItem(worldIn, pos, iblockstate, 0);
         worldIn.setBlockToAir(pos);
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

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return false;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return false;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      BlockPos blockpos = pos.offset(enumfacing.getOpposite());
      in iblockstate = worldIn.getBlockState(blockpos);
      if (iblockstate.getBlock() != Nk.PISTON && iblockstate.getBlock() != Nk.STICKY_PISTON) {
         worldIn.setBlockToAir(pos);
      } else {
         iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
      }

   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   @Nullable
   public static EnumFacing getFacing(int meta) {
      int i = meta & 7;
      return i > 5 ? null : EnumFacing.byIndex(i);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(state.getValue(TYPE) == fh.STICKY ? Nk.STICKY_PISTON : Nk.PISTON);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(TYPE, (meta & 8) > 0 ? fh.STICKY : fh.DEFAULT);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (state.getValue(TYPE) == fh.STICKY) {
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
      return new ii(this, new hT[]{FACING, TYPE, SHORT});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == state.getValue(FACING) ? ib.SOLID : ib.UNDEFINED;
   }
}
