package neo;

public class 0bx extends 0cV {
   private static int _DSC GG NEOWARECLIENT _;

   public void method_bze(String[] a) {
      String var10000;
      Object[] var10001;
      if (method_Qi().isSingleplayer()) {
         var10000 = method_Qh("\u0018;758\u001c;' ");
      } else {
         var10000 = method_Qh("q'nq'");
         var10001 = new Object[26842 ^ -30828 ^ 23707 ^ -19497];
         var10001[32261 ^ -1142 ^ 6212 ^ -25141] = 0ef.method_bFj();
         var10001[21925 ^ -30177 ^ 23260 ^ -31385] = 0ef.method_bFi();
         var10000 = String.format(var10000, var10001);
      }

      String b = var10000;
      var10000 = method_Qh("7;995:0z31 =$z=:2;");
      var10001 = new Object[11351 ^ -4627 ^ 28743 ^ -19972];
      var10001[14537 ^ -1872 ^ 30476 ^ -18571] = b;
      0ek.addMessage(0cT.method_byW(var10000, var10001));
      lg.setClipboardString(b);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Qh(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18148 ^ -21717 ^ 23598 ^ -19999; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13814 ^ -8522 ^ 4732 ^ -1688));
      }

      return var1.toString();
   }

   private static nC method_Qi() {
      return mc;
   }

   public _bx/* $FF was: 0bx*/() {
      super(method_Qh("31 =$"), method_Qh("7;995:0z31 =$z01'7&=$ =;:"));
   }
}
