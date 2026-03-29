package neo;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class Tw implements Sz<Ts> {
   private boolean firstSync;
   private Map<ResourceLocation, a> advancementsToAdd;
   private Set<ResourceLocation> advancementsToRemove;
   private Map<ResourceLocation, h> progressUpdates;

   public Tw() {
   }

   public Tw(boolean p_i47519_1_, Collection<b> p_i47519_2_, Set<ResourceLocation> p_i47519_3_, Map<ResourceLocation, h> p_i47519_4_) {
      this.firstSync = p_i47519_1_;
      this.advancementsToAdd = Maps.newHashMap();
      Iterator var5 = p_i47519_2_.iterator();

      while(var5.hasNext()) {
         b advancement = (b)var5.next();
         this.advancementsToAdd.put(advancement.getId(), advancement.copy());
      }

      this.advancementsToRemove = p_i47519_3_;
      this.progressUpdates = Maps.newHashMap(p_i47519_4_);
   }

   public void processPacket(Ts handler) {
      handler.handleAdvancementInfo(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.firstSync = buf.readBoolean();
      this.advancementsToAdd = Maps.newHashMap();
      this.advancementsToRemove = Sets.newLinkedHashSet();
      this.progressUpdates = Maps.newHashMap();
      int i = buf.readVarInt();

      int l;
      ResourceLocation resourcelocation2;
      for(l = 0; l < i; ++l) {
         resourcelocation2 = buf.readResourceLocation();
         a advancement$builder = a.readFrom(buf);
         this.advancementsToAdd.put(resourcelocation2, advancement$builder);
      }

      i = buf.readVarInt();

      for(l = 0; l < i; ++l) {
         resourcelocation2 = buf.readResourceLocation();
         this.advancementsToRemove.add(resourcelocation2);
      }

      i = buf.readVarInt();

      for(l = 0; l < i; ++l) {
         resourcelocation2 = buf.readResourceLocation();
         this.progressUpdates.put(resourcelocation2, h.fromNetwork(buf));
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBoolean(this.firstSync);
      buf.writeVarInt(this.advancementsToAdd.size());
      Iterator var2 = this.advancementsToAdd.entrySet().iterator();

      Map.Entry entry1;
      while(var2.hasNext()) {
         entry1 = (Map.Entry)var2.next();
         ResourceLocation resourcelocation = (ResourceLocation)entry1.getKey();
         a advancement$builder = (a)entry1.getValue();
         buf.writeResourceLocation(resourcelocation);
         advancement$builder.writeTo(buf);
      }

      buf.writeVarInt(this.advancementsToRemove.size());
      var2 = this.advancementsToRemove.iterator();

      while(var2.hasNext()) {
         ResourceLocation resourcelocation1 = (ResourceLocation)var2.next();
         buf.writeResourceLocation(resourcelocation1);
      }

      buf.writeVarInt(this.progressUpdates.size());
      var2 = this.progressUpdates.entrySet().iterator();

      while(var2.hasNext()) {
         entry1 = (Map.Entry)var2.next();
         buf.writeResourceLocation((ResourceLocation)entry1.getKey());
         ((h)entry1.getValue()).serializeToNetwork(buf);
      }

   }

   public Map<ResourceLocation, a> getAdvancementsToAdd() {
      return this.advancementsToAdd;
   }

   public Set<ResourceLocation> getAdvancementsToRemove() {
      return this.advancementsToRemove;
   }

   public Map<ResourceLocation, h> getProgressUpdates() {
      return this.progressUpdates;
   }

   public boolean isFirstSync() {
      return this.firstSync;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
