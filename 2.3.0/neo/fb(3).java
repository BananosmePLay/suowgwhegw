package neo;

import java.util.Random;

public class fb extends co {
   public fb() {
      super(hM.PACKED_ICE);
      this.slipperiness = 0.98F;
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int quantityDropped(Random random) {
      return 0;
   }
}
