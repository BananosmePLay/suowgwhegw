package neo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class Sb extends MessageToMessageDecoder<ByteBuf> {
   private final Sd decryptionCodec;

   public Sb(Cipher cipher) {
      this.decryptionCodec = new Sd(cipher);
   }

   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws ShortBufferException, Exception {
      p_decode_3_.add(this.decryptionCodec.decipher(p_decode_1_, p_decode_2_));
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void decode(ChannelHandlerContext var1, Object var2, List var3) throws Exception {
      this.decode(var1, (ByteBuf)var2, var3);
   }
}
