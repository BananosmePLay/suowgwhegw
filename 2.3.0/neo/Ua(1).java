package neo;

import java.io.IOException;
import java.util.List;

public class Ua implements Sz<Ts> {
   private int entityId;
   private List<Ru<?>> dataManagerEntries;

   public Ua() {
   }

   public Ua(int entityIdIn, Rv dataManagerIn, boolean sendAll) {
      this.entityId = entityIdIn;
      if (sendAll) {
         this.dataManagerEntries = dataManagerIn.getAll();
         dataManagerIn.setClean();
      } else {
         this.dataManagerEntries = dataManagerIn.getDirty();
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.dataManagerEntries = Rv.readEntries(buf);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      Rv.writeEntries(this.dataManagerEntries, buf);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityMetadata(this);
   }

   public List<Ru<?>> getDataManagerEntries() {
      return this.dataManagerEntries;
   }

   public int getEntityId() {
      return this.entityId;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
