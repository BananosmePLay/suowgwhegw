package neo;

public class Qa extends OX {
   protected final co unused;
   protected final PZ nameFunction;

   public Qa(co p_i47262_1_, co p_i47262_2_, PZ p_i47262_3_) {
      super(p_i47262_1_);
      this.unused = p_i47262_2_;
      this.nameFunction = p_i47262_3_;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public Qa(co block, co block2, final String[] namesByMeta) {
      this(block, block2, new PZ() {
         public String apply(Qy p_apply_1_) {
            int i = p_apply_1_.getMetadata();
            if (i < 0 || i >= namesByMeta.length) {
               i = 0;
            }

            return namesByMeta[i];
         }
      });
   }

   public int getMetadata(int damage) {
      return damage;
   }

   public String getTranslationKey(Qy stack) {
      return super.getTranslationKey() + "." + this.nameFunction.apply(stack);
   }
}
