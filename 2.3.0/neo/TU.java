package neo;

import java.io.IOException;

public class TU extends TV {
   public TU() {
      this.rotating = true;
   }

   public TU(int entityIdIn, long xIn, long yIn, long zIn, byte yawIn, byte pitchIn, boolean onGroundIn) {
      super(entityIdIn);
      this.posX = (int)xIn;
      this.posY = (int)yIn;
      this.posZ = (int)zIn;
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.onGround = onGroundIn;
      this.rotating = true;
   }

   public void readPacketData(SA buf) throws IOException {
      super.readPacketData(buf);
      this.posX = buf.readShort();
      this.posY = buf.readShort();
      this.posZ = buf.readShort();
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.onGround = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      super.writePacketData(buf);
      buf.writeShort(this.posX);
      buf.writeShort(this.posY);
      buf.writeShort(this.posZ);
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
