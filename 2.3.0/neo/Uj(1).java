package neo;

import java.io.IOException;

public class Uj implements Sz<Ts> {
   private long id;

   public Uj() {
   }

   public Uj(long idIn) {
      this.id = idIn;
   }

   public void processPacket(Ts handler) {
      handler.handleKeepAlive(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.id = buf.readLong();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeLong(this.id);
   }

   public long getId() {
      return this.id;
   }

   public String toString() {
      return "SPacketKeepAlive{id=" + this.id + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
