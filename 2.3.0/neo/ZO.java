package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZO extends Zi {
   private static final bca SAVANNA_TREE = new bca(false);

   protected ZO(Zf properties) {
      super(properties);
      this.spawnableCreatureList.add(new Zg(LF.class, 1, 2, 6));
      this.spawnableCreatureList.add(new Zg(LC.class, 1, 1, 1));
      if (this.getBaseHeight() > 1.1F) {
         this.spawnableCreatureList.add(new Zg(LK.class, 8, 4, 4));
      }

      this.decorator.treesPerChunk = 1;
      this.decorator.flowersPerChunk = 4;
      this.decorator.grassPerChunk = 20;
   }

   public bbn getRandomTreeFeature(Random rand) {
      return (bbn)(rand.nextInt(5) > 0 ? SAVANNA_TREE : TREE_FEATURE);
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      DOUBLE_PLANT_GENERATOR.setPlantType(dq.GRASS);

      for(int i = 0; i < 7; ++i) {
         int j = rand.nextInt(16) + 8;
         int k = rand.nextInt(16) + 8;
         int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
         DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
      }

      super.decorate(worldIn, rand, pos);
   }

   public Class<? extends Zi> getBiomeClass() {
      return ZO.class;
   }
}
