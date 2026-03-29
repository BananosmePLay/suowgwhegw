package neo;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.net.Proxy;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextFormatting;

public class 0bc extends Thread {
   public final Minecraft mc = Minecraft.getMinecraft();
   public final 0bb alt;
   public String status;

   private static 0bb mHgGgc9Ju6(0bc var0) {
      return var0.alt;
   }

   private static void GNwk6acVWq(0bc var0, String var1) {
      var0.status = var1;
   }

   private static TextFormatting iaSD0qJooa() {
      return TextFormatting.GREEN;
   }

   private static Minecraft NXLiYgWSrO(0bc var0) {
      return var0.mc;
   }

   private static 0ba _VMoxkxmgo/* $FF was: 6VMoxkxmgo*/() {
      return 0ba.NotWorking;
   }

   public void setStatus(String status) {
      GNwk6acVWq(this, status);
   }

   private static 0bb NhSjrwC6rI(0bc var0) {
      return var0.alt;
   }

   private static 0ba lyWvnsqcET() {
      return 0ba.Unchecked;
   }

   public _bc/* $FF was: 0bc*/(0bb alt) {
      this.alt = alt;
      this.status = FhYYIyNOom("ƂĒŲńŌőŌŋłċċċ");
   }

   private static TextFormatting F7QpByYdDT() {
      return TextFormatting.GREEN;
   }

   private static Proxy _mYOjJAv1F/* $FF was: 3mYOjJAv1F*/() {
      return Proxy.NO_PROXY;
   }

   private static 0bb eTbTW1qylt(0bc var0) {
      return var0.alt;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String FhYYIyNOom(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14093 ^ -25126 ^ 6959 ^ -19976; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 22686 ^ -21488 ^ 12281 ^ -9646));
      }

      return var1.toString();
   }

   private static 0bb tiN7w2vnym(0bc var0) {
      return var0.alt;
   }

   private static Agent jaDb7o1t6G() {
      return Agent.MINECRAFT;
   }

   private static ChatFormatting bO7T7IMKdO() {
      return ChatFormatting.RED;
   }

   private static 0bb K6bddOaaOO(0bc var0) {
      return var0.alt;
   }

   public Session login(String name, String password) {
      YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(3mYOjJAv1F(), FhYYIyNOom(""));
      UserAuthentication authentication = new YggdrasilUserAuthentication(service, jaDb7o1t6G());
      YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)authentication;
      auth.setUsername(name);
      auth.setPassword(password);

      try {
         auth.logIn();
         return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), FhYYIyNOom("ňŊŏńŋł"));
      } catch (AuthenticationException var7) {
         AuthenticationException var8 = var7;
         var8.printStackTrace();
         return null;
      }
   }

   public void run() {
      if (FViItIeDgT(this).getPassword().equals(FhYYIyNOom(""))) {
         79dGYGwoFD(NXLiYgWSrO(this), new Session(NhSjrwC6rI(this).getUsername(), FhYYIyNOom(""), FhYYIyNOom(""), FhYYIyNOom("ňŊŏńŋł")));
         0SqQBBSdgx(this, iaSD0qJooa() + FhYYIyNOom("ũŊłłŀŁąŌŋąĈą") + 6obDSPrrSC() + eTbTW1qylt(this).getUsername() + UV4LrMHVKL() + bO7T7IMKdO() + FhYYIyNOom("ąčūŊőąũŌņŀŋŖŀČ"));
      } else {
         aJuLNHvoOf(this, FhYYIyNOom("ũŊłłŌŋłąŌŋċċċ"));
         Session auth = this.login(eEI4UZTIJ6(this).getUsername(), AqS4iLDcoY(this).getPassword());
         if (auth == null) {
            vaiE9iBDeG(this, FhYYIyNOom("ŦŊŋŋŀņőąŃńŌŉŀŁĄ"));
            if (FymEAgDTtG(this).getStatus().equals(lyWvnsqcET())) {
               VzJNALFPet(this).setStatus(6VMoxkxmgo());
            }
         } else {
            nGUNFaZlhr(new 0bb(tiN7w2vnym(this).getUsername(), mHgGgc9Ju6(this).getPassword()));
            B6V65bsNbl(this, F7QpByYdDT() + FhYYIyNOom("ũŊłłŀŁąŌŋąĈą") + LGtV4l2wtJ() + auth.getUsername() + au4gIfvon4() + LbqE6DdaMo() + FhYYIyNOom("ąčũŌņŀŋŖŀČ"));
            K6bddOaaOO(this).setMask(auth.getUsername());
            Q6nFxdXal8(N2TKw6WLLN(this), auth);
         }
      }

   }

   private static ChatFormatting au4gIfvon4() {
      return ChatFormatting.BOLD;
   }

   private static String arWH391UDn(0bc var0) {
      return var0.status;
   }

   private static void B6V65bsNbl(0bc var0, String var1) {
      var0.status = var1;
   }

   private static void _SqQBBSdgx/* $FF was: 0SqQBBSdgx*/(0bc var0, String var1) {
      var0.status = var1;
   }

   private static 0bb VzJNALFPet(0bc var0) {
      return var0.alt;
   }

   private static ChatFormatting LbqE6DdaMo() {
      return ChatFormatting.RED;
   }

   public String getStatus() {
      return arWH391UDn(this);
   }

   private static void _9dGYGwoFD/* $FF was: 79dGYGwoFD*/(Minecraft var0, Session var1) {
      var0.session = var1;
   }

   private static ChatFormatting UV4LrMHVKL() {
      return ChatFormatting.BOLD;
   }

   private static void aJuLNHvoOf(0bc var0, String var1) {
      var0.status = var1;
   }

   private static Minecraft N2TKw6WLLN(0bc var0) {
      return var0.mc;
   }

   private static 0bb eEI4UZTIJ6(0bc var0) {
      return var0.alt;
   }

   private static 0bb FymEAgDTtG(0bc var0) {
      return var0.alt;
   }

   private static ChatFormatting LGtV4l2wtJ() {
      return ChatFormatting.WHITE;
   }

   private static void vaiE9iBDeG(0bc var0, String var1) {
      var0.status = var1;
   }

   private static void Q6nFxdXal8(Minecraft var0, Session var1) {
      var0.session = var1;
   }

   private static 0bb FViItIeDgT(0bc var0) {
      return var0.alt;
   }

   private static void nGUNFaZlhr(0bb var0) {
      0bd.lastAlt = var0;
   }

   private static ChatFormatting _obDSPrrSC/* $FF was: 6obDSPrrSC*/() {
      return ChatFormatting.WHITE;
   }

   private static 0bb AqS4iLDcoY(0bc var0) {
      return var0.alt;
   }
}
