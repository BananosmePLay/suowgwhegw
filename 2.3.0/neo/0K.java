package neo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;

public class 0K extends ArrayList<String> {
   public int field_a;
   private static String _ _;

   public _K/* $FF was: 0K*/() {
      this.method_jV();
   }

   private static void method_ka(0K var0, int var1) {
      var0.field_a = var1;
   }

   private static void method_kd(0K var0, int var1) {
      var0.field_a = var1;
   }

   private static int method_kc(0K var0) {
      return var0.field_a;
   }

   private static int method_jZ(0K var0) {
      return var0.field_a;
   }

   public String method_jU() {
      String var1 = method_jX().method_bnq();
      int var2 = -19925 ^ -18256 ^ 29061 ^ -31519;
      switch (var1.hashCode()) {
         case -1180434330:
            if (var1.equals(method_jW("ЎкЧХЎСФЭ"))) {
               var2 = 5545 ^ -6036 ^ 11916 ^ -11445;
            }
            break;
         case -792413163:
            if (var1.equals(method_jW("ІЭЧПЩкЭ"))) {
               var2 = 22753 ^ -17533 ^ 1271 ^ -6251;
            }
            break;
         case 2029746065:
            if (var1.equals(method_jW("ЋнлмЧХ"))) {
               var2 = ~15905 ^ 28760 ^ -20089;
            }
      }

      switch (var2) {
         case 0:
            return method_jW("ІЭЧПЩкЭЗ") + 0ec.randomNumber(7355 ^ -15149 ^ 29086 ^ -22029);
         case 1:
            return 0V.format(method_jY().method_bnP());
         case 2:
            if (this.size() == 0) {
               return method_jW("ЭХимб");
            }

            if (method_jZ(this) >= this.size()) {
               method_ka(this, 31844 ^ -32051 ^ 20438 ^ -20097);
            }

            String a = (String)this.get(method_kb(this));
            method_kd(this, method_kc(this) + (25813 ^ -1971 ^ 14148 ^ -21539));
            return a;
         default:
            return 0ec.randomString(21276 ^ -6229 ^ 29902 ^ -16265);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_jW(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28255 ^ -16603 ^ 25838 ^ -19052; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12122 ^ -14380 ^ 18895 ^ -23287));
      }

      return var1.toString();
   }

   private static int method_kb(0K var0) {
      return var0.field_a;
   }

   public void method_jV() {
      this.clear();
      File c = 0ed.method_bFf(method_jW("ѧІЭЧПЩкЭѧЦСЫУлѧЦСЫУлѦмам"));
      0ee.createFile(c);

      try {
         Iterator var5 = Files.readAllLines(c.toPath()).iterator();

         while(var5.hasNext()) {
            String a = (String)var5.next();
            a = a.replaceAll(method_jW("ГЖЩѥвЉѥВѸѥѱЗЕ"), method_jW(""));
            if (a.length() > (2166 ^ -9851 ^ 11804 ^ -20) && a.length() < (3064 ^ -22254 ^ 27446 ^ -13876)) {
               this.add(a);
            }
         }
      } catch (Exception var4) {
         Exception b = var4;
         if (b instanceof NoSuchFileException) {
            0ek.addMessage(0cT.method_byX(method_jW("ЫЧХХЩЦЬѦЪЧмлѦФЧЩЬЦСЫУлѦЮСФЭѦЭХимб")));
         }

         b.printStackTrace();
         return;
      }

      String var10000 = method_jW("ЫЧХХЩЦЬѦЪЧмлѦФЧЩЬЦСЫУлѦСЦЮЧ");
      Object[] var10001 = new Object[8319 ^ -15348 ^ 2788 ^ -4458];
      var10001[9063 ^ -7193 ^ 1645 ^ -14611] = this.size();
      0ek.addMessage(0cT.method_byW(var10000, var10001));
   }

   private static 0cu method_jY() {
      return 0bH.field_d;
   }

   private static 0cs method_jX() {
      return 0bH.field_b;
   }
}
