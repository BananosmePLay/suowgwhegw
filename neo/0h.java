package neo;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;

@0a(
   name = "help",
   description = "Help"
)
public class 0h extends 0b {
   public void execute(String[] args) throws Exception {
      Iterator var2 = o3h5DgrVYa(0bK.getInstance()).getCommands().iterator();

      while(var2.hasNext()) {
         0b command = (0b)var2.next();
         if (!(command instanceof 0h)) {
            this.sendMessage(qdb1kD27TQ() + XDFJfaPaSm() + tWWwYaQVId(command) + qqqGayXHLa() + rbLeVvbmnV("ՍՅ") + QiPvedp9AD() + r44aIOEtP6(command) + mXv2KU4L9w() + rbLeVvbmnV("Մ"));
         }
      }

   }

   private static String XDFJfaPaSm() {
      return 0c.PREFIX;
   }

   private static ChatFormatting qdb1kD27TQ() {
      return ChatFormatting.WHITE;
   }

   private static 0bM o3h5DgrVYa(0bK var0) {
      return var0.commandManager;
   }

   private static ChatFormatting DT7yLuq34Q() {
      return ChatFormatting.RED;
   }

   private static ChatFormatting QiPvedp9AD() {
      return ChatFormatting.WHITE;
   }

   private static ChatFormatting mXv2KU4L9w() {
      return ChatFormatting.GRAY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String rbLeVvbmnV(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 26586 ^ -29956 ^ 17028 ^ -20574; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24951 ^ -8 ^ 27113 ^ -3573));
      }

      return var1.toString();
   }

   public _h/* $FF was: 0h*/() {
   }

   private static String tWWwYaQVId(0b var0) {
      return var0.name;
   }

   public void error() {
      this.sendMessage(DT7yLuq34Q() + rbLeVvbmnV("ԥԈԁԝ"));
   }

   private static String r44aIOEtP6(0b var0) {
      return var0.description;
   }

   private static ChatFormatting qqqGayXHLa() {
      return ChatFormatting.GRAY;
   }
}
