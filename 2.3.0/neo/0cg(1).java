package neo;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.util.text.TextFormatting;

class 0cg extends Thread {
   public final String field_c;
   // $FF: synthetic field
   public final 0ch field_b;
   public final String field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static String method_Zb(0cg var0) {
      return var0.field_c;
   }

   private static String method_Za(0cg var0) {
      return var0.field_a;
   }

   private static TextFormatting method_YV() {
      return TextFormatting.BOLD;
   }

   private static TextFormatting method_YX() {
      return TextFormatting.RED;
   }

   private static 0ch method_YY(0cg var0) {
      return var0.field_b;
   }

   private static 0ch method_Zh(0cg var0) {
      return var0.field_b;
   }

   private static 0ch method_YR(0cg var0) {
      return var0.field_b;
   }

   private static String method_Zj(0cg var0) {
      return var0.field_c;
   }

   private static TextFormatting method_Zi() {
      return TextFormatting.AQUA;
   }

   _cg/* $FF was: 0cg*/(0ch var1, String a, String b) {
      this.field_b = var1;
      this.field_c = a;
      this.field_a = b;
      0ch.method_Zm(var1, TextFormatting.GRAY + method_YN("؋ئخا٬٬٬"));
   }

   private static 0ch method_Zc(0cg var0) {
      return var0.field_b;
   }

   private static 0ch method_YW(0cg var0) {
      return var0.field_b;
   }

   private static String method_Zf(0cg var0) {
      return var0.field_c;
   }

   private static String method_YU(0cg var0) {
      return var0.field_c;
   }

   private static TextFormatting method_Ze() {
      return TextFormatting.RED;
   }

   private static TextFormatting method_YT() {
      return TextFormatting.RED;
   }

   public void run() {
      if (method_Za(this).equals(method_YN(""))) {
         0cf.method_Yx(new 0cd(method_Zb(this), method_YN("")));
         0ch.method_Zm(method_Zc(this), method_Zd() + method_YN("\u0603ئئائ٢أخض٢ٯ٢") + method_Ze() + method_Zf(this) + method_Zg() + method_YN("٪جحج٢خثءاجرا٫"));
      } else {
         0ch.method_Zm(method_Zh(this), method_Zi() + method_YN("ؖذػثجإ٢ءحججاءض٬٬٬"));
         this.method_YM(method_Zj(this), method_Zk(this));
      }

   }

   private static Agent method_YP() {
      return Agent.MINECRAFT;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_YN(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 15307 ^ -12559 ^ 12321 ^ -15077; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12912 ^ -21327 ^ 5360 ^ -29581));
      }

      return var1.toString();
   }

   private static TextFormatting method_Zd() {
      return TextFormatting.GREEN;
   }

   private static 0cc method_YQ() {
      return 0cc.field_c;
   }

   private static TextFormatting method_YS() {
      return TextFormatting.GREEN;
   }

   private static TextFormatting method_YZ() {
      return TextFormatting.RED;
   }

   private static String method_Zk(0cg var0) {
      return var0.field_a;
   }

   private void method_YM(String e, String f) {
      try {
         YggdrasilAuthenticationService b = new YggdrasilAuthenticationService(method_YO(), method_YN(""));
         YggdrasilUserAuthentication c = (YggdrasilUserAuthentication)b.createUserAuthentication(method_YP());
         c.setUsername(e);
         c.setPassword(f);

         try {
            c.logIn();
            0cf.method_Yx(new 0cd(e, f, c.getSelectedProfile().getName(), method_YQ()));
            0ch.method_Zm(method_YR(this), method_YS() + method_YN("\u0603ئئائ٢أخض٢ٯ٢") + method_YT() + method_YU(this) + method_YV() + method_YN("٪خثءاجرا٫"));
         } catch (AuthenticationException var6) {
            AuthenticationException a = var6;
            0ch.method_Zm(method_YW(this), method_YX() + method_YN("\u0601حججاءض٢ؤأثخائ٣"));
            a.printStackTrace();
         }
      } catch (Throwable var7) {
         Throwable d = var7;
         0ch.method_Zm(method_YY(this), method_YZ() + method_YN("؇ذذحذ"));
         d.printStackTrace();
      }

   }

   private static TextFormatting method_Zg() {
      return TextFormatting.BOLD;
   }

   private static Proxy method_YO() {
      return Proxy.NO_PROXY;
   }
}
