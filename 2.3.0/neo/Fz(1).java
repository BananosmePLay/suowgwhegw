package neo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class Fz extends Fa {
   public final Fy protectionType;

   public Fz(EZ rarityIn, Fy protectionTypeIn, EntityEquipmentSlot... slots) {
      super(rarityIn, FS.ARMOR, slots);
      this.protectionType = protectionTypeIn;
      if (protectionTypeIn == Fy.FALL) {
         this.type = FS.ARMOR_FEET;
      }

   }

   public int getMinEnchantability(int enchantmentLevel) {
      return this.protectionType.getMinimalEnchantability() + (enchantmentLevel - 1) * this.protectionType.getEnchantIncreasePerLevel();
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + this.protectionType.getEnchantIncreasePerLevel();
   }

   public int getMaxLevel() {
      return 4;
   }

   public int calcModifierDamage(int level, DamageSource source) {
      if (source.canHarmInCreative()) {
         return 0;
      } else if (this.protectionType == Fy.ALL) {
         return level;
      } else if (this.protectionType == Fy.FIRE && source.isFireDamage()) {
         return level * 2;
      } else if (this.protectionType == Fy.FALL && source == DamageSource.FALL) {
         return level * 3;
      } else if (this.protectionType == Fy.EXPLOSION && source.isExplosion()) {
         return level * 2;
      } else {
         return this.protectionType == Fy.PROJECTILE && source.isProjectile() ? level * 2 : 0;
      }
   }

   public String getName() {
      return "enchantment.protect." + this.protectionType.getTypeName();
   }

   public boolean canApplyTogether(Fa ench) {
      if (ench instanceof Fz) {
         Fz enchantmentprotection = (Fz)ench;
         if (this.protectionType == enchantmentprotection.protectionType) {
            return false;
         } else {
            return this.protectionType == Fy.FALL || enchantmentprotection.protectionType == Fy.FALL;
         }
      } else {
         return super.canApplyTogether(ench);
      }
   }

   public static int getFireTimeForEntity(Iw p_92093_0_, int p_92093_1_) {
      int i = Ft.getMaxEnchantmentLevel(NJ.FIRE_PROTECTION, p_92093_0_);
      if (i > 0) {
         p_92093_1_ -= MathHelper.floor((float)p_92093_1_ * (float)i * 0.15F);
      }

      return p_92093_1_;
   }

   public static double getBlastDamageReduction(Iw entityLivingBaseIn, double damage) {
      int i = Ft.getMaxEnchantmentLevel(NJ.BLAST_PROTECTION, entityLivingBaseIn);
      if (i > 0) {
         damage -= (double)MathHelper.floor(damage * (double)((float)i * 0.15F));
      }

      return damage;
   }
}
