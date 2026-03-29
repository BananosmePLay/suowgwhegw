package neo;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;

public class 0bC extends 0dr {
   public final 0ei field_b = new 0ei();
   public static 0cs field_f;
   public static ArrayList<0bA> field_c;
   public static 0cu field_d;
   public static 0cp field_e;
   public static 0ct field_a;
   private static String _ _;

   private static 0cs method_RC() {
      return field_f;
   }

   private static ArrayList method_Ra() {
      return field_c;
   }

   private static Bl method_Rx(Bj var0) {
      return var0.keyBindSprint;
   }

   private static Bj method_Ri() {
      return nC.gameSettings;
   }

   private static Bj method_Rk() {
      return nC.gameSettings;
   }

   private static 0ei method_Rf(0bC var0) {
      return var0.field_b;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_QZ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 20829 ^ -15618 ^ 16525 ^ -11474; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27152 ^ -21259 ^ 11584 ^ -6004));
      }

      return var1.toString();
   }

   private static jh method_Rb() {
      return nC.player;
   }

   private static float method_Rt(jh var0) {
      return var0.rotationPitch;
   }

   @Subscribe
   public void method_QX(0de a) {
      String b = a.method_bzZ();
      if (!b.startsWith(method_QZ("̇"))) {
         method_RA().add(new 0bA(method_RB(), b));
      }

   }

   private static Bj method_Ry() {
      return nC.gameSettings;
   }

   private static Bl method_Rp(Bj var0) {
      return var0.keyBindRight;
   }

   private static ArrayList method_Re() {
      return field_c;
   }

   private static ArrayList method_RA() {
      return field_c;
   }

   private static jh method_Rd() {
      return nC.player;
   }

   private static Bj method_Rm() {
      return nC.gameSettings;
   }

   private static Bl method_Rz(Bj var0) {
      return var0.keyBindJump;
   }

   private static 0cp method_Rc() {
      return field_e;
   }

   public void method_bAU() {
      super.method_bAU();
      method_Ra().clear();
      0ek.addMessage(0cT.method_byX(method_QZ("͍͈͇͍͚͈̈́͆͌̇͊̀͆͛͌͊͆͛͌͛̇͛͜͝͝͝ͅ")));
      if (method_Rb() != null && method_Rc().method_bna()) {
         method_Rd().sendChatMessage(method_QZ("͚͙͈̇͋͆̉͛͌͌͝͝"));
      }

   }

   private static Bl method_Rj(Bj var0) {
      return var0.keyBindForward;
   }

   private static 0bB method_RB() {
      return 0bB.field_d;
   }

   private static ArrayList method_Rg() {
      return field_c;
   }

   private static 0bB method_Rh() {
      return 0bB.field_a;
   }

   @Subscribe
   public void method_QW(0dm a) {
      if (method_Rf(this).hasReached(16875 ^ -14516 ^ 9752 ^ -24405)) {
         method_Rg().add(new 0bA(method_Rh(), method_Rj(method_Ri()).isKeyDown(), method_Rl(method_Rk()).isKeyDown(), method_Rn(method_Rm()).isKeyDown(), method_Rp(method_Ro()).isKeyDown(), method_Rr(method_Rq()), method_Rt(method_Rs()), method_Rv(method_Ru()).isKeyDown(), method_Rx(method_Rw()).isKeyDown(), method_Rz(method_Ry()).isKeyDown()));
      }

   }

   private static Bl method_Rv(Bj var0) {
      return var0.keyBindSneak;
   }

   private static Bl method_Rn(Bj var0) {
      return var0.keyBindLeft;
   }

   public void method_bAV() {
      super.method_bAV();
      String var10000 = method_QZ("͍͈͇͍͚͙̈́͆͌̇͊̀͆͛͌͊͆͛͌͛̇͆͜͝͝ͅ");
      Object[] var10001 = new Object[15880 ^ -10106 ^ 27690 ^ -30043];
      var10001[30727 ^ -22090 ^ 670 ^ -11473] = method_Re().size();
      0ek.addMessage(0cT.method_byW(var10000, var10001));
   }

   private static float method_Rr(jh var0) {
      return var0.rotationYaw;
   }

   private static Bj method_Ro() {
      return nC.gameSettings;
   }

   private static Bj method_Ru() {
      return nC.gameSettings;
   }

   private static Bj method_Rw() {
      return nC.gameSettings;
   }

   private static Bl method_Rl(Bj var0) {
      return var0.keyBindBack;
   }

   private static jh method_Rs() {
      return nC.player;
   }

   static {
      String var10002 = method_QZ("ͽ͎͎͛̀͌͛");
      String var10003 = method_QZ("͇ͧ͆͌");
      String[] var10004 = new String[22605 ^ -17451 ^ 5318 ^ -2211];
      var10004[9963 ^ -31672 ^ 5465 ^ -18438] = method_QZ("͇͇͆ͣ͆̀");
      var10004[6166 ^ -5065 ^ 25959 ^ -28345] = method_QZ("͇͆ͨ́͜͝");
      var10004[19179 ^ -11677 ^ 30194 ^ -4744] = method_QZ("͇͚͚͈͎͆ͤ͌͌");
      field_f = new 0cs(var10002, var10003, var10004);
      field_a = new 0ct(method_QZ("ͻ͇͈̉ͭ͌͐͜ͅ"), Float.intBitsToFloat(28871 ^ 120051 ^ 338 ^ -486231547 ^ 31304 ^ 104872 ^ 5711 ^ -486219060), Float.intBitsToFloat(121727 ^ 88920 ^ 11044 ^ 502955010 ^ 9889 ^ 31826 ^ 5684 ^ 502963142), Float.intBitsToFloat(14396 ^ 494211 ^ 506114 ^ -312224221 ^ 'ﯥ' ^ 1009199 ^ 14130 ^ -1462241434));
      field_d = new 0cu(method_QZ("͚͚͈͎ͤ͌͌"), method_QZ("͎͛͛͝Ͷ͚͎̈́"), () -> {
         return method_RC().method_bnr(method_QZ("͇͚͚͈͎͆ͤ͌͌"));
      });
      field_e = new 0cp(method_QZ("ͻ͇͇͚̉͆̉͋͆͜͝"), (boolean)(22417 ^ -19154 ^ 25755 ^ -31196));
   }

   public _bC/* $FF was: 0bC*/() {
      super(method_QZ("͇ͨ͊̀͆͝ͻ͍͌͊͆͛͌͛"), 0dz.field_h, 26215 ^ -10367 ^ 709 ^ -19677);
      0cv[] var10001 = new 0cv[11350 ^ -15651 ^ 5282 ^ -1491];
      var10001[1535 ^ -2207 ^ 31396 ^ -30662] = field_f;
      var10001[8326 ^ -1448 ^ 28129 ^ -18626] = field_a;
      var10001[379 ^ -28354 ^ 30825 ^ -6098] = field_d;
      var10001[20790 ^ -1548 ^ 18590 ^ -8097] = field_e;
      this.method_bBe(var10001);
      field_c = new ArrayList();
   }

   private static jh method_Rq() {
      return nC.player;
   }
}
