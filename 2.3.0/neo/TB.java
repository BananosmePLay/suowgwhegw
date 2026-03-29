package neo;

import java.io.IOException;
import javax.annotation.Nullable;

public class TB implements Sz<Ts> {
   public int entityId;

   public TB() {
   }

   public TB(Ig entityIn) {
      this.entityId = entityIn.getEntityId();
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
   }

   public void processPacket(Ts handler) {
      handler.handleCamera(this);
   }

   @Nullable
   public Ig getEntity(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
