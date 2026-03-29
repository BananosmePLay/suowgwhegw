package neo;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class 0ex {
   public static 0ey roundedShader = new 0ey(QSoeDmyFOl("ڻڦڼڧڭڬڭڛڬڪڽ"));
   public static 0ey roundedOutlineShader = new 0ey(QSoeDmyFOl("ڻڦڼڧڭڬڭچڼڽڥڠڧڬڛڬڪڽ"));
   public static final 0ey roundedTexturedShader = new 0ey(QSoeDmyFOl("ڧڬڦھڨڻڬۦںڡڨڭڬڻںۦڻڦڼڧڭۧگڻڨڮ"));
   public static final 0ey roundedGradientShader = new 0ey(QSoeDmyFOl("ڻڦڼڧڭڬڭڛڬڪڽڎڻڨڭڠڬڧڽ"));

   private static 0ey idLllAQma7() {
      return roundedOutlineShader;
   }

   private static 0ey TY9Xg8Yvbr() {
      return roundedGradientShader;
   }

   private static 0ey O7VVQrWRia() {
      return roundedTexturedShader;
   }

   private static 0ey avGJNbdMTN() {
      return roundedTexturedShader;
   }

   public static void drawRound(float x, float y, float width, float height, float radius, Color color) {
      drawRound(x, y, width, height, radius, (boolean)(15183 ^ -11846 ^ 5161 ^ -292), color);
   }

   private static 0ey th0VA9Do54() {
      return roundedGradientShader;
   }

   private static 0ey ovjzOkvWab() {
      return roundedOutlineShader;
   }

   private static 0ey FHT6S78Lsz() {
      return roundedGradientShader;
   }

   private static 0ey bRWoGt9LNt() {
      return roundedShader;
   }

   private static Minecraft _4Gg6dwCwq/* $FF was: 74Gg6dwCwq*/() {
      return 0eB.mc;
   }

   private static 0ey VmLk3v45Md() {
      return roundedOutlineShader;
   }

   private static 0ey NHnOQocOOl() {
      return roundedOutlineShader;
   }

   private static 0ey q0isvnsq3S() {
      return roundedTexturedShader;
   }

   private static 0ey gattGvEgAX() {
      return roundedShader;
   }

   public _ex/* $FF was: 0ex*/() {
   }

   public static void drawRound(float x, float y, float width, float height, float radius, boolean blur, Color color) {
      0ew.resetColor();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(29070 ^ -11151 ^ 15625 ^ -25612, 17519 ^ -4150 ^ 27012 ^ -16094);
      tDBgD8PK1j().init();
      setupRoundedRectUniforms(x, y, width, height, radius, gattGvEgAX());
      0ey var10000 = lAZnjJoJiO();
      String var10001 = QSoeDmyFOl("ګڥڼڻ");
      int[] var10002 = new int[19622 ^ -1100 ^ 3773 ^ -18002];
      var10002[14694 ^ -11877 ^ 19079 ^ -23942] = blur ? 30242 ^ -13169 ^ 16019 ^ -31681 : 5846 ^ -29082 ^ 4820 ^ -30108;
      var10000.setUniformi(var10001, var10002);
      var10000 = oX9jaNe11B();
      var10001 = QSoeDmyFOl("ڪڦڥڦڻ");
      float[] var7 = new float[8827 ^ -18304 ^ 29725 ^ -4382];
      var7[15558 ^ -14371 ^ 32119 ^ -31124] = (float)color.getRed() / Float.intBitsToFloat(5840 ^ 103033 ^ 21183 ^ -2007305742 ^ '곝' ^ 506685 ^ '\ueaa6' ^ -886842718);
      var7[1952 ^ -29667 ^ 22820 ^ -11624] = (float)color.getGreen() / Float.intBitsToFloat(8076 ^ 213569 ^ 'ꓮ' ^ 1108202134 ^ 28751 ^ 93496 ^ '팷' ^ 24176117);
      var7[11912 ^ -2111 ^ 31292 ^ -23689] = (float)color.getBlue() / Float.intBitsToFloat(119098 ^ 93733 ^ 7912 ^ -1447138303 ^ 255805 ^ 219176 ^ 4669 ^ -356425506);
      var7[12981 ^ -24309 ^ 7426 ^ -28993] = (float)color.getAlpha() / Float.intBitsToFloat('靭' ^ 219976 ^ 7919 ^ 356372047 ^ '陇' ^ 102998 ^ '탋' ^ 1447086175);
      var10000.setUniformf(var10001, var7);
      0ey.drawQuads(x - Float.intBitsToFloat(7211 ^ 1022376 ^ 25368 ^ -1198006116 ^ 20268 ^ 1016324 ^ 22745 ^ -2028497930), y - Float.intBitsToFloat(125558 ^ 83645 ^ 7802 ^ -663197158 ^ 119179 ^ 128142 ^ 12025 ^ -403123369), width + Float.intBitsToFloat(110876 ^ 124991 ^ 2255 ^ 1081654614 ^ 130888 ^ 16579 ^ 130480 ^ 7908993), height + Float.intBitsToFloat('ｮ' ^ '줧' ^ 5877 ^ 793595356 ^ '\uded8' ^ 12785 ^ '셒' ^ 1867334427));
      bRWoGt9LNt().unload();
      GlStateManager.disableBlend();
   }

   public static void drawRoundCircleOut(float x, float y, float radius, float thikness, Color color, Color sidecolor) {
      drawRoundOutline(x - radius / Float.intBitsToFloat(235999 ^ '駫' ^ 260821 ^ 804864494 ^ 239504 ^ '顺' ^ 249600 ^ 1878608869), y - radius / Float.intBitsToFloat(7034 ^ 206955 ^ '꣩' ^ 1110818496 ^ 6956 ^ 224684 ^ '鿌' ^ 37080180), radius, radius, radius / Float.intBitsToFloat(5498 ^ '\uf5a8' ^ 21589 ^ -363514182 ^ 27218 ^ '\ude3e' ^ 5400 ^ -1437260983) - Float.intBitsToFloat(19516 ^ 129552 ^ 27572 ^ 1677774774 ^ 10349 ^ 32743 ^ 17050 ^ 1526794046), thikness, color, sidecolor);
   }

   private static 0ey Sr4gNmQ9gg() {
      return roundedGradientShader;
   }

   private static 0ey lAZnjJoJiO() {
      return roundedShader;
   }

   private static int P4L0DJ0yei(Minecraft var0) {
      return var0.displayHeight;
   }

   private static int SCBTxJYOzy(Minecraft var0) {
      return var0.displayHeight;
   }

   public static void drawGradientVertical(float x, float y, float width, float height, float radius, Color top, Color bottom) {
      drawGradientRound(x, y, width, height, radius, bottom, top, bottom, top);
   }

   private static 0ey QqjZbr1I2S() {
      return roundedOutlineShader;
   }

   public static void drawGradientRound(float x, float y, float width, float height, float radius, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
      0ew.resetColor();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(121 ^ -9023 ^ 26174 ^ -18044, 12452 ^ -24285 ^ 29688 ^ -7812);
      TY9Xg8Yvbr().init();
      setupRoundedRectUniforms(x, y, width, height, radius, FHT6S78Lsz());
      0ey var10000 = Sr4gNmQ9gg();
      String var10001 = QSoeDmyFOl("ڪڦڥڦڻ۸");
      float[] var10002 = new float[11658 ^ -3737 ^ 23576 ^ -32527];
      var10002[9291 ^ -29952 ^ 5255 ^ -17460] = (float)bottomLeft.getRed() / Float.intBitsToFloat('菉' ^ '쟶' ^ 2983 ^ -840938589 ^ 'ꆊ' ^ '\uecbd' ^ 23261 ^ -1902155482);
      var10002[27702 ^ -14903 ^ 16582 ^ -5832] = (float)bottomLeft.getGreen() / Float.intBitsToFloat(261992 ^ 242436 ^ 12936 ^ -1061578955 ^ 115812 ^ 81406 ^ 25070 ^ -2084146779);
      var10002[29930 ^ -3169 ^ 27996 ^ -5589] = (float)bottomLeft.getBlue() / Float.intBitsToFloat(130352 ^ '腞' ^ 125936 ^ -1880600339 ^ 7078 ^ 1013610 ^ 1032516 ^ -862491909);
      var10002[10213 ^ -16814 ^ 15527 ^ -23277] = Float.intBitsToFloat(1561 ^ 117711 ^ 12721 ^ 2031680612 ^ 5757 ^ '騕' ^ 16610 ^ 1203334184);
      var10000.setUniformf(var10001, var10002);
      var10000 = WFtJAhTY1w();
      var10001 = QSoeDmyFOl("ڪڦڥڦڻۻ");
      var10002 = new float[30770 ^ -8247 ^ 12452 ^ -26789];
      var10002[3251 ^ -24950 ^ 7112 ^ -30223] = (float)topLeft.getRed() / Float.intBitsToFloat(29029 ^ 68378 ^ 130496 ^ 115093063 ^ 4882 ^ 30378 ^ 16982 ^ 1168346646);
      var10002[21839 ^ -4510 ^ 2727 ^ -20085] = (float)topLeft.getGreen() / Float.intBitsToFloat(32322 ^ 85765 ^ 129928 ^ 1765701560 ^ 243978 ^ 219110 ^ 1092 ^ 708925407);
      var10002[30223 ^ -9076 ^ 8929 ^ -30624] = (float)topLeft.getBlue() / Float.intBitsToFloat(8855 ^ '뷑' ^ '펻' ^ 65737797 ^ 1857 ^ '蜴' ^ '黼' ^ 1083459121);
      var10002[26001 ^ -32515 ^ 26317 ^ -31838] = Float.intBitsToFloat('葞' ^ '껟' ^ 13840 ^ -844879482 ^ 18975 ^ 12834 ^ 25780 ^ -217806529);
      var10000.setUniformf(var10001, var10002);
      var10000 = WOErY0sQxN();
      var10001 = QSoeDmyFOl("ڪڦڥڦڻۺ");
      var10002 = new float[24894 ^ -456 ^ 4266 ^ -28760];
      var10002[28720 ^ -26149 ^ 15507 ^ -10888] = (float)bottomRight.getRed() / Float.intBitsToFloat(260059 ^ 236492 ^ 25159 ^ -1879444169 ^ 108745 ^ 103020 ^ 30024 ^ -863587190);
      var10002[1894 ^ -25371 ^ 16543 ^ -9443] = (float)bottomRight.getGreen() / Float.intBitsToFloat('쒰' ^ 234043 ^ '\udfba' ^ -1354371942 ^ 7927 ^ '졳' ^ '냈' ^ -331807769);
      var10002[25498 ^ -4373 ^ 32465 ^ -3166] = (float)bottomRight.getBlue() / Float.intBitsToFloat('\ud807' ^ '蟎' ^ 20320 ^ 1091584609 ^ '\uef76' ^ '훋' ^ 27934 ^ 40830571);
      var10002[26064 ^ -19639 ^ 7207 ^ -13635] = Float.intBitsToFloat(2093424 ^ 2068848 ^ 13717 ^ -1653634491 ^ '錦' ^ '諾' ^ 5817 ^ -1546715053);
      var10000.setUniformf(var10001, var10002);
      var10000 = th0VA9Do54();
      var10001 = QSoeDmyFOl("ڪڦڥڦڻ۽");
      var10002 = new float[18815 ^ -16370 ^ 10564 ^ -24527];
      var10002[31266 ^ -24163 ^ 9616 ^ -465] = (float)topRight.getRed() / Float.intBitsToFloat(6056 ^ 122117 ^ 20914 ^ -1849126819 ^ 13234 ^ 250001 ^ 5301 ^ -759825196);
      var10002[12435 ^ -30507 ^ 30503 ^ -12448] = (float)topRight.getGreen() / Float.intBitsToFloat(1028331 ^ 10313 ^ 1031824 ^ 493339775 ^ 123645 ^ 77509 ^ 21286 ^ 1578663251);
      var10002[14920 ^ -23446 ^ 17686 ^ -9418] = (float)topRight.getBlue() / Float.intBitsToFloat('콥' ^ '꾈' ^ 7696 ^ 1918975633 ^ 'ﳶ' ^ 19203 ^ '쿩' ^ 824064112);
      var10002[6871 ^ -4104 ^ 21052 ^ -22768] = Float.intBitsToFloat(17013 ^ 'ꇯ' ^ '윌' ^ -1827204772 ^ 27270 ^ 'ﺚ' ^ 13974 ^ -1380501535);
      var10000.setUniformf(var10001, var10002);
      0ey.drawQuads(x - Float.intBitsToFloat(7484 ^ '뽥' ^ 'Ꞹ' ^ -139976212 ^ '곉' ^ '韃' ^ 2181 ^ -936896638), y - Float.intBitsToFloat('섽' ^ '퐱' ^ 31131 ^ -470875964 ^ 6670 ^ 'ﭷ' ^ '밹' ^ -596692717), width + Float.intBitsToFloat('옯' ^ '锈' ^ 1311 ^ 2106135320 ^ 8107 ^ '訮' ^ '텓' ^ 1032389110), height + Float.intBitsToFloat(24007 ^ '蝯' ^ '촵' ^ -1784140030 ^ '鼙' ^ 'ꃹ' ^ 1337 ^ -710409658));
      lIFa7TPhe6().unload();
      GlStateManager.disableBlend();
   }

   private static 0ey EUwwsNWriL() {
      return roundedOutlineShader;
   }

   private static 0ey lIFa7TPhe6() {
      return roundedGradientShader;
   }

   private static 0ey oX9jaNe11B() {
      return roundedShader;
   }

   private static 0ey V2XPNWwYez() {
      return roundedTexturedShader;
   }

   private static 0ey WuqXeCgtC6() {
      return roundedTexturedShader;
   }

   private static Minecraft Wb7sZc5IJn() {
      return 0eB.mc;
   }

   private static 0ey WOErY0sQxN() {
      return roundedGradientShader;
   }

   private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius, 0ey roundedTexturedShader) {
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      String var10001 = QSoeDmyFOl("ڥڦڪڨڽڠڦڧ");
      float[] var10002 = new float[10291 ^ -24202 ^ 7138 ^ -27995];
      var10002[16484 ^ -21622 ^ 20038 ^ -23128] = x * (float)sr.getScaleFactor();
      var10002[15670 ^ -31005 ^ 7909 ^ -23247] = (float)P4L0DJ0yei(Minecraft.getMinecraft()) - height * (float)sr.getScaleFactor() - y * (float)sr.getScaleFactor();
      roundedTexturedShader.setUniformf(var10001, var10002);
      var10001 = QSoeDmyFOl("ڻڬڪڽښڠڳڬ");
      var10002 = new float[9571 ^ -18124 ^ 20673 ^ -13164];
      var10002[26492 ^ -18907 ^ 14479 ^ -5674] = width * (float)sr.getScaleFactor();
      var10002[29305 ^ -22061 ^ 6901 ^ -16034] = height * (float)sr.getScaleFactor();
      roundedTexturedShader.setUniformf(var10001, var10002);
      var10001 = QSoeDmyFOl("ڻڨڭڠڼں");
      var10002 = new float[3144 ^ -22823 ^ 23936 ^ -2288];
      var10002[20154 ^ -28354 ^ 29168 ^ -20876] = radius * (float)sr.getScaleFactor();
      roundedTexturedShader.setUniformf(var10001, var10002);
   }

   public static void drawRoundTextured(float x, float y, float width, float height, float radius, float alpha) {
      0ew.resetColor();
      avGJNbdMTN().init();
      0ey var10000 = q0isvnsq3S();
      String var10001 = QSoeDmyFOl("ڽڬڱڽڼڻڬڀڧ");
      int[] var10002 = new int[19212 ^ -27903 ^ 11205 ^ -3127];
      var10002[7502 ^ -32689 ^ 29267 ^ -4270] = 25399 ^ -12245 ^ 16981 ^ -3767;
      var10000.setUniformi(var10001, var10002);
      setupRoundedRectUniforms(x, y, width, height, radius, WuqXeCgtC6());
      var10000 = V2XPNWwYez();
      var10001 = QSoeDmyFOl("ڨڥڹڡڨ");
      float[] var6 = new float[4455 ^ -21128 ^ 21328 ^ -4274];
      var6[3029 ^ -5298 ^ 24369 ^ -16470] = alpha;
      var10000.setUniformf(var10001, var6);
      0ey.drawQuads(x - Float.intBitsToFloat('낹' ^ '헪' ^ 21631 ^ 665732260 ^ '\uecd9' ^ '뉉' ^ 1808 ^ 405679112), y - Float.intBitsToFloat('蛎' ^ 210801 ^ 592 ^ -1460818231 ^ 23295 ^ '龐' ^ '찒' ^ -1754393509), width + Float.intBitsToFloat(13990 ^ 99951 ^ 23760 ^ -436012012 ^ 27106 ^ 105367 ^ 19823 ^ -1509773545), height + Float.intBitsToFloat(13074 ^ '让' ^ 485 ^ -1931399071 ^ 29527 ^ '\ue2eb' ^ 22023 ^ -857649532));
      O7VVQrWRia().unload();
      GlStateManager.disableBlend();
   }

   public static void drawRoundOutline(float x, float y, float width, float height, float radius, float thickness, Color insideColor, Color outlineColor) {
      ScaledResolution sr = new ScaledResolution(Wb7sZc5IJn());
      GlStateManager.color(Float.intBitsToFloat(24087 ^ '\ueeab' ^ 1924 ^ -1145883743 ^ 5239 ^ '푚' ^ 7741 ^ -2077009271), Float.intBitsToFloat(24327 ^ 24084 ^ 11730 ^ 635801819 ^ 17829 ^ 241049 ^ 246022 ^ 442864928), Float.intBitsToFloat(258054 ^ 235891 ^ 5885 ^ 1230880477 ^ 7699 ^ 239264 ^ 247891 ^ 1994240437), Float.intBitsToFloat(28149 ^ '\ud930' ^ 30134 ^ -103397721 ^ 11603 ^ 13118 ^ 6732 ^ -967408651));
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(31702 ^ -30174 ^ 7554 ^ -4236, 11742 ^ -20604 ^ 6803 ^ -25654);
      EUwwsNWriL().init();
      0ey var10000 = VmLk3v45Md();
      String var10001 = QSoeDmyFOl("ڥڦڪڨڽڠڦڧ");
      float[] var10002 = new float[15696 ^ -17408 ^ 1544 ^ -30886];
      var10002[8266 ^ -4953 ^ 28676 ^ -17175] = x * (float)sr.getScaleFactor();
      var10002[32241 ^ -29700 ^ 7858 ^ -5954] = (float)SCBTxJYOzy(74Gg6dwCwq()) - height * (float)sr.getScaleFactor() - y * (float)sr.getScaleFactor();
      var10000.setUniformf(var10001, var10002);
      var10000 = 14Sq4mM7yE();
      var10001 = QSoeDmyFOl("ںڠڳڬ");
      var10002 = new float[16394 ^ -22733 ^ 25074 ^ -31031];
      var10002[1019 ^ -24256 ^ 26262 ^ -15315] = width * (float)sr.getScaleFactor();
      var10002[14841 ^ -11248 ^ 19862 ^ -24450] = height * (float)sr.getScaleFactor();
      var10000.setUniformf(var10001, var10002);
      var10000 = 6LqIi93NW1();
      var10001 = QSoeDmyFOl("ڻڨڭڠڼں");
      var10002 = new float[7755 ^ -5000 ^ 3245 ^ -353];
      var10002[21839 ^ -22274 ^ 9809 ^ -9248] = radius * (float)sr.getScaleFactor();
      var10000.setUniformf(var10001, var10002);
      var10000 = ovjzOkvWab();
      var10001 = QSoeDmyFOl("ڽڡڠڪڢڧڬںں");
      var10002 = new float[27706 ^ -22284 ^ 24484 ^ -25749];
      var10002[12562 ^ -21065 ^ 20704 ^ -13243] = thickness * (float)sr.getScaleFactor();
      var10000.setUniformf(var10001, var10002);
      var10000 = QqjZbr1I2S();
      var10001 = QSoeDmyFOl("ڪڦڥڦڻ");
      var10002 = new float[7973 ^ -20366 ^ 22540 ^ -2209];
      var10002[13272 ^ -15682 ^ 21901 ^ -23317] = (float)insideColor.getRed() / Float.intBitsToFloat('艗' ^ '딦' ^ 19762 ^ -398452505 ^ 1063 ^ 19177 ^ 6711 ^ -1421920675);
      var10002[27892 ^ -29817 ^ 28978 ^ -27072] = (float)insideColor.getGreen() / Float.intBitsToFloat(14950 ^ 27762 ^ 15646 ^ -1844729791 ^ 12101 ^ 'ꅷ' ^ 15976 ^ -780897519);
      var10002[13287 ^ -27682 ^ 5125 ^ -19394] = (float)insideColor.getBlue() / Float.intBitsToFloat(12802 ^ 12079 ^ 2400 ^ -1043234501 ^ 106229 ^ '\ue354' ^ 117023 ^ -2102517304);
      var10002[22112 ^ -2902 ^ 16945 ^ -7944] = (float)insideColor.getAlpha() / Float.intBitsToFloat(27843 ^ 111577 ^ 25554 ^ -661870221 ^ 25670 ^ 252490 ^ 17282 ^ -1678645195);
      var10000.setUniformf(var10001, var10002);
      var10000 = NHnOQocOOl();
      var10001 = QSoeDmyFOl("ڦڼڽڥڠڧڬڊڦڥڦڻ");
      var10002 = new float[8616 ^ -9069 ^ 13002 ^ -12299];
      var10002[11180 ^ -12362 ^ 17509 ^ -24449] = (float)outlineColor.getRed() / Float.intBitsToFloat(30492 ^ 109493 ^ 8852 ^ -766973859 ^ 20514 ^ 240763 ^ 1425 ^ -1858736216);
      var10002[11433 ^ -31933 ^ 18257 ^ -5958] = (float)outlineColor.getGreen() / Float.intBitsToFloat(232690 ^ 231306 ^ 5214 ^ -23755269 ^ 284 ^ 'ꨀ' ^ 30197 ^ -1108720588);
      var10002[12585 ^ -17267 ^ 26511 ^ -5591] = (float)outlineColor.getBlue() / Float.intBitsToFloat(31295 ^ 32451 ^ 4030 ^ -189627092 ^ 30453 ^ '뵵' ^ 8510 ^ -1211276080);
      var10002[7797 ^ -18823 ^ 8766 ^ -30159] = (float)outlineColor.getAlpha() / Float.intBitsToFloat(6273 ^ '\uebf3' ^ 3688 ^ -1872013022 ^ 108064 ^ '많' ^ 124457 ^ -753639937);
      var10000.setUniformf(var10001, var10002);
      0ey.drawQuads(x - (Float.intBitsToFloat(21736 ^ 500290 ^ 521261 ^ 984852787 ^ 4760 ^ '龈' ^ 28170 ^ 2058569902) + thickness), y - (Float.intBitsToFloat(2564 ^ 2096223 ^ 9433 ^ -623913345 ^ 17331 ^ 23325 ^ 7523 ^ -1697644240) + thickness), width + Float.intBitsToFloat(2008 ^ 504673 ^ 27466 ^ 1389610664 ^ 17673 ^ 494290 ^ 12943 ^ 307487759) + thickness * Float.intBitsToFloat(2724 ^ 127800 ^ 30673 ^ 1789823302 ^ 24903 ^ 127421 ^ 151 ^ 716085094), height + Float.intBitsToFloat(7846 ^ '\ue5cc' ^ 11381 ^ -198815262 ^ 5779 ^ '\udb1f' ^ 7603 ^ -1264167230) + thickness * Float.intBitsToFloat(129429 ^ 92901 ^ 12812 ^ 73433007 ^ 129363 ^ 125706 ^ 30485 ^ 1147190175));
      idLllAQma7().unload();
      GlStateManager.enableAlpha();
   }

   private static 0ey tDBgD8PK1j() {
      return roundedShader;
   }

   public static void drawRoundScale(float x, float y, float width, float height, float radius, Color color, float scale) {
      drawRound(x + width - width * scale, y + height / Float.intBitsToFloat('픚' ^ 80516 ^ 16898 ^ 1716319966 ^ '작' ^ '鳯' ^ 16128 ^ 642596668) - height / Float.intBitsToFloat(19698 ^ '阥' ^ 16089 ^ -1011456349 ^ 17860 ^ '\uf211' ^ 28644 ^ -2085201252) * scale, width * scale, height * scale, radius, (boolean)(18911 ^ -30053 ^ 4618 ^ -11954), color);
   }

   public static void drawGradientHorizontal(float x, float y, float width, float height, float radius, Color left, Color right) {
      drawGradientRound(x, y, width, height, radius, left, left, right, right);
   }

   private static 0ey WFtJAhTY1w() {
      return roundedGradientShader;
   }

   private static 0ey _LqIi93NW1/* $FF was: 6LqIi93NW1*/() {
      return roundedOutlineShader;
   }

   public static void drawRoundCircle(float x, float y, float radius, Color color) {
      drawRound(x - radius / Float.intBitsToFloat('짵' ^ 1019299 ^ '\ud9bf' ^ -692587120 ^ '\ufb10' ^ '볃' ^ 7603 ^ -1766313447), y - radius / Float.intBitsToFloat(22029 ^ 88358 ^ 120793 ^ 286028260 ^ 4439 ^ 12551 ^ 4546 ^ 1359779972), radius, radius, radius / Float.intBitsToFloat('헟' ^ '郙' ^ 4994 ^ 1071580290 ^ 3039 ^ 70476 ^ '鑹' ^ 2145312492) - Float.intBitsToFloat(11596 ^ 18248 ^ 24576 ^ -1062596267 ^ 26827 ^ 245861 ^ 7207 ^ -5656616), color);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String QSoeDmyFOl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31009 ^ -19646 ^ 1572 ^ -13241; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10806 ^ -27174 ^ 19794 ^ -2953));
      }

      return var1.toString();
   }

   private static 0ey _4Sq4mM7yE/* $FF was: 14Sq4mM7yE*/() {
      return roundedOutlineShader;
   }
}
