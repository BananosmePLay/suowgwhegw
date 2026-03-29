package neo;

import java.util.Random;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;

public class FB extends Fa {
   public FB(EZ rarityIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR_CHEST, slots);
      this.setName("thorns");
   }

   public int getMinEnchantability(int enchantmentLevel) {
      return 10 + 20 * (enchantmentLevel - 1);
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 50;
   }

   public int getMaxLevel() {
      return 3;
   }

   public boolean canApply(Qy stack) {
      return stack.getItem() instanceof OR ? true : super.canApply(stack);
   }

   public void onUserHurt(Iw user, Ig attacker, int level) {
      Random random = user.getRNG();
      Qy itemstack = Ft.getEnchantedItem(NJ.THORNS, user);
      if (shouldHit(level, random)) {
         if (attacker != null) {
            attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float)getDamage(level, random));
         }

         if (!itemstack.isEmpty()) {
            itemstack.damageItem(3, user);
         }
      } else if (!itemstack.isEmpty()) {
         itemstack.damageItem(1, user);
      }

   }

   public static boolean shouldHit(int level, Random rnd) {
      if (level <= 0) {
         return false;
      } else {
         return rnd.nextFloat() < 0.15F * (float)level;
      }
   }

   public static int getDamage(int level, Random rnd) {
      return level > 10 ? level - 10 : 1 + rnd.nextInt(4);
   }
}
