package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

public interface Lo extends Mx {
   Predicate<Ig> MOB_SELECTOR = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof Lo;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
   Predicate<Ig> VISIBLE_MOB_SELECTOR = new Predicate<Ig>() {
      public boolean apply(@Nullable Ig p_apply_1_) {
         return p_apply_1_ instanceof Lo && !p_apply_1_.isInvisible();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Ig)var1);
      }
   };
}
