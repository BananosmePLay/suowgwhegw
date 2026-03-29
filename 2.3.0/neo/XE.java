package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class XE implements Vz {
   private static final ITextComponent EXIT_MESSAGE = new TextComponentString("Status request has been handled.");
   private final Xx server;
   private final Sp networkManager;
   private boolean handled;

   public XE(Xx serverIn, Sp netManager) {
      this.server = serverIn;
      this.networkManager = netManager;
   }

   public void onDisconnect(ITextComponent reason) {
   }

   public void processServerQuery(Vw packetIn) {
      if (this.handled) {
         this.networkManager.closeChannel(EXIT_MESSAGE);
      } else {
         this.handled = true;
         this.networkManager.sendPacket(new VD(this.server.getServerStatusResponse()));
      }

   }

   public void processPing(Vv packetIn) {
      this.networkManager.sendPacket(new VC(packetIn.getClientTime()));
      this.networkManager.closeChannel(EXIT_MESSAGE);
   }
}
