package neo;

import java.io.IOException;

public class TL implements Sz<Ts> {
   private OL item;
   private int ticks;

   public TL() {
   }

   public TL(OL itemIn, int ticksIn) {
      this.item = itemIn;
      this.ticks = ticksIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.item = OL.getItemById(buf.readVarInt());
      this.ticks = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(OL.getIdFromItem(this.item));
      buf.writeVarInt(this.ticks);
   }

   public void processPacket(Ts handler) {
      handler.handleCooldown(this);
   }

   public OL getItem() {
      return this.item;
   }

   public int getTicks() {
      return this.ticks;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
