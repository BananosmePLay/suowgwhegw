package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cI extends co {
   protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896, 0.0, 0.30000001192092896, 0.699999988079071, 0.6000000238418579, 0.699999988079071);

   protected cI() {
      this(hM.PLANTS);
   }

   protected cI(hM materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected cI(hM materialIn, hK mapColorIn) {
      super(materialIn, mapColorIn);
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && this.canSustainBush(worldIn.getBlockState(pos.down()));
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.GRASS || state.getBlock() == Nk.DIRT || state.getBlock() == Nk.FARMLAND;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      this.checkAndDropBlock(worldIn, pos, state);
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      this.checkAndDropBlock(worldIn, pos, state);
   }

   protected void checkAndDropBlock(bij worldIn, BlockPos pos, in state) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockState(pos, Nk.AIR.getDefaultState(), 3);
      }

   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      return this.canSustainBush(worldIn.getBlockState(pos.down()));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return BUSH_AABB;
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

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
