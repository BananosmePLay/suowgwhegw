package neo;

import javax.vecmath.Vector2f;

public class 0y extends 0u {
   public Ig field_a = null;
   private static int _DSC GG NEOWARECLIENT _;

   private static double method_fB(Ig var0) {
      return var0.posZ;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_ft(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 2922 ^ -32681 ^ 4865 ^ -26564; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21933 ^ -9164 ^ 340 ^ -28887));
      }

      return var1.toString();
   }

   private static 0g method_fS(0a var0) {
      return var0.keyboard;
   }

   private static Ig method_fI(0y var0) {
      return var0.field_a;
   }

   private static 0f method_fG(0a var0) {
      return var0.player;
   }

   private static double method_fL(Ig var0) {
      return var0.posZ;
   }

   private static Ig method_fw(0y var0) {
      return var0.field_a;
   }

   private static Ig method_fu(0y var0) {
      return var0.field_a;
   }

   private static Ig method_fy(0y var0) {
      return var0.field_a;
   }

   private static void method_fP(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static float method_fN(Vector2f var0) {
      return var0.x;
   }

   private static double method_fD(0f var0) {
      return var0.posX;
   }

   private static 0f method_fE(0a var0) {
      return var0.player;
   }

   private static float method_fM(Vector2f var0) {
      return var0.y;
   }

   private static 0f method_fO(0a var0) {
      return var0.player;
   }

   private static void method_fT(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static Ig method_fA(0y var0) {
      return var0.field_a;
   }

   public _y/* $FF was: 0y*/(0a a, String b) {
      super(a, method_ft("ޢދވވދޓ"));
      if (b.equalsIgnoreCase(this.method_dp().getNickname())) {
         this.method_dt(method_ft("݃ߑ\u07bf݃ހަދސ߉ޢޑފއސލދފ݃ߑ\u07b9߄݃ߓަދސ߄") + a.getNickname() + method_ft("߄ޘ߄ޭފޒޅލވހ߄ސޅޖރށސ߄ށފސލސޝ߅"));
         this.method_ds().method_cR((0u)null);
      } else {
         this.field_a = (Ig)(b.equalsIgnoreCase(method_ft("ޤމށ")) ? nC.player : 0X.getByName(b));
      }
   }

   private static double method_fz(Ig var0) {
      return var0.posY;
   }

   private static void method_fR(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static double method_fF(0f var0) {
      return var0.posY;
   }

   private static double method_fJ(Ig var0) {
      return var0.posX;
   }

   private static 0f method_fC(0a var0) {
      return var0.player;
   }

   public void method_dm() {
      if (method_fu(this) == null) {
         this.method_dt(method_ft("݃ߑ\u07bf݃ހަދސ߉ޢޑފއސލދފ݃ߑ\u07b9߄݃ߓަދސ߄") + this.method_dp().getNickname() + method_ft("߄ޘ߄ޱފޏފދޓފ߄ސޅޖރށސ߅"));
         this.method_ds().method_cR((0u)null);
      }

   }

   public void method_do() {
      super.method_do();
   }

   private static Ig method_fK(0y var0) {
      return var0.field_a;
   }

   private static 0f method_fQ(0a var0) {
      return var0.player;
   }

   private static double method_fH(0f var0) {
      return var0.posZ;
   }

   public void method_dn() {
      if (method_fv(this) != null) {
         0a a = this.method_dp();
         Vector2f b = 0X.getBlockAngles(method_fx(method_fw(this)), method_fz(method_fy(this)) + Double.longBitsToDouble(-1208105481696113927L ^ -3396854900598174983L), method_fB(method_fA(this)), method_fD(method_fC(a)), method_fF(method_fE(a)), method_fH(method_fG(a)));
         float c = 0X.getDistance(a, method_fJ(method_fI(this)), method_fL(method_fK(this)));
         float d = 0X.normalizeYaw(method_fM(b));
         float e = 0X.normalizePitch(method_fN(b));
         if (!Float.isNaN(d) && !Float.isNaN(e)) {
            method_fP(method_fO(a), d);
            method_fR(method_fQ(a), e);
         }

         method_fT(method_fS(a), (boolean)(c > Float.intBitsToFloat('鵖' ^ 259070 ^ '\ue470' ^ 1610193208 ^ 29886 ^ 223819 ^ '\uf7ca' ^ 536476383) ? 21848 ^ -5561 ^ 11675 ^ -28027 : 25819 ^ -14808 ^ 31906 ^ -8623));
      }
   }

   private static double method_fx(Ig var0) {
      return var0.posX;
   }

   private static Ig method_fv(0y var0) {
      return var0.field_a;
   }
}
