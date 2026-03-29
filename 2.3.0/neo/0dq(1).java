package neo;

import org.json.JSONObject;

public class 0dq {
   public int field_c;
   public final String field_b;
   public final String field_a;
   private static String _DSC GG NEOWARECLIENT _;

   private static String method_bAR(0dq var0) {
      return var0.field_a;
   }

   private static int method_bAS(0dq var0) {
      return var0.field_c;
   }

   private static String method_bAP(0dq var0) {
      return var0.field_b;
   }

   public int method_bAI() {
      return method_bAO(this);
   }

   private static void method_bAQ(0dq var0, int var1) {
      var0.field_c = var1;
   }

   public void method_bAK(int a) {
      method_bAQ(this, a);
   }

   private static String method_bAT(0dq var0) {
      return var0.field_b;
   }

   private static int method_bAO(0dq var0) {
      return var0.field_c;
   }

   public String method_bAH() {
      return method_bAN(this);
   }

   public JSONObject method_bAL() {
      JSONObject a = new JSONObject();
      a.put(method_bAM("ǵǺǶǾ"), (Object)method_bAR(this));
      a.put(method_bAM("ǰǾǢ"), method_bAS(this));
      a.put(method_bAM("ǸǴǶǶǺǵǿ"), (Object)method_bAT(this));
      return a;
   }

   private static String method_bAN(0dq var0) {
      return var0.field_a;
   }

   public _dq/* $FF was: 0dq*/(String a, int b, String c) {
      this.field_a = a;
      this.field_c = b;
      this.field_b = c;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bAM(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 29444 ^ -3401 ^ 11113 ^ -21798; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12952 ^ -12502 ^ 27567 ^ -26746));
      }

      return var1.toString();
   }

   public String method_bAJ() {
      return method_bAP(this);
   }
}
