package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dF extends dh {
   protected static final AxisAlignedBB END_ROD_VERTICAL_AABB = new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
   protected static final AxisAlignedBB END_ROD_NS_AABB = new AxisAlignedBB(0.375, 0.375, 0.0, 0.625, 0.625, 1.0);
   protected static final AxisAlignedBB END_ROD_EW_AABB = new AxisAlignedBB(0.0, 0.375, 0.375, 1.0, 0.625, 0.625);

   protected dF() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withProperty(FACING, mirrorIn.mirror((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch (((EnumFacing)state.getValue(FACING)).getAxis()) {
         case X:
         default:
            return END_ROD_EW_AABB;
         case Z:
            return END_ROD_NS_AABB;
         case Y:
            return END_ROD_VERTICAL_AABB;
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
      return true;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));
      if (iblockstate.getBlock() == Nk.END_ROD) {
         EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);
         if (enumfacing == facing) {
            return this.getDefaultState().withProperty(FACING, facing.getOpposite());
         }
      }

      return this.getDefaultState().withProperty(FACING, facing);
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
      double d0 = (double)pos.getX() + 0.55 - (double)(rand.nextFloat() * 0.1F);
      double d1 = (double)pos.getY() + 0.55 - (double)(rand.nextFloat() * 0.1F);
      double d2 = (double)pos.getZ() + 0.55 - (double)(rand.nextFloat() * 0.1F);
      double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
      if (rand.nextInt(5) == 0) {
         worldIn.spawnParticle(EnumParticleTypes.END_ROD, d0 + (double)enumfacing.getXOffset() * d3, d1 + (double)enumfacing.getYOffset() * d3, d2 + (double)enumfacing.getZOffset() * d3, rand.nextGaussian() * 0.005, rand.nextGaussian() * 0.005, rand.nextGaussian() * 0.005);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      in iblockstate = this.getDefaultState();
      iblockstate = iblockstate.withProperty(FACING, EnumFacing.byIndex(meta));
      return iblockstate;
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING});
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.NORMAL;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
