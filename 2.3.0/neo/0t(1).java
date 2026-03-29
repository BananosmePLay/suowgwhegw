package neo;

public class 0t {
   public 0u field_b;
   public final 0a field_a;
   private static String _DSC GG NEOWARECLIENT _;

   private static 0g method_db(0a var0) {
      return var0.keyboard;
   }

   private static 0f method_cY(0a var0) {
      return var0.player;
   }

   private static 0u method_dg(0t var0) {
      return var0.field_b;
   }

   private static 0a method_cT(0t var0) {
      return var0.field_a;
   }

   private static 0u method_dk(0t var0) {
      return var0.field_b;
   }

   private static 0u method_dl(0t var0) {
      return var0.field_b;
   }

   private static 0u method_dh(0t var0) {
      return var0.field_b;
   }

   public void method_cR(0u a) {
      if (a == null && method_dg(this) != null || a != null && method_dh(this) != null) {
         method_di(this).method_do();
      }

      method_dj(this, a);
      if (a != null) {
         method_dk(this).method_dm();
      }

   }

   private static 0u method_di(0t var0) {
      return var0.field_b;
   }

   public 0u method_cS() {
      return method_dl(this);
   }

   private static void method_cZ(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static 0cp method_dc() {
      return 0bG.field_e;
   }

   private static 0g method_cV(0a var0) {
      return var0.keyboard;
   }

   private static 0u method_de(0t var0) {
      return var0.field_b;
   }

   private static 0a method_da(0t var0) {
      return var0.field_a;
   }

   public void method_cQ() {
      if (method_cT(this).isOnline()) {
         method_cZ(method_cV(method_cU(this)), (boolean)(method_cW().method_bna() && method_cY(method_cX(this)).isInWater() ? 4431 ^ -26340 ^ 9832 ^ -20934 : 12626 ^ -25891 ^ 8781 ^ -30270));
         method_dd(method_db(method_da(this)), method_dc().method_bna());
         if (method_de(this) != null) {
            method_df(this).method_dn();
         }

      }
   }

   private static 0a method_cU(0t var0) {
      return var0.field_a;
   }

   private static void method_dd(0g var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static 0u method_df(0t var0) {
      return var0.field_b;
   }

   private static void method_dj(0t var0, 0u var1) {
      var0.field_b = var1;
   }

   public _t/* $FF was: 0t*/(0a a) {
      this.field_a = a;
   }

   private static 0cp method_cW() {
      return 0bG.field_g;
   }

   private static 0a method_cX(0t var0) {
      return var0.field_a;
   }
}
