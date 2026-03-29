package neo;

import java.io.IOException;

public class Tx implements Sz<Ts> {
   private int entityId;
   private int type;

   public Tx() {
   }

   public Tx(Ig entityIn, int typeIn) {
      this.entityId = entityIn.getEntityId();
      this.type = typeIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.type = buf.readUnsignedByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(this.type);
   }

   public void processPacket(Ts handler) {
      handler.handleAnimation(this);
   }

   public int getEntityID() {
      return this.entityId;
   }

   public int getAnimationType() {
      return this.type;
   }

   public String toString() {
      return "SPacketAnimation{entityId=" + this.entityId + ", type=" + this.type + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
