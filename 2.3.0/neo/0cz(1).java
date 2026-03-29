package neo;

import java.io.IOException;
import java.util.List;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;

public class 0cz extends lg {
   public lg field_e;
   public 0cy field_d;
   public 0cy field_a;
   public static 0dC field_c = 0dC.method_bBI();
   public lE field_b;
   private final int field_f = 5205 ^ -15821 ^ 31501 ^ -21159;
   private static String _DSC GG NEOWARECLIENT _;

   private static int method_buI(jK var0) {
      return var0.id;
   }

   private static int method_btZ(0cz var0) {
      return var0.width;
   }

   private static jH method_buh(0cz var0) {
      return var0.fontRenderer;
   }

   private static 0dC method_bvo() {
      return field_c;
   }

   private static 0dB method_buU() {
      return 0dB.field_e;
   }

   private static 0cy method_buD(0cz var0) {
      return var0.field_d;
   }

   private static 0dB method_bvg() {
      return 0dB.field_b;
   }

   private static 0dB method_bvG() {
      return 0dB.field_b;
   }

   private static int method_buj(0cz var0) {
      return var0.height;
   }

   private static int method_bud(0cz var0) {
      return var0.height;
   }

   private static int method_buK(jK var0) {
      return var0.id;
   }

   private static int method_buf(0cz var0) {
      return var0.width;
   }

   private static 0dC method_bvj() {
      return field_c;
   }

   private static void method_buw(0cz var0, 0cy var1) {
      var0.field_a = var1;
   }

   private static jH method_bun(0cz var0) {
      return var0.fontRenderer;
   }

   private static 0cy method_bvA(0cz var0) {
      return var0.field_a;
   }

   private static int method_bvD(0cz var0) {
      return var0.height;
   }

   private static lE method_bvE(0cz var0) {
      return var0.field_b;
   }

   private static List method_bub(0cz var0) {
      return var0.buttonList;
   }

   private static int method_bua(0cz var0) {
      return var0.height;
   }

   private static 0dC method_bvl() {
      return field_c;
   }

   private static void method_bvQ(jK var0, String var1) {
      var0.displayString = var1;
   }

   private static jH method_bvM(0cz var0) {
      return var0.fontRenderer;
   }

   protected void mouseClicked(int a, int b, int c) throws IOException {
      super.mouseClicked(a, b, c);
      method_bvv(this).mouseClicked(a, b, c);
      method_bvw(this).method_bpH(a, b, c);
      method_bvx(this).method_bpH(a, b, c);
   }

   public void updateScreen() {
      method_bvy(this).updateCursorCounter();
      method_bvz(this).method_bpp();
      method_bvA(this).method_bpp();
      super.updateScreen();
   }

   private static 0dC method_bvk() {
      return field_c;
   }

   protected void keyTyped(char a, int b) throws IOException {
      super.keyTyped(a, b);
      if (b == (27544 ^ -27805 ^ 8352 ^ -10169)) {
         this.actionPerformed((jK)method_bvr(this).get(14108 ^ -11583 ^ 1154 ^ -7842));
      }

      method_bvs(this).textboxKeyTyped(a, b);
      method_bvt(this).method_bpG(a, b);
      method_bvu(this).method_bpG(a, b);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents((boolean)(21839 ^ -8209 ^ 27964 ^ -6244));
   }

   public static void method_btP() {
      try {
         JSONObject a = new JSONObject();
         a.put(method_btU("ݠݥݥݳݤݲݲ"), (Object)method_bvi().method_bBJ());
         a.put(method_btU("ݵݸݱݤ"), (Object)method_bvj().method_bBP().name());
         a.put(method_btU("ݴݲݤݳݯݠݬݤ"), (Object)method_bvk().method_bBL());
         a.put(method_btU("ݱݠݲݲݶݮݳݥ"), (Object)method_bvl().method_bBN());
         0ee.saveFile(0ed.method_bFf(method_btU("ܮݏݤݮݖݠݳݤܮݬݢݱݳݮݹݸܯݫݲݮݯ")), a.toString());
      } catch (Exception var1) {
         Exception b = var1;
         b.printStackTrace();
      }

   }

   private static 0cy method_bur(0cz var0) {
      return var0.field_d;
   }

   private static lE method_bvs(0cz var0) {
      return var0.field_b;
   }

   private static 0dC method_buP() {
      return field_c;
   }

   private static List method_bue(0cz var0) {
      return var0.buttonList;
   }

   private static 0cy method_bvx(0cz var0) {
      return var0.field_a;
   }

   private static int method_bvK(0cz var0) {
      return var0.width;
   }

   private static 0cy method_bus(0cz var0) {
      return var0.field_d;
   }

   private static int method_bvL(0cz var0) {
      return var0.height;
   }

   private static 0dC method_bvi() {
      return field_c;
   }

   public static 0dC method_btR() {
      return method_bvq();
   }

   private static 0dC method_bvn() {
      return field_c;
   }

   private static int method_buz(jK var0) {
      return var0.id;
   }

   private static List method_bvh(0cz var0) {
      return var0.buttonList;
   }

   private static 0dC method_bvF() {
      return field_c;
   }

   private static 0cy method_bvH(0cz var0) {
      return var0.field_d;
   }

   private static lE method_bul(0cz var0) {
      return var0.field_b;
   }

   private static int method_bvC(0cz var0) {
      return var0.width;
   }

   private static 0dC method_buR() {
      return field_c;
   }

   private static 0dC method_buE() {
      return field_c;
   }

   private static 0cy method_buZ(0cz var0) {
      return var0.field_a;
   }

   private static 0cy method_bvt(0cz var0) {
      return var0.field_d;
   }

   private static void method_buq(0cz var0, 0cy var1) {
      var0.field_d = var1;
   }

   private static List method_btV(0cz var0) {
      return var0.buttonList;
   }

   private static jH method_but(0cz var0) {
      return var0.fontRenderer;
   }

   protected void actionPerformed(jK a) throws IOException {
      if (method_buz(a) == 0) {
         method_buA().method_bBK(method_buB(this).getText().replaceAll(method_btU("ݚݟݝݥܻܯݜ"), method_btU("")));
         method_buC().method_bBM(method_buD(this).method_bpr());
         method_buE().method_bBO(method_buF(this).method_bpr());
         method_buG(this).displayGuiScreen(method_buH(this));
      } else {
         if (method_buI(a) == (23765 ^ -13607 ^ 15889 ^ -22500)) {
            method_buJ(0dC.method_bBI());
         } else if (method_buK(a) == (31294 ^ -30691 ^ 21431 ^ -24169)) {
            method_buL().method_bBR(method_buM().method_bBP().next());
         } else if (method_buN(a) == (24565 ^ -25634 ^ 14380 ^ -1021)) {
            method_buO(0cG.method_bwd().method_bwe().method_kg());
            if (method_buP() == null) {
               method_buQ(0dC.method_bBI());
            }
         }

         this.method_btO();
         method_btP();
      }
   }

   private static 0cy method_buF(0cz var0) {
      return var0.field_a;
   }

   private static 0dC method_buW() {
      return field_c;
   }

   private static 0cy method_bux(0cz var0) {
      return var0.field_a;
   }

   private static 0dC method_buT() {
      return field_c;
   }

   private static lE method_bvv(0cz var0) {
      return var0.field_b;
   }

   private static lE method_bvy(0cz var0) {
      return var0.field_b;
   }

   private static 0dB method_bvd() {
      return 0dB.field_b;
   }

   private static int method_buN(jK var0) {
      return var0.id;
   }

   public boolean doesGuiPauseGame() {
      return (boolean)(19081 ^ -8074 ^ 2508 ^ -23758);
   }

   private static 0dC method_bvc() {
      return field_c;
   }

   private static int method_buo(0cz var0) {
      return var0.width;
   }

   private static int method_buc(0cz var0) {
      return var0.width;
   }

   private static void method_buk(0cz var0, lE var1) {
      var0.field_b = var1;
   }

   private static 0dC method_bvP() {
      return field_c;
   }

   private static 0dC method_bvm() {
      return field_c;
   }

   public void drawScreen(int a, int b, float c) {
      this.drawDefaultBackground();
      yh.scale(Float.intBitsToFloat(11345 ^ 113238 ^ 126942 ^ -658329036 ^ 11035 ^ 92666 ^ 123287 ^ -415078245), Float.intBitsToFloat('걈' ^ '뿒' ^ 15655 ^ -384347008 ^ '걷' ^ '騫' ^ 5728 ^ -694724095), Float.intBitsToFloat(492540 ^ 22545 ^ 496977 ^ -1519680400 ^ 12403 ^ 28425 ^ 3520 ^ -1695836042));
      this.drawCenteredString(method_bvB(this), method_btU("ݑݳݮݹݸܡ\u074cݠݯݠݦݤݳ"), method_bvC(this) / (15556 ^ -12790 ^ 20172 ^ -17408), method_bvD(this) / (19083 ^ -28855 ^ 30229 ^ -19499) - (3977 ^ -6896 ^ 14210 ^ -8919) - (4913 ^ -32390 ^ 11098 ^ -18149), -18581 ^ -26805 ^ 8846 ^ -687);
      method_bvE(this).drawTextBox();
      if (method_bvF().method_bBQ(method_bvG())) {
         method_bvH(this).method_bpI();
         method_bvI(this).method_bpI();
         this.drawString(method_bvJ(this), method_btU("ݔݲݤݳݯݠݬݤ"), method_bvK(this) / (20078 ^ -14737 ^ 701 ^ -30018) - (15487 ^ -7911 ^ 29695 ^ -20745), method_bvL(this) / (26785 ^ -26292 ^ 8940 ^ -11517) + (30658 ^ -13492 ^ 26832 ^ -11180) + (22211 ^ -23245 ^ 16417 ^ -19463) - (15038 ^ -20464 ^ 676 ^ -30664), -6557 ^ -25363 ^ 1080 ^ -32439);
         this.drawString(method_bvM(this), method_btU("ݑݠݲݲݶݮݳݥ"), method_bvN(this) / (17063 ^ -10069 ^ 14947 ^ -24467) - (12868 ^ -6760 ^ 16438 ^ -26748), method_bvO(this) / (546 ^ -28771 ^ 15562 ^ -20105) + (28108 ^ -12531 ^ 13703 ^ -26804) + (16972 ^ -9291 ^ 24658 ^ -1541) - (15200 ^ -10963 ^ 22035 ^ -18324), -13184 ^ -20892 ^ 8955 ^ -16416);
      }

      super.drawScreen(a, b, c);
   }

   private void method_btO() {
      if (method_buR().method_bBQ(method_buS())) {
         method_buT().method_bBR(method_buU());
      }

      method_buV(this).setText(method_buW().method_bBJ());
      method_buX(this).method_bpq(method_buY().method_bBL());
      method_buZ(this).method_bpq(method_bva().method_bBN());
      method_bvb(this).method_bqa(method_bvc().method_bBQ(method_bvd()));
      method_bve(this).method_bqa(method_bvf().method_bBQ(method_bvg()));
      method_bvh(this).stream().filter((a) -> {
         return (boolean)(method_bvR(a) == (26269 ^ -30662 ^ 28376 ^ -32644) ? 22889 ^ -23037 ^ 26651 ^ -26768 : 1508 ^ -30084 ^ 18732 ^ -14668);
      }).findAny().ifPresent((a) -> {
         method_bvQ(a, method_bvP().method_bBP().name().toLowerCase());
      });
   }

   public _cz/* $FF was: 0cz*/(lg a) {
      this.field_e = a;
   }

   private static 0cy method_bvw(0cz var0) {
      return var0.field_d;
   }

   private static void method_buO(0dC var0) {
      field_c = var0;
   }

   private static 0dC method_bvp() {
      return field_c;
   }

   private static 0dC method_buY() {
      return field_c;
   }

   private static int method_bui(0cz var0) {
      return var0.width;
   }

   private static lE method_buV(0cz var0) {
      return var0.field_b;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_btU(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25186 ^ -8119 ^ 17959 ^ -15348; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17919 ^ -29240 ^ 13625 ^ -1521));
      }

      return var1.toString();
   }

   private static 0dC method_buM() {
      return field_c;
   }

   private static int method_btX(0cz var0) {
      return var0.height;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents((boolean)(23476 ^ -28001 ^ 4053 ^ -14593));
      method_btV(this).add(new jK(997 ^ -13550 ^ 28604 ^ -22709, method_btW(this) / (24681 ^ -8718 ^ 720 ^ -16567) - (10453 ^ -29720 ^ 29937 ^ -10328), method_btX(this) - (8504 ^ -10204 ^ 27340 ^ -27678), 23547 ^ -4740 ^ 1623 ^ -20456, 30446 ^ -25354 ^ 22743 ^ -19749, method_btU("݅ݮݯݤ")));
      method_btY(this).add(new jK(31179 ^ -25411 ^ 22500 ^ -19821, method_btZ(this) / (17832 ^ -17364 ^ 18113 ^ -16569) + (21727 ^ -26614 ^ 28462 ^ -23597), method_bua(this) / (25930 ^ -11249 ^ 4353 ^ -24506) + (10483 ^ -32235 ^ 32318 ^ -11054) - (15684 ^ -25895 ^ 10862 ^ -29247), 23528 ^ -5901 ^ 31074 ^ -13721, 27148 ^ -2798 ^ 27041 ^ -2389, method_btU("ݓݤݲݤݵ")));
      method_bub(this).add(new jK(29847 ^ -24383 ^ 15176 ^ -4323, method_buc(this) / (24292 ^ -14245 ^ 21480 ^ -15019) + (28089 ^ -28456 ^ 16444 ^ -17125), method_bud(this) / (19213 ^ -23589 ^ 28079 ^ -31365) + (10676 ^ -8731 ^ 5551 ^ -7692) - (32192 ^ -45641 ^ '\ufde9' ^ -12884), 21385 ^ -32240 ^ 5197 ^ -14852, 9224 ^ -24506 ^ 25073 ^ -6741, method_btU("ݕݸݱݤ")));
      method_bue(this).add(new jK(18907 ^ -18900 ^ 6174 ^ -6163, method_buf(this) / (28052 ^ -9532 ^ 1546 ^ -20136) + (27280 ^ -22295 ^ 1959 ^ -14868), method_bug(this) / (10106 ^ -30179 ^ 21213 ^ -72) + (16437 ^ -22871 ^ 15666 ^ -9331) - (6215 ^ -15858 ^ 1210 ^ -8511), 13464 ^ -14294 ^ 3965 ^ -3088, 25162 ^ -20884 ^ 9613 ^ -5697, method_btU("݇ݳݤݤܡݑݳݮݹݸ")));
      method_buk(this, new lE(24979 ^ -7010 ^ 22921 ^ -9082, method_buh(this), method_bui(this) / (9646 ^ -2188 ^ 9729 ^ -2855) - (2732 ^ -29697 ^ 29533 ^ -3488), method_buj(this) / (9710 ^ -29523 ^ 25534 ^ -13569) + (24424 ^ -691 ^ 26548 ^ -14949) - (18192 ^ -10302 ^ 23958 ^ -12938), 26232 ^ -5615 ^ 19462 ^ -16205, 22056 ^ -11583 ^ 17263 ^ -14446));
      method_bul(this).setMaxStringLength(4696 ^ -19265 ^ 18388 ^ -7899);
      method_bum(this).setFocused((boolean)(2447 ^ -30694 ^ 4345 ^ -28307));
      method_buq(this, new 0cy(8054 ^ -501 ^ 27346 ^ -29779, method_bun(this), method_buo(this) / (25705 ^ -21268 ^ 21714 ^ -25515) - (2374 ^ -8127 ^ 19850 ^ -23325), method_bup(this) / (12304 ^ -30816 ^ 10651 ^ -25047) + (13521 ^ -16994 ^ 24044 ^ -11095) + (20209 ^ -19464 ^ 19062 ^ -18611) - (14836 ^ -28004 ^ 20773 ^ -1409), 26290 ^ -29826 ^ 23638 ^ -20154, 32758 ^ -8901 ^ 7434 ^ -16429));
      method_bur(this).method_bpL(18678 ^ -18563 ^ 32331 ^ -32298);
      method_bus(this).method_bpS((boolean)(7389 ^ -2420 ^ 31179 ^ -27750));
      method_buw(this, new 0cy(29306 ^ -19745 ^ 8765 ^ -7526, method_but(this), method_buu(this) / (24137 ^ -8885 ^ 32118 ^ -394) - (12542 ^ -22451 ^ 27220 ^ -3447), method_buv(this) / (8986 ^ -29851 ^ 19050 ^ -7657) + (32368 ^ -3981 ^ 10620 ^ -22667) + (160 ^ -16802 ^ 29723 ^ -13633) - (7188 ^ -10928 ^ 26323 ^ -20571), 8366 ^ -28268 ^ 15967 ^ -28743, 19455 ^ -18634 ^ 16793 ^ -17084));
      method_bux(this).method_bpL(18312 ^ -4625 ^ 15209 ^ -28392);
      method_buy(this).method_bpS((boolean)(20466 ^ -14738 ^ 6879 ^ -27837));
      this.method_btO();
   }

   private static int method_bvO(0cz var0) {
      return var0.height;
   }

   private static 0dC method_buL() {
      return field_c;
   }

   private static List method_btY(0cz var0) {
      return var0.buttonList;
   }

   private static 0dC method_bvq() {
      return field_c;
   }

   private static 0cy method_buX(0cz var0) {
      return var0.field_d;
   }

   private static int method_btW(0cz var0) {
      return var0.width;
   }

   private static int method_bug(0cz var0) {
      return var0.height;
   }

   private static 0cy method_buy(0cz var0) {
      return var0.field_a;
   }

   private static int method_buv(0cz var0) {
      return var0.height;
   }

   private static int method_bvR(jK var0) {
      return var0.id;
   }

   private static 0cy method_bvz(0cz var0) {
      return var0.field_d;
   }

   private static List method_bvr(0cz var0) {
      return var0.buttonList;
   }

   private static int method_bup(0cz var0) {
      return var0.height;
   }

   private static 0cy method_bvu(0cz var0) {
      return var0.field_a;
   }

   private static jH method_bvJ(0cz var0) {
      return var0.fontRenderer;
   }

   private static lE method_bum(0cz var0) {
      return var0.field_b;
   }

   private static 0dC method_bva() {
      return field_c;
   }

   private static 0dC method_bvf() {
      return field_c;
   }

   public static void method_btQ() {
      try {
         JSONObject a = new JSONObject(0ee.readFile(0ed.method_bFf(method_btU("ܮݏݤݮݖݠݳݤܮݬݢݱݳݮݹݸܯݫݲݮݯ"))));
         method_bvm().method_bBK(a.getString(method_btU("ݠݥݥݳݤݲݲ")));
         method_bvn().method_bBR(0dB.getType(a.getString(method_btU("ݵݸݱݤ"))));
         method_bvo().method_bBM(a.getString(method_btU("ݴݲݤݳݯݠݬݤ")));
         method_bvp().method_bBO(a.getString(method_btU("ݱݠݲݲݶݮݳݥ")));
      } catch (Exception var1) {
      }

   }

   private static int method_buu(0cz var0) {
      return var0.width;
   }

   private static nC method_buG(0cz var0) {
      return var0.mc;
   }

   private static 0cy method_bvb(0cz var0) {
      return var0.field_d;
   }

   private static jH method_bvB(0cz var0) {
      return var0.fontRenderer;
   }

   private static 0dC method_buC() {
      return field_c;
   }

   private static 0cy method_bvI(0cz var0) {
      return var0.field_a;
   }

   private static 0cy method_bve(0cz var0) {
      return var0.field_a;
   }

   private static 0dC method_buA() {
      return field_c;
   }

   private static 0dB method_buS() {
      return 0dB.field_c;
   }

   private static void method_buQ(0dC var0) {
      field_c = var0;
   }

   private static lE method_buB(0cz var0) {
      return var0.field_b;
   }

   private static lg method_buH(0cz var0) {
      return var0.field_e;
   }

   private static int method_bvN(0cz var0) {
      return var0.width;
   }

   private static void method_buJ(0dC var0) {
      field_c = var0;
   }
}
