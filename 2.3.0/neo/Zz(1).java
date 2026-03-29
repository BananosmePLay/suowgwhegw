package neo;

import java.util.Random;

public class Zz extends Zy {
   public Zz(Zf properties) {
      super(Zx.BIRCH, properties);
   }

   public bbn getRandomTreeFeature(Random rand) {
      return rand.nextBoolean() ? Zy.SUPER_BIRCH_TREE : Zy.BIRCH_TREE;
   }
}
