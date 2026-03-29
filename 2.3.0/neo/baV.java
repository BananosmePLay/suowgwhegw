package neo;

public enum baV {
   PEACEFUL(0, "options.difficulty.peaceful"),
   EASY(1, "options.difficulty.easy"),
   NORMAL(2, "options.difficulty.normal"),
   HARD(3, "options.difficulty.hard");

   private static final baV[] ID_MAPPING = new baV[values().length];
   private final int id;
   private final String translationKey;

   private baV(int difficultyIdIn, String difficultyResourceKeyIn) {
      this.id = difficultyIdIn;
      this.translationKey = difficultyResourceKeyIn;
   }

   public int getId() {
      return this.id;
   }

   public static baV byId(int id) {
      return ID_MAPPING[id % ID_MAPPING.length];
   }

   public String getTranslationKey() {
      return this.translationKey;
   }

   static {
      baV[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         baV enumdifficulty = var0[var2];
         ID_MAPPING[enumdifficulty.id] = enumdifficulty;
      }

   }
}
