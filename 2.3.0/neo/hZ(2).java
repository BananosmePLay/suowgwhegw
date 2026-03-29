package neo;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class hZ extends hY<Integer> {
   private final ImmutableSet<Integer> allowedValues;

   protected hZ(String name, int min, int max) {
      super(name, Integer.class);
      if (min < 0) {
         throw new IllegalArgumentException("Min value of " + name + " must be 0 or greater");
      } else if (max <= min) {
         throw new IllegalArgumentException("Max value of " + name + " must be greater than min (" + min + ")");
      } else {
         Set<Integer> set = Sets.newHashSet();

         for(int i = min; i <= max; ++i) {
            set.add(i);
         }

         this.allowedValues = ImmutableSet.copyOf(set);
      }
   }

   public Collection<Integer> getAllowedValues() {
      return this.allowedValues;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ instanceof hZ && super.equals(p_equals_1_)) {
         hZ propertyinteger = (hZ)p_equals_1_;
         return this.allowedValues.equals(propertyinteger.allowedValues);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 31 * super.hashCode() + this.allowedValues.hashCode();
   }

   public static hZ create(String name, int min, int max) {
      return new hZ(name, min, max);
   }

   public Optional<Integer> parseValue(String value) {
      try {
         Integer integer = Integer.valueOf(value);
         return this.allowedValues.contains(integer) ? Optional.of(integer) : Optional.absent();
      } catch (NumberFormatException var3) {
         return Optional.absent();
      }
   }

   public String getName(Integer value) {
      return value.toString();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public String getName(Comparable var1) {
      return this.getName((Integer)var1);
   }
}
