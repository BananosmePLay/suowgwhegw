package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fx extends Fa {
   public Fx(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR_HEAD, slots);
      this.setName("oxygen");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 10 * enchantmentLevel;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 30;
   }

   public int getMaxLevel() {
      return 3;
   }
}
