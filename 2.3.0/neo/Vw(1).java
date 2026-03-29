package neo;

import java.io.IOException;

public class Vw implements Sz<Vz> {
   public Vw() {
   }

   public void readPacketData(SA buf) throws IOException {
   }

   public void writePacketData(SA buf) throws IOException {
   }

   public void processPacket(Vz handler) {
      handler.processServerQuery(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Vz)var1);
   }
}
