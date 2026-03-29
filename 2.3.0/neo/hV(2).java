package neo;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class hV extends hY<Boolean> {
   private final ImmutableSet<Boolean> allowedValues = ImmutableSet.of(true, false);

   protected hV(String name) {
      super(name, Boolean.class);
   }

   public Collection<Boolean> getAllowedValues() {
      return this.allowedValues;
   }

   public static hV create(String name) {
      return new hV(name);
   }

   public Optional<Boolean> parseValue(String value) {
      return !"true".equals(value) && !"false".equals(value) ? Optional.absent() : Optional.of(Boolean.valueOf(value));
   }

   public String getName(Boolean value) {
      return value.toString();
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ instanceof hV && super.equals(p_equals_1_)) {
         hV propertybool = (hV)p_equals_1_;
         return this.allowedValues.equals(propertybool.allowedValues);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 31 * super.hashCode() + this.allowedValues.hashCode();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public String getName(Comparable var1) {
      return this.getName((Boolean)var1);
   }
}
