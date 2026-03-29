package neo;

import java.util.Random;

public class cW extends co {
   public cW() {
      super(hM.CLAY);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.CLAY_BALL;
   }

   public int quantityDropped(Random random) {
      return 4;
   }
}
