package neo;

import java.awt.Color;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.util.SoundEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class 0cx extends lg implements 0cC {
   public ArrayList<0cw> field_c;
   public static 0ep field_a = new 0ep(method_boN("ڜےۀۀۖۇۀڜ۞ۚ\u06ddۖېہےەۇڜ\u06ddۖۜۄےہۖڜۀۛےۗۖہۀڜۑےېۘ۔ہۜۆ\u06ddۗڝۅۀۛ"));
   public static final long field_b = System.currentTimeMillis();
   private static String _DSC GG NEOWARECLIENT _;

   private static nC method_boO(0cx var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_boN(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 330 ^ -29576 ^ 8635 ^ -21367; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21002 ^ -12382 ^ 31284 ^ -7889));
      }

      return var1.toString();
   }

   private static ArrayList method_boQ(0cx var0) {
      return var0.field_c;
   }

   private static SoundEvent method_bph() {
      return NO.UI_BUTTON_CLICK;
   }

   private static nC method_bpk(0cx var0) {
      return var0.mc;
   }

   private static nC method_bpg(0cx var0) {
      return var0.mc;
   }

   private static 0dY method_bpc() {
      return 0dZ.field_d;
   }

   private static nC method_boW(0cx var0) {
      return var0.mc;
   }

   private static nC method_bpl(0cx var0) {
      return var0.mc;
   }

   private static ArrayList method_boS(0cx var0) {
      return var0.field_c;
   }

   private static 0bz method_bpb() {
      return client;
   }

   public void mouseClicked(int b, int c, int d) {
      Iterator var4 = method_bpf(this).iterator();

      while(var4.hasNext()) {
         0cw a = (0cw)var4.next();
         if (a.method_boh(b, c)) {
            method_bpg(this).getSoundHandler().playSound(iN.getMasterRecord(method_bph(), Float.intBitsToFloat('鏭' ^ '탧' ^ 2288 ^ 1066285846 ^ '颒' ^ '텏' ^ 15749 ^ 918708)));
            switch (a.method_bof()) {
               case 1:
                  method_bpi(this).displayGuiScreen(new lJ(this));
                  break;
               case 2:
                  method_bpj(this).displayGuiScreen(new kI(this));
                  break;
               case 3:
                  method_bpk(this).displayGuiScreen(new 0ck());
                  break;
               case 4:
                  method_bpl(this).displayGuiScreen(new kL(this, method_bpm()));
                  break;
               case 5:
                  method_bpn(this).shutdown();
                  break;
               case 6:
                  this.openWebLink(URI.create(method_boN("ۛۇۇۃۀډڜڜۗۚۀېۜہۗڝ۔۔ڜڅڇ\u06ddۻۦ۞۔۞۰۷")));
            }
         }
      }

   }

   private static nC method_bpn(0cx var0) {
      return var0.mc;
   }

   private static Bj method_bpm() {
      return nC.gameSettings;
   }

   private static ArrayList method_boU(0cx var0) {
      return var0.field_c;
   }

   private static long method_boZ() {
      return field_b;
   }

   public void initGui() {
      mC a = new mC(method_boO(this));
      method_boP(this, new ArrayList());
      method_boQ(this).add(new 0cw(15856 ^ -19613 ^ 4943 ^ -25123, a.getScaledWidth() / (19445 ^ -30315 ^ 839 ^ -16091), a.getScaledHeight() / (5815 ^ -1405 ^ 25934 ^ -30344) - (11729 ^ -6420 ^ 28683 ^ -17637), 6152 ^ -5882 ^ 26852 ^ -26226, 17352 ^ -16226 ^ 1969 ^ -31497, 0cT.method_byX(method_boN("ې۟ۚۖ\u06ddۇڝ۔ۆۚڝ۞ےۚ\u06ddڝۀۚ\u06dd۔۟ۖۃ۟ےۊۖہ"))));
      method_boR(this).add(new 0cw(27143 ^ -23564 ^ 22221 ^ -24772, a.getScaledWidth() / (3202 ^ -26497 ^ 12392 ^ -23401), a.getScaledHeight() / (17505 ^ -2844 ^ 5833 ^ -22962) - (6060 ^ -8843 ^ 17324 ^ -30376) + (7418 ^ -99 ^ 15854 ^ -8547), 17297 ^ -5415 ^ 2673 ^ -23715, 29635 ^ -8823 ^ 12485 ^ -24929, 0cT.method_byX(method_boN("ې۟ۚۖ\u06ddۇڝ۔ۆۚڝ۞ےۚ\u06ddڝ۞ۆ۟ۇۚۃ۟ےۊۖہ"))));
      method_boS(this).add(new 0cw(5296 ^ -14352 ^ 19840 ^ -24893, a.getScaledWidth() / (31864 ^ -20112 ^ 30261 ^ -17601), a.getScaledHeight() / (32667 ^ -20273 ^ 1752 ^ -13938) - (30268 ^ -32293 ^ 13641 ^ -15741) + (440 ^ -31538 ^ 20182 ^ -13432), 15243 ^ -12570 ^ 19310 ^ -16793, 9390 ^ -23713 ^ 12293 ^ -18460, 0cT.method_byX(method_boN("ې۟ۚۖ\u06ddۇڝ۔ۆۚڝ۞ےۚ\u06ddڝےېېۜۆ\u06ddۇۀ"))));
      method_boT(this).add(new 0cw(1559 ^ -2865 ^ 222 ^ -3582, a.getScaledWidth() / (15261 ^ -31350 ^ 14900 ^ -31711), a.getScaledHeight() / (6817 ^ -3679 ^ 11933 ^ -14945) - (23198 ^ -4765 ^ 16004 ^ -30380) + (29636 ^ -26018 ^ 19019 ^ -23571), 22474 ^ -19794 ^ 32524 ^ -26100, 3890 ^ -13470 ^ 25691 ^ -24549, 0cT.method_byX(method_boN("ې۟ۚۖ\u06ddۇڝ۔ۆۚڝ۞ےۚ\u06ddڝۜۃۇۚۜ\u06ddۀ"))));
      method_boU(this).add(new 0cw(5358 ^ -21421 ^ 22427 ^ -4317, a.getScaledWidth() / (29713 ^ -8851 ^ 16711 ^ -6087), a.getScaledHeight() / (24260 ^ -12468 ^ 4943 ^ -32059) - (22968 ^ -30193 ^ 29537 ^ -24325) + (29220 ^ -8495 ^ 25536 ^ -12443), 29007 ^ -7410 ^ 28305 ^ -844, 23424 ^ -19612 ^ 29897 ^ -25539, 0cT.method_byX(method_boN("ې۟ۚۖ\u06ddۇڝ۔ۆۚڝ۞ےۚ\u06ddڝۖۋۚۇ"))));
      method_boV(this).add(new 0cw(22243 ^ -1980 ^ 29856 ^ -9727, a.getScaledWidth() / (4478 ^ -1610 ^ 15279 ^ -11419), a.getScaledHeight() / (28392 ^ -24472 ^ 11715 ^ -7359) - (7021 ^ -28825 ^ 14786 ^ -21019) + (28218 ^ -8018 ^ 25826 ^ -5606), 24132 ^ -2904 ^ 30318 ^ -8986, 10701 ^ -16966 ^ 21824 ^ -16089, method_boN("۷ۚۀېۜہۗ")));
   }

   public void drawScreen(int a, int b, float c) {
      mC d = new mC(method_boW(this));
      if (!method_boX().method_bJx()) {
         method_boY().method_bJu(d.getScaledWidth(), d.getScaledHeight(), (float)a, (float)b, (float)(System.currentTimeMillis() - method_boZ()) / Float.intBitsToFloat(4794 ^ 255785 ^ 233563 ^ 1397072587 ^ '\ue954' ^ 239905 ^ '\uf1b6' ^ 402519744));
         GL11.glBegin(18194 ^ -14560 ^ 19572 ^ -13247);
         GL11.glVertex2f(Float.intBitsToFloat(549 ^ 126309 ^ 108157 ^ 849911959 ^ 118059 ^ 101225 ^ 1378 ^ -1926714742), Float.intBitsToFloat('철' ^ '\uee7f' ^ 11004 ^ -1789046098 ^ '\uf4f8' ^ 22661 ^ '욂' ^ 719138930));
         GL11.glVertex2f(Float.intBitsToFloat('꺘' ^ 115418 ^ '덞' ^ -1942817779 ^ 'Ꞑ' ^ 29701 ^ '\uea93' ^ 867373591), Float.intBitsToFloat('귤' ^ 102229 ^ '땾' ^ 1141569114 ^ 'ꯀ' ^ 95438 ^ 23075 ^ 2072700088));
         GL11.glVertex2f(Float.intBitsToFloat('텣' ^ 17799 ^ '쑗' ^ 1293685668 ^ 8551 ^ 27154 ^ 12102 ^ 1922836260), Float.intBitsToFloat('\ue95d' ^ 83012 ^ 5237 ^ -51059613 ^ '𤋮' ^ '謾' ^ 2031 ^ -1015731278));
         GL11.glVertex2f(Float.intBitsToFloat(22747 ^ 8364164 ^ 1613 ^ 1228920662 ^ 'ꂥ' ^ 8356134 ^ 'ﻍ' ^ 1992291850), Float.intBitsToFloat(126701 ^ 101465 ^ 2353 ^ -1777300112 ^ 481 ^ 109652 ^ 124421 ^ 697347397));
         GL11.glEnd();
         GL20.glUseProgram(5530 ^ -4703 ^ 5815 ^ -4468);
         yh.disableCull();
      } else {
         0eo.method_bIF(Float.intBitsToFloat(5753 ^ 25553 ^ 17075 ^ 1114347665 ^ 21876 ^ 32138 ^ 3561 ^ 1114344093), Float.intBitsToFloat(22869 ^ 26171 ^ 5497 ^ -1460927414 ^ 'ꐕ' ^ 497190 ^ '\ue147' ^ -1460929239), (float)d.getScaledWidth(), (float)d.getScaledHeight(), Float.intBitsToFloat(117777 ^ 116768 ^ 1423 ^ -289484708 ^ 124382 ^ 90007 ^ 14000 ^ -1346478821), method_bpa());
      }

      yh.pushMatrix();
      0cS e = method_bpb().method_Qt();
      0eo.method_bII((float)d.getScaledWidth() / Float.intBitsToFloat(111435 ^ 115635 ^ 15506 ^ -568281307 ^ 115902 ^ 121179 ^ 20949 ^ -1642023041) - Float.intBitsToFloat(22233 ^ '闎' ^ 2489 ^ -1288352640 ^ 20057 ^ '꾸' ^ 9068 ^ -239647581), (float)d.getScaledHeight() / Float.intBitsToFloat(515692 ^ 523592 ^ 7986 ^ 1997790120 ^ 502758 ^ 513724 ^ 23197 ^ 924041337) - Float.intBitsToFloat(2453 ^ 492286 ^ 19315 ^ 1310442497 ^ 14535 ^ 460619 ^ '볙' ^ 213616460), Float.intBitsToFloat(1055 ^ '饡' ^ '뫧' ^ 451549078 ^ 'Ꝋ' ^ '귰' ^ 4810 ^ 1508386943), Float.intBitsToFloat(112036 ^ '里' ^ 119630 ^ 2019309507 ^ 19310 ^ '\ue455' ^ 15615 ^ 997997316), Float.intBitsToFloat(3936 ^ 9318 ^ 10784 ^ -61746128 ^ 16144 ^ 17806 ^ 938 ^ -1118720990), e.method_byP(2188 ^ -13760 ^ 230 ^ -15830), e.method_byP(21403 ^ -31551 ^ 16240 ^ -5936), e.method_byP(16912 ^ -16136 ^ 30881 ^ -1881), e.method_byP(22375 ^ -29421 ^ 17479 ^ -25125));
      0eo.method_bIF((float)d.getScaledWidth() / Float.intBitsToFloat(7937 ^ 26665 ^ 5695 ^ -180674177 ^ '\ue809' ^ 88218 ^ 8708 ^ -1254436097) - Float.intBitsToFloat(25078 ^ 102290 ^ 8256 ^ 992402507 ^ 17011 ^ 119864 ^ 7271 ^ 2040830019) + Float.intBitsToFloat('龅' ^ 109965 ^ '\ude01' ^ 285531294 ^ 18370 ^ 99763 ^ 2313 ^ 1359281135), (float)d.getScaledHeight() / Float.intBitsToFloat(20354 ^ 226047 ^ 248927 ^ -1850128706 ^ 240163 ^ '놵' ^ 261637 ^ -776383985) - Float.intBitsToFloat(2095307 ^ 2065488 ^ 4200 ^ 34581453 ^ 2081344 ^ 2073510 ^ 704 ^ 1085252632) + Float.intBitsToFloat(23563 ^ 'ꒈ' ^ 19411 ^ 812727297 ^ 26271 ^ '韛' ^ 21315 ^ 1886465366), Float.intBitsToFloat('눐' ^ '꼦' ^ 12338 ^ -1196143897 ^ 'ꨄ' ^ '銱' ^ 29791 ^ -95933687), Float.intBitsToFloat(240183 ^ 239702 ^ 9726 ^ 1435621455 ^ 233980 ^ 241855 ^ 24839 ^ 378388372), Float.intBitsToFloat('\udd0d' ^ 'ꂋ' ^ 25542 ^ 1175872417 ^ '釈' ^ 'Ꝃ' ^ 10061 ^ 118909990), new Color(9335 ^ -24310 ^ 16080 ^ -17476, 30337 ^ -5353 ^ 24983 ^ -1008, 3226 ^ -4606 ^ 7655 ^ -146, 4593 ^ -22604 ^ 6401 ^ -20556));
      method_bpc().method_bEH(method_boN("۽ۖۜۤےہۖړ۰۟ۚۖ\u06ddۇ"), (float)d.getScaledWidth() / Float.intBitsToFloat('\uf5bb' ^ 27872 ^ '삯' ^ 387848634 ^ 9178 ^ 221592 ^ '\uf75c' ^ 1461581136), (float)d.getScaledHeight() / Float.intBitsToFloat(14418 ^ '쀗' ^ 32137 ^ -1921378571 ^ 106239 ^ '괨' ^ 117674 ^ -847615164) - Float.intBitsToFloat('뾲' ^ '뺒' ^ 9082 ^ 230232310 ^ 3817 ^ 124168 ^ 27253 ^ 1328853816));
      method_bpd().method_bED(method_boN("ۅ") + 0dH.method_bDy(), (float)d.getScaledWidth() / Float.intBitsToFloat(109856 ^ 84699 ^ 2067 ^ -1437707655 ^ 105165 ^ '꤇' ^ 121704 ^ -363965133) + Float.intBitsToFloat(114971 ^ 104509 ^ 28766 ^ -598941834 ^ 112841 ^ 102576 ^ 15342 ^ -1639127655), (float)d.getScaledHeight() / Float.intBitsToFloat(14898 ^ 88971 ^ '턩' ^ 2047934430 ^ '\udb81' ^ 87465 ^ 6241 ^ 974182663) - Float.intBitsToFloat(243325 ^ 258749 ^ 32256 ^ 613536985 ^ 515947 ^ 506242 ^ 8525 ^ 1711516093), (new Color(8303 ^ -7252 ^ 13522 ^ -2139, 23174 ^ -10506 ^ 12156 ^ -23624, 5974 ^ -3230 ^ 10750 ^ -12930)).getRGB());
      method_bpe(this).forEach((cx) -> {
         cx.method_bog(a, b);
      });
      yh.popMatrix();
      super.drawScreen(a, b, c);
   }

   private void openWebLink(URI d) {
      try {
         Class<?> a = Class.forName(method_boN("ۙےۅےڝےۄۇڝ۷ۖۀۘۇۜۃ"));
         Object b = a.getMethod(method_boN("۔ۖۇ۷ۖۀۘۇۜۃ")).invoke((Object)null);
         String var10001 = method_boN("ۑہۜۄۀۖ");
         Class[] var10002 = new Class[15022 ^ -7583 ^ 27951 ^ -18975];
         var10002[9236 ^ -23390 ^ 14310 ^ -18608] = URI.class;
         Method var10000 = a.getMethod(var10001, var10002);
         Object[] var6 = new Object[226 ^ -22019 ^ 22257 ^ -17];
         var6[28994 ^ -20118 ^ 23327 ^ -25801] = d;
         var10000.invoke(b, var6);
      } catch (Throwable var4) {
         Throwable c = var4;
         c.printStackTrace();
      }

   }

   private static nC method_bpj(0cx var0) {
      return var0.mc;
   }

   private static ArrayList method_bpf(0cx var0) {
      return var0.field_c;
   }

   private static ArrayList method_boT(0cx var0) {
      return var0.field_c;
   }

   private static void method_boP(0cx var0, ArrayList var1) {
      var0.field_c = var1;
   }

   private static 0ep method_boX() {
      return field_a;
   }

   private static 0dY method_bpd() {
      return 0dZ.field_g;
   }

   private static 0ep method_boY() {
      return field_a;
   }

   private static ArrayList method_bpe(0cx var0) {
      return var0.field_c;
   }

   private static nC method_bpi(0cx var0) {
      return var0.mc;
   }

   private static ArrayList method_boV(0cx var0) {
      return var0.field_c;
   }

   private static Color method_bpa() {
      return Color.DARK_GRAY;
   }

   private static ArrayList method_boR(0cx var0) {
      return var0.field_c;
   }

   public _cx/* $FF was: 0cx*/() {
      client.method_Qo().method_bza();
   }
}
