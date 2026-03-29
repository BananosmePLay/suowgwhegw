package neo;

import java.io.IOException;

public class TZ implements Sz<Ts> {
   private int entityId;
   private byte yaw;

   public TZ() {
   }

   public TZ(Ig entityIn, byte yawIn) {
      this.entityId = entityIn.getEntityId();
      this.yaw = yawIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.yaw = buf.readByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(this.yaw);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityHeadLook(this);
   }

   public Ig getEntity(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public byte getYaw() {
      return this.yaw;
   }

   public String toString() {
      return "SPacketEntityHeadLook{entityId=" + this.entityId + ", yaw=" + this.yaw + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
