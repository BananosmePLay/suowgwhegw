package neo;

public class 0cd {
   public 0cc field_b;
   public final String field_c;
   public String field_d;
   public String field_a;
   private static String _ _;

   public void method_XB(0cc a) {
      method_XJ(this, a);
   }

   private static void method_XN(0cd var0, String var1) {
      var0.field_a = var1;
   }

   public void method_XF(String a) {
      method_XN(this, a);
   }

   public _cd/* $FF was: 0cd*/(String a, String b) {
      this(a, b, 0cc.field_a);
   }

   public String method_XG() {
      return method_XO(this);
   }

   public _cd/* $FF was: 0cd*/(String a, String b, String c, 0cc d) {
      this.field_c = a;
      this.field_a = b;
      this.field_d = c;
      this.field_b = d;
   }

   public String method_XE() {
      return method_XM(this);
   }

   public 0cc method_XA() {
      return method_XI(this);
   }

   public _cd/* $FF was: 0cd*/(String a, String b, 0cc c) {
      this(a, b, method_XH(""), c);
   }

   private static String method_XM(0cd var0) {
      return var0.field_a;
   }

   private static void method_XL(0cd var0, String var1) {
      var0.field_d = var1;
   }

   private static String method_XO(0cd var0) {
      return var0.field_c;
   }

   private static 0cc method_XI(0cd var0) {
      return var0.field_b;
   }

   private static void method_XJ(0cd var0, 0cc var1) {
      var0.field_b = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_XH(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 17797 ^ -28839 ^ 16936 ^ -30476; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 23677 ^ -21287 ^ 26163 ^ -26720));
      }

      return var1.toString();
   }

   public String method_XC() {
      return method_XK(this);
   }

   private static String method_XK(0cd var0) {
      return var0.field_d;
   }

   public void method_XD(String a) {
      method_XL(this, a);
   }
}
