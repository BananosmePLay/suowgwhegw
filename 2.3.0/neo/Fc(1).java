package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fc extends Fa {
   public Fc(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.BOW, slots);
      this.setName("arrowFire");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 20;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return 50;
   }

   public int getMaxLevel() {
      return 1;
   }
}
