package neo;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bha extends bgI {
   private static final Logger LOGGER = LogManager.getLogger();

   public bha(bgv[] conditionsIn) {
      super(conditionsIn);
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      if (stack.isEmpty()) {
         return stack;
      } else {
         Qy itemstack = NQ.instance().getSmeltingResult(stack);
         if (itemstack.isEmpty()) {
            LOGGER.warn("Couldn't smelt {} because there is no smelting recipe", stack);
            return stack;
         } else {
            Qy itemstack1 = itemstack.copy();
            itemstack1.setCount(stack.getCount());
            return itemstack1;
         }
      }
   }
}
