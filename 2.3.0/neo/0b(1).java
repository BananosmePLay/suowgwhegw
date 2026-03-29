package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

class 0b implements Predicate<Ig> {
   // $FF: synthetic field
   final 0d this$0;
   private static String _ _;

   public boolean apply(@Nullable Ig a) {
      return (boolean)(a != null && a.canBeCollidedWith() ? 9634 ^ -20824 ^ 2633 ^ -32446 : 15912 ^ -5971 ^ 10746 ^ -129);
   }

   _b/* $FF was: 0b*/(0d a) {
      this.this$0 = a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((Ig)var1);
   }
}
