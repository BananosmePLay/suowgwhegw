package neo;

public class bix {
   public static final bix[] WORLD_TYPES = new bix[16];
   public static final bix DEFAULT = (new bix(0, "default", 1)).setVersioned();
   public static final bix FLAT = new bix(1, "flat");
   public static final bix LARGE_BIOMES = new bix(2, "largeBiomes");
   public static final bix AMPLIFIED = (new bix(3, "amplified")).enableInfoNotice();
   public static final bix CUSTOMIZED = new bix(4, "customized");
   public static final bix DEBUG_ALL_BLOCK_STATES = new bix(5, "debug_all_block_states");
   public static final bix DEFAULT_1_1 = (new bix(8, "default_1_1", 0)).setCanBeCreated(false);
   private final int id;
   private final String name;
   private final int version;
   private boolean canBeCreated;
   private boolean versioned;
   private boolean hasInfoNotice;

   private bix(int id, String name) {
      this(id, name, 0);
   }

   private bix(int id, String name, int version) {
      this.name = name;
      this.version = version;
      this.canBeCreated = true;
      this.id = id;
      WORLD_TYPES[id] = this;
   }

   public String getName() {
      return this.name;
   }

   public String getTranslationKey() {
      return "generator." + this.name;
   }

   public String getInfoTranslationKey() {
      return this.getTranslationKey() + ".info";
   }

   public int getVersion() {
      return this.version;
   }

   public bix getWorldTypeForGeneratorVersion(int version) {
      return this == DEFAULT && version == 0 ? DEFAULT_1_1 : this;
   }

   private bix setCanBeCreated(boolean enable) {
      this.canBeCreated = enable;
      return this;
   }

   public boolean canBeCreated() {
      return this.canBeCreated;
   }

   private bix setVersioned() {
      this.versioned = true;
      return this;
   }

   public boolean isVersioned() {
      return this.versioned;
   }

   public static bix byName(String type) {
      bix[] var1 = WORLD_TYPES;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         bix worldtype = var1[var3];
         if (worldtype != null && worldtype.name.equalsIgnoreCase(type)) {
            return worldtype;
         }
      }

      return null;
   }

   public int getId() {
      return this.id;
   }

   public boolean hasInfoNotice() {
      return this.hasInfoNotice;
   }

   private bix enableInfoNotice() {
      this.hasInfoNotice = true;
      return this;
   }
}
