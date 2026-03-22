package neo;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetSocketAddress;
import net.minecraft.client.Minecraft;

@0a(
   name = "getip",
   description = "Получить айпи сервера."
)
public class 0g extends 0b {
   public void error() {
      0dK.formatMsg(FyatJnoVIj() + aKvoLJvqIZ("֝֟֎֓֊ךחךǥǄǁƹƽǂƸƶךǊǃǅǂךƻǏƺǈǏƺǊה"));
   }

   private static String FyatJnoVIj() {
      return 0c.PREFIX;
   }

   public void execute(String[] args) throws Exception {
      String serverAddress = aKvoLJvqIZ("");
      if (!9Yxd4BJtL7().isSingleplayer()) {
         serverAddress = (new InetSocketAddress(0er.getIp(), 0er.getPort())).toString();
      } else {
         serverAddress = aKvoLJvqIZ("ֶ֛֖֕֙֒֕։֎");
      }

      0dK.formatMsg(aKvoLJvqIZ("ǪǃǅǂךƻǏƺǈǏƺǊ׀ך") + serverAddress);
      StringSelection selection = new StringSelection(serverAddress);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(selection, selection);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String aKvoLJvqIZ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 46 ^ -26533 ^ 15612 ^ -23415; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12357 ^ -27154 ^ 16275 ^ -24638));
      }

      return var1.toString();
   }

   public _g/* $FF was: 0g*/() {
   }

   private static Minecraft _Yxd4BJtL7/* $FF was: 9Yxd4BJtL7*/() {
      return mc;
   }
}
