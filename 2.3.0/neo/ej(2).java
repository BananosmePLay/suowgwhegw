package neo;

import net.minecraft.util.math.BlockPos;

public class ej extends co {
   public ej() {
      super(hM.ROCK);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.ADOBE;
   }
}
