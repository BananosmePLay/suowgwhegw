package neo;

import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;

public class WG extends WY {
   private QQ hostPlayerData;

   public WG(WK server) {
      super(server);
      this.setViewDistance(10);
   }

   protected void writePlayerData(MG playerIn) {
      if (playerIn.getName().equals(this.getServerInstance().getServerOwner())) {
         this.hostPlayerData = playerIn.writeToNBT(new QQ());
      }

      super.writePlayerData(playerIn);
   }

   public String allowUserToConnect(SocketAddress address, GameProfile profile) {
      return profile.getName().equalsIgnoreCase(this.getServerInstance().getServerOwner()) && this.getPlayerByUsername(profile.getName()) != null ? "That name is already taken." : super.allowUserToConnect(address, profile);
   }

   public WK getServerInstance() {
      return (WK)super.getServerInstance();
   }

   public QQ getHostPlayerData() {
      return this.hostPlayerData;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Xx getServerInstance() {
      return this.getServerInstance();
   }
}
