package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ho extends co {
   public static final hW FACING = hW.create("facing", new Predicate<EnumFacing>() {
      public boolean apply(@Nullable EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.DOWN;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((EnumFacing)var1);
      }
   });
   protected static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645, 0.0, 0.4000000059604645, 0.6000000238418579, 0.6000000238418579, 0.6000000238418579);
   protected static final AxisAlignedBB TORCH_NORTH_AABB = new AxisAlignedBB(0.3499999940395355, 0.20000000298023224, 0.699999988079071, 0.6499999761581421, 0.800000011920929, 1.0);
   protected static final AxisAlignedBB TORCH_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355, 0.20000000298023224, 0.0, 0.6499999761581421, 0.800000011920929, 0.30000001192092896);
   protected static final AxisAlignedBB TORCH_WEST_AABB = new AxisAlignedBB(0.699999988079071, 0.20000000298023224, 0.3499999940395355, 1.0, 0.800000011920929, 0.6499999761581421);
   protected static final AxisAlignedBB TORCH_EAST_AABB = new AxisAlignedBB(0.0, 0.20000000298023224, 0.3499999940395355, 0.30000001192092896, 0.800000011920929, 0.6499999761581421);

   protected ho() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
            return TORCH_EAST_AABB;
         case WEST:
            return TORCH_WEST_AABB;
         case SOUTH:
            return TORCH_SOUTH_AABB;
         case NORTH:
            return TORCH_NORTH_AABB;
         default:
            return STANDING_AABB;
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

   private boolean canPlaceOn(bij worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos).getBlock();
      boolean flag = block == Nk.END_GATEWAY || block == Nk.LIT_PUMPKIN;
      if (worldIn.getBlockState(pos).isTopSolid()) {
         return !flag;
      } else {
         boolean flag1 = block instanceof dL || block == Nk.GLASS || block == Nk.COBBLESTONE_WALL || block == Nk.STAINED_GLASS;
         return flag1 && !flag;
      }
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      Iterator var3 = FACING.getAllowedValues().iterator();

      EnumFacing enumfacing;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         enumfacing = (EnumFacing)var3.next();
      } while(!this.canPlaceAt(worldIn, pos, enumfacing));

      return true;
   }

   private boolean canPlaceAt(bij worldIn, BlockPos pos, EnumFacing facing) {
      BlockPos blockpos = pos.offset(facing.getOpposite());
      in iblockstate = worldIn.getBlockState(blockpos);
      co block = iblockstate.getBlock();
      ib blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);
      if (facing.equals(EnumFacing.UP) && this.canPlaceOn(worldIn, blockpos)) {
         return true;
      } else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
         return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == ib.SOLID;
      } else {
         return false;
      }
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      if (this.canPlaceAt(worldIn, pos, facing)) {
         return this.getDefaultState().withProperty(FACING, facing);
      } else {
         Iterator var9 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing;
         do {
            if (!var9.hasNext()) {
               return this.getDefaultState();
            }

            enumfacing = (EnumFacing)var9.next();
         } while(!this.canPlaceAt(worldIn, pos, enumfacing));

         return this.getDefaultState().withProperty(FACING, enumfacing);
      }
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.checkForDrop(worldIn, pos, state);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      this.onNeighborChangeInternal(worldIn, pos, state);
   }

   protected boolean onNeighborChangeInternal(bij worldIn, BlockPos pos, in state) {
      if (!this.checkForDrop(worldIn, pos, state)) {
         return true;
      } else {
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         EnumFacing.Axis enumfacing$axis = enumfacing.getAxis();
         EnumFacing enumfacing1 = enumfacing.getOpposite();
         BlockPos blockpos = pos.offset(enumfacing1);
         boolean flag = false;
         if (enumfacing$axis.isHorizontal() && worldIn.getBlockState(blockpos).getBlockFaceShape(worldIn, blockpos, enumfacing) != ib.SOLID) {
            flag = true;
         } else if (enumfacing$axis.isVertical() && !this.canPlaceOn(worldIn, blockpos)) {
            flag = true;
         }

         if (flag) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return true;
         } else {
            return false;
         }
      }
   }

   protected boolean checkForDrop(bij worldIn, BlockPos pos, in state) {
      if (state.getBlock() == this && this.canPlaceAt(worldIn, pos, (EnumFacing)state.getValue(FACING))) {
         return true;
      } else {
         if (worldIn.getBlockState(pos).getBlock() == this) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

         return false;
      }
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
      double d0 = (double)pos.getX() + 0.5;
      double d1 = (double)pos.getY() + 0.7;
      double d2 = (double)pos.getZ() + 0.5;
      double d3 = 0.22;
      double d4 = 0.27;
      if (enumfacing.getAxis().isHorizontal()) {
         EnumFacing enumfacing1 = enumfacing.getOpposite();
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27 * (double)enumfacing1.getXOffset(), d1 + 0.22, d2 + 0.27 * (double)enumfacing1.getZOffset(), 0.0, 0.0, 0.0);
         worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.27 * (double)enumfacing1.getXOffset(), d1 + 0.22, d2 + 0.27 * (double)enumfacing1.getZOffset(), 0.0, 0.0, 0.0);
      } else {
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
         worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState();
      switch (meta) {
         case 1:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
            break;
         case 2:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
            break;
         case 3:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            break;
         case 4:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
            break;
         case 5:
         default:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
      }

      return iblockstate;
   }

   public int getMetaFromState(in state) {
      int i = 0;
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
            i |= 1;
            break;
         case WEST:
            i |= 2;
            break;
         case SOUTH:
            i |= 3;
            break;
         case NORTH:
            i |= 4;
            break;
         case DOWN:
         case UP:
         default:
            i |= 5;
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
      return new ii(this, new hT[]{FACING});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
