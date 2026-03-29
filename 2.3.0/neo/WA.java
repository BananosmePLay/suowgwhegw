package neo;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.text.TextFormatting;

public class WA extends WE {
   private final Ws scoreboard;
   private final String registeredName;
   private final String name;
   private final Set<String> membershipSet = Sets.newHashSet();
   private String displayName;
   private String prefix = "";
   private String suffix = "";
   private boolean allowFriendlyFire = true;
   private boolean canSeeFriendlyInvisibles = true;
   private WD nameTagVisibility;
   private WD deathMessageVisibility;
   private TextFormatting color;
   private WC collisionRule;

   public WA(Ws scoreboardIn, String name) {
      this.nameTagVisibility = WD.ALWAYS;
      this.deathMessageVisibility = WD.ALWAYS;
      this.color = TextFormatting.RESET;
      this.collisionRule = WC.ALWAYS;
      this.scoreboard = scoreboardIn;
      this.registeredName = name;
      this.name = name;
      this.displayName = name;
   }

   public String getName() {
      return this.name;
   }

   public String getRegisteredName() {
      return this.registeredName;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public void setDisplayName(String name) {
      if (name == null) {
         throw new IllegalArgumentException("Name cannot be null");
      } else {
         this.displayName = name;
         this.scoreboard.broadcastTeamInfoUpdate(this);
      }
   }

   public Collection<String> getMembershipCollection() {
      return this.membershipSet;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      if (prefix == null) {
         throw new IllegalArgumentException("Prefix cannot be null");
      } else {
         this.prefix = prefix;
         this.scoreboard.broadcastTeamInfoUpdate(this);
      }
   }

   public String getSuffix() {
      return this.suffix;
   }

   public void setSuffix(String suffix) {
      this.suffix = suffix;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public String formatString(String input) {
      return this.getPrefix() + input + this.getSuffix();
   }

   public static String formatPlayerName(@Nullable WE teamIn, String string) {
      return teamIn == null ? string : teamIn.formatString(string);
   }

   public boolean getAllowFriendlyFire() {
      return this.allowFriendlyFire;
   }

   public void setAllowFriendlyFire(boolean friendlyFire) {
      this.allowFriendlyFire = friendlyFire;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public boolean getSeeFriendlyInvisiblesEnabled() {
      return this.canSeeFriendlyInvisibles;
   }

   public void setSeeFriendlyInvisiblesEnabled(boolean friendlyInvisibles) {
      this.canSeeFriendlyInvisibles = friendlyInvisibles;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public WD getNameTagVisibility() {
      return this.nameTagVisibility;
   }

   public WD getDeathMessageVisibility() {
      return this.deathMessageVisibility;
   }

   public void setNameTagVisibility(WD visibility) {
      this.nameTagVisibility = visibility;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public void setDeathMessageVisibility(WD visibility) {
      this.deathMessageVisibility = visibility;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public WC getCollisionRule() {
      return this.collisionRule;
   }

   public void setCollisionRule(WC rule) {
      this.collisionRule = rule;
      this.scoreboard.broadcastTeamInfoUpdate(this);
   }

   public int getFriendlyFlags() {
      int i = 0;
      if (this.getAllowFriendlyFire()) {
         i |= 1;
      }

      if (this.getSeeFriendlyInvisiblesEnabled()) {
         i |= 2;
      }

      return i;
   }

   public void setFriendlyFlags(int flags) {
      this.setAllowFriendlyFire((flags & 1) > 0);
      this.setSeeFriendlyInvisiblesEnabled((flags & 2) > 0);
   }

   public void setColor(TextFormatting color) {
      this.color = color;
   }

   public TextFormatting getColor() {
      return this.color;
   }
}
