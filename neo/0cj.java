package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class 0cj extends 0cB {
   private static Minecraft _v9VEoolDG/* $FF was: 1v9VEoolDG*/() {
      return 0eB.mc;
   }

   private static GameSettings SsAnveFgzO() {
      return Minecraft.gameSettings;
   }

   private static GameSettings OiVoTOzYRW() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding A62VGdeSRu(GameSettings var0) {
      return var0.keyBindJump;
   }

   private static Minecraft Ay85eC4WIT() {
      return 0eB.mc;
   }

   private static void uYYQr4D50r(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   public void onDisable() {
      IoJgto8o1g();
      b9uGRWD2iT(qaOYI1Fwnp(rda7nXnFHb()), (boolean)(29201 ^ -20565 ^ 27089 ^ -19349));
      S76NE2jaoQ();
      chiGzgDafF(OTrYro3AW9(qQLvQr4KWp()), (boolean)(26654 ^ -3589 ^ 6116 ^ -29183));
      Bqt2qUc2W9();
      InMTJVIbxL(cdViz901St(OiVoTOzYRW()), (boolean)(31014 ^ -14011 ^ 11116 ^ -25841));
      ZXJJjvUTc7();
      6CtolAhsBH(hg9AlCDvnj(79ttljcg24()), (boolean)(5247 ^ -7870 ^ 24946 ^ -27569));
      GWvMa4N1BB();
      abOTge6oGr(Pbj8dSMyp6(4AI9Vgbttn()), (boolean)(30369 ^ -463 ^ 11935 ^ -23025));
      wFGyLaYo7S();
      prAVjROWWt(wagGNqQ6Tp(2GrpGGmSNL()), (boolean)(15546 ^ -31034 ^ 15617 ^ -30851));
      super.onDisable();
   }

   @0X
   public void onUpdate(0K event) {
      if (!(iTTGvyrFYe(6aWvZ1to4f()) instanceof GuiChat)) {
         lp1yzKpcfT();
         KeyBinding var10000 = UQiqDjBlOF(0m4pTsoyd1());
         2vOHVlroMo();
         ToPGATI0bM(var10000, Keyboard.isKeyDown(A62VGdeSRu(OtFwDoQiOQ()).getKeyCode()));
         hj9dRYLAAm();
         var10000 = 5MqAQKYnQ4(v5vbdddPiA());
         drgPjpmeoT();
         L5tRebOaQV(var10000, Keyboard.isKeyDown(56btWa7cJL(QAqvjCYv2C()).getKeyCode()));
         BrT1fzOMo9();
         var10000 = CL4lazeICG(SfFiPl77eY());
         afBMyRtIak();
         QRvQ2xYlrG(var10000, Keyboard.isKeyDown(SgH7UAYlOC(TeQkSNd6E2()).getKeyCode()));
         VtetFhZvQi();
         var10000 = BbemIA822I(q2fBpETuo7());
         KWiAFqG9JI();
         rzJySMl7t4(var10000, Keyboard.isKeyDown(O61u1Ta0q6(SsAnveFgzO()).getKeyCode()));
         1v9VEoolDG();
         var10000 = l1jGsETnLf(cjvWODmih0());
         ZSt8FqjDLO();
         uYYQr4D50r(var10000, Keyboard.isKeyDown(dTKa8d6Ba2(zQGGvl7PUG()).getKeyCode()));
         DR4bhT1vA9();
         var10000 = l2AdlUj6LQ(7xqNSL7eD4());
         Ay85eC4WIT();
         hBGjMlOSKS(var10000, Keyboard.isKeyDown(t2acSTl3Ie(xvAqjwRmQr()).getKeyCode()));
         GJoWHuQ3uI();
         var10000 = wvUhOJ2MLg(AMBaSPVY7j());
         ntoGRvlbS5();
         6q0CN14vgO(var10000, Keyboard.isKeyDown(DtRZb01inr(BLGjQaBLNO()).getKeyCode()));
      }

   }

   private static GameSettings OtFwDoQiOQ() {
      return Minecraft.gameSettings;
   }

   private static GameSettings QAqvjCYv2C() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding O61u1Ta0q6(GameSettings var0) {
      return var0.keyBindLeft;
   }

   private static Minecraft Bqt2qUc2W9() {
      return 0eB.mc;
   }

   private static void prAVjROWWt(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void b9uGRWD2iT(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static GameSettings v5vbdddPiA() {
      return Minecraft.gameSettings;
   }

   private static Minecraft hj9dRYLAAm() {
      return 0eB.mc;
   }

   private static KeyBinding DtRZb01inr(GameSettings var0) {
      return var0.keyBindSneak;
   }

   private static void L5tRebOaQV(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Minecraft IoJgto8o1g() {
      return 0eB.mc;
   }

   private static KeyBinding _6btWa7cJL/* $FF was: 56btWa7cJL*/(GameSettings var0) {
      return var0.keyBindForward;
   }

   private static Minecraft ZSt8FqjDLO() {
      return 0eB.mc;
   }

   private static KeyBinding hg9AlCDvnj(GameSettings var0) {
      return var0.keyBindLeft;
   }

   private static void ToPGATI0bM(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   public void onEnable() {
      super.onEnable();
   }

   private static GameSettings _m4pTsoyd1/* $FF was: 0m4pTsoyd1*/() {
      return Minecraft.gameSettings;
   }

   private static GameSettings zQGGvl7PUG() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding CL4lazeICG(GameSettings var0) {
      return var0.keyBindBack;
   }

   private static KeyBinding t2acSTl3Ie(GameSettings var0) {
      return var0.keyBindSprint;
   }

   private static void _q0CN14vgO/* $FF was: 6q0CN14vgO*/(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static GameSettings TeQkSNd6E2() {
      return Minecraft.gameSettings;
   }

   private static GameSettings _GrpGGmSNL/* $FF was: 2GrpGGmSNL*/() {
      return Minecraft.gameSettings;
   }

   private static GameSettings rda7nXnFHb() {
      return Minecraft.gameSettings;
   }

   public _cj/* $FF was: 0cj*/() {
      super(mL2mazVq7D("ޑޣ\u07bfޛ\u07b9ޠ\u07b3"), 0bV.Other);
   }

   private static Minecraft ZXJJjvUTc7() {
      return 0eB.mc;
   }

   private static GameSettings _AI9Vgbttn/* $FF was: 4AI9Vgbttn*/() {
      return Minecraft.gameSettings;
   }

   private static Minecraft _aWvZ1to4f/* $FF was: 6aWvZ1to4f*/() {
      return mc;
   }

   private static Minecraft BrT1fzOMo9() {
      return 0eB.mc;
   }

   private static Minecraft ntoGRvlbS5() {
      return 0eB.mc;
   }

   private static KeyBinding dTKa8d6Ba2(GameSettings var0) {
      return var0.keyBindRight;
   }

   private static void InMTJVIbxL(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static KeyBinding cdViz901St(GameSettings var0) {
      return var0.keyBindBack;
   }

   private static KeyBinding wagGNqQ6Tp(GameSettings var0) {
      return var0.keyBindSprint;
   }

   private static void hBGjMlOSKS(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Minecraft afBMyRtIak() {
      return 0eB.mc;
   }

   private static KeyBinding l2AdlUj6LQ(GameSettings var0) {
      return var0.keyBindSprint;
   }

   private static KeyBinding Pbj8dSMyp6(GameSettings var0) {
      return var0.keyBindRight;
   }

   private static KeyBinding SgH7UAYlOC(GameSettings var0) {
      return var0.keyBindBack;
   }

   private static GameSettings q2fBpETuo7() {
      return Minecraft.gameSettings;
   }

   private static void QRvQ2xYlrG(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static GameSettings _xqNSL7eD4/* $FF was: 7xqNSL7eD4*/() {
      return Minecraft.gameSettings;
   }

   private static GameSettings SfFiPl77eY() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding wvUhOJ2MLg(GameSettings var0) {
      return var0.keyBindSneak;
   }

   private static GuiScreen iTTGvyrFYe(Minecraft var0) {
      return var0.currentScreen;
   }

   private static void chiGzgDafF(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void rzJySMl7t4(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Minecraft _vOHVlroMo/* $FF was: 2vOHVlroMo*/() {
      return 0eB.mc;
   }

   private static void _CtolAhsBH/* $FF was: 6CtolAhsBH*/(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static GameSettings BLGjQaBLNO() {
      return Minecraft.gameSettings;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String mL2mazVq7D(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3185 ^ -25273 ^ 17858 ^ -11020; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13294 ^ -41283 ^ '눑' ^ -10092));
      }

      return var1.toString();
   }

   private static GameSettings _9ttljcg24/* $FF was: 79ttljcg24*/() {
      return Minecraft.gameSettings;
   }

   private static Minecraft KWiAFqG9JI() {
      return 0eB.mc;
   }

   private static void abOTge6oGr(KeyBinding var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Minecraft drgPjpmeoT() {
      return 0eB.mc;
   }

   private static Minecraft lp1yzKpcfT() {
      return 0eB.mc;
   }

   private static Minecraft S76NE2jaoQ() {
      return 0eB.mc;
   }

   private static GameSettings qQLvQr4KWp() {
      return Minecraft.gameSettings;
   }

   private static GameSettings AMBaSPVY7j() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding OTrYro3AW9(GameSettings var0) {
      return var0.keyBindForward;
   }

   private static KeyBinding _MqAQKYnQ4/* $FF was: 5MqAQKYnQ4*/(GameSettings var0) {
      return var0.keyBindForward;
   }

   private static GameSettings xvAqjwRmQr() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding qaOYI1Fwnp(GameSettings var0) {
      return var0.keyBindJump;
   }

   private static Minecraft wFGyLaYo7S() {
      return 0eB.mc;
   }

   private static KeyBinding l1jGsETnLf(GameSettings var0) {
      return var0.keyBindRight;
   }

   private static KeyBinding BbemIA822I(GameSettings var0) {
      return var0.keyBindLeft;
   }

   private static GameSettings cjvWODmih0() {
      return Minecraft.gameSettings;
   }

   private static Minecraft GWvMa4N1BB() {
      return 0eB.mc;
   }

   private static Minecraft DR4bhT1vA9() {
      return 0eB.mc;
   }

   private static Minecraft GJoWHuQ3uI() {
      return 0eB.mc;
   }

   private static Minecraft VtetFhZvQi() {
      return 0eB.mc;
   }

   private static KeyBinding UQiqDjBlOF(GameSettings var0) {
      return var0.keyBindJump;
   }
}
