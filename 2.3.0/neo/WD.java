package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;

public enum WD {
   ALWAYS("always", 0),
   NEVER("never", 1),
   HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
   HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

   private static final Map<String, WD> nameMap = Maps.newHashMap();
   public final String internalName;
   public final int id;

   public static String[] getNames() {
      return (String[])((String[])nameMap.keySet().toArray(new String[nameMap.size()]));
   }

   @Nullable
   public static WD getByName(String nameIn) {
      return (WD)nameMap.get(nameIn);
   }

   private WD(String nameIn, int idIn) {
      this.internalName = nameIn;
      this.id = idIn;
   }

   static {
      WD[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         WD team$enumvisible = var0[var2];
         nameMap.put(team$enumvisible.internalName, team$enumvisible);
      }

   }
}
