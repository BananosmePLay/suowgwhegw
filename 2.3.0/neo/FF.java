package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class FF extends Fa {
   public FF(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR_HEAD, slots);
      this.setName("waterWorker");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 1;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 40;
   }

   public int getMaxLevel() {
      return 1;
   }
}
