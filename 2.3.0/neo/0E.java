package neo;

import java.util.ArrayList;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumHand;

public class 0E extends 0u {
   public 0ei field_a;
   public int field_b;
   private static String _ _;

   private static 0bB method_ii(0bA var0) {
      return var0.field_h;
   }

   private static boolean method_ir(0bA var0) {
      return var0.field_b;
   }

   public void method_dm() {
      method_hS(this, new 0ei());
   }

   private static void method_iH(0g var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static void method_iy(0g var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static void method_iN(0f var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static boolean method_iG(0bA var0) {
      return var0.field_c;
   }

   private static 0bB method_id(0bA var0) {
      return var0.field_h;
   }

   private static 0bB method_ie() {
      return 0bB.field_e;
   }

   private static 0bB method_iO(0bA var0) {
      return var0.field_h;
   }

   private static 0g method_iF(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_iw(0a var0) {
      return var0.keyboard;
   }

   private static float method_iJ(0bA var0) {
      return var0.field_g;
   }

   private static boolean method_io(0bA var0) {
      return var0.field_e;
   }

   private static boolean method_ix(0bA var0) {
      return var0.field_i;
   }

   private static EnumHand method_iS() {
      return EnumHand.MAIN_HAND;
   }

   private static boolean method_iD(0bA var0) {
      return var0.field_a;
   }

   private static 0g method_in(0a var0) {
      return var0.keyboard;
   }

   private static int method_ik(0bA var0) {
      return var0.field_k;
   }

   private static int method_iT(0E var0) {
      return var0.field_b;
   }

   private static 0ct method_hU() {
      return 0bC.field_a;
   }

   private static void method_is(0g var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static int method_iV(0E var0) {
      return var0.field_b;
   }

   private static void method_ip(0g var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0bB method_hZ(0bA var0) {
      return var0.field_h;
   }

   private static 0bB method_il(0bA var0) {
      return var0.field_h;
   }

   private static 0bB method_ij() {
      return 0bB.field_c;
   }

   private static void method_iK(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static boolean method_iu(0bA var0) {
      return var0.field_l;
   }

   private static String method_ic(0bA var0) {
      return var0.field_d;
   }

   private static 0bB method_ia() {
      return 0bB.field_d;
   }

   public void method_dn() {
      if (method_hT(this).hasReached(method_hU().method_bnH())) {
         0a a = this.method_dp();
         if (method_hV(this) < method_hW().size()) {
            0bA b = (0bA)method_hX().get(method_hY(this));
            if (method_hZ(b).equals(method_ia())) {
               method_ib(a).sendChatMessage(method_ic(b));
            } else if (method_id(b).equals(method_ie())) {
               method_if(a).windowClick(method_ig(b), 3957 ^ -4373 ^ 7185 ^ -625, method_ih());
            } else if (method_ii(b).equals(method_ij())) {
               a.changeSlot(method_ik(b));
               a.useItem();
            } else if (method_il(b).equals(method_im())) {
               method_ip(method_in(a), method_io(b));
               method_is(method_iq(a), method_ir(b));
               method_iv(method_it(a), method_iu(b));
               method_iy(method_iw(a), method_ix(b));
               method_iB(method_iz(a), method_iA(b));
               method_iE(method_iC(a), method_iD(b));
               method_iH(method_iF(a), method_iG(b));
               method_iK(method_iI(a), method_iJ(b));
               method_iN(method_iL(a), method_iM(b));
            } else if (method_iO(b).equals(method_iP())) {
               method_iQ(a).interactWithEntity(method_iR(b), method_iS());
            }

            method_iU(this, method_iT(this) + (7012 ^ -9041 ^ 4506 ^ -10672));
            if (method_iV(this) >= method_iW().size() - (3041 ^ -13023 ^ 24742 ^ -22937)) {
               this.method_ds().method_cR((0u)null);
            }

         }
      }
   }

   private static void method_iE(0g var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static void method_hS(0E var0, 0ei var1) {
      var0.field_a = var1;
   }

   private static void method_iB(0g var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static ArrayList method_iW() {
      return 0bC.field_c;
   }

   public void method_do() {
      super.method_do();
   }

   private static ClickType method_ih() {
      return ClickType.PICKUP;
   }

   private static 0f method_iI(0a var0) {
      return var0.player;
   }

   private static ArrayList method_hX() {
      return 0bC.field_c;
   }

   private static boolean method_iA(0bA var0) {
      return var0.field_f;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_hR(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 17754 ^ -7123 ^ 15870 ^ -25463; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 22954 ^ -1339 ^ 19580 ^ -4584));
      }

      return var1.toString();
   }

   private static int method_hV(0E var0) {
      return var0.field_b;
   }

   private static 0f method_ib(0a var0) {
      return var0.player;
   }

   private static int method_ig(0bA var0) {
      return var0.field_k;
   }

   public _E/* $FF was: 0E*/(0a a) {
      super(a, method_hR("řŮŻŮŪſ"));
   }

   private static 0f method_iL(0a var0) {
      return var0.player;
   }

   private static 0g method_it(0a var0) {
      return var0.keyboard;
   }

   private static 0bB method_im() {
      return 0bB.field_a;
   }

   private static ArrayList method_hW() {
      return 0bC.field_c;
   }

   private static 0ei method_hT(0E var0) {
      return var0.field_a;
   }

   private static int method_hY(0E var0) {
      return var0.field_b;
   }

   private static 0g method_iC(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_iz(0a var0) {
      return var0.keyboard;
   }

   private static 0g method_iq(0a var0) {
      return var0.keyboard;
   }

   private static 0bB method_iP() {
      return 0bB.field_b;
   }

   private static void method_iv(0g var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static void method_iU(0E var0, int var1) {
      var0.field_b = var1;
   }

   private static 0i method_iQ(0a var0) {
      return var0.controller;
   }

   private static float method_iM(0bA var0) {
      return var0.field_j;
   }

   private static int method_iR(0bA var0) {
      return var0.field_k;
   }

   private static 0i method_if(0a var0) {
      return var0.controller;
   }
}
