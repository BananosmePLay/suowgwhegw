package neo;

public abstract class 0u {
   public final 0a field_a;
   public final String field_c;
   public final 0d mc;
   public final 0t field_b;
   private static String _ _;

   private static void method_dA(0g var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static 0g method_dL(0a var0) {
      return var0.keyboard;
   }

   public void method_do() {
      method_dx(method_dw(method_dv(this)), (boolean)(4313 ^ -14902 ^ 16901 ^ -26858));
      method_dA(method_dz(method_dy(this)), (boolean)(26956 ^ -23371 ^ 6727 ^ -10306));
      method_dD(method_dC(method_dB(this)), (boolean)(180 ^ -13369 ^ 18469 ^ -31914));
      method_dG(method_dF(method_dE(this)), (boolean)(27801 ^ -18133 ^ 4370 ^ -15200));
      method_dJ(method_dI(method_dH(this)), (boolean)(6847 ^ -20892 ^ 11888 ^ -25941));
      method_dM(method_dL(method_dK(this)), (boolean)(16251 ^ -20867 ^ 1326 ^ -27608));
      method_dP(method_dO(method_dN(this)), (boolean)(27957 ^ -9370 ^ 23293 ^ -4946));
      this.method_dt(method_du("¾,B¾}[vm4_lwzmpvw¾,D9¾.[vm9") + this.method_dp().getNickname() + method_du("9e9vr9\u007fpwpjq|}"));
   }

   private static 0g method_dC(0a var0) {
      return var0.keyboard;
   }

   private static void method_dG(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static 0cp method_dU() {
      return 0bF.field_g;
   }

   private static 0a method_dH(0u var0) {
      return var0.field_a;
   }

   private static 0d method_dS(0u var0) {
      return var0.mc;
   }

   private static 0g method_dz(0a var0) {
      return var0.keyboard;
   }

   public abstract void method_dm();

   private static void method_dD(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static String method_dR(0u var0) {
      return var0.field_c;
   }

   public 0a method_dp() {
      return method_dQ(this);
   }

   private static 0g method_dw(0a var0) {
      return var0.keyboard;
   }

   private static void method_dM(0g var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static 0a method_dE(0u var0) {
      return var0.field_a;
   }

   private static 0a method_dv(0u var0) {
      return var0.field_a;
   }

   private static 0a method_dy(0u var0) {
      return var0.field_a;
   }

   private static void method_dJ(0g var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static 0a method_dB(0u var0) {
      return var0.field_a;
   }

   private static 0a method_dN(0u var0) {
      return var0.field_a;
   }

   private static 0t method_dT(0u var0) {
      return var0.field_b;
   }

   private static void method_dP(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static 0g method_dI(0a var0) {
      return var0.keyboard;
   }

   public String method_dq() {
      return method_dR(this);
   }

   public 0t method_ds() {
      return method_dT(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_du(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10007 ^ -24441 ^ 20297 ^ -14119; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20111 ^ -21345 ^ 31712 ^ -26135));
      }

      return var1.toString();
   }

   private static 0a method_dQ(0u var0) {
      return var0.field_a;
   }

   public void method_dt(String a) {
      if (method_dU().method_bna()) {
         0ek.printMessage(a);
      }

   }

   private static 0g method_dF(0a var0) {
      return var0.keyboard;
   }

   public abstract void method_dn();

   private static 0g method_dO(0a var0) {
      return var0.keyboard;
   }

   private static 0a method_dK(0u var0) {
      return var0.field_a;
   }

   public _u/* $FF was: 0u*/(0a a, String b) {
      this.field_a = a;
      this.field_c = b;
      this.mc = a.mc;
      this.field_b = a.getFunction();
   }

   public 0d method_dr() {
      return method_dS(this);
   }

   private static void method_dx(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }
}
