package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZD extends Zi {
   private final bbE silverfishSpawner;
   private final bcg spruceGenerator;
   private final ZC type;

   protected ZD(ZC p_i46710_1_, Zf properties) {
      super(properties);
      this.silverfishSpawner = new bbW(Nk.MONSTER_EGG.getDefaultState().withProperty(gB.VARIANT, gA.STONE), 9);
      this.spruceGenerator = new bcg(false);
      if (p_i46710_1_ == ZC.EXTRA_TREES) {
         this.decorator.treesPerChunk = 3;
      }

      this.spawnableCreatureList.add(new Zg(LK.class, 5, 4, 6));
      this.type = p_i46710_1_;
   }

   public bbn getRandomTreeFeature(Random rand) {
      return (bbn)(rand.nextInt(3) > 0 ? this.spruceGenerator : super.getRandomTreeFeature(rand));
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      super.decorate(worldIn, rand, pos);
      int i = 3 + rand.nextInt(6);

      int j1;
      int k1;
      int l1;
      int i2;
      for(j1 = 0; j1 < i; ++j1) {
         k1 = rand.nextInt(16);
         l1 = rand.nextInt(28) + 4;
         i2 = rand.nextInt(16);
         BlockPos blockpos = pos.add(k1, l1, i2);
         if (worldIn.getBlockState(blockpos).getBlock() == Nk.STONE) {
            worldIn.setBlockState(blockpos, Nk.EMERALD_ORE.getDefaultState(), 2);
         }
      }

      for(j1 = 0; j1 < 7; ++j1) {
         k1 = rand.nextInt(16);
         l1 = rand.nextInt(64);
         i2 = rand.nextInt(16);
         this.silverfishSpawner.generate(worldIn, rand, pos.add(k1, l1, i2));
      }

   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      this.topBlock = Nk.GRASS.getDefaultState();
      this.fillerBlock = Nk.DIRT.getDefaultState();
      if ((noiseVal < -1.0 || noiseVal > 2.0) && this.type == ZC.MUTATED) {
         this.topBlock = Nk.GRAVEL.getDefaultState();
         this.fillerBlock = Nk.GRAVEL.getDefaultState();
      } else if (noiseVal > 1.0 && this.type != ZC.EXTRA_TREES) {
         this.topBlock = Nk.STONE.getDefaultState();
         this.fillerBlock = Nk.STONE.getDefaultState();
      }

      this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
   }
}
