package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;

public enum WC {
   ALWAYS("always", 0),
   NEVER("never", 1),
   HIDE_FOR_OTHER_TEAMS("pushOtherTeams", 2),
   HIDE_FOR_OWN_TEAM("pushOwnTeam", 3);

   private static final Map<String, WC> nameMap = Maps.newHashMap();
   public final String name;
   public final int id;

   public static String[] getNames() {
      return (String[])((String[])nameMap.keySet().toArray(new String[nameMap.size()]));
   }

   @Nullable
   public static WC getByName(String nameIn) {
      return (WC)nameMap.get(nameIn);
   }

   private WC(String nameIn, int idIn) {
      this.name = nameIn;
      this.id = idIn;
   }

   static {
      WC[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         WC team$collisionrule = var0[var2];
         nameMap.put(team$collisionrule.name, team$collisionrule);
      }

   }
}
