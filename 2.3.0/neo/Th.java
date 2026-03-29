package neo;

import java.io.IOException;

public class Th implements Sz<Tt> {
   private Tg action;

   public Th() {
   }

   public Th(Tg p_i47156_1_) {
      this.action = p_i47156_1_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.action = (Tg)buf.readEnumValue(Tg.class);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.action);
   }

   public void processPacket(Tt handler) {
      handler.handleResourcePackStatus(this);
   }

   public String toString() {
      return "CPacketResourcePackStatus{action=" + this.action + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
