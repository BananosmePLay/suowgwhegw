package net.minecraft.network.play.server;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class SPacketStatistics implements Packet<INetHandlerPlayClient> {
   private Map<StatBase, Integer> statisticMap;

   public SPacketStatistics() {
   }

   public SPacketStatistics(Map<StatBase, Integer> statisticMapIn) {
      this.statisticMap = statisticMapIn;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleStatistics(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      int i = buf.readVarInt();
      this.statisticMap = Maps.newHashMap();

      for(int j = 0; j < i; ++j) {
         StatBase statbase = StatList.getOneShotStat(buf.readString(32767));
         int k = buf.readVarInt();
         if (statbase != null) {
            this.statisticMap.put(statbase, k);
         }
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.statisticMap.size());
      Iterator var2 = this.statisticMap.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<StatBase, Integer> entry = (Map.Entry)var2.next();
         buf.writeString(((StatBase)entry.getKey()).statId);
         buf.writeVarInt((Integer)entry.getValue());
      }

   }

   public Map<StatBase, Integer> getStatisticMap() {
      return this.statisticMap;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
