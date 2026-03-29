package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class FC extends Fa {
   protected FC(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.DIGGER, slots);
      this.setName("untouching");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 15;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 1;
   }

   public boolean canApplyTogether(Fa ench) {
      return super.canApplyTogether(ench) && ench != NJ.FORTUNE;
   }
}
