package neo;

import java.io.IOException;
import javax.annotation.Nullable;

public class TW implements Sz<Ts> {
   private int entityId;
   private int vehicleEntityId;

   public TW() {
   }

   public TW(Ig entityIn, @Nullable Ig vehicleIn) {
      this.entityId = entityIn.getEntityId();
      this.vehicleEntityId = vehicleIn != null ? vehicleIn.getEntityId() : -1;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readInt();
      this.vehicleEntityId = buf.readInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.entityId);
      buf.writeInt(this.vehicleEntityId);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityAttach(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public int getVehicleEntityId() {
      return this.vehicleEntityId;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
