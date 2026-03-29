package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MovementInput;
import org.lwjgl.input.Keyboard;

public class 0cI extends MovementInput {
   public final 0cE gameSettings;

   public _cI/* $FF was: 0cI*/(0cE gameSettingsIn) {
      this.gameSettings = gameSettingsIn;
   }

   private static void g6pDRLRq8s(0cI var0, boolean var1) {
      var0.jump = var1;
   }

   private static boolean tBzS2NGVBr(0cE var0) {
      return var0.keyBindSneak;
   }

   private static void _5qNKJvMui/* $FF was: 35qNKJvMui*/(0cI var0, float var1) {
      var0.moveForward = var1;
   }

   private static float Q3Iv38t2BA(0cI var0) {
      return var0.moveStrafe;
   }

   private static void fiDTDSAfGG(0cI var0, boolean var1) {
      var0.forwardKeyDown = var1;
   }

   private static boolean h2oqBUfvim(0cE var0) {
      return var0.keyBindJump;
   }

   private static void Unb8uvuirm(0cI var0, boolean var1) {
      var0.leftKeyDown = var1;
   }

   private static GuiScreen IfHGtYWGiv(Minecraft var0) {
      return var0.currentScreen;
   }

   private static GuiScreen ir9JODZ8qs(Minecraft var0) {
      return var0.currentScreen;
   }

   private static boolean _tGo98yctL/* $FF was: 5tGo98yctL*/(0cE var0) {
      return var0.keyBindLeft;
   }

   private static float qjgT77XHYA(0cI var0) {
      return var0.moveStrafe;
   }

   private static boolean i49Ik4jsRw(0cE var0) {
      return var0.keyBindRight;
   }

   private static 0cE E6zbM4JEOV(0cI var0) {
      return var0.gameSettings;
   }

   private static float aVheOyQgsJ(0cI var0) {
      return var0.moveForward;
   }

   private static 0cE fZYqvIcvdp(0cI var0) {
      return var0.gameSettings;
   }

   private static GuiScreen S1GSI3lNVn(Minecraft var0) {
      return var0.currentScreen;
   }

   private static void HjlFq8eMrz(0cI var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static 0cE qvEzgvubjS(0cI var0) {
      return var0.gameSettings;
   }

   private static void _AiobAy1DY/* $FF was: 9AiobAy1DY*/(0cI var0, boolean var1) {
      var0.forwardKeyDown = var1;
   }

   private static void wv8bCJWTNj(0cI var0, float var1) {
      var0.moveForward = var1;
   }

   private static 0cE To9NPnrr4J(0cI var0) {
      return var0.gameSettings;
   }

   private static float hWpHqWIPdr(0cI var0) {
      return var0.moveStrafe;
   }

   private static boolean _0DFOBcwpe/* $FF was: 90DFOBcwpe*/(0cE var0) {
      return var0.keyBindBack;
   }

   private static GuiScreen qxnpRrnGl1(Minecraft var0) {
      return var0.currentScreen;
   }

   private static GuiScreen JdejLjXdrQ(Minecraft var0) {
      return var0.currentScreen;
   }

   private static 0cE WwNJNKTW5h(0cI var0) {
      return var0.gameSettings;
   }

   private static 0cE mTFbNIlFJ6(0cI var0) {
      return var0.gameSettings;
   }

   private static void oGLirFmJtV(0cI var0, boolean var1) {
      var0.jump = var1;
   }

   private static void ZAdZE1YGpT(0cI var0, float var1) {
      var0.moveForward = var1;
   }

   private static boolean TQwuTSHjw1(0cE var0) {
      return var0.keyBindForward;
   }

   private static void dfAlyxp7zs(0cI var0, boolean var1) {
      var0.backKeyDown = var1;
   }

   private static void _OeIuUB2we/* $FF was: 4OeIuUB2we*/(0cI var0, float var1) {
      var0.moveForward = var1;
   }

   private static void reOtqQALGA(0cI var0, boolean var1) {
      var0.rightKeyDown = var1;
   }

   private static float _iGVBy2Brd/* $FF was: 1iGVBy2Brd*/(0cI var0) {
      return var0.moveForward;
   }

   private static void OwTFS2lQsG(0cI var0, boolean var1) {
      var0.sneak = var1;
   }

   private static void UlZaqiZiAn(0cI var0, float var1) {
      var0.moveStrafe = var1;
   }

   public void updatePlayerMoveState() {
      oGLirFmJtV(this, (boolean)(3513 ^ -24224 ^ 3921 ^ -23672));
      OwTFS2lQsG(this, (boolean)(20193 ^ -13904 ^ 25148 ^ -6803));
      1b96fOBpD4(this, Float.intBitsToFloat(123257 ^ '땘' ^ 118303 ^ 1194218790 ^ 10090 ^ 119871 ^ 109176 ^ 1194235445));
      4OeIuUB2we(this, Float.intBitsToFloat(130998 ^ 109130 ^ 7754 ^ 292328446 ^ 122797 ^ 127889 ^ 17444 ^ 292335696));
      if (!TQwuTSHjw1(To9NPnrr4J(this)) && (JdejLjXdrQ(Minecraft.getMinecraft()) instanceof GuiChat || !Keyboard.isKeyDown(9253 ^ -21118 ^ 27964 ^ -6957) && !Keyboard.isKeyDown(29386 ^ -30419 ^ 11764 ^ -10533))) {
         9AiobAy1DY(this, (boolean)(8904 ^ -5354 ^ 5290 ^ -8844));
      } else {
         wv8bCJWTNj(this, Qnbc96bVw0(this) + Float.intBitsToFloat(29995 ^ 117379 ^ 92 ^ 1270917223 ^ '錬' ^ 99614 ^ '솫' ^ 1950400522));
         fiDTDSAfGG(this, (boolean)(10923 ^ -1162 ^ 14185 ^ -6475));
      }

      if (!90DFOBcwpe(fZYqvIcvdp(this)) && (qxnpRrnGl1(Minecraft.getMinecraft()) instanceof GuiChat || !Keyboard.isKeyDown(24164 ^ -18684 ^ 10259 ^ -16065) && !Keyboard.isKeyDown(1884 ^ -30788 ^ 5954 ^ -26766))) {
         dfAlyxp7zs(this, (boolean)(31388 ^ -23620 ^ 7509 ^ -15243));
      } else {
         ZAdZE1YGpT(this, 1iGVBy2Brd(this) - Float.intBitsToFloat(31534 ^ 11806 ^ 17455 ^ -1363869430 ^ '鷅' ^ 124987 ^ '朗' ^ -1858822279));
         4WG61vE4jN(this, (boolean)(25261 ^ -15705 ^ 14522 ^ -26447));
      }

      if (!5tGo98yctL(qvEzgvubjS(this)) && (IfHGtYWGiv(Minecraft.getMinecraft()) instanceof GuiChat || !Keyboard.isKeyDown(728 ^ -20272 ^ 2955 ^ -17976) && !Keyboard.isKeyDown(380 ^ -10364 ^ 23917 ^ -29858))) {
         LVPpL7WVNe(this, (boolean)(32161 ^ -13462 ^ 11132 ^ -25161));
      } else {
         UlZaqiZiAn(this, hWpHqWIPdr(this) + Float.intBitsToFloat(8226 ^ 118152 ^ 432 ^ 118027436 ^ '諜' ^ 22887 ^ '쀕' ^ 948505368));
         Unb8uvuirm(this, (boolean)(22620 ^ -11854 ^ 21180 ^ -9389));
      }

      if (!i49Ik4jsRw(mTFbNIlFJ6(this)) && (S1GSI3lNVn(Minecraft.getMinecraft()) instanceof GuiChat || !Keyboard.isKeyDown(16714 ^ -11658 ^ 7141 ^ -30572) && !Keyboard.isKeyDown(5124 ^ -30351 ^ 25203 ^ -53))) {
         reOtqQALGA(this, (boolean)(22060 ^ -27040 ^ 16171 ^ -153));
      } else {
         HjlFq8eMrz(this, qjgT77XHYA(this) - Float.intBitsToFloat('쎌' ^ '鼱' ^ 1426 ^ 1057950294 ^ 8793 ^ 85158 ^ '\ue6d8' ^ 9362270));
         YfmpgJ7WBS(this, (boolean)(15470 ^ -9810 ^ 13987 ^ -11422));
      }

      if (h2oqBUfvim(E6zbM4JEOV(this)) || !(ir9JODZ8qs(Minecraft.getMinecraft()) instanceof GuiChat) && Keyboard.isKeyDown(1649 ^ -4511 ^ 20431 ^ -22640)) {
         g6pDRLRq8s(this, (boolean)(10328 ^ -16103 ^ 4780 ^ -1044));
      }

      if (tBzS2NGVBr(WwNJNKTW5h(this)) || !(Z4JoOWBiUu(Minecraft.getMinecraft()) instanceof GuiChat) && Keyboard.isKeyDown(8956 ^ -13592 ^ 23001 ^ -20068)) {
         Goib8U9FfF(this, (boolean)(31122 ^ -21159 ^ 25372 ^ -18474));
         eB3APp8aF7(this, (float)((double)Q3Iv38t2BA(this) * Double.longBitsToDouble(1686713035267366172L ^ 2935040336089771567L)));
         35qNKJvMui(this, (float)((double)aVheOyQgsJ(this) * Double.longBitsToDouble(-820680446284155056L ^ -3796701301225782173L)));
      }

   }

   private static void LVPpL7WVNe(0cI var0, boolean var1) {
      var0.leftKeyDown = var1;
   }

   private static void _b96fOBpD4/* $FF was: 1b96fOBpD4*/(0cI var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static float Qnbc96bVw0(0cI var0) {
      return var0.moveForward;
   }

   private static void eB3APp8aF7(0cI var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void Goib8U9FfF(0cI var0, boolean var1) {
      var0.sneak = var1;
   }

   private static void _WG61vE4jN/* $FF was: 4WG61vE4jN*/(0cI var0, boolean var1) {
      var0.backKeyDown = var1;
   }

   private static void YfmpgJ7WBS(0cI var0, boolean var1) {
      var0.rightKeyDown = var1;
   }

   private static GuiScreen Z4JoOWBiUu(Minecraft var0) {
      return var0.currentScreen;
   }
}
