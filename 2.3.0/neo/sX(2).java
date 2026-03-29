package neo;

import com.google.common.base.Predicate;

public class sX {
   private final sQ condition;
   private final tg variantList;

   public sX(sQ conditionIn, tg variantListIn) {
      if (conditionIn == null) {
         throw new IllegalArgumentException("Missing condition for selector");
      } else if (variantListIn == null) {
         throw new IllegalArgumentException("Missing variant for selector");
      } else {
         this.condition = conditionIn;
         this.variantList = variantListIn;
      }
   }

   public tg getVariantList() {
      return this.variantList;
   }

   public Predicate<in> getPredicate(ii state) {
      return this.condition.getPredicate(state);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         if (p_equals_1_ instanceof sX) {
            sX selector = (sX)p_equals_1_;
            if (this.condition.equals(selector.condition)) {
               return this.variantList.equals(selector.variantList);
            }
         }

         return false;
      }
   }

   public int hashCode() {
      return 31 * this.condition.hashCode() + this.variantList.hashCode();
   }
}
