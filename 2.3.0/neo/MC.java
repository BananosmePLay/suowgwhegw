package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

class MC implements Predicate<Kl> {
   private final ME player;

   private MC(ME playerIn) {
      this.player = playerIn;
   }

   public boolean apply(@Nullable Kl p_apply_1_) {
      return p_apply_1_.isPreventingPlayerRest(this.player);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((Kl)var1);
   }

   // $FF: synthetic method
   MC(ME x0, Object x1) {
      this(x0);
   }
}
