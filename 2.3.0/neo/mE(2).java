package neo;

public class mE implements kx {
   private final kI screen;
   protected final nC mc;
   protected final pq serverData;
   private long lastClickTime;

   protected mE(kI p_i47141_1_, pq p_i47141_2_) {
      this.screen = p_i47141_1_;
      this.serverData = p_i47141_2_;
      this.mc = nC.getMinecraft();
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      this.mc.fontRenderer.drawString(Ax.format("lanServer.title"), x + 32 + 3, y + 1, 16777215);
      this.mc.fontRenderer.drawString(this.serverData.getServerMotd(), x + 32 + 3, y + 12, 8421504);
      nC var10000 = this.mc;
      if (nC.gameSettings.hideServerAddress) {
         this.mc.fontRenderer.drawString(Ax.format("selectServer.hiddenAddress"), x + 32 + 3, y + 12 + 11, 3158064);
      } else {
         this.mc.fontRenderer.drawString(this.serverData.getServerIpPort(), x + 32 + 3, y + 12 + 11, 3158064);
      }

   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      this.screen.selectServer(slotIndex);
      if (nC.getSystemTime() - this.lastClickTime < 250L) {
         this.screen.connectToSelected();
      }

      this.lastClickTime = nC.getSystemTime();
      return false;
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
   }

   public pq getServerData() {
      return this.serverData;
   }
}
