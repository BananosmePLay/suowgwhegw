package neo;

import java.io.IOException;

public class SL implements Sz<Tt> {
   private int windowId;
   private short uid;
   private boolean accepted;

   public SL() {
   }

   public SL(int windowIdIn, short uidIn, boolean acceptedIn) {
      this.windowId = windowIdIn;
      this.uid = uidIn;
      this.accepted = acceptedIn;
   }

   public void processPacket(Tt handler) {
      handler.processConfirmTransaction(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readByte();
      this.uid = buf.readShort();
      this.accepted = buf.readByte() != 0;
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.uid);
      buf.writeByte(this.accepted ? 1 : 0);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public short getUid() {
      return this.uid;
   }

   public String toString() {
      return "CPacketConfirmTransaction{windowId=" + this.windowId + ", uid=" + this.uid + ", accepted=" + this.accepted + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
