package neo;

import net.minecraft.util.math.BlockPos;

public class 0x extends 0u {
   public 0ei field_b;
   public final BlockPos field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static BlockPos method_eK(0x var0) {
      return var0.field_a;
   }

   public void method_do() {
      super.method_do();
   }

   private static 0g method_eW(0a var0) {
      return var0.keyboard;
   }

   private static void method_fd(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0ei method_fh(0x var0) {
      return var0.field_b;
   }

   private static 0bo method_fq(0a var0) {
      return var0.world;
   }

   private static 0f method_fb(0a var0) {
      return var0.player;
   }

   private static void method_fj(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static double method_fn(0f var0) {
      return var0.posY;
   }

   private static 0g method_eU(0a var0) {
      return var0.keyboard;
   }

   private static BlockPos method_eN(0x var0) {
      return var0.field_a;
   }

   private static 0f method_eR(0a var0) {
      return var0.player;
   }

   private static void method_eM(0x var0, 0ei var1) {
      var0.field_b = var1;
   }

   public void method_dm() {
      if (method_eK(this) == null) {
         this.method_dt(method_eJ("\u0003\u0091ÿ\u0003ÀæËÐ\u0089âÑÊÇÐÍËÊ\u0003\u0091ù\u0084\u0003\u0093æËÐ\u0084") + this.method_dp().getNickname() + method_eJ("\u0084Ø\u0084ñÊÏÊËÓÊ\u0084ÔË×ÍÐÍËÊ\u0085"));
         this.method_ds().method_cR((0u)null);
      } else {
         method_eL(this.method_dp()).jump();
         method_eM(this, new 0ei());
      }
   }

   private static 0ct method_fg() {
      return 0bG.field_b;
   }

   private static 0f method_fa(0a var0) {
      return var0.player;
   }

   private static 0f method_eS(0a var0) {
      return var0.player;
   }

   private static void method_eX(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static void method_eV(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static 0bo method_fr(0a var0) {
      return var0.world;
   }

   private static 0f method_fk(0a var0) {
      return var0.player;
   }

   private static 0f method_eQ(0a var0) {
      return var0.player;
   }

   private static void method_ff(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static float method_fc(0f var0) {
      return var0.rotationYaw;
   }

   private static 0f method_fe(0a var0) {
      return var0.player;
   }

   private double method_eI() {
      double c = method_fl(method_fk(this.method_dp()));
      double d = method_fn(method_fm(this.method_dp()));
      double e = method_fp(method_fo(this.method_dp()));

      for(int b = (int)d; b > 0; --b) {
         BlockPos a = new BlockPos(c, (double)b, e);
         if (!method_fq(this.method_dp()).getChunk(a).isLoaded()) {
            return Double.longBitsToDouble(5120414543780342700L ^ 531633551582784428L);
         }

         if (!method_fr(this.method_dp()).getBlockState(new BlockPos(c, (double)b, e)).getBlock().equals(method_fs())) {
            return d - (double)b;
         }
      }

      return Double.longBitsToDouble(2321792215135066434L ^ 6942098404724218178L);
   }

   private static 0f method_fm(0a var0) {
      return var0.player;
   }

   private static BlockPos method_eY(0x var0) {
      return var0.field_a;
   }

   private static double method_fp(0f var0) {
      return var0.posZ;
   }

   private static co method_fs() {
      return Nk.AIR;
   }

   private static 0f method_eZ(0a var0) {
      return var0.player;
   }

   private static double method_eT(0f var0) {
      return var0.motionY;
   }

   private static 0f method_fi(0a var0) {
      return var0.player;
   }

   private static 0f method_eL(0a var0) {
      return var0.player;
   }

   private static BlockPos method_eO(0x var0) {
      return var0.field_a;
   }

   private static BlockPos method_eP(0x var0) {
      return var0.field_a;
   }

   private static double method_fl(0f var0) {
      return var0.posX;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_eJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18103 ^ -29783 ^ 20826 ^ -25532; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30099 ^ -24428 ^ 3082 ^ -9815));
      }

      return var1.toString();
   }

   private static 0f method_fo(0a var0) {
      return var0.player;
   }

   public _x/* $FF was: 0x*/(0a a, BlockPos b) {
      super(a, method_eJ("åÑÐËôÍÈËÐ"));
      this.field_a = b;
   }

   public void method_dn() {
      if (method_eN(this) != null) {
         0a b = this.method_dp();
         float c = 0X.getDistance(b, (double)method_eO(this).getX(), (double)method_eP(this).getZ());
         if (!method_eQ(b).isElytraFlying() && c < Float.intBitsToFloat(259628 ^ 213590 ^ 11980 ^ -121800361 ^ 254439 ^ 12841 ^ 256453 ^ -1189226006)) {
            this.method_ds().method_cR((0u)null);
         } else {
            if (!method_eR(b).isElytraFlying() && method_eT(method_eS(b)) < Double.longBitsToDouble(172401247635868343L ^ 172401247635868343L) && c > Float.intBitsToFloat(13689 ^ 491587 ^ 5010 ^ -1455163406 ^ 16186 ^ 502751 ^ 6127 ^ -387722672)) {
               method_eV(method_eU(b), (boolean)(9795 ^ -3714 ^ 31789 ^ -21743));
            } else {
               method_eX(method_eW(b), (boolean)(30817 ^ -31065 ^ 11711 ^ -11399));
               float[] a = 0I.method_jP(method_eY(this), method_eZ(b));
               method_fd(method_fa(b), 0X.normalizeYaw(0I.method_jQ(method_fc(method_fb(b)), a[1658 ^ -2192 ^ 18026 ^ -18592], Float.intBitsToFloat(31618 ^ 30559 ^ 1938 ^ -941018666 ^ '댙' ^ 214593 ^ 9911 ^ -104996021), Float.intBitsToFloat(25140 ^ 2091058 ^ 13748 ^ 588227346 ^ 126088 ^ 125805 ^ 5942 ^ 475840702))));
               method_ff(method_fe(b), Float.intBitsToFloat(28403 ^ 90492 ^ 120334 ^ -2010879540 ^ 104159 ^ 104080 ^ 12692 ^ 1235517846));
               if (c > Float.intBitsToFloat(251626 ^ 244328 ^ 5848 ^ -1608404724 ^ 250978 ^ 244820 ^ 5118 ^ -511594338) && this.method_eI() < (double)method_fg().method_bnH() && method_fh(this).hasReached(3708 ^ 4496 ^ 7054 ^ 946)) {
                  b.useItem();
                  method_fj(method_fi(b), Float.intBitsToFloat('蝙' ^ 8337933 ^ 8153 ^ 901756700 ^ 11531 ^ 8332889 ^ '밉' ^ -197156150));
               }
            }

         }
      }
   }
}
