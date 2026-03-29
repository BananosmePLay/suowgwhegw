package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public interface iC {
   ResourceLocation getSoundLocation();

   @Nullable
   iQ createAccessor(iU var1);

   iP getSound();

   SoundCategory getCategory();

   boolean canRepeat();

   int getRepeatDelay();

   float getVolume();

   float getPitch();

   float getXPosF();

   float getYPosF();

   float getZPosF();

   iB getAttenuationType();
}
