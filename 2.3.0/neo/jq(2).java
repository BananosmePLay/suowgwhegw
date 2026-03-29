package neo;

import java.io.IOException;

public class jq extends lg implements ml {
   protected lg parentScreen;
   protected String screenTitle = "Select world";
   private jm generalStats;
   private jo itemStats;
   private jl blockStats;
   private jp mobStats;
   private final XT stats;
   private ly displaySlot;
   private boolean doesGuiPauseGame = true;

   public jq(lg parent, XT manager) {
      this.parentScreen = parent;
      this.stats = manager;
   }

   public void initGui() {
      this.screenTitle = Ax.format("gui.stats");
      this.doesGuiPauseGame = true;
      this.mc.getConnection().sendPacket(new SI(SH.REQUEST_STATS));
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      if (this.displaySlot != null) {
         this.displaySlot.handleMouseInput();
      }

   }

   public void initLists() {
      this.generalStats = new jm(this, this.mc);
      this.generalStats.registerScrollButtons(1, 1);
      this.itemStats = new jo(this, this.mc);
      this.itemStats.registerScrollButtons(1, 1);
      this.blockStats = new jl(this, this.mc);
      this.blockStats.registerScrollButtons(1, 1);
      this.mobStats = new jp(this, this.mc);
      this.mobStats.registerScrollButtons(1, 1);
   }

   public void initButtons() {
      this.buttonList.add(new jK(0, this.width / 2 + 4, this.height - 28, 150, 20, Ax.format("gui.done")));
      this.buttonList.add(new jK(1, this.width / 2 - 160, this.height - 52, 80, 20, Ax.format("stat.generalButton")));
      jK guibutton = this.addButton(new jK(2, this.width / 2 - 80, this.height - 52, 80, 20, Ax.format("stat.blocksButton")));
      jK guibutton1 = this.addButton(new jK(3, this.width / 2, this.height - 52, 80, 20, Ax.format("stat.itemsButton")));
      jK guibutton2 = this.addButton(new jK(4, this.width / 2 + 80, this.height - 52, 80, 20, Ax.format("stat.mobsButton")));
      if (this.blockStats.getSize() == 0) {
         guibutton.enabled = false;
      }

      if (this.itemStats.getSize() == 0) {
         guibutton1.enabled = false;
      }

      if (this.mobStats.getSize() == 0) {
         guibutton2.enabled = false;
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
         } else if (button.id == 1) {
            this.displaySlot = this.generalStats;
         } else if (button.id == 3) {
            this.displaySlot = this.itemStats;
         } else if (button.id == 2) {
            this.displaySlot = this.blockStats;
         } else if (button.id == 4) {
            this.displaySlot = this.mobStats;
         } else {
            this.displaySlot.actionPerformed(button);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.doesGuiPauseGame) {
         this.drawDefaultBackground();
         this.drawCenteredString(this.fontRenderer, Ax.format("multiplayer.downloadingStats"), this.width / 2, this.height / 2, 16777215);
         this.drawCenteredString(this.fontRenderer, LOADING_STRINGS[(int)(nC.getSystemTime() / 150L % (long)LOADING_STRINGS.length)], this.width / 2, this.height / 2 + this.fontRenderer.FONT_HEIGHT * 2, 16777215);
      } else {
         this.displaySlot.drawScreen(mouseX, mouseY, partialTicks);
         this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
         super.drawScreen(mouseX, mouseY, partialTicks);
      }

   }

   public void onStatsUpdated() {
      if (this.doesGuiPauseGame) {
         this.initLists();
         this.initButtons();
         this.displaySlot = this.generalStats;
         this.doesGuiPauseGame = false;
      }

   }

   public boolean doesGuiPauseGame() {
      return !this.doesGuiPauseGame;
   }

   private void drawStatsScreen(int x, int y, OL itemIn) {
      this.drawButtonBackground(x + 1, y + 1);
      yh.enableRescaleNormal();
      yz.enableGUIStandardItemLighting();
      this.itemRender.renderItemIntoGUI(itemIn.getDefaultInstance(), x + 2, y + 2);
      yz.disableStandardItemLighting();
      yh.disableRescaleNormal();
   }

   private void drawButtonBackground(int x, int y) {
      this.drawSprite(x, y, 0, 0);
   }

   private void drawSprite(int x, int y, int u, int v) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(STAT_ICONS);
      float f = 0.0078125F;
      float f1 = 0.0078125F;
      int i = true;
      int j = true;
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos((double)(x + 0), (double)(y + 18), (double)this.zLevel).tex((double)((float)(u + 0) * 0.0078125F), (double)((float)(v + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 18), (double)(y + 18), (double)this.zLevel).tex((double)((float)(u + 18) * 0.0078125F), (double)((float)(v + 18) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 18), (double)(y + 0), (double)this.zLevel).tex((double)((float)(u + 18) * 0.0078125F), (double)((float)(v + 0) * 0.0078125F)).endVertex();
      bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(u + 0) * 0.0078125F), (double)((float)(v + 0) * 0.0078125F)).endVertex();
      tessellator.draw();
   }

   // $FF: synthetic method
   static void access$000(jq x0, int x1, int x2, int x3, int x4) {
      x0.drawSprite(x1, x2, x3, x4);
   }

   // $FF: synthetic method
   static XT access$100(jq x0) {
      return x0.stats;
   }

   // $FF: synthetic method
   static jH access$200(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$300(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$400(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$500(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$600(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static void access$700(jq x0, int x1, int x2, int x3, int x4, int x5, int x6) {
      x0.drawGradientRect(x1, x2, x3, x4, x5, x6);
   }

   // $FF: synthetic method
   static jH access$800(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$900(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static void access$1000(jq x0, int x1, int x2, int x3, int x4, int x5, int x6) {
      x0.drawGradientRect(x1, x2, x3, x4, x5, x6);
   }

   // $FF: synthetic method
   static jH access$1100(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static void access$1200(jq x0, int x1, int x2, OL x3) {
      x0.drawStatsScreen(x1, x2, x3);
   }

   // $FF: synthetic method
   static jH access$1300(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1400(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1500(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1600(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1700(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1800(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$1900(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$2000(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$2100(jq x0) {
      return x0.fontRenderer;
   }

   // $FF: synthetic method
   static jH access$2200(jq x0) {
      return x0.fontRenderer;
   }
}
