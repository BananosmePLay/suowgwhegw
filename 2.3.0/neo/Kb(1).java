package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

class Kb implements Predicate<Iw> {
   private final Kc parentEntity;

   public Kb(Kc guardian) {
      this.parentEntity = guardian;
   }

   public boolean apply(@Nullable Iw p_apply_1_) {
      return (p_apply_1_ instanceof ME || p_apply_1_ instanceof Mf) && p_apply_1_.getDistanceSq(this.parentEntity) > 9.0;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((Iw)var1);
   }
}
