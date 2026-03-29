package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ef extends dH {
   public ef() {
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      if (fortune > 3) {
         fortune = 3;
      }

      return rand.nextInt(10 - fortune * 3) == 0 ? NK.FLINT : super.getItemDropped(state, rand, fortune);
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.STONE;
   }

   public int getDustColor(in state) {
      return -8356741;
   }
}
