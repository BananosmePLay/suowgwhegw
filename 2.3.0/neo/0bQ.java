package neo;

import com.google.common.eventbus.Subscribe;
import net.minecraft.inventory.Container;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;

public class 0bQ extends 0dr {
   public double field_c;
   public double field_a;
   public double field_b;
   private static int _DSC GG NEOWARECLIENT _;

   private static jh method_Ug() {
      return nC.player;
   }

   private static MovementInput method_UD(jh var0) {
      return var0.movementInput;
   }

   private static jh method_Ut() {
      return nC.player;
   }

   private static void method_UN(jh var0, double var1) {
      var0.motionZ = var1;
   }

   private static double method_UU(0bQ var0) {
      return var0.field_a;
   }

   private static void method_UB(jh var0, boolean var1) {
      var0.noClip = var1;
   }

   private static boolean method_Ux(Bl var0) {
      return var0.pressed;
   }

   public void method_bAU() {
      super.method_bAU();
      if (method_TE(method_TD())) {
         this.method_bBi();
      }

      method_TH(this, method_TG(method_TF()));
      method_TK(this, method_TJ(method_TI()));
      method_TN(this, method_TM(method_TL()));
      jg a = new jg(method_TP(method_TO()), method_TQ().getGameProfile());
      method_TT(a, method_TS(method_TR()));
      method_TW(a, method_TV(method_TU()));
      a.setHealth(method_TX().getHealth());
      a.setPositionAndRotation(method_TY(this), method_Ua(method_TZ().getEntityBoundingBox()), method_Ub(this), method_Ud(method_Uc()), method_Uf(method_Ue()));
      method_Ui(a, method_Uh(method_Ug()));
      method_Uk(method_Uj()).addEntityToWorld(-11950 ^ -29209 ^ 10065 ^ -31717, a);
   }

   public void method_Tz(double a) {
      float b = method_UE(method_UD(method_UC()));
      float c = method_UH(method_UG(method_UF()));
      float d = method_UJ(method_UI());
      if (b == Float.intBitsToFloat(117496 ^ 113320 ^ 4601 ^ 1327540952 ^ 123798 ^ 105867 ^ 8350 ^ 1327535602) && c == Float.intBitsToFloat(10093 ^ '똸' ^ 23374 ^ -2083433684 ^ 20797 ^ 30003 ^ 16012 ^ -2083421259)) {
         method_UL(method_UK(), Double.longBitsToDouble(529046901627268406L ^ 529046901627268406L));
         method_UN(method_UM(), Double.longBitsToDouble(-5971545816241165780L ^ -5971545816241165780L));
      } else if (b != Float.intBitsToFloat(122085 ^ 32420 ^ 129054 ^ -788098160 ^ 8528 ^ 24383 ^ 4613 ^ -788093531)) {
         if (c >= Float.intBitsToFloat('\uab00' ^ 497823 ^ '\ue2cb' ^ 1120657667 ^ 30354 ^ 493952 ^ 2317 ^ 2102117960)) {
            d += (float)(b > Float.intBitsToFloat(4770 ^ 100823 ^ 129115 ^ -31019420 ^ 101186 ^ '脕' ^ 111784 ^ -31034443) ? -6835 ^ -11649 ^ 21278 ^ -25615 : 5733 ^ -25744 ^ 23709 ^ -11861);
            c = Float.intBitsToFloat('\udccc' ^ 231617 ^ 'ﷅ' ^ -1181355510 ^ '襓' ^ 28963 ^ 'ꃪ' ^ -1181351080);
         } else if (c <= Float.intBitsToFloat(110507 ^ 117097 ^ 18671 ^ -1660625674 ^ 120431 ^ 129695 ^ 24895 ^ 579125012)) {
            d += (float)(b > Float.intBitsToFloat(812 ^ '逍' ^ '趕' ^ 71262806 ^ '갣' ^ 83869 ^ 27217 ^ 71236877) ? 3292 ^ -2693 ^ 7084 ^ -7640 : -5999 ^ -12735 ^ 4523 ^ -14170);
            c = Float.intBitsToFloat('궃' ^ '\uead4' ^ 28627 ^ -2104469173 ^ 'ﳐ' ^ 2037427 ^ 26415 ^ -2104492925);
         }

         if (b > Float.intBitsToFloat(28000 ^ 29532 ^ 8927 ^ -1987436602 ^ 30432 ^ 248516 ^ 31506 ^ -1987451885)) {
            b = Float.intBitsToFloat(22004 ^ 100001 ^ 12826 ^ -116472164 ^ '뼌' ^ 109942 ^ '쀮' ^ -963709561);
         } else if (b < Float.intBitsToFloat(114689 ^ 85352 ^ 13109 ^ 732193872 ^ 11646 ^ 110005 ^ 126871 ^ 732214608)) {
            b = Float.intBitsToFloat('\uddae' ^ '鴦' ^ 10151 ^ -128186279 ^ '\ue9b3' ^ '魕' ^ 5431 ^ 1205602471);
         }
      }

      double e = Math.cos(Math.toRadians((double)(d + Float.intBitsToFloat(114883 ^ 90105 ^ 14661 ^ -1284346944 ^ 122573 ^ 977 ^ 122015 ^ -238629828))));
      double f = Math.sin(Math.toRadians((double)(d + Float.intBitsToFloat(1511 ^ 1015947 ^ 17859 ^ -1794638949 ^ 'ꎂ' ^ 21750 ^ '쪴' ^ -675543308))));
      method_UP(method_UO(), (double)b * a * e + (double)c * a * f);
      method_UR(method_UQ(), (double)b * a * f - (double)c * a * e);
   }

   private static float method_UE(MovementInput var0) {
      return var0.moveForward;
   }

   private static jh method_US() {
      return nC.player;
   }

   private static jh method_Uy() {
      return nC.player;
   }

   private static double method_TY(0bQ var0) {
      return var0.field_b;
   }

   private static jh method_TR() {
      return nC.player;
   }

   private static void method_TH(0bQ var0, double var1) {
      var0.field_b = var1;
   }

   private static boolean method_Vf(jh var0) {
      return var0.onGround;
   }

   private static pm method_TP(nC var0) {
      return var0.world;
   }

   private static jh method_TI() {
      return nC.player;
   }

   private static jh method_UY() {
      return nC.player;
   }

   private static void method_TK(0bQ var0, double var1) {
      var0.field_a = var1;
   }

   private static jh method_Ue() {
      return nC.player;
   }

   private static Bj method_Uv() {
      return nC.gameSettings;
   }

   private static double method_UZ(jh var0) {
      return var0.posX;
   }

   private static double method_TJ(jh var0) {
      return var0.posY;
   }

   private static double method_Ub(0bQ var0) {
      return var0.field_c;
   }

   private static jh method_TQ() {
      return nC.player;
   }

   private static float method_Uh(jh var0) {
      return var0.rotationYawHead;
   }

   private static float method_UJ(jh var0) {
      return var0.rotationYaw;
   }

   private static double method_UT(0bQ var0) {
      return var0.field_b;
   }

   public _bQ/* $FF was: 0bQ*/() {
      super(method_TA("ԿԋԜԜԺԘԔ"), 0dz.field_f, 28299 ^ -8448 ^ 21373 ^ -7434);
   }

   private static jh method_TD() {
      return nC.player;
   }

   private static double method_TM(jh var0) {
      return var0.posZ;
   }

   private static jh method_TX() {
      return nC.player;
   }

   private static nC method_TO() {
      return mc;
   }

   private static double method_Vd(jh var0) {
      return var0.posZ;
   }

   private static Bl method_Ur(Bj var0) {
      return var0.keyBindJump;
   }

   private static jh method_TB() {
      return nC.player;
   }

   public void method_bAV() {
      super.method_bAV();
      method_US().setPosition(method_UT(this), method_UU(this), method_UV(this));
      if (method_UW().getConnection() != null) {
         method_UX().getConnection().sendPacket(new SV(method_UZ(method_UY()), method_Vb(method_Va()) + Double.longBitsToDouble(-3594610673786197645L ^ -1037769366675161848L), method_Vd(method_Vc()), method_Vf(method_Ve())));
      }

      method_Vi(method_Vh(method_Vg()), (boolean)(23303 ^ -11715 ^ 4020 ^ -31090));
      method_Vk(method_Vj(), (boolean)(2621 ^ -2097 ^ 13755 ^ -14263));
      method_Vm(method_Vl()).removeEntityFromWorld(-20750 ^ -10099 ^ 18342 ^ -12762);
   }

   private static ML method_Vh(jh var0) {
      return var0.capabilities;
   }

   private static jh method_UO() {
      return nC.player;
   }

   private static Bj method_Uq() {
      return nC.gameSettings;
   }

   private static boolean method_Us(Bl var0) {
      return var0.pressed;
   }

   private static jh method_Uo() {
      return nC.player;
   }

   private static void method_UP(jh var0, double var1) {
      var0.motionX = var1;
   }

   private static void method_TW(jg var0, Container var1) {
      var0.inventoryContainer = var1;
   }

   @Subscribe
   public void method_Ty(0dm a) {
      if (method_Ul() != null && method_Un(method_Um()) != null) {
         method_Up(method_Uo(), Double.longBitsToDouble(-7619315436508230056L ^ -7619315436508230056L));
         if (method_Us(method_Ur(method_Uq()))) {
            method_Uu(method_Ut(), Double.longBitsToDouble(-2852357277801282734L ^ -1757982568350252206L));
         }

         if (method_Ux(method_Uw(method_Uv()))) {
            method_Uz(method_Uy(), Double.longBitsToDouble(-1366297701245690704L ^ 5979073290995588272L));
         }

         method_UB(method_UA(), (boolean)(19060 ^ -17573 ^ 4 ^ -3798));
         this.method_Tz(Double.longBitsToDouble(-2419249550579290981L ^ -2189565969583395685L));
      }
   }

   private static double method_Ua(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static boolean method_TE(jh var0) {
      return var0.isDead;
   }

   private static jh method_Ul() {
      return nC.player;
   }

   private static jh method_UM() {
      return nC.player;
   }

   private static void method_Uz(jh var0, double var1) {
      var0.motionY = var1;
   }

   private static jh method_Uc() {
      return nC.player;
   }

   private static float method_Uf(jh var0) {
      return var0.rotationPitch;
   }

   private static pm method_Vm(nC var0) {
      return var0.world;
   }

   private static jh method_TZ() {
      return nC.player;
   }

   private static pm method_Un(nC var0) {
      return var0.world;
   }

   private static void method_Uu(jh var0, double var1) {
      var0.motionY = var1;
   }

   private static float method_Ud(jh var0) {
      return var0.rotationYaw;
   }

   private static jh method_Vg() {
      return nC.player;
   }

   private static jh method_UA() {
      return nC.player;
   }

   private static void method_Ui(jg var0, float var1) {
      var0.rotationYawHead = var1;
   }

   private static double method_UV(0bQ var0) {
      return var0.field_c;
   }

   private static boolean method_TC(jh var0) {
      return var0.isDead;
   }

   private static jh method_Va() {
      return nC.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_TA(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7500 ^ -25675 ^ 2230 ^ -29105; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2771 ^ -3187 ^ 27340 ^ -26901));
      }

      return var1.toString();
   }

   private static void method_Up(jh var0, double var1) {
      var0.motionY = var1;
   }

   private static jh method_TF() {
      return nC.player;
   }

   private static float method_UH(MovementInput var0) {
      return var0.moveStrafe;
   }

   private static void method_TT(jg var0, MJ var1) {
      var0.inventory = var1;
   }

   private static jh method_Vc() {
      return nC.player;
   }

   private static MovementInput method_UG(jh var0) {
      return var0.movementInput;
   }

   private static nC method_Uj() {
      return mc;
   }

   private static double method_Vb(jh var0) {
      return var0.posY;
   }

   private static nC method_UW() {
      return mc;
   }

   private static jh method_UI() {
      return nC.player;
   }

   private static jh method_UF() {
      return nC.player;
   }

   private static void method_Vi(ML var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static pm method_Uk(nC var0) {
      return var0.world;
   }

   private static nC method_UX() {
      return mc;
   }

   private static void method_UR(jh var0, double var1) {
      var0.motionZ = var1;
   }

   private static void method_Vk(jh var0, boolean var1) {
      var0.noClip = var1;
   }

   private static double method_TG(jh var0) {
      return var0.posX;
   }

   private static nC method_Vl() {
      return mc;
   }

   private static void method_UL(jh var0, double var1) {
      var0.motionX = var1;
   }

   private static jh method_TL() {
      return nC.player;
   }

   private static Container method_TV(jh var0) {
      return var0.inventoryContainer;
   }

   private static jh method_Vj() {
      return nC.player;
   }

   private static void method_TN(0bQ var0, double var1) {
      var0.field_c = var1;
   }

   private static jh method_TU() {
      return nC.player;
   }

   @Subscribe
   public void method_Tx(0dg a) {
      if (method_TC(method_TB())) {
         this.method_bBi();
      }

   }

   private static jh method_UC() {
      return nC.player;
   }

   private static nC method_Um() {
      return mc;
   }

   private static jh method_UK() {
      return nC.player;
   }

   private static jh method_Ve() {
      return nC.player;
   }

   private static MJ method_TS(jh var0) {
      return var0.inventory;
   }

   private static jh method_UQ() {
      return nC.player;
   }

   private static Bl method_Uw(Bj var0) {
      return var0.keyBindSneak;
   }
}
