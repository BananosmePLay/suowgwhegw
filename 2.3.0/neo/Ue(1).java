package neo;

import java.io.IOException;

public class Ue implements Sz<Ts> {
   private int entityId;
   private double posX;
   private double posY;
   private double posZ;
   private byte yaw;
   private byte pitch;
   private boolean onGround;

   public Ue() {
   }

   public Ue(Ig entityIn) {
      this.entityId = entityIn.getEntityId();
      this.posX = entityIn.posX;
      this.posY = entityIn.posY;
      this.posZ = entityIn.posZ;
      this.yaw = (byte)((int)(entityIn.rotationYaw * 256.0F / 360.0F));
      this.pitch = (byte)((int)(entityIn.rotationPitch * 256.0F / 360.0F));
      this.onGround = entityIn.onGround;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.posX = buf.readDouble();
      this.posY = buf.readDouble();
      this.posZ = buf.readDouble();
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.onGround = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeDouble(this.posX);
      buf.writeDouble(this.posY);
      buf.writeDouble(this.posZ);
      buf.writeByte(this.yaw);
      buf.writeByte(this.pitch);
      buf.writeBoolean(this.onGround);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityTeleport(this);
   }

   public int getEntityId() {
      return this.entityId;
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

   public byte getYaw() {
      return this.yaw;
   }

   public byte getPitch() {
      return this.pitch;
   }

   public boolean getOnGround() {
      return this.onGround;
   }

   public String toString() {
      return "SPacketEntityTeleport{entityId=" + this.entityId + ", posX=" + this.posX + ", posY=" + this.posY + ", posZ=" + this.posZ + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", onGround=" + this.onGround + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
