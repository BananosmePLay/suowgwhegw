package neo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.util.math.MathHelper;

public class 0ei implements 0eB {
   public static final float PI2 = 6.2831855F;
   public static final float PI = 3.1415927F;

   public _ei/* $FF was: 0ei*/() {
   }

   public static int clamp(int value, int min, int max) {
      if (value < min) {
         return min;
      } else {
         return value > max ? max : value;
      }
   }

   public static Double interpolate1(double oldValue, double newValue, double interpolationValue) {
      return oldValue + (newValue - oldValue) * interpolationValue;
   }

   public static double interpolate(double current, double old, double scale) {
      return old + (current - old) * scale;
   }

   public static float calculateGaussianValue(float x, float sigma) {
      double PI = Double.longBitsToDouble(3347267898937384105L ^ 7960890315380159889L);
      double output = Double.longBitsToDouble(-6230053319631141488L ^ -7603651205979142768L) / Math.sqrt(Double.longBitsToDouble(-612649534331549131L ^ -5224335552758937035L) * PI * (double)(sigma * sigma));
      return (float)(output * Math.exp((double)(-(x * x)) / (Double.longBitsToDouble(7601114435493849356L ^ 2989428417066461452L) * (double)(sigma * sigma))));
   }

   public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
      return (int)interpolate((double)oldValue, (double)newValue, (double)((float)interpolationValue));
   }

   private static RoundingMode vddVZOWYgr() {
      return RoundingMode.HALF_UP;
   }

   public static double round(double value, int places) {
      if (places < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal bd = new BigDecimal(value);
         bd = bd.setScale(places, vddVZOWYgr());
         return bd.doubleValue();
      }
   }

   public static float rotate(float from, float to, float minstep, float maxstep) {
      float f = wrapDegrees(to - from) * clamp(Float.intBitsToFloat(5904 ^ 27236 ^ 23406 ^ 1714061380 ^ '螿' ^ 111652 ^ '\ueff5' ^ 1496507306), Float.intBitsToFloat(15160 ^ 17720 ^ 5283 ^ 1916679694 ^ '쎊' ^ 15123 ^ '\ue535' ^ 1916682497), Float.intBitsToFloat(122022 ^ 115908 ^ 29306 ^ 953582169 ^ 129748 ^ 107750 ^ 10076 ^ 123113775));
      if (f < Float.intBitsToFloat(12658 ^ 103118 ^ 1199 ^ 114212681 ^ 30624 ^ 116673 ^ 5032 ^ 114211731)) {
         f = clamp(f, -maxstep, -minstep);
      } else {
         f = clamp(f, minstep, maxstep);
      }

      return Math.abs(f) > Math.abs(wrapDegrees(to - from)) ? to : from + f;
   }

   public static int getMiddle(int old, int newValue) {
      return (old + newValue) / (10132 ^ -271 ^ 3398 ^ -11231);
   }

   public static float clamp(float value, float min, float max) {
      if (value < min) {
         return min;
      } else {
         return value > max ? max : value;
      }
   }

   public static float checkAngle(float one, float two, float three) {
      float f = MathHelper.wrapDegrees(one - two);
      if (f < -three) {
         f = -three;
      }

      if (f >= three) {
         f = three;
      }

      return one - f;
   }

   public static double wrapDegrees(double value) {
      value %= Double.longBitsToDouble(2494463188979254751L ^ 7127119093171586527L);
      if (value >= Double.longBitsToDouble(-2963129933869098618L ^ -7600289437688800890L)) {
         value -= Double.longBitsToDouble(-4876183275215415573L ^ -278430268135205141L);
      }

      if (value < Double.longBitsToDouble(1484212796840134295L ^ -3098903511451122025L)) {
         value += Double.longBitsToDouble(7863590141707921639L ^ 3267244509511264487L);
      }

      return value;
   }

   public static Object getRandomInRange(int i, int i1) {
      return null;
   }

   public static float lerp(double start, double end, double step) {
      return (float)(start + (end - start) * step);
   }

   public static float wrapDegrees(float value) {
      value %= Float.intBitsToFloat(240980 ^ 25397 ^ 256370 ^ 2012840066 ^ 231101 ^ '\udb4e' ^ 236038 ^ 877486180);
      if (value >= Float.intBitsToFloat('锽' ^ '\ue752' ^ 21680 ^ 1005279037 ^ 'ꄑ' ^ 76629 ^ 3725 ^ 2027877675)) {
         value -= Float.intBitsToFloat(258634 ^ 240305 ^ 29060 ^ 1728434038 ^ 252903 ^ 259366 ^ 4098 ^ 615635146);
      }

      if (value < Float.intBitsToFloat(128422 ^ 112232 ^ 14901 ^ -604588494 ^ 15024 ^ 92009 ^ 126701 ^ 415399165)) {
         value += Float.intBitsToFloat(8385603 ^ 8365366 ^ 14663 ^ -1832933445 ^ 523994 ^ '说' ^ 516069 ^ -787782334);
      }

      return value;
   }

   public static double clamp(double value, double min, double max) {
      if (value < min) {
         return min;
      } else {
         return value > max ? max : value;
      }
   }

   public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
      return (float)interpolate((double)oldValue, (double)newValue, (double)((float)interpolationValue));
   }

   public static int intRandom(int max, int min) {
      return (int)(Math.random() * (double)(max - min)) + min;
   }

   public static long clamp(long value, long min, long max) {
      if (value < min) {
         return min;
      } else {
         return value > max ? max : value;
      }
   }

   public static double randomNumber(double max, double min) {
      return Math.random() * (max - min) + min;
   }

   public static short clamp(short value, short min, short max) {
      if (value < min) {
         return min;
      } else {
         return value > max ? max : value;
      }
   }
}
