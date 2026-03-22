package neo;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.inventory.ClickType;

public class 0cR {
   private static 0cH QAq0VnkJF9(0cC var0) {
      return var0.mc;
   }

   private static 0bX D8tlNOJA6w(0bW var0) {
      return var0.actionType;
   }

   private static int _orEhda0Wj/* $FF was: 1orEhda0Wj*/(0bW var0) {
      return var0.integer;
   }

   private static 0cH Q5VSbKSZbi(0cC var0) {
      return var0.mc;
   }

   private static 0cE eVV5fDQ4Wi(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH tgaiHAtpON(0cC var0) {
      return var0.mc;
   }

   private static boolean DYi2NKZOtA(0bW var0) {
      return var0.forwardKeyDown;
   }

   private static 0cE rEJSbKLJAG(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cE LVqyS23Nal(0cH var0) {
      return var0.gameSettings;
   }

   private static void xZUq9vpVz4(0cE var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static String L9mit1f7Wm(0bW var0) {
      return var0.message;
   }

   private static 0cE UO4grahqnZ(0cH var0) {
      return var0.gameSettings;
   }

   private static int vzcofdgA4V(0bW var0) {
      return var0.integer;
   }

   private static 0bX V6yo81kr2b(0bW var0) {
      return var0.actionType;
   }

   private static void wrUWiwcZLr(0cE var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static void lHwJDrLN2M(0cE var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String lvNDKTViLX(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28928 ^ -23441 ^ 26596 ^ -19829; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24798 ^ -178 ^ 8580 ^ -16680));
      }

      return var1.toString();
   }

   private static boolean CgXKCaE2To(0bW var0) {
      return var0.keyBindSprint;
   }

   private static 0bz _dwgLYib01/* $FF was: 9dwgLYib01*/() {
      return 0bY.runDelay;
   }

   private static 0cH Ala6bWJ6Gn(0cC var0) {
      return var0.mc;
   }

   private static void JVRtCHy6Qc(0cE var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static boolean nGL6i6iEVZ(0bW var0) {
      return var0.keyBindSneak;
   }

   private static float wGjsd8yvpT(0bW var0) {
      return var0.pitch;
   }

   private static 0cE _GwDytZMZF/* $FF was: 9GwDytZMZF*/(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cE cjDAVbDw4P(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH vlAyY4lMdQ(0cC var0) {
      return var0.mc;
   }

   private static 0bX NQSjf9F31n() {
      return 0bX.INVCLICK;
   }

   private static void pTV1KYveek(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static boolean qfDtNJUS9s(0bW var0) {
      return var0.keyBindJump;
   }

   private static 0cE oGgWQdZNfd(0cH var0) {
      return var0.gameSettings;
   }

   private static 0bX me2nvkDo1I(0bW var0) {
      return var0.actionType;
   }

   public static void trigger(0cC pBot, String trtype) {
      if (!AnLOcWNDiN(0bK.getInstance()).getModule(0bY.class).isModuleState()) {
         if (trtype.equals(VF1sO3eqWN().get()) || trtype.equals(lvNDKTViLX("¾©¼©\u00ad¸\u008f£¡¡\u00ad¢¨"))) {
            Thread botThread = new Thread(() -> {
               if (!owSqwClABg(pBot)) {
                  G74BlVLZ1Q(pBot, (boolean)(7668 ^ -8079 ^ 14521 ^ -15043));
                  0et.sleep((long)q4rtyavm9l(9dwgLYib01()));

                  for(Iterator var1 = bmsidrxWni().iterator(); var1.hasNext(); 0et.sleep(50L)) {
                     0bW action = (0bW)var1.next();
                     if (V6yo81kr2b(action).equals(36A7xgZrbZ())) {
                        pBot.sendMessage(L9mit1f7Wm(action));
                     } else if (D8tlNOJA6w(action).equals(NQSjf9F31n())) {
                        pBot.windowClick(vzcofdgA4V(action), 736 ^ -10083 ^ 28796 ^ -22015, w6gLWD4tQi());
                     } else if (me2nvkDo1I(action).equals(jQItpOlyRy())) {
                        pBot.changeSlot(OC9cn78Qww(action));
                        pBot.useItem();
                     } else if (iblnj4v4Ob(action).equals(qcBpO3ViMG())) {
                        ybzBTNdeSB(g90fJVGrYh(yGDa2WdhoC(pBot)), DYi2NKZOtA(action));
                        JVRtCHy6Qc(deGLJBzVGH(HdLFlyGBY8(pBot)), tDZD19A2JX(action));
                        pTV1KYveek(LVqyS23Nal(tgaiHAtpON(pBot)), E9bWlH9MaW(action));
                        iO23bBBhK5(9GwDytZMZF(wje5Wq2IdQ(pBot)), nyf6U7beqt(action));
                        9zCAW7BVDB(B1bJbIbf3G(Fya3d1Ubnc(pBot)), CgXKCaE2To(action));
                        xZUq9vpVz4(EguoLQZj0S(Q5VSbKSZbi(pBot)), nGL6i6iEVZ(action));
                        lHwJDrLN2M(oGgWQdZNfd(aJy65NYqt2(pBot)), qfDtNJUS9s(action));
                        sUwVzOAeSF(jLtXS3vLfr(pBot), nBliZXBaC9(action));
                        qhl6LEywBj(fdBOIp7FRe(pBot), wGjsd8yvpT(action));
                     } else if (a9aPY41h40(action).equals(VjwdreJvrW())) {
                        pBot.clickEntity(1orEhda0Wj(action), (boolean)(18390 ^ -16748 ^ 9432 ^ -8805));
                     }
                  }

                  RCLdSivlNG(B72j746i93(SJT1269gy1(pBot)), (boolean)(1449 ^ -30649 ^ 20 ^ -29190));
                  gN1SiWv23o(cjDAVbDw4P(QAq0VnkJF9(pBot)), (boolean)(22363 ^ -30310 ^ 7863 ^ -16266));
                  gyTe1ZAzkq(UXnRIwV7c9(Ala6bWJ6Gn(pBot)), (boolean)(27848 ^ -10882 ^ 18129 ^ -153));
                  wrUWiwcZLr(eVV5fDQ4Wi(joIawDGiQo(pBot)), (boolean)(29443 ^ -22285 ^ 20025 ^ -27191));
                  aFkSByQimS(UO4grahqnZ(3Yy6iQbYxz(pBot)), (boolean)(5413 ^ -5478 ^ 18442 ^ -18507));
                  eUpOBAKfPd(rEJSbKLJAG(JnVd8Qgi52(pBot)), (boolean)(19757 ^ -7458 ^ 9466 ^ -29943));
                  yJsJCWggVS(rQM27xFUOC(vlAyY4lMdQ(pBot)), (boolean)(26052 ^ -12196 ^ 6165 ^ -21107));
                  hSQb7cGNRA(pBot, (boolean)(4177 ^ -28738 ^ 6240 ^ -30833));
               }

            });
            botThread.setName(lvNDKTViLX("\u009c\u008e£¸\u009e©¯£¾¨©¾á") + pBot.getNickname() + lvNDKTViLX("á") + 0ej.randomNumber(4168 ^ -22781 ^ 25659 ^ -11403));
            botThread.start();
         }

      }
   }

   private static void gN1SiWv23o(0cE var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static 0bX iblnj4v4Ob(0bW var0) {
      return var0.actionType;
   }

   private static void _zCAW7BVDB/* $FF was: 9zCAW7BVDB*/(0cE var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static 0bX VjwdreJvrW() {
      return 0bX.ENTITY;
   }

   private static boolean nyf6U7beqt(0bW var0) {
      return var0.rightKeyDown;
   }

   private static 0cH aJy65NYqt2(0cC var0) {
      return var0.mc;
   }

   private static float nBliZXBaC9(0bW var0) {
      return var0.yaw;
   }

   private static 0cE deGLJBzVGH(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cE EguoLQZj0S(0cH var0) {
      return var0.gameSettings;
   }

   private static int OC9cn78Qww(0bW var0) {
      return var0.integer;
   }

   private static void aFkSByQimS(0cE var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   public _cR/* $FF was: 0cR*/() {
   }

   private static 0bX _6A7xgZrbZ/* $FF was: 36A7xgZrbZ*/() {
      return 0bX.CHAT;
   }

   private static 0bN AnLOcWNDiN(0bK var0) {
      return var0.moduleManager;
   }

   private static 0cH Fya3d1Ubnc(0cC var0) {
      return var0.mc;
   }

   private static 0cE B72j746i93(0cH var0) {
      return var0.gameSettings;
   }

   private static 0bX jQItpOlyRy() {
      return 0bX.HOTBARCLICK;
   }

   private static ArrayList bmsidrxWni() {
      return 0bY.records;
   }

   private static 0bX a9aPY41h40(0bW var0) {
      return var0.actionType;
   }

   private static void RCLdSivlNG(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cH SJT1269gy1(0cC var0) {
      return var0.mc;
   }

   private static 0cE UXnRIwV7c9(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH _Yy6iQbYxz/* $FF was: 3Yy6iQbYxz*/(0cC var0) {
      return var0.mc;
   }

   private static 0cH wje5Wq2IdQ(0cC var0) {
      return var0.mc;
   }

   private static boolean E9bWlH9MaW(0bW var0) {
      return var0.leftKeyDown;
   }

   private static 0cH joIawDGiQo(0cC var0) {
      return var0.mc;
   }

   private static ClickType w6gLWD4tQi() {
      return ClickType.PICKUP;
   }

   private static 0cE g90fJVGrYh(0cH var0) {
      return var0.gameSettings;
   }

   private static void ybzBTNdeSB(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cH HdLFlyGBY8(0cC var0) {
      return var0.mc;
   }

   private static void hSQb7cGNRA(0cC var0, boolean var1) {
      var0.recorderActive = var1;
   }

   private static void sUwVzOAeSF(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static void iO23bBBhK5(0cE var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static 0cD jLtXS3vLfr(0cC var0) {
      return var0.player;
   }

   private static 0cE B1bJbIbf3G(0cH var0) {
      return var0.gameSettings;
   }

   private static boolean owSqwClABg(0cC var0) {
      return var0.recorderActive;
   }

   private static void eUpOBAKfPd(0cE var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static 0cD fdBOIp7FRe(0cC var0) {
      return var0.player;
   }

   private static void gyTe1ZAzkq(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static 0cH yGDa2WdhoC(0cC var0) {
      return var0.mc;
   }

   private static boolean tDZD19A2JX(0bW var0) {
      return var0.backKeyDown;
   }

   private static float q4rtyavm9l(0bz var0) {
      return var0.value;
   }

   private static void yJsJCWggVS(0cE var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static void G74BlVLZ1Q(0cC var0, boolean var1) {
      var0.recorderActive = var1;
   }

   private static void qhl6LEywBj(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cH JnVd8Qgi52(0cC var0) {
      return var0.mc;
   }

   private static 0cE rQM27xFUOC(0cH var0) {
      return var0.gameSettings;
   }

   private static 0by VF1sO3eqWN() {
      return 0bY.trigger;
   }

   private static 0bX qcBpO3ViMG() {
      return 0bX.KEYBOARD;
   }
}
