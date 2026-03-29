package neo;

import net.minecraft.util.WeightedRandom;

public class Fh extends WeightedRandom.Item {
   public final Fa enchantment;
   public final int enchantmentLevel;

   public Fh(Fa enchantmentObj, int enchLevel) {
      super(enchantmentObj.getRarity().getWeight());
      this.enchantment = enchantmentObj;
      this.enchantmentLevel = enchLevel;
   }
}
