package neo;

import java.io.IOException;
import java.util.Set;

public class Uy implements Sz<Ts> {
   private double x;
   private double y;
   private double z;
   public float yaw;
   public float pitch;
   private Set<Ux> flags;
   private int teleportId;

   public Uy() {
   }

   public Uy(double xIn, double yIn, double zIn, float yawIn, float pitchIn, Set<Ux> flagsIn, int teleportIdIn) {
      this.x = xIn;
      this.y = yIn;
      this.z = zIn;
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.flags = flagsIn;
      this.teleportId = teleportIdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
      this.flags = Ux.unpack(buf.readUnsignedByte());
      this.teleportId = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.yaw);
      buf.writeFloat(this.pitch);
      buf.writeByte(Ux.pack(this.flags));
      buf.writeVarInt(this.teleportId);
   }

   public void processPacket(Ts handler) {
      handler.handlePlayerPosLook(this);
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

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public int getTeleportId() {
      return this.teleportId;
   }

   public Set<Ux> getFlags() {
      return this.flags;
   }

   public String toString() {
      return "SPacketPlayerPosLook{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", flags=" + this.flags + ", teleportId=" + this.teleportId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
