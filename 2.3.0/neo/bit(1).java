package neo;

public class bit extends bis {
   private static final long DEMO_WORLD_SEED = (long)"North Carolina".hashCode();
   public static final biw DEMO_WORLD_SETTINGS;

   public bit(Xx server, bgm saveHandlerIn, bhY worldInfoIn, int dimensionId, Wk profilerIn) {
      super(server, saveHandlerIn, worldInfoIn, dimensionId, profilerIn);
      this.worldInfo.populateFromWorldSettings(DEMO_WORLD_SETTINGS);
   }

   static {
      DEMO_WORLD_SETTINGS = (new biw(DEMO_WORLD_SEED, bbb.SURVIVAL, true, false, bix.DEFAULT)).enableBonusChest();
   }
}
