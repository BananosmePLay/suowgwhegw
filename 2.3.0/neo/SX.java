package neo;

import java.io.IOException;

public class SX extends SY {
   public SX() {
      this.rotating = true;
   }

   public SX(float yawIn, float pitchIn, boolean onGroundIn) {
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.onGround = onGroundIn;
      this.rotating = true;
   }

   public void readPacketData(SA buf) throws IOException {
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
      super.readPacketData(buf);
   }

   public void writePacketData(SA buf) throws IOException {
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
