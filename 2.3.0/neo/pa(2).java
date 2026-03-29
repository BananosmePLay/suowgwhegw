package neo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pa extends lg {
   private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
   private static final Logger LOGGER = LogManager.getLogger();
   private Sp networkManager;
   private boolean cancel;
   private final lg previousGuiScreen;

   public pa(lg parent, nC mcIn, pf serverDataIn) {
      this.mc = mcIn;
      this.previousGuiScreen = parent;
      pd serveraddress = pd.fromString(serverDataIn.serverIP);
      mcIn.loadWorld((pm)null);
      mcIn.setServerData(serverDataIn);
      this.connect(serveraddress.getIP(), serveraddress.getPort());
   }

   public pa(lg parent, nC mcIn, String hostName, int port) {
      this.mc = mcIn;
      this.previousGuiScreen = parent;
      mcIn.loadWorld((pm)null);
      this.connect(hostName, port);
   }

   private void connect(final String ip, final int port) {
      LOGGER.info("Connecting to {}, {}", ip, port);
      (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet()) {
         public void run() {
            InetAddress inetaddress = null;

            try {
               if (pa.this.cancel) {
                  return;
               }

               inetaddress = InetAddress.getByName(ip);
               pa var10000 = pa.this;
               int var10002 = port;
               pa.this.mc;
               var10000.networkManager = Sp.createNetworkManagerAndConnect(inetaddress, var10002, nC.gameSettings.isUsingNativeTransport(), 30);
               pa.this.networkManager.setNetHandler(new pt(pa.this.networkManager, pa.this.mc, pa.this.previousGuiScreen));
               pa.this.networkManager.sendPacket(new RD(ip, port, RB.LOGIN));
               pa.this.networkManager.sendPacket(new RK(pa.this.mc.getSession().getProfile()));
            } catch (UnknownHostException var5) {
               UnknownHostException unknownhostexception = var5;
               if (pa.this.cancel) {
                  return;
               }

               pa.LOGGER.error("Couldn't connect to server", unknownhostexception);
               pa.this.mc.displayGuiScreen(new kd(pa.this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host"})));
            } catch (Exception var6) {
               Exception exception = var6;
               if (pa.this.cancel) {
                  return;
               }

               pa.LOGGER.error("Couldn't connect to server", exception);
               String s = exception.toString();
               if (inetaddress != null) {
                  String s1 = inetaddress + ":" + port;
                  s = s.replaceAll(s1, "");
               }

               pa.this.mc.displayGuiScreen(new kd(pa.this.previousGuiScreen, "connect.failed", new TextComponentTranslation("disconnect.genericReason", new Object[]{s})));
            }

         }
      }).start();
   }

   public void updateScreen() {
      if (this.networkManager != null) {
         if (this.networkManager.isChannelOpen()) {
            this.networkManager.processReceivedPackets();
         } else {
            this.networkManager.handleDisconnection();
         }
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 120 + 12, Ax.format("gui.cancel")));
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 0) {
         this.cancel = true;
         if (this.networkManager != null) {
            this.networkManager.closeChannel(new TextComponentString("Aborted"));
         }

         this.mc.displayGuiScreen(this.previousGuiScreen);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      if (this.networkManager == null) {
         this.drawCenteredString(this.fontRenderer, Ax.format("connect.connecting"), this.width / 2, this.height / 2 - 50, 16777215);
      } else {
         this.drawCenteredString(this.fontRenderer, Ax.format("connect.authorizing"), this.width / 2, this.height / 2 - 50, 16777215);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
