package neo;

public class PQ extends OX {
   private final ew leaves;

   public PQ(ew block) {
      super(block);
      this.leaves = block;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int damage) {
      return damage | 4;
   }

   public String getTranslationKey(Qy stack) {
      return super.getTranslationKey() + "." + this.leaves.getWoodType(stack.getMetadata()).getTranslationKey();
   }
}
