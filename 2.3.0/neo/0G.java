package neo;

import javax.vecmath.Vector2f;

public class 0G extends 0u {
   public int field_b;
   public Ig field_a = null;
   public 0F field_c;
   private static String _ _;

   private static 0g method_jD(0a var0) {
      return var0.keyboard;
   }

   private static double method_jp(0f var0) {
      return var0.posZ;
   }

   private static 0F method_jI() {
      return 0F.field_b;
   }

   private static 0g method_jB(0a var0) {
      return var0.keyboard;
   }

   private static Ig method_ji(0G var0) {
      return var0.field_a;
   }

   public void method_do() {
      super.method_do();
   }

   private static void method_jM(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static double method_jj(Ig var0) {
      return var0.posZ;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_iX(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11779 ^ -27922 ^ 31632 ^ -14467; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31479 ^ -4245 ^ 28377 ^ -1556));
      }

      return var1.toString();
   }

   private static 0g method_jL(0a var0) {
      return var0.keyboard;
   }

   private static 0F method_jH(0G var0) {
      return var0.field_c;
   }

   private static float method_jq(Vector2f var0) {
      return var0.y;
   }

   private static void method_jK(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static Ig method_jg(0G var0) {
      return var0.field_a;
   }

   private static void method_jC(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   public void method_dn() {
      if (method_iZ(this) != null) {
         0a a = this.method_dp();
         float b = 0X.getDistance(a, method_jb(method_ja(this)), method_jd(method_jc(this)));
         Vector2f c = 0X.getBlockAngles(method_jf(method_je(this)), method_jh(method_jg(this)) + Double.longBitsToDouble(-1293505825894335372L ^ -3320125658211058572L), method_jj(method_ji(this)), method_jl(method_jk(a)), method_jn(method_jm(a)), method_jp(method_jo(a)));
         float d = 0X.normalizeYaw(method_jq(c));
         float e = 0X.normalizePitch(method_jr(c));
         if (!Float.isNaN(d) && !Float.isNaN(e)) {
            method_jt(method_js(a), d);
            method_jv(method_ju(a), e);
         }

         if (b > (float)method_jw(this)) {
            method_jy(method_jx(a), (boolean)(18172 ^ -18856 ^ 3350 ^ -589));
            if (method_jz(this).equals(method_jA())) {
               method_jC(method_jB(a), (boolean)(3790 ^ -26927 ^ 1451 ^ -25164));
            } else {
               method_jE(method_jD(a), (boolean)(14927 ^ -6160 ^ 21454 ^ -29071));
            }
         } else {
            method_jG(method_jF(a), (boolean)(2225 ^ -28721 ^ 17439 ^ -15519));
            if (method_jH(this).equals(method_jI())) {
               method_jK(method_jJ(a), (boolean)(27754 ^ -9916 ^ 14842 ^ -29483));
            } else {
               method_jM(method_jL(a), (boolean)(25325 ^ -29914 ^ 20363 ^ -22975));
            }
         }

      }
   }

   private static int method_jw(0G var0) {
      return var0.field_b;
   }

   private static 0f method_jm(0a var0) {
      return var0.player;
   }

   private static Ig method_iZ(0G var0) {
      return var0.field_a;
   }

   private static double method_jl(0f var0) {
      return var0.posX;
   }

   private static 0F method_jA() {
      return 0F.field_b;
   }

   private static double method_jd(Ig var0) {
      return var0.posZ;
   }

   private static 0f method_ju(0a var0) {
      return var0.player;
   }

   private static void method_jv(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0f method_jo(0a var0) {
      return var0.player;
   }

   private static 0g method_jJ(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_jF(0a var0) {
      return var0.keyboard;
   }

   private static Ig method_ja(0G var0) {
      return var0.field_a;
   }

   private static Ig method_iY(0G var0) {
      return var0.field_a;
   }

   private static 0f method_jk(0a var0) {
      return var0.player;
   }

   private static void method_jt(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static Ig method_je(0G var0) {
      return var0.field_a;
   }

   private static double method_jn(0f var0) {
      return var0.posY;
   }

   private static double method_jb(Ig var0) {
      return var0.posX;
   }

   public void method_dm() {
      if (method_iY(this) == null) {
         this.method_dt(method_iX("Ȏʜ˲Ȏˍ˫ˆ˝ʄ˯˜ˇˊ˝ˀˆˇȎʜ˴ʉȎʞ˫ˆ˝ʉ") + this.method_dp().getNickname() + method_iX("ʉ˕ʉ˼ˇ˂ˇˆ˞ˇʉ˝ˈ˛ˎˌ˝ʈ"));
         this.method_ds().method_cR((0u)null);
      }

   }

   public _G/* $FF was: 0G*/(0a a, String b, int c, 0F d) {
      super(a, method_iX("˺˙ˀˇ"));
      if (b.equalsIgnoreCase(this.method_dp().getNickname())) {
         this.method_dt(method_iX("Ȏʜ˲Ȏˍ˫ˆ˝ʄ˯˜ˇˊ˝ˀˆˇȎʜ˴ʉȎʞ˫ˆ˝ʉ") + a.getNickname() + method_iX("ʉ˕ʉˠˇ˟ˈˀ˅ˍʉ˝ˈ˛ˎˌ˝ʉˌˇ˝ˀ˝ːʈ"));
         this.method_ds().method_cR((0u)null);
      } else {
         this.field_b = c;
         this.field_a = (Ig)(b.equalsIgnoreCase(method_iX("˩˄ˌ")) ? nC.player : 0X.getByName(b));
         this.field_c = d;
      }
   }

   private static void method_jG(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static double method_jh(Ig var0) {
      return var0.posY;
   }

   private static 0F method_jz(0G var0) {
      return var0.field_c;
   }

   private static 0f method_js(0a var0) {
      return var0.player;
   }

   private static double method_jf(Ig var0) {
      return var0.posX;
   }

   private static 0g method_jx(0a var0) {
      return var0.keyboard;
   }

   private static void method_jy(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static Ig method_jc(0G var0) {
      return var0.field_a;
   }

   private static void method_jE(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static float method_jr(Vector2f var0) {
      return var0.x;
   }
}
