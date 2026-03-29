package neo;

import net.minecraft.util.MovementInput;

public class 0h extends MovementInput {
   public final 0g keyboard;
   private static String _DSC GG NEOWARECLIENT _;

   private static boolean jthlCq5qGF(0g var0) {
      return var0.keyBindRight;
   }

   private static 0g NvgWXjAjWe(0h var0) {
      return var0.keyboard;
   }

   private static void iSDQYBL4Lr(0h var0, boolean var1) {
      var0.leftKeyDown = var1;
   }

   private static float _WnGTpllnL/* $FF was: 2WnGTpllnL*/(0h var0) {
      return var0.moveStrafe;
   }

   private static void a6H1nIO2b3(0h var0, boolean var1) {
      var0.leftKeyDown = var1;
   }

   private static 0g tFRlS36b4R(0h var0) {
      return var0.keyboard;
   }

   public void updatePlayerMoveState() {
      Z6CP49hNSN(this, Float.intBitsToFloat(25463 ^ 252696 ^ 32062 ^ 1028515984 ^ 27384 ^ '颛' ^ '젢' ^ 1028528000));
      bxFndr1Ye2(this, Float.intBitsToFloat(258742 ^ 250408 ^ 9320 ^ 1412032847 ^ 6974 ^ 252725 ^ 241016 ^ 1412006090));
      if (Saw3o7jYu7(NvgWXjAjWe(this))) {
         vu1wsLrVdT(this, ji4ededTvo(this) + Float.intBitsToFloat(110081 ^ 85626 ^ 27678 ^ 556035993 ^ 108598 ^ 119892 ^ 10601 ^ 514111223));
         L3Qxr34jNW(this, (boolean)(29818 ^ -25463 ^ 12930 ^ -9616));
      } else {
         NYQAtwFkaj(this, (boolean)(17765 ^ -16272 ^ 27213 ^ -4264));
      }

      if (y56BEoUYkh(qknSXiVB8o(this))) {
         LJgc2W5dw3(this, wp8aXAjdD4(this) - Float.intBitsToFloat(109170 ^ 27239 ^ 130354 ^ 775066039 ^ 19742 ^ 114616 ^ 128687 ^ 296921241));
         tQbx6Qe4oA(this, (boolean)(252 ^ -6987 ^ 3947 ^ -5341));
      } else {
         7eN8qlIcf8(this, (boolean)(2418 ^ -13540 ^ 19448 ^ -30314));
      }

      if (rlYviqS3bk(tFRlS36b4R(this))) {
         gpQ2ZBJNYc(this, 2WnGTpllnL(this) + Float.intBitsToFloat(236313 ^ 251234 ^ 21044 ^ -1321080833 ^ 239916 ^ 260334 ^ 5208 ^ -1899905494));
         iSDQYBL4Lr(this, (boolean)(759 ^ -11958 ^ 26881 ^ -17731));
      } else {
         a6H1nIO2b3(this, (boolean)(23510 ^ -3156 ^ 27251 ^ -15863));
      }

      if (jthlCq5qGF(BSiqyLZT8V(this))) {
         bZwy12GLpQ(this, SVToS9BvJd(this) - Float.intBitsToFloat(22609 ^ 471557 ^ 523745 ^ -1108133721 ^ 26000 ^ 487613 ^ 518029 ^ -2106368590));
         Nbji7DT1yk(this, (boolean)(22387 ^ -8542 ^ 13834 ^ -16422));
      } else {
         Rr9M1BSzI2(this, (boolean)(30314 ^ -2559 ^ 9001 ^ -23742));
      }

      AMWmJWNVB5(this, DZl4IASPOX(FT72n0oaNS(this)));
      18TQGSIyNn(this, Nm8vToYNti(TGovAuoCma(this)));
      if (BjQQn12LBw(this)) {
         EVjKtV3NjV(this, (float)((double)9LrvmszXLe(this) * Double.longBitsToDouble(-3415435786529622598L ^ -1203908005103565175L)));
         4qfdkWegML(this, (float)((double)av9tNoG1ge(this) * Double.longBitsToDouble(-4303026689339169355L ^ -316475500814215546L)));
      }

   }

   private static float ji4ededTvo(0h var0) {
      return var0.moveForward;
   }

   private static void _qfdkWegML/* $FF was: 4qfdkWegML*/(0h var0, float var1) {
      var0.moveForward = var1;
   }

   private static boolean rlYviqS3bk(0g var0) {
      return var0.keyBindLeft;
   }

   private static void _eN8qlIcf8/* $FF was: 7eN8qlIcf8*/(0h var0, boolean var1) {
      var0.backKeyDown = var1;
   }

   private static void LJgc2W5dw3(0h var0, float var1) {
      var0.moveForward = var1;
   }

   public _h/* $FF was: 0h*/(0g a) {
      this.keyboard = a;
   }

   private static void Rr9M1BSzI2(0h var0, boolean var1) {
      var0.rightKeyDown = var1;
   }

   private static void _8TQGSIyNn/* $FF was: 18TQGSIyNn*/(0h var0, boolean var1) {
      var0.sneak = var1;
   }

   private static float SVToS9BvJd(0h var0) {
      return var0.moveStrafe;
   }

   private static void EVjKtV3NjV(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void bxFndr1Ye2(0h var0, float var1) {
      var0.moveForward = var1;
   }

   private static boolean BjQQn12LBw(0h var0) {
      return var0.sneak;
   }

   private static 0g qknSXiVB8o(0h var0) {
      return var0.keyboard;
   }

   private static float wp8aXAjdD4(0h var0) {
      return var0.moveForward;
   }

   private static void Nbji7DT1yk(0h var0, boolean var1) {
      var0.rightKeyDown = var1;
   }

   private static void bZwy12GLpQ(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void vu1wsLrVdT(0h var0, float var1) {
      var0.moveForward = var1;
   }

   private static 0g FT72n0oaNS(0h var0) {
      return var0.keyboard;
   }

   private static float _LrvmszXLe/* $FF was: 9LrvmszXLe*/(0h var0) {
      return var0.moveStrafe;
   }

   private static void tQbx6Qe4oA(0h var0, boolean var1) {
      var0.backKeyDown = var1;
   }

   private static boolean Saw3o7jYu7(0g var0) {
      return var0.keyBindForward;
   }

   private static void Z6CP49hNSN(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void AMWmJWNVB5(0h var0, boolean var1) {
      var0.jump = var1;
   }

   private static void NYQAtwFkaj(0h var0, boolean var1) {
      var0.forwardKeyDown = var1;
   }

   private static void L3Qxr34jNW(0h var0, boolean var1) {
      var0.forwardKeyDown = var1;
   }

   private static boolean Nm8vToYNti(0g var0) {
      return var0.keyBindSneak;
   }

   private static boolean y56BEoUYkh(0g var0) {
      return var0.keyBindBack;
   }

   private static float av9tNoG1ge(0h var0) {
      return var0.moveForward;
   }

   private static 0g BSiqyLZT8V(0h var0) {
      return var0.keyboard;
   }

   private static boolean DZl4IASPOX(0g var0) {
      return var0.keyBindJump;
   }

   private static void gpQ2ZBJNYc(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static 0g TGovAuoCma(0h var0) {
      return var0.keyboard;
   }
}
