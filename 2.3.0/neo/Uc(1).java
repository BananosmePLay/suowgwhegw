package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Uc implements Sz<Ts> {
   private int entityId;
   private final List<Ub> snapshots = Lists.newArrayList();

   public Uc() {
   }

   public Uc(int entityIdIn, Collection<FZ> instances) {
      this.entityId = entityIdIn;
      Iterator var3 = instances.iterator();

      while(var3.hasNext()) {
         FZ iattributeinstance = (FZ)var3.next();
         this.snapshots.add(new Ub(this, iattributeinstance.getAttribute().getName(), iattributeinstance.getBaseValue(), iattributeinstance.getModifiers()));
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      int i = buf.readInt();

      for(int j = 0; j < i; ++j) {
         String s = buf.readString(64);
         double d0 = buf.readDouble();
         List<FW> list = Lists.newArrayList();
         int k = buf.readVarInt();

         for(int l = 0; l < k; ++l) {
            UUID uuid = buf.readUniqueId();
            list.add(new FW(uuid, "Unknown synced attribute modifier", buf.readDouble(), buf.readByte()));
         }

         this.snapshots.add(new Ub(this, s, d0, list));
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeInt(this.snapshots.size());
      Iterator var2 = this.snapshots.iterator();

      while(var2.hasNext()) {
         Ub spacketentityproperties$snapshot = (Ub)var2.next();
         buf.writeString(spacketentityproperties$snapshot.getName());
         buf.writeDouble(spacketentityproperties$snapshot.getBaseValue());
         buf.writeVarInt(spacketentityproperties$snapshot.getModifiers().size());
         Iterator var4 = spacketentityproperties$snapshot.getModifiers().iterator();

         while(var4.hasNext()) {
            FW attributemodifier = (FW)var4.next();
            buf.writeUniqueId(attributemodifier.getID());
            buf.writeDouble(attributemodifier.getAmount());
            buf.writeByte(attributemodifier.getOperation());
         }
      }

   }

   public void processPacket(Ts handler) {
      handler.handleEntityProperties(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public List<Ub> getSnapshots() {
      return this.snapshots;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
