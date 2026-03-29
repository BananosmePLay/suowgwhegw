package neo;

import java.io.IOException;
import net.minecraft.util.EnumHand;

public class Tc implements Sz<Tt> {
   private EnumHand hand;

   public Tc() {
   }

   public Tc(EnumHand handIn) {
      this.hand = handIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.hand = (EnumHand)buf.readEnumValue(EnumHand.class);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.hand);
   }

   public void processPacket(Tt handler) {
      handler.processTryUseItem(this);
   }

   public EnumHand getHand() {
      return this.hand;
   }

   public String toString() {
      return "CPacketPlayerTryUseItem{hand=" + this.hand + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
