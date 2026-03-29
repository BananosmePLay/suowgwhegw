package neo;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgW extends bgI {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bhC metaRange;

   public bgW(bgv[] conditionsIn, bhC metaRangeIn) {
      super(conditionsIn);
      this.metaRange = metaRangeIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      if (stack.isItemStackDamageable()) {
         LOGGER.warn("Couldn't set data of loot item {}", stack);
      } else {
         stack.setItemDamage(this.metaRange.generateInt(rand));
      }

      return stack;
   }

   // $FF: synthetic method
   static bhC access$000(bgW x0) {
      return x0.metaRange;
   }
}
