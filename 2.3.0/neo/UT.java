package neo;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class UT implements Sz<Ts> {
   private Map<XQ, Integer> statisticMap;

   public UT() {
   }

   public UT(Map<XQ, Integer> statisticMapIn) {
      this.statisticMap = statisticMapIn;
   }

   public void processPacket(Ts handler) {
      handler.handleStatistics(this);
   }

   public void readPacketData(SA buf) throws IOException {
      int i = buf.readVarInt();
      this.statisticMap = Maps.newHashMap();

      for(int j = 0; j < i; ++j) {
         XQ statbase = XV.getOneShotStat(buf.readString(32767));
         int k = buf.readVarInt();
         if (statbase != null) {
            this.statisticMap.put(statbase, k);
         }
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.statisticMap.size());
      Iterator var2 = this.statisticMap.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<XQ, Integer> entry = (Map.Entry)var2.next();
         buf.writeString(((XQ)entry.getKey()).statId);
         buf.writeVarInt((Integer)entry.getValue());
      }

   }

   public Map<XQ, Integer> getStatisticMap() {
      return this.statisticMap;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
