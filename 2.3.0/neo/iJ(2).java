package neo;

import net.minecraft.util.SoundEvent;

public enum iJ {
   MENU(NO.MUSIC_MENU, 20, 600),
   GAME(NO.MUSIC_GAME, 12000, 24000),
   CREATIVE(NO.MUSIC_CREATIVE, 1200, 3600),
   CREDITS(NO.MUSIC_CREDITS, 0, 0),
   NETHER(NO.MUSIC_NETHER, 1200, 3600),
   END_BOSS(NO.MUSIC_DRAGON, 0, 0),
   END(NO.MUSIC_END, 6000, 24000);

   private final SoundEvent musicLocation;
   private final int minDelay;
   private final int maxDelay;

   private iJ(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn) {
      this.musicLocation = musicLocationIn;
      this.minDelay = minDelayIn;
      this.maxDelay = maxDelayIn;
   }

   public SoundEvent getMusicLocation() {
      return this.musicLocation;
   }

   public int getMinDelay() {
      return this.minDelay;
   }

   public int getMaxDelay() {
      return this.maxDelay;
   }
}
