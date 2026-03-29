package neo;

import java.io.IOException;

public class SJ implements Sz<Tt> {
   public int windowId;

   public SJ() {
   }

   public SJ(int windowIdIn) {
      this.windowId = windowIdIn;
   }

   public void processPacket(Tt handler) {
      handler.processCloseWindow(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
   }

   public String toString() {
      return "CPacketCloseWindow{windowId=" + this.windowId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
