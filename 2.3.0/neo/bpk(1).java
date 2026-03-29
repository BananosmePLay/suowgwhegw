package neo;

import java.io.InputStream;

public class bpk implements bpa {
   public bpk() {
   }

   public void close() {
   }

   public InputStream getResourceAsStream(String resName) {
      return bpk.class.getResourceAsStream(resName);
   }

   public String getName() {
      return "(internal)";
   }

   public boolean hasDirectory(String name) {
      return false;
   }
}
