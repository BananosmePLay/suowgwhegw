package neo;

import java.util.Random;
import net.minecraft.util.math.MathHelper;

class Ml implements Mk {
   Ml() {
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      Fa enchantment = (Fa)Fa.REGISTRY.getRandomObject(random);
      int i = MathHelper.getInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
      Qy itemstack = Pv.getEnchantedItemStack(new Fh(enchantment, i));
      int j = 2 + random.nextInt(5 + i * 10) + 3 * i;
      if (enchantment.isTreasureEnchantment()) {
         j *= 2;
      }

      if (j > 64) {
         j = 64;
      }

      recipeList.add(new YW(new Qy(NK.BOOK), new Qy(NK.EMERALD, j), itemstack));
   }
}
