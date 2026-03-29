package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class FE extends Fa {
   public FE(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR_FEET, slots);
      this.setName("waterWalker");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return enchantmentLevel * 10;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   public int getMaxLevel() {
      return 3;
   }

   public boolean canApplyTogether(Fa ench) {
      return super.canApplyTogether(ench) && ench != NJ.FROST_WALKER;
   }
}
