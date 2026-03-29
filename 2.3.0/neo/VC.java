package neo;

import java.io.IOException;

public class VC implements Sz<Vy> {
   private long clientTime;

   public VC() {
   }

   public VC(long clientTimeIn) {
      this.clientTime = clientTimeIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeLong(this.clientTime);
   }

   public void processPacket(Vy handler) {
      handler.handlePong(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Vy)var1);
   }
}
