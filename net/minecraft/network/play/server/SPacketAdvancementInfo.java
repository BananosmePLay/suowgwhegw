package net.minecraft.network.play.server;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.ResourceLocation;

public class SPacketAdvancementInfo implements Packet<INetHandlerPlayClient> {
   private boolean firstSync;
   private Map<ResourceLocation, Advancement.Builder> advancementsToAdd;
   private Set<ResourceLocation> advancementsToRemove;
   private Map<ResourceLocation, AdvancementProgress> progressUpdates;

   public SPacketAdvancementInfo() {
   }

   public SPacketAdvancementInfo(boolean p_i47519_1_, Collection<Advancement> p_i47519_2_, Set<ResourceLocation> p_i47519_3_, Map<ResourceLocation, AdvancementProgress> p_i47519_4_) {
      this.firstSync = p_i47519_1_;
      this.advancementsToAdd = Maps.newHashMap();
      Iterator var5 = p_i47519_2_.iterator();

      while(var5.hasNext()) {
         Advancement advancement = (Advancement)var5.next();
         this.advancementsToAdd.put(advancement.getId(), advancement.copy());
      }

      this.advancementsToRemove = p_i47519_3_;
      this.progressUpdates = Maps.newHashMap(p_i47519_4_);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleAdvancementInfo(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.firstSync = buf.readBoolean();
      this.advancementsToAdd = Maps.newHashMap();
      this.advancementsToRemove = Sets.newLinkedHashSet();
      this.progressUpdates = Maps.newHashMap();
      int i = buf.readVarInt();

      int l;
      ResourceLocation resourcelocation2;
      for(l = 0; l < i; ++l) {
         resourcelocation2 = buf.readResourceLocation();
         Advancement.Builder advancement$builder = Advancement.Builder.readFrom(buf);
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
         this.progressUpdates.put(resourcelocation2, AdvancementProgress.fromNetwork(buf));
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBoolean(this.firstSync);
      buf.writeVarInt(this.advancementsToAdd.size());
      Iterator var2 = this.advancementsToAdd.entrySet().iterator();

      Map.Entry entry1;
      while(var2.hasNext()) {
         entry1 = (Map.Entry)var2.next();
         ResourceLocation resourcelocation = (ResourceLocation)entry1.getKey();
         Advancement.Builder advancement$builder = (Advancement.Builder)entry1.getValue();
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
         ((AdvancementProgress)entry1.getValue()).serializeToNetwork(buf);
      }

   }

   public Map<ResourceLocation, Advancement.Builder> getAdvancementsToAdd() {
      return this.advancementsToAdd;
   }

   public Set<ResourceLocation> getAdvancementsToRemove() {
      return this.advancementsToRemove;
   }

   public Map<ResourceLocation, AdvancementProgress> getProgressUpdates() {
      return this.progressUpdates;
   }

   public boolean isFirstSync() {
      return this.firstSync;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
