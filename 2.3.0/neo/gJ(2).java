package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class gJ extends co {
   protected gJ() {
      super(hM.CRAFTED_SNOW);
      this.setTickRandomly(true);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.SNOWBALL;
   }

   public int quantityDropped(Random random) {
      return 4;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (worldIn.getLightFor(baW.BLOCK, pos) > 11) {
         this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
         worldIn.setBlockToAir(pos);
      }

   }
}
