package neo;

public class Pm extends OX {
   private String[] subtypeNames;

   public Pm(co block, boolean hasSubtypes) {
      super(block);
      if (hasSubtypes) {
         this.setMaxDamage(0);
         this.setHasSubtypes(true);
      }

   }

   public int getMetadata(int damage) {
      return damage;
   }

   public Pm setSubtypeNames(String[] names) {
      this.subtypeNames = names;
      return this;
   }

   public String getTranslationKey(Qy stack) {
      if (this.subtypeNames == null) {
         return super.getTranslationKey(stack);
      } else {
         int i = stack.getMetadata();
         return i >= 0 && i < this.subtypeNames.length ? super.getTranslationKey(stack) + "." + this.subtypeNames[i] : super.getTranslationKey(stack);
      }
   }
}
