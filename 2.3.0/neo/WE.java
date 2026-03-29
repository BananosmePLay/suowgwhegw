package neo;

import java.util.Collection;
import javax.annotation.Nullable;
import net.minecraft.util.text.TextFormatting;

public abstract class WE {
   public WE() {
   }

   public boolean isSameTeam(@Nullable WE other) {
      if (other == null) {
         return false;
      } else {
         return this == other;
      }
   }

   public abstract String getName();

   public abstract String formatString(String var1);

   public abstract boolean getSeeFriendlyInvisiblesEnabled();

   public abstract boolean getAllowFriendlyFire();

   public abstract WD getNameTagVisibility();

   public abstract TextFormatting getColor();

   public abstract Collection<String> getMembershipCollection();

   public abstract WD getDeathMessageVisibility();

   public abstract WC getCollisionRule();
}
