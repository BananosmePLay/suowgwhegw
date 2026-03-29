package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

public class 0ct extends 0cB {
   public 0bz timerAmount = new 0bz(bXFFPgr0zD("̎̑ͯ̑ͮͭͣ̕"), Float.intBitsToFloat(18193 ^ '蠪' ^ '\ue8e8' ^ 1189483505 ^ '뛆' ^ 261435 ^ '즘' ^ 115717703), Float.intBitsToFloat('\udc0f' ^ 92994 ^ 354 ^ 938409368 ^ 7601 ^ '럃' ^ '\uf40d' ^ 141497800), Float.intBitsToFloat(20999 ^ 1415 ^ 20584 ^ 36215640 ^ 24820 ^ '荾' ^ 7034 ^ 1124623424), Float.intBitsToFloat(7 ^ 89273 ^ '퇆' ^ -274142471 ^ '\uf255' ^ '鴱' ^ 22886 ^ -802598525));

   private static Timer _rfboy8H9g/* $FF was: 7rfboy8H9g*/(Minecraft var0) {
      return var0.timer;
   }

   private static Timer dGvx0gG9y4(Minecraft var0) {
      return var0.timer;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String bXFFPgr0zD(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 19088 ^ -21465 ^ 7173 ^ -1358; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 11463 ^ -1566 ^ 6933 ^ -14049));
      }

      return var1.toString();
   }

   private static void j0jieqAl9D(Timer var0, float var1) {
      var0.timerSpeed = var1;
   }

   private static 0bz bwLjQlqY70(0ct var0) {
      return var0.timerAmount;
   }

   public _ct/* $FF was: 0ct*/() {
      super(bXFFPgr0zD("ݻ݆݂݊ݝ"), 0bV.Player);
      0bC[] var10001 = new 0bC[15304 ^ -24350 ^ 1261 ^ -24634];
      var10001[17793 ^ -26565 ^ 13820 ^ -6074] = this.timerAmount;
      this.addSetting(var10001);
   }

   private static float S07DheHeVQ(0bz var0) {
      return var0.value;
   }

   private static Minecraft t6EOui72Vu() {
      return mc;
   }

   public void onDisable() {
      super.onDisable();
      j0jieqAl9D(dGvx0gG9y4(t6EOui72Vu()), Float.intBitsToFloat(4172898 ^ '\udc8b' ^ 4173925 ^ -1151376669 ^ 29870 ^ '겄' ^ 7664 ^ -2065733707));
   }

   @0X
   public void onPreUpdate(0G eventPreMotion) {
      1drfz1PBNS(7rfboy8H9g(arYBCIPrmg()), S07DheHeVQ(bwLjQlqY70(this)));
   }

   private static Minecraft arYBCIPrmg() {
      return mc;
   }

   private static void _drfz1PBNS/* $FF was: 1drfz1PBNS*/(Timer var0, float var1) {
      var0.timerSpeed = var1;
   }
}
