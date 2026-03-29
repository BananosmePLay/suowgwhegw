package neo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class 0en {
   public static String readUsingFiles(File file) {
      try {
         return new String(Files.readAllBytes(file.toPath()));
      } catch (IOException var2) {
         IOException e = var2;
         e.printStackTrace();
         return null;
      }
   }

   public _en/* $FF was: 0en*/() {
   }
}
