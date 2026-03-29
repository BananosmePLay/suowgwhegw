package neo;

import javax.vecmath.Vector2f;
import net.minecraft.util.math.BlockPos;

public class 0z extends 0u {
   public final BlockPos field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static void method_gp(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static BlockPos method_fY(0z var0) {
      return var0.field_a;
   }

   public _z/* $FF was: 0z*/(0a a, BlockPos b) {
      super(a, method_fU("ְְֳֳ֙֨֏ְ֬"));
      this.field_a = b;
   }

   private static float method_gj(Vector2f var0) {
      return var0.x;
   }

   private static 0f method_gm(0a var0) {
      return var0.player;
   }

   private static BlockPos method_fW(0z var0) {
      return var0.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_fU(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 20963 ^ -6963 ^ 16895 ^ -2863; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ '蟛' ^ -32427 ^ '\ude40' ^ -8943));
      }

      return var1.toString();
   }

   private static double method_gf(0f var0) {
      return var0.posZ;
   }

   public void method_do() {
      super.method_do();
   }

   private static 0f method_gk(0a var0) {
      return var0.player;
   }

   private static 0g method_go(0a var0) {
      return var0.keyboard;
   }

   private static void method_gl(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static BlockPos method_gh(0z var0) {
      return var0.field_a;
   }

   private static 0f method_ge(0a var0) {
      return var0.player;
   }

   private static BlockPos method_fX(0z var0) {
      return var0.field_a;
   }

   private static BlockPos method_fZ(0z var0) {
      return var0.field_a;
   }

   private static 0f method_gc(0a var0) {
      return var0.player;
   }

   private static BlockPos method_gg(0z var0) {
      return var0.field_a;
   }

   private static double method_gb(0f var0) {
      return var0.posX;
   }

   public void method_dm() {
      if (method_fV(this) == null) {
         this.method_dt(method_fU("ոתքոְֻ֝֫ײְֱֱֶּ֪֙֫ոתւ\u05ffոרְ֝֫\u05ff") + this.method_dp().getNickname() + method_fU("\u05ff֣\u05ff֊ְֱֱֱִ֨\u05ffְְֱֶֶ֯֬֫\u05fe"));
         this.method_ds().method_cR((0u)null);
      }

   }

   private static 0f method_ga(0a var0) {
      return var0.player;
   }

   public void method_dn() {
      if (method_fW(this) != null) {
         0a a = this.method_dp();
         Vector2f b = 0X.getBlockAngles((double)method_fX(this).getX() + Double.longBitsToDouble(4899284792886028364L ^ 8943517258264733772L), (double)(method_fY(this).getY() + (22373 ^ -31583 ^ 941 ^ -12184)), (double)method_fZ(this).getZ() + Double.longBitsToDouble(5567547074245330885L ^ 8260699651412887493L), method_gb(method_ga(a)), method_gd(method_gc(a)), method_gf(method_ge(a)));
         float c = 0X.getDistance(a, (double)method_gg(this).getX() + Double.longBitsToDouble(-3706212824604058898L ^ -904973856379610386L), (double)method_gh(this).getZ() + Double.longBitsToDouble(4299009277765706317L ^ 308820007915446861L));
         float d = 0X.normalizeYaw(method_gi(b));
         float e = 0X.normalizePitch(method_gj(b));
         if (!Float.isNaN(d) && !Float.isNaN(e)) {
            method_gl(method_gk(a), d);
            method_gn(method_gm(a), e);
         }

         method_gp(method_go(a), (boolean)(24788 ^ -31331 ^ 28485 ^ -30195));
         if ((double)c < Double.longBitsToDouble(-4366664807807672274L ^ -238720571402798668L)) {
            this.method_ds().method_cR((0u)null);
         }

      }
   }

   private static void method_gn(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static double method_gd(0f var0) {
      return var0.posY;
   }

   private static float method_gi(Vector2f var0) {
      return var0.y;
   }

   private static BlockPos method_fV(0z var0) {
      return var0.field_a;
   }
}
