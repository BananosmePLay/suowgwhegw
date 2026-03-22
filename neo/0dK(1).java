package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.text.TextComponentString;

public class 0dK implements 0eB {
   private static GuiIngame DZrQRwv25Y(Minecraft var0) {
      return var0.ingameGUI;
   }

   private static GuiIngame GJrYgymFvv(Minecraft var0) {
      return var0.ingameGUI;
   }

   private static GuiIngame bz0GJVojla(Minecraft var0) {
      return var0.ingameGUI;
   }

   public _dK/* $FF was: 0dK*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String Jej9ZeCvx6(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31726 ^ -3947 ^ 3054 ^ -32619; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4831 ^ -17662 ^ 2789 ^ -22812));
      }

      return var1.toString();
   }

   private static GuiIngame VuVl9G35w7(Minecraft var0) {
      return var0.ingameGUI;
   }

   public static void formatMsg(String message) {
      if (bz0GJVojla(Minecraft.getMinecraft()) != null) {
         GJrYgymFvv(Minecraft.getMinecraft()).getChatGUI().printChatMessage(new TextComponentString(Jej9ZeCvx6("ջָջְֳֹ֒ջֺջְ\u058bֹֽ֮\u05fcջָջְ◾\u05fcջֺջְ") + message.replace(Jej9ZeCvx6("\u05fa"), Jej9ZeCvx6("ջ"))));
      }

   }

   public static void defaultMsg(String message) {
      if (DZrQRwv25Y(Minecraft.getMinecraft()) != null) {
         VuVl9G35w7(Minecraft.getMinecraft()).getChatGUI().printChatMessage(new TextComponentString(message.replace(Jej9ZeCvx6("\u05fa"), Jej9ZeCvx6("ջ"))));
      }

   }
}
