package neo;

import java.util.Random;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

public class iK implements ITickable {
   private final Random rand = new Random();
   private final nC mc;
   private iC currentMusic;
   private int timeUntilNextMusic = 100;

   public iK(nC mcIn) {
      this.mc = mcIn;
   }

   public void update() {
      iJ musicticker$musictype = this.mc.getAmbientMusicType();
      if (this.currentMusic != null) {
         if (!musicticker$musictype.getMusicLocation().getSoundName().equals(this.currentMusic.getSoundLocation())) {
            this.mc.getSoundHandler().stopSound(this.currentMusic);
            this.timeUntilNextMusic = MathHelper.getInt((Random)this.rand, 0, musicticker$musictype.getMinDelay() / 2);
         }

         if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
            this.currentMusic = null;
            this.timeUntilNextMusic = Math.min(MathHelper.getInt(this.rand, musicticker$musictype.getMinDelay(), musicticker$musictype.getMaxDelay()), this.timeUntilNextMusic);
         }
      }

      this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, musicticker$musictype.getMaxDelay());
      if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
         this.playMusic(musicticker$musictype);
      }

   }

   public void playMusic(iJ requestedMusicType) {
      this.currentMusic = iN.getMusicRecord(requestedMusicType.getMusicLocation());
      this.mc.getSoundHandler().playSound(this.currentMusic);
      this.timeUntilNextMusic = Integer.MAX_VALUE;
   }
}
