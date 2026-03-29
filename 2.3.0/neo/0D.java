package neo;

public class 0D extends 0u {
   public float field_d;
   public float field_a;
   public final 0ei field_b;
   public final 0C field_c;
   private static String _DSC GG NEOWARECLIENT _;

   private static void method_hz(0D var0, float var1) {
      var0.field_a = var1;
   }

   private static float method_hC(0f var0) {
      return var0.rotationYaw;
   }

   private static 0f method_hA(0a var0) {
      return var0.player;
   }

   private static 0ei method_hx(0D var0) {
      return var0.field_b;
   }

   private static 0f method_hF(0a var0) {
      return var0.player;
   }

   private static int[] method_hv() {
      return 0B.field_a;
   }

   private static 0f method_hQ(0a var0) {
      return var0.player;
   }

   private static 0f method_hK(0a var0) {
      return var0.player;
   }

   private static void method_hJ(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static void method_hu(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float method_hD(0D var0) {
      return var0.field_d;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_hs(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8284 ^ -10761 ^ 31781 ^ -30322; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7626 ^ -32738 ^ 30938 ^ -8035));
      }

      return var1.toString();
   }

   public void method_dm() {
   }

   private static float method_hO(0f var0) {
      return var0.rotationPitch;
   }

   private static 0C method_hw(0D var0) {
      return var0.field_c;
   }

   private static 0f method_hN(0a var0) {
      return var0.player;
   }

   private static float method_hL(0f var0) {
      return var0.rotationYaw;
   }

   private static 0f method_hG(0a var0) {
      return var0.player;
   }

   private static 0f method_hB(0a var0) {
      return var0.player;
   }

   private static float method_hH(0f var0) {
      return var0.rotationPitch;
   }

   private static float method_hI(0D var0) {
      return var0.field_a;
   }

   private static void method_hM(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static void method_hP(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   public _D/* $FF was: 0D*/(0a a, 0C b) {
      super(a, method_hs("ׁײ\u05fd\u05f7\u05fc\u05feמ\u05fcץ\u05f6"));
      this.field_c = b;
      this.field_b = new 0ei();
   }

   private static void method_hy(0D var0, float var1) {
      var0.field_d = var1;
   }

   private static 0g method_ht(0a var0) {
      return var0.keyboard;
   }

   private static void method_hE(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   public void method_dn() {
      0a a = this.method_dp();
      method_hu(method_ht(a), (boolean)(10410 ^ -21744 ^ 25789 ^ -6394));
      switch (method_hv()[method_hw(this).ordinal()]) {
         case 1:
            if (method_hx(this).hasReached(15631 ^ -1785 ^ 13590 ^ -3862)) {
               method_hy(this, 0ec.floatRandom(Float.intBitsToFloat(32746 ^ 7814 ^ 29385 ^ 4269968 ^ 3118 ^ 20112 ^ 27579 ^ -1015734992), Float.intBitsToFloat(107869 ^ '訧' ^ 125764 ^ -157647648 ^ 102878 ^ 108424 ^ 3828 ^ -1246855044)));
               method_hz(this, 0ec.floatRandom(Float.intBitsToFloat('봶' ^ '쵫' ^ 2558 ^ -911735529 ^ '褌' ^ 22720 ^ '諭' ^ 186394517), Float.intBitsToFloat(98794 ^ 92627 ^ 30673 ^ 1225937553 ^ 10236 ^ 19457 ^ 4099 ^ 195475079)));
            }

            method_hE(method_hA(a), 0X.normalizeYaw(0I.method_jQ(method_hC(method_hB(a)), method_hD(this), Float.intBitsToFloat(28119 ^ '뱽' ^ 19976 ^ 363992593 ^ 23963 ^ 'ꐰ' ^ 27085 ^ 736433128), Float.intBitsToFloat(106816 ^ 26605 ^ 122050 ^ 1307487894 ^ '\ua7e0' ^ 226047 ^ 20969 ^ 1923218626))));
            method_hJ(method_hF(a), 0X.normalizePitch(0I.method_jQ(method_hH(method_hG(a)), method_hI(this), Float.intBitsToFloat(26147 ^ 462883 ^ 520094 ^ -1886902 ^ 107673 ^ '찅' ^ 113248 ^ -1045138411), Float.intBitsToFloat(129321 ^ 96995 ^ 5308 ^ 1487429173 ^ 5957 ^ 500213 ^ 23410 ^ 1742989388))));
            break;
         case 2:
            0f var10000 = method_hK(a);
            method_hM(var10000, method_hL(var10000) + 0X.normalizeYaw((float)0ec.intRandom(-25181 ^ -30658 ^ 24589 ^ -30089, 7473 ^ -18445 ^ 25705 ^ -12622)));
            var10000 = method_hN(a);
            method_hP(var10000, method_hO(var10000) + 0X.normalizePitch((float)0ec.intRandom(-631 ^ -30581 ^ 11471 ^ -22998, 25019 ^ -12566 ^ 13422 ^ -25818)));
            method_hQ(a).jump();
      }

   }
}
