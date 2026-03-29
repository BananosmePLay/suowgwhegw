package neo;

import java.sql.Timestamp;
import java.util.Arrays;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.util.math.MathHelper;

public class 0el {
   public static int nextIndex;
   public static final float[] tickRates = new float[25978 ^ -17900 ^ 4704 ^ -13030];
   public static long millis;
   public static long timeLastTimeUpdate;

   private static long RhyXODHcGF() {
      return millis;
   }

   private static void elTWRywzjV(int var0) {
      nextIndex = var0;
   }

   private static long etkEyubva5() {
      return timeLastTimeUpdate;
   }

   private static void LshH0LwijW(long var0) {
      millis = var0;
   }

   @0X
   public void onReceivePacket(0v event) {
      if (event.getPacket() instanceof SPacketTimeUpdate || event.getPacket() instanceof SPacketChat || event.getPacket() instanceof SPacketSetExperience || event.getPacket() instanceof SPacketUpdateBossInfo) {
         LshH0LwijW((new Timestamp(System.currentTimeMillis())).getTime());
         onTimeUpdate();
      }

   }

   public static float getTickRate() {
      float numTicks = Float.intBitsToFloat(7900 ^ 4161602 ^ 624 ^ 1746908561 ^ 4948 ^ 4167639 ^ 22647 ^ 1746924939);
      float sumTickRates = Float.intBitsToFloat(28318 ^ 99552 ^ 130118 ^ 1933355450 ^ 23672 ^ 111428 ^ 120323 ^ 1933346493);
      float[] arrayOfFloat;
      int i = (arrayOfFloat = qIwF8xUTV1()).length;

      for(byte b = 29837 ^ -13194 ^ 14744 ^ -32413; b < i; b = (byte)(b + (1868 ^ -952 ^ 4965 ^ -6048))) {
         float tickRate = arrayOfFloat[b];
         if (tickRate > Float.intBitsToFloat(5651 ^ '쇸' ^ 'ꏨ' ^ -431613251 ^ 7970 ^ 226697 ^ 'ﳓ' ^ -431621946)) {
            sumTickRates += tickRate;
            numTicks += Float.intBitsToFloat('휊' ^ '\uf1ee' ^ 15431 ^ -1427020669 ^ '\ue57c' ^ '蒎' ^ 14925 ^ -1787746913);
         }
      }

      return MathHelper.clamp(sumTickRates / numTicks, Float.intBitsToFloat(117309 ^ 109861 ^ 3464 ^ 938261503 ^ 121635 ^ '뷳' ^ 119346 ^ 938239373), Float.intBitsToFloat(126018 ^ 81851 ^ 16835 ^ 1898475838 ^ 5222 ^ 109903 ^ 124588 ^ 814267521));
   }

   private static void _5ua6mDbrn/* $FF was: 75ua6mDbrn*/(long var0) {
      timeLastTimeUpdate = var0;
   }

   private static void uICZFL0FO2(int var0) {
      nextIndex = var0;
   }

   public _el/* $FF was: 0el*/() {
   }

   private static float[] qIwF8xUTV1() {
      return tickRates;
   }

   private static void onTimeUpdate() {
      if (etkEyubva5() != -1L) {
         float timeElapsed = (float)(System.currentTimeMillis() - qK1LIaVw1d()) / Float.intBitsToFloat('\uf3fc' ^ 16732277 ^ 13982 ^ -897590139 ^ '\ue315' ^ 208359 ^ 28153 ^ -1896224103);
         7OwMYDBaQF()[I2oi2YS5Z7() % doQVnlai7G().length] = MathHelper.clamp(Float.intBitsToFloat('邇' ^ 111986 ^ '캃' ^ 1125970810 ^ 17217 ^ 73420 ^ '\ud97e' ^ 45915391) / timeElapsed, Float.intBitsToFloat(17915 ^ '쨴' ^ 28981 ^ -1609335834 ^ 6443 ^ '\uec1c' ^ 12048 ^ -1609343173), Float.intBitsToFloat(27012 ^ 235102 ^ 13462 ^ 63559045 ^ '頃' ^ 24647 ^ '\ue072' ^ 1114244863));
         elTWRywzjV(a1NKHQboIT() + (28532 ^ -26537 ^ 6541 ^ -4433));
      }

      AKiDOLDviH(System.currentTimeMillis());
   }

   private static float[] _OwMYDBaQF/* $FF was: 7OwMYDBaQF*/() {
      return tickRates;
   }

   private static int a1NKHQboIT() {
      return nextIndex;
   }

   public static long getLagPackets() {
      return (new Timestamp(System.currentTimeMillis())).getTime() - RhyXODHcGF();
   }

   private static int I2oi2YS5Z7() {
      return nextIndex;
   }

   private static long qK1LIaVw1d() {
      return timeLastTimeUpdate;
   }

   public static String getLagFormatColor() {
      long lagms = getLagPackets();
      if (lagms < 900L) {
         return 7QjvKNnp15("˸Ⱦ˸ȳ");
      } else {
         return lagms > 900L && lagms < 1400L ? 7QjvKNnp15("˸ɩ˸ȳ") : 7QjvKNnp15("˸ȼ˸ȳ");
      }
   }

   public static void init() {
      uICZFL0FO2(3634 ^ -13605 ^ 30760 ^ -17215);
      75ua6mDbrn(-1L);
      Arrays.fill(3zUDa4Dv82(), Float.intBitsToFloat(11884 ^ 13521 ^ 2061 ^ 1759185441 ^ '表' ^ 6063 ^ '谳' ^ 1759185765));
      0m.register(new 0el());
   }

   private static float[] doQVnlai7G() {
      return tickRates;
   }

   private static void AKiDOLDviH(long var0) {
      timeLastTimeUpdate = var0;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _QjvKNnp15/* $FF was: 7QjvKNnp15*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18200 ^ -31091 ^ 15083 ^ -1154; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 4524 ^ -30429 ^ 28077 ^ -2179));
      }

      return var1.toString();
   }

   private static float[] _zUDa4Dv82/* $FF was: 3zUDa4Dv82*/() {
      return tickRates;
   }
}
