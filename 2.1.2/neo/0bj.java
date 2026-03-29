package neo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;

public class 0bj {
   public static final File directory;
   public static ArrayList<0bi> files;

   private static ArrayList I82shYqqIo() {
      return files;
   }

   public void init() throws IOException {
      if (!AYmSZB4Ow5().exists()) {
         eW4s4yNtSt().mkdirs();
      } else {
         this.loadFiles();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String yzGrSnqeMu(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10567 ^ -15980 ^ 21947 ^ -17048; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29934 ^ -12316 ^ 13635 ^ -29376));
      }

      return var1.toString();
   }

   public void saveFiles() {
      Iterator var1 = 2VyzX1n2vI().iterator();

      while(var1.hasNext()) {
         0bi f = (0bi)var1.next();

         try {
            f.saveFile();
         } catch (Exception var4) {
            Exception e = var4;
            e.printStackTrace();
         }
      }

   }

   private static File eW4s4yNtSt() {
      return directory;
   }

   public 0bi getFile(Class<?> clazz) {
      Iterator<0bi> customFileIterator = SC2SS5ndvI().iterator();

      while(customFileIterator.hasNext()) {
         0bi file = (0bi)customFileIterator.next();
         if (file.getClass() == clazz) {
            return file;
         }
      }

      return null;
   }

   static {
      directory = new File(Minecraft.getMinecraft().gameDir, yzGrSnqeMu("̦͇ͬͦͨ͞ͻ̦ͬ"));
      files = new ArrayList();
   }

   public void loadFiles() {
      Iterator var1 = I82shYqqIo().iterator();

      while(var1.hasNext()) {
         0bi file = (0bi)var1.next();

         try {
            if (0bi.access$000(file)) {
               file.loadFile();
            }
         } catch (Exception var4) {
            Exception e = var4;
            e.printStackTrace();
         }
      }

   }

   private static ArrayList _VyzX1n2vI/* $FF was: 2VyzX1n2vI*/() {
      return files;
   }

   private static File AYmSZB4Ow5() {
      return directory;
   }

   private static ArrayList SC2SS5ndvI() {
      return files;
   }

   public _bj/* $FF was: 0bj*/() {
      files.add(new 0be(yzGrSnqeMu("ͨͪͪͦͼͧͽͺ"), (boolean)(8512 ^ -23876 ^ 1082 ^ -30777)));
   }
}
