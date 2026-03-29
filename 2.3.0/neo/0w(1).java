package neo;

public class 0w extends 0u {
   public 0ei field_a;
   private static String _DSC GG NEOWARECLIENT _;

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_ez(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28198 ^ -32186 ^ 26001 ^ -30223; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 8136 ^ -12026 ^ 2334 ^ -14412));
      }

      return var1.toString();
   }

   private static 0ei method_eG(0w var0) {
      return var0.field_a;
   }

   public _w/* $FF was: 0w*/(0a a) {
      super(a, method_ez("%\u0011\u0010\u000b\"\r\u0017\f"));
   }

   private static PG method_eA() {
      return NK.FISHING_ROD;
   }

   private static 0ei method_eE(0w var0) {
      return var0.field_a;
   }

   public void method_ey() {
      method_eH(this).reset();
      this.method_dt(method_ez("ÃQ?Ã\u0000&\u000b\u0010I\"\u0011\n\u0007\u0010\r\u000b\nÃQ9DÃS&\u000b\u0010D") + this.method_dp().getNickname() + method_ez("D\u0018D\u0002\r\u0017\fD\f\u000b\u000b\u000f\u0001\u0000"));
      0bz.method_Qm().method_Qp().addScheduler(this.method_dr()::rightClickMouse, 100L).addScheduler(this.method_dr()::rightClickMouse, 700L);
   }

   private static MU method_eD(0f var0) {
      return var0.fishEntity;
   }

   private static void method_eB(0w var0, 0ei var1) {
      var0.field_a = var1;
   }

   private static 0ei method_eH(0w var0) {
      return var0.field_a;
   }

   public void method_do() {
      this.method_dp().changeSlot(14884 ^ -26650 ^ 14456 ^ -27205);
      super.method_do();
   }

   public void method_dm() {
      0a a = this.method_dp();
      if (0X.findItem(a, method_eA()) == (-30486 ^ -26996 ^ 359 ^ -7938)) {
         this.method_dt(method_ez("ÃQ?Ã\u0000&\u000b\u0010I\"\u0011\n\u0007\u0010\r\u000b\nÃQ9DÃS&\u000b\u0010D") + a.getNickname() + method_ez("D\u0018D\"-7,-*#;6+ D\n\u000b\u0010D\u0002\u000b\u0011\n\u0000E"));
         this.method_ds().method_cR((0u)null);
      } else {
         a.changeSlot(31182 ^ -30212 ^ 6905 ^ -5429);
         this.method_dr().rightClickMouse();
         this.method_dt(method_ez("ÃQ?Ã\u0000&\u000b\u0010I\"\u0011\n\u0007\u0010\r\u000b\nÃQ9DÃS&\u000b\u0010D") + a.getNickname() + method_ez("D\u0018D7\u0010\u0005\u0016\u0010\r\n\u0003D%\u0011\u0010\u000b\"\r\u0017\f\u0001\u0016JJJ"));
         method_eB(this, new 0ei());
      }
   }

   public void method_dn() {
      MU a = method_eD(method_eC(this.method_dp()));
      if (method_eE(this).hasReached(method_eF().method_bnH())) {
         if (a != null) {
            this.method_ey();
         } else {
            method_eG(this).reset();
            this.method_dr().rightClickMouse();
         }
      }

      if (a != null) {
         a.onUpdate();
      }
   }

   private static 0f method_eC(0a var0) {
      return var0.player;
   }

   private static 0ct method_eF() {
      return 0bG.field_f;
   }
}
