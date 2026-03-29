package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class iN extends iM {
   public iN(SoundEvent soundIn, SoundCategory categoryIn, float volumeIn, float pitchIn, BlockPos pos) {
      this(soundIn, categoryIn, volumeIn, pitchIn, (float)pos.getX() + 0.5F, (float)pos.getY() + 0.5F, (float)pos.getZ() + 0.5F);
   }

   public static iN getMasterRecord(SoundEvent soundIn, float pitchIn) {
      return getRecord(soundIn, pitchIn, 0.25F);
   }

   public static iN getRecord(SoundEvent soundIn, float pitchIn, float volumeIn) {
      return new iN(soundIn, SoundCategory.MASTER, volumeIn, pitchIn, false, 0, iB.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static iN getMusicRecord(SoundEvent soundIn) {
      return new iN(soundIn, SoundCategory.MUSIC, 1.0F, 1.0F, false, 0, iB.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static iN getRecordSoundRecord(SoundEvent soundIn, float xIn, float yIn, float zIn) {
      return new iN(soundIn, SoundCategory.RECORDS, 4.0F, 1.0F, false, 0, iB.LINEAR, xIn, yIn, zIn);
   }

   public iN(SoundEvent soundIn, SoundCategory categoryIn, float volumeIn, float pitchIn, float xIn, float yIn, float zIn) {
      this((SoundEvent)soundIn, categoryIn, volumeIn, pitchIn, false, 0, iB.LINEAR, xIn, yIn, zIn);
   }

   private iN(SoundEvent soundIn, SoundCategory categoryIn, float volumeIn, float pitchIn, boolean repeatIn, int repeatDelayIn, iB attenuationTypeIn, float xIn, float yIn, float zIn) {
      this(soundIn.getSoundName(), categoryIn, volumeIn, pitchIn, repeatIn, repeatDelayIn, attenuationTypeIn, xIn, yIn, zIn);
   }

   public iN(ResourceLocation soundId, SoundCategory categoryIn, float volumeIn, float pitchIn, boolean repeatIn, int repeatDelayIn, iB attenuationTypeIn, float xIn, float yIn, float zIn) {
      super(soundId, categoryIn);
      this.volume = volumeIn;
      this.pitch = pitchIn;
      this.xPosF = xIn;
      this.yPosF = yIn;
      this.zPosF = zIn;
      this.repeat = repeatIn;
      this.repeatDelay = repeatDelayIn;
      this.attenuationType = attenuationTypeIn;
   }
}
