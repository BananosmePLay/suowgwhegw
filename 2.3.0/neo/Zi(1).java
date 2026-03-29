package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryNamespaced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Zi {
   private static final Logger LOGGER = LogManager.getLogger();
   protected static final in STONE;
   protected static final in AIR;
   protected static final in BEDROCK;
   protected static final in GRAVEL;
   protected static final in RED_SANDSTONE;
   protected static final in SANDSTONE;
   protected static final in ICE;
   protected static final in WATER;
   public static final ObjectIntIdentityMap<Zi> MUTATION_TO_BASE_ID_MAP;
   protected static final bcT TEMPERATURE_NOISE;
   protected static final bcT GRASS_COLOR_NOISE;
   protected static final bbz DOUBLE_PLANT_GENERATOR;
   protected static final bci TREE_FEATURE;
   protected static final bbq BIG_TREE_FEATURE;
   protected static final bce SWAMP_FEATURE;
   public static final RegistryNamespaced<ResourceLocation, Zi> REGISTRY;
   private final String biomeName;
   private final float baseHeight;
   private final float heightVariation;
   private final float temperature;
   private final float rainfall;
   private final int waterColor;
   private final boolean enableSnow;
   private final boolean enableRain;
   @Nullable
   private final String baseBiomeRegName;
   public in topBlock;
   public in fillerBlock;
   public Zr decorator;
   protected List<Zg> spawnableMonsterList;
   protected List<Zg> spawnableCreatureList;
   protected List<Zg> spawnableWaterCreatureList;
   protected List<Zg> spawnableCaveCreatureList;

   public static int getIdForBiome(Zi biome) {
      return REGISTRY.getIDForObject(biome);
   }

   @Nullable
   public static Zi getBiomeForId(int id) {
      return (Zi)REGISTRY.getObjectById(id);
   }

   @Nullable
   public static Zi getMutationForBiome(Zi biome) {
      return (Zi)MUTATION_TO_BASE_ID_MAP.getByValue(getIdForBiome(biome));
   }

   protected Zi(Zf properties) {
      this.topBlock = Nk.GRASS.getDefaultState();
      this.fillerBlock = Nk.DIRT.getDefaultState();
      this.spawnableMonsterList = Lists.newArrayList();
      this.spawnableCreatureList = Lists.newArrayList();
      this.spawnableWaterCreatureList = Lists.newArrayList();
      this.spawnableCaveCreatureList = Lists.newArrayList();
      this.biomeName = Zf.access$000(properties);
      this.baseHeight = Zf.access$100(properties);
      this.heightVariation = Zf.access$200(properties);
      this.temperature = Zf.access$300(properties);
      this.rainfall = Zf.access$400(properties);
      this.waterColor = Zf.access$500(properties);
      this.enableSnow = Zf.access$600(properties);
      this.enableRain = Zf.access$700(properties);
      this.baseBiomeRegName = Zf.access$800(properties);
      this.decorator = this.createBiomeDecorator();
      this.spawnableCreatureList.add(new Zg(Mb.class, 12, 4, 4));
      this.spawnableCreatureList.add(new Zg(LQ.class, 10, 4, 4));
      this.spawnableCreatureList.add(new Zg(LA.class, 10, 4, 4));
      this.spawnableCreatureList.add(new Zg(LB.class, 8, 4, 4));
      this.spawnableMonsterList.add(new Zg(KW.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Zg(Lk.class, 95, 4, 4));
      this.spawnableMonsterList.add(new Zg(Ll.class, 5, 1, 1));
      this.spawnableMonsterList.add(new Zg(KH.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Zg(JB.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Zg(KN.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Zg(JJ.class, 10, 1, 4));
      this.spawnableMonsterList.add(new Zg(Lg.class, 5, 1, 1));
      this.spawnableWaterCreatureList.add(new Zg(Mf.class, 10, 4, 4));
      this.spawnableCaveCreatureList.add(new Zg(Lz.class, 10, 8, 8));
   }

   protected Zr createBiomeDecorator() {
      return new Zr();
   }

   public boolean isMutation() {
      return this.baseBiomeRegName != null;
   }

   public bbn getRandomTreeFeature(Random rand) {
      return (bbn)(rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
   }

   public bbE getRandomWorldGenForGrass(Random rand) {
      return new bch(hj.GRASS);
   }

   public dR pickRandomFlower(Random rand, BlockPos pos) {
      return rand.nextInt(3) > 0 ? dR.DANDELION : dR.POPPY;
   }

   public int getSkyColorByTemp(float currentTemperature) {
      currentTemperature /= 3.0F;
      currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
      return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
   }

   public List<Zg> getSpawnableList(IC creatureType) {
      switch (creatureType) {
         case MONSTER:
            return this.spawnableMonsterList;
         case CREATURE:
            return this.spawnableCreatureList;
         case WATER_CREATURE:
            return this.spawnableWaterCreatureList;
         case AMBIENT:
            return this.spawnableCaveCreatureList;
         default:
            return Collections.emptyList();
      }
   }

   public boolean getEnableSnow() {
      return this.isSnowyBiome();
   }

   public boolean canRain() {
      return this.isSnowyBiome() ? false : this.enableRain;
   }

   public boolean isHighHumidity() {
      return this.getRainfall() > 0.85F;
   }

   public float getSpawningChance() {
      return 0.1F;
   }

   public final float getTemperature(BlockPos pos) {
      if (pos.getY() > 64) {
         float f = (float)(TEMPERATURE_NOISE.getValue((double)((float)pos.getX() / 8.0F), (double)((float)pos.getZ() / 8.0F)) * 4.0);
         return this.getDefaultTemperature() - (f + (float)pos.getY() - 64.0F) * 0.05F / 30.0F;
      } else {
         return this.getDefaultTemperature();
      }
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      this.decorator.decorate(worldIn, rand, this, pos);
   }

   public int getGrassColorAtPos(BlockPos pos) {
      double d0 = (double)MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
      double d1 = (double)MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
      return baK.getGrassColor(d0, d1);
   }

   public int getFoliageColorAtPos(BlockPos pos) {
      double d0 = (double)MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
      double d1 = (double)MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
      return baJ.getFoliageColor(d0, d1);
   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
   }

   public final void generateBiomeTerrain(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      int i = worldIn.getSeaLevel();
      in iblockstate = this.topBlock;
      in iblockstate1 = this.fillerBlock;
      int j = -1;
      int k = (int)(noiseVal / 3.0 + 3.0 + rand.nextDouble() * 0.25);
      int l = x & 15;
      int i1 = z & 15;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int j1 = 255; j1 >= 0; --j1) {
         if (j1 <= rand.nextInt(5)) {
            chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
         } else {
            in iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
            if (iblockstate2.getMaterial() == hM.AIR) {
               j = -1;
            } else if (iblockstate2.getBlock() == Nk.STONE) {
               if (j == -1) {
                  if (k <= 0) {
                     iblockstate = AIR;
                     iblockstate1 = STONE;
                  } else if (j1 >= i - 4 && j1 <= i + 1) {
                     iblockstate = this.topBlock;
                     iblockstate1 = this.fillerBlock;
                  }

                  if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == hM.AIR)) {
                     if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                        iblockstate = ICE;
                     } else {
                        iblockstate = WATER;
                     }
                  }

                  j = k;
                  if (j1 >= i - 1) {
                     chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                  } else if (j1 < i - 7 - k) {
                     iblockstate = AIR;
                     iblockstate1 = STONE;
                     chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
                  } else {
                     chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                  }
               } else if (j > 0) {
                  --j;
                  chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                  if (j == 0 && iblockstate1.getBlock() == Nk.SAND && k > 1) {
                     j = rand.nextInt(4) + Math.max(0, j1 - 63);
                     iblockstate1 = iblockstate1.getValue(gk.VARIANT) == gj.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                  }
               }
            }
         }
      }

   }

   public Class<? extends Zi> getBiomeClass() {
      return this.getClass();
   }

   public Zh getTempCategory() {
      if ((double)this.getDefaultTemperature() < 0.2) {
         return Zh.COLD;
      } else {
         return (double)this.getDefaultTemperature() < 1.0 ? Zh.MEDIUM : Zh.WARM;
      }
   }

   @Nullable
   public static Zi getBiome(int id) {
      return getBiome(id, (Zi)null);
   }

   public static Zi getBiome(int biomeId, Zi fallback) {
      Zi biome = getBiomeForId(biomeId);
      return biome == null ? fallback : biome;
   }

   public boolean ignorePlayerSpawnSuitability() {
      return false;
   }

   public final float getBaseHeight() {
      return this.baseHeight;
   }

   public final float getRainfall() {
      return this.rainfall;
   }

   public final String getBiomeName() {
      return this.biomeName;
   }

   public final float getHeightVariation() {
      return this.heightVariation;
   }

   public final float getDefaultTemperature() {
      return this.temperature;
   }

   public final int getWaterColor() {
      return this.waterColor;
   }

   public final boolean isSnowyBiome() {
      return this.enableSnow;
   }

   public static void registerBiomes() {
      registerBiome(0, "ocean", new ZJ((new Zf("Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F)));
      registerBiome(1, "plains", new ZK(false, (new Zf("Plains")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.4F)));
      registerBiome(2, "desert", new Zs((new Zf("Desert")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(3, "extreme_hills", new ZD(ZC.NORMAL, (new Zf("Extreme Hills")).setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(4, "forest", new Zy(Zx.NORMAL, (new Zf("Forest")).setTemperature(0.7F).setRainfall(0.8F)));
      registerBiome(5, "taiga", new ZU(ZT.NORMAL, (new Zf("Taiga")).setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F)));
      registerBiome(6, "swampland", new ZS((new Zf("Swampland")).setBaseHeight(-0.2F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.9F).setWaterColor(14745518)));
      registerBiome(7, "river", new ZN((new Zf("River")).setBaseHeight(-0.5F).setHeightVariation(0.0F)));
      registerBiome(8, "hell", new ZA((new Zf("Hell")).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(9, "sky", new Zt((new Zf("The End")).setRainDisabled()));
      registerBiome(10, "frozen_ocean", new ZJ((new Zf("FrozenOcean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled()));
      registerBiome(11, "frozen_river", new ZN((new Zf("FrozenRiver")).setBaseHeight(-0.5F).setHeightVariation(0.0F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled()));
      registerBiome(12, "ice_flats", new ZQ(false, (new Zf("Ice Plains")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled()));
      registerBiome(13, "ice_mountains", new ZQ(false, (new Zf("Ice Mountains")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled()));
      registerBiome(14, "mushroom_island", new ZI((new Zf("MushroomIsland")).setBaseHeight(0.2F).setHeightVariation(0.3F).setTemperature(0.9F).setRainfall(1.0F)));
      registerBiome(15, "mushroom_island_shore", new ZI((new Zf("MushroomIslandShore")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.9F).setRainfall(1.0F)));
      registerBiome(16, "beaches", new Zj((new Zf("Beach")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F)));
      registerBiome(17, "desert_hills", new Zs((new Zf("DesertHills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(18, "forest_hills", new Zy(Zx.NORMAL, (new Zf("ForestHills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.7F).setRainfall(0.8F)));
      registerBiome(19, "taiga_hills", new ZU(ZT.NORMAL, (new Zf("TaigaHills")).setTemperature(0.25F).setRainfall(0.8F).setBaseHeight(0.45F).setHeightVariation(0.3F)));
      registerBiome(20, "smaller_extreme_hills", new ZD(ZC.EXTRA_TREES, (new Zf("Extreme Hills Edge")).setBaseHeight(0.8F).setHeightVariation(0.3F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(21, "jungle", new ZE(false, (new Zf("Jungle")).setTemperature(0.95F).setRainfall(0.9F)));
      registerBiome(22, "jungle_hills", new ZE(false, (new Zf("JungleHills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.95F).setRainfall(0.9F)));
      registerBiome(23, "jungle_edge", new ZE(true, (new Zf("JungleEdge")).setTemperature(0.95F).setRainfall(0.8F)));
      registerBiome(24, "deep_ocean", new ZJ((new Zf("Deep Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F)));
      registerBiome(25, "stone_beach", new ZR((new Zf("Stone Beach")).setBaseHeight(0.1F).setHeightVariation(0.8F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(26, "cold_beach", new Zj((new Zf("Cold Beach")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.05F).setRainfall(0.3F).setSnowEnabled()));
      registerBiome(27, "birch_forest", new Zy(Zx.BIRCH, (new Zf("Birch Forest")).setTemperature(0.6F).setRainfall(0.6F)));
      registerBiome(28, "birch_forest_hills", new Zy(Zx.BIRCH, (new Zf("Birch Forest Hills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.6F).setRainfall(0.6F)));
      registerBiome(29, "roofed_forest", new Zy(Zx.ROOFED, (new Zf("Roofed Forest")).setTemperature(0.7F).setRainfall(0.8F)));
      registerBiome(30, "taiga_cold", new ZU(ZT.NORMAL, (new Zf("Cold Taiga")).setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(-0.5F).setRainfall(0.4F).setSnowEnabled()));
      registerBiome(31, "taiga_cold_hills", new ZU(ZT.NORMAL, (new Zf("Cold Taiga Hills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(-0.5F).setRainfall(0.4F).setSnowEnabled()));
      registerBiome(32, "redwood_taiga", new ZU(ZT.MEGA, (new Zf("Mega Taiga")).setTemperature(0.3F).setRainfall(0.8F).setBaseHeight(0.2F).setHeightVariation(0.2F)));
      registerBiome(33, "redwood_taiga_hills", new ZU(ZT.MEGA, (new Zf("Mega Taiga Hills")).setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.3F).setRainfall(0.8F)));
      registerBiome(34, "extreme_hills_with_trees", new ZD(ZC.EXTRA_TREES, (new Zf("Extreme Hills+")).setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(35, "savanna", new ZO((new Zf("Savanna")).setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(1.2F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(36, "savanna_rock", new ZO((new Zf("Savanna Plateau")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(1.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(37, "mesa", new ZH(false, false, (new Zf("Mesa")).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(38, "mesa_rock", new ZH(false, true, (new Zf("Mesa Plateau F")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(39, "mesa_clear_rock", new ZH(false, false, (new Zf("Mesa Plateau")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(127, "void", new ZV((new Zf("The Void")).setRainDisabled()));
      registerBiome(129, "mutated_plains", new ZK(true, (new Zf("Sunflower Plains")).setBaseBiome("plains").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.4F)));
      registerBiome(130, "mutated_desert", new Zs((new Zf("Desert M")).setBaseBiome("desert").setBaseHeight(0.225F).setHeightVariation(0.25F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(131, "mutated_extreme_hills", new ZD(ZC.MUTATED, (new Zf("Extreme Hills M")).setBaseBiome("extreme_hills").setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(132, "mutated_forest", new Zy(Zx.FLOWER, (new Zf("Flower Forest")).setBaseBiome("forest").setHeightVariation(0.4F).setTemperature(0.7F).setRainfall(0.8F)));
      registerBiome(133, "mutated_taiga", new ZU(ZT.NORMAL, (new Zf("Taiga M")).setBaseBiome("taiga").setBaseHeight(0.3F).setHeightVariation(0.4F).setTemperature(0.25F).setRainfall(0.8F)));
      registerBiome(134, "mutated_swampland", new ZS((new Zf("Swampland M")).setBaseBiome("swampland").setBaseHeight(-0.1F).setHeightVariation(0.3F).setTemperature(0.8F).setRainfall(0.9F).setWaterColor(14745518)));
      registerBiome(140, "mutated_ice_flats", new ZQ(true, (new Zf("Ice Plains Spikes")).setBaseBiome("ice_flats").setBaseHeight(0.425F).setHeightVariation(0.45000002F).setTemperature(0.0F).setRainfall(0.5F).setSnowEnabled()));
      registerBiome(149, "mutated_jungle", new ZE(false, (new Zf("Jungle M")).setBaseBiome("jungle").setBaseHeight(0.2F).setHeightVariation(0.4F).setTemperature(0.95F).setRainfall(0.9F)));
      registerBiome(151, "mutated_jungle_edge", new ZE(true, (new Zf("JungleEdge M")).setBaseBiome("jungle_edge").setBaseHeight(0.2F).setHeightVariation(0.4F).setTemperature(0.95F).setRainfall(0.8F)));
      registerBiome(155, "mutated_birch_forest", new Zz((new Zf("Birch Forest M")).setBaseBiome("birch_forest").setBaseHeight(0.2F).setHeightVariation(0.4F).setTemperature(0.6F).setRainfall(0.6F)));
      registerBiome(156, "mutated_birch_forest_hills", new Zz((new Zf("Birch Forest Hills M")).setBaseBiome("birch_forest_hills").setBaseHeight(0.55F).setHeightVariation(0.5F).setTemperature(0.6F).setRainfall(0.6F)));
      registerBiome(157, "mutated_roofed_forest", new Zy(Zx.ROOFED, (new Zf("Roofed Forest M")).setBaseBiome("roofed_forest").setBaseHeight(0.2F).setHeightVariation(0.4F).setTemperature(0.7F).setRainfall(0.8F)));
      registerBiome(158, "mutated_taiga_cold", new ZU(ZT.NORMAL, (new Zf("Cold Taiga M")).setBaseBiome("taiga_cold").setBaseHeight(0.3F).setHeightVariation(0.4F).setTemperature(-0.5F).setRainfall(0.4F).setSnowEnabled()));
      registerBiome(160, "mutated_redwood_taiga", new ZU(ZT.MEGA_SPRUCE, (new Zf("Mega Spruce Taiga")).setBaseBiome("redwood_taiga").setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F)));
      registerBiome(161, "mutated_redwood_taiga_hills", new ZU(ZT.MEGA_SPRUCE, (new Zf("Redwood Taiga Hills M")).setBaseBiome("redwood_taiga_hills").setBaseHeight(0.2F).setHeightVariation(0.2F).setTemperature(0.25F).setRainfall(0.8F)));
      registerBiome(162, "mutated_extreme_hills_with_trees", new ZD(ZC.MUTATED, (new Zf("Extreme Hills+ M")).setBaseBiome("extreme_hills_with_trees").setBaseHeight(1.0F).setHeightVariation(0.5F).setTemperature(0.2F).setRainfall(0.3F)));
      registerBiome(163, "mutated_savanna", new ZP((new Zf("Savanna M")).setBaseBiome("savanna").setBaseHeight(0.3625F).setHeightVariation(1.225F).setTemperature(1.1F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(164, "mutated_savanna_rock", new ZP((new Zf("Savanna Plateau M")).setBaseBiome("savanna_rock").setBaseHeight(1.05F).setHeightVariation(1.2125001F).setTemperature(1.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(165, "mutated_mesa", new ZH(true, false, (new Zf("Mesa (Bryce)")).setBaseBiome("mesa").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(166, "mutated_mesa_rock", new ZH(false, true, (new Zf("Mesa Plateau F M")).setBaseBiome("mesa_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
      registerBiome(167, "mutated_mesa_clear_rock", new ZH(false, false, (new Zf("Mesa Plateau M")).setBaseBiome("mesa_clear_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled()));
   }

   private static void registerBiome(int id, String name, Zi biome) {
      REGISTRY.register(id, new ResourceLocation(name), biome);
      if (biome.isMutation()) {
         MUTATION_TO_BASE_ID_MAP.put(biome, getIdForBiome((Zi)REGISTRY.getObject(new ResourceLocation(biome.baseBiomeRegName))));
      }

   }

   static {
      STONE = Nk.STONE.getDefaultState();
      AIR = Nk.AIR.getDefaultState();
      BEDROCK = Nk.BEDROCK.getDefaultState();
      GRAVEL = Nk.GRAVEL.getDefaultState();
      RED_SANDSTONE = Nk.RED_SANDSTONE.getDefaultState();
      SANDSTONE = Nk.SANDSTONE.getDefaultState();
      ICE = Nk.ICE.getDefaultState();
      WATER = Nk.WATER.getDefaultState();
      MUTATION_TO_BASE_ID_MAP = new ObjectIntIdentityMap();
      TEMPERATURE_NOISE = new bcT(new Random(1234L), 1);
      GRASS_COLOR_NOISE = new bcT(new Random(2345L), 1);
      DOUBLE_PLANT_GENERATOR = new bbz();
      TREE_FEATURE = new bci(false);
      BIG_TREE_FEATURE = new bbq(false);
      SWAMP_FEATURE = new bce();
      REGISTRY = new RegistryNamespaced();
   }
}
