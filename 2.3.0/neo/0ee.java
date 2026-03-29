package neo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class 0ee {
   private static int _DSC GG NEOWARECLIENT _;

   public static void saveFile(File c, String d) {
      try {
         BufferedWriter a = new BufferedWriter(new FileWriter(c));
         Throwable var3 = null;

         try {
            a.write(d);
         } catch (Throwable var13) {
            var3 = var13;
            throw var13;
         } finally {
            if (a != null) {
               if (var3 != null) {
                  try {
                     a.close();
                  } catch (Throwable var12) {
                     var3.addSuppressed(var12);
                  }
               } else {
                  a.close();
               }
            }

         }
      } catch (IOException var15) {
         IOException b = var15;
         b.printStackTrace();
      }

   }

   public static void createFile(File c) {
      try {
         Path a = c.toPath();
         Files.createDirectories(a.getParent());
         if (c.isFile() && !a.toFile().exists()) {
            Files.createFile(a);
         }
      } catch (IOException var2) {
         IOException b = var2;
         b.printStackTrace();
      }

   }

   public static String[] listFiles(File a) {
      return a.list();
   }

   private static Charset SPkTTvJN3Y() {
      return StandardCharsets.UTF_8;
   }

   public static List<String> readFileLines(File b) {
      try {
         return Files.readAllLines(b.toPath());
      } catch (IOException var2) {
         IOException a = var2;
         a.printStackTrace();
         return null;
      }
   }

   public static boolean fileExists(File a) {
      return (boolean)(a.exists() && a.isFile() ? 6443 ^ -26741 ^ 29378 ^ -925 : 22359 ^ -12110 ^ 19491 ^ -13370);
   }

   public static String readFile(File f) {
      StringBuilder g = new StringBuilder();

      try {
         FileInputStream d = new FileInputStream(f);
         Throwable var3 = null;

         try {
            InputStreamReader c = new InputStreamReader(d, SPkTTvJN3Y());
            Throwable var5 = null;

            try {
               BufferedReader b = new BufferedReader(c);
               Throwable var7 = null;

               try {
                  String a;
                  try {
                     while((a = b.readLine()) != null) {
                        g.append(a).append(System.lineSeparator());
                     }
                  } catch (Throwable var54) {
                     var7 = var54;
                     throw var54;
                  }
               } finally {
                  if (b != null) {
                     if (var7 != null) {
                        try {
                           b.close();
                        } catch (Throwable var53) {
                           var7.addSuppressed(var53);
                        }
                     } else {
                        b.close();
                     }
                  }

               }
            } catch (Throwable var56) {
               var5 = var56;
               throw var56;
            } finally {
               if (c != null) {
                  if (var5 != null) {
                     try {
                        c.close();
                     } catch (Throwable var52) {
                        var5.addSuppressed(var52);
                     }
                  } else {
                     c.close();
                  }
               }

            }
         } catch (Throwable var58) {
            var3 = var58;
            throw var58;
         } finally {
            if (d != null) {
               if (var3 != null) {
                  try {
                     d.close();
                  } catch (Throwable var51) {
                     var3.addSuppressed(var51);
                  }
               } else {
                  d.close();
               }
            }

         }
      } catch (IOException var60) {
         IOException e = var60;
         e.printStackTrace();
      }

      return g.toString();
   }

   public _ee/* $FF was: 0ee*/() {
   }
}
