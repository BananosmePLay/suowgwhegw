package neo;

import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;

public class Tk implements Sz<Tt> {
   private UUID id;

   public Tk() {
   }

   public Tk(UUID uniqueIdIn) {
      this.id = uniqueIdIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.id = buf.readUniqueId();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeUniqueId(this.id);
   }

   public void processPacket(Tt handler) {
      handler.handleSpectate(this);
   }

   @Nullable
   public Ig getEntity(bis worldIn) {
      return worldIn.getEntityFromUuid(this.id);
   }

   public String toString() {
      return "CPacketSpectate{id=" + this.id + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
