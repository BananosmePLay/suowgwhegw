package neo;

import java.io.IOException;

public class TT extends TV {
   public TT() {
      this.rotating = true;
   }

   public TT(int entityIdIn, byte yawIn, byte pitchIn, boolean onGroundIn) {
      super(entityIdIn);
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.rotating = true;
      this.onGround = onGroundIn;
   }

   public void readPacketData(SA buf) throws IOException {
      super.readPacketData(buf);
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.onGround = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      super.writePacketData(buf);
      buf.writeByte(this.yaw);
      buf.writeByte(this.pitch);
      buf.writeBoolean(this.onGround);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      super.processPacket((Ts)var1);
   }
}
