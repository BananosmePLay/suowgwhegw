package neo;

import com.google.common.base.MoreObjects;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hv extends co {
   public static final hW FACING;
   public static final hV POWERED;
   public static final hV ATTACHED;
   protected static final AxisAlignedBB HOOK_NORTH_AABB;
   protected static final AxisAlignedBB HOOK_SOUTH_AABB;
   protected static final AxisAlignedBB HOOK_WEST_AABB;
   protected static final AxisAlignedBB HOOK_EAST_AABB;

   public hv() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false).withProperty(ATTACHED, false));
      this.setCreativeTab(EN.REDSTONE);
      this.setTickRandomly(true);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
         default:
            return HOOK_EAST_AABB;
         case WEST:
            return HOOK_WEST_AABB;
         case SOUTH:
            return HOOK_SOUTH_AABB;
         case NORTH:
            return HOOK_NORTH_AABB;
      }
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
      EnumFacing enumfacing = side.getOpposite();
      BlockPos blockpos = pos.offset(enumfacing);
      in iblockstate = worldIn.getBlockState(blockpos);
      boolean flag = isExceptBlockForAttachWithPiston(iblockstate.getBlock());
      return !flag && side.getAxis().isHorizontal() && iblockstate.getBlockFaceShape(worldIn, blockpos, side) == ib.SOLID && !iblockstate.canProvidePower();
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      Iterator var3 = EnumFacing.Plane.HORIZONTAL.iterator();

      EnumFacing enumfacing;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         enumfacing = (EnumFacing)var3.next();
      } while(!this.canPlaceBlockOnSide(worldIn, pos, enumfacing));

      return true;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = this.getDefaultState().withProperty(POWERED, false).withProperty(ATTACHED, false);
      if (facing.getAxis().isHorizontal()) {
         iblockstate = iblockstate.withProperty(FACING, facing);
      }

      return iblockstate;
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      this.calculateState(worldIn, pos, state, false, false, -1, (in)null);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (blockIn != this && this.checkForDrop(worldIn, pos, state)) {
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         if (!this.canPlaceBlockOnSide(worldIn, pos, enumfacing)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }
      }

   }

   public void calculateState(bij worldIn, BlockPos pos, in hookState, boolean p_176260_4_, boolean p_176260_5_, int p_176260_6_, @Nullable in p_176260_7_) {
      EnumFacing enumfacing = (EnumFacing)hookState.getValue(FACING);
      boolean flag = (Boolean)hookState.getValue(ATTACHED);
      boolean flag1 = (Boolean)hookState.getValue(POWERED);
      boolean flag2 = !p_176260_4_;
      boolean flag3 = false;
      int i = 0;
      in[] aiblockstate = new in[42];

      BlockPos blockpos1;
      for(int j = 1; j < 42; ++j) {
         blockpos1 = pos.offset(enumfacing, j);
         in iblockstate = worldIn.getBlockState(blockpos1);
         if (iblockstate.getBlock() == Nk.TRIPWIRE_HOOK) {
            if (iblockstate.getValue(FACING) == enumfacing.getOpposite()) {
               i = j;
            }
            break;
         }

         if (iblockstate.getBlock() != Nk.TRIPWIRE && j != p_176260_6_) {
            aiblockstate[j] = null;
            flag2 = false;
         } else {
            if (j == p_176260_6_) {
               iblockstate = (in)MoreObjects.firstNonNull(p_176260_7_, iblockstate);
            }

            boolean flag4 = !(Boolean)iblockstate.getValue(ht.DISARMED);
            boolean flag5 = (Boolean)iblockstate.getValue(ht.POWERED);
            flag3 |= flag4 && flag5;
            aiblockstate[j] = iblockstate;
            if (j == p_176260_6_) {
               worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
               flag2 &= flag4;
            }
         }
      }

      flag2 &= i > 1;
      flag3 &= flag2;
      in iblockstate1 = this.getDefaultState().withProperty(ATTACHED, flag2).withProperty(POWERED, flag3);
      if (i > 0) {
         blockpos1 = pos.offset(enumfacing, i);
         EnumFacing enumfacing1 = enumfacing.getOpposite();
         worldIn.setBlockState(blockpos1, iblockstate1.withProperty(FACING, enumfacing1), 3);
         this.notifyNeighbors(worldIn, blockpos1, enumfacing1);
         this.playSound(worldIn, blockpos1, flag2, flag3, flag, flag1);
      }

      this.playSound(worldIn, pos, flag2, flag3, flag, flag1);
      if (!p_176260_4_) {
         worldIn.setBlockState(pos, iblockstate1.withProperty(FACING, enumfacing), 3);
         if (p_176260_5_) {
            this.notifyNeighbors(worldIn, pos, enumfacing);
         }
      }

      if (flag != flag2) {
         for(int k = 1; k < i; ++k) {
            BlockPos blockpos2 = pos.offset(enumfacing, k);
            in iblockstate2 = aiblockstate[k];
            if (iblockstate2 != null && worldIn.getBlockState(blockpos2).getMaterial() != hM.AIR) {
               worldIn.setBlockState(blockpos2, iblockstate2.withProperty(ATTACHED, flag2), 3);
            }
         }
      }

   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      this.calculateState(worldIn, pos, state, false, true, -1, (in)null);
   }

   private void playSound(bij worldIn, BlockPos pos, boolean p_180694_3_, boolean p_180694_4_, boolean p_180694_5_, boolean p_180694_6_) {
      if (p_180694_4_ && !p_180694_6_) {
         worldIn.playSound((ME)null, pos, NO.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.BLOCKS, 0.4F, 0.6F);
      } else if (!p_180694_4_ && p_180694_6_) {
         worldIn.playSound((ME)null, pos, NO.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.BLOCKS, 0.4F, 0.5F);
      } else if (p_180694_3_ && !p_180694_5_) {
         worldIn.playSound((ME)null, pos, NO.BLOCK_TRIPWIRE_ATTACH, SoundCategory.BLOCKS, 0.4F, 0.7F);
      } else if (!p_180694_3_ && p_180694_5_) {
         worldIn.playSound((ME)null, pos, NO.BLOCK_TRIPWIRE_DETACH, SoundCategory.BLOCKS, 0.4F, 1.2F / (worldIn.rand.nextFloat() * 0.2F + 0.9F));
      }

   }

   private void notifyNeighbors(bij worldIn, BlockPos pos, EnumFacing side) {
      worldIn.notifyNeighborsOfStateChange(pos, this, false);
      worldIn.notifyNeighborsOfStateChange(pos.offset(side.getOpposite()), this, false);
   }

   private boolean checkForDrop(bij worldIn, BlockPos pos, in state) {
      if (!this.canPlaceBlockAt(worldIn, pos)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      boolean flag = (Boolean)state.getValue(ATTACHED);
      boolean flag1 = (Boolean)state.getValue(POWERED);
      if (flag || flag1) {
         this.calculateState(worldIn, pos, state, true, false, -1, (in)null);
      }

      if (flag1) {
         worldIn.notifyNeighborsOfStateChange(pos, this, false);
         worldIn.notifyNeighborsOfStateChange(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite()), this, false);
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

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3)).withProperty(POWERED, (meta & 8) > 0).withProperty(ATTACHED, (meta & 4) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         i |= 8;
      }

      if ((Boolean)state.getValue(ATTACHED)) {
         i |= 4;
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
      return new ii(this, new hT[]{FACING, POWERED, ATTACHED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      POWERED = hV.create("powered");
      ATTACHED = hV.create("attached");
      HOOK_NORTH_AABB = new AxisAlignedBB(0.3125, 0.0, 0.625, 0.6875, 0.625, 1.0);
      HOOK_SOUTH_AABB = new AxisAlignedBB(0.3125, 0.0, 0.0, 0.6875, 0.625, 0.375);
      HOOK_WEST_AABB = new AxisAlignedBB(0.625, 0.0, 0.3125, 1.0, 0.625, 0.6875);
      HOOK_EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.3125, 0.375, 0.625, 0.6875);
   }
}
