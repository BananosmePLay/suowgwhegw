package neo;

import java.io.IOException;

public class SW extends SY {
   public SW() {
      this.moving = true;
      this.rotating = true;
   }

   public SW(double xIn, double yIn, double zIn, float yawIn, float pitchIn, boolean onGroundIn) {
      this.x = xIn;
      this.y = yIn;
      this.z = zIn;
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.onGround = onGroundIn;
      this.rotating = true;
      this.moving = true;
   }

   public void readPacketData(SA buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
      super.readPacketData(buf);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.yaw);
      buf.writeFloat(this.pitch);
      super.writePacketData(buf);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      super.processPacket((Tt)var1);
   }
}
