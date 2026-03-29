package neo;

import net.minecraft.util.SoundEvent;

public enum nd {
   SHOW(NO.UI_TOAST_IN),
   HIDE(NO.UI_TOAST_OUT);

   private final SoundEvent sound;

   private nd(SoundEvent soundIn) {
      this.sound = soundIn;
   }

   public void playSound(iU handler) {
      handler.playSound(iN.getRecord(this.sound, 1.0F, 1.0F));
   }
}
