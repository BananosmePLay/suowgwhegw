package neo;

import net.minecraft.util.EnumHand;

public class 0v extends 0u {
   public Ig field_b = null;
   public final 0ei field_a = new 0ei();
   private static int _DSC GG NEOWARECLIENT _;

   private static boolean method_dZ(Ig var0) {
      return var0.isDead;
   }

   private static 0f method_eh(0a var0) {
      return var0.player;
   }

   public _v/* $FF was: 0v*/(0a a, String b) {
      super(a, method_dV("ժ՞ՙՊ"));
      if (b.equalsIgnoreCase(this.method_dp().getNickname())) {
         this.method_dt(method_dV("\u058cԞհ\u058cՏթՄ՟Ԇխ՞ՅՈ՟ՂՄՅ\u058cԞնԋ\u058cԜթՄ՟ԋ") + a.getNickname() + method_dV("ԋ\u0557ԋբՅ՝ՊՂՇՏԋ՟ՊՙՌՎ՟ԋՎՅ՟Ղ՟ՒԊ"));
         this.method_ds().method_cR((0u)null);
      } else {
         this.field_b = (Ig)(b.equalsIgnoreCase(method_dV("իՆՎ")) ? nC.player : 0X.getByName(b));
      }
   }

   private static Ig method_dW(0v var0) {
      return var0.field_b;
   }

   private static 0i method_eu(0a var0) {
      return var0.controller;
   }

   private static void method_el(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   public void method_dm() {
      if (method_dW(this) == null) {
         this.method_dt(method_dV("\u058cԞհ\u058cՏթՄ՟Ԇխ՞ՅՈ՟ՂՄՅ\u058cԞնԋ\u058cԜթՄ՟ԋ") + this.method_dp().getNickname() + method_dV("ԋ\u0557ԋվՅՀՅՄ՜Յԋ՟ՊՙՌՎ՟Ԋ"));
         this.method_ds().method_cR((0u)null);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_dV(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10557 ^ -22941 ^ 22049 ^ -9857; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 20052 ^ -32091 ^ 14480 ^ -3766));
      }

      return var1.toString();
   }

   private static Ig method_dX(0v var0) {
      return var0.field_b;
   }

   private static 0g method_er(0a var0) {
      return var0.keyboard;
   }

   private static 0f method_en(0a var0) {
      return var0.player;
   }

   private static 0f method_em(0a var0) {
      return var0.player;
   }

   private static double method_eb(Ig var0) {
      return var0.posX;
   }

   private static float method_ek(0f var0) {
      return var0.rotationYaw;
   }

   private static 0f method_ej(0a var0) {
      return var0.player;
   }

   private static 0f method_eq(0a var0) {
      return var0.player;
   }

   private static double method_ed(Ig var0) {
      return var0.posY;
   }

   private static EnumHand method_ex() {
      return EnumHand.MAIN_HAND;
   }

   private static void method_ep(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static Ig method_ee(0v var0) {
      return var0.field_b;
   }

   private static 0f method_ew(0a var0) {
      return var0.player;
   }

   private static Ig method_ec(0v var0) {
      return var0.field_b;
   }

   private static 0ei method_et(0v var0) {
      return var0.field_a;
   }

   private static Ig method_ea(0v var0) {
      return var0.field_b;
   }

   private static 0f method_ei(0a var0) {
      return var0.player;
   }

   private static Ig method_dY(0v var0) {
      return var0.field_b;
   }

   public void method_dn() {
      if (method_dX(this) != null) {
         if (method_dZ(method_dY(this))) {
            this.method_ds().method_cR((0u)null);
         } else {
            0a a = this.method_dp();
            float b = 0X.getDistance(a, method_eb(method_ea(this)), method_ed(method_ec(this)), method_ef(method_ee(this)));
            float[] c = 0I.method_jP(method_eg(this).getPosition(), method_eh(a));
            method_el(method_ei(a), 0X.normalizeYaw(0I.method_jQ(method_ek(method_ej(a)), c[12758 ^ -20658 ^ 566 ^ -25426], Float.intBitsToFloat(520275 ^ 22922 ^ 517248 ^ 57377347 ^ 344 ^ 103482 ^ 14956 ^ 1013509960), Float.intBitsToFloat('횔' ^ 1028017 ^ '\ue1fa' ^ 1109905651 ^ 6111 ^ '흁' ^ 25914 ^ 2103714117))));
            method_ep(method_em(a), 0X.normalizePitch(0I.method_jQ(method_eo(method_en(a)), c[4971 ^ -18634 ^ 30785 ^ -9187], Float.intBitsToFloat(9919 ^ '\uf1e8' ^ 29925 ^ 1369121871 ^ 10116 ^ 114641 ^ 8930 ^ 1855500822), Float.intBitsToFloat('\ueb78' ^ '틆' ^ 12945 ^ -549632193 ^ 3235 ^ '\uddac' ^ 31326 ^ -529454196))));
            if (b < Float.intBitsToFloat(27299 ^ 1018083 ^ 9719 ^ 320338849 ^ 'ꇘ' ^ 1019801 ^ '\ud905' ^ 1398262610)) {
               method_eq(a).jump();
            }

            method_es(method_er(a), (boolean)(5984 ^ -6740 ^ 3888 ^ -515));
            if (b < Float.intBitsToFloat('贅' ^ '\udbc7' ^ 16009 ^ -873064544 ^ '띾' ^ 'ﺴ' ^ 8488 ^ -1951000823) && method_et(this).hasReached(0ec.intRandom(32661 ^ -10639 ^ 21725 ^ -1655, 14764 ^ -16911 ^ 23365 ^ -10452))) {
               method_eu(a).attackEntity(method_ev(this));
               method_ew(a).swingArm(method_ex());
            }

         }
      }
   }

   private static Ig method_ev(0v var0) {
      return var0.field_b;
   }

   private static float method_eo(0f var0) {
      return var0.rotationPitch;
   }

   private static Ig method_eg(0v var0) {
      return var0.field_b;
   }

   private static void method_es(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   public void method_do() {
      super.method_do();
   }

   private static double method_ef(Ig var0) {
      return var0.posZ;
   }
}
