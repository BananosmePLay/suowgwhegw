package neo;

import java.io.InputStream;

public interface bpa {
   String getName();

   InputStream getResourceAsStream(String var1);

   boolean hasDirectory(String var1);

   void close();
}
