package neo;

public interface biF<V> {
   String getName();

   boolean isValid(V var1);

   Class<V> getType();

   String valueToString(V var1);
}
