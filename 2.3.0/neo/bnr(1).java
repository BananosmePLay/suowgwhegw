package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class bnr implements bmP {
   private String player = null;

   public bnr(String player) {
      this.player = player;
   }

   public void fileDownloadFinished(String url, byte[] bytes, Throwable exception) {
      if (bytes != null) {
         try {
            String s = new String(bytes, "ASCII");
            JsonParser jsonparser = new JsonParser();
            JsonElement jsonelement = jsonparser.parse(s);
            bnq playerconfigurationparser = new bnq(this.player);
            bnp playerconfiguration = playerconfigurationparser.parsePlayerConfiguration(jsonelement);
            if (playerconfiguration != null) {
               playerconfiguration.setInitialized(true);
               bns.setPlayerConfiguration(this.player, playerconfiguration);
            }
         } catch (Exception var9) {
            Exception exception1 = var9;
            XH.dbg("Error parsing configuration: " + url + ", " + exception1.getClass().getName() + ": " + exception1.getMessage());
         }
      }

   }
}
