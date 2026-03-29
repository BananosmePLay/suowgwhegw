package neo;

import java.util.Random;

class ZG extends Zr {
   // $FF: synthetic field
   final ZH this$0;

   private ZG(ZH this$0) {
      this.this$0 = this$0;
   }

   protected void generateOres(bij worldIn, Random random) {
      super.generateOres(worldIn, random);
      this.genStandardOre1(worldIn, random, 20, this.goldGen, 32, 80);
   }

   // $FF: synthetic method
   ZG(ZH x0, Object x1) {
      this(x0);
   }
}
