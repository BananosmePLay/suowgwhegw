package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgE extends bgI {
   private static final Logger LOGGER = LogManager.getLogger();
   private final List<Fa> enchantments;

   public bgE(bgv[] conditionsIn, @Nullable List<Fa> enchantmentsIn) {
      super(conditionsIn);
      this.enchantments = enchantmentsIn == null ? Collections.emptyList() : enchantmentsIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      Fa enchantment;
      if (this.enchantments.isEmpty()) {
         List<Fa> list = Lists.newArrayList();
         Iterator var6 = Fa.REGISTRY.iterator();

         label32:
         while(true) {
            Fa enchantment1;
            do {
               if (!var6.hasNext()) {
                  if (list.isEmpty()) {
                     LOGGER.warn("Couldn't find a compatible enchantment for {}", stack);
                     return stack;
                  }

                  enchantment = (Fa)list.get(rand.nextInt(list.size()));
                  break label32;
               }

               enchantment1 = (Fa)var6.next();
            } while(stack.getItem() != NK.BOOK && !enchantment1.canApply(stack));

            list.add(enchantment1);
         }
      } else {
         enchantment = (Fa)this.enchantments.get(rand.nextInt(this.enchantments.size()));
      }

      int i = MathHelper.getInt(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
      if (stack.getItem() == NK.BOOK) {
         stack = new Qy(NK.ENCHANTED_BOOK);
         Pv.addEnchantment(stack, new Fh(enchantment, i));
      } else {
         stack.addEnchantment(enchantment, i);
      }

      return stack;
   }

   // $FF: synthetic method
   static List access$000(bgE x0) {
      return x0.enchantments;
   }
}
