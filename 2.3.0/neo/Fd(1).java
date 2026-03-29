package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Fd extends Fa {
   public Fd(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.BOW, slots);
      this.setName("arrowInfinite");
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

   public boolean canApplyTogether(Fa ench) {
      return ench instanceof Fw ? false : super.canApplyTogether(ench);
   }
}
