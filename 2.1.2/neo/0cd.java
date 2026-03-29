package neo;

import java.util.function.Supplier;

public class 0cd extends 0cB {
   public static 0bv RPskip;
   public static 0bv pingServer;
   public static 0bA customname;
   public static 0bv botsTabPrefix;
   private static final 0bx gameguard_info;
   private static final 0bx joinfixer_info;
   public static 0by nickstype;
   private static final 0bx cacheAfter_info;
   public static 0bz cacheAfter;
   private static final 0bx useProxy_info;
   public static 0bz botsPerProxy;
   public static 0bv gameguard;
   public static 0bv removebadproxy;
   public static 0by antibot;
   public static 0bv useProxy;
   public static 0bv webSkipper;
   public static 0by gameguardBlock;
   private static final 0bx customname_info;
   private static final 0bx pingServer_info;
   public static 0bv joinFixer;
   private static final 0bx botsPerProxy_info;
   public static 0bz joinFixerDelay;

   public void onEnable() {
      super.onEnable();
      this.toggle();
   }

   private static 0bv SqFzGBxVkL() {
      return gameguard;
   }

   private static boolean m8OWrGQozq(0bv var0) {
      return var0.value;
   }

   private static 0bv gGyvGjGwGr() {
      return joinFixer;
   }

   public _cd/* $FF was: 0cd*/() {
      super(DLhi2aw9Fi("ޖ\u07bbޠއޱޠޠ\u07bd\u07ba\u07b3ާ"), 0bV.Bots);
      0bC[] var10001 = new 0bC[4118 ^ -31693 ^ 24778 ^ -2823];
      var10001[20330 ^ -11408 ^ 15556 ^ -24354] = nickstype;
      var10001[13521 ^ -31286 ^ 10473 ^ -26125] = customname;
      var10001[24087 ^ -6255 ^ 22715 ^ -7873] = customname_info;
      var10001[483 ^ -18204 ^ 27029 ^ -12143] = antibot;
      var10001[17760 ^ -7574 ^ 19046 ^ -4760] = gameguard;
      var10001[20826 ^ -11684 ^ 23875 ^ -8640] = gameguardBlock;
      var10001[27964 ^ -13027 ^ 19436 ^ -5173] = gameguard_info;
      var10001[12549 ^ -21337 ^ 13094 ^ -20861] = removebadproxy;
      var10001[24132 ^ -18888 ^ 15751 ^ -10765] = webSkipper;
      var10001[399 ^ -18193 ^ 923 ^ -17678] = joinFixer;
      var10001[27468 ^ -7077 ^ 2060 ^ -30959] = joinFixerDelay;
      var10001[21761 ^ -31121 ^ 27269 ^ -17952] = joinfixer_info;
      var10001[3590 ^ -14023 ^ 8153 ^ -10006] = RPskip;
      var10001[12220 ^ -24641 ^ 30889 ^ -14169] = botsTabPrefix;
      var10001[4147 ^ -11911 ^ 3385 ^ -13187] = useProxy;
      var10001[30655 ^ -21305 ^ 8979 ^ -1948] = useProxy_info;
      var10001[29849 ^ -20520 ^ 22662 ^ -31785] = pingServer;
      var10001[536 ^ -12432 ^ 15154 ^ -2485] = pingServer_info;
      var10001[24357 ^ -15679 ^ 26074 ^ -2004] = cacheAfter;
      var10001[25612 ^ -8927 ^ 31332 ^ -15526] = cacheAfter_info;
      var10001[22496 ^ -24652 ^ 22326 ^ -24714] = botsPerProxy;
      var10001[22123 ^ -8539 ^ 15567 ^ -19436] = botsPerProxy_info;
      this.addSetting(var10001);
   }

   static {
      String var10002 = DLhi2aw9Fi("ޚ\u07bd\u07b7\u07bf");
      String var10003 = DLhi2aw9Fi("ޚޱ\u07bbރ\u07b5ަޱ");
      String[] var10004 = new String[32761 ^ -22843 ^ 12869 ^ -5254];
      var10004[11180 ^ -1054 ^ 29307 ^ -24011] = DLhi2aw9Fi("ކ\u07b5\u07baް\u07bb\u07b9");
      var10004[14828 ^ -31284 ^ 2906 ^ -18565] = DLhi2aw9Fi("ޗޡާޠ\u07bb\u07b9");
      var10004[4293 ^ -16181 ^ 27222 ^ -17830] = DLhi2aw9Fi("ޒަ\u07bb\u07b9ޒ\u07bd\u07b8ޱ");
      nickstype = new 0by(var10002, var10003, var10004);
      var10002 = DLhi2aw9Fi("ޖޭޤ\u07b5ާާ");
      var10003 = DLhi2aw9Fi("ޚ\u07bb\u07baޱ");
      var10004 = new String[16221 ^ -23746 ^ 28107 ^ -3666];
      var10004[8108 ^ -29665 ^ 23989 ^ -12794] = DLhi2aw9Fi("ރޱ\u07b8\u07b8ޙ\u07bbަޱ");
      var10004[3950 ^ -11021 ^ 827 ^ -10073] = DLhi2aw9Fi("ޙޡ\u07b8ޠޭޖޭޤ\u07b5ާާ");
      var10004[21293 ^ -20337 ^ 23240 ^ -18072] = DLhi2aw9Fi("ޟޱޭ\u07b6\u07bb\u07b5ަްޗ\u07b8\u07bd\u07b7\u07bf");
      var10004[11738 ^ -24469 ^ 13004 ^ -16514] = DLhi2aw9Fi("ޙ\u07b5ޠ\u07bcޗ\u07b8\u07bd\u07b7\u07bf");
      var10004[32338 ^ -21235 ^ 6260 ^ -13521] = DLhi2aw9Fi("ޕ\u07baޢ\u07bd\u07b8ކޱ\u07ba\u07b5\u07b9ޱ");
      var10004[7225 ^ -30082 ^ 19752 ^ -9366] = DLhi2aw9Fi("ހ\u07b5\u07b6ޗ\u07b5ޤޠ\u07b7\u07bc\u07b5");
      antibot = new 0by(var10002, var10003, var10004);
      gameguard = new 0bv(DLhi2aw9Fi("ޓ\u07b5\u07b9ޱޓޡ\u07b5ަްߴޖޭޤ\u07b5ާާ"), (boolean)(14392 ^ -14839 ^ 15971 ^ -16302));
      var10002 = DLhi2aw9Fi("ޖ\u07b8\u07bb\u07b7\u07bf");
      var10003 = DLhi2aw9Fi("\u07b8\u07bd\u07b9ޱ");
      Supplier var1 = () -> {
         return m8OWrGQozq(SqFzGBxVkL());
      };
      String[] var10005 = new String[18507 ^ -25694 ^ 17899 ^ -27124];
      var10005[30112 ^ -14341 ^ 2717 ^ -18234] = DLhi2aw9Fi("\u07bbަ\u07b5\u07ba\u07b3ޱ");
      var10005[2794 ^ -9455 ^ 30675 ^ -22999] = DLhi2aw9Fi("\u07b9\u07b5\u07b3ޱ\u07baޠ\u07b5");
      var10005[15246 ^ -1257 ^ 7917 ^ -8586] = DLhi2aw9Fi("ޭޱ\u07b8\u07b8\u07bbޣ");
      var10005[21706 ^ -22712 ^ 31750 ^ -28793] = DLhi2aw9Fi("ޣ\u07bc\u07bdޠޱ");
      var10005[22095 ^ -15293 ^ 9239 ^ -18913] = DLhi2aw9Fi("ޤ\u07bd\u07ba\u07bf");
      var10005[23948 ^ -30586 ^ 7954 ^ -13795] = DLhi2aw9Fi("\u07b3ަ\u07b5ޭ");
      var10005[30705 ^ -5855 ^ 21904 ^ -13498] = DLhi2aw9Fi("ާ\u07bd\u07b8ޢޱަ");
      var10005[14481 ^ -27400 ^ 4228 ^ -17174] = DLhi2aw9Fi("\u07b7ޭ\u07b5\u07ba");
      var10005[18634 ^ -22827 ^ 14976 ^ -11113] = DLhi2aw9Fi("ޤޡަޤ\u07b8ޱ");
      var10005[8760 ^ -27197 ^ 6012 ^ -24434] = DLhi2aw9Fi("\u07b6\u07b8ޡޱ");
      var10005[16681 ^ -23827 ^ 12061 ^ -13101] = DLhi2aw9Fi("\u07b6ަ\u07bbޣ\u07ba");
      var10005[12239 ^ -6604 ^ 5446 ^ -9034] = DLhi2aw9Fi("\u07b3ަޱޱ\u07ba");
      var10005[17020 ^ -4509 ^ 19672 ^ -7989] = DLhi2aw9Fi("ަޱް");
      var10005[15385 ^ -25390 ^ 24529 ^ -233] = DLhi2aw9Fi("\u07b6\u07b8\u07b5\u07b7\u07bf");
      gameguardBlock = new 0by(var10002, var10003, var1, var10005);
      String[] var0 = new String[788 ^ -30503 ^ 13423 ^ -16477];
      var0[27731 ^ -20449 ^ 1832 ^ -9372] = DLhi2aw9Fi("ޓ\u07b5\u07b9ޱޓޡ\u07b5ަްߴ߹ߴϣϡϯϡϩΟϡߴϮϩϪϫϮϬߴϬߴΖߺϠ");
      gameguard_info = new 0bx(var0);
      customname = new 0bA(DLhi2aw9Fi("ޗޡާޠ\u07bb\u07b9ߴ\u07ba\u07bd\u07b7\u07bf"), DLhi2aw9Fi("ބ\u07b8\u07b5ޭޱަދ߱\u07ba"), () -> {
         return JAdAyVs9z9().is(DLhi2aw9Fi("ޗޡާޠ\u07bb\u07b9"));
      });
      var0 = new String[14018 ^ -6556 ^ 2295 ^ -10153];
      var0[30680 ^ -30701 ^ 9822 ^ -9835] = DLhi2aw9Fi("ϋϯϡϭΕΑϪϯϠϡΔΟ߮ߴ");
      var0[29829 ^ -22410 ^ 5885 ^ -13809] = DLhi2aw9Fi("߱\u07baߴ߹ߴϴϤϩϠϪϨϩΟϡߴߢߴΒϬΐΔ");
      var0[6326 ^ -1962 ^ 18551 ^ -22379] = DLhi2aw9Fi("߱ާߴ߹ߴϴϤϩϠϪϨϩΟϡߴߢߴϥΗϮϦ");
      var0[9475 ^ -4463 ^ 17602 ^ -28845] = DLhi2aw9Fi("߱ޤ\u07b8\u07b5ޭޱަާߴ߹ߴϴϤϩϠϪϨϩΟϭߴϩϬϮߴϬϣߴΖϤϥϤ");
      var0[4491 ^ -14143 ^ 2061 ^ -11965] = DLhi2aw9Fi("߱ާޣޱ\u07b5ަߴ߹ߴϴϤϩϠϪϨϩϤΛߴϫΔϪϦϪϮϤΒϬΛ");
      var0[8311 ^ -352 ^ 11804 ^ -3890] = DLhi2aw9Fi("ϬߴΖߺϠߺ");
      customname_info = new 0bx(var0);
      removebadproxy = new 0bv(DLhi2aw9Fi("ކޱ\u07b9\u07bbޢޱߴޖ\u07b5ްߴބަ\u07bbެޭ"), (boolean)(556 ^ -18037 ^ 1396 ^ -16685));
      webSkipper = new 0bv(DLhi2aw9Fi("ރޱ\u07b6ߴއ\u07bf\u07bdޤޤޱަ"), (boolean)(31235 ^ -27264 ^ 16248 ^ -12037));
      joinFixer = new 0bv(DLhi2aw9Fi("ޞ\u07bb\u07bd\u07baߴޒ\u07bdެޱަ"), (boolean)(19256 ^ -30840 ^ 2237 ^ -15347));
      joinFixerDelay = new 0bz(DLhi2aw9Fi("ސޱ\u07b8\u07b5ޭ"), Float.intBitsToFloat(21779 ^ 199547 ^ '\ud988' ^ 639213394 ^ 31102 ^ 28943 ^ 5934 ^ 1699874797), Float.intBitsToFloat('척' ^ 219830 ^ 12414 ^ 1981673736 ^ 15516 ^ 243806 ^ 3918 ^ 926801109), Float.intBitsToFloat('쮞' ^ 17000 ^ '\uef5b' ^ 115292235 ^ '썤' ^ 100245 ^ '\ue308' ^ 1118107935), Float.intBitsToFloat('공' ^ 248729 ^ '믵' ^ -642646635 ^ 32386 ^ 232033 ^ 7631 ^ -1735276512), () -> {
         return lMYGUjoDOT(gGyvGjGwGr());
      });
      var0 = new String[11849 ^ -24368 ^ 25064 ^ -4235];
      var0[23146 ^ -26040 ^ 9493 ^ -6857] = DLhi2aw9Fi("ψϡϩΛϡΖߴϫΔϪϮΕϬߴϥϪΖϤߴϫΔϬߴΖϤϭϨϤΗΖϡ");
      var0[16809 ^ -20556 ^ 13990 ^ -10054] = DLhi2aw9Fi("ϫϪϠϮϯΚΓϡϩϬΛߴϮߴΕϡΔϦϡΔΗ");
      var0[4620 ^ -20966 ^ 16756 ^ -672] = DLhi2aw9Fi("ސޱ\u07b8\u07b5ޭߴ߹ߴσϤϠϡΔϢϮϤߴΕϨϡϩΟߴϫΔϪϮΕϬ");
      var0[8302 ^ -6294 ^ 12564 ^ -2541] = DLhi2aw9Fi("ϬߴϫϡΔϡϣϤΑϪϠϤߴϩϤߴΕϡΔϦϡΔ");
      joinfixer_info = new 0bx(var0);
      RPskip = new 0bv(DLhi2aw9Fi("ކޱާ\u07bbޡަ\u07b7ޱބ\u07b5\u07b7\u07bfߴާ\u07bf\u07bdޤ"), (boolean)(14202 ^ -11966 ^ 19867 ^ -21598));
      botsTabPrefix = new 0bv(DLhi2aw9Fi("ޖ\u07bbޠާߴހޕޖߴޤަޱ\u07b2\u07bdެ"), (boolean)(25163 ^ -8724 ^ 1831 ^ -18303));
      useProxy = new 0bv(DLhi2aw9Fi("ށާޱߴބަ\u07bbެޭ"), (boolean)(18725 ^ -19451 ^ 23607 ^ -24298));
      var0 = new String[23806 ^ -24236 ^ 29707 ^ -30297];
      var0[26002 ^ -27554 ^ 6556 ^ -6064] = DLhi2aw9Fi("ωϤΕΖΔϪϭϮϤߴϬΕϫϪϯΘϣϪϦϤϩϬΛߴϫΔϪϮΕϬ");
      var0[894 ^ -24260 ^ 8292 ^ -32217] = DLhi2aw9Fi("ΗߴϥϪΖϪϦߺߴρΕϯϬߴφϿώϏϺϳό϶ϸߴ߹ߴϥϪΖΟ");
      var0[12408 ^ -5534 ^ 11138 ^ -3686] = DLhi2aw9Fi("ϥΗϠΗΖߴϣϤΑϪϠϬΖΘߴυρσߴϋϴϊώϵό߸ߴϩϪ");
      var0[11403 ^ -26974 ^ 16120 ^ -31534] = DLhi2aw9Fi("ϡΕϯϬߴϣϤϥϤϩΛΖߴϪϠϩϪϧϪߴϫϪߴޝބ߸ߴϮϬϮϩϡΖ");
      var0[7767 ^ -5314 ^ 10029 ^ -11712] = DLhi2aw9Fi("ϦΕϡΑߴϦߴΖϪϨߴΓϬΕϯϡߴϬߴϦϤΕߵ");
      var0[15740 ^ -29988 ^ 22482 ^ -8073] = DLhi2aw9Fi("ωϡߴΔϡϮϪϨϡϩϠΗϡΖΕΛߴϬΕϫϪϯΘϣϪϦϤΖΘߵ");
      useProxy_info = new 0bx(var0);
      pingServer = new 0bv(DLhi2aw9Fi("ބ\u07bd\u07ba\u07b3ߴއޱަޢޱަ"), (boolean)(12353 ^ -21638 ^ 29227 ^ -5872));
      var0 = new String[9094 ^ -31131 ^ 18107 ^ -7332];
      var0[24769 ^ -6321 ^ 2546 ^ -29060] = DLhi2aw9Fi("ϋΔϬߴϦϮϯΚΓϡϩϬϬߴϥϪΖߴϥΗϠϡΖߴϫϬϩϧϪϦϤΖΘ");
      var0[18061 ^ -32114 ^ 28137 ^ -22037] = DLhi2aw9Fi("ΕϡΔϦϡΔߴϫϡΔϡϠߴϣϤΑϪϠϪϨߺߴϋΔϬߴΙΖϪϨ");
      var0[15229 ^ -3436 ^ 30744 ^ -19981] = DLhi2aw9Fi("ϪϩϪߴϠϪϥϤϦϬΖߴϫϤΔΗߴΕϡϮΗϩϠߴϫϡΔϡϠ");
      var0[14092 ^ -10251 ^ 21215 ^ -19931] = DLhi2aw9Fi("ϣϤΑϪϠϪϨߴϥϪΖϤߴϩϤߴΕϡΔϦϡΔߵ");
      pingServer_info = new 0bx(var0);
      cacheAfter = new 0bz(DLhi2aw9Fi("ޗ\u07b5\u07b7\u07bcޱߴޣ\u07bbަ\u07b8ްߴ\u07b5\u07b2ޠޱަ"), Float.intBitsToFloat(502270 ^ 500151 ^ 4824 ^ 1417931885 ^ 514486 ^ 520957 ^ 18743 ^ 1795396736), Float.intBitsToFloat(30566 ^ 8200 ^ 29992 ^ -1748133974 ^ 238912 ^ 227146 ^ 1444 ^ -1748147646), Float.intBitsToFloat(32237 ^ 230967 ^ 14604 ^ 1032181939 ^ '讐' ^ 206070 ^ 5677 ^ 2091229998), Float.intBitsToFloat(119543 ^ 112237 ^ 17229 ^ 535747628 ^ 117379 ^ 3483 ^ 118136 ^ 544141723));
      var0 = new String[15257 ^ -1440 ^ 26785 ^ -22177];
      var0[30905 ^ -22303 ^ 7517 ^ -13051] = DLhi2aw9Fi("ϷΕΖϤϩϪϦϮϤߴϩϪϨϡΔϤߴϨϬΔϤ߸ߴϫϪΕϯϡ");
      var0[19191 ^ -22537 ^ 19530 ^ -24245] = DLhi2aw9Fi("ϮϪΖϪΔϪϧϪߴΓϤϩϮϬߴϩϤΓϬϩϤΚΖ");
      var0[16959 ^ -12597 ^ 10059 ^ -21571] = DLhi2aw9Fi("ϮΙΜϬΔϪϦϤΖΘΕΛߴΗߴϥϪΖϤߺ");
      var0[30179 ^ -15312 ^ 24565 ^ -4571] = DLhi2aw9Fi("ߤߴ߹ߴώΙΜϬΔϪϦϤΖΘߴϦΕϡϧϠϤ");
      var0[19255 ^ -13171 ^ 6412 ^ -24910] = DLhi2aw9Fi("ߥߴ߹ߴώΙΜϬΔϪϦϤΖΘߴϫϪΕϯϡߴϤϩΖϬϥϪΖϤ");
      var0[1921 ^ -16422 ^ 10123 ^ -24619] = DLhi2aw9Fi("ϪΕΖϤϯΘϩΟϡߴϣϩϤΓϡϩϬΛߴϨϤϯϪߴϧϠϡ");
      var0[12686 ^ -28475 ^ 24359 ^ -406] = DLhi2aw9Fi("ΖΔϡϥΗΚΖΕΛ");
      cacheAfter_info = new 0bx(var0);
      botsPerProxy = new 0bz(DLhi2aw9Fi("ޖ\u07bbޠާߴޤޱަߴߥߴޤަ\u07bbެޭ"), Float.intBitsToFloat(23502 ^ '\uedf3' ^ 20865 ^ -1961335514 ^ 7184 ^ '댺' ^ 2652 ^ -1265096724), Float.intBitsToFloat('\uf533' ^ 225691 ^ 3634 ^ 1743113052 ^ '횴' ^ 213822 ^ 8158 ^ 1483066258), Float.intBitsToFloat(28907 ^ 29606 ^ 11547 ^ -937425393 ^ 20703 ^ '釹' ^ '\uef5e' ^ -1972894175), Float.intBitsToFloat(1016192 ^ '軄' ^ 1024120 ^ -10000825 ^ 11555 ^ 1030838 ^ 1042003 ^ -1058555715));
      var0 = new String[32022 ^ -6100 ^ 31316 ^ -4244];
      var0[17432 ^ -3835 ^ 6194 ^ -21201] = DLhi2aw9Fi("ϷΕΖϤϩϪϦϮϤߴϮϪϯϬΓϡΕΖϦϤߴϥϪΖϪϦ");
      var0[7818 ^ -16960 ^ 30385 ^ -10758] = DLhi2aw9Fi("ϩϤߴϣϤΑϪϠߴΕߴߥߴϫΔϪϮΕϬ");
      botsPerProxy_info = new 0bx(var0);
   }

   private static 0by JAdAyVs9z9() {
      return nickstype;
   }

   private static boolean lMYGUjoDOT(0bv var0) {
      return var0.value;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String DLhi2aw9Fi(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6097 ^ -5730 ^ 29943 ^ -30024; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31656 ^ -41021 ^ 'ﲧ' ^ -8424));
      }

      return var1.toString();
   }
}
