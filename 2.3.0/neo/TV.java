package neo;

import java.io.IOException;

public class TV implements Sz<Ts> {
   protected int entityId;
   protected int posX;
   protected int posY;
   protected int posZ;
   protected byte yaw;
   protected byte pitch;
   protected boolean onGround;
   protected boolean rotating;

   public TV() {
   }

   public TV(int entityIdIn) {
      this.entityId = entityIdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityMovement(this);
   }

   public String toString() {
      return "Entity_" + super.toString();
   }

   public Ig getEntity(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public int getX() {
      return this.posX;
   }

   public int getY() {
      return this.posY;
   }

   public int getZ() {
      return this.posZ;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public byte getPitch() {
      return this.pitch;
   }

   public boolean isRotating() {
      return this.rotating;
   }

   public boolean getOnGround() {
      return this.onGround;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
