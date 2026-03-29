package neo;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class kk extends lg {
   private int enableButtonsTimer;
   private final ITextComponent causeOfDeath;

   public kk(@Nullable ITextComponent causeOfDeathIn) {
      this.causeOfDeath = causeOfDeathIn;
   }

   public void initGui() {
      this.buttonList.clear();
      this.enableButtonsTimer = 0;
      if (this.mc.world.getWorldInfo().isHardcoreModeEnabled()) {
         this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 72, Ax.format("deathScreen.spectate")));
         this.buttonList.add(new jK(1, this.width / 2 - 100, this.height / 4 + 96, Ax.format("deathScreen." + (this.mc.isIntegratedServerRunning() ? "deleteWorld" : "leaveServer"))));
      } else {
         this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 72, Ax.format("deathScreen.respawn")));
         this.buttonList.add(new jK(1, this.width / 2 - 100, this.height / 4 + 96, Ax.format("deathScreen.titleScreen")));
         if (this.mc.getSession() == null) {
            ((jK)this.buttonList.get(1)).enabled = false;
         }
      }

      jK guibutton;
      for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); guibutton.enabled = false) {
         guibutton = (jK)var1.next();
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   protected void actionPerformed(jK button) throws IOException {
      switch (button.id) {
         case 0:
            nC var10000 = this.mc;
            nC.player.respawnPlayer();
            this.mc.displayGuiScreen((lg)null);
            break;
         case 1:
            if (this.mc.world.getWorldInfo().isHardcoreModeEnabled()) {
               this.mc.displayGuiScreen(new 0cx());
            } else {
               lK guiyesno = new lK(this, Ax.format("deathScreen.quit.confirm"), "", Ax.format("deathScreen.titleScreen"), Ax.format("deathScreen.respawn"), 0);
               this.mc.displayGuiScreen(guiyesno);
               guiyesno.setButtonDelay(20);
            }
      }

   }

   public void confirmClicked(boolean result, int id) {
      if (result) {
         if (this.mc.world != null) {
            this.mc.world.sendQuittingDisconnectingPacket();
         }

         this.mc.loadWorld((pm)null);
         this.mc.displayGuiScreen(new 0cx());
      } else {
         nC var10000 = this.mc;
         nC.player.respawnPlayer();
         this.mc.displayGuiScreen((lg)null);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      boolean flag = this.mc.world.getWorldInfo().isHardcoreModeEnabled();
      this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
      yh.pushMatrix();
      yh.scale(2.0F, 2.0F, 2.0F);
      this.drawCenteredString(this.fontRenderer, Ax.format(flag ? "deathScreen.title.hardcore" : "deathScreen.title"), this.width / 2 / 2, 30, 16777215);
      yh.popMatrix();
      if (this.causeOfDeath != null) {
         this.drawCenteredString(this.fontRenderer, this.causeOfDeath.getFormattedText(), this.width / 2, 85, 16777215);
      }

      jH var10001 = this.fontRenderer;
      StringBuilder var10002 = (new StringBuilder()).append(Ax.format("deathScreen.score")).append(": ").append(TextFormatting.YELLOW);
      nC var10003 = this.mc;
      this.drawCenteredString(var10001, var10002.append(nC.player.getScore()).toString(), this.width / 2, 100, 16777215);
      if (this.causeOfDeath != null && mouseY > 85 && mouseY < 85 + this.fontRenderer.FONT_HEIGHT) {
         ITextComponent itextcomponent = this.getClickedComponentAt(mouseX);
         if (itextcomponent != null && itextcomponent.getStyle().getHoverEvent() != null) {
            this.handleComponentHover(itextcomponent, mouseX, mouseY);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Nullable
   public ITextComponent getClickedComponentAt(int p_184870_1_) {
      if (this.causeOfDeath == null) {
         return null;
      } else {
         int i = this.mc.fontRenderer.getStringWidth(this.causeOfDeath.getFormattedText());
         int j = this.width / 2 - i / 2;
         int k = this.width / 2 + i / 2;
         int l = j;
         if (p_184870_1_ >= j && p_184870_1_ <= k) {
            Iterator var6 = this.causeOfDeath.iterator();

            ITextComponent itextcomponent;
            do {
               if (!var6.hasNext()) {
                  return null;
               }

               itextcomponent = (ITextComponent)var6.next();
               l += this.mc.fontRenderer.getStringWidth(lF.removeTextColorsIfConfigured(itextcomponent.getUnformattedComponentText(), false));
            } while(l <= p_184870_1_);

            return itextcomponent;
         } else {
            return null;
         }
      }
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.enableButtonsTimer;
      jK guibutton;
      if (this.enableButtonsTimer == 20) {
         for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); guibutton.enabled = true) {
            guibutton = (jK)var1.next();
         }
      }

   }
}
