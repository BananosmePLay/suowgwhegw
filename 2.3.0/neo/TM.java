package neo;

import io.netty.buffer.ByteBuf;
import java.io.IOException;

public class TM implements Sz<Ts> {
   private String channel;
   private SA data;

   public TM() {
   }

   public TM(String channelIn, SA bufIn) {
      this.channel = channelIn;
      this.data = bufIn;
      if (bufIn.writerIndex() > 1048576) {
         throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
      }
   }

   public void readPacketData(SA buf) throws IOException {
      this.channel = buf.readString(20);
      int i = buf.readableBytes();
      if (i >= 0 && i <= 1048576) {
         this.data = new SA(buf.readBytes(i));
      } else {
         throw new IOException("Payload may not be larger than 1048576 bytes");
      }
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.channel);
      buf.writeBytes((ByteBuf)this.data);
   }

   public void processPacket(Ts handler) {
      handler.handleCustomPayload(this);
   }

   public String getChannelName() {
      return this.channel;
   }

   public SA getBufferData() {
      return this.data;
   }

   public String toString() {
      return "SPacketCustomPayload{channel='" + this.channel + '\'' + ", data=" + this.data + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
