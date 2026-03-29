package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fl extends Fa {
   protected Fl(EZ rarityIn, FS typeIn, EntityEquipmentSlot... slots) {
      super(rarityIn, typeIn, slots);
      this.setName("fishingSpeed");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 15 + (enchantmentLevel - 1) * 9;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 3;
   }
}
