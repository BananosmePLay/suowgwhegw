package neo;

import com.google.common.eventbus.Subscribe;
import org.lwjgl.input.Keyboard;

public class 0bR extends 0dr {
   private static int _DSC GG NEOWARECLIENT _;

   private static Bj method_Vy() {
      return nC.gameSettings;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Vo(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7344 ^ -7017 ^ 22103 ^ -20880; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 23926 ^ -27534 ^ 15978 ^ -2257));
      }

      return var1.toString();
   }

   private static void method_Wl(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_Vw() {
      return nC.gameSettings;
   }

   private static Bl method_Wk(Bj var0) {
      return var0.keyBindRight;
   }

   private static Bj method_VG() {
      return nC.gameSettings;
   }

   private static Bj method_VD() {
      return nC.gameSettings;
   }

   private static Bj method_Ws() {
      return nC.gameSettings;
   }

   private static Bj method_Wj() {
      return nC.gameSettings;
   }

   private static Bj method_VS() {
      return nC.gameSettings;
   }

   private static Bj method_Wa() {
      return nC.gameSettings;
   }

   private static Bl method_VT(Bj var0) {
      return var0.keyBindJump;
   }

   private static Bl method_Vx(Bj var0) {
      return var0.keyBindBack;
   }

   private static Bl method_Wb(Bj var0) {
      return var0.keyBindForward;
   }

   private static Bl method_VJ(Bj var0) {
      return var0.keyBindRight;
   }

   private static Bl method_Wh(Bj var0) {
      return var0.keyBindLeft;
   }

   private static void method_VA(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void method_VF(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void method_Vv(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_VX() {
      return nC.gameSettings;
   }

   private static Bj method_Wd() {
      return nC.gameSettings;
   }

   private static nC method_Vp() {
      return mc;
   }

   private static Bl method_VW(Bj var0) {
      return var0.keyBindSneak;
   }

   private static Bj method_VQ() {
      return nC.gameSettings;
   }

   private static Bj method_Wm() {
      return nC.gameSettings;
   }

   private static void method_VP(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   public _bR/* $FF was: 0bR*/() {
      super(method_Vo("\u00064(\f.7$"), 0dz.field_f, 18731 ^ -28655 ^ 5060 ^ -13570);
   }

   private static void method_Wo(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bl method_Wn(Bj var0) {
      return var0.keyBindSprint;
   }

   private static Bl method_VH(Bj var0) {
      return var0.keyBindRight;
   }

   private static Bl method_VM(Bj var0) {
      return var0.keyBindSprint;
   }

   private static Bj method_VL() {
      return nC.gameSettings;
   }

   public void method_bAV() {
      super.method_bAV();
      method_Wc(method_Wb(method_Wa()), (boolean)(22829 ^ -2525 ^ 16744 ^ -4506));
      method_Wf(method_We(method_Wd()), (boolean)(4870 ^ -8911 ^ 12236 ^ -7685));
      method_Wi(method_Wh(method_Wg()), (boolean)(27328 ^ -866 ^ 22137 ^ -16345));
      method_Wl(method_Wk(method_Wj()), (boolean)(342 ^ -18786 ^ 7475 ^ -21765));
      method_Wo(method_Wn(method_Wm()), (boolean)(32164 ^ -7823 ^ 24985 ^ -692));
      method_Wr(method_Wq(method_Wp()), (boolean)(24514 ^ -31886 ^ 26753 ^ -19407));
      method_Wu(method_Wt(method_Ws()), (boolean)(26529 ^ -27744 ^ 20312 ^ -17575));
   }

   private static void method_Wc(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_VV() {
      return nC.gameSettings;
   }

   private static lg method_Vq(nC var0) {
      return var0.currentScreen;
   }

   private static Bl method_Vs(Bj var0) {
      return var0.keyBindForward;
   }

   private static Bl method_Wt(Bj var0) {
      return var0.keyBindSneak;
   }

   private static Bj method_Wg() {
      return nC.gameSettings;
   }

   private static Bl method_Vz(Bj var0) {
      return var0.keyBindBack;
   }

   private static Bj method_Vr() {
      return nC.gameSettings;
   }

   private static Bj method_VB() {
      return nC.gameSettings;
   }

   private static Bl method_Vu(Bj var0) {
      return var0.keyBindForward;
   }

   public void method_bAU() {
      super.method_bAU();
   }

   private static void method_VZ(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static void method_Wu(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   @Subscribe
   public void method_Vn(0dm a) {
      if (!(method_Vq(method_Vp()) instanceof jP)) {
         method_Vv(method_Vs(method_Vr()), Keyboard.isKeyDown(method_Vu(method_Vt()).getKeyCode()));
         method_VA(method_Vx(method_Vw()), Keyboard.isKeyDown(method_Vz(method_Vy()).getKeyCode()));
         method_VF(method_VC(method_VB()), Keyboard.isKeyDown(method_VE(method_VD()).getKeyCode()));
         method_VK(method_VH(method_VG()), Keyboard.isKeyDown(method_VJ(method_VI()).getKeyCode()));
         method_VP(method_VM(method_VL()), Keyboard.isKeyDown(method_VO(method_VN()).getKeyCode()));
         method_VU(method_VR(method_VQ()), Keyboard.isKeyDown(method_VT(method_VS()).getKeyCode()));
         method_VZ(method_VW(method_VV()), Keyboard.isKeyDown(method_VY(method_VX()).getKeyCode()));
      }

   }

   private static Bl method_We(Bj var0) {
      return var0.keyBindBack;
   }

   private static void method_Wr(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_VI() {
      return nC.gameSettings;
   }

   private static void method_VU(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bl method_VR(Bj var0) {
      return var0.keyBindJump;
   }

   private static void method_Wi(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bl method_VC(Bj var0) {
      return var0.keyBindLeft;
   }

   private static void method_VK(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_VN() {
      return nC.gameSettings;
   }

   private static Bl method_Wq(Bj var0) {
      return var0.keyBindJump;
   }

   private static Bl method_VE(Bj var0) {
      return var0.keyBindLeft;
   }

   private static Bl method_VO(Bj var0) {
      return var0.keyBindSprint;
   }

   private static Bj method_Vt() {
      return nC.gameSettings;
   }

   private static Bl method_VY(Bj var0) {
      return var0.keyBindSneak;
   }

   private static void method_Wf(Bl var0, boolean var1) {
      var0.pressed = var1;
   }

   private static Bj method_Wp() {
      return nC.gameSettings;
   }
}
