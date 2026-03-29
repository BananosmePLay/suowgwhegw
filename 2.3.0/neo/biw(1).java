package neo;

public final class biw {
   private final long seed;
   private final bbb gameType;
   private final boolean mapFeaturesEnabled;
   private final boolean hardcoreEnabled;
   private final bix terrainType;
   private boolean commandsAllowed;
   private boolean bonusChestEnabled;
   private String generatorOptions;

   public biw(long seedIn, bbb gameType, boolean enableMapFeatures, boolean hardcoreMode, bix worldTypeIn) {
      this.generatorOptions = "";
      this.seed = seedIn;
      this.gameType = gameType;
      this.mapFeaturesEnabled = enableMapFeatures;
      this.hardcoreEnabled = hardcoreMode;
      this.terrainType = worldTypeIn;
   }

   public biw(bhY info) {
      this(info.getSeed(), info.getGameType(), info.isMapFeaturesEnabled(), info.isHardcoreModeEnabled(), info.getTerrainType());
   }

   public biw enableBonusChest() {
      this.bonusChestEnabled = true;
      return this;
   }

   public biw enableCommands() {
      this.commandsAllowed = true;
      return this;
   }

   public biw setGeneratorOptions(String options) {
      this.generatorOptions = options;
      return this;
   }

   public boolean isBonusChestEnabled() {
      return this.bonusChestEnabled;
   }

   public long getSeed() {
      return this.seed;
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public boolean getHardcoreEnabled() {
      return this.hardcoreEnabled;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public bix getTerrainType() {
      return this.terrainType;
   }

   public boolean areCommandsAllowed() {
      return this.commandsAllowed;
   }

   public static bbb getGameTypeById(int id) {
      return bbb.getByID(id);
   }

   public String getGeneratorOptions() {
      return this.generatorOptions;
   }
}
