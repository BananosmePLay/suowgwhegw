package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fi extends Fa {
   protected Fi(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.DIGGER, slots);
      this.setName("digging");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 1 + 10 * (enchantmentLevel - 1);
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 5;
   }

   public boolean canApply(Qy stack) {
      return stack.getItem() == NK.SHEARS ? true : super.canApply(stack);
   }
}
