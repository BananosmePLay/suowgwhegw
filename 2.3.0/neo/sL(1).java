package neo;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.List;
import javax.annotation.Nullable;

public class sL implements sQ {
   private static final Splitter SPLITTER = Splitter.on('|').omitEmptyStrings();
   private final String key;
   private final String value;

   public sL(String keyIn, String valueIn) {
      this.key = keyIn;
      this.value = valueIn;
   }

   public Predicate<in> getPredicate(ii blockState) {
      final hT<?> iproperty = blockState.getProperty(this.key);
      if (iproperty == null) {
         throw new RuntimeException(this.toString() + ": Definition: " + blockState + " has no property: " + this.key);
      } else {
         String s = this.value;
         boolean flag = !s.isEmpty() && s.charAt(0) == '!';
         if (flag) {
            s = s.substring(1);
         }

         List<String> list = SPLITTER.splitToList(s);
         if (list.isEmpty()) {
            throw new RuntimeException(this.toString() + ": has an empty value: " + this.value);
         } else {
            Predicate predicate;
            if (list.size() == 1) {
               predicate = this.makePredicate(iproperty, s);
            } else {
               predicate = Predicates.or(Iterables.transform(list, new Function<String, Predicate<in>>() {
                  @Nullable
                  public Predicate<in> apply(@Nullable String p_apply_1_) {
                     return sL.this.makePredicate(iproperty, p_apply_1_);
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  @Nullable
                  public Object apply(@Nullable Object var1) {
                     return this.apply((String)var1);
                  }
               }));
            }

            return flag ? Predicates.not(predicate) : predicate;
         }
      }
   }

   private Predicate<in> makePredicate(final hT<?> property, String valueIn) {
      final Optional<?> optional = property.parseValue(valueIn);
      if (!optional.isPresent()) {
         throw new RuntimeException(this.toString() + ": has an unknown value: " + this.value);
      } else {
         return new Predicate<in>() {
            public boolean apply(@Nullable in p_apply_1_) {
               return p_apply_1_ != null && p_apply_1_.getValue(property).equals(optional.get());
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((in)var1);
            }
         };
      }
   }

   public String toString() {
      return MoreObjects.toStringHelper(this).add("key", this.key).add("value", this.value).toString();
   }
}
