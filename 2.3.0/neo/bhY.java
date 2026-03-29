package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;

public class bhY {
   private String versionName;
   private int versionId;
   private boolean versionSnapshot;
   public static final baV DEFAULT_DIFFICULTY;
   private long randomSeed;
   private bix terrainType;
   private String generatorOptions;
   private int spawnX;
   private int spawnY;
   private int spawnZ;
   private long totalTime;
   private long worldTime;
   private long lastTimePlayed;
   private long sizeOnDisk;
   private QQ playerTag;
   private int dimension;
   private String levelName;
   private int saveVersion;
   private int cleanWeatherTime;
   private boolean raining;
   private int rainTime;
   private boolean thundering;
   private int thunderTime;
   private bbb gameType;
   private boolean mapFeaturesEnabled;
   private boolean hardcore;
   private boolean allowCommands;
   private boolean initialized;
   private baV difficulty;
   private boolean difficultyLocked;
   private double borderCenterX;
   private double borderCenterZ;
   private double borderSize;
   private long borderSizeLerpTime;
   private double borderSizeLerpTarget;
   private double borderSafeZone;
   private double borderDamagePerBlock;
   private int borderWarningDistance;
   private int borderWarningTime;
   private final Map<baM, QQ> dimensionData;
   private bba gameRules;

   protected bhY() {
      this.terrainType = bix.DEFAULT;
      this.generatorOptions = "";
      this.borderSize = 6.0E7;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.dimensionData = Maps.newEnumMap(baM.class);
      this.gameRules = new bba();
   }

   public static void registerFixes(DataFixer fixer) {
      fixer.registerWalker(FixTypes.LEVEL, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (compound.hasKey("Player", 10)) {
               compound.setTag("Player", fixer.process(FixTypes.PLAYER, compound.getCompoundTag("Player"), versionIn));
            }

            return compound;
         }
      });
   }

   public bhY(QQ nbt) {
      this.terrainType = bix.DEFAULT;
      this.generatorOptions = "";
      this.borderSize = 6.0E7;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.dimensionData = Maps.newEnumMap(baM.class);
      this.gameRules = new bba();
      QQ nbttagcompound1;
      if (nbt.hasKey("Version", 10)) {
         nbttagcompound1 = nbt.getCompoundTag("Version");
         this.versionName = nbttagcompound1.getString("Name");
         this.versionId = nbttagcompound1.getInteger("Id");
         this.versionSnapshot = nbttagcompound1.getBoolean("Snapshot");
      }

      this.randomSeed = nbt.getLong("RandomSeed");
      if (nbt.hasKey("generatorName", 8)) {
         String s1 = nbt.getString("generatorName");
         this.terrainType = bix.byName(s1);
         if (this.terrainType == null) {
            this.terrainType = bix.DEFAULT;
         } else if (this.terrainType.isVersioned()) {
            int i = 0;
            if (nbt.hasKey("generatorVersion", 99)) {
               i = nbt.getInteger("generatorVersion");
            }

            this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(i);
         }

         if (nbt.hasKey("generatorOptions", 8)) {
            this.generatorOptions = nbt.getString("generatorOptions");
         }
      }

      this.gameType = bbb.getByID(nbt.getInteger("GameType"));
      if (nbt.hasKey("MapFeatures", 99)) {
         this.mapFeaturesEnabled = nbt.getBoolean("MapFeatures");
      } else {
         this.mapFeaturesEnabled = true;
      }

      this.spawnX = nbt.getInteger("SpawnX");
      this.spawnY = nbt.getInteger("SpawnY");
      this.spawnZ = nbt.getInteger("SpawnZ");
      this.totalTime = nbt.getLong("Time");
      if (nbt.hasKey("DayTime", 99)) {
         this.worldTime = nbt.getLong("DayTime");
      } else {
         this.worldTime = this.totalTime;
      }

      this.lastTimePlayed = nbt.getLong("LastPlayed");
      this.sizeOnDisk = nbt.getLong("SizeOnDisk");
      this.levelName = nbt.getString("LevelName");
      this.saveVersion = nbt.getInteger("version");
      this.cleanWeatherTime = nbt.getInteger("clearWeatherTime");
      this.rainTime = nbt.getInteger("rainTime");
      this.raining = nbt.getBoolean("raining");
      this.thunderTime = nbt.getInteger("thunderTime");
      this.thundering = nbt.getBoolean("thundering");
      this.hardcore = nbt.getBoolean("hardcore");
      if (nbt.hasKey("initialized", 99)) {
         this.initialized = nbt.getBoolean("initialized");
      } else {
         this.initialized = true;
      }

      if (nbt.hasKey("allowCommands", 99)) {
         this.allowCommands = nbt.getBoolean("allowCommands");
      } else {
         this.allowCommands = this.gameType == bbb.CREATIVE;
      }

      if (nbt.hasKey("Player", 10)) {
         this.playerTag = nbt.getCompoundTag("Player");
         this.dimension = this.playerTag.getInteger("Dimension");
      }

      if (nbt.hasKey("GameRules", 10)) {
         this.gameRules.readFromNBT(nbt.getCompoundTag("GameRules"));
      }

      if (nbt.hasKey("Difficulty", 99)) {
         this.difficulty = baV.byId(nbt.getByte("Difficulty"));
      }

      if (nbt.hasKey("DifficultyLocked", 1)) {
         this.difficultyLocked = nbt.getBoolean("DifficultyLocked");
      }

      if (nbt.hasKey("BorderCenterX", 99)) {
         this.borderCenterX = nbt.getDouble("BorderCenterX");
      }

      if (nbt.hasKey("BorderCenterZ", 99)) {
         this.borderCenterZ = nbt.getDouble("BorderCenterZ");
      }

      if (nbt.hasKey("BorderSize", 99)) {
         this.borderSize = nbt.getDouble("BorderSize");
      }

      if (nbt.hasKey("BorderSizeLerpTime", 99)) {
         this.borderSizeLerpTime = nbt.getLong("BorderSizeLerpTime");
      }

      if (nbt.hasKey("BorderSizeLerpTarget", 99)) {
         this.borderSizeLerpTarget = nbt.getDouble("BorderSizeLerpTarget");
      }

      if (nbt.hasKey("BorderSafeZone", 99)) {
         this.borderSafeZone = nbt.getDouble("BorderSafeZone");
      }

      if (nbt.hasKey("BorderDamagePerBlock", 99)) {
         this.borderDamagePerBlock = nbt.getDouble("BorderDamagePerBlock");
      }

      if (nbt.hasKey("BorderWarningBlocks", 99)) {
         this.borderWarningDistance = nbt.getInteger("BorderWarningBlocks");
      }

      if (nbt.hasKey("BorderWarningTime", 99)) {
         this.borderWarningTime = nbt.getInteger("BorderWarningTime");
      }

      if (nbt.hasKey("DimensionData", 10)) {
         nbttagcompound1 = nbt.getCompoundTag("DimensionData");
         Iterator var6 = nbttagcompound1.getKeySet().iterator();

         while(var6.hasNext()) {
            String s = (String)var6.next();
            this.dimensionData.put(baM.getById(Integer.parseInt(s)), nbttagcompound1.getCompoundTag(s));
         }
      }

   }

   public bhY(biw settings, String name) {
      this.terrainType = bix.DEFAULT;
      this.generatorOptions = "";
      this.borderSize = 6.0E7;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.dimensionData = Maps.newEnumMap(baM.class);
      this.gameRules = new bba();
      this.populateFromWorldSettings(settings);
      this.levelName = name;
      this.difficulty = DEFAULT_DIFFICULTY;
      this.initialized = false;
   }

   public void populateFromWorldSettings(biw settings) {
      this.randomSeed = settings.getSeed();
      this.gameType = settings.getGameType();
      this.mapFeaturesEnabled = settings.isMapFeaturesEnabled();
      this.hardcore = settings.getHardcoreEnabled();
      this.terrainType = settings.getTerrainType();
      this.generatorOptions = settings.getGeneratorOptions();
      this.allowCommands = settings.areCommandsAllowed();
   }

   public bhY(bhY worldInformation) {
      this.terrainType = bix.DEFAULT;
      this.generatorOptions = "";
      this.borderSize = 6.0E7;
      this.borderSafeZone = 5.0;
      this.borderDamagePerBlock = 0.2;
      this.borderWarningDistance = 5;
      this.borderWarningTime = 15;
      this.dimensionData = Maps.newEnumMap(baM.class);
      this.gameRules = new bba();
      this.randomSeed = worldInformation.randomSeed;
      this.terrainType = worldInformation.terrainType;
      this.generatorOptions = worldInformation.generatorOptions;
      this.gameType = worldInformation.gameType;
      this.mapFeaturesEnabled = worldInformation.mapFeaturesEnabled;
      this.spawnX = worldInformation.spawnX;
      this.spawnY = worldInformation.spawnY;
      this.spawnZ = worldInformation.spawnZ;
      this.totalTime = worldInformation.totalTime;
      this.worldTime = worldInformation.worldTime;
      this.lastTimePlayed = worldInformation.lastTimePlayed;
      this.sizeOnDisk = worldInformation.sizeOnDisk;
      this.playerTag = worldInformation.playerTag;
      this.dimension = worldInformation.dimension;
      this.levelName = worldInformation.levelName;
      this.saveVersion = worldInformation.saveVersion;
      this.rainTime = worldInformation.rainTime;
      this.raining = worldInformation.raining;
      this.thunderTime = worldInformation.thunderTime;
      this.thundering = worldInformation.thundering;
      this.hardcore = worldInformation.hardcore;
      this.allowCommands = worldInformation.allowCommands;
      this.initialized = worldInformation.initialized;
      this.gameRules = worldInformation.gameRules;
      this.difficulty = worldInformation.difficulty;
      this.difficultyLocked = worldInformation.difficultyLocked;
      this.borderCenterX = worldInformation.borderCenterX;
      this.borderCenterZ = worldInformation.borderCenterZ;
      this.borderSize = worldInformation.borderSize;
      this.borderSizeLerpTime = worldInformation.borderSizeLerpTime;
      this.borderSizeLerpTarget = worldInformation.borderSizeLerpTarget;
      this.borderSafeZone = worldInformation.borderSafeZone;
      this.borderDamagePerBlock = worldInformation.borderDamagePerBlock;
      this.borderWarningTime = worldInformation.borderWarningTime;
      this.borderWarningDistance = worldInformation.borderWarningDistance;
   }

   public QQ cloneNBTCompound(@Nullable QQ nbt) {
      if (nbt == null) {
         nbt = this.playerTag;
      }

      QQ nbttagcompound = new QQ();
      this.updateTagCompound(nbttagcompound, nbt);
      return nbttagcompound;
   }

   private void updateTagCompound(QQ nbt, QQ playerNbt) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("Name", "1.12.2");
      nbttagcompound.setInteger("Id", 1343);
      nbttagcompound.setBoolean("Snapshot", false);
      nbt.setTag("Version", nbttagcompound);
      nbt.setInteger("DataVersion", 1343);
      nbt.setLong("RandomSeed", this.randomSeed);
      nbt.setString("generatorName", this.terrainType.getName());
      nbt.setInteger("generatorVersion", this.terrainType.getVersion());
      nbt.setString("generatorOptions", this.generatorOptions);
      nbt.setInteger("GameType", this.gameType.getID());
      nbt.setBoolean("MapFeatures", this.mapFeaturesEnabled);
      nbt.setInteger("SpawnX", this.spawnX);
      nbt.setInteger("SpawnY", this.spawnY);
      nbt.setInteger("SpawnZ", this.spawnZ);
      nbt.setLong("Time", this.totalTime);
      nbt.setLong("DayTime", this.worldTime);
      nbt.setLong("SizeOnDisk", this.sizeOnDisk);
      nbt.setLong("LastPlayed", Xx.getCurrentTimeMillis());
      nbt.setString("LevelName", this.levelName);
      nbt.setInteger("version", this.saveVersion);
      nbt.setInteger("clearWeatherTime", this.cleanWeatherTime);
      nbt.setInteger("rainTime", this.rainTime);
      nbt.setBoolean("raining", this.raining);
      nbt.setInteger("thunderTime", this.thunderTime);
      nbt.setBoolean("thundering", this.thundering);
      nbt.setBoolean("hardcore", this.hardcore);
      nbt.setBoolean("allowCommands", this.allowCommands);
      nbt.setBoolean("initialized", this.initialized);
      nbt.setDouble("BorderCenterX", this.borderCenterX);
      nbt.setDouble("BorderCenterZ", this.borderCenterZ);
      nbt.setDouble("BorderSize", this.borderSize);
      nbt.setLong("BorderSizeLerpTime", this.borderSizeLerpTime);
      nbt.setDouble("BorderSafeZone", this.borderSafeZone);
      nbt.setDouble("BorderDamagePerBlock", this.borderDamagePerBlock);
      nbt.setDouble("BorderSizeLerpTarget", this.borderSizeLerpTarget);
      nbt.setDouble("BorderWarningBlocks", (double)this.borderWarningDistance);
      nbt.setDouble("BorderWarningTime", (double)this.borderWarningTime);
      if (this.difficulty != null) {
         nbt.setByte("Difficulty", (byte)this.difficulty.getId());
      }

      nbt.setBoolean("DifficultyLocked", this.difficultyLocked);
      nbt.setTag("GameRules", this.gameRules.writeToNBT());
      QQ nbttagcompound1 = new QQ();
      Iterator var5 = this.dimensionData.entrySet().iterator();

      while(var5.hasNext()) {
         Map.Entry<baM, QQ> entry = (Map.Entry)var5.next();
         nbttagcompound1.setTag(String.valueOf(((baM)entry.getKey()).getId()), (QH)entry.getValue());
      }

      nbt.setTag("DimensionData", nbttagcompound1);
      if (playerNbt != null) {
         nbt.setTag("Player", playerNbt);
      }

   }

   public long getSeed() {
      return this.randomSeed;
   }

   public int getSpawnX() {
      return this.spawnX;
   }

   public int getSpawnY() {
      return this.spawnY;
   }

   public int getSpawnZ() {
      return this.spawnZ;
   }

   public long getWorldTotalTime() {
      return this.totalTime;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public QQ getPlayerNBTTagCompound() {
      return this.playerTag;
   }

   public void setSpawnX(int x) {
      this.spawnX = x;
   }

   public void setSpawnY(int y) {
      this.spawnY = y;
   }

   public void setSpawnZ(int z) {
      this.spawnZ = z;
   }

   public void setWorldTotalTime(long time) {
      this.totalTime = time;
   }

   public void setWorldTime(long time) {
      this.worldTime = time;
   }

   public void setSpawn(BlockPos spawnPoint) {
      this.spawnX = spawnPoint.getX();
      this.spawnY = spawnPoint.getY();
      this.spawnZ = spawnPoint.getZ();
   }

   public String getWorldName() {
      return this.levelName;
   }

   public void setWorldName(String worldName) {
      this.levelName = worldName;
   }

   public int getSaveVersion() {
      return this.saveVersion;
   }

   public void setSaveVersion(int version) {
      this.saveVersion = version;
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public int getCleanWeatherTime() {
      return this.cleanWeatherTime;
   }

   public void setCleanWeatherTime(int cleanWeatherTimeIn) {
      this.cleanWeatherTime = cleanWeatherTimeIn;
   }

   public boolean isThundering() {
      return this.thundering;
   }

   public void setThundering(boolean thunderingIn) {
      this.thundering = thunderingIn;
   }

   public int getThunderTime() {
      return this.thunderTime;
   }

   public void setThunderTime(int time) {
      this.thunderTime = time;
   }

   public boolean isRaining() {
      return this.raining;
   }

   public void setRaining(boolean isRaining) {
      this.raining = isRaining;
   }

   public int getRainTime() {
      return this.rainTime;
   }

   public void setRainTime(int time) {
      this.rainTime = time;
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public void setMapFeaturesEnabled(boolean enabled) {
      this.mapFeaturesEnabled = enabled;
   }

   public void setGameType(bbb type) {
      this.gameType = type;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public void setHardcore(boolean hardcoreIn) {
      this.hardcore = hardcoreIn;
   }

   public bix getTerrainType() {
      return this.terrainType;
   }

   public void setTerrainType(bix type) {
      this.terrainType = type;
   }

   public String getGeneratorOptions() {
      return this.generatorOptions == null ? "" : this.generatorOptions;
   }

   public boolean areCommandsAllowed() {
      return this.allowCommands;
   }

   public void setAllowCommands(boolean allow) {
      this.allowCommands = allow;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setServerInitialized(boolean initializedIn) {
      this.initialized = initializedIn;
   }

   public bba getGameRulesInstance() {
      return this.gameRules;
   }

   public double getBorderCenterX() {
      return this.borderCenterX;
   }

   public double getBorderCenterZ() {
      return this.borderCenterZ;
   }

   public double getBorderSize() {
      return this.borderSize;
   }

   public void setBorderSize(double size) {
      this.borderSize = size;
   }

   public long getBorderLerpTime() {
      return this.borderSizeLerpTime;
   }

   public void setBorderLerpTime(long time) {
      this.borderSizeLerpTime = time;
   }

   public double getBorderLerpTarget() {
      return this.borderSizeLerpTarget;
   }

   public void setBorderLerpTarget(double lerpSize) {
      this.borderSizeLerpTarget = lerpSize;
   }

   public void getBorderCenterZ(double posZ) {
      this.borderCenterZ = posZ;
   }

   public void getBorderCenterX(double posX) {
      this.borderCenterX = posX;
   }

   public double getBorderSafeZone() {
      return this.borderSafeZone;
   }

   public void setBorderSafeZone(double amount) {
      this.borderSafeZone = amount;
   }

   public double getBorderDamagePerBlock() {
      return this.borderDamagePerBlock;
   }

   public void setBorderDamagePerBlock(double damage) {
      this.borderDamagePerBlock = damage;
   }

   public int getBorderWarningDistance() {
      return this.borderWarningDistance;
   }

   public int getBorderWarningTime() {
      return this.borderWarningTime;
   }

   public void setBorderWarningDistance(int amountOfBlocks) {
      this.borderWarningDistance = amountOfBlocks;
   }

   public void setBorderWarningTime(int ticks) {
      this.borderWarningTime = ticks;
   }

   public baV getDifficulty() {
      return this.difficulty;
   }

   public void setDifficulty(baV newDifficulty) {
      this.difficulty = newDifficulty;
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public void setDifficultyLocked(boolean locked) {
      this.difficultyLocked = locked;
   }

   public void addToCrashReport(Ey category) {
      category.addDetail("Level seed", new Ez<String>() {
         public String call() throws Exception {
            return String.valueOf(bhY.this.getSeed());
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level generator", new Ez<String>() {
         public String call() throws Exception {
            return String.format("ID %02d - %s, ver %d. Features enabled: %b", bhY.this.terrainType.getId(), bhY.this.terrainType.getName(), bhY.this.terrainType.getVersion(), bhY.this.mapFeaturesEnabled);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level generator options", new Ez<String>() {
         public String call() throws Exception {
            return bhY.this.generatorOptions;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level spawn location", new Ez<String>() {
         public String call() throws Exception {
            return Ey.getCoordinateInfo(bhY.this.spawnX, bhY.this.spawnY, bhY.this.spawnZ);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level time", new Ez<String>() {
         public String call() throws Exception {
            return String.format("%d game time, %d day time", bhY.this.totalTime, bhY.this.worldTime);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level dimension", new Ez<String>() {
         public String call() throws Exception {
            return String.valueOf(bhY.this.dimension);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level storage version", new Ez<String>() {
         public String call() throws Exception {
            String s = "Unknown?";

            try {
               switch (bhY.this.saveVersion) {
                  case 19132:
                     s = "McRegion";
                     break;
                  case 19133:
                     s = "Anvil";
               }
            } catch (Throwable var3) {
            }

            return String.format("0x%05X - %s", bhY.this.saveVersion, s);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level weather", new Ez<String>() {
         public String call() throws Exception {
            return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", bhY.this.rainTime, bhY.this.raining, bhY.this.thunderTime, bhY.this.thundering);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      category.addDetail("Level game mode", new Ez<String>() {
         public String call() throws Exception {
            return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", bhY.this.gameType.getName(), bhY.this.gameType.getID(), bhY.this.hardcore, bhY.this.allowCommands);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
   }

   public QQ getDimensionData(baM dimensionIn) {
      QQ nbttagcompound = (QQ)this.dimensionData.get(dimensionIn);
      return nbttagcompound == null ? new QQ() : nbttagcompound;
   }

   public void setDimensionData(baM dimensionIn, QQ compound) {
      this.dimensionData.put(dimensionIn, compound);
   }

   public int getVersionId() {
      return this.versionId;
   }

   public boolean isVersionSnapshot() {
      return this.versionSnapshot;
   }

   public String getVersionName() {
      return this.versionName;
   }

   static {
      DEFAULT_DIFFICULTY = baV.NORMAL;
   }
}
