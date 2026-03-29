package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public abstract class bbn extends bbE {
   public bbn(boolean notify) {
      super(notify);
   }

   protected boolean canGrowInto(co blockType) {
      hM material = blockType.getDefaultState().getMaterial();
      return material == hM.AIR || material == hM.LEAVES || blockType == Nk.GRASS || blockType == Nk.DIRT || blockType == Nk.LOG || blockType == Nk.LOG2 || blockType == Nk.SAPLING || blockType == Nk.VINE;
   }

   public void generateSaplings(bij worldIn, Random random, BlockPos pos) {
   }

   protected void setDirtAt(bij worldIn, BlockPos pos) {
      if (worldIn.getBlockState(pos).getBlock() != Nk.DIRT) {
         this.setBlockAndNotifyAdequately(worldIn, pos, Nk.DIRT.getDefaultState());
      }

   }
}
