package neo;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.play.server.SPacketTimeUpdate;

public class 0cz extends 0cB {
   public static 0bv snow = new 0bv(a0rILp6qpZ("̞̖̂̐"), (boolean)(6591 ^ -24311 ^ 9709 ^ -25254));
   public static 0bv worldColor = new 0bv(a0rILp6qpZ("̹̝̟̓͢͡܃̖ͥ̑͡܃̛̟ͣ̓"), (boolean)(15527 ^ -19939 ^ 18321 ^ -14037));
   public 0by ambienceMode;
   public 0bz ambienceSpeed;
   public long spinTime = 0L;
   public 0bv ambience = new 0bv(a0rILp6qpZ("̱̖̟ͣͬ܃̝̙͢͠͡"), (boolean)(18465 ^ -17203 ^ 21684 ^ -24488));
   public static 0bw weatherColor = new 0bw(a0rILp6qpZ("̖̅̑͡܃̜̝̝̗̐ͨ"), (new Color(33534819 ^ 33530252 ^ 1552 ^ 16771840)).getRGB());
   public static 0bw worldColors = new 0bw(a0rILp6qpZ("̖̅̑͡܃̛̟ͣ̓"), (new Color(33532788 ^ 33552232 ^ 10272 ^ 16747459)).getRGB());

   private static boolean YdvJ1iD1EW(0bv var0) {
      return var0.value;
   }

   private static WorldClient _VTrPoFWFe/* $FF was: 5VTrPoFWFe*/(Minecraft var0) {
      return var0.world;
   }

   @0X
   public void onPacket(0v event) {
      if (FWy7r1dwwN(q4GBBLbjTr(this)) && event.getPacket() instanceof SPacketTimeUpdate) {
         event.setCancelled((boolean)(30620 ^ -524 ^ 15193 ^ -20176));
      }

   }

   public _cz/* $FF was: 0cz*/() {
      super(a0rILp6qpZ("ݴ\u074cݑݏ݇"), 0bV.Render);
      String var10003 = a0rILp6qpZ("̛̜́");
      String var10004 = a0rILp6qpZ("ݧ݂ݚ");
      String[] var10005 = new String[25563 ^ -3851 ^ 10097 ^ -19365];
      var10005[5691 ^ -16039 ^ 22384 ^ -32750] = a0rILp6qpZ("ݭ݄݊\u074bݗ");
      var10005[9814 ^ -6276 ^ 11491 ^ -4664] = a0rILp6qpZ("ݮ\u074cݑݍ݊ݍ݄");
      var10005[16420 ^ -25240 ^ 19722 ^ -28604] = a0rILp6qpZ("ݰݖݍݐ݆ݗ");
      var10005[13109 ^ -4512 ^ 11150 ^ -2344] = a0rILp6qpZ("ݰݓ݊ݍ");
      this.ambienceMode = new 0by(var10003, var10004, var10005);
      this.ambienceSpeed = new 0bz(a0rILp6qpZ("̙̝̝̂ͣͯ͢͡"), Float.intBitsToFloat(28209 ^ 'ꈰ' ^ 4073 ^ 2087733891 ^ 27260 ^ '菠' ^ 30735 ^ 1037048056), Float.intBitsToFloat(5724 ^ '\ue241' ^ 1293 ^ 1745507539 ^ '謠' ^ 227793 ^ 25598 ^ 1439037441), Float.intBitsToFloat(101918 ^ 108834 ^ 16587 ^ 1497536966 ^ 101872 ^ 105980 ^ 31290 ^ 490247175), Float.intBitsToFloat(13124 ^ 243827 ^ 257222 ^ -1137068519 ^ 256027 ^ 230523 ^ 30616 ^ -2084976112));
      0bC[] var10001 = new 0bC[23097 ^ -11489 ^ 19817 ^ -15288];
      var10001[30578 ^ -15634 ^ 25950 ^ -12094] = snow;
      var10001[19813 ^ -24109 ^ 7746 ^ -3339] = weatherColor;
      var10001[2599 ^ -2511 ^ 4614 ^ -4590] = worldColor;
      var10001[14116 ^ -9720 ^ 14549 ^ -10758] = worldColors;
      var10001[19412 ^ -282 ^ 4080 ^ -17722] = this.ambience;
      var10001[12327 ^ -23140 ^ 6797 ^ -28877] = this.ambienceMode;
      var10001[18869 ^ -12181 ^ 32692 ^ -6548] = this.ambienceSpeed;
      this.addSetting(var10001);
   }

   private static 0by gNINYNtV4j(0cz var0) {
      return var0.ambienceMode;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String a0rILp6qpZ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11251 ^ -15140 ^ 945 ^ -4962; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7712 ^ -21043 ^ 10444 ^ -25598));
      }

      return var1.toString();
   }

   private static 0by nx11mrueRx(0cz var0) {
      return var0.ambienceMode;
   }

   private static long oIHbTdD0Dc(0cz var0) {
      return var0.spinTime;
   }

   private static Minecraft J17nKjQtji() {
      return 0eB.mc;
   }

   private static long g9cyjBqTuT(0cz var0) {
      return var0.spinTime;
   }

   private static 0by wxlSU6jLBt(0cz var0) {
      return var0.ambienceMode;
   }

   private static float _x1UTXijvk/* $FF was: 2x1UTXijvk*/(0bz var0) {
      return var0.value;
   }

   private static 0by DF8rhTnOwt(0cz var0) {
      return var0.ambienceMode;
   }

   private static boolean FWy7r1dwwN(0bv var0) {
      return var0.value;
   }

   @0X
   public void onUpdate(0K event) {
      if (YdvJ1iD1EW(FjFpFnoWtA(this))) {
         if (nx11mrueRx(this).is(a0rILp6qpZ("ݰݓ݊ݍ"))) {
            C4tdvDtS6b(J17nKjQtji()).setWorldTime(g9cyjBqTuT(this));
            CcqWmyaay0(this, (long)((float)oIHbTdD0Dc(this) + 2x1UTXijvk(Bcj6IWauLe(this))));
         } else if (DF8rhTnOwt(this).is(a0rILp6qpZ("ݧ݂ݚ"))) {
            5VTrPoFWFe(tw2YtWeZcv()).setWorldTime(5000L);
         } else if (gNINYNtV4j(this).is(a0rILp6qpZ("ݭ݄݊\u074bݗ"))) {
            fSbeg4Q2ng(NnT9zO24uI()).setWorldTime(17000L);
         } else if (wxlSU6jLBt(this).is(a0rILp6qpZ("ݮ\u074cݑݍ݊ݍ݄"))) {
            prQ6Wwh426(hwW9dNGcWd()).setWorldTime(0L);
         } else if (Xq06Fl6Sql(this).is(a0rILp6qpZ("ݰݖݍݐ݆ݗ"))) {
            PV22x7SVBW(Ht3QiKh6Wp()).setWorldTime(13000L);
         }
      }

   }

   private static WorldClient fSbeg4Q2ng(Minecraft var0) {
      return var0.world;
   }

   private static WorldClient PV22x7SVBW(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft hwW9dNGcWd() {
      return 0eB.mc;
   }

   private static 0by Xq06Fl6Sql(0cz var0) {
      return var0.ambienceMode;
   }

   private static Minecraft NnT9zO24uI() {
      return 0eB.mc;
   }

   private static Minecraft tw2YtWeZcv() {
      return 0eB.mc;
   }

   private static 0bv FjFpFnoWtA(0cz var0) {
      return var0.ambience;
   }

   private static WorldClient C4tdvDtS6b(Minecraft var0) {
      return var0.world;
   }

   private static 0bv q4GBBLbjTr(0cz var0) {
      return var0.ambience;
   }

   private static Minecraft Ht3QiKh6Wp() {
      return 0eB.mc;
   }

   private static WorldClient prQ6Wwh426(Minecraft var0) {
      return var0.world;
   }

   private static void CcqWmyaay0(0cz var0, long var1) {
      var0.spinTime = var1;
   }

   private static 0bz Bcj6IWauLe(0cz var0) {
      return var0.ambienceSpeed;
   }
}
