package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class eU extends co {
   public eU() {
      super(hM.ROCK);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.OBSIDIAN);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.BLACK;
   }
}
