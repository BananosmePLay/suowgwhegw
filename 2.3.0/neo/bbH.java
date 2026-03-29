package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bbH extends bbE {
   private dS flower;
   private in state;

   public bbH(dS flowerIn, dR type) {
      this.setGeneratedBlock(flowerIn, type);
   }

   public void setGeneratedBlock(dS flowerIn, dR typeIn) {
      this.flower = flowerIn;
      this.state = flowerIn.getDefaultState().withProperty(flowerIn.getTypeProperty(), typeIn);
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      for(int i = 0; i < 64; ++i) {
         BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && this.flower.canBlockStay(worldIn, blockpos, this.state)) {
            worldIn.setBlockState(blockpos, this.state, 2);
         }
      }

      return true;
   }
}
