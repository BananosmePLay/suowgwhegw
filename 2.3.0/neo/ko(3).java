package neo;

import java.io.IOException;

public class ko extends lg {
   public ko() {
   }

   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new jK(1, this.width / 2 - 100, this.height / 4 + 120 - 16, Ax.format("menu.returnToMenu")));
      if (!this.mc.isIntegratedServerRunning()) {
         ((jK)this.buttonList.get(0)).displayString = Ax.format("menu.disconnect");
      }

      this.buttonList.add(new jK(4, this.width / 2 - 100, this.height / 4 + 24 - 16, Ax.format("menu.returnToGame")));
      this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 96 - 16, 98, 20, Ax.format("menu.options")));
      jK guibutton = this.addButton(new jK(7, this.width / 2 + 2, this.height / 4 + 96 - 16, 98, 20, Ax.format("menu.shareToLan")));
      guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
      this.buttonList.add(new jK(5, this.width / 2 - 100, this.height / 4 + 48 - 16, 98, 20, Ax.format("gui.advancements")));
      this.buttonList.add(new jK(6, this.width / 2 + 2, this.height / 4 + 48 - 16, 98, 20, Ax.format("gui.stats")));
   }

   protected void actionPerformed(jK button) throws IOException {
      switch (button.id) {
         case 0:
            this.mc.displayGuiScreen(new kL(this, nC.gameSettings));
            break;
         case 1:
            boolean flag = this.mc.isIntegratedServerRunning();
            button.enabled = false;
            this.mc.world.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((pm)null);
            if (flag) {
               this.mc.displayGuiScreen(new 0cx());
            } else {
               this.mc.displayGuiScreen(new kI(new 0cx()));
            }
         case 2:
         case 3:
         default:
            break;
         case 4:
            this.mc.displayGuiScreen((lg)null);
            this.mc.setIngameFocus();
            break;
         case 5:
            this.mc.displayGuiScreen(new jx(nC.player.connection.getAdvancementManager()));
            break;
         case 6:
            this.mc.displayGuiScreen(new jq(this, nC.player.getStatFileWriter()));
            break;
         case 7:
            this.mc.displayGuiScreen(new lu(this));
      }

   }

   public void updateScreen() {
      super.updateScreen();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("menu.game"), this.width / 2, 40, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
