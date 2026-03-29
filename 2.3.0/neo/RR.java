package neo;

import java.io.IOException;

public class RR implements Sz<RM> {
   private int compressionThreshold;

   public RR() {
   }

   public RR(int thresholdIn) {
      this.compressionThreshold = thresholdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.compressionThreshold = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.compressionThreshold);
   }

   public void processPacket(RM handler) {
      handler.handleEnableCompression(this);
   }

   public int getCompressionThreshold() {
      return this.compressionThreshold;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RM)var1);
   }
}
