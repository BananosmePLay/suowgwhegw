package neo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Sf extends MessageToByteEncoder<Sz<?>> {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Marker RECEIVED_PACKET_MARKER;
   private final RC direction;

   public Sf(RC direction) {
      this.direction = direction;
   }

   protected void encode(ChannelHandlerContext p_encode_1_, Sz<?> p_encode_2_, ByteBuf p_encode_3_) throws IOException, Exception {
      RB enumconnectionstate = (RB)p_encode_1_.channel().attr(Sp.PROTOCOL_ATTRIBUTE_KEY).get();
      if (enumconnectionstate == null) {
         throw new RuntimeException("ConnectionProtocol unknown: " + p_encode_2_.toString());
      } else {
         Integer integer = enumconnectionstate.getPacketId(this.direction, p_encode_2_);
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(RECEIVED_PACKET_MARKER, "OUT: [{}:{}] {}", p_encode_1_.channel().attr(Sp.PROTOCOL_ATTRIBUTE_KEY).get(), integer, p_encode_2_.getClass().getName());
         }

         if (integer == null) {
            throw new IOException("Can't serialize unregistered packet");
         } else {
            SA packetbuffer = new SA(p_encode_3_);
            packetbuffer.writeVarInt(integer);

            try {
               p_encode_2_.writePacketData(packetbuffer);
            } catch (Throwable var8) {
               Throwable throwable = var8;
               LOGGER.error(throwable);
            }

         }
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void encode(ChannelHandlerContext var1, Object var2, ByteBuf var3) throws Exception {
      this.encode(var1, (Sz)var2, var3);
   }

   static {
      RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_SENT", Sp.NETWORK_PACKETS_MARKER);
   }
}
