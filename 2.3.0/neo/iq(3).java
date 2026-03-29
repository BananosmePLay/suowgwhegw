package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

public class iq implements Predicate<in> {
   private final hM material;

   private iq(hM materialIn) {
      this.material = materialIn;
   }

   public static iq forMaterial(hM materialIn) {
      return new iq(materialIn);
   }

   public boolean apply(@Nullable in p_apply_1_) {
      return p_apply_1_ != null && p_apply_1_.getMaterial() == this.material;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((in)var1);
   }
}
