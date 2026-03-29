package neo;

import java.io.IOException;

public class SM implements Sz<Tt> {
   private int slotId;
   private Qy stack;

   public SM() {
      this.stack = Qy.EMPTY;
   }

   public SM(int slotIdIn, Qy stackIn) {
      this.stack = Qy.EMPTY;
      this.slotId = slotIdIn;
      this.stack = stackIn.copy();
   }

   public void processPacket(Tt handler) {
      handler.processCreativeInventoryAction(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.slotId = buf.readShort();
      this.stack = buf.readItemStack();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeShort(this.slotId);
      buf.writeItemStack(this.stack);
   }

   public int getSlotId() {
      return this.slotId;
   }

   public Qy getStack() {
      return this.stack;
   }

   public String toString() {
      return "CPacketCreativeInventoryAction{slotId=" + this.slotId + ", stack=" + this.stack + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
