package neo;

import java.io.IOException;

public class SY implements Sz<Tt> {
   protected double x;
   public double y;
   protected double z;
   protected float yaw;
   protected float pitch;
   public boolean onGround;
   protected boolean moving;
   protected boolean rotating;

   public SY() {
   }

   public SY(boolean onGroundIn) {
      this.onGround = onGroundIn;
   }

   public void processPacket(Tt handler) {
      handler.processPlayer(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.onGround = buf.readUnsignedByte() != 0;
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.onGround ? 1 : 0);
   }

   public double getX(double defaultValue) {
      return this.moving ? this.x : defaultValue;
   }

   public double getY(double defaultValue) {
      return this.moving ? this.y : defaultValue;
   }

   public double getZ(double defaultValue) {
      return this.moving ? this.z : defaultValue;
   }

   public float getYaw(float defaultValue) {
      return this.rotating ? this.yaw : defaultValue;
   }

   public float getPitch(float defaultValue) {
      return this.rotating ? this.pitch : defaultValue;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public String toString() {
      return "CPacketPlayer{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", onGround=" + this.onGround + ", moving=" + this.moving + ", rotating=" + this.rotating + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
