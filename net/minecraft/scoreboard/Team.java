package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.text.TextFormatting;

public abstract class Team {
   public Team() {
   }

   public boolean isSameTeam(@Nullable Team other) {
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

   public abstract EnumVisible getNameTagVisibility();

   public abstract TextFormatting getColor();

   public abstract Collection<String> getMembershipCollection();

   public abstract EnumVisible getDeathMessageVisibility();

   public abstract CollisionRule getCollisionRule();

   public static enum EnumVisible {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

      private static final Map<String, EnumVisible> nameMap = Maps.newHashMap();
      public final String internalName;
      public final int id;

      public static String[] getNames() {
         return (String[])((String[])nameMap.keySet().toArray(new String[nameMap.size()]));
      }

      @Nullable
      public static EnumVisible getByName(String nameIn) {
         return (EnumVisible)nameMap.get(nameIn);
      }

      private EnumVisible(String nameIn, int idIn) {
         this.internalName = nameIn;
         this.id = idIn;
      }

      static {
         EnumVisible[] var0 = values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            EnumVisible team$enumvisible = var0[var2];
            nameMap.put(team$enumvisible.internalName, team$enumvisible);
         }

      }
   }

   public static enum CollisionRule {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("pushOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("pushOwnTeam", 3);

      private static final Map<String, CollisionRule> nameMap = Maps.newHashMap();
      public final String name;
      public final int id;

      public static String[] getNames() {
         return (String[])((String[])nameMap.keySet().toArray(new String[nameMap.size()]));
      }

      @Nullable
      public static CollisionRule getByName(String nameIn) {
         return (CollisionRule)nameMap.get(nameIn);
      }

      private CollisionRule(String nameIn, int idIn) {
         this.name = nameIn;
         this.id = idIn;
      }

      static {
         CollisionRule[] var0 = values();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            CollisionRule team$collisionrule = var0[var2];
            nameMap.put(team$collisionrule.name, team$collisionrule);
         }

      }
   }
}
