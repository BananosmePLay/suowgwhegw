package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public abstract class iM implements iC {
   protected iP sound;
   @Nullable
   private iQ soundEvent;
   protected SoundCategory category;
   protected ResourceLocation positionedSoundLocation;
   protected float volume;
   protected float pitch;
   protected float xPosF;
   protected float yPosF;
   protected float zPosF;
   protected boolean repeat;
   protected int repeatDelay;
   protected iB attenuationType;

   protected iM(SoundEvent soundIn, SoundCategory categoryIn) {
      this(soundIn.getSoundName(), categoryIn);
   }

   protected iM(ResourceLocation soundId, SoundCategory categoryIn) {
      this.volume = 1.0F;
      this.pitch = 1.0F;
      this.attenuationType = iB.LINEAR;
      this.positionedSoundLocation = soundId;
      this.category = categoryIn;
   }

   public ResourceLocation getSoundLocation() {
      return this.positionedSoundLocation;
   }

   public iQ createAccessor(iU handler) {
      this.soundEvent = handler.getAccessor(this.positionedSoundLocation);
      if (this.soundEvent == null) {
         this.sound = iU.MISSING_SOUND;
      } else {
         this.sound = this.soundEvent.cloneEntry();
      }

      return this.soundEvent;
   }

   public iP getSound() {
      return this.sound;
   }

   public SoundCategory getCategory() {
      return this.category;
   }

   public boolean canRepeat() {
      return this.repeat;
   }

   public int getRepeatDelay() {
      return this.repeatDelay;
   }

   public float getVolume() {
      return this.volume * this.sound.getVolume();
   }

   public float getPitch() {
      return this.pitch * this.sound.getPitch();
   }

   public float getXPosF() {
      return this.xPosF;
   }

   public float getYPosF() {
      return this.yPosF;
   }

   public float getZPosF() {
      return this.zPosF;
   }

   public iB getAttenuationType() {
      return this.attenuationType;
   }
}
