package neo;

public class 0db {
   public final int field_d;
   public boolean field_e;
   public final long field_a;
   public final Runnable field_c;
   public final boolean field_b;
   private static String _DSC GG NEOWARECLIENT _;

   private static void method_bzR(0db var0, boolean var1) {
      var0.field_e = var1;
   }

   public long method_bzF() {
      return method_bzO(this);
   }

   public void method_bzI() {
      method_bzR(this, (boolean)(23147 ^ -30302 ^ 16654 ^ -27962));
   }

   private static long method_bzO(0db var0) {
      return var0.field_a;
   }

   public boolean method_bzH() {
      return method_bzQ(this);
   }

   private static int method_bzM(0db var0) {
      return var0.field_d;
   }

   public Runnable method_bzE() {
      return method_bzN(this);
   }

   private static boolean method_bzQ(0db var0) {
      return var0.field_e;
   }

   public _db/* $FF was: 0db*/(int a, Runnable b, long c, boolean d) {
      this.field_d = a;
      this.field_c = b;
      this.field_a = c;
      this.field_b = d;
      this.field_e = (boolean)(12578 ^ -2404 ^ 30426 ^ -20124);
   }

   public String method_bzK() {
      String var10000 = method_bzL("ѳушхфѕьхфЀѣьщхюєѴсѓыЀЃЅф");
      Object[] var10001 = new Object[6395 ^ -23761 ^ 27181 ^ -11784];
      var10001[797 ^ -11376 ^ 3678 ^ -8493] = method_bzS(this);
      return String.format(var10000, var10001);
   }

   public boolean method_bzJ() {
      return (boolean)(!this.method_bzH() && System.currentTimeMillis() > this.method_bzF() ? 25529 ^ -28694 ^ 25783 ^ -30491 : 1195 ^ -10622 ^ 23892 ^ -28803);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bzL(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 20851 ^ -29552 ^ 32016 ^ -24333; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ '蟡' ^ -30870 ^ '鎸' ^ -26861));
      }

      return var1.toString();
   }

   public int method_bzD() {
      return method_bzM(this);
   }

   private static boolean method_bzP(0db var0) {
      return var0.field_b;
   }

   private static Runnable method_bzN(0db var0) {
      return var0.field_c;
   }

   private static int method_bzS(0db var0) {
      return var0.field_d;
   }

   public boolean method_bzG() {
      return method_bzP(this);
   }
}
