package neo;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextFormatting;

public class 0ce extends Thread {
   public String field_b;
   public final 0cd field_a;
   public final nC mc = nC.getMinecraft();
   private static int _DSC GG NEOWARECLIENT _;

   public void method_XR(String a) {
      method_XW(this, a);
   }

   private static 0cd method_Yd(0ce var0) {
      return var0.field_a;
   }

   private static void method_XW(0ce var0, String var1) {
      var0.field_b = var1;
   }

   private static TextFormatting method_Yq() {
      return TextFormatting.WHITE;
   }

   private static void method_Yh(0ce var0, String var1) {
      var0.field_b = var1;
   }

   private static 0cc method_Yo() {
      return 0cc.field_e;
   }

   private static TextFormatting method_Ye() {
      return TextFormatting.BOLD;
   }

   public Session method_XP(String b, String c) {
      YggdrasilAuthenticationService d = new YggdrasilAuthenticationService(method_XT(), method_XS(""));
      UserAuthentication e = new YggdrasilUserAuthentication(d, method_XU());
      YggdrasilUserAuthentication f = (YggdrasilUserAuthentication)e;
      f.setUsername(b);
      f.setPassword(c);

      try {
         f.logIn();
         return new Session(f.getSelectedProfile().getName(), f.getSelectedProfile().getId().toString(), f.getAuthenticatedToken(), method_XS("ˠˢ˧ˬˣ˪"));
      } catch (AuthenticationException var7) {
         AuthenticationException a = var7;
         a.printStackTrace();
         return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_XS(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 4166 ^ -16784 ^ 22222 ^ -1800; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 18011 ^ -29455 ^ 19495 ^ -31744));
      }

      return var1.toString();
   }

   private static TextFormatting method_Yp() {
      return TextFormatting.GREEN;
   }

   private static 0cd method_Yi(0ce var0) {
      return var0.field_a;
   }

   private static TextFormatting method_Yf() {
      return TextFormatting.RED;
   }

   private static TextFormatting method_Yr() {
      return TextFormatting.BOLD;
   }

   private static 0cd method_XZ(0ce var0) {
      return var0.field_a;
   }

   private static nC method_Yv(0ce var0) {
      return var0.mc;
   }

   private static String method_XV(0ce var0) {
      return var0.field_b;
   }

   private static Agent method_XU() {
      return Agent.MINECRAFT;
   }

   public _ce/* $FF was: 0ce*/(0cd a) {
      this.field_a = a;
      this.field_b = method_XS("Ȫʺ˚ˬˤ˹ˤˣ˪ʣʣʣ");
   }

   public void run() {
      if (method_XX(this).method_XE().equals(method_XS(""))) {
         method_Ya(method_XY(this), new Session(method_XZ(this).method_XG(), method_XS(""), method_XS(""), method_XS("ˠˢ˧ˬˣ˪")));
         method_Yg(this, method_Yb() + method_XS("ˁˢ˪˪˨˩ʭˤˣʭʠʭ") + method_Yc() + method_Yd(this).method_XG() + method_Ye() + method_Yf() + method_XS("ʭʥ˃ˢ˹ʭˁˤˮ˨ˣ˾˨ʤ"));
      } else {
         method_Yh(this, method_XS("ˁˢ˪˪ˤˣ˪ʭˤˣʣʣʣ"));
         Session a = this.method_XP(method_Yi(this).method_XG(), method_Yj(this).method_XE());
         if (a == null) {
            method_Yk(this, method_XS("ˎˢˣˣ˨ˮ˹ʭ˫ˬˤˡ˨˩ʬ"));
            if (method_Yl(this).method_XA().equals(method_Ym())) {
               method_Yn(this).method_XB(method_Yo());
            }
         } else {
            method_Yt(this, method_Yp() + method_XS("ˁˢ˪˪˨˩ʭˤˣʭʠʭ") + method_Yq() + a.getUsername() + method_Yr() + method_Ys() + method_XS("ʭʥˁˤˮ˨ˣ˾˨ʤ"));
            method_Yu(this).method_XD(a.getUsername());
            method_Yw(method_Yv(this), a);
         }
      }

   }

   private static 0cd method_Yj(0ce var0) {
      return var0.field_a;
   }

   private static 0cd method_Yl(0ce var0) {
      return var0.field_a;
   }

   private static TextFormatting method_Yb() {
      return TextFormatting.GREEN;
   }

   private static void method_Yg(0ce var0, String var1) {
      var0.field_b = var1;
   }

   private static 0cd method_XX(0ce var0) {
      return var0.field_a;
   }

   private static void method_Yk(0ce var0, String var1) {
      var0.field_b = var1;
   }

   public String method_XQ() {
      return method_XV(this);
   }

   private static 0cc method_Ym() {
      return 0cc.field_a;
   }

   private static void method_Yw(nC var0, Session var1) {
      var0.session = var1;
   }

   private static TextFormatting method_Ys() {
      return TextFormatting.RED;
   }

   private static 0cd method_Yn(0ce var0) {
      return var0.field_a;
   }

   private static void method_Ya(nC var0, Session var1) {
      var0.session = var1;
   }

   private static nC method_XY(0ce var0) {
      return var0.mc;
   }

   private static 0cd method_Yu(0ce var0) {
      return var0.field_a;
   }

   private static TextFormatting method_Yc() {
      return TextFormatting.WHITE;
   }

   private static void method_Yt(0ce var0, String var1) {
      var0.field_b = var1;
   }

   private static Proxy method_XT() {
      return Proxy.NO_PROXY;
   }
}
