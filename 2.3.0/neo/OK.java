package neo;

public enum OK {
   WOOD(0, 59, 2.0F, 0.0F, 15),
   STONE(1, 131, 4.0F, 1.0F, 5),
   IRON(2, 250, 6.0F, 2.0F, 14),
   DIAMOND(3, 1561, 8.0F, 3.0F, 10),
   GOLD(0, 32, 12.0F, 0.0F, 22);

   private final int harvestLevel;
   private final int maxUses;
   private final float efficiency;
   private final float attackDamage;
   private final int enchantability;

   private OK(int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability) {
      this.harvestLevel = harvestLevel;
      this.maxUses = maxUses;
      this.efficiency = efficiency;
      this.attackDamage = damageVsEntity;
      this.enchantability = enchantability;
   }

   public int getMaxUses() {
      return this.maxUses;
   }

   public float getEfficiency() {
      return this.efficiency;
   }

   public float getAttackDamage() {
      return this.attackDamage;
   }

   public int getHarvestLevel() {
      return this.harvestLevel;
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public OL getRepairItem() {
      if (this == WOOD) {
         return OL.getItemFromBlock(Nk.PLANKS);
      } else if (this == STONE) {
         return OL.getItemFromBlock(Nk.COBBLESTONE);
      } else if (this == GOLD) {
         return NK.GOLD_INGOT;
      } else if (this == IRON) {
         return NK.IRON_INGOT;
      } else {
         return this == DIAMOND ? NK.DIAMOND : null;
      }
   }
}
