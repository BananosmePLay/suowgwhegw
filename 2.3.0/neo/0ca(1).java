package neo;

import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import net.minecraft.util.ResourceLocation;

public class 0ca extends 0dr {
   public final 0cp field_b = new 0cp(method_Xj("įĂČğĉ"), (boolean)(4228 ^ -24036 ^ 16705 ^ -3112));
   public final 0cp field_a;
   public final 0cp field_c;
   private static String _DSC GG NEOWARECLIENT _;

   private static jh method_Xs() {
      return nC.player;
   }

   private static jH method_Xm(nC var0) {
      return var0.fontRenderer;
   }

   private static jh method_Xq() {
      return nC.player;
   }

   private static jh method_Xt() {
      return nC.player;
   }

   private static ArrayList method_Xv() {
      return 0bC.field_c;
   }

   private static String method_Xp(pf var0) {
      return var0.serverIP;
   }

   private static 0dY method_Xy() {
      return 0dZ.field_f;
   }

   private static nC method_Xl() {
      return mc;
   }

   public _ca/* $FF was: 0ca*/() {
      super(method_Xj("ĥĸĩ"), 0dz.field_g, 12640 ^ -22126 ^ 17236 ^ -9306);
      String var10003 = method_Xj("ĥĄĉĈōĤĽ");
      int var10004 = 22912 ^ -20422 ^ 19572 ^ -23090;
      0cp var10005 = this.field_b;
      var10005.getClass();
      this.field_c = new 0cp(var10003, (boolean)var10004, var10005::method_bna);
      this.field_a = new 0cp(method_Xj("ġČĊĩĈęĈĎęĂğ"), (boolean)(29546 ^ -7262 ^ 18404 ^ -10451));
      0cv[] var10001 = new 0cv[7236 ^ -15840 ^ 13476 ^ -5437];
      var10001[4664 ^ -21454 ^ 5087 ^ -21035] = this.field_b;
      var10001[5425 ^ -12523 ^ 6660 ^ -16351] = this.field_c;
      var10001[10112 ^ -21913 ^ 23985 ^ -12204] = this.field_a;
      this.method_bBe(var10001);
   }

   private static 0cp method_Xk(0ca var0) {
      return var0.field_b;
   }

   private static 0cp method_Xw(0ca var0) {
      return var0.field_a;
   }

   private static nC method_Xx() {
      return mc;
   }

   private static jh method_Xr() {
      return nC.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Xj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 17912 ^ -5683 ^ 4163 ^ -17290; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7788 ^ -13124 ^ 5198 ^ -14349));
      }

      return var1.toString();
   }

   private static HashMap method_Xu() {
      return 0bq.field_b;
   }

   private static 0cp method_Xn(0ca var0) {
      return var0.field_c;
   }

   private Color method_Xi() {
      return 0em.method_bGa(new Color(27450 ^ -29899 ^ 14957 ^ -9571, 13239 ^ -7226 ^ 27498 ^ -17573, 23519 ^ -21980 ^ 16510 ^ -20027), new Color(8768 ^ -24414 ^ 29974 ^ -2293, 7165 ^ -43907 ^ '鮱' ^ -11199, 6466 ^ -8039 ^ 11595 ^ -11040, 31857 ^ -13831 ^ 29376 ^ -14409), Double.longBitsToDouble(-6736638089631203007L ^ -2119322571669601983L), 22424 ^ -12644 ^ 5325 ^ -29239);
   }

   private static nC method_Xo() {
      return mc;
   }

   @Subscribe
   public void method_Xh(0dn d) {
      try {
         mC c = d.method_bAv();
         if (method_Xk(this).method_bna()) {
            Runtime a = Runtime.getRuntime();
            jH b = method_Xm(method_Xl());
            b.drawString(method_Xj("ǊĉǊāģĈĂǊċǊāĺČğĈō") + 0dH.method_bDy(), 8190 ^ -16298 ^ 13689 ^ -5413, 13210 ^ -6384 ^ 29912 ^ -24522, -3416 ^ -21012 ^ 29980 ^ -10841);
            b.drawString(method_Xj("ǊċǊāīĝĞŗōǊĉǊā") + nC.getDebugFPS(), 31838 ^ -2208 ^ 24900 ^ -5520, 29498 ^ -9704 ^ 21389 ^ -1343, -9779 ^ -22679 ^ 19326 ^ -13787);
            b.drawString(method_Xj("ǊċǊāĤĽŗōǊĉǊā") + (method_Xn(this).method_bna() ? method_Xj("ĥĄĉĉĈă") : (method_Xo().isSingleplayer() ? method_Xj("ġĂĎČāąĂĞę") : method_Xp((pf)Objects.requireNonNull(nC.getMinecraft().getCurrentServerData())))), 24467 ^ -4792 ^ 4091 ^ -17110, 1694 ^ -12671 ^ 2553 ^ -15970, -20337 ^ -18836 ^ 13761 ^ -13091);
            StringBuilder var10001 = (new StringBuilder()).append(method_Xj("ǊċǊāĹĽľŗōǊĉǊā"));
            String var10002 = method_Xj("ňŃşċ");
            Object[] var10003 = new Object[3872 ^ -3398 ^ 520 ^ -109];
            var10003[24332 ^ -6880 ^ 11272 ^ -27100] = 0ej.method_bFw();
            b.drawString(var10001.append(String.format(var10002, var10003)).toString(), 12247 ^ -27861 ^ 27927 ^ -11807, 31409 ^ -32518 ^ 17918 ^ -16585, -12842 ^ -15255 ^ 20965 ^ -22619);
            b.drawString(method_Xj("ǊċǊāįğČăĉŗōǊĉǊā") + (method_Xq().getServerBrand() != null && method_Xr().getServerBrand().split(method_Xj("ō")).length >= (22163 ^ -32347 ^ 1932 ^ -12104) ? method_Xs().getServerBrand().split(method_Xj("ō"))[25766 ^ -13934 ^ 25246 ^ -12374] : method_Xt().getServerBrand()), 29492 ^ -28688 ^ 32543 ^ -31791, 9680 ^ -989 ^ 5508 ^ -13061, -5657 ^ -19219 ^ 19701 ^ -4608);
            b.drawString(method_Xj("ǊċǊāġČĊōĩĈęĈĎęĂğŗō") + 0ej.method_bFz() + method_Xj("ĀĞ"), 13056 ^ -8607 ^ 6225 ^ -2758, 22747 ^ -30695 ^ 30098 ^ -23098, -32422 ^ -8350 ^ 6136 ^ -18881);
            b.drawString(method_Xj("ǊċǊāġĂČĉĈĉōĽğĂĕĄĈĞŗōǊĉǊā") + 0cG.method_bwd().method_bwe().size(), 26229 ^ -13067 ^ 28266 ^ -15136, 11790 ^ -11494 ^ 1200 ^ -1788, -29523 ^ -9437 ^ 7076 ^ -19499);
            b.drawString(method_Xj("ǊċǊāįĂęĞōĮĂăăĈĎęĈĉŗōǊĉǊā") + 0e.getOnline().size() + method_Xj("ł") + 0e.getBots().size(), 24951 ^ -16918 ^ 2590 ^ -10615, 18265 ^ -17188 ^ 21390 ^ -22367, -14359 ^ -25087 ^ 5955 ^ -20140);
            b.drawString(method_Xj("ǊċǊāĿĬĠōĸĞČĊĈŗōǊĉǊā") + (a.totalMemory() - a.freeMemory()) / 1048576L + method_Xj("ł") + a.maxMemory() / 1048576L, 11216 ^ -14515 ^ 13868 ^ -9541, 2744 ^ -14681 ^ 17752 ^ -30221, -6581 ^ -3539 ^ 21366 ^ -18193);
            b.drawString(method_Xj("ǊċǊāĠČăĘČāĥĈāĝĈğŗōǊĉǊā") + 0cG.method_bwd().method_bwg().method_i().method_bA(), 8314 ^ -8930 ^ 16519 ^ -16919, 26864 ^ -14231 ^ 13556 ^ -27437, -7171 ^ -25671 ^ 20145 ^ -14070);
            b.drawString(method_Xj("ǊċǊāĮČĎąĈĉōĺĂğāĉĞŗōǊĉǊā") + method_Xu().keySet().size(), 12811 ^ -5744 ^ 23523 ^ -32654, 22536 ^ -1382 ^ 9414 ^ -31076, -21049 ^ -19105 ^ 22789 ^ -16798);
            b.drawString(method_Xj("ǊċǊāĿĈĎĂğĉĈğŗōǊĉǊā") + method_Xv().size(), 17945 ^ -2038 ^ 8347 ^ -24958, 3273 ^ -30386 ^ 28161 ^ -5292, -31263 ^ -16687 ^ 1469 ^ -16014);
            b.drawString(method_Xj("ǊċǊāĹąğĈČĉĞŗōǊĉǊā") + Thread.activeCount(), 7065 ^ -5347 ^ 3867 ^ -107, 9394 ^ -9194 ^ 9653 ^ -8755, -7559 ^ -24790 ^ 12280 ^ -21164);
         }

         if (method_Xw(this).method_bna() && !method_Xx().isSingleplayer() && 0ej.method_bFy() > 1500L) {
            0en.method_bGp(new ResourceLocation(method_Xj("ăĈĂĚČğĈłĄĀČĊĈĞłāĂĞęĲĎĂăăĈĎęĄĂăŃĝăĊ")), (float)c.getScaledWidth() / Float.intBitsToFloat(1046905 ^ 1019648 ^ 20235 ^ 1234687435 ^ 4384 ^ 1017299 ^ 1044812 ^ 160925958) - Float.intBitsToFloat(248842 ^ 256456 ^ 14942 ^ -1227241736 ^ 234772 ^ 231052 ^ 2572 ^ -195444496), (float)c.getScaledHeight() / Float.intBitsToFloat(1102 ^ 4163335 ^ 4189570 ^ 1549769838 ^ 4169957 ^ 4188154 ^ 8715 ^ 476026801) - Float.intBitsToFloat(126171 ^ 112912 ^ 3192 ^ -1426152975 ^ 110913 ^ 128697 ^ 25876 ^ -373892434), Float.intBitsToFloat('씍' ^ '뵏' ^ 3569 ^ 1393896527 ^ '銰' ^ '퓁' ^ 19508 ^ 269833145), Float.intBitsToFloat(18855 ^ 23400 ^ 2962 ^ 925601625 ^ 492107 ^ 'ꬓ' ^ 513181 ^ 1949002689), this.method_Xi());
            method_Xy().method_bEE(method_Xj("ǊāōōղՓԯ\u0558ԭԢՐՓōԬՓ\u0558ՙՕՐ\u0558ՐՕ\u0558ōԬōԬ\u0558ԭ՟\u0558ԭՓՑ"), (float)c.getScaledWidth() / Float.intBitsToFloat('\udb4a' ^ 478627 ^ 5812 ^ 365162850 ^ '\ua7e9' ^ '脒' ^ 19638 ^ 1438915442), (float)c.getScaledHeight() / Float.intBitsToFloat(12583 ^ 252994 ^ 31643 ^ 217160786 ^ 21498 ^ 239195 ^ 8701 ^ 1290919664) - Float.intBitsToFloat('\ue6f2' ^ '鹣' ^ 11796 ^ 1053894149 ^ 'Ｘ' ^ '通' ^ 21768 ^ 2085833386), this.method_Xi().getRGB());
         }
      } catch (Exception var5) {
      }

   }
}
