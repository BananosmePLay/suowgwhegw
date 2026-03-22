package neo;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.net.Proxy;
import net.minecraft.util.text.TextFormatting;

class 0bk extends Thread {
   public final String password;
   // $FF: synthetic field
   public final 0bl this$0;
   public final String username;

   private static 0ba nO9f5199Oo() {
      return 0ba.Working;
   }

   private static ChatFormatting AySA6wTYhv() {
      return ChatFormatting.BOLD;
   }

   private static TextFormatting AbJlQ48O9H() {
      return TextFormatting.RED;
   }

   private static TextFormatting YvN1714EwW() {
      return TextFormatting.AQUA;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String VP8rlvYbgE(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16045 ^ -32613 ^ 1898 ^ -18084; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10026 ^ -13830 ^ 3251 ^ -8085));
      }

      return var1.toString();
   }

   _bk/* $FF was: 0bk*/(0bl var1, String username, String password) {
      this.this$0 = var1;
      this.username = username;
      this.password = password;
      0bl.access$000(var1, TextFormatting.GRAY + VP8rlvYbgE("ɁɬɤɭȦȦȦ"));
   }

   private static TextFormatting iQ946HSZ8G() {
      return TextFormatting.GREEN;
   }

   private static ChatFormatting dZa5WXgjDD() {
      return ChatFormatting.RED;
   }

   private static String aLTQY1vfLn(0bk var0) {
      return var0.password;
   }

   private static ChatFormatting EfKjmYGJ2Y() {
      return ChatFormatting.BOLD;
   }

   private static String jFPuRBeatp(0bk var0) {
      return var0.username;
   }

   private static ChatFormatting hRgBGGtGlX() {
      return ChatFormatting.RED;
   }

   private static TextFormatting glcexvv6NE() {
      return TextFormatting.RED;
   }

   private static String _J4n1pNiGv/* $FF was: 6J4n1pNiGv*/(0bk var0) {
      return var0.username;
   }

   public void run() {
      if (aLTQY1vfLn(this).equals(VP8rlvYbgE(""))) {
         0bd.addAccount(new 0bb(BPYnngd18n(this), VP8rlvYbgE("")));
         0bl.access$000(e0tWiyqdid(this), iQ946HSZ8G() + VP8rlvYbgE("ɉɬɬɭɬȨɩɤɼȨȥȨ") + hRgBGGtGlX() + jFPuRBeatp(this) + EfKjmYGJ2Y() + VP8rlvYbgE("ȠɦɧɦȨɤɡɫɭɦɻɭȡ"));
      } else {
         0bl.access$000(abmTt4GXyE(this), YvN1714EwW() + VP8rlvYbgE("ɜɺɱɡɦɯȨɫɧɦɦɭɫɼȦȦȦ"));
         this.checkAndAddAlt(6J4n1pNiGv(this), oSJqOGrT9u(this));
      }

   }

   private static Agent CdJ3ioYkqd() {
      return Agent.MINECRAFT;
   }

   private static String oSJqOGrT9u(0bk var0) {
      return var0.password;
   }

   private static 0bl e0tWiyqdid(0bk var0) {
      return var0.this$0;
   }

   private static 0bl _s7WEdM5Oz/* $FF was: 1s7WEdM5Oz*/(0bk var0) {
      return var0.this$0;
   }

   private static 0bl _SC9oVjyt5/* $FF was: 9SC9oVjyt5*/(0bk var0) {
      return var0.this$0;
   }

   private static Proxy WN6LM7W1VV() {
      return Proxy.NO_PROXY;
   }

   private static 0bl dbD5LjrNLV(0bk var0) {
      return var0.this$0;
   }

   private static 0bl abmTt4GXyE(0bk var0) {
      return var0.this$0;
   }

   private static TextFormatting n8zPZWWJ7J() {
      return TextFormatting.GREEN;
   }

   private static String _a3ehHgP5W/* $FF was: 0a3ehHgP5W*/(0bk var0) {
      return var0.username;
   }

   private void checkAndAddAlt(String username, String password) {
      try {
         YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(WN6LM7W1VV(), VP8rlvYbgE(""));
         YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(CdJ3ioYkqd());
         auth.setUsername(username);
         auth.setPassword(password);

         try {
            auth.logIn();
            0bd.addAccount(new 0bb(username, password, auth.getSelectedProfile().getName(), nO9f5199Oo()));
            0bl.access$000(dbD5LjrNLV(this), n8zPZWWJ7J() + VP8rlvYbgE("ɉɬɬɭɬȨɩɤɼȨȥȨ") + dZa5WXgjDD() + 0a3ehHgP5W(this) + AySA6wTYhv() + VP8rlvYbgE("Ƞɤɡɫɭɦɻɭȡ"));
         } catch (AuthenticationException var6) {
            AuthenticationException var7 = var6;
            0bl.access$000(9SC9oVjyt5(this), glcexvv6NE() + VP8rlvYbgE("ɋɧɦɦɭɫɼȨɮɩɡɤɭɬȩ"));
            var7.printStackTrace();
         }
      } catch (Throwable var7) {
         Throwable e = var7;
         0bl.access$000(1s7WEdM5Oz(this), AbJlQ48O9H() + VP8rlvYbgE("ɍɺɺɧɺ"));
         e.printStackTrace();
      }

   }

   private static String BPYnngd18n(0bk var0) {
      return var0.username;
   }
}
