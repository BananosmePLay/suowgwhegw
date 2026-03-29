package neo;

import java.io.IOException;
import java.util.Arrays;

public class TO implements Sz<Ts> {
   private int[] entityIDs;

   public TO() {
   }

   public TO(int... entityIdsIn) {
      this.entityIDs = entityIdsIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityIDs = new int[buf.readVarInt()];

      for(int i = 0; i < this.entityIDs.length; ++i) {
         this.entityIDs[i] = buf.readVarInt();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityIDs.length);
      int[] var2 = this.entityIDs;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int i = var2[var4];
         buf.writeVarInt(i);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleDestroyEntities(this);
   }

   public int[] getEntityIDs() {
      return this.entityIDs;
   }

   public String toString() {
      return "SPacketDestroyEntities{entityIDs=" + Arrays.toString(this.entityIDs) + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
