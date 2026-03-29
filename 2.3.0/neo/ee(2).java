package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ee extends co {
   protected static final AxisAlignedBB GRASS_PATH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9375, 1.0);

   protected ee() {
      super(hM.GROUND);
      this.setLightOpacity(255);
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      switch (side) {
         case UP:
            return true;
         case NORTH:
         case SOUTH:
         case WEST:
         case EAST:
            in iblockstate = blockAccess.getBlockState(pos.offset(side));
            co block = iblockstate.getBlock();
            return !iblockstate.isOpaqueCube() && block != Nk.FARMLAND && block != Nk.GRASS_PATH;
         default:
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
      }
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      this.updateBlockState(worldIn, pos);
   }

   private void updateBlockState(bij worldIn, BlockPos pos) {
      if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
         dJ.turnToDirt(worldIn, pos);
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return GRASS_PATH_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return Nk.DIRT.getItemDropped(Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.DIRT), rand, fortune);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this);
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      this.updateBlockState(worldIn, pos);
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? ib.SOLID : ib.UNDEFINED;
   }
}
