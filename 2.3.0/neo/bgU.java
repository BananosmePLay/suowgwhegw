package neo;

import java.util.Random;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgU extends bgI {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bhC damageRange;

   public bgU(bgv[] conditionsIn, bhC damageRangeIn) {
      super(conditionsIn);
      this.damageRange = damageRangeIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      if (stack.isItemStackDamageable()) {
         float f = 1.0F - this.damageRange.generateFloat(rand);
         stack.setItemDamage(MathHelper.floor(f * (float)stack.getMaxDamage()));
      } else {
         LOGGER.warn("Couldn't set damage of loot item {}", stack);
      }

      return stack;
   }

   // $FF: synthetic method
   static bhC access$000(bgU x0) {
      return x0.damageRange;
   }
}
