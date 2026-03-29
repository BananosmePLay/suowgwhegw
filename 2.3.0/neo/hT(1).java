package neo;

import com.google.common.base.Optional;
import java.util.Collection;

public interface hT<T extends Comparable<T>> {
   String getName();

   Collection<T> getAllowedValues();

   Class<T> getValueClass();

   Optional<T> parseValue(String var1);

   String getName(T var1);
}
