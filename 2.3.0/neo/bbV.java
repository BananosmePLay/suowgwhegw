package neo;

import com.google.common.base.Predicate;

class bbV implements Predicate<in> {
   private bbV() {
   }

   public boolean apply(in p_apply_1_) {
      if (p_apply_1_ != null && p_apply_1_.getBlock() == Nk.STONE) {
         gY blockstone$enumtype = (gY)p_apply_1_.getValue(gZ.VARIANT);
         return blockstone$enumtype.isNatural();
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(Object var1) {
      return this.apply((in)var1);
   }

   // $FF: synthetic method
   bbV(Object x0) {
      this();
   }
}
