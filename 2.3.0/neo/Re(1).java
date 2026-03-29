package neo;

import java.io.IOException;

public interface Re<T> {
   void write(SA var1, T var2);

   T read(SA var1) throws IOException;

   Rd<T> createKey(int var1);

   T copyValue(T var1);
}
