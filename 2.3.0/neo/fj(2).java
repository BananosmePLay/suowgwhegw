package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class fj extends dd {
   public static final hW FACING;
   public static final hX<fh> TYPE;

   public fj() {
      super(hM.PISTON);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, fh.DEFAULT));
      this.setHardness(-1.0F);
   }

   @Nullable
   public Yg createNewTileEntity(bij worldIn, int meta) {
      return null;
   }

   public static Yg createTilePiston(in blockStateIn, EnumFacing facingIn, boolean extendingIn, boolean shouldHeadBeRenderedIn) {
      return new YK(blockStateIn, facingIn, extendingIn, shouldHeadBeRenderedIn);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof YK) {
         ((YK)tileentity).clearPistonTileEntity();
      } else {
         super.breakBlock(worldIn, pos, state);
      }

   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return false;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return false;
   }

   public void onPlayerDestroy(bij worldIn, BlockPos pos, in state) {
      BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
      in iblockstate = worldIn.getBlockState(blockpos);
      if (iblockstate.getBlock() instanceof ff && (Boolean)iblockstate.getValue(ff.EXTENDED)) {
         worldIn.setBlockToAir(blockpos);
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

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null) {
         worldIn.setBlockToAir(pos);
         return true;
      } else {
         return false;
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (!worldIn.isRemote) {
         YK tileentitypiston = this.getTilePistonAt(worldIn, pos);
         if (tileentitypiston != null) {
            in iblockstate = tileentitypiston.getPistonState();
            iblockstate.getBlock().dropBlockAsItem(worldIn, pos, iblockstate, 0);
         }
      }

   }

   @Nullable
   public RayTraceResult collisionRayTrace(in blockState, bij worldIn, BlockPos pos, Vec3d start, Vec3d end) {
      return null;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         worldIn.getTileEntity(pos);
      }

   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      YK tileentitypiston = this.getTilePistonAt(worldIn, pos);
      return tileentitypiston == null ? null : tileentitypiston.getAABB(worldIn, pos);
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      YK tileentitypiston = this.getTilePistonAt(worldIn, pos);
      if (tileentitypiston != null) {
         tileentitypiston.addCollissionAABBs(worldIn, pos, entityBox, collidingBoxes, entityIn);
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      YK tileentitypiston = this.getTilePistonAt(source, pos);
      return tileentitypiston != null ? tileentitypiston.getAABB(source, pos) : FULL_BLOCK_AABB;
   }

   @Nullable
   private YK getTilePistonAt(bfZ iBlockAccessIn, BlockPos blockPosIn) {
      Yg tileentity = iBlockAccessIn.getTileEntity(blockPosIn);
      return tileentity instanceof YK ? (YK)tileentity : null;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return Qy.EMPTY;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, fi.getFacing(meta)).withProperty(TYPE, (meta & 8) > 0 ? fh.STICKY : fh.DEFAULT);
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (state.getValue(TYPE) == fh.STICKY) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, TYPE});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = fi.FACING;
      TYPE = fi.TYPE;
   }
}
