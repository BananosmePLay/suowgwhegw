package neo;

import java.io.IOException;

public class UJ implements Sz<Ts> {
   private int windowId;
   private int slot;
   private Qy item;

   public UJ() {
      this.item = Qy.EMPTY;
   }

   public UJ(int windowIdIn, int slotIn, Qy itemIn) {
      this.item = Qy.EMPTY;
      this.windowId = windowIdIn;
      this.slot = slotIn;
      this.item = itemIn.copy();
   }

   public void processPacket(Ts handler) {
      handler.handleSetSlot(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readByte();
      this.slot = buf.readShort();
      this.item = buf.readItemStack();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.slot);
      buf.writeItemStack(this.item);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getSlot() {
      return this.slot;
   }

   public Qy getStack() {
      return this.item;
   }

   public String toString() {
      return "SPacketSetSlot{windowId=" + this.windowId + ", slot=" + this.slot + ", item=" + this.item + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
