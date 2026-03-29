package neo;

import java.io.IOException;

public class Uh implements Sz<Ts> {
   private int heldItemHotbarIndex;

   public Uh() {
   }

   public Uh(int hotbarIndexIn) {
      this.heldItemHotbarIndex = hotbarIndexIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.heldItemHotbarIndex = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.heldItemHotbarIndex);
   }

   public void processPacket(Ts handler) {
      handler.handleHeldItemChange(this);
   }

   public int getHeldItemHotbarIndex() {
      return this.heldItemHotbarIndex;
   }

   public String toString() {
      return "SPacketHeldItemChange{heldItemHotbarIndex=" + this.heldItemHotbarIndex + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
