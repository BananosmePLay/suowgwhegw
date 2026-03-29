package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZE extends Zi {
   private final boolean isEdge;
   private static final in JUNGLE_LOG;
   private static final in JUNGLE_LEAF;
   private static final in OAK_LEAF;

   public ZE(boolean isEdgeIn, Zf properties) {
      super(properties);
      this.isEdge = isEdgeIn;
      if (isEdgeIn) {
         this.decorator.treesPerChunk = 2;
      } else {
         this.decorator.treesPerChunk = 50;
      }

      this.decorator.grassPerChunk = 25;
      this.decorator.flowersPerChunk = 4;
      if (!isEdgeIn) {
         this.spawnableMonsterList.add(new Zg(LN.class, 2, 1, 1));
      }

      this.spawnableCreatureList.add(new Zg(LP.class, 40, 1, 2));
      this.spawnableCreatureList.add(new Zg(LA.class, 10, 4, 4));
   }

   public bbn getRandomTreeFeature(Random rand) {
      if (rand.nextInt(10) == 0) {
         return BIG_TREE_FEATURE;
      } else if (rand.nextInt(2) == 0) {
         return new bcb(JUNGLE_LOG, OAK_LEAF);
      } else {
         return (bbn)(!this.isEdge && rand.nextInt(3) == 0 ? new bbR(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF) : new bci(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true));
      }
   }

   public bbE getRandomWorldGenForGrass(Random rand) {
      return rand.nextInt(4) == 0 ? new bch(hj.FERN) : new bch(hj.GRASS);
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      super.decorate(worldIn, rand, pos);
      int i = rand.nextInt(16) + 8;
      int j = rand.nextInt(16) + 8;
      int k = rand.nextInt(worldIn.getHeight(pos.add(i, 0, j)).getY() * 2);
      (new bbT()).generate(worldIn, rand, pos.add(i, k, j));
      bcj worldgenvines = new bcj();

      for(int j1 = 0; j1 < 50; ++j1) {
         k = rand.nextInt(16) + 8;
         int l = true;
         int i1 = rand.nextInt(16) + 8;
         worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
      }

   }

   static {
      JUNGLE_LOG = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.JUNGLE);
      JUNGLE_LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.JUNGLE).withProperty(ew.CHECK_DECAY, false);
      OAK_LEAF = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.OAK).withProperty(ew.CHECK_DECAY, false);
   }
}
