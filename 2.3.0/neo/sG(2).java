package neo;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import javax.annotation.Nullable;

public class sG implements sQ {
   private final Iterable<sQ> conditions;

   public sG(Iterable<sQ> conditionsIn) {
      this.conditions = conditionsIn;
   }

   public Predicate<in> getPredicate(final ii blockState) {
      return Predicates.and(Iterables.transform(this.conditions, new Function<sQ, Predicate<in>>() {
         @Nullable
         public Predicate<in> apply(@Nullable sQ p_apply_1_) {
            return p_apply_1_ == null ? null : p_apply_1_.getPredicate(blockState);
         }

         // $FF: synthetic method
         // $FF: bridge method
         @Nullable
         public Object apply(@Nullable Object var1) {
            return this.apply((sQ)var1);
         }
      }));
   }
}
