package neo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class ZM extends ZL {
   private final Zi biome;

   public ZM(Zi biomeIn) {
      this.biome = biomeIn;
   }

   public Zi getBiome(BlockPos pos) {
      return this.biome;
   }

   public Zi[] getBiomesForGeneration(Zi[] biomes, int x, int z, int width, int height) {
      if (biomes == null || biomes.length < width * height) {
         biomes = new Zi[width * height];
      }

      Arrays.fill(biomes, 0, width * height, this.biome);
      return biomes;
   }

   public Zi[] getBiomes(@Nullable Zi[] oldBiomeList, int x, int z, int width, int depth) {
      if (oldBiomeList == null || oldBiomeList.length < width * depth) {
         oldBiomeList = new Zi[width * depth];
      }

      Arrays.fill(oldBiomeList, 0, width * depth, this.biome);
      return oldBiomeList;
   }

   public Zi[] getBiomes(@Nullable Zi[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
      return this.getBiomes(listToReuse, x, z, width, length);
   }

   @Nullable
   public BlockPos findBiomePosition(int x, int z, int range, List<Zi> biomes, Random random) {
      return biomes.contains(this.biome) ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1)) : null;
   }

   public boolean areBiomesViable(int x, int z, int radius, List<Zi> allowed) {
      return allowed.contains(this.biome);
   }

   public boolean isFixedBiome() {
      return true;
   }

   public Zi getFixedBiome() {
      return this.biome;
   }
}
