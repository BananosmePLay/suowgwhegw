package neo;

import java.util.List;
import javax.annotation.Nullable;

public class iV {
   private final List<iP> sounds;
   private final boolean replaceExisting;
   private final String subtitle;

   public iV(List<iP> soundsIn, boolean replceIn, String subtitleIn) {
      this.sounds = soundsIn;
      this.replaceExisting = replceIn;
      this.subtitle = subtitleIn;
   }

   public List<iP> getSounds() {
      return this.sounds;
   }

   public boolean canReplaceExisting() {
      return this.replaceExisting;
   }

   @Nullable
   public String getSubtitle() {
      return this.subtitle;
   }
}
