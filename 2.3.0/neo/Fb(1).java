package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fb extends Fa {
   public Fb(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.BOW, slots);
      this.setName("arrowDamage");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 1 + (enchantmentLevel - 1) * 10;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   public int getMaxLevel() {
      return 5;
   }
}
