package neo;

import java.io.IOException;

public class SK implements Sz<Tt> {
   private int telportId;

   public SK() {
   }

   public SK(int teleportIdIn) {
      this.telportId = teleportIdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.telportId = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.telportId);
   }

   public void processPacket(Tt handler) {
      handler.processConfirmTeleport(this);
   }

   public int getTeleportId() {
      return this.telportId;
   }

   public String toString() {
      return "CPacketConfirmTeleport{telportId=" + this.telportId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
