package neo;

public class ke extends lg {
   private bjI customLoadingScreen = bjJ.getCustomLoadingScreen();

   public ke() {
   }

   public void initGui() {
      this.buttonList.clear();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.customLoadingScreen != null) {
         this.customLoadingScreen.drawBackground(this.width, this.height);
      } else {
         this.drawBackground(0);
      }

      this.drawCenteredString(this.fontRenderer, Ax.format("multiplayer.downloadingTerrain"), this.width / 2, this.height / 2 - 50, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }
}
