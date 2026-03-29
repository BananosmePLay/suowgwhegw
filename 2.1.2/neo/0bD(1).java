package neo;

import java.awt.Color;

public class 0bD {
   public final String text;
   public float sizeAnimation;
   public final int height;
   public final int width;
   public float colorAnimation;
   public final int id;
   public final int y;
   public final int x;

   private static int _oMfWOltD6/* $FF was: 0oMfWOltD6*/(0bD var0) {
      return var0.width;
   }

   private static int L86L6RHBdY(0bD var0) {
      return var0.x;
   }

   private static int AYhxpGHr0n(0bD var0) {
      return var0.width;
   }

   public _bD/* $FF was: 0bD*/(int id, int x, int y, int width, int height, String text) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.text = text;
   }

   private static float QZzsktxV0n(0bD var0) {
      return var0.colorAnimation;
   }

   private static float _GQa9d8HAI/* $FF was: 2GQa9d8HAI*/(0bD var0) {
      return var0.sizeAnimation;
   }

   public void render(int mouseX, int mouseY) {
      wQATaqwBOw(this, 0dJ.animation(OhdWpGiLEt(this), (float)(this.isHovered(mouseX, mouseY) ? 16430 ^ -8350 ^ 1140 ^ -25634 : 17762 ^ -19648 ^ 3745 ^ -1993), Float.intBitsToFloat('쮛' ^ 98248 ^ 9303 ^ -1918968462 ^ 11260 ^ 1012326 ^ 1041046 ^ -1155040546)));
      gDJyBtg98i(this, 0dJ.animation(bBnvtOQ27c(this), (float)(this.isHovered(mouseX, mouseY) ? 31662 ^ -32298 ^ 5254 ^ -4356 : 21444 ^ -3718 ^ 9102 ^ -32464), Float.intBitsToFloat(10425 ^ 109580 ^ 122492 ^ -842103801 ^ 'ﾬ' ^ 237993 ^ '\uf502' ^ -76154515)));
      0ex.drawGradientRound((float)(FOQ3rrzLvj(this) - AYhxpGHr0n(this) / (1653 ^ -24761 ^ 29407 ^ -5137)) - 2GQa9d8HAI(this), (float)(x9roWpSl4o(this) - aiAatvkQtq(this) / (26636 ^ -28232 ^ 24732 ^ -26326)), (float)tyOsE1zWbT(this) + 9EnJoYcp4I(this) * Float.intBitsToFloat(19194 ^ 124664 ^ 30956 ^ 410966179 ^ 20900 ^ 107803 ^ 11928 ^ 1484707434), (float)znViaacURi(this), Float.intBitsToFloat(31590 ^ '鸖' ^ 11870 ^ -1389608674 ^ 24978 ^ '膰' ^ 30116 ^ -307486794), new Color(25547 ^ -14818 ^ 17393 ^ -6598, 13879 ^ -17098 ^ 22390 ^ -9111, 3625 ^ -16729 ^ 27076 ^ -9900, 29242 ^ 30577 ^ 1340 ^ 136), new Color(22167 ^ -4469 ^ 24466 ^ -6256, 29541 ^ -3760 ^ 11980 ^ -21273, 22851 ^ -24583 ^ 655 ^ -15317, 13755 ^ -24766 ^ 30612 ^ -8814), new Color(24027 ^ -32139 ^ 8266 ^ -129, 731 ^ -6993 ^ 16406 ^ -22791, 19442 ^ -20825 ^ 12501 ^ -10981, 347 ^ -29447 ^ 21688 ^ -9755), new Color(23822 ^ -19848 ^ 30884 ^ -26676, 18554 ^ -11646 ^ 17094 ^ -10208, 28264 ^ -22746 ^ 15914 ^ -2182, 31595 ^ -13813 ^ 16818 ^ -4051));
      090DYollnl().drawCenteredString(oOINm6BHn9(this), (float)TinawQTalf(this), (float)(BIk9VpJIb9(this) - (2403 ^ -30982 ^ 346 ^ -28991)), (new Color((int)q3niyo7CbH(this), (int)QZzsktxV0n(this), (int)Yon2Qgun0r(this))).getRGB());
   }

   private static int aDqvCWQjJ9(0bD var0) {
      return var0.y;
   }

   private static int V7KJ2b4DHd(0bD var0) {
      return var0.y;
   }

   private static 0eg _90DYollnl/* $FF was: 090DYollnl*/() {
      return 0eh.mnstb_15;
   }

   private static int x9roWpSl4o(0bD var0) {
      return var0.y;
   }

   private static float Yon2Qgun0r(0bD var0) {
      return var0.colorAnimation;
   }

   private static int QNSuIGFXN4(0bD var0) {
      return var0.width;
   }

   private static float bBnvtOQ27c(0bD var0) {
      return var0.sizeAnimation;
   }

   private static float OhdWpGiLEt(0bD var0) {
      return var0.colorAnimation;
   }

   private static int Sb1qB4JPyK(0bD var0) {
      return var0.id;
   }

   private static int tyOsE1zWbT(0bD var0) {
      return var0.width;
   }

   public int getId() {
      return Sb1qB4JPyK(this);
   }

   private static int FOQ3rrzLvj(0bD var0) {
      return var0.x;
   }

   private static int IA2gNGRmS9(0bD var0) {
      return var0.height;
   }

   public boolean isHovered(int mouseX, int mouseY) {
      return (boolean)(mouseX >= W1geUEfLC0(this) - L11Zlr97iJ(this) / (16654 ^ -193 ^ 3819 ^ -20264) && mouseX <= L86L6RHBdY(this) - 0oMfWOltD6(this) / (24290 ^ -3317 ^ 18966 ^ -6147) + QNSuIGFXN4(this) && mouseY >= V7KJ2b4DHd(this) - NM49ImqbaE(this) / (26138 ^ -17844 ^ 23405 ^ -30919) && mouseY <= aDqvCWQjJ9(this) - DwxjkB2FAk(this) / (713 ^ -27082 ^ 10459 ^ -17370) + IA2gNGRmS9(this) ? 24637 ^ -6586 ^ 15071 ^ -17243 : 28315 ^ -3919 ^ 16312 ^ -24174);
   }

   private static int aiAatvkQtq(0bD var0) {
      return var0.height;
   }

   private static int TinawQTalf(0bD var0) {
      return var0.x;
   }

   private static int BIk9VpJIb9(0bD var0) {
      return var0.y;
   }

   private static float _EnJoYcp4I/* $FF was: 9EnJoYcp4I*/(0bD var0) {
      return var0.sizeAnimation;
   }

   private static int W1geUEfLC0(0bD var0) {
      return var0.x;
   }

   private static String oOINm6BHn9(0bD var0) {
      return var0.text;
   }

   private static void wQATaqwBOw(0bD var0, float var1) {
      var0.colorAnimation = var1;
   }

   private static float q3niyo7CbH(0bD var0) {
      return var0.colorAnimation;
   }

   private static void gDJyBtg98i(0bD var0, float var1) {
      var0.sizeAnimation = var1;
   }

   private static int NM49ImqbaE(0bD var0) {
      return var0.height;
   }

   private static int L11Zlr97iJ(0bD var0) {
      return var0.width;
   }

   private static int DwxjkB2FAk(0bD var0) {
      return var0.height;
   }

   private static int znViaacURi(0bD var0) {
      return var0.height;
   }
}
