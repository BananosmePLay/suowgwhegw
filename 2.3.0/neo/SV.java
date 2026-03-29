package neo;

import java.io.IOException;

public class SV extends SY {
   public SV() {
      this.moving = true;
   }

   public SV(double xIn, double yIn, double zIn, boolean onGroundIn) {
      this.x = xIn;
      this.y = yIn;
      this.z = zIn;
      this.onGround = onGroundIn;
      this.moving = true;
   }

   public void readPacketData(SA buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      super.readPacketData(buf);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      super.writePacketData(buf);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      super.processPacket((Tt)var1);
   }
}
