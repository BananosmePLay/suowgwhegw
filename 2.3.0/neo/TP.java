package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;

public class TP implements Sz<Ts> {
   private ITextComponent reason;

   public TP() {
   }

   public TP(ITextComponent messageIn) {
      this.reason = messageIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.reason = buf.readTextComponent();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeTextComponent(this.reason);
   }

   public void processPacket(Ts handler) {
      handler.handleDisconnect(this);
   }

   public ITextComponent getReason() {
      return this.reason;
   }

   public String toString() {
      return "SPacketDisconnect{reason=" + this.reason + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
