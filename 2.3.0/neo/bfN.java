package neo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bfN extends bdr {
   private final int featureSpacing = 80;
   private final int minFeatureSeparation = 20;
   public static final List<Zi> ALLOWED_BIOMES;
   private final bbg provider;

   public bfN(bbg providerIn) {
      this.provider = providerIn;
   }

   public String getStructureName() {
      return "Mansion";
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX;
      int j = chunkZ;
      if (chunkX < 0) {
         i = chunkX - 79;
      }

      if (chunkZ < 0) {
         j = chunkZ - 79;
      }

      int k = i / 80;
      int l = j / 80;
      Random random = this.world.setRandomSeed(k, l, 10387319);
      k *= 80;
      l *= 80;
      k += (random.nextInt(60) + random.nextInt(60)) / 2;
      l += (random.nextInt(60) + random.nextInt(60)) / 2;
      if (chunkX == k && chunkZ == l) {
         boolean flag = this.world.getBiomeProvider().areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, ALLOWED_BIOMES);
         if (flag) {
            return true;
         }
      }

      return false;
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      this.world = worldIn;
      ZL biomeprovider = worldIn.getBiomeProvider();
      return biomeprovider.isFixedBiome() && biomeprovider.getFixedBiome() != Nj.ROOFED_FOREST ? null : findNearestStructurePosBySpacing(worldIn, this, pos, 80, 20, 10387319, true, 100, findUnexplored);
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bfM(this.world, this.provider, this.rand, chunkX, chunkZ);
   }

   static {
      ALLOWED_BIOMES = Arrays.asList(Nj.ROOFED_FOREST, Nj.MUTATED_ROOFED_FOREST);
   }
}
