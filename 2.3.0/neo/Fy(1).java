package neo;

public enum Fy {
   ALL("all", 1, 11, 20),
   FIRE("fire", 10, 8, 12),
   FALL("fall", 5, 6, 10),
   EXPLOSION("explosion", 5, 8, 12),
   PROJECTILE("projectile", 3, 6, 15);

   private final String typeName;
   private final int minEnchantability;
   private final int levelCost;
   private final int levelCostSpan;

   private Fy(String name, int minimal, int perLevelEnchantability, int p_i47051_6_) {
      this.typeName = name;
      this.minEnchantability = minimal;
      this.levelCost = perLevelEnchantability;
      this.levelCostSpan = p_i47051_6_;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public int getMinimalEnchantability() {
      return this.minEnchantability;
   }

   public int getEnchantIncreasePerLevel() {
      return this.levelCost;
   }
}
