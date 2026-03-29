package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class cG extends co {
   private final boolean ignoreSimilarity;

   protected cG(hM materialIn, boolean ignoreSimilarityIn) {
      this(materialIn, ignoreSimilarityIn, materialIn.getMaterialMapColor());
   }

   protected cG(hM materialIn, boolean ignoreSimilarityIn, hK mapColorIn) {
      super(materialIn, mapColorIn);
      this.ignoreSimilarity = ignoreSimilarityIn;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      in iblockstate = blockAccess.getBlockState(pos.offset(side));
      co block = iblockstate.getBlock();
      if (this == Nk.GLASS || this == Nk.STAINED_GLASS) {
         if (blockState != iblockstate) {
            return true;
         }

         if (block == this) {
            return false;
         }
      }

      return !this.ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
   }
}
