package neo;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class 0bY extends 0cB {
   public final 0ek timer = new 0ek();
   public static ArrayList<0bW> records;
   public static 0by trigger;
   public static 0bz runDelay;
   public static boolean warning;
   public static 0bA message;

   public _bY/* $FF was: 0bY*/() {
      super(QBS72I5nqd("БгФйпоЂегпТдеТ"), 0bV.Bots);
      0bC[] var10001 = new 0bC[9927 ^ -7883 ^ 9568 ^ -7535];
      var10001[12849 ^ -8248 ^ 8430 ^ -13033] = trigger;
      var10001[15048 ^ -28424 ^ 29643 ^ -9734] = runDelay;
      var10001[13551 ^ -9977 ^ 17576 ^ -22206] = message;
      this.addSetting(var10001);
      records = new ArrayList();
   }

   private static ArrayList BE4Yx7DJik() {
      return records;
   }

   private static KeyBinding RIBZWoqal5(GameSettings var0) {
      return var0.keyBindLeft;
   }

   private static GameSettings AZozyt0dB6() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding PjoV16Sg0L(GameSettings var0) {
      return var0.keyBindRight;
   }

   private static 0bX fw4HtSD4Mw() {
      return 0bX.KEYBOARD;
   }

   private static void _5DWc4A6EF/* $FF was: 85DWc4A6EF*/(boolean var0) {
      warning = var0;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String QBS72I5nqd(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 2483 ^ -10520 ^ 17428 ^ -25777; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 9274 ^ -16072 ^ 28633 ^ -29045));
      }

      return var1.toString();
   }

   static {
      String var10002 = QBS72I5nqd("ЄТйззеТ");
      String var10003 = QBS72I5nqd("Опое");
      String[] var10004 = new String[30667 ^ -6994 ^ 6607 ^ -30034];
      var10004[15213 ^ -14419 ^ 24555 ^ -23765] = QBS72I5nqd("поКпйо");
      var10004[13276 ^ -10398 ^ 12945 ^ -10706] = QBS72I5nqd("поБХФи");
      var10004[14224 ^ -9487 ^ 12369 ^ -8910] = QBS72I5nqd("поНеУУбзе");
      var10004[1701 ^ -32747 ^ 20551 ^ -10508] = QBS72I5nqd("поЗбнеЗХбТд");
      trigger = new 0by(var10002, var10003, var10004);
      runDelay = new 0bz(QBS72I5nqd("ЂХоѰДембЩ"), Float.intBitsToFloat('ꇻ' ^ 205967 ^ 1052 ^ -1854294227 ^ '鳖' ^ 23566 ^ '\ue9c1' ^ -1854267556), Float.intBitsToFloat(18444 ^ 253262 ^ 2970 ^ 2106178528 ^ 21697 ^ 247501 ^ 3650 ^ 2106177910), Float.intBitsToFloat(15874 ^ '遰' ^ '쨧' ^ -885834794 ^ 127084 ^ 22602 ^ 120963 ^ -1903644890), Float.intBitsToFloat('\uf120' ^ '都' ^ 17413 ^ 1720077560 ^ '멄' ^ '븳' ^ 15237 ^ 665212626));
      message = new 0bA(QBS72I5nqd("НеУУбзе"), QBS72I5nqd("ФТзТЏнУз"), () -> {
         return TvNR6ZTtmY().is(QBS72I5nqd("поНеУУбзе"));
      });
   }

   private static GameSettings _dSVzgh92v/* $FF was: 2dSVzgh92v*/() {
      return Minecraft.gameSettings;
   }

   @0X
   public void onPreMotionEvent(0G event) {
      if (yNFeWN51wg(this).hasReached(Double.longBitsToDouble(-3934885621838107460L ^ -8552201139799708484L))) {
         aeWqYQSilJ(this).reset();
         if (qaZ8oYndAw().size() > (26280 ^ -30424 ^ 10991 ^ -15205) && !tZYtqYNwQ9()) {
            0dK.formatMsg(QBS72I5nqd("ѶгDnkc`\u001fѰg`oh\u0011\u001cѰdei\u0011\u0012bhiѰlnfe\u0012Ѱmec`\u0012hbmnѰ\u0011j`g`\u0012\u001c\u0011\u001fѰm`Ѱan\u0012`\u0015ѱ"));
            r2PDM7D9OZ((boolean)(10193 ^ -12852 ^ 28676 ^ -26088));
         }

         BE4Yx7DJik().add(new 0bW(fw4HtSD4Mw(), JrTiWSrFOO(W4nTseHjEf()).isKeyDown(), zLdVzDMhMQ(CbWC7jAMVD()).isKeyDown(), RIBZWoqal5(j07j7jo3rG()).isKeyDown(), PjoV16Sg0L(Q0ya1StZvJ()).isKeyDown(), event.getYaw(), event.getPitch(), m6FgyJhaY7(2dSVzgh92v()).isKeyDown(), ZJn9DmgSXt(AZozyt0dB6()).isKeyDown(), WeQTok0jHz(qJLFiG97VZ()).isKeyDown()));
      }

   }

   @0X
   public void onMessage(0u event) {
      String msg = event.getMessage();
      if (!msg.startsWith(QBS72I5nqd("Ѿ"))) {
         GWfMrOeppl().add(new 0bW(0vWLqigjav(), msg));
      }

   }

   private static KeyBinding m6FgyJhaY7(GameSettings var0) {
      return var0.keyBindSneak;
   }

   private static KeyBinding WeQTok0jHz(GameSettings var0) {
      return var0.keyBindJump;
   }

   private static ArrayList qaZ8oYndAw() {
      return records;
   }

   private static KeyBinding JrTiWSrFOO(GameSettings var0) {
      return var0.keyBindForward;
   }

   private static GameSettings qJLFiG97VZ() {
      return Minecraft.gameSettings;
   }

   private static ArrayList _3s9CNijyQ/* $FF was: 93s9CNijyQ*/() {
      return records;
   }

   private static GameSettings Q0ya1StZvJ() {
      return Minecraft.gameSettings;
   }

   private static void r2PDM7D9OZ(boolean var0) {
      warning = var0;
   }

   public void onDisable() {
      super.onDisable();
      0dK.formatMsg(QBS72I5nqd("G`oh\u0011\u001cѰdei\u0011\u0012bhiѰn\u0011\u0012`mnbkem`ѱѰG`oh\u0011`mnѰ") + WKJAnyLsC1().size() + QBS72I5nqd("Ѱ\u001dkelem\u0012nbѾ"));
   }

   private static GameSettings CbWC7jAMVD() {
      return Minecraft.gameSettings;
   }

   private static ArrayList WKJAnyLsC1() {
      return records;
   }

   private static boolean tZYtqYNwQ9() {
      return warning;
   }

   private static GameSettings j07j7jo3rG() {
      return Minecraft.gameSettings;
   }

   private static GameSettings W4nTseHjEf() {
      return Minecraft.gameSettings;
   }

   private static KeyBinding ZJn9DmgSXt(GameSettings var0) {
      return var0.keyBindSprint;
   }

   private static 0ek aeWqYQSilJ(0bY var0) {
      return var0.timer;
   }

   private static KeyBinding zLdVzDMhMQ(GameSettings var0) {
      return var0.keyBindBack;
   }

   private static 0by TvNR6ZTtmY() {
      return trigger;
   }

   public void onEnable() {
      super.onEnable();
      93s9CNijyQ().clear();
      85DWc4A6EF((boolean)(8733 ^ -17286 ^ 21479 ^ -12928));
      0dK.formatMsg(QBS72I5nqd("G`oh\u0011\u001cѰdei\u0011\u0012bhiѰg`o\u0013\u0019em`ѱ"));
   }

   private static 0bX _vWLqigjav/* $FF was: 0vWLqigjav*/() {
      return 0bX.CHAT;
   }

   private static 0ek yNFeWN51wg(0bY var0) {
      return var0.timer;
   }

   private static ArrayList GWfMrOeppl() {
      return records;
   }
}
