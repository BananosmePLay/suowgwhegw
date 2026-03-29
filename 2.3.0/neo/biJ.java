package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class biJ {
   private static sc modelSnowLayer = null;

   public biJ() {
   }

   public static void update() {
      modelSnowLayer = XH.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(Nk.SNOW_LAYER.getDefaultState());
   }

   public static sc getModelSnowLayer() {
      return modelSnowLayer;
   }

   public static in getStateSnowLayer() {
      return Nk.SNOW_LAYER.getDefaultState();
   }

   public static boolean shouldRender(bfZ blockAccess, in blockState, BlockPos blockPos) {
      co block = blockState.getBlock();
      return !checkBlock(block, blockState) ? false : hasSnowNeighbours(blockAccess, blockPos);
   }

   private static boolean hasSnowNeighbours(bfZ blockAccess, BlockPos pos) {
      co block = Nk.SNOW_LAYER;
      return blockAccess.getBlockState(pos.north()).getBlock() != block && blockAccess.getBlockState(pos.south()).getBlock() != block && blockAccess.getBlockState(pos.west()).getBlock() != block && blockAccess.getBlockState(pos.east()).getBlock() != block ? false : blockAccess.getBlockState(pos.down()).isOpaqueCube();
   }

   private static boolean checkBlock(co block, in blockState) {
      if (blockState.isFullCube()) {
         return false;
      } else if (blockState.isOpaqueCube()) {
         return false;
      } else if (block instanceof gI) {
         return false;
      } else if (block instanceof cI && (block instanceof dr || block instanceof dS || block instanceof eI || block instanceof go || block instanceof hk)) {
         return true;
      } else if (!(block instanceof dL) && !(block instanceof dM) && !(block instanceof dV) && !(block instanceof fd) && !(block instanceof gg) && !(block instanceof hz)) {
         if (block instanceof gc && blockState.getValue(ho.FACING) == EnumFacing.UP) {
            return true;
         } else {
            if (block instanceof ez) {
               Object object = blockState.getValue(ez.FACING);
               if (object == ey.UP_X || object == ey.UP_Z) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }
}
