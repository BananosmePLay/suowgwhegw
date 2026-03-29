package neo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class 0cf {
   public static ArrayList<0cd> field_a;
   public static final File field_b;
   private static String _ _;

   public static void method_Yy(0cd a) {
      method_YE().remove(a);
      method_YB();
   }

   public static void method_Yx(0cd a) {
      method_YD().add(a);
      method_YB();
   }

   public static void method_YB() {
      try {
         PrintWriter b = new PrintWriter(new FileWriter(method_YK()));
         Iterator var1 = method_YL().iterator();

         while(var1.hasNext()) {
            0cd a = (0cd)var1.next();
            if (a.method_XC().equals(method_YC(""))) {
               b.println(a.method_XG() + method_YC("ʷ") + a.method_XE() + method_YC("ʷ") + a.method_XG() + method_YC("ʷ") + a.method_XA());
            } else {
               b.println(a.method_XG() + method_YC("ʷ") + a.method_XE() + method_YC("ʷ") + a.method_XC() + method_YC("ʷ") + a.method_XA());
            }
         }

         b.close();
      } catch (Exception var3) {
         Exception c = var3;
         c.printStackTrace();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_YC(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10516 ^ -18466 ^ 14551 ^ -23011; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 1333 ^ -12779 ^ 2716 ^ -15567));
      }

      return var1.toString();
   }

   private static ArrayList method_YL() {
      return field_a;
   }

   public static void method_Yz() {
      method_YF().clear();
      method_YB();
   }

   private static ArrayList method_YE() {
      return field_a;
   }

   private static ArrayList method_YJ() {
      return field_a;
   }

   public static void method_YA() {
      try {
         BufferedReader b = new BufferedReader(new FileReader(method_YG()));

         String c;
         while((c = b.readLine()) != null) {
            String[] a = c.split(method_YC("ʷ"));
            if (a.length > (17748 ^ -24624 ^ 27141 ^ -20349)) {
               method_YH().add(new 0cd(a[13451 ^ -31894 ^ 22134 ^ -7785], a[15014 ^ -6157 ^ 28201 ^ -19587], a[4356 ^ -30246 ^ 18971 ^ -11577], a.length > (18909 ^ -7006 ^ 7030 ^ -18934) ? 0cc.valueOf(a[4161 ^ -17853 ^ 8280 ^ -30119]) : method_YI()));
            } else {
               method_YJ().add(new 0cd(a[25131 ^ -8249 ^ 521 ^ -16411], a[19672 ^ -392 ^ 30184 ^ -14519]));
            }
         }

         b.close();
      } catch (Exception var3) {
         Exception d = var3;
         d.printStackTrace();
      }

   }

   private static ArrayList method_YD() {
      return field_a;
   }

   private static ArrayList method_YH() {
      return field_a;
   }

   static {
      field_b = new File(nC.getMinecraft().gameDir, method_YC("ʢ˃˨ˢ˚ˬ˿˨ʢˬˮˮˢ˸ˣ˹˾ʣ˹˵˹"));
      field_a = new ArrayList();
   }

   private static 0cc method_YI() {
      return 0cc.field_a;
   }

   public _cf/* $FF was: 0cf*/() {
   }

   private static File method_YG() {
      return field_b;
   }

   private static ArrayList method_YF() {
      return field_a;
   }

   private static File method_YK() {
      return field_b;
   }
}
