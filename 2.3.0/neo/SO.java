package neo;

import java.io.IOException;

public class SO implements Sz<Tt> {
   private int windowId;
   private int button;

   public SO() {
   }

   public SO(int windowIdIn, int buttonIn) {
      this.windowId = windowIdIn;
      this.button = buttonIn;
   }

   public void processPacket(Tt handler) {
      handler.processEnchantItem(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readByte();
      this.button = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeByte(this.button);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getButton() {
      return this.button;
   }

   public String toString() {
      return "CPacketEnchantItem{windowId=" + this.windowId + ", button=" + this.button + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
