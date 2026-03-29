package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class 0ek implements 0cD {
   private static String _ _;

   private static kn dtNalHAsW4(nC var0) {
      return var0.ingameGUI;
   }

   public static void addMessage(String a, String b, String c) {
      Style d = new Style();
      d.setClickEvent(new ClickEvent(SBVRMNiyYM(), b));
      d.setHoverEvent(new HoverEvent(0XxHSPwCoT(), new TextComponentString(c)));
      addMessage((new TextComponentString(a)).setStyle(d));
   }

   public static void addMessage(String a) {
      if (nGapoDjLzd(98wB6AiVol()) != null) {
         a = a.replace(Knz19eKsQS("ݿ"), Knz19eKsQS("߾"));
         fl0TAo49dI(ajyoGQo67i()).getChatGUI().printChatMessage((new TextComponentString(Knz19eKsQS("߾ܽ߾ܵܗܼܶ߾ܿ߾ܵ\u070eܸܫܼݹ߾ܽ߾ܵ❻ݹ"))).appendText(a));
      }
   }

   public static void printMessage(String a) {
      if (dtNalHAsW4(FjcW40H25l()) != null) {
         Q4vaGlaSrL(rqdsW271eC()).getChatGUI().printChatMessage(new TextComponentString(a));
      }
   }

   public static void addException(Exception b) {
      if (wN1TVNJpGe(Q0qZa28ny9()) != null) {
         StringBuilder c = new StringBuilder();
         c.append(Knz19eKsQS("ݓ"));
         StackTraceElement[] var2 = b.getStackTrace();
         int var3 = var2.length;

         for(int var4 = 28856 ^ -10601 ^ 18296 ^ -7849; var4 < var3; ++var4) {
            StackTraceElement a = var2[var4];
            c.append(Knz19eKsQS("߾ܺݹܸܭݹ߾ܺ")).append(a.toString()).append(Knz19eKsQS("ݓ"));
         }

         NHOIVjWgDY(SKIYS9bo8t()).getChatGUI().printChatMessage(new TextComponentString(Knz19eKsQS("߾ܺ") + b + c));
      }
   }

   private static nC VDYKWPxot4() {
      return mc;
   }

   private static kn NHOIVjWgDY(nC var0) {
      return var0.ingameGUI;
   }

   private static kn HZMd4LFMS0(nC var0) {
      return var0.ingameGUI;
   }

   private static kn wN1TVNJpGe(nC var0) {
      return var0.ingameGUI;
   }

   private static kn nGapoDjLzd(nC var0) {
      return var0.ingameGUI;
   }

   private static kn Q4vaGlaSrL(nC var0) {
      return var0.ingameGUI;
   }

   private static ClickEvent.Action SBVRMNiyYM() {
      return ClickEvent.Action.RUN_COMMAND;
   }

   private static HoverEvent.Action _XxHSPwCoT/* $FF was: 0XxHSPwCoT*/() {
      return HoverEvent.Action.SHOW_TEXT;
   }

   private static nC ajyoGQo67i() {
      return mc;
   }

   public _ek/* $FF was: 0ek*/() {
   }

   public static void addMessage(ITextComponent a) {
      if (NGlklaVw6E(juqQgfWvLK()) != null) {
         HZMd4LFMS0(VDYKWPxot4()).getChatGUI().printChatMessage((new TextComponentString(Knz19eKsQS("߾ܽ߾ܵܗܼܶ߾ܿ߾ܵ\u070eܸܫܼݹ߾ܽ߾ܵ❻ݹ"))).appendSibling(a));
      }
   }

   private static nC rqdsW271eC() {
      return mc;
   }

   private static nC juqQgfWvLK() {
      return mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String Knz19eKsQS(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 26875 ^ -21269 ^ 8846 ^ -6498; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 5811 ^ -41977 ^ '钲' ^ -9889));
      }

      return var1.toString();
   }

   private static kn fl0TAo49dI(nC var0) {
      return var0.ingameGUI;
   }

   private static kn NGlklaVw6E(nC var0) {
      return var0.ingameGUI;
   }

   private static nC SKIYS9bo8t() {
      return mc;
   }

   private static nC _8wB6AiVol/* $FF was: 98wB6AiVol*/() {
      return mc;
   }

   private static nC Q0qZa28ny9() {
      return mc;
   }

   private static nC FjcW40H25l() {
      return mc;
   }
}
