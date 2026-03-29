package neo;

import java.io.IOException;

public class TF implements Sz<Ts> {
   private int windowId;

   public TF() {
   }

   public TF(int windowIdIn) {
      this.windowId = windowIdIn;
   }

   public void processPacket(Ts handler) {
      handler.handleCloseWindow(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
   }

   public String toString() {
      return "SPacketCloseWindow{windowId=" + this.windowId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
