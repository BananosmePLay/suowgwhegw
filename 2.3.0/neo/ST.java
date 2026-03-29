package neo;

import java.io.IOException;

public class ST implements Sz<Tt> {
   private long key;

   public ST() {
   }

   public ST(long idIn) {
      this.key = idIn;
   }

   public void processPacket(Tt handler) {
      handler.processKeepAlive(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.key = buf.readLong();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeLong(this.key);
   }

   public long getKey() {
      return this.key;
   }

   public String toString() {
      return "CPacketKeepAlive{key=" + this.key + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
