package neo;

import net.minecraft.network.Packet;

public class 0v extends 0n implements 0p {
   public Packet packet;

   public _v/* $FF was: 0v*/(Packet packet) {
      this.packet = packet;
   }

   private static Packet QDPO3oPvc7(0v var0) {
      return var0.packet;
   }

   public Packet getPacket() {
      return QDPO3oPvc7(this);
   }
}
