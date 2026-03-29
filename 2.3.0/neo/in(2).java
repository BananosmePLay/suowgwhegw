package neo;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;

public interface in extends il, im {
   Collection<hT<?>> getPropertyKeys();

   <T extends Comparable<T>> T getValue(hT<T> var1);

   <T extends Comparable<T>, V extends T> in withProperty(hT<T> var1, V var2);

   <T extends Comparable<T>> in cycleProperty(hT<T> var1);

   ImmutableMap<hT<?>, Comparable<?>> getProperties();

   co getBlock();
}
