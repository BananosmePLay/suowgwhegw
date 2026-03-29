package neo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class 0p {
   public final HashMap<String, String> field_a = new HashMap();
   private static int _DSC GG NEOWARECLIENT _;

   private static HashMap method_bH(0p var0) {
      return var0.field_a;
   }

   public _p/* $FF was: 0p*/() {
      this.method_bx();
   }

   private static HashMap method_bD(0p var0) {
      return var0.field_a;
   }

   private static HashMap method_bF(0p var0) {
      return var0.field_a;
   }

   private static HashMap method_bE(0p var0) {
      return var0.field_a;
   }

   private static HashMap method_bG(0p var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bC(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3111 ^ -30709 ^ 18266 ^ -15498; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30816 ^ -32478 ^ 23693 ^ -22964));
      }

      return var1.toString();
   }

   public String method_bz(0cW a) {
      return (String)method_bG(this).getOrDefault(a.getHash(), (Object)null);
   }

   public void method_bx() {
      try {
         0ee.createFile(0ed.method_bFf(method_bC("άύϦϬϔϢϱϦάώϢϭ϶ϢϯϋϦϯϳϦϱάϰϢϵϦϧ")));
         if (0ee.fileExists(0ed.method_bFf(method_bC("άύϦϬϔϢϱϦάώϢϭ϶ϢϯϋϦϯϳϦϱάϧϢϷϢέϷϻϷ")))) {
            BufferedReader c = new BufferedReader(new FileReader(0ed.method_bFf(method_bC("άύϦϬϔϢϱϦάώϢϭ϶ϢϯϋϦϯϳϦϱάϧϢϷϢέϷϻϷ"))));
            Throwable var2 = null;

            try {
               String b;
               try {
                  while((b = c.readLine()) != null) {
                     String[] a = b.split(method_bC("ι"));
                     if (a.length == (7167 ^ -27156 ^ 20185 ^ -16184)) {
                        method_bE(this).put(a[24685 ^ -31465 ^ 15068 ^ -8282], a[25251 ^ -15695 ^ 12526 ^ -28419]);
                     }
                  }
               } catch (Throwable var13) {
                  var2 = var13;
                  throw var13;
               }
            } finally {
               if (c != null) {
                  if (var2 != null) {
                     try {
                        c.close();
                     } catch (Throwable var12) {
                        var2.addSuppressed(var12);
                     }
                  } else {
                     c.close();
                  }
               }

            }
         }
      } catch (IOException var15) {
         IOException d = var15;
         d.printStackTrace();
      }

   }

   public void method_by(0cW a, String b) {
      method_bF(this).put(a.getHash(), b);
      this.method_bw();
   }

   public void method_bw() {
      try {
         0ee.createFile(0ed.method_bFf(method_bC("άύϦϬϔϢϱϦάώϢϭ϶ϢϯϋϦϯϳϦϱάϰϢϵϦϧ")));
         PrintWriter a = new PrintWriter(0ed.method_bFf(method_bC("άύϦϬϔϢϱϦάώϢϭ϶ϢϯϋϦϯϳϦϱάϧϢϷϢέϷϻϷ")));
         Throwable var2 = null;

         try {
            method_bD(this).forEach((bx, c) -> {
               a.println(bx + method_bC("ι") + c);
            });
            a.flush();
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (a != null) {
               if (var2 != null) {
                  try {
                     a.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  a.close();
               }
            }

         }
      } catch (IOException var14) {
         IOException b = var14;
         b.printStackTrace();
      }

   }

   public int method_bA() {
      return method_bH(this).size();
   }
}
