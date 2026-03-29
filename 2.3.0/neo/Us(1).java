package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;

public class Us implements Sz<Ts> {
   private ITextComponent header;
   private ITextComponent footer;

   public Us() {
   }

   public void readPacketData(SA buf) throws IOException {
      this.header = buf.readTextComponent();
      this.footer = buf.readTextComponent();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeTextComponent(this.header);
      buf.writeTextComponent(this.footer);
   }

   public void processPacket(Ts handler) {
      handler.handlePlayerListHeaderFooter(this);
   }

   public ITextComponent getHeader() {
      return this.header;
   }

   public ITextComponent getFooter() {
      return this.footer;
   }

   public String toString() {
      return "SPacketPlayerListHeaderFooter{header=" + this.header + ", footer=" + this.footer + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
