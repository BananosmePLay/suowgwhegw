package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

public interface sQ {
   sQ TRUE = new sQ() {
      public Predicate<in> getPredicate(ii blockState) {
         return new Predicate<in>() {
            public boolean apply(@Nullable in p_apply_1_) {
               return true;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((in)var1);
            }
         };
      }
   };
   sQ FALSE = new sQ() {
      public Predicate<in> getPredicate(ii blockState) {
         return new Predicate<in>() {
            public boolean apply(@Nullable in p_apply_1_) {
               return false;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((in)var1);
            }
         };
      }
   };

   Predicate<in> getPredicate(ii var1);
}
