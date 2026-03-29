package neo;

import java.io.File;
import java.io.IOException;

public abstract class 0bi {
   public final String name;
   public final File file;
   public final boolean load;

   private boolean loadOnStart() {
      return E1qJaV6TBy(this);
   }

   public abstract void loadFile() throws IOException;

   private static boolean E1qJaV6TBy(0bi var0) {
      return var0.load;
   }

   public final String getName() {
      return sTPMBBbosS(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String cYzlaC14j1(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7558 ^ -11906 ^ 19867 ^ -32413; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29808 ^ -27945 ^ 1437 ^ -7736));
      }

      return var1.toString();
   }

   // $FF: synthetic method
   static boolean access$000(0bi x0) {
      return x0.loadOnStart();
   }

   public _bi/* $FF was: 0bi*/(String name, boolean loadOnStart) {
      this.name = name;
      this.load = loadOnStart;
      this.file = new File(0bj.directory, name + cYzlaC14j1("˜ʘʁʝʜ"));
      if (!this.file.exists()) {
         try {
            this.saveFile();
         } catch (Exception var4) {
            Exception e = var4;
            e.printStackTrace();
         }
      }

   }

   private static String sTPMBBbosS(0bi var0) {
      return var0.name;
   }

   private static File cFJcGgNlkA(0bi var0) {
      return var0.file;
   }

   public abstract void saveFile() throws IOException;

   public final File getFile() {
      return cFJcGgNlkA(this);
   }
}
