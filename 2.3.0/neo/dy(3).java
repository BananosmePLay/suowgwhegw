package neo;

import java.util.Random;

public class dy extends co {
   public dy(hM materialIn) {
      super(materialIn);
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }
}
