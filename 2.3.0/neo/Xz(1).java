package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class Xz implements RF {
   private final Xx server;
   private final Sp networkManager;

   public Xz(Xx serverIn, Sp netManager) {
      this.server = serverIn;
      this.networkManager = netManager;
   }

   public void processHandshake(RD packetIn) {
      switch (packetIn.getRequestedState()) {
         case LOGIN:
            this.networkManager.setConnectionState(RB.LOGIN);
            TextComponentTranslation itextcomponent1;
            if (packetIn.getProtocolVersion() > 340) {
               itextcomponent1 = new TextComponentTranslation("multiplayer.disconnect.outdated_server", new Object[]{"1.12.2"});
               this.networkManager.sendPacket(new RQ(itextcomponent1));
               this.networkManager.closeChannel(itextcomponent1);
            } else if (packetIn.getProtocolVersion() < 340) {
               itextcomponent1 = new TextComponentTranslation("multiplayer.disconnect.outdated_client", new Object[]{"1.12.2"});
               this.networkManager.sendPacket(new RQ(itextcomponent1));
               this.networkManager.closeChannel(itextcomponent1);
            } else {
               this.networkManager.setNetHandler(new XD(this.server, this.networkManager));
            }
            break;
         case STATUS:
            this.networkManager.setConnectionState(RB.STATUS);
            this.networkManager.setNetHandler(new XE(this.server, this.networkManager));
            break;
         default:
            throw new UnsupportedOperationException("Invalid intention " + packetIn.getRequestedState());
      }

   }

   public void onDisconnect(ITextComponent reason) {
   }
}
