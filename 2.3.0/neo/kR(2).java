package neo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class kR extends kV {
   private final Predicate<String> filter;

   public kR(int p_i45534_1_, String p_i45534_2_, boolean p_i45534_3_, Predicate<String> p_i45534_4_) {
      super(p_i45534_1_, p_i45534_2_, p_i45534_3_);
      this.filter = (Predicate)MoreObjects.firstNonNull(p_i45534_4_, Predicates.alwaysTrue());
   }

   public Predicate<String> getFilter() {
      return this.filter;
   }
}
