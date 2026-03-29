package neo;

import net.minecraft.util.text.ITextComponent;

public class pr implements RF {
   private final Xx server;
   private final Sp networkManager;

   public pr(Xx mcServerIn, Sp networkManagerIn) {
      this.server = mcServerIn;
      this.networkManager = networkManagerIn;
   }

   public void processHandshake(RD packetIn) {
      this.networkManager.setConnectionState(packetIn.getRequestedState());
      this.networkManager.setNetHandler(new XD(this.server, this.networkManager));
   }

   public void onDisconnect(ITextComponent reason) {
   }
}
