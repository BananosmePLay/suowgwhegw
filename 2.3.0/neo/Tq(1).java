package neo;

import java.io.IOException;

public class Tq implements Sz<Tt> {
   private double x;
   private double y;
   private double z;
   private float yaw;
   private float pitch;

   public Tq() {
   }

   public Tq(Ig entityIn) {
      this.x = entityIn.posX;
      this.y = entityIn.posY;
      this.z = entityIn.posZ;
      this.yaw = entityIn.rotationYaw;
      this.pitch = entityIn.rotationPitch;
   }

   public void readPacketData(SA buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.yaw);
      buf.writeFloat(this.pitch);
   }

   public void processPacket(Tt handler) {
      handler.processVehicleMove(this);
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

   public String toString() {
      return "CPacketVehicleMove{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", pitch=" + this.pitch + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
