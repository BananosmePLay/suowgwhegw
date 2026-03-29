package neo;

import java.io.IOException;

public class Vv implements Sz<Vz> {
   private long clientTime;

   public Vv() {
   }

   public Vv(long clientTimeIn) {
      this.clientTime = clientTimeIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeLong(this.clientTime);
   }

   public void processPacket(Vz handler) {
      handler.processPing(this);
   }

   public long getClientTime() {
      return this.clientTime;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Vz)var1);
   }
}
