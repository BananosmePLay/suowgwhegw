package neo;

import java.io.IOException;

public class UZ implements Sz<Ts> {
   private int x;
   private int z;

   public UZ() {
   }

   public UZ(int xIn, int zIn) {
      this.x = xIn;
      this.z = zIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.x = buf.readInt();
      this.z = buf.readInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.x);
      buf.writeInt(this.z);
   }

   public void processPacket(Ts handler) {
      handler.processChunkUnload(this);
   }

   public int getX() {
      return this.x;
   }

   public int getZ() {
      return this.z;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
