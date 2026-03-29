package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class lu extends lg {
   private final lg lastScreen;
   private jK allowCheatsButton;
   private jK gameModeButton;
   private String gameMode = "survival";
   private boolean allowCheats;

   public lu(lg lastScreenIn) {
      this.lastScreen = lastScreenIn;
   }

   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new jK(101, this.width / 2 - 155, this.height - 28, 150, 20, Ax.format("lanServer.start")));
      this.buttonList.add(new jK(102, this.width / 2 + 5, this.height - 28, 150, 20, Ax.format("gui.cancel")));
      this.gameModeButton = this.addButton(new jK(104, this.width / 2 - 155, 100, 150, 20, Ax.format("selectWorld.gameMode")));
      this.allowCheatsButton = this.addButton(new jK(103, this.width / 2 + 5, 100, 150, 20, Ax.format("selectWorld.allowCommands")));
      this.updateDisplayNames();
   }

   private void updateDisplayNames() {
      this.gameModeButton.displayString = Ax.format("selectWorld.gameMode") + ": " + Ax.format("selectWorld.gameMode." + this.gameMode);
      this.allowCheatsButton.displayString = Ax.format("selectWorld.allowCommands") + " ";
      if (this.allowCheats) {
         this.allowCheatsButton.displayString = this.allowCheatsButton.displayString + Ax.format("options.on");
      } else {
         this.allowCheatsButton.displayString = this.allowCheatsButton.displayString + Ax.format("options.off");
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 102) {
         this.mc.displayGuiScreen(this.lastScreen);
      } else if (button.id == 104) {
         if ("spectator".equals(this.gameMode)) {
            this.gameMode = "creative";
         } else if ("creative".equals(this.gameMode)) {
            this.gameMode = "adventure";
         } else if ("adventure".equals(this.gameMode)) {
            this.gameMode = "survival";
         } else {
            this.gameMode = "spectator";
         }

         this.updateDisplayNames();
      } else if (button.id == 103) {
         this.allowCheats = !this.allowCheats;
         this.updateDisplayNames();
      } else if (button.id == 101) {
         this.mc.displayGuiScreen((lg)null);
         String s = this.mc.getIntegratedServer().shareToLAN(bbb.getByName(this.gameMode), this.allowCheats);
         Object itextcomponent;
         if (s != null) {
            itextcomponent = new TextComponentTranslation("commands.publish.started", new Object[]{s});
         } else {
            itextcomponent = new TextComponentString("commands.publish.failed");
         }

         this.mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent)itextcomponent);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("lanServer.title"), this.width / 2, 50, 16777215);
      this.drawCenteredString(this.fontRenderer, Ax.format("lanServer.otherPlayers"), this.width / 2, 82, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
