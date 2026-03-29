package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public abstract class iG extends iM implements iF {
   protected boolean donePlaying;

   protected iG(SoundEvent soundIn, SoundCategory categoryIn) {
      super(soundIn, categoryIn);
   }

   public boolean isDonePlaying() {
      return this.donePlaying;
   }
}
