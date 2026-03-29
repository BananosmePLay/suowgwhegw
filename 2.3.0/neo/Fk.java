package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fk extends Fa {
   protected Fk(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.WEAPON, slots);
      this.setName("fire");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 10 + 20 * (enchantmentLevel - 1);
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 2;
   }
}
