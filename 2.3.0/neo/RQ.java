package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;

public class RQ implements Sz<RM> {
   private ITextComponent reason;

   public RQ() {
   }

   public RQ(ITextComponent p_i46853_1_) {
      this.reason = p_i46853_1_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.reason = ITextComponent.Serializer.fromJsonLenient(buf.readString(32767));
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeTextComponent(this.reason);
   }

   public void processPacket(RM handler) {
      handler.handleDisconnect(this);
   }

   public ITextComponent getReason() {
      return this.reason;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RM)var1);
   }
}
