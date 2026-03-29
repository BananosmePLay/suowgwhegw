package neo;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class 0L extends ArrayList<0dC> {
   public int field_b;
   public int field_a;
   private static String _ _;

   private void method_kf(String c, String d) {
      try {
         0dC a = 0dC.method_bBI();
         a.method_bBR(0dB.getType(d));
         if (c.contains(method_kk("۱ۤۤ"))) {
            a.method_bBR(0dB.getType(c.split(method_kk("۱ۤۤ"))[25807 ^ -20243 ^ 19945 ^ -26165]));
            c = c.split(method_kk("۱ۤۤ"))[6588 ^ -1828 ^ 25515 ^ -32054];
         }

         if (c.contains(method_kk("۱")) && c.contains(method_kk("ڋ"))) {
            a.method_bBM(c.split(method_kk("ڋ"))[6242 ^ -726 ^ 24229 ^ -17427].split(method_kk("۱"))[18456 ^ -8928 ^ 2478 ^ -25450]);
            a.method_bBO(c.split(method_kk("ڋ"))[7081 ^ -1416 ^ 13544 ^ -10951].split(method_kk("۱"))[6473 ^ -18618 ^ 27299 ^ -15187]);
            c = c.split(method_kk("ڋ"))[14653 ^ -29381 ^ 12962 ^ -31067];
         }

         a.method_bBK(c);
         this.add(a);
      } catch (Exception var4) {
         Exception b = var4;
         String var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڻڪڹڸڮۥڮڹڹڤڹ");
         Object[] var10001 = new Object[12224 ^ -25743 ^ 20361 ^ -1223];
         var10001[10832 ^ -24535 ^ 26295 ^ -4914] = c;
         0ek.addMessage(0cT.method_byW(var10000, var10001));
         b.printStackTrace();
      }

   }

   private static int method_kn(0L var0) {
      return var0.field_a;
   }

   private static void method_kx(0L var0, int var1) {
      var0.field_b = var1;
   }

   private static int method_kr(0L var0) {
      return var0.field_b;
   }

   private static void method_ks(0L var0, int var1) {
      var0.field_b = var1;
   }

   private static 0dB method_kA() {
      return 0dB.field_e;
   }

   private int method_kh(0dB a) {
      return (int)this.stream().filter((b) -> {
         return b.method_bBP().equals(a);
      }).count();
   }

   public _L/* $FF was: 0L*/() {
      try {
         String var10001 = method_kk("ڣڿڿڻ۱ۤۤۮڸۤڪڻڢۤڻڹڤڳڲ۴ڸڮڸڸڢڤڥ۶ۮڸ");
         Object[] var10002 = new Object[5162 ^ -6068 ^ 19775 ^ -20133];
         var10002[30423 ^ -6573 ^ 32253 ^ -4743] = 0bz.method_Qm().method_Ql().method_bxl();
         var10002[1241 ^ -12396 ^ 16740 ^ -30168] = 0dI.method_bDA();
         this.method_ke(String.format(var10001, var10002), method_kk("ڸڤڨڠڸ۾"));
      } catch (Exception var2) {
         Exception a = var2;
         a.printStackTrace();
      }

   }

   private static int method_kv(0L var0) {
      return var0.field_a;
   }

   public void method_ke(String f, String g) {
      if (0dB.getType(g) == null) {
         0ek.addMessage(0cT.method_byX(method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڿڲڻڮۥڮڹڹڤڹ")));
      } else {
         this.clear();
         method_kl(this, 28841 ^ -30104 ^ 15709 ^ -14436);
         method_km(this, 7306 ^ -18201 ^ 16954 ^ -6569);
         Exception e;
         if (!f.startsWith(method_kk("ڣڿڿڻڸ۱ۤۤ")) && !f.startsWith(method_kk("ڣڿڿڻ۱ۤۤ"))) {
            try {
               Iterator var11 = ((List)Objects.requireNonNull(0ee.readFileLines(0ed.method_bFf(method_kk("ۤڅڮڤڜڪڹڮۤڻڹڤڳڲۤ") + f.replace(method_kk("ۥڿڳڿ"), method_kk("")) + method_kk("ۥڿڳڿ"))))).iterator();

               while(var11.hasNext()) {
                  String d = (String)var11.next();
                  this.method_kf(d, g);
               }
            } catch (Exception var8) {
               e = var8;
               if (e instanceof NoSuchFileException) {
                  String var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڭڢڧڮۥڮڦڻڿڲ");
                  Object[] var10001 = new Object[11418 ^ -15336 ^ 31664 ^ -27853];
                  var10001[12351 ^ -22231 ^ 2520 ^ -28466] = f;
                  0ek.addMessage(0cT.method_byW(var10000, var10001));
               }

               e.printStackTrace();
               return;
            }
         } else {
            try {
               Document b = Jsoup.connect(f).ignoreHttpErrors((boolean)(28324 ^ -28311 ^ 41 ^ -27)).get();
               String[] var4 = b.text().split(method_kk("۫"));
               int var5 = var4.length;

               for(int var6 = 19894 ^ -23733 ^ 10630 ^ -14469; var6 < var5; ++var6) {
                  String a = var4[var6];
                  this.method_kf(a, g);
               }
            } catch (Exception var9) {
               e = var9;
               0ek.addMessage(0cT.method_byX(method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥھڹڧۥڮڦڻڿڲ")));
               e.printStackTrace();
            }
         }

         this.method_ki();
      }
   }

   private static 0dB method_ky() {
      return 0dB.field_d;
   }

   private static void method_kl(0L var0, int var1) {
      var0.field_a = var1;
   }

   private static void method_kp(0L var0, int var1) {
      var0.field_b = var1;
   }

   private static int method_kt(0L var0) {
      return var0.field_b;
   }

   public 0dC method_kg() {
      if (this.size() == 0) {
         return null;
      } else {
         if (method_kn(this) >= this.size()) {
            method_ko(this, 19883 ^ -3257 ^ 3509 ^ -19623);
            method_kp(this, 18504 ^ -19695 ^ 4087 ^ -2898);
         }

         0dC a = (0dC)this.get(method_kq(this));
         method_ks(this, method_kr(this) + (1598 ^ -15731 ^ 21706 ^ -28552));
         if ((float)method_kt(this) >= method_ku().method_bnH()) {
            method_kw(this, method_kv(this) + (13702 ^ -21805 ^ 459 ^ -24929));
            method_kx(this, 19841 ^ -13457 ^ 16955 ^ -15147);
         }

         return a;
      }
   }

   private static void method_ko(0L var0, int var1) {
      var0.field_a = var1;
   }

   private static void method_km(0L var0, int var1) {
      var0.field_b = var1;
   }

   private static int method_kq(0L var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_kk(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 275 ^ -24480 ^ 20177 ^ -4190; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ '葒' ^ -28470 ^ '鯇' ^ -30316));
      }

      return var1.toString();
   }

   private void method_ki() {
      String var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڢڥڭڤ");
      Object[] var10001 = new Object[11710 ^ -16709 ^ 29875 ^ -6217];
      var10001[30505 ^ -3982 ^ 19158 ^ -12915] = this.size();
      0ek.addMessage(0cT.method_byW(var10000, var10001));
      if (this.size() > 0) {
         var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڿڲڻڮۥڸڤڨڠڸۿ");
         var10001 = new Object[14024 ^ -5540 ^ 14867 ^ -6522];
         var10001[14148 ^ -14304 ^ 31168 ^ -31068] = this.method_kh(method_ky());
         0ek.addMessage(0cT.method_byW(var10000, var10001));
         var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڿڲڻڮۥڸڤڨڠڸ۾");
         var10001 = new Object[17558 ^ -4036 ^ 17390 ^ -2235];
         var10001[18581 ^ -17505 ^ 15358 ^ -14092] = this.method_kh(method_kz());
         0ek.addMessage(0cT.method_byW(var10000, var10001));
         var10000 = method_kk("ڨڤڦڦڪڥگۥکڤڿڸۥڧڤڪگڻڹڤڳڲۥڿڲڻڮۥڣڿڿڻ");
         var10001 = new Object[14833 ^ -23429 ^ 21025 ^ -12374];
         var10001[32236 ^ -6808 ^ 5942 ^ -28750] = this.method_kh(method_kA());
         0ek.addMessage(0cT.method_byW(var10000, var10001));
      }

   }

   private static 0ct method_ku() {
      return 0bH.field_f;
   }

   private static 0dB method_kz() {
      return 0dB.field_b;
   }

   private static void method_kw(0L var0, int var1) {
      var0.field_a = var1;
   }
}
