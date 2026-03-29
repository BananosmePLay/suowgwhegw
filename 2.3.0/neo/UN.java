package neo;

import java.io.IOException;

public class UN implements Sz<Ts> {
   private int entityId;
   private double x;
   private double y;
   private double z;
   private int type;

   public UN() {
   }

   public UN(Ig entityIn) {
      this.entityId = entityIn.getEntityId();
      this.x = entityIn.posX;
      this.y = entityIn.posY;
      this.z = entityIn.posZ;
      if (entityIn instanceof HX) {
         this.type = 1;
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.type = buf.readByte();
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(this.type);
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
   }

   public void processPacket(Ts handler) {
      handler.handleSpawnGlobalEntity(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public int getType() {
      return this.type;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
