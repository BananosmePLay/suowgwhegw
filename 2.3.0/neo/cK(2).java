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

public abstract class cK extends dh {
   public static final hV POWERED = hV.create("powered");
   protected static final AxisAlignedBB AABB_DOWN_OFF = new AxisAlignedBB(0.3125, 0.875, 0.375, 0.6875, 1.0, 0.625);
   protected static final AxisAlignedBB AABB_UP_OFF = new AxisAlignedBB(0.3125, 0.0, 0.375, 0.6875, 0.125, 0.625);
   protected static final AxisAlignedBB AABB_NORTH_OFF = new AxisAlignedBB(0.3125, 0.375, 0.875, 0.6875, 0.625, 1.0);
   protected static final AxisAlignedBB AABB_SOUTH_OFF = new AxisAlignedBB(0.3125, 0.375, 0.0, 0.6875, 0.625, 0.125);
   protected static final AxisAlignedBB AABB_WEST_OFF = new AxisAlignedBB(0.875, 0.375, 0.3125, 1.0, 0.625, 0.6875);
   protected static final AxisAlignedBB AABB_EAST_OFF = new AxisAlignedBB(0.0, 0.375, 0.3125, 0.125, 0.625, 0.6875);
   protected static final AxisAlignedBB AABB_DOWN_ON = new AxisAlignedBB(0.3125, 0.9375, 0.375, 0.6875, 1.0, 0.625);
   protected static final AxisAlignedBB AABB_UP_ON = new AxisAlignedBB(0.3125, 0.0, 0.375, 0.6875, 0.0625, 0.625);
   protected static final AxisAlignedBB AABB_NORTH_ON = new AxisAlignedBB(0.3125, 0.375, 0.9375, 0.6875, 0.625, 1.0);
   protected static final AxisAlignedBB AABB_SOUTH_ON = new AxisAlignedBB(0.3125, 0.375, 0.0, 0.6875, 0.625, 0.0625);
   protected static final AxisAlignedBB AABB_WEST_ON = new AxisAlignedBB(0.9375, 0.375, 0.3125, 1.0, 0.625, 0.6875);
   protected static final AxisAlignedBB AABB_EAST_ON = new AxisAlignedBB(0.0, 0.375, 0.3125, 0.0625, 0.625, 0.6875);
   private final boolean wooden;

   protected cK(boolean wooden) {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.REDSTONE);
      this.wooden = wooden;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   public int tickRate(bij worldIn) {
      return this.wooden ? 30 : 20;
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
      return canPlaceBlock(worldIn, pos, side);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      EnumFacing[] var3 = EnumFacing.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumFacing enumfacing = var3[var5];
         if (canPlaceBlock(worldIn, pos, enumfacing)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean canPlaceBlock(bij worldIn, BlockPos pos, EnumFacing direction) {
      BlockPos blockpos = pos.offset(direction.getOpposite());
      in iblockstate = worldIn.getBlockState(blockpos);
      boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == ib.SOLID;
      co block = iblockstate.getBlock();
      if (direction == EnumFacing.UP) {
         return block == Nk.HOPPER || !isExceptionBlockForAttaching(block) && flag;
      } else {
         return !isExceptBlockForAttachWithPiston(block) && flag;
      }
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return canPlaceBlock(worldIn, pos, facing) ? this.getDefaultState().withProperty(FACING, facing).withProperty(POWERED, false) : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(POWERED, false);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (this.checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, (EnumFacing)state.getValue(FACING))) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   private boolean checkForDrop(bij worldIn, BlockPos pos, in state) {
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
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      boolean flag = (Boolean)state.getValue(POWERED);
      switch (enumfacing) {
         case EAST:
            return flag ? AABB_EAST_ON : AABB_EAST_OFF;
         case WEST:
            return flag ? AABB_WEST_ON : AABB_WEST_OFF;
         case SOUTH:
            return flag ? AABB_SOUTH_ON : AABB_SOUTH_OFF;
         case NORTH:
         default:
            return flag ? AABB_NORTH_ON : AABB_NORTH_OFF;
         case UP:
            return flag ? AABB_UP_ON : AABB_UP_OFF;
         case DOWN:
            return flag ? AABB_DOWN_ON : AABB_DOWN_OFF;
      }
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if ((Boolean)state.getValue(POWERED)) {
         return true;
      } else {
         worldIn.setBlockState(pos, state.withProperty(POWERED, true), 3);
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
         this.playClickSound(playerIn, worldIn, pos);
         this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
         worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
         return true;
      }
   }

   protected abstract void playClickSound(ME var1, bij var2, BlockPos var3);

   protected abstract void playReleaseSound(bij var1, BlockPos var2);

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if ((Boolean)state.getValue(POWERED)) {
         this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
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
         return blockState.getValue(FACING) == side ? 15 : 0;
      }
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote && (Boolean)state.getValue(POWERED)) {
         if (this.wooden) {
            this.checkPressed(state, worldIn, pos);
         } else {
            worldIn.setBlockState(pos, state.withProperty(POWERED, false));
            this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
            this.playReleaseSound(worldIn, pos);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
         }
      }

   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote && this.wooden && !(Boolean)state.getValue(POWERED)) {
         this.checkPressed(state, worldIn, pos);
      }

   }

   private void checkPressed(in state, bij worldIn, BlockPos pos) {
      List<? extends Ig> list = worldIn.getEntitiesWithinAABB(MO.class, state.getBoundingBox(worldIn, pos).offset(pos));
      boolean flag = !list.isEmpty();
      boolean flag1 = (Boolean)state.getValue(POWERED);
      if (flag && !flag1) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, true));
         this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
         this.playClickSound((ME)null, worldIn, pos);
      }

      if (!flag && flag1) {
         worldIn.setBlockState(pos, state.withProperty(POWERED, false));
         this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
         worldIn.markBlockRangeForRenderUpdate(pos, pos);
         this.playReleaseSound(worldIn, pos);
      }

      if (flag) {
         worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
      }

   }

   private void notifyNeighbors(bij worldIn, BlockPos pos, EnumFacing facing) {
      worldIn.notifyNeighborsOfStateChange(pos, this, false);
      worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this, false);
   }

   public in getStateFromMeta(int meta) {
      EnumFacing enumfacing;
      switch (meta & 7) {
         case 0:
            enumfacing = EnumFacing.DOWN;
            break;
         case 1:
            enumfacing = EnumFacing.EAST;
            break;
         case 2:
            enumfacing = EnumFacing.WEST;
            break;
         case 3:
            enumfacing = EnumFacing.SOUTH;
            break;
         case 4:
            enumfacing = EnumFacing.NORTH;
            break;
         case 5:
         default:
            enumfacing = EnumFacing.UP;
      }

      return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(POWERED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i;
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
            i = 1;
            break;
         case WEST:
            i = 2;
            break;
         case SOUTH:
            i = 3;
            break;
         case NORTH:
            i = 4;
            break;
         case UP:
         default:
            i = 5;
            break;
         case DOWN:
            i = 0;
      }

      if ((Boolean)state.getValue(POWERED)) {
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
      return new ii(this, new hT[]{FACING, POWERED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
