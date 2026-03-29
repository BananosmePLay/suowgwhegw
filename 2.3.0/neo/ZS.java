package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZS extends Zi {
   protected static final in WATER_LILY;

   protected ZS(Zf properties) {
      super(properties);
      this.decorator.treesPerChunk = 2;
      this.decorator.flowersPerChunk = 1;
      this.decorator.deadBushPerChunk = 1;
      this.decorator.mushroomsPerChunk = 8;
      this.decorator.reedsPerChunk = 10;
      this.decorator.clayPerChunk = 1;
      this.decorator.waterlilyPerChunk = 4;
      this.decorator.sandPatchesPerChunk = 0;
      this.decorator.gravelPatchesPerChunk = 0;
      this.decorator.grassPerChunk = 5;
      this.spawnableMonsterList.add(new Zg(KN.class, 1, 1, 1));
   }

   public bbn getRandomTreeFeature(Random rand) {
      return SWAMP_FEATURE;
   }

   public int getGrassColorAtPos(BlockPos pos) {
      double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225, (double)pos.getZ() * 0.0225);
      return d0 < -0.1 ? 5011004 : 6975545;
   }

   public int getFoliageColorAtPos(BlockPos pos) {
      return 6975545;
   }

   public dR pickRandomFlower(Random rand, BlockPos pos) {
      return dR.BLUE_ORCHID;
   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25, (double)z * 0.25);
      if (d0 > 0.0) {
         int i = x & 15;
         int j = z & 15;

         for(int k = 255; k >= 0; --k) {
            if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != hM.AIR) {
               if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Nk.WATER) {
                  chunkPrimerIn.setBlockState(j, k, i, WATER);
                  if (d0 < 0.12) {
                     chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);
                  }
               }
               break;
            }
         }
      }

      this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      super.decorate(worldIn, rand, pos);
      if (rand.nextInt(64) == 0) {
         (new bbI()).generate(worldIn, rand, pos);
      }

   }

   static {
      WATER_LILY = Nk.WATERLILY.getDefaultState();
   }
}
