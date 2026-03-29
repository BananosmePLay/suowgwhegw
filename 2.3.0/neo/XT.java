package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.TupleIntJsonSerializable;

public class XT {
   protected final Map<XQ, TupleIntJsonSerializable> statsData = Maps.newConcurrentMap();

   public XT() {
   }

   public void increaseStat(ME player, XQ stat, int amount) {
      this.unlockAchievement(player, stat, this.readStat(stat) + amount);
   }

   public void unlockAchievement(ME playerIn, XQ statIn, int p_150873_3_) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.statsData.get(statIn);
      if (tupleintjsonserializable == null) {
         tupleintjsonserializable = new TupleIntJsonSerializable();
         this.statsData.put(statIn, tupleintjsonserializable);
      }

      tupleintjsonserializable.setIntegerValue(p_150873_3_);
   }

   public int readStat(XQ stat) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.statsData.get(stat);
      return tupleintjsonserializable == null ? 0 : tupleintjsonserializable.getIntegerValue();
   }
}
