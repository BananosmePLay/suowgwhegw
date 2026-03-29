package neo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class bpl implements bpa {
   protected File packFile;

   public bpl(String name, File file) {
      this.packFile = file;
   }

   public void close() {
   }

   public InputStream getResourceAsStream(String resName) {
      try {
         String s = bqP.removePrefixSuffix(resName, "/", "/");
         File file1 = new File(this.packFile, s);
         return !file1.exists() ? null : new BufferedInputStream(new FileInputStream(file1));
      } catch (Exception var4) {
         return null;
      }
   }

   public boolean hasDirectory(String name) {
      File file1 = new File(this.packFile, name.substring(1));
      return !file1.exists() ? false : file1.isDirectory();
   }

   public String getName() {
      return this.packFile.getName();
   }
}
