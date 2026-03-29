package neo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.SoundEvent;

public enum OQ {
   LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, NO.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
   CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, NO.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
   IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, NO.ITEM_ARMOR_EQUIP_IRON, 0.0F),
   GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, NO.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
   DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, NO.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

   private final String name;
   private final int maxDamageFactor;
   private final int[] damageReductionAmountArray;
   private final int enchantability;
   private final SoundEvent soundEvent;
   private final float toughness;

   private OQ(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn) {
      this.name = nameIn;
      this.maxDamageFactor = maxDamageFactorIn;
      this.damageReductionAmountArray = damageReductionAmountArrayIn;
      this.enchantability = enchantabilityIn;
      this.soundEvent = soundEventIn;
      this.toughness = toughnessIn;
   }

   public int getDurability(EntityEquipmentSlot armorType) {
      return OR.access$000()[armorType.getIndex()] * this.maxDamageFactor;
   }

   public int getDamageReductionAmount(EntityEquipmentSlot armorType) {
      return this.damageReductionAmountArray[armorType.getIndex()];
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public SoundEvent getSoundEvent() {
      return this.soundEvent;
   }

   public OL getRepairItem() {
      if (this == LEATHER) {
         return NK.LEATHER;
      } else if (this == CHAIN) {
         return NK.IRON_INGOT;
      } else if (this == GOLD) {
         return NK.GOLD_INGOT;
      } else if (this == IRON) {
         return NK.IRON_INGOT;
      } else {
         return this == DIAMOND ? NK.DIAMOND : null;
      }
   }

   public String getName() {
      return this.name;
   }

   public float getToughness() {
      return this.toughness;
   }
}
