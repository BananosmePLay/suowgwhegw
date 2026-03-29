package neo;

import java.util.Random;

public class eG extends co {
   protected eG() {
      super(hM.GOURD, hK.LIME);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.MELON;
   }

   public int quantityDropped(Random random) {
      return 3 + random.nextInt(5);
   }

   public int quantityDroppedWithBonus(int fortune, Random random) {
      return Math.min(9, this.quantityDropped(random) + random.nextInt(1 + fortune));
   }
}
