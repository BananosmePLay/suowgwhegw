package neo;

import java.io.IOException;

public class UM implements Sz<Ts> {
   private int entityID;
   private double posX;
   private double posY;
   private double posZ;
   private int xpValue;

   public UM() {
   }

   public UM(Js orb) {
      this.entityID = orb.getEntityId();
      this.posX = orb.posX;
      this.posY = orb.posY;
      this.posZ = orb.posZ;
      this.xpValue = orb.getXpValue();
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.posX = buf.readDouble();
      this.posY = buf.readDouble();
      this.posZ = buf.readDouble();
      this.xpValue = buf.readShort();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeDouble(this.posX);
      buf.writeDouble(this.posY);
      buf.writeDouble(this.posZ);
      buf.writeShort(this.xpValue);
   }

   public void processPacket(Ts handler) {
      handler.handleSpawnExperienceOrb(this);
   }

   public int getEntityID() {
      return this.entityID;
   }

   public double getX() {
      return this.posX;
   }

   public double getY() {
      return this.posY;
   }

   public double getZ() {
      return this.posZ;
   }

   public int getXPValue() {
      return this.xpValue;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
