package neo;

public class Pk extends OX {
   public Pk(co block) {
      super(block);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int damage) {
      return damage;
   }

   public String getTranslationKey(Qy stack) {
      return super.getTranslationKey() + "." + Om.byMetadata(stack.getMetadata()).getTranslationKey();
   }
}
