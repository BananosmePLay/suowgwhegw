package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZP extends ZO {
   public ZP(Zf properties) {
      super(properties);
      this.decorator.treesPerChunk = 2;
      this.decorator.flowersPerChunk = 2;
      this.decorator.grassPerChunk = 5;
   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      this.topBlock = Nk.GRASS.getDefaultState();
      this.fillerBlock = Nk.DIRT.getDefaultState();
      if (noiseVal > 1.75) {
         this.topBlock = Nk.STONE.getDefaultState();
         this.fillerBlock = Nk.STONE.getDefaultState();
      } else if (noiseVal > -0.5) {
         this.topBlock = Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.COARSE_DIRT);
      }

      this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      this.decorator.decorate(worldIn, rand, this, pos);
   }
}
