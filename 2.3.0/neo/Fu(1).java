package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fu extends Fa {
   protected Fu(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.WEAPON, slots);
      this.setName("knockback");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 5 + 20 * (enchantmentLevel - 1);
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 2;
   }
}
