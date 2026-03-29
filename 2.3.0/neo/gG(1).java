package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class gG extends co {
   public static final hX<gF> HALF = hX.create("half", gF.class);
   protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
   protected static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);

   public gG(hM materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   public gG(hM p_i47249_1_, hK p_i47249_2_) {
      super(p_i47249_1_, p_i47249_2_);
      this.fullBlock = this.isDouble();
      this.setLightOpacity(255);
   }

   protected boolean canSilkHarvest() {
      return false;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      if (this.isDouble()) {
         return FULL_BLOCK_AABB;
      } else {
         return state.getValue(HALF) == gF.TOP ? AABB_TOP_HALF : AABB_BOTTOM_HALF;
      }
   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return ((gG)state.getBlock()).isDouble() || state.getValue(HALF) == gF.TOP;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      if (((gG)state.getBlock()).isDouble()) {
         return ib.SOLID;
      } else if (face == EnumFacing.UP && state.getValue(HALF) == gF.TOP) {
         return ib.SOLID;
      } else {
         return face == EnumFacing.DOWN && state.getValue(HALF) == gF.BOTTOM ? ib.SOLID : ib.UNDEFINED;
      }
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return this.isDouble();
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(HALF, gF.BOTTOM);
      if (this.isDouble()) {
         return iblockstate;
      } else {
         return facing == EnumFacing.DOWN || facing != EnumFacing.UP && !((double)hitY <= 0.5) ? iblockstate.withProperty(HALF, gF.TOP) : iblockstate;
      }
   }

   public int quantityDropped(Random random) {
      return this.isDouble() ? 2 : 1;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return this.isDouble();
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (this.isDouble()) {
         return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      } else if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(blockState, blockAccess, pos, side)) {
         return false;
      } else {
         in iblockstate = blockAccess.getBlockState(pos.offset(side));
         boolean flag = isHalfSlab(iblockstate) && iblockstate.getValue(HALF) == gF.TOP;
         boolean flag1 = isHalfSlab(blockState) && blockState.getValue(HALF) == gF.TOP;
         if (flag1) {
            if (side == EnumFacing.DOWN) {
               return true;
            } else if (side == EnumFacing.UP && super.shouldSideBeRendered(blockState, blockAccess, pos, side)) {
               return true;
            } else {
               return !isHalfSlab(iblockstate) || !flag;
            }
         } else if (side == EnumFacing.UP) {
            return true;
         } else if (side == EnumFacing.DOWN && super.shouldSideBeRendered(blockState, blockAccess, pos, side)) {
            return true;
         } else {
            return !isHalfSlab(iblockstate) || flag;
         }
      }
   }

   protected static boolean isHalfSlab(in state) {
      co block = state.getBlock();
      return block == Nk.STONE_SLAB || block == Nk.WOODEN_SLAB || block == Nk.STONE_SLAB2 || block == Nk.PURPUR_SLAB;
   }

   public abstract String getTranslationKey(int var1);

   public abstract boolean isDouble();

   public abstract hT<?> getVariantProperty();

   public abstract Comparable<?> getTypeForItem(Qy var1);
}
