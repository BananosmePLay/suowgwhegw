package neo;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import de.florianmichael.viamcp.ViaMCP;
import de.florianmichael.viamcp.gui.GuiProtocolSelector;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class kI extends lg {
   private static final Logger LOGGER = LogManager.getLogger();
   private final pG oldServerPinger = new pG();
   private final lg parentScreen;
   private mI serverListSelector;
   private pg savedServerList;
   private jK btnEditServer;
   private jK btnSelectServer;
   private jK btnDeleteServer;
   private boolean deletingServer;
   private boolean addingServer;
   private boolean editingServer;
   private boolean directConnect;
   private String hoveringText;
   private pf selectedServer;
   private pn lanServerList;
   private po lanServerDetector;
   private boolean initialized;

   public kI(lg parentScreen) {
      this.parentScreen = parentScreen;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      if (this.initialized) {
         this.serverListSelector.setDimensions(this.width, this.height, 32, this.height - 64);
      } else {
         this.initialized = true;
         this.savedServerList = new pg(this.mc);
         this.savedServerList.loadServerList();
         this.lanServerList = new pn();

         try {
            this.lanServerDetector = new po(this.lanServerList);
            this.lanServerDetector.start();
         } catch (Exception var2) {
            Exception exception = var2;
            LOGGER.warn("Unable to start LAN server detection: {}", exception.getMessage());
         }

         this.serverListSelector = new mI(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
         this.serverListSelector.updateOnlineServers(this.savedServerList);
      }

      this.createButtons();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.serverListSelector.handleMouseInput();
   }

   public void createButtons() {
      this.btnEditServer = this.addButton(new jK(7, this.width / 2 - 154, this.height - 28, 70, 20, Ax.format("selectServer.edit")));
      this.btnDeleteServer = this.addButton(new jK(2, this.width / 2 - 74, this.height - 28, 70, 20, Ax.format("selectServer.delete")));
      this.btnSelectServer = this.addButton(new jK(1, this.width / 2 - 154, this.height - 52, 100, 20, Ax.format("selectServer.select")));
      this.buttonList.add(new jK(4, this.width / 2 - 50, this.height - 52, 100, 20, Ax.format("selectServer.direct")));
      this.buttonList.add(new jK(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, Ax.format("selectServer.add")));
      this.buttonList.add(new jK(8, this.width / 2 + 4, this.height - 28, 70, 20, Ax.format("selectServer.refresh")));
      this.buttonList.add(new jK(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, Ax.format("gui.cancel")));
      this.buttonList.add(new jK(9, this.width - 50, 5, 40, 20, "Proxy"));
      this.buttonList.add(new jK(10, 140, 5, 30, 20, "List"));
      this.buttonList.add(ViaMCP.INSTANCE.getAsyncVersionSlider());
      this.selectServer(this.serverListSelector.getSelected());
   }

   public void updateScreen() {
      super.updateScreen();
      if (this.lanServerList.getWasUpdated()) {
         List<pq> list = this.lanServerList.getLanServers();
         this.lanServerList.setWasNotUpdated();
         this.serverListSelector.updateNetworkServers(list);
      }

      this.oldServerPinger.pingPendingNetworks();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      if (this.lanServerDetector != null) {
         this.lanServerDetector.interrupt();
         this.lanServerDetector = null;
      }

      this.oldServerPinger.clearPendingNetworks();
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         kx guilistextended$iguilistentry = this.serverListSelector.getSelected() < 0 ? null : this.serverListSelector.getListEntry(this.serverListSelector.getSelected());
         if (button.id == 2 && guilistextended$iguilistentry instanceof mH) {
            String s4 = ((mH)guilistextended$iguilistentry).getServerData().serverName;
            if (s4 != null) {
               this.deletingServer = true;
               String s = Ax.format("selectServer.deleteQuestion");
               String s1 = "'" + s4 + "' " + Ax.format("selectServer.deleteWarning");
               String s2 = Ax.format("selectServer.deleteButton");
               String s3 = Ax.format("gui.cancel");
               lK guiyesno = new lK(this, s, s1, s2, s3, this.serverListSelector.getSelected());
               this.mc.displayGuiScreen(guiyesno);
            }
         } else if (button.id == 1) {
            this.connectToSelected();
         } else if (button.id == 4) {
            this.directConnect = true;
            this.selectedServer = new pf(Ax.format("selectServer.defaultName"), "", false);
            this.mc.displayGuiScreen(new ls(this, this.selectedServer));
         } else if (button.id == 3) {
            this.addingServer = true;
            this.selectedServer = new pf(Ax.format("selectServer.defaultName"), "", false);
            this.mc.displayGuiScreen(new li(this, this.selectedServer));
         } else if (button.id == 7 && guilistextended$iguilistentry instanceof mH) {
            this.editingServer = true;
            pf serverdata = ((mH)guilistextended$iguilistentry).getServerData();
            this.selectedServer = new pf(serverdata.serverName, serverdata.serverIP, false);
            this.selectedServer.copyFrom(serverdata);
            this.mc.displayGuiScreen(new li(this, this.selectedServer));
         } else if (button.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
         } else if (button.id == 8) {
            this.refreshServerList();
         } else if (button.id == 9) {
            this.mc.displayGuiScreen(new 0cz(this));
         } else if (button.id == 10) {
            this.mc.displayGuiScreen(new GuiProtocolSelector(this));
         }
      }

   }

   private void refreshServerList() {
      this.mc.displayGuiScreen(new kI(this.parentScreen));
   }

   public void confirmClicked(boolean result, int id) {
      kx guilistextended$iguilistentry = this.serverListSelector.getSelected() < 0 ? null : this.serverListSelector.getListEntry(this.serverListSelector.getSelected());
      if (this.deletingServer) {
         this.deletingServer = false;
         if (result && guilistextended$iguilistentry instanceof mH) {
            this.savedServerList.removeServerData(this.serverListSelector.getSelected());
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.updateOnlineServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.directConnect) {
         this.directConnect = false;
         if (result) {
            this.connectToServer(this.selectedServer);
         } else {
            this.mc.displayGuiScreen(this);
         }
      } else if (this.addingServer) {
         this.addingServer = false;
         if (result) {
            this.savedServerList.addServerData(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.updateOnlineServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.editingServer) {
         this.editingServer = false;
         if (result && guilistextended$iguilistentry instanceof mH) {
            pf serverdata = ((mH)guilistextended$iguilistentry).getServerData();
            serverdata.serverName = this.selectedServer.serverName;
            serverdata.serverIP = this.selectedServer.serverIP;
            serverdata.copyFrom(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.updateOnlineServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      int i = this.serverListSelector.getSelected();
      kx guilistextended$iguilistentry = i < 0 ? null : this.serverListSelector.getListEntry(i);
      if (keyCode == 63) {
         this.refreshServerList();
      } else if (i >= 0) {
         if (keyCode == 200) {
            if (isShiftKeyDown()) {
               if (i > 0 && guilistextended$iguilistentry instanceof mH) {
                  this.savedServerList.swapServers(i, i - 1);
                  this.selectServer(this.serverListSelector.getSelected() - 1);
                  this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
                  this.serverListSelector.updateOnlineServers(this.savedServerList);
               }
            } else if (i > 0) {
               this.selectServer(this.serverListSelector.getSelected() - 1);
               this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getListEntry(this.serverListSelector.getSelected()) instanceof mF) {
                  if (this.serverListSelector.getSelected() > 0) {
                     this.selectServer(this.serverListSelector.getSize() - 1);
                     this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
                  } else {
                     this.selectServer(-1);
                  }
               }
            } else {
               this.selectServer(-1);
            }
         } else if (keyCode == 208) {
            if (isShiftKeyDown()) {
               if (i < this.savedServerList.countServers() - 1) {
                  this.savedServerList.swapServers(i, i + 1);
                  this.selectServer(i + 1);
                  this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
                  this.serverListSelector.updateOnlineServers(this.savedServerList);
               }
            } else if (i < this.serverListSelector.getSize()) {
               this.selectServer(this.serverListSelector.getSelected() + 1);
               this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getListEntry(this.serverListSelector.getSelected()) instanceof mF) {
                  if (this.serverListSelector.getSelected() < this.serverListSelector.getSize() - 1) {
                     this.selectServer(this.serverListSelector.getSize() + 1);
                     this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
                  } else {
                     this.selectServer(-1);
                  }
               }
            } else {
               this.selectServer(-1);
            }
         } else if (keyCode != 28 && keyCode != 156) {
            super.keyTyped(typedChar, keyCode);
         } else {
            this.actionPerformed((jK)this.buttonList.get(2));
         }
      } else {
         super.keyTyped(typedChar, keyCode);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.hoveringText = null;
      this.drawDefaultBackground();
      this.serverListSelector.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, Ax.format("multiplayer.title"), this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.hoveringText != null) {
         this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), mouseX, mouseY);
      }

   }

   public void connectToSelected() {
      kx guilistextended$iguilistentry = this.serverListSelector.getSelected() < 0 ? null : this.serverListSelector.getListEntry(this.serverListSelector.getSelected());
      if (guilistextended$iguilistentry instanceof mH) {
         this.connectToServer(((mH)guilistextended$iguilistentry).getServerData());
      } else if (guilistextended$iguilistentry instanceof mE) {
         pq lanserverinfo = ((mE)guilistextended$iguilistentry).getServerData();
         this.connectToServer(new pf(lanserverinfo.getServerMotd(), lanserverinfo.getServerIpPort(), true));
      }

   }

   private void connectToServer(pf server) {
      this.mc.displayGuiScreen(new pa(this, this.mc, server));
   }

   public void selectServer(int index) {
      this.serverListSelector.setSelectedSlotIndex(index);
      kx guilistextended$iguilistentry = index < 0 ? null : this.serverListSelector.getListEntry(index);
      this.btnSelectServer.enabled = false;
      this.btnEditServer.enabled = false;
      this.btnDeleteServer.enabled = false;
      if (guilistextended$iguilistentry != null && !(guilistextended$iguilistentry instanceof mF)) {
         this.btnSelectServer.enabled = true;
         if (guilistextended$iguilistentry instanceof mH) {
            this.btnEditServer.enabled = true;
            this.btnDeleteServer.enabled = true;
         }
      }

   }

   public pG getOldServerPinger() {
      return this.oldServerPinger;
   }

   public void setHoveringText(String p_146793_1_) {
      this.hoveringText = p_146793_1_;
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.serverListSelector.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      this.serverListSelector.mouseReleased(mouseX, mouseY, state);
   }

   public pg getServerList() {
      return this.savedServerList;
   }

   public boolean canMoveUp(mH p_175392_1_, int p_175392_2_) {
      return p_175392_2_ > 0;
   }

   public boolean canMoveDown(mH p_175394_1_, int p_175394_2_) {
      return p_175394_2_ < this.savedServerList.countServers() - 1;
   }

   public void moveServerUp(mH p_175391_1_, int p_175391_2_, boolean p_175391_3_) {
      int i = p_175391_3_ ? 0 : p_175391_2_ - 1;
      this.savedServerList.swapServers(p_175391_2_, i);
      if (this.serverListSelector.getSelected() == p_175391_2_) {
         this.selectServer(i);
      }

      this.serverListSelector.updateOnlineServers(this.savedServerList);
   }

   public void moveServerDown(mH p_175393_1_, int p_175393_2_, boolean p_175393_3_) {
      int i = p_175393_3_ ? this.savedServerList.countServers() - 1 : p_175393_2_ + 1;
      this.savedServerList.swapServers(p_175393_2_, i);
      if (this.serverListSelector.getSelected() == p_175393_2_) {
         this.selectServer(i);
      }

      this.serverListSelector.updateOnlineServers(this.savedServerList);
   }
}
