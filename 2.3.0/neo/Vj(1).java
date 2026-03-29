package neo;

import java.io.IOException;

public class Vj implements Sz<Ts> {
   private int windowId;
   private int property;
   private int value;

   public Vj() {
   }

   public Vj(int windowIdIn, int propertyIn, int valueIn) {
      this.windowId = windowIdIn;
      this.property = propertyIn;
      this.value = valueIn;
   }

   public void processPacket(Ts handler) {
      handler.handleWindowProperty(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      this.property = buf.readShort();
      this.value = buf.readShort();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.property);
      buf.writeShort(this.value);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getProperty() {
      return this.property;
   }

   public int getValue() {
      return this.value;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
