package neo;

import java.io.IOException;

public class TS extends TV {
   public TS() {
   }

   public TS(int entityIdIn, long xIn, long yIn, long zIn, boolean onGroundIn) {
      super(entityIdIn);
      this.posX = (int)xIn;
      this.posY = (int)yIn;
      this.posZ = (int)zIn;
      this.onGround = onGroundIn;
   }

   public void readPacketData(SA buf) throws IOException {
      super.readPacketData(buf);
      this.posX = buf.readShort();
      this.posY = buf.readShort();
      this.posZ = buf.readShort();
      this.onGround = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      super.writePacketData(buf);
      buf.writeShort(this.posX);
      buf.writeShort(this.posY);
      buf.writeShort(this.posZ);
      buf.writeBoolean(this.onGround);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      super.processPacket((Ts)var1);
   }
}
