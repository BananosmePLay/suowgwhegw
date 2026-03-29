package neo;

import net.minecraft.util.math.BlockPos;

public class 0I {
   private static String _ _;

   private static double method_jR(0f var0) {
      return var0.posX;
   }

   private static double method_jT(0f var0) {
      return var0.posZ;
   }

   private static double method_jS(0f var0) {
      return var0.posY;
   }

   public static float[] method_jP(BlockPos a, 0f b) {
      double c = (double)a.getX() - method_jR(b);
      double d = (double)a.getY() - (method_jS(b) + (double)b.getEyeHeight()) + Double.longBitsToDouble(-7642644541645643790L ^ -6190233661818658830L);
      double e = (double)a.getZ() - method_jT(b);
      double f = Math.sqrt(c * c + e * e);
      float g = (float)Math.toDegrees(Math.atan2(e, c)) - Float.intBitsToFloat(15402 ^ 95725 ^ '땐' ^ 513728617 ^ 14805 ^ 71901 ^ 'ꊮ' ^ 1546297176);
      float h = (float)(-Math.toDegrees(Math.atan2(d, f)));
      h += (float)(Math.random() - Double.longBitsToDouble(-1175555494838215258L ^ -3436362507778204250L)) * Float.intBitsToFloat('꾹' ^ 16043 ^ '롪' ^ -857994824 ^ 19564 ^ 468498 ^ '\ue871' ^ -1931762993) * Float.intBitsToFloat(28313 ^ 21876 ^ 6489 ^ 1472894602 ^ '跂' ^ '\ued84' ^ 30517 ^ 1778806656);
      float[] var10000 = new float[31337 ^ -13331 ^ 10609 ^ -26377];
      var10000[23279 ^ -25877 ^ 16947 ^ -32201] = g;
      var10000[19154 ^ -2184 ^ 22954 ^ -7167] = h;
      return var10000;
   }

   public _I/* $FF was: 0I*/() {
   }

   public static float method_jQ(float a, float b, float c, float d) {
      float e = b - a;
      if (Math.abs(e) > Float.intBitsToFloat(1457 ^ 494709 ^ 22340 ^ 1373359118 ^ 29536 ^ 1029784 ^ 18856 ^ 317169374)) {
         e = (Float.intBitsToFloat(516101 ^ 496785 ^ 27992 ^ 2075378494 ^ 1043102 ^ 1019566 ^ 26453 ^ 940038551) - Math.abs(e)) * (e > Float.intBitsToFloat(523882 ^ 482746 ^ 14908 ^ -1064086268 ^ 8276 ^ 492311 ^ 520161 ^ -1064075190) ? Float.intBitsToFloat(118997 ^ 113250 ^ 8031 ^ 264286492 ^ 128171 ^ 'ꏵ' ^ 118524 ^ -1337959082) : Float.intBitsToFloat(9292 ^ 490363 ^ 503893 ^ 1859353751 ^ 25503 ^ '\udb45' ^ 28670 ^ 1364411601));
      }

      float f = e * c;
      float g = (float)(Math.random() - Double.longBitsToDouble(-6927202026520896479L ^ -6900180428756673503L)) * Float.intBitsToFloat(10454 ^ 2053031 ^ 2092506 ^ 768565131 ^ 1740 ^ 2061228 ^ 2093496 ^ 323964408);
      return a + f * d + g;
   }
}
