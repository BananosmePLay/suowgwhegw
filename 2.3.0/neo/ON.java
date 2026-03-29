package neo;

public class ON extends Qa {
   public ON(co block) {
      super(block, block, new String[]{"intact", "slightlyDamaged", "veryDamaged"});
   }

   public int getMetadata(int damage) {
      return damage << 2;
   }
}
