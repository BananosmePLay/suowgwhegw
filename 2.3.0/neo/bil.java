package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public abstract class bil {
   public static final float[] MOON_PHASE_FACTORS = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
   protected bij world;
   private bix terrainType;
   private String generatorSettings;
   protected ZL biomeProvider;
   protected boolean doesWaterVaporize;
   protected boolean nether;
   protected boolean hasSkyLight;
   protected final float[] lightBrightnessTable = new float[16];
   private final float[] colorsSunriseSunset = new float[4];

   public bil() {
   }

   public final void setWorld(bij worldIn) {
      this.world = worldIn;
      this.terrainType = worldIn.getWorldInfo().getTerrainType();
      this.generatorSettings = worldIn.getWorldInfo().getGeneratorOptions();
      this.init();
      this.generateLightBrightnessTable();
   }

   protected void generateLightBrightnessTable() {
      float f = 0.0F;

      for(int i = 0; i <= 15; ++i) {
         float f1 = 1.0F - (float)i / 15.0F;
         this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 1.0F + 0.0F;
      }

   }

   protected void init() {
      this.hasSkyLight = true;
      bix worldtype = this.world.getWorldInfo().getTerrainType();
      if (worldtype == bix.FLAT) {
         bcl flatgeneratorinfo = bcl.createFlatGeneratorFromString(this.world.getWorldInfo().getGeneratorOptions());
         this.biomeProvider = new ZM(Zi.getBiome(flatgeneratorinfo.getBiome(), Nj.DEFAULT));
      } else if (worldtype == bix.DEBUG_ALL_BLOCK_STATES) {
         this.biomeProvider = new ZM(Nj.PLAINS);
      } else {
         this.biomeProvider = new ZL(this.world.getWorldInfo());
      }

   }

   public bcn createChunkGenerator() {
      if (this.terrainType == bix.FLAT) {
         return new bbe(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings);
      } else if (this.terrainType == bix.DEBUG_ALL_BLOCK_STATES) {
         return new bbc(this.world);
      } else {
         return this.terrainType == bix.CUSTOMIZED ? new bbg(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings) : new bbg(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings);
      }
   }

   public boolean canCoordinateBeSpawn(int x, int z) {
      BlockPos blockpos = new BlockPos(x, 0, z);
      if (this.world.getBiome(blockpos).ignorePlayerSpawnSuitability()) {
         return true;
      } else {
         return this.world.getGroundAboveSeaLevel(blockpos).getBlock() == Nk.GRASS;
      }
   }

   public float calculateCelestialAngle(long worldTime, float partialTicks) {
      int i = (int)(worldTime % 24000L);
      float f = ((float)i + partialTicks) / 24000.0F - 0.25F;
      if (f < 0.0F) {
         ++f;
      }

      if (f > 1.0F) {
         --f;
      }

      float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0) / 2.0);
      f += (f1 - f) / 3.0F;
      return f;
   }

   public int getMoonPhase(long worldTime) {
      return (int)(worldTime / 24000L % 8L + 8L) % 8;
   }

   public boolean isSurfaceWorld() {
      return true;
   }

   @Nullable
   public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
      float f = 0.4F;
      float f1 = MathHelper.cos(celestialAngle * 6.2831855F) - 0.0F;
      float f2 = -0.0F;
      if (f1 >= -0.4F && f1 <= 0.4F) {
         float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
         float f4 = 1.0F - (1.0F - MathHelper.sin(f3 * 3.1415927F)) * 0.99F;
         f4 *= f4;
         this.colorsSunriseSunset[0] = f3 * 0.3F + 0.7F;
         this.colorsSunriseSunset[1] = f3 * f3 * 0.7F + 0.2F;
         this.colorsSunriseSunset[2] = f3 * f3 * 0.0F + 0.2F;
         this.colorsSunriseSunset[3] = f4;
         return this.colorsSunriseSunset;
      } else {
         return null;
      }
   }

   public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
      float f = MathHelper.cos(p_76562_1_ * 6.2831855F) * 2.0F + 0.5F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      float f1 = 0.7529412F;
      float f2 = 0.84705883F;
      float f3 = 1.0F;
      f1 *= f * 0.94F + 0.06F;
      f2 *= f * 0.94F + 0.06F;
      f3 *= f * 0.91F + 0.09F;
      return new Vec3d((double)f1, (double)f2, (double)f3);
   }

   public boolean canRespawnHere() {
      return true;
   }

   public float getCloudHeight() {
      return 128.0F;
   }

   public boolean isSkyColored() {
      return true;
   }

   @Nullable
   public BlockPos getSpawnCoordinate() {
      return null;
   }

   public int getAverageGroundLevel() {
      return this.terrainType == bix.FLAT ? 4 : this.world.getSeaLevel() + 1;
   }

   public double getVoidFogYFactor() {
      return this.terrainType == bix.FLAT ? 1.0 : 0.03125;
   }

   public boolean doesXZShowFog(int x, int z) {
      return false;
   }

   public ZL getBiomeProvider() {
      return this.biomeProvider;
   }

   public boolean doesWaterVaporize() {
      return this.doesWaterVaporize;
   }

   public boolean hasSkyLight() {
      return this.hasSkyLight;
   }

   public boolean isNether() {
      return this.nether;
   }

   public float[] getLightBrightnessTable() {
      return this.lightBrightnessTable;
   }

   public bab createWorldBorder() {
      return new bab();
   }

   public void onPlayerAdded(MG player) {
   }

   public void onPlayerRemoved(MG player) {
   }

   public abstract baM getDimensionType();

   public void onWorldSave() {
   }

   public void onWorldUpdateEntities() {
   }

   public boolean canDropChunk(int x, int z) {
      return true;
   }
}
