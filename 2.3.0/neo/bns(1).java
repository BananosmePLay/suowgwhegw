package neo;

import java.util.HashMap;
import java.util.Map;

public class bns {
   private static Map mapConfigurations = null;
   private static boolean reloadPlayerItems = Boolean.getBoolean("player.models.reload");
   private static long timeReloadPlayerItemsMs = System.currentTimeMillis();

   public bns() {
   }

   public static void renderPlayerItems(nM modelBiped, jf player, float scale, float partialTicks) {
      bnp playerconfiguration = getPlayerConfiguration(player);
      if (playerconfiguration != null) {
         playerconfiguration.renderPlayerItems(modelBiped, player, scale, partialTicks);
      }

   }

   public static synchronized bnp getPlayerConfiguration(jf player) {
      if (reloadPlayerItems && System.currentTimeMillis() > timeReloadPlayerItemsMs + 5000L) {
         nC.getMinecraft();
         jf abstractclientplayer = nC.player;
         if (abstractclientplayer != null) {
            setPlayerConfiguration(((jf)abstractclientplayer).getNameClear(), (bnp)null);
            timeReloadPlayerItemsMs = System.currentTimeMillis();
         }
      }

      String s1 = player.getNameClear();
      if (s1 == null) {
         return null;
      } else {
         bnp playerconfiguration = (bnp)getMapConfigurations().get(s1);
         if (playerconfiguration == null) {
            playerconfiguration = new bnp();
            getMapConfigurations().put(s1, playerconfiguration);
            bnr playerconfigurationreceiver = new bnr(s1);
            String s = bmO.getPlayerItemsUrl() + "/users/" + s1 + ".cfg";
            bmD filedownloadthread = new bmD(s, playerconfigurationreceiver);
            filedownloadthread.start();
         }

         return playerconfiguration;
      }
   }

   public static synchronized void setPlayerConfiguration(String player, bnp pc) {
      getMapConfigurations().put(player, pc);
   }

   private static Map getMapConfigurations() {
      if (mapConfigurations == null) {
         mapConfigurations = new HashMap();
      }

      return mapConfigurations;
   }
}
