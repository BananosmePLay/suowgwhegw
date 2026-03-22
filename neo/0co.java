package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;

public class 0co extends 0cB {
   public static final List<0cn> notifies = new ArrayList();

   public _co/* $FF was: 0co*/() {
      super(3Ww6ewOtkE("тѣѸѥѪѥѯѭѸѥѣѢѿ"), 0bV.Other);
   }

   private static int tdlT7aaQnV(GameSettings var0) {
      return var0.guiScale;
   }

   private static Minecraft iVd6SEAFQV() {
      return mc;
   }

   private static Minecraft tT4dBwWKID() {
      return mc;
   }

   public static void render(ScaledResolution res) {
      float yOffset = Float.intBitsToFloat(244697 ^ 219062 ^ 5018 ^ 1229015849 ^ 240745 ^ '셼' ^ 240964 ^ -2004781939);
      if (kj5e6Ndyf7(iVd6SEAFQV()) instanceof GuiChat) {
         MDq3fIQuBe();
         int i = tdlT7aaQnV(ubBrEsacQF());
         if (i == 0) {
            yOffset -= Float.intBitsToFloat('췘' ^ '額' ^ 10414 ^ 935788582 ^ '\ud9b1' ^ '荀' ^ 25913 ^ 1981235925);
         }

         if (i == (10248 ^ -7152 ^ 25855 ^ -22299)) {
            yOffset -= Float.intBitsToFloat(1825 ^ '\uf1e9' ^ 926 ^ -263692531 ^ 101551 ^ 94408 ^ 11667 ^ -1322746961);
         }
      }

      0cn notify;
      for(Iterator var4 = AwsLQCX17j().iterator(); var4.hasNext(); yOffset -= notify.draw(res, yOffset)) {
         notify = (0cn)var4.next();
      }

   }

   private static List mOvWI6Yl2F() {
      return notifies;
   }

   private static List AwsLQCX17j() {
      return notifies;
   }

   private static List iy9Fi6uZwm() {
      return notifies;
   }

   private static GameSettings ubBrEsacQF() {
      return Minecraft.gameSettings;
   }

   private static EntityPlayerSP BLcu6iDlnC() {
      return Minecraft.player;
   }

   @0X
   public void onUpdate(0P event) {
      We4AkWExLU().forEach((notify) -> {
         notify.updateAnimation();
      });
      iy9Fi6uZwm().removeIf(0cn::updateAnimation);
      render(new ScaledResolution(Minecraft.getMinecraft()));
   }

   private static GuiScreen kj5e6Ndyf7(Minecraft var0) {
      return var0.currentScreen;
   }

   private static Minecraft MDq3fIQuBe() {
      return mc;
   }

   private static List We4AkWExLU() {
      return notifies;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _Ww6ewOtkE/* $FF was: 3Ww6ewOtkE*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31606 ^ -25865 ^ 7062 ^ -1513; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 18961 ^ -11418 ^ 27693 ^ -3754));
      }

      return var1.toString();
   }

   public static void notify(String title, String text, 0cm type, int second) {
      tT4dBwWKID();
      if (BLcu6iDlnC() != null) {
         mOvWI6Yl2F().add((new 0cn(title, text, type)).setMaxTime(second * (15366 ^ -5003 ^ 30242 ^ -22961)));
      }

   }
}
