package neo;

import java.util.Random;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class 0bs {
   public int size;
   public int x;
   public int fallingSpeed;
   public int y;

   public void update(ScaledResolution res) {
      Gui.drawRect(NiKBJmqwWP(this), 7ked9N4yuy(this), zyGIFRhSJb(this) + OFsV7B72JI(this), r7r2dyvNVA(this) + npAqr5coAM(this), 10565 ^ 238997 ^ 258303 ^ -1714832918);
      0DWiQf46lc(this, wW470kg1nA(this) + 47eBigeLK1(this));
      if (eA9t71pHDg(this) > res.getScaledHeight() + (17081 ^ -26807 ^ 4457 ^ -15213) || P4y8WFYIDt(this) < (-4 ^ -24907 ^ 5422 ^ -29807)) {
         GoO6VmJbri(this, -9211 ^ -19069 ^ 22034 ^ -16286);
         Random rand = new Random();
         W1TZlqtxrV(this, rand.nextInt(7262 ^ -12947 ^ 1778 ^ -10293) + (25869 ^ -29588 ^ 17244 ^ -21956));
         dtiLH4mLiq(this, rand.nextInt(20170 ^ -4369 ^ 58 ^ -24549) + (24167 ^ -5783 ^ 17459 ^ -3268));
      }

   }

   private static int zyGIFRhSJb(0bs var0) {
      return var0.x;
   }

   private static int npAqr5coAM(0bs var0) {
      return var0.size;
   }

   private static int P4y8WFYIDt(0bs var0) {
      return var0.y;
   }

   private static void W1TZlqtxrV(0bs var0, int var1) {
      var0.fallingSpeed = var1;
   }

   private static int OFsV7B72JI(0bs var0) {
      return var0.size;
   }

   private static int NiKBJmqwWP(0bs var0) {
      return var0.x;
   }

   public _bs/* $FF was: 0bs*/(int x, int y, int fallspeed, int size) {
      this.x = x;
      this.y = y;
      this.fallingSpeed = fallspeed;
      this.size = size;
   }

   private static int _7eBigeLK1/* $FF was: 47eBigeLK1*/(0bs var0) {
      return var0.fallingSpeed;
   }

   private static void _DWiQf46lc/* $FF was: 0DWiQf46lc*/(0bs var0, int var1) {
      var0.y = var1;
   }

   private static int eA9t71pHDg(0bs var0) {
      return var0.y;
   }

   private static int _ked9N4yuy/* $FF was: 7ked9N4yuy*/(0bs var0) {
      return var0.y;
   }

   private static void GoO6VmJbri(0bs var0, int var1) {
      var0.y = var1;
   }

   private static void dtiLH4mLiq(0bs var0, int var1) {
      var0.size = var1;
   }

   private static int wW470kg1nA(0bs var0) {
      return var0.y;
   }

   private static int r7r2dyvNVA(0bs var0) {
      return var0.y;
   }
}
