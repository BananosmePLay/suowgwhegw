package neo;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;

public class bmv extends bmw {
   private final lg parentScreen;
   private String title;
   private String message;
   private long messageHideTimeMs;
   private String linkUrl;
   private bmm buttonCopyLink;

   public bmv(lg parentScreenIn) {
      this.parentScreen = parentScreenIn;
   }

   public void initGui() {
      int i = 0;
      this.title = Ax.format("of.options.capeOF.title");
      i += 2;
      this.buttonList.add(new bmm(210, this.width / 2 - 155, this.height / 6 + 24 * (i >> 1), 150, 20, Ax.format("of.options.capeOF.openEditor")));
      this.buttonList.add(new bmm(220, this.width / 2 - 155 + 160, this.height / 6 + 24 * (i >> 1), 150, 20, Ax.format("of.options.capeOF.reloadCape")));
      i += 6;
      this.buttonCopyLink = new bmm(230, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), 200, 20, Ax.format("of.options.capeOF.copyEditorLink"));
      this.buttonCopyLink.visible = this.linkUrl != null;
      this.addButton(this.buttonCopyLink);
      i += 4;
      this.buttonList.add(new bmm(200, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), Ax.format("gui.done")));
   }

   protected void actionPerformed(jK button) {
      if (button.enabled) {
         if (button.id == 200) {
            this.mc.displayGuiScreen(this.parentScreen);
         }

         if (button.id == 210) {
            try {
               String s = this.mc.getSession().getProfile().getName();
               String s1 = this.mc.getSession().getProfile().getId().toString().replace("-", "");
               String s2 = this.mc.getSession().getToken();
               Random random = new Random();
               Random random1 = new Random((long)System.identityHashCode(new Object()));
               BigInteger biginteger = new BigInteger(128, random);
               BigInteger biginteger1 = new BigInteger(128, random1);
               BigInteger biginteger2 = biginteger.xor(biginteger1);
               String s3 = biginteger2.toString(16);
               this.mc.getSessionService().joinServer(this.mc.getSession().getProfile(), s2, s3);
               String s4 = "https://optifine.net/capeChange?u=" + s1 + "&n=" + s + "&s=" + s3;
               boolean flag = XH.openWebLink(new URI(s4));
               if (flag) {
                  this.showMessage(bmW.get("of.message.capeOF.openEditor"), 10000L);
               } else {
                  this.showMessage(bmW.get("of.message.capeOF.openEditorError"), 10000L);
                  this.setLinkUrl(s4);
               }
            } catch (InvalidCredentialsException var13) {
               InvalidCredentialsException invalidcredentialsexception = var13;
               XH.showGuiMessage(Ax.format("of.message.capeOF.error1"), Ax.format("of.message.capeOF.error2", invalidcredentialsexception.getMessage()));
               XH.warn("Mojang authentication failed");
               XH.warn(invalidcredentialsexception.getClass().getName() + ": " + invalidcredentialsexception.getMessage());
            } catch (Exception var14) {
               Exception exception = var14;
               XH.warn("Error opening OptiFine cape link");
               XH.warn(exception.getClass().getName() + ": " + exception.getMessage());
            }
         }

         if (button.id == 220) {
            this.showMessage(bmW.get("of.message.capeOF.reloadCape"), 15000L);
            nC var10000 = this.mc;
            if (nC.player != null) {
               long i = 15000L;
               long j = System.currentTimeMillis() + i;
               var10000 = this.mc;
               nC.player.setReloadCapeTimeMs(j);
            }
         }

         if (button.id == 230 && this.linkUrl != null) {
            setClipboardString(this.linkUrl);
         }
      }

   }

   private void showMessage(String msg, long timeMs) {
      this.message = msg;
      this.messageHideTimeMs = System.currentTimeMillis() + timeMs;
      this.setLinkUrl((String)null);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
      if (this.message != null) {
         this.drawCenteredString(this.fontRenderer, this.message, this.width / 2, this.height / 6 + 60, 16777215);
         if (System.currentTimeMillis() > this.messageHideTimeMs) {
            this.message = null;
            this.setLinkUrl((String)null);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void setLinkUrl(String linkUrl) {
      this.linkUrl = linkUrl;
      this.buttonCopyLink.visible = linkUrl != null;
   }
}
