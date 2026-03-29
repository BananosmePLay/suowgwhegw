package neo;

import io.netty.buffer.ByteBuf;
import java.io.IOException;

public class SN implements Sz<Tt> {
   private String channel;
   private SA data;

   public SN() {
   }

   public SN(String channelIn, SA bufIn) {
      this.channel = channelIn;
      this.data = bufIn;
      if (bufIn.writerIndex() > 32767) {
         throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
      }
   }

   public void readPacketData(SA buf) throws IOException {
      this.channel = buf.readString(20);
      int i = buf.readableBytes();
      if (i >= 0 && i <= 32767) {
         this.data = new SA(buf.readBytes(i));
      } else {
         throw new IOException("Payload may not be larger than 32767 bytes");
      }
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.channel);
      buf.writeBytes((ByteBuf)this.data);
   }

   public void processPacket(Tt handler) {
      handler.processCustomPayload(this);
      if (this.data != null) {
         this.data.release();
      }

   }

   public String getChannelName() {
      return this.channel;
   }

   public SA getBufferData() {
      return this.data;
   }

   public String toString() {
      return "CPacketCustomPayload{channel='" + this.channel + '\'' + ", data=" + this.data.toString() + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
