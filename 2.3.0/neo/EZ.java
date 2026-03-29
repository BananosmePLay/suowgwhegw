package neo;

public enum EZ {
   COMMON(10),
   UNCOMMON(5),
   RARE(2),
   VERY_RARE(1);

   private final int weight;

   private EZ(int rarityWeight) {
      this.weight = rarityWeight;
   }

   public int getWeight() {
      return this.weight;
   }
}
