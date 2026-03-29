package neo;

import java.io.File;
import java.io.FileNotFoundException;

public class AL extends FileNotFoundException {
   public AL(File resourcePack, String fileName) {
      super(String.format("'%s' in ResourcePack '%s'", fileName, resourcePack));
   }
}
