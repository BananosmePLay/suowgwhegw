package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fw extends Fa {
   public Fw(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.BREAKABLE, slots);
      this.setName("mending");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return enchantmentLevel * 25;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 50;
   }

   public boolean isTreasureEnchantment() {
      return true;
   }

   public int getMaxLevel() {
      return 1;
   }
}
