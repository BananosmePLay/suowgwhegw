package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbz extends bbE {
   private dq plantType;

   public bbz() {
   }

   public void setPlantType(dq plantTypeIn) {
      this.plantType = plantTypeIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      boolean flag = false;

      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && Nk.DOUBLE_PLANT.canPlaceBlockAt(worldIn, blockpos)) {
            Nk.DOUBLE_PLANT.placeAt(worldIn, blockpos, this.plantType, 2);
            flag = true;
         }
      }

      return flag;
   }
}
