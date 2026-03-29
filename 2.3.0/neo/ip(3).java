package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

public class ip implements Predicate<in> {
   private final co block;

   private ip(co blockType) {
      this.block = blockType;
   }

   public static ip forBlock(co blockType) {
      return new ip(blockType);
   }

   public boolean apply(@Nullable in p_apply_1_) {
      return p_apply_1_ != null && p_apply_1_.getBlock() == this.block;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((in)var1);
   }
}
