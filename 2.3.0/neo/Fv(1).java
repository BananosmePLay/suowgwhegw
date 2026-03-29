package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fv extends Fa {
   protected Fv(EZ rarityIn, FS typeIn, EntityEquipmentSlot... slots) {
      super(rarityIn, typeIn, slots);
      if (typeIn == FS.DIGGER) {
         this.setName("lootBonusDigger");
      } else if (typeIn == FS.FISHING_ROD) {
         this.setName("lootBonusFishing");
      } else {
         this.setName("lootBonus");
      }

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

   public boolean canApplyTogether(Fa ench) {
      return super.canApplyTogether(ench) && ench != NJ.SILK_TOUCH;
   }
}
