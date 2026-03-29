package neo;

public class ch<T extends cg> {
   private final T criterionInstance;
   private final b advancement;
   private final String criterionName;

   public ch(T criterionInstanceIn, b advancementIn, String criterionNameIn) {
      this.criterionInstance = criterionInstanceIn;
      this.advancement = advancementIn;
      this.criterionName = criterionNameIn;
   }

   public T getCriterionInstance() {
      return this.criterionInstance;
   }

   public void grantCriterion(cl playerAdvancementsIn) {
      playerAdvancementsIn.grantCriterion(this.advancement, this.criterionName);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         ch<?> listener = (ch)p_equals_1_;
         if (!this.criterionInstance.equals(listener.criterionInstance)) {
            return false;
         } else {
            return !this.advancement.equals(listener.advancement) ? false : this.criterionName.equals(listener.criterionName);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int i = this.criterionInstance.hashCode();
      i = 31 * i + this.advancement.hashCode();
      i = 31 * i + this.criterionName.hashCode();
      return i;
   }
}
