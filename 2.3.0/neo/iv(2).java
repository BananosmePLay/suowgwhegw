package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class iv implements Predicate<in> {
   public static final Predicate<in> ANY = new Predicate<in>() {
      public boolean apply(@Nullable in p_apply_1_) {
         return true;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((in)var1);
      }
   };
   private final ii blockstate;
   private final Map<hT<?>, Predicate<?>> propertyPredicates = Maps.newHashMap();

   private iv(ii blockStateIn) {
      this.blockstate = blockStateIn;
   }

   public static iv forBlock(co blockIn) {
      return new iv(blockIn.getBlockState());
   }

   public boolean apply(@Nullable in p_apply_1_) {
      if (p_apply_1_ != null && p_apply_1_.getBlock().equals(this.blockstate.getBlock())) {
         if (this.propertyPredicates.isEmpty()) {
            return true;
         } else {
            Iterator var2 = this.propertyPredicates.entrySet().iterator();

            Map.Entry entry;
            do {
               if (!var2.hasNext()) {
                  return true;
               }

               entry = (Map.Entry)var2.next();
            } while(this.matches(p_apply_1_, (hT)entry.getKey(), (Predicate)entry.getValue()));

            return false;
         }
      } else {
         return false;
      }
   }

   protected <T extends Comparable<T>> boolean matches(in blockState, hT<T> property, Predicate<T> predicate) {
      return predicate.apply(blockState.getValue(property));
   }

   public <V extends Comparable<V>> iv where(hT<V> property, Predicate<? extends V> is) {
      if (!this.blockstate.getProperties().contains(property)) {
         throw new IllegalArgumentException(this.blockstate + " cannot support property " + property);
      } else {
         this.propertyPredicates.put(property, is);
         return this;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((in)var1);
   }
}
