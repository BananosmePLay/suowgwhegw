package neo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UI implements Sz<Ts> {
   private int entityId;
   private int[] passengerIds;

   public UI() {
   }

   public UI(Ig entityIn) {
      this.entityId = entityIn.getEntityId();
      List<Ig> list = entityIn.getPassengers();
      this.passengerIds = new int[list.size()];

      for(int i = 0; i < list.size(); ++i) {
         this.passengerIds[i] = ((Ig)list.get(i)).getEntityId();
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.passengerIds = buf.readVarIntArray();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeVarIntArray(this.passengerIds);
   }

   public void processPacket(Ts handler) {
      handler.handleSetPassengers(this);
   }

   public int[] getPassengerIds() {
      return this.passengerIds;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public String toString() {
      return "SPacketSetPassengers{entityId=" + this.entityId + ", passengerIds=" + Arrays.toString(this.passengerIds) + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
