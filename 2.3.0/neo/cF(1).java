package neo;

import java.util.Random;

public class cF extends co {
   public cF() {
      super(hM.WOOD);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int quantityDropped(Random random) {
      return 3;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.BOOK;
   }
}
