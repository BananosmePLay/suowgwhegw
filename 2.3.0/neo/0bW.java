package neo;

public class 0bW extends 0dr {
   public Thread field_a;
   public static final 0cu field_c = new 0cu(method_WM("ȍȼȮȮȪȲȯȹ"), method_WM("ɬɯɮɬɯɮ"));
   public static final 0cp field_b = new 0cp(method_WM("Ȼȴȥ"), (boolean)(1601 ^ -5811 ^ 12323 ^ -8402));
   private static int _DSC GG NEOWARECLIENT _;

   private static 0cp method_WS() {
      return field_b;
   }

   private static Thread method_WO(0bW var0) {
      return var0.field_a;
   }

   private static 0cu method_WT() {
      return field_c;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_WM(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 24199 ^ -11687 ^ 8540 ^ -21118; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3882 ^ -6313 ^ 1037 ^ -4563));
      }

      return var1.toString();
   }

   public _bW/* $FF was: 0bW*/() {
      super(method_WM("ȑȴȰȿȲȜȨȩȵ"), 0dz.field_d, 15413 ^ -8454 ^ 26397 ^ -31278);
      0cv[] var10001 = new 0cv[12788 ^ -31297 ^ 4898 ^ -22677];
      var10001[11235 ^ -4375 ^ 30562 ^ -19864] = field_c;
      var10001[22661 ^ -20398 ^ 19926 ^ -23296] = field_b;
      this.method_bBe(var10001);
   }

   private static Thread method_WQ(0bW var0) {
      return var0.field_a;
   }

   public void method_bAU() {
      super.method_bAU();
      this.method_bBi();
      method_WN(this, new Thread(() -> {
         for(int a = 5185 ^ -456 ^ 22735 ^ -19786; a < (5326 ^ -16646 ^ 25936 ^ -14156); ++a) {
            jh var10000 = method_WR();
            String var10001 = method_WM("ɲɸȮɽɸȮɽɸȮ");
            Object[] var10002 = new Object[9783 ^ -30162 ^ 18959 ^ -6635];
            var10002[28578 ^ -26758 ^ 21674 ^ -21390] = method_WS().method_bna() ? method_WM("ȾȵȼȳȺȸȭȼȮȮȪȲȯȹ") : method_WM("ȞȵȼȳȺȸȭȼȮȮȪȲȯȹ");
            var10002[19361 ^ -29131 ^ 24353 ^ -25932] = method_WT();
            var10002[16700 ^ -17248 ^ 24106 ^ -23628] = method_WU();
            var10000.sendChatMessage(String.format(var10001, var10002));
         }

      }));
      method_WO(this).start();
   }

   private static void method_WN(0bW var0, Thread var1) {
      var0.field_a = var1;
   }

   private static jh method_WR() {
      return nC.player;
   }

   private static Thread method_WP(0bW var0) {
      return var0.field_a;
   }

   private static 0cu method_WU() {
      return field_c;
   }

   public void method_bAV() {
      super.method_bAV();
      if (method_WP(this) != null) {
         method_WQ(this).interrupt();
      }

   }
}
