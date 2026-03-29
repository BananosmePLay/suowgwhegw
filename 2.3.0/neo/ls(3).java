package neo;

import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class ls extends lg {
   private final lg lastScreen;
   private final pf serverData;
   private lE ipEdit;

   public ls(lg lastScreenIn, pf serverDataIn) {
      this.lastScreen = lastScreenIn;
      this.serverData = serverDataIn;
   }

   public void updateScreen() {
      this.ipEdit.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 96 + 12, Ax.format("selectServer.select")));
      this.buttonList.add(new jK(1, this.width / 2 - 100, this.height / 4 + 120 + 12, Ax.format("gui.cancel")));
      this.ipEdit = new lE(2, this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
      this.ipEdit.setMaxStringLength(128);
      this.ipEdit.setFocused(true);
      nC var10001 = this.mc;
      this.ipEdit.setText(nC.gameSettings.lastServer);
      ((jK)this.buttonList.get(0)).enabled = !this.ipEdit.getText().isEmpty() && this.ipEdit.getText().split(":").length > 0;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      nC var10000 = this.mc;
      nC.gameSettings.lastServer = this.ipEdit.getText();
      var10000 = this.mc;
      nC.gameSettings.saveOptions();
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 1) {
            this.lastScreen.confirmClicked(false, 0);
         } else if (button.id == 0) {
            this.serverData.serverIP = this.ipEdit.getText();
            this.lastScreen.confirmClicked(true, 0);
         }
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.ipEdit.textboxKeyTyped(typedChar, keyCode)) {
         ((jK)this.buttonList.get(0)).enabled = !this.ipEdit.getText().isEmpty() && this.ipEdit.getText().split(":").length > 0;
      } else if (keyCode == 28 || keyCode == 156) {
         this.actionPerformed((jK)this.buttonList.get(0));
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.ipEdit.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("selectServer.direct"), this.width / 2, 20, 16777215);
      this.drawString(this.fontRenderer, Ax.format("addServer.enterIp"), this.width / 2 - 100, 100, 10526880);
      this.ipEdit.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
