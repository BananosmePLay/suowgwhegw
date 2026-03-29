package neo;

import java.io.IOException;

public class SR implements Sz<Tt> {
   private int slotId;

   public SR() {
   }

   public SR(int slotIdIn) {
      this.slotId = slotIdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.slotId = buf.readShort();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeShort(this.slotId);
   }

   public void processPacket(Tt handler) {
      handler.processHeldItemChange(this);
   }

   public int getSlotId() {
      return this.slotId;
   }

   public String toString() {
      return "CPacketHeldItemChange{slotId=" + this.slotId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
