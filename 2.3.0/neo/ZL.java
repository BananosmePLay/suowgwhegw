package neo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;

public class ZL {
   private bbk settings;
   private bco genBiomes;
   private bco biomeIndexLayer;
   private final Zl biomeCache;
   private final List<Zi> biomesToSpawnIn;

   protected ZL() {
      this.biomeCache = new Zl(this);
      this.biomesToSpawnIn = Lists.newArrayList(new Zi[]{Nj.FOREST, Nj.PLAINS, Nj.TAIGA, Nj.TAIGA_HILLS, Nj.FOREST_HILLS, Nj.JUNGLE, Nj.JUNGLE_HILLS});
   }

   private ZL(long seed, bix worldTypeIn, String options) {
      this();
      if (worldTypeIn == bix.CUSTOMIZED && !options.isEmpty()) {
         this.settings = bbi.jsonToFactory(options).build();
      }

      bco[] agenlayer = bco.initializeAllBiomeGenerators(seed, worldTypeIn, this.settings);
      this.genBiomes = agenlayer[0];
      this.biomeIndexLayer = agenlayer[1];
   }

   public ZL(bhY info) {
      this(info.getSeed(), info.getTerrainType(), info.getGeneratorOptions());
   }

   public List<Zi> getBiomesToSpawnIn() {
      return this.biomesToSpawnIn;
   }

   public Zi getBiome(BlockPos pos) {
      return this.getBiome(pos, (Zi)null);
   }

   public Zi getBiome(BlockPos pos, Zi defaultBiome) {
      return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
   }

   public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_) {
      return p_76939_1_;
   }

   public Zi[] getBiomesForGeneration(Zi[] biomes, int x, int z, int width, int height) {
      bcK.resetIntCache();
      if (biomes == null || biomes.length < width * height) {
         biomes = new Zi[width * height];
      }

      int[] aint = this.genBiomes.getInts(x, z, width, height);

      try {
         for(int i = 0; i < width * height; ++i) {
            biomes[i] = Zi.getBiome(aint[i], Nj.DEFAULT);
         }

         return biomes;
      } catch (Throwable var10) {
         Throwable throwable = var10;
         Er crashreport = Er.makeCrashReport(throwable, "Invalid Biome id");
         Ey crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
         crashreportcategory.addCrashSection("biomes[] size", biomes.length);
         crashreportcategory.addCrashSection("x", x);
         crashreportcategory.addCrashSection("z", z);
         crashreportcategory.addCrashSection("w", width);
         crashreportcategory.addCrashSection("h", height);
         throw new ReportedException(crashreport);
      }
   }

   public Zi[] getBiomes(@Nullable Zi[] oldBiomeList, int x, int z, int width, int depth) {
      return this.getBiomes(oldBiomeList, x, z, width, depth, true);
   }

   public Zi[] getBiomes(@Nullable Zi[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
      bcK.resetIntCache();
      if (listToReuse == null || listToReuse.length < width * length) {
         listToReuse = new Zi[width * length];
      }

      if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
         Zi[] abiome = this.biomeCache.getCachedBiomes(x, z);
         System.arraycopy(abiome, 0, listToReuse, 0, width * length);
         return listToReuse;
      } else {
         int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

         for(int i = 0; i < width * length; ++i) {
            listToReuse[i] = Zi.getBiome(aint[i], Nj.DEFAULT);
         }

         return listToReuse;
      }
   }

   public boolean areBiomesViable(int x, int z, int radius, List<Zi> allowed) {
      bcK.resetIntCache();
      int i = x - radius >> 2;
      int j = z - radius >> 2;
      int k = x + radius >> 2;
      int l = z + radius >> 2;
      int i1 = k - i + 1;
      int j1 = l - j + 1;
      int[] aint = this.genBiomes.getInts(i, j, i1, j1);

      try {
         for(int k1 = 0; k1 < i1 * j1; ++k1) {
            Zi biome = Zi.getBiome(aint[k1]);
            if (!allowed.contains(biome)) {
               return false;
            }
         }

         return true;
      } catch (Throwable var15) {
         Throwable throwable = var15;
         Er crashreport = Er.makeCrashReport(throwable, "Invalid Biome id");
         Ey crashreportcategory = crashreport.makeCategory("Layer");
         crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
         crashreportcategory.addCrashSection("x", x);
         crashreportcategory.addCrashSection("z", z);
         crashreportcategory.addCrashSection("radius", radius);
         crashreportcategory.addCrashSection("allowed", allowed);
         throw new ReportedException(crashreport);
      }
   }

   @Nullable
   public BlockPos findBiomePosition(int x, int z, int range, List<Zi> biomes, Random random) {
      bcK.resetIntCache();
      int i = x - range >> 2;
      int j = z - range >> 2;
      int k = x + range >> 2;
      int l = z + range >> 2;
      int i1 = k - i + 1;
      int j1 = l - j + 1;
      int[] aint = this.genBiomes.getInts(i, j, i1, j1);
      BlockPos blockpos = null;
      int k1 = 0;

      for(int l1 = 0; l1 < i1 * j1; ++l1) {
         int i2 = i + l1 % i1 << 2;
         int j2 = j + l1 / i1 << 2;
         Zi biome = Zi.getBiome(aint[l1]);
         if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
            blockpos = new BlockPos(i2, 0, j2);
            ++k1;
         }
      }

      return blockpos;
   }

   public void cleanupCache() {
      this.biomeCache.cleanupCache();
   }

   public boolean isFixedBiome() {
      return this.settings != null && this.settings.fixedBiome >= 0;
   }

   public Zi getFixedBiome() {
      return this.settings != null && this.settings.fixedBiome >= 0 ? Zi.getBiomeForId(this.settings.fixedBiome) : null;
   }
}
