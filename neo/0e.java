package neo;

import java.io.File;
import net.minecraft.client.Minecraft;

@0a(
   name = "cfg",
   description = "Загрузка конфига для клиента."
)
public class 0e extends 0b {
   public void error() {
      0dK.formatMsg(FQzvTZajqY() + nV7wvYIYNp("ɇɂɃȄȘɈɋɅɀȋɗɅɒɁȚȄȘɇɋɊɂɍɃɊɅɉɁȚȄȉȄسؔؗ٤٧ؓ؞ؔȄ؞ؙؚ٠\u061cؗؔȄؐ؟٫Ȅ؞؟\u061cؙؑ٦ؔȊ"));
   }

   private static String FQzvTZajqY() {
      return 0c.PREFIX;
   }

   private static 0dN _IEe8LX7oJ/* $FF was: 4IEe8LX7oJ*/(0bK var0) {
      return var0.configManager;
   }

   public _e/* $FF was: 0e*/() {
   }

   private static File lwTMgL1oGF(Minecraft var0) {
      return var0.gameDir;
   }

   private static 0dN aZHnieTFal(0bK var0) {
      return var0.configManager;
   }

   public void execute(String[] args) throws Exception {
      if (args.length != 0) {
         if (args.length == (31826 ^ -7459 ^ 19230 ^ -10861)) {
            if (args[1785 ^ -508 ^ 17473 ^ -17220].equalsIgnoreCase(nV7wvYIYNp("ɈɋɅɀ"))) {
               if (!(new File(lwTMgL1oGF(Minecraft.getMinecraft()), nV7wvYIYNp("ȋɪɁɋɳɅɖɁȋɇɋɊɂɍɃɗȋ") + args[5057 ^ -11531 ^ 23643 ^ -25234] + nV7wvYIYNp("ȊɇɂɃ"))).exists()) {
                  0dK.formatMsg(nV7wvYIYNp("ؾؙؚ٠\u061cؗȄ") + args[9949 ^ -32545 ^ 18541 ^ -4498] + nV7wvYIYNp("ȄؙؑȄؙؔ؝ؙؐؑȅ"));
               } else {
                  aZHnieTFal(0bK.getInstance()).loadConfig(args[26199 ^ -8171 ^ 15119 ^ -17076]);
                  0dK.formatMsg(nV7wvYIYNp("ؾؙؚ٠\u061cؗȄ") + args[677 ^ -32438 ^ 867 ^ -32627] + nV7wvYIYNp("Ȅ٧٥؛ؑ٬ؙؚȄؓؔؗ٤٧ؙؒؑȅ"));
               }
            }

            if (args[20003 ^ -10857 ^ 29912 ^ -4244].equalsIgnoreCase(nV7wvYIYNp("ɗɅɒɁ"))) {
               0dK.formatMsg(nV7wvYIYNp("ؾؙؚ٠\u061cؗȄ") + args[25656 ^ -10140 ^ 2824 ^ -18603] + nV7wvYIYNp("Ȅ٧٥؛ؑ٬ؙؚȄ٥ؚ١٤ؙؙؔؑȅ"));
               4IEe8LX7oJ(0bK.getInstance()).saveConfig(args[28663 ^ -13570 ^ 603 ^ -22701]);
            }
         } else {
            this.error();
         }
      } else {
         this.error();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String nV7wvYIYNp(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 32713 ^ -18456 ^ 30594 ^ -16477; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4535 ^ -27887 ^ 5251 ^ -27647));
      }

      return var1.toString();
   }
}
