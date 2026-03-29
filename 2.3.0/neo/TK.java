package neo;

import java.io.IOException;

public class TK implements Sz<Ts> {
   private int windowId;
   private short actionNumber;
   private boolean accepted;

   public TK() {
   }

   public TK(int windowIdIn, short actionNumberIn, boolean acceptedIn) {
      this.windowId = windowIdIn;
      this.actionNumber = actionNumberIn;
      this.accepted = acceptedIn;
   }

   public void processPacket(Ts handler) {
      handler.handleConfirmTransaction(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      this.actionNumber = buf.readShort();
      this.accepted = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.actionNumber);
      buf.writeBoolean(this.accepted);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public boolean wasAccepted() {
      return this.accepted;
   }

   public String toString() {
      return "SPacketConfirmTransaction{windowId=" + this.windowId + ", actionNumber=" + this.actionNumber + ", accepted=" + this.accepted + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
