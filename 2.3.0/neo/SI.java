package neo;

import java.io.IOException;

public class SI implements Sz<Tt> {
   private SH status;

   public SI() {
   }

   public SI(SH p_i46886_1_) {
      this.status = p_i46886_1_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.status = (SH)buf.readEnumValue(SH.class);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.status);
   }

   public void processPacket(Tt handler) {
      handler.processClientStatus(this);
   }

   public SH getStatus() {
      return this.status;
   }

   public String toString() {
      return "CPacketClientStatus{status=" + this.status + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
