package neo;

public class 0bN extends 0dr {
   public final long field_d;
   public static 0dU field_c;
   public 0bM field_b;
   public static 0cp field_a = new 0cp(method_Tl("ʒʳʾʿ˺ʓʊ"), (boolean)(9775 ^ -7214 ^ 29281 ^ -18532));
   private static String _DSC GG NEOWARECLIENT _;

   public _bN/* $FF was: 0bN*/() {
      super(method_Tl("ʞʳʩʹʵʨʾʓʴʼʵ"), 0dz.field_f, 180 ^ -28067 ^ 20474 ^ -8941);
      0cv[] var10001 = new 0cv[7272 ^ -11833 ^ 25004 ^ -21502];
      var10001[3499 ^ -14018 ^ 26569 ^ -23716] = field_a;
      this.method_bBe(var10001);
      this.field_d = System.currentTimeMillis() / 1000L;
   }

   private static long method_Ts(0bN var0) {
      return var0.field_d;
   }

   private static void method_Tn(0bN var0, 0bM var1) {
      var0.field_b = var1;
   }

   public void method_bAU() {
      super.method_bAU();
      0dQ a = (new 0dP()).ready((ax) -> {
         method_Tr(ax);
      }).build();
      method_Tm().Discord_Initialize(method_Tl("˫˨˫˪ˣ˭˭ˣ˨˩˪ˬ˩˪ˢ˭˫ˮˮ"), a, (boolean)(10330 ^ -12614 ^ 22960 ^ -16559), (String)null);
      method_Tn(this, new 0bM(this));
      method_To(this).start();
   }

   private static 0dT method_Tm() {
      return 0dT.INSTANCE;
   }

   private static 0bM method_To(0bN var0) {
      return var0.field_b;
   }

   // $FF: synthetic method
   static long method_Tk(0bN a) {
      return method_Ts(a);
   }

   private static 0dT method_Tp() {
      return 0dT.INSTANCE;
   }

   private static void method_Tr(0dU var0) {
      field_c = var0;
   }

   public static 0dU method_Ti() {
      return method_Tq();
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Tl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11683 ^ -10351 ^ 1932 ^ -578; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30478 ^ -2925 ^ 5780 ^ -26669));
      }

      return var1.toString();
   }

   public void method_bAV() {
      super.method_bAV();
      method_Tp().Discord_Shutdown();
   }

   private static 0dU method_Tq() {
      return field_c;
   }
}
