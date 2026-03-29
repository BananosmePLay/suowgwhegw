package neo;

import java.io.IOException;

public class Ud implements Sz<Ts> {
   private int entityId;
   private byte logicOpcode;

   public Ud() {
   }

   public Ud(Ig entityIn, byte opcodeIn) {
      this.entityId = entityIn.getEntityId();
      this.logicOpcode = opcodeIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readInt();
      this.logicOpcode = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.entityId);
      buf.writeByte(this.logicOpcode);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityStatus(this);
   }

   public Ig getEntity(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public byte getOpCode() {
      return this.logicOpcode;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
