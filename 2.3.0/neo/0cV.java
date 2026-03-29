package neo;

import java.util.Iterator;
import java.util.Map;

public abstract class 0cV implements 0cD, 0cC, 0cB {
   public final String field_b;
   public final String field_a;
   private static String _ _;

   private static String method_bzn(0cV var0) {
      return var0.field_b;
   }

   public abstract void method_bze(String[] var1);

   public String method_bzh() {
      return 0cT.method_byX(method_bzn(this));
   }

   public String method_bzg() {
      return method_bzm(this);
   }

   private static String method_bzm(0cV var0) {
      return var0.field_a;
   }

   public _cV/* $FF was: 0cV*/(String a, String b) {
      this.field_a = a;
      this.field_b = b;
   }

   private static String method_bzl(0cV var0) {
      return var0.field_a;
   }

   public String method_bzi() {
      return method_bzo().method_Qr().method_bxu();
   }

   public void method_bzf() {
      Iterator var1 = method_bzk().method_Qo().entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<Object, Object> b = (Map.Entry)var1.next();
         String a = (String)b.getKey();
         String var10001 = method_bzj("ƬƠƢƢƮơƫǡǪƼ");
         Object[] var10002 = new Object[25164 ^ -31721 ^ 26850 ^ -29000];
         var10002[23325 ^ -15534 ^ 848 ^ -25825] = method_bzl(this);
         if (a.startsWith(String.format(var10001, var10002)) && a.endsWith(method_bzj("ǡƼƶơƻƮƷ"))) {
            Object[] var4 = new Object[708 ^ -6653 ^ 14091 ^ -11315];
            var4[1396 ^ -16942 ^ 21876 ^ -4654] = this.method_bzi();
            0ek.addMessage(0cT.method_byW(a, var4));
         }
      }

   }

   private static 0bz method_bzk() {
      return client;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bzj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6497 ^ -32355 ^ 21448 ^ -13516; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 29345 ^ -13698 ^ 17467 ^ -725));
      }

      return var1.toString();
   }

   private static 0bz method_bzo() {
      return client;
   }
}
